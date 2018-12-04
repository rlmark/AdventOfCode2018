import java.time.{LocalDateTime, ZoneOffset}
import java.time.format.DateTimeFormatter

import scala.io.Source

object December4 {
  def parseInput(filename: String): List[Event] = {
    val inputStream = getClass.getResourceAsStream(filename)
    val lines = Source.fromInputStream(inputStream).getLines
    lines.map(string => makeSchedule(string)).toList
  }

  def makeSchedule(rawString: String): Event = {
    val timeString = rawString.substring(1, 17)
    val format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
    val dateTime = LocalDateTime.parse(timeString, format)

    val eventString: String = rawString.substring(18)
    if (eventString.contains("Guard")) {
      val id = eventString.filter(c => c.isDigit)
      GuardArrives(GuardId(id.toInt), dateTime)
    }
    else if (eventString.contains("wakes up")) Wake(dateTime)
    else  Sleep(dateTime)
  }

  def orderSchedule(events: List[Event]): List[Event] = {
    implicit val localDateTimeOrdering: Ordering[LocalDateTime] = Ordering.by(x => x.toEpochSecond(ZoneOffset.UTC))
    events.sortBy(event => event.time)
  }

  def read(schedules:List[Event]): Map[GuardId, List[Nap]]= {
    ???
  }
}

sealed trait Event {
  val time: LocalDateTime
}
sealed trait SleepEvent extends Event
case class GuardId(id: Int)
case class GuardArrives(guard: GuardId, time: LocalDateTime) extends Event
case class Wake(time: LocalDateTime) extends SleepEvent
case class Sleep(time: LocalDateTime) extends SleepEvent

case class Nap(start: Sleep, end: Wake){
  def durationInMinutes = {
    val seconds = end.time.toEpochSecond(ZoneOffset.UTC) - start.time.toEpochSecond(ZoneOffset.UTC)
    seconds / 60.0
  }
}
