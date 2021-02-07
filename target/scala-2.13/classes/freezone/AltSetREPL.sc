type AltSet = (Int, List[Int]) => Boolean

def altSet(i: Int, l: List[Int]): Boolean = l.contains(i)

def union(x: AltSet, y: AltSet) =