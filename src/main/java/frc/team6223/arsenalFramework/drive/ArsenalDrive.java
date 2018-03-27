package frc.team6223.arsenalFramework.drive;


import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team6223.arsenalFramework.hardware.ArsenalNavXMicro;
import frc.team6223.arsenalFramework.hardware.motor.ArsenalTalon;
import frc.team6223.arsenalFramework.hardware.motor.MotorControlMode;
import frc.team6223.arsenalFramework.logging.Loggable;


public class ArsenalDrive extends Subsystem implements Loggable {

    private DriveController driveController;
    private ArsenalNavXMicro navXMicro;
    private ArsenalTalon leftController;
    private ArsenalTalon rightController;

    public ArsenalDrive(DriveController driveController, ArsenalNavXMicro navXMicro, ArsenalTalon leftController,
      ArsenalTalon rightController)
    {
        this.driveController = driveController;
        this.navXMicro = navXMicro;
        this.leftController = leftController;
        this.rightController = rightController;
    }

    public void driveMotors() {
        DriveControllerOutput output =
          this.driveController.calculateMotorOutput(
            new ControllerInput(
              leftController.getPosition(), leftController.getVelocity(),
              rightController.getPosition(), rightController.getVelocity(),
              leftController.getRawPosition(), rightController.getRawPosition(),
              navXMicro
            )
          );
        leftController.set(output.getControlMode(), output.getLeftOutput());
        rightController.set(output.getControlMode(), output.getRightOutput());
    }

    public void manualOverride(double left, double right) {
        leftController.set(MotorControlMode.VoltagePercentOut, left);
        rightController.set(MotorControlMode.VoltagePercentOut, right);
    }

    public void setEncoderPhasing(boolean left, boolean right) {
        this.leftController.setEncoderPhase(left);
        this.rightController.setEncoderPhase(right);
    }

    public void resetEncoders() {
        leftController.resetEncoder();
        rightController.resetEncoder();
    }

    public void zeroYaw() {
        navXMicro.reset();
    }

    @Override
    protected void initDefaultCommand() {}

    @Override
    public void dashboardPeriodic() {
        this.leftController.dashboardPeriodic();
        this.rightController.dashboardPeriodic();
        this.driveController.dashboardPeriodic();
        this.navXMicro.dashboardPeriodic();
    }

    public DriveController getDriveController() {
        return driveController;
    }

    public void setDriveController(DriveController driveController) {
        this.driveController.stopController();
        this.driveController = driveController;
        this.driveController.startController(this.leftController.getPosition(), this.rightController.getPosition());
    }
}
