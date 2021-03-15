package signalbank

object Main extends App {
  def consolidated(accounts: List[BankAccount]): Signal[Int] =
    Signal(accounts.map(_.balance()).sum)

  var x = new BankAccount
  var y = new BankAccount

  x deposit 10
  y deposit 20

  println(consolidated(List(x, y)).toString)
}
