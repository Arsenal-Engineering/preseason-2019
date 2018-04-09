package frc.team6223.arsenalFramework.software.commands;


import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team6223.arsenalFramework.drive.ArsenalDrive;
import frc.team6223.arsenalFramework.drive.ControllerInput;
import frc.team6223.arsenalFramework.drive.DriveControllerOutput;
import frc.team6223.arsenalFramework.hardware.motor.MotorControlMode;
import frc.team6223.arsenalFramework.drive.MovementControllerCommand;
import frc.team6223.arsenalFramework.software.NetworkTableUtilities;
import frc.team6223.arsenalFramework.software.pid.PIDFConstants;
import frc.team6223.arsenalFramework.software.pid.PIDFController;
import frc.team6223.arsenalFramework.software.units.Distance;
import frc.team6223.arsenalFramework.software.units.DistanceUnits;


public class PIDDistanceController extends MovementControllerCommand {

    private PIDFConstants leftSideConstants;
    private PIDFConstants rightSideConstants;

    private PIDFController leftSideController;
    private PIDFController rightSideController;

    private double targetDistance;

    public PIDDistanceController(ArsenalDrive drive, PIDFConstants leftSideConstants, PIDFConstants rightSideConstants,
      double targetDistance)
    {
        super(drive);
        this.leftSideConstants = leftSideConstants;
        this.rightSideConstants = rightSideConstants;
        this.targetDistance = targetDistance;

        this.leftSideController = new PIDFController(leftSideConstants, targetDistance);
        this.rightSideController = new PIDFController(rightSideConstants, targetDistance);
    }

    @Override
    public DriveControllerOutput calculateMotorOutput(ControllerInput input) {
        double leftOut = leftSideController.runController(input.getLeftDriveDistance().numericValue(DistanceUnits.FEET));
        double rightOut = rightSideController.runController(input.getRightDriveDistance().numericValue(DistanceUnits.FEET));

        return new DriveControllerOutput(MotorControlMode.PIDDistance, leftOut, rightOut);
    }

    @Override
    public void startController(Distance leftInitial, Distance rightInitial) {
        System.out.println("Moving using PIDF");
    }

    @Override
    public void stopController() {

    }

    @Override
    protected void interruptedController() {

    }

    @Override
    public boolean isFinished() {
        return leftSideController.isFinished() && rightSideController.isFinished();
    }

    @Override
    public void dashboardPeriodic(NetworkTable table) {
        NetworkTableUtilities.putItemInTable(table, "Current Controller", "DistanceController");
        NetworkTableUtilities.putItemInTable(table, "Distance Target", this.targetDistance);
    }
}
