object Main {
  def main(args: Array[String]): Unit = {
//    // Day 1
//    val day1Input = parseInput("december_1_input")
//    println(day1Input.sum)
//    println(duplicateFrequency(day1Input))
//
//    // Day 2
//    val day2checksum = December2.runCheckSum("december_2_input")
//    println(day2checksum)
//    val day2String = December2.findString("december_2_input")
//    println(day2String)
//
//    // Day 3
//    val lines = December3.parseInput("december_3_input")
//    val day3Input = December3.countSharedClaims(lines)
//    val unique = December3.findUnique(lines)
//    println(day3Input)
//    println(unique)
    // Day 4
    val events = December4.parseInput("december_4_input")
    val ordered = December4.orderEvents(events)
    val naps = December4.guardsTakeNaps(ordered)
    val sleepiest = December4.sleepiestGuard(naps)
    println(sleepiest)

  }
}
