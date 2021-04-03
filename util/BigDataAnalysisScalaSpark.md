# Big Data Analysis with Scala and Spark

## Data Parallel 
* Shared Memory - Data partitioned in memory and operated upon in parallel with threads and multiple cores, using the shared memory
* Distributed - Data partitioned among machines and networks using the separated memory and operated in parallel

Our focus using big data and spark is **distributed** data execution in parallel

## Distributed Data Parallel

**Main points to worry about on it:**
1. **Partial failure**: when crash a machine or a subset of machines involved in the distributed computation... 

*What can we do to minimize it?* 

- With a good recovery and replication of the information

*Spark can solve this problem?* 

- No, it is a problem about infrastructure

2. **Latency**: the time delay of an execution, where it depends on the location of the information being processed... in general lines:
    - Memory: fastest
    - Disk: slow
    - Network: slowest
    
*Why can we do to minimize it?*
- Processing data more in memory than on disk

*Spark can solve this problem?*
- Yes, because Spark is optimized to process data in memory

# RDD

RDD is Resilient Distributed Datasets.

RDDs are the Spark's distributed collections

RDDs seems a lot like ***immutable*** sequential or parallel Scala collections, like Lists

There is a simplification:
```scala
abstract class RDD[T] {
    def map[U](f: T => U): RDD[U] = ...
    def flatMap[U](f: T=> TransversableOnce[U]): RDD [U] = ...
    def filter(pred: T => Boolean): RDD[T] = ...
    def reduce(op: (T, T) => T): T = ...
    def fold...
    def aggregate...
}
```
RDDs can be create for two ways:
- Transforming an existing RDD
- From SparkContext (or SparkSession) object

The SparkContext object (renamed to SparkSession) can be thought of as your handle of the Spark cluster. **It represents the connection between the Spark cluster and your application**. It defines a handful of methods to create or populate a new RDD, such as:
- ``parallelize``: convert a local Scala collection to an RDD
- ``textfile``: read a text from HDFS or local file system and return a RDD of strings

``parallelize`` is not so used so much in real development because usually we'll take a table or text file and load it in a RDD, not transform another object in a RDD, but there is an example to see how SparkContext works 

```scala
import org.apache.spark._

val wlist = List("scala", "java",
  "hadoop",
  "spark",
  "akka",
  "spark vs hadoop",
  "pyspark",
  "pyspark and spark")

val conf = new SparkConf().setAppName("spark-test").setMaster("local[*]")

val sc = new SparkContext(conf)

val words = sc.parallelize(wlist)

val counts = words.count()

counts
```

## RDD Transformations and Actions

Thinking about the Scala collections, we have:
- **Transformers** return new Collections as a result. (Not a single valeu) e.g. ``map, filter, flatMap, groupBy``
- **Accessors** return a single value as a result (Not a collection) e.g. ``reduce, fold, aggregate``

Going at the same line, Spark defines Transformations and Actions on RDDs:
- **Transformations** return new Collections of RDDs. **They are ``lazy``, their result RDD is not immediately computed**  
- **Actions** compute a result based on an RDD, and either returned or saved to an external storage system (e.g. HDFS). **They are ``eager``, their result is immediately computed** 

> **Lazyness/eagerness** is how we can limit network communication using the programming model

LAZY: Common transformations: ``map, filter, flatMap, distinct``

