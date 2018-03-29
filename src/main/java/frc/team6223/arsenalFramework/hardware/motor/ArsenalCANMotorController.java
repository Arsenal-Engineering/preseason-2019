package frc.team6223.arsenalFramework.hardware.motor;


import com.ctre.phoenix.motorcontrol.can.BaseMotorController;
import frc.team6223.arsenalFramework.logging.Loggable;


public interface ArsenalCANMotorController extends Loggable {

    BaseMotorController getInternalMotorController();
    boolean isInverted();
    MotorControlMode getInternalMotorControlMode();

    void set(MotorControlMode mode, double output);
    void addFollower(ArsenalCANMotorController controller);

}
