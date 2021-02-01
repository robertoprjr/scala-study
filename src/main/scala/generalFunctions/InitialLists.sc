var lst = List(1,4,3,6,8,2)

def sum(xs: List[Int]): Int = {
  if (xs.nonEmpty) {
    xs.head + sum(xs.drop(1))
  } else 0
}

val x = sum(lst)
x

1+4+3+6+8+2

def maxHead(head: Int, last: Int): Boolean = {
  if (head > last) true else false
}

def max(xs: List[Int]): Int = {
  if (xs.isEmpty) {
    0
  }
  else if (xs.length == 1) {
    xs.head
  }
  else {
    if (maxHead(xs.head, xs.last))
      max(xs.dropRight(1))
    else
      max(xs.drop(1))
  }
}


val y = max(lst)
y