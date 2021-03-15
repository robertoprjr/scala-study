package signalbank

class BankAccount extends Account {
  val balance = Var(0)

  def deposit(amount: Int) = {
    if (amount > 0) {
      val b = balance()
      balance() = b + amount
    }
  }

  def withdrawal(amount: Int) = {
    if (amount > 0 && amount <= balance()) {
      val b = balance()
      balance() = b - amount
    }
    else throw new Error("no funds on bank account")
  }
}
