import org.scalatest.{FlatSpec, Matchers}

import scala.collection.mutable

class December3Spec extends FlatSpec with Matchers {
  "FillXY" should "take a claim and create a map" in {
    val expectedMap = mutable.Map(
      XY(1,1) -> 1
    )
    December3.fillXY(Claim("id", XY(1, 1), 1, 1), mutable.Map()) shouldBe expectedMap
  }
  "CreateClaimMap" should "merge values for same XY keys" in {
    val expectedMap = mutable.Map(
      XY(1,1) -> 2
    )
    val claim = Claim("id", XY(1, 1), 1, 1)
    December3.createClaimMap(List(claim, claim)) shouldBe expectedMap
  }
  "FilterClaim" should "findCommonClaimAreas" in {
    val inputList = List(
      Claim("1", XY(1,3), 4,4),
      Claim("2", XY(3,1), 4,4),
      Claim("3", XY(5,5), 2,2)
    )
    December3.countSharedClaims(inputList) shouldBe 4
  }
}
