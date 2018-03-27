package frc.team6223.arsenalFramework.software.controllers

import frc.team6223.arsenalFramework.drive.ControllerInput
import frc.team6223.arsenalFramework.drive.DriveController
import frc.team6223.arsenalFramework.drive.DriveControllerOutput
import frc.team6223.arsenalFramework.hardware.TimeUtilities
import frc.team6223.arsenalFramework.hardware.motor.MotorControlMode
import frc.team6223.arsenalFramework.software.units.Distance
import frc.team6223.arsenalFramework.software.units.Time
import frc.team6223.arsenalFramework.software.units.TimeUnits

class ForceMovementController(private val deltaTime: Time, private val leftMotorOut: Double, private val rightMotorOut: Double):
        DriveController {

    private lateinit var startTime: Time

    override fun calculateMotorOutput(controllerInput: ControllerInput): DriveControllerOutput {
        return DriveControllerOutput(MotorControlMode.VoltagePercentOut, leftMotorOut, rightMotorOut)
    }

    override fun startController(leftInitial: Distance, rightInitial: Distance) {
        println("Starting FMC")
        startTime = TimeUtilities.currentTimeSec()
    }

    override fun stopController() {
        println("Stopping FMC")
    }

    override fun isMovementCompleted(): Boolean {
        return (TimeUtilities.currentTimeSec() - startTime).numericValue(TimeUnits.SECONDS) >= deltaTime.numericValue(TimeUnits.SECONDS)
    }

    override fun dashboardPeriodic() {

    }
}