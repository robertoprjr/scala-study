// High order function example
// with Curryling

/*
Isso pode ser lido da seguinte maneira
- Função conc recebe como parâmetro a função f
- A função f tem que ser do tipo generico que recebe uma string e retorna uma string
- :
- Define o retorno da função conc, que seria uma função também, nesse caso uma função que recebe 2 string e retorna uma string
- Função que é descrita no corpo de conc
 */

def conc(f: String => String): (String, String) => String = {
  (a: String, b: String) => f(a) + "..." + b
}

def just(f: String => String) = {
  (a: String) => f(a)
}

def sum(f: Int => Int)(a: Int, b: Int): Int =
  if (a > b) 0 else f(a) + sum(f)(a + 1, b)

def xpto(x: String) = x + "&xpto"

def product(f: Int => Int)(a: Int, b: Int): Int =
  if (a > b) 1 else f(a) * product(f)(a+1, b)

def fact(n: Int): Int = product(x => x)(1, n)

conc(xpto)("abc", "def")
conc((x: String) => x + "&xxx")("abc", "def")

just(xpto)("wzy")

sum((x: Int) => x * x)(2,5)
product((x: Int) => x * x)(2,5)
fact(5)


