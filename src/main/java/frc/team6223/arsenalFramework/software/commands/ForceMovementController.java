package frc.team6223.arsenalFramework.software.commands;


import edu.wpi.first.networktables.NetworkTable;
import frc.team6223.arsenalFramework.drive.ArsenalDrive;
import frc.team6223.arsenalFramework.drive.ControllerInput;
import frc.team6223.arsenalFramework.drive.DriveControllerOutput;
import frc.team6223.arsenalFramework.hardware.TimeUtilities;
import frc.team6223.arsenalFramework.hardware.motor.MotorControlMode;
import frc.team6223.arsenalFramework.drive.MovementControllerCommand;
import frc.team6223.arsenalFramework.software.units.Distance;
import frc.team6223.arsenalFramework.software.units.Time;
import frc.team6223.arsenalFramework.software.units.TimeUnits;


public class ForceMovementController extends MovementControllerCommand {

    private Time deltaTime;
    private double leftMotorOut;
    private double rightMotorOut;

    private Time startTime;

    public ForceMovementController(ArsenalDrive drive, Time deltaTime, double leftMotorOut, double rightMotorOut) {
        super(drive);
        this.deltaTime = deltaTime;
        this.leftMotorOut = leftMotorOut;
        this.rightMotorOut = rightMotorOut;
    }

    @Override
    public DriveControllerOutput calculateMotorOutput(ControllerInput input) {
        return new DriveControllerOutput(MotorControlMode.VoltagePercentOut, leftMotorOut, rightMotorOut);
    }

    @Override
    public void startController(Distance leftInitial, Distance rightInitial) {
        System.out.println("Starting ForceMotionController");
        startTime = TimeUtilities.currentTimeSec();
    }

    @Override
    public void stopController() {
        System.out.println("Stopping ForceMotionController");
    }

    @Override
    protected void interruptedController() {
        System.out.println("ForceMotionController was interrupted. Robot in unknown location");
    }

    @Override
    public boolean isFinished() {
        return (TimeUtilities.currentTimeSec().minus(startTime).numericValue(TimeUnits.SECONDS) >= deltaTime.numericValue(TimeUnits.SECONDS));
    }

    @Override
    public void dashboardPeriodic(NetworkTable table) {

    }
}
