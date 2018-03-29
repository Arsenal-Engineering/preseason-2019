package frc.team6223.arsenalFramework.software.controllers;


import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team6223.arsenalFramework.drive.ControllerInput;
import frc.team6223.arsenalFramework.drive.DriveController;
import frc.team6223.arsenalFramework.drive.DriveControllerOutput;
import frc.team6223.arsenalFramework.hardware.motor.MotorControlMode;
import frc.team6223.arsenalFramework.software.units.Distance;


public class ArcadeDriveController implements DriveController {

    private Joystick joystick;
    private double scaleFactor = 1.0;

    public ArcadeDriveController(Joystick joystick, double scaleFactor) {
        this.joystick = joystick;
        this.scaleFactor = scaleFactor;
    }

    public Joystick getJoystick() {
        return joystick;
    }

    public double getScaleFactor() {
        return scaleFactor;
    }

    public void setScaleFactor(double scaleFactor) {
        this.scaleFactor = scaleFactor;
    }

    @Override
    public DriveControllerOutput calculateMotorOutput(ControllerInput input) {
        double rotateValue = joystick.getX() * scaleFactor;
        double moveValue = -joystick.getY() * scaleFactor;

        moveValue = Math.copySign(moveValue * moveValue, moveValue);
        rotateValue = Math.copySign(rotateValue * rotateValue, rotateValue);

        if (moveValue >= 1.0) {
            moveValue = 1.0;
        } else if (moveValue <= -1.0) {
            moveValue = -1.0;
        }

        if (rotateValue >= 1.0) {
            rotateValue = 1.0;
        } else if (rotateValue <= -1.0) {
            rotateValue = -1.0;
        }

        double maxInput = Math.copySign(Math.max(Math.abs(moveValue), Math.abs(rotateValue)), moveValue);

        double leftOut = 0.0;
        double rightOut = 0.0;

        if (moveValue >= 0.0) {
            if (rotateValue > 0.0) {
                leftOut = maxInput;
                rightOut = moveValue - rotateValue;
            } else if (rotateValue < 0.0) {
                leftOut = rotateValue + moveValue;
                rightOut = maxInput;
            } else {
                leftOut = maxInput;
                rightOut = maxInput;
            }
        } else if (moveValue < 0.0) {
            if (rotateValue > 0.0) {
                leftOut = rotateValue + moveValue;
                rightOut = maxInput;
            } else if (rotateValue < 0.0) {
                leftOut = maxInput;
                rightOut = moveValue - rotateValue;
            } else {
                leftOut = maxInput;
                rightOut = maxInput;
            }
        }

        SmartDashboard.putNumber("RotateValue", rotateValue);
        SmartDashboard.putNumber("MoveValue", moveValue);
        SmartDashboard.putNumber("Left Output", leftOut);
        SmartDashboard.putNumber("Right Output", rightOut);

        return new DriveControllerOutput(MotorControlMode.VoltagePercentOut, leftOut, rightOut);

    }

    @Override
    public void startController(Distance leftInitial, Distance rightInitial) {
        System.out.println("Starting Arcade Drive");
    }

    @Override
    public void stopController() {
        System.out.println("Stopping Arcade Drive");
    }

    @Override
    public boolean isMovementCompleted() {
        return false;
    }

    @Override
    public void dashboardPeriodic() {
        SmartDashboard.putString("CurrentController", "ArcadeController");
        SmartDashboard.putNumber("CurrentControllerMoveValue", joystick.getY());
        SmartDashboard.putNumber("CurrentControllerRotateValue", joystick.getX());
    }
}
