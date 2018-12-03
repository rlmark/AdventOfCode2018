
import org.scalatest.mockito.MockitoSugar
import org.scalatest.{FlatSpec, Matchers}
import December1._

class December1Spec extends FlatSpec with Matchers with MockitoSugar {
  "December1" should "detect duplicate frequencies" in {
    duplicateFrequency(List(-1, 1)) shouldBe 0
  }
  it should "work when having to loop multiple times" in {
    duplicateFrequency(List(3,3,4,-2,-4)) shouldBe 10
  }

}
