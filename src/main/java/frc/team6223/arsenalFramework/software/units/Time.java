package frc.team6223.arsenalFramework.software.units;


public class Time extends Unit<TimeUnits> {

    private double time;
    private TimeUnits scale;

    public Time(double time, TimeUnits scale) {
        this.time = time;
        this.scale = scale;
    }

    @Override
    public double numericValue() {
        return time * scale.getScaleFactor();
    }

    @Override
    public double numericValue(TimeUnits rep) {
        return this.numericValue() / rep.getScaleFactor();
    }

    @Override
    public Time unit() {
        return new Time(this.numericValue(), this.getDefaultScale());
    }

    @Override
    public Time rescale(TimeUnits rep) {
        return new Time(this.numericValue(rep), rep);
    }

    @Override
    public TimeUnits getScale() {
        return scale;
    }

    @Override
    public TimeUnits getDefaultScale() {
        return TimeUnits.MILLISECONDS;
    }

    @Override
    public Time plus(Unit<TimeUnits> inc) {
        return new Time(this.numericValue() + inc.numericValue(), getDefaultScale());
    }

    @Override
    public Time minus(Unit<TimeUnits> dec) {
        return new Time(this.numericValue() - dec.numericValue(), getDefaultScale());
    }

    @Override
    public Time times(Unit<TimeUnits> mult) {
        return new Time(this.numericValue() * mult.numericValue(), getDefaultScale());
    }

    @Override
    public Time div(Unit<TimeUnits> div) {
        return new Time(this.numericValue() / div.numericValue(), getDefaultScale());
    }
}