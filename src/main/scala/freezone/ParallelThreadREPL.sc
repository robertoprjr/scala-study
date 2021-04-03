

def power(x: Int, p: Double) = math.exp(p * math.log(math.abs(x))).toInt

def sumSegment(a: Array[Int], p: Double, s: Int, t: Int) = {
  var i = s; var sum: Int = 0
  while (i < t) {
    sum = sum + power(a(i), p)
    i = i + 1
  }
  sum
}

def pNormTwoPart(a: Array[Int], p: Double): Int = {
  val m = a.length / 2
  val (sum1, sum2) = (sumSegment(a, p, 0, m),
                      sumSegment(a, p, m, a.length))
  power(sum1 + sum2, 1/p)
}

// to parallelize it

def parallel[A,B](taskA: => A, taskB: => B): (A,B) = {
  var b = task(taskB)
  var a = taskA
  (a, b.join)
}

def pNormTwoPartParallel(a: Array[Int], p: Double): Int = {
  val m = a.length / 2
  val (sum1, sum2) = parallel (sumSegment(a, p, 0, m),
                               sumSegment(a, p, m, a.length))
  power(sum1 + sum2, 1/p)
}


// with tasks

trait Task[A] {
  def join: A
}

def task[A](c: => A): Task[A]

def pNormTwoPartParallelTask(a: Array[Int], p: Double): Int = {
  val m = a.length / 2
  val t1 = task(sumSegment(a, p, 0, m))
  val t2 = task(sumSegment(a, p, m, a.length))
  val v1 = t1.join
  val v2 = t2.join
  v1 + v2
}
