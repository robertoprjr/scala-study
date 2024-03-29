class Account(private var amount: Int = 0)
{
    def showAmount = amount

    def transfer(target: Account, n: Int) { 
        this.synchronized {
            target.synchronized {
                this.amount -= n
                target.amount += n
            }
        }

    }
}

def startThread(a: Account, b: Account, n: Int) = {
    val t = new Thread {
        override def run() {
            for (i <- 0 until n) {
                a.transfer(b, 1)
            }
        }
    }
    t.start()
    t
}

val a1 = new Account(500000)
val a2 = new Account(700000)

val t = startThread(a1, a2, 15000)
val s = startThread(a2, a1, 25000)
val k = startThread(a1, a2, 71000)

t.join()
s.join()
k.join()

println(a1.showAmount)
println(a2.showAmount)