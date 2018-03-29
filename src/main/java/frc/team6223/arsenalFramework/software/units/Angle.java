package frc.team6223.arsenalFramework.software.units;


import java.util.Objects;


public class Angle extends Unit<AngleUnits> {

    private double angle;
    private AngleUnits scale;

    public Angle(double angle, AngleUnits scale) {
        this.angle = angle;
        this.scale = scale;
    }

    @Override
    public double numericValue() {
        return angle * scale.getScaleFactor();
    }

    @Override
    public double numericValue(AngleUnits rep) {
        return this.numericValue() / rep.getScaleFactor();
    }

    @Override
    public Unit<AngleUnits> unit() {
        return this.rescale(this.getDefaultScale());
    }

    @Override
    public Unit<AngleUnits> rescale(AngleUnits rep) {
        return new Angle(angle / rep.getScaleFactor(), rep);
    }

    @Override
    public AngleUnits getScale() {
        return this.scale;
    }

    @Override
    public AngleUnits getDefaultScale() {
        return AngleUnits.DEGREES;
    }

    @Override
    public Angle plus(Unit<AngleUnits> inc) {
        return new Angle(this.numericValue() + inc.numericValue(), getDefaultScale());
    }

    @Override
    public Angle minus(Unit<AngleUnits> dec) {
        return new Angle(this.numericValue() - dec.numericValue(), getDefaultScale());
    }

    @Override
    public Angle times(Unit<AngleUnits> mult) {
        return new Angle(this.numericValue() * mult.numericValue(), getDefaultScale());
    }

    @Override
    public Angle div(Unit<AngleUnits> div) {
        return new Angle(this.numericValue() / div.numericValue(), getDefaultScale());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }
        Angle angle1 = (Angle) o;
        return Double.compare(angle1.angle, angle) == 0 && getScale() == angle1.getScale();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), angle, getScale());
    }
}
