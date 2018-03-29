package frc.team6223.robot;


import edu.wpi.first.wpilibj.Joystick;
import frc.team6223.arsenalFramework.operator.ArsenalOperatorInterface;
import frc.team6223.robot.subsystem.Climber;

import java.util.ArrayList;
import java.util.List;


public class OI implements ArsenalOperatorInterface {

    private Climber climber;

    public OI(Climber climber) {
        this.climber = climber;
    }

    private Joystick joystick = new Joystick(0);
    private Joystick launchPad = new Joystick(1);

    @Override
    public Joystick getPrimaryJoystick() {
        return joystick;
    }

    @Override
    public List<Joystick> getJoysticks() {
        List<Joystick> joysticks = new ArrayList<>();
        joysticks.add(joystick);
        joysticks.add(launchPad);
        return joysticks;
    }
}
