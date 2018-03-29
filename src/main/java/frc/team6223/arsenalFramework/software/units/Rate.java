package frc.team6223.arsenalFramework.software.units;


import java.util.Objects;


public class Rate<tU extends ScaleUnit, dU extends ScaleUnit> extends Unit<RateScaleUnit<tU, dU>> {

    private Unit<tU> topUnit;
    private Unit<dU> bottomUnit;

    public Rate(Unit<tU> topUnit, Unit<dU> bottomUnit) {
        this.topUnit = topUnit;
        this.bottomUnit = bottomUnit;
    }

    @Override
    public double numericValue() {
        return topUnit.numericValue(topUnit.getScale()) / bottomUnit.numericValue(bottomUnit.getScale());
    }

    @Override
    public double numericValue(RateScaleUnit<tU, dU> rep) {
        return this.rescaleScalar(rep.getTopScale(), rep.getBottomScale());
    }

    public double rescaleTopScalar(tU rep) {
        return this.rescaleTopRate(rep).numericValue();
    }

    public Rate<tU, dU> rescaleTopRate(tU rep) {
        return new Rate<>(topUnit.rescale(rep), bottomUnit);
    }

    public double rescaleBottomScalar(dU rep) {
        return this.rescaleBottomRate(rep).numericValue();
    }

    public Rate<tU, dU> rescaleBottomRate(dU rep) {
        return new Rate<>(topUnit, bottomUnit.rescale(rep));
    }

    public double rescaleScalar(tU rep, dU repBot) {
        return this.rescaleRate(rep, repBot).numericValue();
    }

    public Rate<tU, dU> rescaleRate(tU rep, dU repBot) {
        return new Rate<>(this.topUnit.rescale(rep), this.bottomUnit.rescale(repBot));
    }

    @Override
    public Unit<RateScaleUnit<tU, dU>> unit() {
        return this.rescaleRate(this.topUnit.getDefaultScale(), this.bottomUnit.getDefaultScale());
    }

    @Override
    public Unit<RateScaleUnit<tU, dU>> rescale(RateScaleUnit<tU, dU> rep) {
        return this.rescaleRate(rep.getTopScale(), rep.getBottomScale());
    }

    @Override
    public RateScaleUnit<tU, dU> getScale() {
        return new RateScaleUnit<>(this.topUnit.getScale(), this.bottomUnit.getScale());
    }

    @Override
    public RateScaleUnit<tU, dU> getDefaultScale() {
        return new RateScaleUnit<>(this.topUnit.getDefaultScale(), this.bottomUnit.getDefaultScale());
    }

    @Override
    public Unit<RateScaleUnit<tU, dU>> plus(Unit<RateScaleUnit<tU, dU>> inc) {
        return null;
    }

    @Override
    public Unit<RateScaleUnit<tU, dU>> minus(Unit<RateScaleUnit<tU, dU>> dec) {
        return null;
    }

    @Override
    public Unit<RateScaleUnit<tU, dU>> times(Unit<RateScaleUnit<tU, dU>> mult) {
        return null;
    }

    @Override
    public Unit<RateScaleUnit<tU, dU>> div(Unit<RateScaleUnit<tU, dU>> div) {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }
        Rate<?, ?> rate = (Rate<?, ?>) o;
        return Objects.equals(topUnit, rate.topUnit) && Objects.equals(bottomUnit, rate.bottomUnit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), topUnit, bottomUnit);
    }
}