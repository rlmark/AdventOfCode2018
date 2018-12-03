import org.scalatest.mockito.MockitoSugar
import org.scalatest.{FlatSpec, Matchers}

class December2Spec extends FlatSpec with Matchers with MockitoSugar{
  "CountInstancesOfLetter" should "count when both 2 and 3 are found" in {
    December2.countLetterInstances("aaabb") shouldBe TwoAndThree(1,1)
  }
  it should "count when 3 letters are present" in {
    December2.countLetterInstances("aaaekpwd") shouldBe TwoAndThree(0, 1)
  }
  it should "not count when more than 3 instances are found" in {
    December2.countLetterInstances("aaadkeoaaa") shouldBe TwoAndThree(0, 0)
  }

  "CharDifference" should "return a string if diff in strings is 1" in {
    December2.charDifference("abcde", "abcdf") shouldBe Some("abcd")
    December2.charDifference("abcdeee", "abcdfff") shouldBe None
  }
  "compareStrings" should "return the string value when finding target" in {
    December2.compareStrings(List("fiewe", "abcde", "abcdf", "abwek")) shouldBe Some("abcd")
  }
}
