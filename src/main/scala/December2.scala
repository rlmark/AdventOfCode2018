import scala.collection.mutable
import scala.io.Source

object December2 {
  def parseInput(fileName: String): List[String] = {
    val fileStream = getClass.getResourceAsStream(fileName)
    try {
      val lines = Source.fromInputStream(fileStream).getLines
      lines.toList
    } finally fileStream.close()
  }

  def countLetterInstances(string: String): TwoAndThree = {
    val counterDict = mutable.Map[Char, Int]()
    string.foreach(char =>
      if (!counterDict.contains(char)) {
        counterDict += (char -> 1)
      } else {
        val oldValue: Int = counterDict(char)
        counterDict += (char -> (oldValue + 1))
      }
    )
    val (two,three) = counterDict.values.foldLeft((0,0)){ case ((two, three), currentValue) =>
      val newTwo = if (currentValue == 2) 1 else two
      val newThree = if (currentValue == 3) 1 else three
      (newTwo, newThree)
    }
    TwoAndThree(two, three)
  }

  def runCheckSum(fileName: String) = {
    val lines = parseInput(fileName)
    val tuples = lines.map(countLetterInstances)
    val (sumTwo, sumThree) = tuples.foldLeft((0, 0)){ case ((two, three), twoAndThree) =>
      (two + twoAndThree.two, three + twoAndThree.three)
    }
    sumTwo * sumThree
  }

  def charDifference(string1: String, string2: String): Option[String] = {
    val (similarString, countDifferent) = string1
      .zip(string2)
      .foldLeft(("", 0)){case ((currentString, countDifference), (s1, s2)) =>
      if (s1 == s2) (currentString + s1, countDifference) else (currentString, countDifference + 1)
    }
    if (countDifferent == 1) Some(similarString) else None
  }

  def compareStrings(list: List[String]) = {
    def loop(target: String, remainder: List[String]): Option[String] = {
      val maybeResult = remainder.find(nextString => charDifference(target, nextString).isDefined)
      maybeResult match {
        case Some(string2) => charDifference(target, string2)
        case None => loop(remainder.head, remainder.tail)
      }
    }
    loop(list.head, list.tail)
  }

  def findString(fileName: String) = {
    compareStrings(parseInput(fileName))
  }
}

case class TwoAndThree(two: Int = 0, three: Int = 0)

