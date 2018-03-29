package frc.team6223.arsenalFramework.software.controllers;


import frc.team6223.arsenalFramework.drive.ControllerInput;
import frc.team6223.arsenalFramework.drive.DriveController;
import frc.team6223.arsenalFramework.drive.DriveControllerOutput;
import frc.team6223.arsenalFramework.hardware.motor.MotorControlMode;
import frc.team6223.arsenalFramework.software.units.Distance;


public class NoMovementController implements DriveController {

    @Override
    public DriveControllerOutput calculateMotorOutput(ControllerInput input) {
        return new DriveControllerOutput(MotorControlMode.VoltagePercentOut, 0.0, 0.0);
    }

    @Override
    public void startController(Distance leftInitial, Distance rightInitial) {
        System.out.println("NoMovementController enabled");
    }

    @Override
    public void stopController() {
        System.out.println("NoMovementController disabled");
    }

    @Override
    public boolean isMovementCompleted() {
        return false;
    }

    @Override
    public void dashboardPeriodic() {

    }
}
