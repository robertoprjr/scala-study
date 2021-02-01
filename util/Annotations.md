FP

Week1

Martin Odersky

imperative programming
functional programming
logic programming

object-oriented programing (a mix of the others)


imperative programming
- modifing mutable variables
- using assignments
- control structures such as if-then-else, loops
  Von Neumann style

functional programming
- concentrate on defining theories for operators expressed as funcions
- avoid mutations
- have powerful ways to abstract and compose functions

(high level concepts for math theories, there's no place for mutation)

In a restrited sense, functional programming (FP) means programming without mutable variables, assignments, loops and other imperative control strucure
In a wider sense, FP means focusing on the functions
In particular, functions can be values that are produced, consumed and composed

Its easier with a FP language but it can be done in any language

In add
- simpler reasoning principles
- better modularity
- good for exploiting parallelism for multicore and cloud computing
  (video -> working hard to keep it simple)

fundamental problem
```java
var x = 0
async { x = x + 1}
async { x = x * 2}
// it can give 0, 1 or 2
```

Non-determinism = parallel processing + mutable state
Parallelism is a reality so we need to cut the use of mutable state (using FP)
To get deterministic processing, avoid the mutable state

control the space, no the time (we will have a lot of threads, we cant control the time)

```scala
// Serial
val people: Array[Person]
val (minors, adults) = people partition (_.age < 18)
```

```scala
// Parallel
val people: Array[Person]
val (minors, adults) = people.par partition (_.age < 18)
```

Call-by-name CBN x Call-by-value CBV

Scala normally use call-by-value

https://stackoverflow.com/questions/13337338/call-by-name-vs-call-by-value-in-scala-clarification-needed#:~:text=The%20same%20value%20will%20be,that%20particular%20parameter%20is%20called.

``` scala
if (b) e1 else e2
if (true) e1 else e2 -> e1
if (false) e1 else e2 -> e2
```
