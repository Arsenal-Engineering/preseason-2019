package frc.team6223.arsenalFramework

import frc.team6223.arsenalFramework.software.units.Distance
import frc.team6223.arsenalFramework.software.units.DistanceUnits
import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.StringSpec
import io.kotlintest.matchers.plusOrMinus

class DistanceTest: StringSpec() {
    init {
        val dist = Distance(1.0, DistanceUnits.METERS)
        "distance should scale correctly" {
            dist.numericValue() shouldBe 3.28084
            dist.numericValue(DistanceUnits.INCHES) shouldBe (39.370 plusOrMinus .001)
            dist.numericValue(DistanceUnits.FEET) shouldBe (3.28084 plusOrMinus .001)
        }

        "distance should input correctly as inches" {
            Distance(1.0, DistanceUnits.INCHES).numericValue() shouldBe (0.083 plusOrMinus .001)
        }

        "distance should input correctly as feet" {
            Distance(1.0, DistanceUnits.FEET).numericValue() shouldBe 1.0
        }

        "distance should properly output via toString()" {
            dist.toString() shouldBe "1.0 m"
        }
    }
}