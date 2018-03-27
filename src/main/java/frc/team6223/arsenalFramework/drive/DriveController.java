package frc.team6223.arsenalFramework.drive;


import frc.team6223.arsenalFramework.logging.Loggable;
import frc.team6223.arsenalFramework.software.units.Distance;


public interface DriveController extends Loggable {
    DriveControllerOutput calculateMotorOutput(ControllerInput input);
    void startController(Distance leftInitial, Distance rightInitial);
    void stopController();
    boolean isMovementCompleted();
}
