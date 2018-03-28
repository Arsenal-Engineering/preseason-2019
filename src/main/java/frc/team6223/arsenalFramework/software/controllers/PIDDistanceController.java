package frc.team6223.arsenalFramework.software.controllers;


import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team6223.arsenalFramework.drive.ControllerInput;
import frc.team6223.arsenalFramework.drive.DriveController;
import frc.team6223.arsenalFramework.drive.DriveControllerOutput;
import frc.team6223.arsenalFramework.hardware.motor.MotorControlMode;
import frc.team6223.arsenalFramework.software.PIDFConstants;
import frc.team6223.arsenalFramework.software.PIDFController;
import frc.team6223.arsenalFramework.software.units.Distance;
import frc.team6223.arsenalFramework.software.units.DistanceUnits;


public class PIDDistanceController implements DriveController {

    private PIDFConstants leftSideConstants;
    private PIDFConstants rightSideConstants;

    private PIDFController leftSideController;
    private PIDFController rightSideController;

    private double targetDistance;

    public PIDDistanceController(PIDFConstants leftSideConstants, PIDFConstants rightSideConstants,
      double targetDistance)
    {
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
    public boolean isMovementCompleted() {
        return leftSideController.isFinished() && rightSideController.isFinished();
    }

    @Override
    public void dashboardPeriodic() {
        SmartDashboard.putString("Current Controller", "DistanceController");
        SmartDashboard.putNumber("Distance Target", this.targetDistance);
    }
}
