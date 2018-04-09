package frc.team6223.arsenalFramework.drive;


import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team6223.arsenalFramework.hardware.ArsenalNavXMicro;
import frc.team6223.arsenalFramework.hardware.motor.ArsenalTalon;
import frc.team6223.arsenalFramework.hardware.motor.MotorControlMode;
import frc.team6223.arsenalFramework.logging.Loggable;
import frc.team6223.arsenalFramework.software.units.Distance;


public class ArsenalDrive extends Subsystem implements Loggable {

    private ArsenalNavXMicro navXMicro;
    private ArsenalTalon leftController;
    private ArsenalTalon rightController;

    public ArsenalDrive(ArsenalNavXMicro navXMicro, ArsenalTalon leftController,
      ArsenalTalon rightController)
    {
        this.navXMicro = navXMicro;
        this.leftController = leftController;
        this.rightController = rightController;
    }

    public ControllerInput generateControllerInput() {
        return new ControllerInput(
          leftController.getPosition(), leftController.getVelocity(),
          rightController.getPosition(), rightController.getVelocity(),
          leftController.getRawPosition(), rightController.getRawPosition(),
          navXMicro
        );
    }

    public void setOutput(double left, double right) {
        leftController.set(MotorControlMode.VoltagePercentOut, left);
        rightController.set(MotorControlMode.VoltagePercentOut, right);
    }

    public void setEncoderPhasing(boolean left, boolean right) {
        this.leftController.setEncoderPhase(left);
        this.rightController.setEncoderPhase(right);
    }

    public Distance getLeftControllerDistance() {
        return leftController.getPosition();
    }

    public Distance getRightControllerDistance() {
        return rightController.getPosition();
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
        this.navXMicro.dashboardPeriodic();
    }
}
