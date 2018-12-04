import scala.collection.mutable
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
        Claim(id, XY(x.toInt,y.toInt), width.toInt, height.toInt)
      }.toList
    } finally {
      inputStream.close()
    }
  }

  def fillXY(claim: Claim, currentMap: mutable.Map[XY, Int]): mutable.Map[XY, Int] = {
    val Claim(id, XY(distanceLeft, distanceTop), width, height) = claim
    val area = for {
      x <- distanceLeft until width + distanceLeft
      y <- distanceTop until height + distanceTop
    } yield XY(x,y)
    area.foreach { xy =>
      if (!currentMap.contains(xy)) currentMap += (xy -> 1)
      else {
        val oldCount = currentMap(xy)
        currentMap += (xy -> (oldCount+1))
      }
    }
    currentMap
  }

  def createClaimMap(claimList: List[Claim]):mutable.Map[XY, Int] = {
    def loop(list: List[Claim], map: mutable.Map[XY, Int]): mutable.Map[XY, Int] = {
      list match {
        case Nil => map
        case h::tail => loop(tail, fillXY(h, map))
      }
    }
    loop(claimList, mutable.Map())
  }

  def countSharedClaims(list: List[Claim]) = {
    val mapResult = createClaimMap(list)
    mapResult.count{case(_, int) => int >= 2}
  }

  def findUnique(list: List[Claim]) = {
    val mapResult: mutable.Map[XY, Int] = createClaimMap(list)
    val claim: Option[Claim] = list.find{case claim =>
      val thisClaimsMap = fillXY(claim, mutable.Map())
        thisClaimsMap.forall{case(xy,_) => mapResult(xy) == 1}
    }
    claim
  }
}


case class XY(distanceLeft: Int, distanceTop: Int)

/*
* A claim like #123 @ 3,2: 5x4 means that claim ID 123 specifiesc
* a rectangle 3 inches from the left edge, 2 inches from the top edge,
* 5 inches wide, and 4 inches tall.
* */
case class Claim(id: String, xY: XY, width: Int, height: Int)
