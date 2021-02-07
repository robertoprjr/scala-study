def filling(n:Int, lst:List[Int]): List[Int] =
  lst.flatMap(x => List.fill(n)(x))

filling(2, List(1,2,3,4))

def showReturn : IndexedSeq[Int] =
  for(a <- 1 to 5) yield a

showReturn

def showEach(lst:Array[String]) =
  lst.foreach(println)

showEach(Array("A","B","C"))

def filtering[T](fil:T, lst:List[T]): List[T] =
  lst.filter(_ == fil)

filtering(3, List(1,2,3,3))

def filtering2[T](fil:T, lst:List[T]): List[T] =
  for(l <- lst if (l == fil)) yield l

filtering2(3, List(1,2,3,3))