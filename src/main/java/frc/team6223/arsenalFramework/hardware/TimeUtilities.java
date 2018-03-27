package frc.team6223.arsenalFramework.hardware;


import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.util.BaseSystemNotInitializedException;
import frc.team6223.arsenalFramework.software.units.Time;
import frc.team6223.arsenalFramework.software.units.TimeUnits;


public class TimeUtilities {

    public static Time currentTimeSec() {
        Time time = new Time(System.currentTimeMillis(), TimeUnits.MILLISECONDS).rescale(TimeUnits.SECONDS);
        try {
            time = new Time(Timer.getFPGATimestamp(), TimeUnits.SECONDS);
        } catch (BaseSystemNotInitializedException e) {}
        return time;
    }

}
