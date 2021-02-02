println("Coding in scala...")
val x1 = 10

println("Is anybody out there?")
9-7

def x = 5      // evaluated when called
val y = 2      // evaluated immediately
lazy val w = 2 // evaluated once when needed
var z = 3

z = 2
// x = 2 (error: value x_= is not a member of $iw)
// y = 3 (error: reassignment to val)

x * y * w * z
