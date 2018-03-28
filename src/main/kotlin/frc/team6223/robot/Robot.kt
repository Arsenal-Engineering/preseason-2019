package frc.team6223.robot

import edu.wpi.first.wpilibj.*
import edu.wpi.first.wpilibj.command.Command
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser
import frc.team6223.arsenalFramework.drive.ArsenalDrive
import frc.team6223.arsenalFramework.hardware.ArsenalNavXMicro
import frc.team6223.arsenalFramework.hardware.ArsenalRobot
import frc.team6223.arsenalFramework.hardware.motor.ArsenalTalon
import frc.team6223.arsenalFramework.hardware.motor.ArsenalVictor
import frc.team6223.arsenalFramework.operator.ArsenalOperatorInterface
import frc.team6223.arsenalFramework.software.commands.MoveDriveTrainCommand
import frc.team6223.arsenalFramework.software.controllers.ArcadeDriveController
import frc.team6223.arsenalFramework.software.controllers.ForceMovementController
import frc.team6223.arsenalFramework.software.controllers.NoMovementController
import frc.team6223.arsenalFramework.software.units.*
import frc.team6223.robot.auto.AutoUtilities
import frc.team6223.robot.conf.LEFT_DRIVE_CONTROLLER
import frc.team6223.robot.conf.RIGHT_DRIVE_CONTROLLER
import frc.team6223.robot.subsystem.Climber

class Robot: ArsenalRobot(TimedRobot.DEFAULT_PERIOD, 0.05) {

    private val climberSubsystem = Climber(
            ArsenalTalon(3, false, false, false),
            ArsenalTalon(7, false, false, false)
                    .also { it.addFollower(ArsenalVictor(9, false)) },
            ArsenalTalon(4, false, false, false)
                    .also { it.addFollower(ArsenalVictor(8, false)) }
    )
    private lateinit var driveSubsystem: ArsenalDrive
    //private val pdpSubsystem = PDP(PDP_CAN_ID)
    private val robotSideChooser = AutoUtilities.generateSendableChooser()

    override fun injectAutonomousChooser(): SendableChooser<Command> {
        val sendableChooser = SendableChooser<Command>()
        sendableChooser.addDefault(
                "Move forward for 2 sec",
                MoveDriveTrainCommand(
                        ForceMovementController(
                                Time(4.0, TimeUnits.SECONDS),
                                0.5, 0.5
                        ),
                        driveSubsystem
                )
        )
        /*sendableChooser.addDefault(
                        "Move 15ft? using Motion Profiling",
                        MoveDriveTrainCommand(
                                MotionProfileController(
                                        arrayOf(
                                                Waypoint(0.0, 0.0, 0.0),
                                                Waypoint(15.0, 0.0, 0.0)
                                        ),
                                        Trajectory.Config(
                                                Trajectory.FitMethod.HERMITE_QUINTIC,
                                                Trajectory.Config.SAMPLES_HIGH,
                                                0.02,
                                                1.0,
                                                1.0,
                                                60.0),
                                        Velocity(Distance(6.54, DistanceUnits.FEET), Time(1.0, TimeUnits.SECONDS))),
                                driveSubsystem)
                )
                */
        return sendableChooser
    }

    override fun allocateSubsystems(preferences: Preferences) {
        driveSubsystem = ArsenalDrive(
                NoMovementController(),
                ArsenalNavXMicro(),
                ArsenalTalon(LEFT_DRIVE_CONTROLLER, true, true, false, false),
                ArsenalTalon(RIGHT_DRIVE_CONTROLLER, true, true, true, false)
        )
    }

    override fun injectSendableChoosers() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun allocateOperatorInterface(preferences: Preferences): ArsenalOperatorInterface {
        return OI(climberSubsystem)
    }

    override fun setTeleoperatedCommand() {
        MoveDriveTrainCommand(ArcadeDriveController(operatorInterface.primaryJoystick), driveSubsystem).start()
    }

    override fun autonomousInit() {
        super.autonomousInit()
        this.driveSubsystem.resetEncoders()
        //this.driveSubsystem.zeroYaw()
    }

    override fun teleopInit() {
        super.teleopInit()
        this.driveSubsystem.resetEncoders()
    }

    override fun dashboardPeriodic() {
        this.driveSubsystem.dashboardPeriodic()
    }
}
