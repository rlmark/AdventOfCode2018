import scala.io.Source

object December1 {

  def parseInput(fileName: String): List[Int] = {
    val fileStream = getClass.getResourceAsStream(fileName)
    val lines = Source.fromInputStream(fileStream).getLines
    try {
      lines.map(stringToInt).toList
    } finally {
      fileStream.close()
    }
  }

  def stringToInt(stringInt: String): Int = {
    stringInt.replace("+", "").toInt
  }

  def duplicateFrequency(xs: List[Int]): Int = {
    def loop(list: List[Int], currentTotal: Int, frequenciesSeen: Set[Int]): Int = {
      list match {
        case Nil => loop(xs, currentTotal, frequenciesSeen)
        case h::t =>
          val newFrequency = h + currentTotal
          if (frequenciesSeen.contains(newFrequency)) {
            newFrequency
          } else {
            loop(t, currentTotal + h, frequenciesSeen + newFrequency)
          }
      }
    }
    loop(xs, 0, Set(0))
  }
}