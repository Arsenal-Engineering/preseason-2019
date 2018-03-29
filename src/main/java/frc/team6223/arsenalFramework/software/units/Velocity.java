package frc.team6223.arsenalFramework.software.units;


public class Velocity extends Rate<DistanceUnits, TimeUnits> {

    public Velocity(Unit<DistanceUnits> topUnit, Unit<TimeUnits> bottomUnit) {
        super(topUnit, bottomUnit);
    }

    public static Velocity convertMagPulseRateToVelocity(double magPulseRate) {
        return new Velocity(Distance.convertMagPulseToDistance(magPulseRate), new Time(100.0, TimeUnits.MILLISECONDS));
    }
}
