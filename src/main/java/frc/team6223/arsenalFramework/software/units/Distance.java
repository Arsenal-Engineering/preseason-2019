package frc.team6223.arsenalFramework.software.units;


import java.util.Objects;

import static frc.team6223.robot.conf.WheelKt.wheelCircumference;


public class Distance extends Unit<DistanceUnits> {

    private double distance;
    private DistanceUnits scale;

    public Distance(double distance, DistanceUnits scale) {
        this.distance = distance;
        this.scale = scale;
    }

    @Override
    public double numericValue() {
        return distance * scale.getScaleFactor();
    }

    @Override
    public double numericValue(DistanceUnits rep) {
        return this.numericValue() / rep.getScaleFactor();
    }

    @Override
    public Unit<DistanceUnits> unit() {
        return new Distance(this.numericValue(), this.getDefaultScale());
    }

    @Override
    public Unit<DistanceUnits> rescale(DistanceUnits rep) {
        return new Distance(this.numericValue(rep), rep);
    }

    @Override
    public DistanceUnits getScale() {
        return this.scale;
    }

    @Override
    public DistanceUnits getDefaultScale() {
        return DistanceUnits.FEET;
    }

    @Override
    public Distance plus(Unit<DistanceUnits> inc) {
        return new Distance(this.numericValue() + inc.numericValue(), DistanceUnits.FEET);
    }

    @Override
    public Distance minus(Unit<DistanceUnits> dec) {
        return new Distance(this.numericValue() - dec.numericValue(), DistanceUnits.FEET);
    }

    @Override
    public Distance times(Unit<DistanceUnits> mult) {
        return new Distance(this.numericValue() * mult.numericValue(), DistanceUnits.FEET);
    }

    @Override
    public Distance div(Unit<DistanceUnits> div) {
        return new Distance(this.numericValue() / div.numericValue(), DistanceUnits.FEET);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }
        Distance distance1 = (Distance) o;
        return Double.compare(distance1.distance, distance) == 0 && getScale() == distance1.getScale();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), distance, getScale());
    }

    public static Distance convertMagPulseToDistance(double magPulse) {
        return new Distance(((magPulse / 4096) * wheelCircumference), DistanceUnits.INCHES);
    }
}
