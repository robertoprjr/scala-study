import scala.annotation.tailrec

class Rational(x: Int, y: Int){
  //Validation
  require(y != 0, "denominator must be nonzero")

  //Secondary constructor
  def this(x: Int) = this(x, 1)

  @tailrec
  private def gcd(a: Int, b: Int): Int = {
    if (b == 0) a else gcd(b, a % b)
  }
  private val g = gcd(x, y)

  def numer = x / g
  def denom = y / g

  def less(that: Rational) =
    this.numer * that.denom < that.numer * this.denom

  def max(that: Rational) =
    if (this.less(that)) that else this

  def add (that: Rational) = {
    new Rational(numer * that.denom + that.numer * denom, denom * that.denom)
  }

  def neg: Rational = new Rational(-numer, denom)

  def sub(that: Rational) : Rational = add(that.neg)

  def < (that: Rational) = less(that)

  def + (that: Rational) = add(that)

  def unary_- : Rational = new Rational(-numer, denom)

  def - (that: Rational) = this + -that


  override def toString: String = numer + "/" + denom
}

val x = new Rational(1,2)
val y = new Rational(3,4)

x.add(y)
x.neg
y.sub(x)
y.less(x)
x.max(y)

y add x
y < x
y - x
y + x

val z = new Rational(1, 0)
val w = new Rational(2)
