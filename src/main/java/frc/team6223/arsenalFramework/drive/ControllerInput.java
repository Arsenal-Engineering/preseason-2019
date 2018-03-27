package frc.team6223.arsenalFramework.drive;


import frc.team6223.arsenalFramework.hardware.ArsenalNavXMicro;
import frc.team6223.arsenalFramework.software.units.Distance;
import frc.team6223.arsenalFramework.software.units.Velocity;

import java.util.Objects;


public class ControllerInput {
    private Distance leftDriveDistance;
    private Velocity leftDriveVelocity;
    private Distance rightDriveDistance;
    private Velocity rightDriveVelocity;
    private double leftDriveRaw;
    private double rightDriveRaw;
    private float yawRotation;
    private double yawRate;
    private float pitchRotation;
    private float rollRotation;

    public ControllerInput(Distance leftDriveDistance, Velocity leftDriveVelocity, Distance rightDriveDistance,
      Velocity rightDriveVelocity, double leftDriveRaw, double rightDriveRaw, float yawRotation, double yawRate,
      float pitchRotation, float rollRotation)
    {
        this.leftDriveDistance = leftDriveDistance;
        this.leftDriveVelocity = leftDriveVelocity;
        this.rightDriveDistance = rightDriveDistance;
        this.rightDriveVelocity = rightDriveVelocity;
        this.leftDriveRaw = leftDriveRaw;
        this.rightDriveRaw = rightDriveRaw;
        this.yawRotation = yawRotation;
        this.yawRate = yawRate;
        this.pitchRotation = pitchRotation;
        this.rollRotation = rollRotation;
    }

    public ControllerInput(Distance leftDriveDistance, Velocity leftDriveVelocity, Distance rightDriveDistance,
      Velocity rightDriveVelocity, double leftDriveRaw, double rightDriveRaw, ArsenalNavXMicro navXMicro) {
        this(leftDriveDistance, leftDriveVelocity, rightDriveDistance, rightDriveVelocity, leftDriveRaw,
          rightDriveRaw, navXMicro.getYaw(), navXMicro.getRate(), navXMicro.getPitch(), navXMicro.getRoll());
    }

    public Distance getLeftDriveDistance() {
        return leftDriveDistance;
    }

    public Velocity getLeftDriveVelocity() {
        return leftDriveVelocity;
    }

    public Distance getRightDriveDistance() {
        return rightDriveDistance;
    }

    public Velocity getRightDriveVelocity() {
        return rightDriveVelocity;
    }

    public double getLeftDriveRaw() {
        return leftDriveRaw;
    }

    public double getRightDriveRaw() {
        return rightDriveRaw;
    }

    public float getYawRotation() {
        return yawRotation;
    }

    public double getYawRate() {
        return yawRate;
    }

    public float getPitchRotation() {
        return pitchRotation;
    }

    public float getRollRotation() {
        return rollRotation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }
        ControllerInput that = (ControllerInput) o;
        return Double.compare(that.getLeftDriveRaw(), getLeftDriveRaw()) == 0 &&
          Double.compare(that.getRightDriveRaw(), getRightDriveRaw()) == 0 &&
          Float.compare(that.getYawRotation(), getYawRotation()) == 0 &&
          Double.compare(that.getYawRate(), getYawRate()) == 0 &&
          Float.compare(that.getPitchRotation(), getPitchRotation()) == 0 &&
          Float.compare(that.getRollRotation(), getRollRotation()) == 0 &&
          Objects.equals(getLeftDriveDistance(), that.getLeftDriveDistance()) &&
          Objects.equals(getLeftDriveVelocity(), that.getLeftDriveVelocity()) &&
          Objects.equals(getRightDriveDistance(), that.getRightDriveDistance()) &&
          Objects.equals(getRightDriveVelocity(), that.getRightDriveVelocity());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLeftDriveDistance(), getLeftDriveVelocity(), getRightDriveDistance(),
          getRightDriveVelocity(), getLeftDriveRaw(), getRightDriveRaw(), getYawRotation(), getYawRate(),
          getPitchRotation(), getRollRotation()
        );
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ControllerInput{");
        sb.append("leftDriveDistance=").append(leftDriveDistance);
        sb.append(", leftDriveVelocity=").append(leftDriveVelocity);
        sb.append(", rightDriveDistance=").append(rightDriveDistance);
        sb.append(", rightDriveVelocity=").append(rightDriveVelocity);
        sb.append(", leftDriveRaw=").append(leftDriveRaw);
        sb.append(", rightDriveRaw=").append(rightDriveRaw);
        sb.append(", yawRotation=").append(yawRotation);
        sb.append(", yawRate=").append(yawRate);
        sb.append(", pitchRotation=").append(pitchRotation);
        sb.append(", rollRotation=").append(rollRotation);
        sb.append('}');
        return sb.toString();
    }
}
