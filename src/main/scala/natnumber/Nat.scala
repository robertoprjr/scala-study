package natnumber

// Peano numbers

abstract class Nat {
  def isZero: Boolean
  def predecessor: Nat
  def successor: Nat = new NatSucc(this)
  def + (that: Nat): Nat
  def - (that: Nat): Nat
}

