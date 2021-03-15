package observerbank

class BankAccount extends Account with Publisher {
  private var balance = 0

  def deposit(amount: Int) = {
    if (amount > 0) {
      balance = balance + amount
      publish()
    }
  }

  def withdrawal(amount: Int) = {
    if (amount > 0 && amount <= balance) {
      balance = balance - amount
      publish()
    }
    else throw new Error("no funds on bank account")
  }

  def currentBalance = balance
}
