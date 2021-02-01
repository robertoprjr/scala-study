// High order function example

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

def xpto(x: String) = x + "&xpto"

conc(xpto)("abc", "def")
conc((x: String) => x + "&xxx")("abc", "def")

just(xpto)("wzy")