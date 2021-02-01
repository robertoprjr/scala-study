package recfun

import scala.annotation.tailrec

object RecFun extends RecFunInterface {

  def main(args: Array[String]): Unit = {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(s"${pascal(col, row)} ")
      println()
    }

    println(s"${balance("(if (zero? x) max (/ 1 x))".toList)} ")
    println(s"${balance("I told him (that it’s not (yet) done). (But he wasn’t listening)".toList)} ")
    println(s"${balance(":-)".toList)} ")
    println(s"${balance("())(".toList)} ")

    println(countChange(301,List(5,10,20,50,100,200,500)))
  }

  /**
   * Exercise 1
   */
  def pascal(c: Int, r: Int): Int = {
    if (c == 0 || r == c) 1
    else pascal(c - 1, r - 1) + pascal(c, r - 1)
  }

  /**
   * Exercise 2
   */
  def balance(chars: List[Char]): Boolean = {
    @tailrec
    def verifyBalance(chars: List[Char], balanced: Int): Int = {
      if (chars.isEmpty || balanced < 0) balanced
      else if (chars.head == '(') verifyBalance(chars.tail, balanced + 1)
      else if (chars.head == ')') verifyBalance(chars.tail, balanced - 1)
      else verifyBalance(chars.tail, balanced)
    }
    (verifyBalance(chars, 0) == 0)
  }

  /**
   * Exercise 3
   */
  def countChange(money: Int, coins: List[Int]): Int = {
    val coinsSorted = coins.sorted

    if (coinsSorted.isEmpty)
      0
    else if (money < coinsSorted.head)
      countChange(money, coinsSorted.tail)
    else if (money > coinsSorted.head)
      countChange(money - coinsSorted.head, coinsSorted) +
        countChange(money, coinsSorted.tail)
    else if (money == coinsSorted.head)
      1
    else
      0
  }
}
