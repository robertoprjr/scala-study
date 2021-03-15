/*
trait Show[A] {
  def apply(a: A): String
}
object Show {
  implicit val showInt: Show[Int] = new Show[Int] {
    def apply(n: Int): String = s"Int($n)"
  }
}

implicitly[Show[Int]](Show.showInt)
*/

trait Show[A] {
  def apply(a: A): String
}
object Show {
  implicit val showInt: Show[Int] = new Show[Int] {
    def apply(n: Int): String = s"Int($n)"
  }
}
def printValue[A: Show](a: A): Unit = {
  println(implicitly[Show[A]].apply(a))
}
printValue(42)