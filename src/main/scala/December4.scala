import java.time.{LocalDateTime, ZoneOffset}
import java.time.format.DateTimeFormatter

import scala.io.Source

object December4 {
  def parseInput(filename: String): List[Schedule] = {
    val inputStream = getClass.getResourceAsStream(filename)
    val lines = Source.fromInputStream(inputStream).getLines
    lines.map(string => makeSchedule(string)).toList
  }

  def makeSchedule(rawString: String): Schedule = {
    val timeString = rawString.substring(1, 17)
    val format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
    val dateTime = LocalDateTime.parse(timeString, format)

    val eventString: String = rawString.substring(18)
    if (eventString.contains("Guard")) {
      val id = eventString.filter(c => c.isDigit)
      Schedule(dateTime, GuardArrives(id.toInt))
    }
    else if (eventString.contains("wakes up")) Schedule(dateTime, Wake)
    else Schedule(dateTime, Sleep)
  }

  def orderSchedule(schedules: List[Schedule]): List[Schedule] = {
    implicit val localDateTimeOrdering: Ordering[LocalDateTime] = Ordering.by(x => x.toEpochSecond(ZoneOffset.UTC))
    schedules.sortBy(schedule => schedule.localDateTime)
  }
}

case class Schedule(localDateTime: LocalDateTime, guardEvent: Event)

sealed trait Event
case class GuardArrives(id: Int) extends Event
case object Wake extends Event
case object Sleep extends Event
