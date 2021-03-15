package observerbank

object Main extends App {
  var x = new BankAccount
  var y = new BankAccount

  var c = new Consolidator(List(x, y))

  x deposit 10
  y deposit 20

  println(c.totalBalance)
}
