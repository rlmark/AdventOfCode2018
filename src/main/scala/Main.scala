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

    // Day 3
    val day3Input = December3.parseInput("december_3_input")
    day3Input.take(3).foreach(println)
  }
}
