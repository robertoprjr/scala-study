/*
Função de MapReduce
f: função numérica para potencializar um numero
combine: função para combinar os numeros sem usar operações em si
ret: retorno padrão
curryling recebendo a e b como valores numericos
 */

def mapReduce(f: Int => Int, combine: (Int, Int) => Int, ret: Int)(a: Int, b: Int): Int = {
  if (a > b) ret
  else combine(f(a), mapReduce(f, combine, ret)(a+1, b))
}

def product(f: Int => Int)(a: Int, b: Int): Int = {
  mapReduce(f, (x, y) => x * y, 1)(a, b)
}

def sum(f: Int => Int)(a: Int, b: Int): Int = {
  mapReduce(f, (x, y) => x + y, 0)(a, b)
}

def exp(f: Int => Int)(a: Int, b: Int): Int = {
  mapReduce(f, (x, y) => x ^ y, 1)(a, b)
}

def fact(n: Int) = product(x => x)(1, n)

def factCube(n: Int) = product(x => x * x * x)(1, n)

fact(5)
factCube(5)

exp(x => x)(1, 3)
exp(x => x * x)(1, 3)

sum(x => x)(1, 3)
sum(x => x * x)(1, 3)

product(x => x)(1, 3)
product(x => x * x)(1, 3)
