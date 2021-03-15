package observerbank

class Consolidator (observed: List[BankAccount]) extends Subscriber {
  // ~~~ initial block
  observed foreach(_.subscribe(this))

  private var total: Int = _
  compute()
  // ~~~

  private def compute() =
    total = observed.map(_.currentBalance).sum

  def handler(publisher: Publisher) = compute()

  def totalBalance = total
}
