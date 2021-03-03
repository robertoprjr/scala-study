case class Book(title: String, authors: List[String])

var books: List[Book] = List (
  Book(title = "Structured and Interpretation of Computer Programs",
    authors = List("Abelson, Harald", "Sussman, Gerald J.")),
  Book(title = "Introduction of Functional Programming",
    authors = List("Bird, Richard", "Wadler, Phil")),
  Book(title = "Effective Java",
    authors = List("Bloch, Joshua")),
  Book(title = "Effective Java 2.0",
    authors = List("Bloch, Joshua")),
  Book(title = "Java Puzzlers",
    authors = List("Bloch, Joshua", "Gafter, Neal")),
  Book(title = "Programming in Scala",
    authors = List("Odersky, Martin", "Spoon, Lex", "Venners, Bill"))
  )

for (b <- books; a <- b.authors if a startsWith "Bird,")
  yield b.title

{
  for {
    b1 <- books
    b2 <- books
    if b1 != b2
    a1 <- b1.authors
    a2 <- b2.authors
    if a1 == a2
  } yield a1
}.distinct


// use distinct or use a set to define:

var books_set: Set[Book] = Set (
  Book(title = "Structured and Interpretation of Computer Programs",
    authors = List("Abelson, Harald", "Sussman, Gerald J.")),
  Book(title = "Introduction of Functional Programming",
    authors = List("Bird, Richard", "Wadler, Phil")),
  Book(title = "Effective Java",
    authors = List("Bloch, Joshua")),
  Book(title = "Effective Java 2.0",
    authors = List("Bloch, Joshua")),
  Book(title = "Java Puzzlers",
    authors = List("Bloch, Joshua", "Gafter, Neal")),
  Book(title = "Programming in Scala",
    authors = List("Odersky, Martin", "Spoon, Lex", "Venners, Bill"))
)

for {
  b1 <- books_set
  b2 <- books_set
  if b1 != b2
  a1 <- b1.authors
  a2 <- b2.authors
  if a1 == a2
} yield a1

// filtra por contains

for {
  b1 <- books
  if b1.title contains  "Java"
} yield b1

// concatena 2 retornos
{
  for {
    b1 <- books
    if b1.title contains "Java"
  } yield b1
} :::
{
  for {
    b1 <- books
    if b1.title contains "Program"
  } yield b1
}

// pega os 2 primeiros
{
  for {
    b1 <- books
    if b1.title contains "Java"
  } yield b1
}.take(2)

// filtra e add itens a lista retornada
{
  for {
    b1 <- books
    if b1.title contains "Programming"
  } yield b1
} ::: List(Book ( title = "Test in Java",
  authors = List("Tree, Pamela")))
