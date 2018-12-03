import scala.io.Source
import scala.util.matching.Regex


object December3 {
  def parseInput(fileName: String): List[Claim] = {
    val idPattern: Regex = "#([0-9]+) @ ([0-9]+),([0-9]+): ([0-9]+)x([0-9]+)".r

    val inputStream = getClass.getResourceAsStream(fileName)
    val lines = Source.fromInputStream(inputStream).getLines
    try {
      lines.map{string =>
        val idPattern(id,x,y,width,height) = string
        Claim(id, XY(x.toInt,y.toInt,1), width.toInt, height.toInt)
      }.toList
    } finally {
      inputStream.close()
    }
  }
}


case class XY(distanceLeft: Int, distanceTop: Int, claimCount: Int = 0)

/*
* A claim like #123 @ 3,2: 5x4 means that claim ID 123 specifiesc
* a rectangle 3 inches from the left edge, 2 inches from the top edge,
* 5 inches wide, and 4 inches tall.
* */
case class Claim(id: String, xY: XY, width: Int, height: Int)
