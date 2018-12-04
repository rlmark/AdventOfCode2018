import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

import scala.io.Source

object December4 {
  def parseInput(filename: String): List[ParseResult] = {
    val inputStream = getClass.getResourceAsStream(filename)
    val lines = Source.fromInputStream(inputStream).getLines
    lines.map(string => makeParseResult(string)).toList
  }

  def makeParseResult(rawString: String): ParseResult = {
    val timeString = rawString.substring(1, 17)
    val format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
    val dateTime = LocalDateTime.parse(timeString, format)

    val event: String = rawString.substring(18)
    println(event)
    if (event.contains("Guard")) {
      val id = event.filter(c => c.isDigit)
      ParseResult(dateTime, GuardArrives(id.toInt))
    }
    else if (event.contains("wakes up")) ParseResult(dateTime, Wake)
    else ParseResult(dateTime, Sleep)
  }
}


case class TimeStamp(year: Int, month: Int, day: Int, hour: Int, minute: Int) {
  def toDateTime(): LocalDateTime = {
    LocalDateTime.of(year, month, day, hour, minute)
  }
}

case class ParseResult(localDateTime: LocalDateTime, guardEvent: Event)

sealed trait Event
case class GuardArrives(id: Int) extends Event
case object Wake extends Event
case object Sleep extends Event