EAGER: Common actions: ``collect, take, count, reduce, foreach``
(look the return of the function... if it isn't an RDD, its eager)

## Two-RDD transformations

RDD also support set-like operations, like union and intersection

Two-RDD transformations combine two RDDs are combine into one.
``union, intersection, subtract, cartesian``, they are LAZY too.

### Other Useful RDD Actions

- ``takeSample`` : Return an array with a random sample of elements
- ``takeOrdened`` : Return the first n elements of the RDD using their natural order or a custom comparator
- ``saveAsTextFile``
- ``saveAsSequencialFile``

They are EAGER.
 
### Caching and Persistence

By default, RDDs are recomputed each time we run an action on them

To avoid this, can be use function ``cache()`` or ``persist()``, so Spark will store that RDDs in memory (or disk) to another use

Possible to persist data set:
- in memory as regular Java objects
- on disk as regular Java objects
- in memory as serialized Java objects
- on disk as serialized Java objects
- both in memory and on disk (spill over to disk to avoid recomputation)

``cache`` :
Use the default, as is in memory only as regular Java objects

``persist`` :
The type of persistence can be configurated in this method. Pass the storage level you'd like as a parameter to persist

> Despite how similar Spark looks to Scala Collections, this is deferred semantics of the RDDs that you're using, have a very different behavior compared to Scala Collections. So the need to use this cache function to prevent things from being evaluated multiple times.

### Cluster topology

Always have to understand where your code is executing... at the workes (1 each instante/machine) or at the driver (machine how joins the computation)

For example, a ``foreach`` just execute at the workers it did need to reduce or filter nothing meanwhile a ``take(10)`` needs to solve the problem at the driver

### Reduction Operation

Generically, operation like ``reduce, fold and aggregate`` and devivates such as ``foldLeft or reduceRight``

It can be define like:
> Reduce Operations walk though a collection and combine neigboring elements of the collection together to produce a single combined result 

Have to remember that some reduce operations are parallelizable, others not... fold is paralelizable because is associative, foldLeft no.

In Spark, the more desirable reduction operator is the ``agregate``

# Pair-RDDs

In single-node Scala, key value pairs can be thought of as maps (or dictionaries in Python)

Most common in world of big data processing

```scala
// A pair RDD type
RDD[(K,V)]

// New methods just for pair RDD
def groupByKey() ...
def reduceByKey() ...
def join[W]() ...

```

Usually, pair-RDDs are create from another simple RDD, for example:

```scala
val rdd: RDD[Wiki] = ??
var pairRdd = rdd.map(page => (page.title, page.text))
```

## Transformations and actions on Pair-RDDs

Most important operations on Pair-RDDs and not in single RDDs:

Transformations
- groupByKey
- reduceByKey
- mapValues
- keys
- join
- leftOtherJoin / rightOtherJoin

Action
- countByKey

Use of groupBy (remember)
```scala
val ages = List(2, 52, 44, 23, 17, 14, 12)
val groupAge = ages.groupBy { age => 
  if (age >= 18 && age < 65>) "adult"
  else if (age < 18) "child"
  else "senior"       
}
```

``groupByKey`` don´t need the condition because the keys are in the Pair-RDD

```scala
case class Event(organizer: String, name: String, budget: String)

val eventsRdd = sc.parallelize(...).map(event => (event.organizer, event.budget))

val groupedRdd = eventsRdd.groupByKey()
groupedRdd.collect().foreach(println)

//Result looks like:
//(Formatura, CompactBuffer(80000))
//(Casamento, CompactBuffer(60000, 90000))
```

```reduceByKey``` can be thought of as a combination of groupByKey and reduce-ing on all values per key. **It's more efficient though, than using each separately**

```scala
case class Event(organizer: String, name: String, budget: String)

val eventsRdd = sc.parallelize(...).map(event => (event.organizer, event.budget))

val reducedRdd = eventsRdd.reduceByKey(_ + _)
reducedRdd.collect().foreach(println)

//Result looks like:
//(Formatura, 80000)
//(Casamento, 150000)
```

```mapValues``` can be thought of as a short-hand for: 
```scala
rdd.map {case (x, y): (x, func(y))}
```

```countByKey``` simply counts the number of elements per key in a Pair-RDD, returning a normal Scala Map. **remember, it's an action**

```scala
val intermediate = eventsRdd.mapValues(b => (b, 1))
//Result looks like
//(Formatura, (80000, 1))
//(Casamento, (60000, 1), (90000, 1))
val reduceInter = intermediate.reduceByKey((v1, v2) => (v1._1 + v2_.1, v2._1, v2._2))
//Result looks like
//(Formatura, (80000, 1))
//(Casamento, (150000, 2))
val avgBudgets = reduceInter.mapValues { case(v1, v2) => v1 / v2 }
//Result looks like
//(Formatura, 80000, 1))
//(Casamento, (150000, 2))
```

(PairRDDFunctions in Spark API Site)

## Joins
- Inner Joins (join)
- Outer Joins (leftOuterJoin, rightOuterJoin)
