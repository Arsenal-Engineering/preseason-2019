package frc.team6223.arsenalFramework.operator;


import edu.wpi.first.wpilibj.Joystick;

import java.util.List;


public interface ArsenalOperatorInterface {

    Joystick getPrimaryJoystick();
    List<Joystick> getJoysticks();

}
