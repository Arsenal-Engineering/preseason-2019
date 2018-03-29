package frc.team6223.arsenalFramework

import frc.team6223.arsenalFramework.software.units.*
import io.kotlintest.matchers.plusOrMinus
import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.StringSpec

class RateTest: StringSpec() {
    init {
        val rate = Rate(Distance(1.0, DistanceUnits.METERS), Time(1.0, TimeUnits.SECONDS))
        "rate converts from m/s to m/ms correctly" {
            rate.rescaleBottomScalar(TimeUnits.MILLISECONDS) shouldBe 0.001

            rate.rescaleBottomRate(TimeUnits.MILLISECONDS).numericValue() shouldBe 0.001
        }

        "rate converts from m/s to m/min correctly" {
            rate.rescaleBottomScalar(TimeUnits.MINUTE) shouldBe (60.0 plusOrMinus .1)

            rate.rescaleBottomRate(TimeUnits.MINUTE).numericValue() shouldBe (60.0 plusOrMinus .1)
        }

        "rate converts from non-unit rate to a unit rate upon creation" {
            val rate = Rate(Distance(1.0, DistanceUnits.METERS), Time(5.0, TimeUnits.SECONDS))
            rate.numericValue() shouldBe 0.20
        }

        "rate formats correctly via toString()" {
            rate.toString() shouldBe "1.0 m/s"
        }
    }
}