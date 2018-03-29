package frc.team6223.arsenalFramework

import frc.team6223.arsenalFramework.software.units.*
import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.StringSpec

class VelocityTest: StringSpec() {
    init {
        val velocity = Velocity(Distance(10.0, DistanceUnits.METERS), Time(1.0, TimeUnits.SECONDS))
        "velocity can convert from m/s to m/min" {
            velocity.rescaleBottomScalar(TimeUnits.MINUTE) shouldBe 600.0
        }

        "velocity can convert from m/s to m/ms" {
            velocity.rescaleBottomScalar(TimeUnits.MILLISECONDS) shouldBe 0.01
        }
    }
}