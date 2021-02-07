import scala.annotation.tailrec

// Definiçao do tipo como atalho de um padrao complexo
type FunSet = Int => Boolean

// Uma funçao de exemplo que pode ser usada como base de calculo
def base5 = (x:Int) => (x % 5 == 0)

// Outra função de exemplo
def neg = (x:Int) => (x < 0)

// Função contains - se atende a função usada no set
// Exemplo, se usar a função base, ela entra no lugar do s
// Se passar outra funcao, atenderá a outra função
def contains(s: FunSet, elem: Int): Boolean = s(elem)
contains(base5, -7) // false
contains(base5, 10) // true
contains(neg, 7) // false
contains(neg, -9) // true

// Função singletonSet - Gera o set conforme o inteiro passado
// Um set singleton
// Outra solução possivel: def singletonSet(elem: Int): FunSet = (item => item == elem)
def singletonSet(elem: Int): FunSet = (_ == elem)
singletonSet(2)(1) // false
singletonSet(2)(2) // true
singletonSet(2) // retorna o set gerado

// Função union - verifica se um OU outro atende a questão
// como o union da teoria de conjuntos
// usando a base5 e a neg, o numero tem que antender uma das duas funções
def union(s: FunSet, t: FunSet): FunSet =
  (number => contains(s, number) || contains(t, number))

union(base5, neg)(7) // false
union(base5, neg)(-7) // true
union(base5, neg)(10) // true
union(base5, neg)(-10) // true

// Função intersect - verifica se um E outro atende
// como intersecção na teoria de conjuntos
// usando a base5 e neg, o numero tem que atender os 2
def intersect(s: FunSet, t: FunSet): FunSet =
  (number => contains(s, number) && contains(t, number))

intersect(base5, neg)(7) // false
intersect(base5, neg)(-7) // false
intersect(base5, neg)(10) // false
intersect(base5, neg)(-10) // true

// Função diff - verifica se atende no primeiro e não atende no segundo
def diff(s: FunSet, t: FunSet): FunSet =
  (x => contains(s, x) && !contains(t, x))

diff(base5, neg)(7) // false
diff(base5, neg)(-7) // false
diff(base5, neg)(10) // true
diff(base5, neg)(-10) // false

// Função filter - verifica se o Set atende mas respeitando o filtro
def filter(s: FunSet, p: Int => Boolean): FunSet =
  (number => contains(s, number) && p(number))

filter(base5, (x: Int) => x % 10 == 0)(7) // false
filter(base5, (x: Int) => x % 10 == 0)(5) // false
filter(base5, (x: Int) => x % 10 == 0)(20) // true


// Definindo limites para a função de forall
def max: Int = 1000
def min: Int = -1000

// Função forall - se todos os valores do set se encaixam na condição
// A condição está invertida para justamente só sair com true quando passar por todos os itens sem retorar o oposto da condição (!p(a))
def forall(s: FunSet, p: Int => Boolean): Boolean = {
  @tailrec
  def iter(a: Int): Boolean = {
    if (filter(s, x => !p(x))(a)) false
    else if (a > max) true
    else iter(a + 1)
  }
  iter(min)
}

// Se todos os itens base5 tbm fazem mod 2 - false
forall(base5, (x: Int) => x % 2 == 0) // false

// Se todos os itens base5 tbm fazem mod 10 - false (o contrario seria verdade)
forall(base5, (x: Int) => x % 10 == 0) // false

// Se todos os itens neg tbm são menores que 100 - true
forall(neg, (x: Int) => x < 100) // true

// Função existe - verifica se o item existe no set
// Usando a função forall, a condição tem que ser reinvertida aqui e inverter a forall pois essa retorna false quando acha um item na lista com a condição invertida
def exists(s: FunSet, p: Int => Boolean): Boolean = !forall(s, x => !p(x))

// Se existe um dos itens base5 que tbm fazem mod 2 - true
exists(base5, (x: Int) => x % 2 == 0) // true

// Se existe um dos itens base5 que tbm fazem mod 10 - true
exists(base5, (x: Int) => x % 10 == 0) // true

// Se existe um dos itens neg que tbm é maior que 100 - false
exists(neg, (x: Int) => x > 100) // false

// Função map - conforme itens existentes, cria um retorno mapeado e testa sua existencia
def map(s: FunSet, f: Int => Int): FunSet =
  (x => exists(s, y => x == f(y)))

// Função usada para simplificar o mapeamento
def x2 (x: Int): Int = x + 2

// base5 5+2=7, então com 7 true
// base5 10+2=12, então com 12 true
map(base5, x2)(5)
map(base5, x2)(6)
map(base5, x2)(7)
map(base5, x2)(8)
map(base5, x2)(9)
map(base5, x2)(10)
map(base5, x2)(11)
map(base5, x2)(12)
map(base5, x2)(13)