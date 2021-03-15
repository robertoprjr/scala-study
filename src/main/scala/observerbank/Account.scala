package observerbank

trait Account {
  def currentBalance: Int
  def deposit(amount: Int)
  def withdrawal(amount: Int)
}
