package observerbank

trait Subscriber {
  def handler(publisher: Publisher)
}
