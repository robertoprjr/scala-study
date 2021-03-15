package observerbank

trait Publisher {
  private var subscribers: Set[Subscriber] = Set()

  def subscribe(subscriber: Subscriber) = subscribers += subscriber
  def unsubscribe(subscriber: Subscriber) = subscribers -= subscriber
  def publish() = subscribers foreach(_.handler(this))
}
