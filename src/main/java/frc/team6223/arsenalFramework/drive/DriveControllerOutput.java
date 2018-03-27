package frc.team6223.arsenalFramework.drive;


import frc.team6223.arsenalFramework.hardware.motor.MotorControlMode;

import java.util.Objects;


public class DriveControllerOutput {
    private MotorControlMode controlMode;
    private double leftOutput;
    private double rightOutput;

    public DriveControllerOutput(MotorControlMode controlMode, double leftOutput, double rightOutput) {
        this.controlMode = controlMode;
        this.leftOutput = leftOutput;
        this.rightOutput = rightOutput;
    }

    public MotorControlMode getControlMode() {
        return controlMode;
    }

    public double getLeftOutput() {
        return leftOutput;
    }

    public double getRightOutput() {
        return rightOutput;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }
        DriveControllerOutput that = (DriveControllerOutput) o;
        return Double.compare(that.getLeftOutput(), getLeftOutput()) == 0 &&
          Double.compare(that.getRightOutput(), getRightOutput()) == 0 && getControlMode() == that.getControlMode();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getControlMode(), getLeftOutput(), getRightOutput());
    }

    @Override
    public String toString() {
        return "DriveControllerOutput{" + "controlMode=" + controlMode + ", leftOutput=" + leftOutput +
          ", rightOutput=" + rightOutput + '}';
    }
}
