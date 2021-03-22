class FirstThread extends Thread {
  override def run(): Unit = {
    println("First Thread")
  }
}

class SecondThread extends Thread {
  override def run(): Unit = {
    println("Second Thread")
  }
}

var t = new FirstThread
var k = new SecondThread

t.start()
k.start()
t.join()
k.join()
