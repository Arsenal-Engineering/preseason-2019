package frc.team6223.arsenalFramework.software.units;


public class RateScaleUnit<tU extends ScaleUnit, dU extends ScaleUnit> implements ScaleUnit {

    public RateScaleUnit(tU topScale, dU bottomScale) {
        this.topScale = topScale;
        this.bottomScale = bottomScale;
    }

    private tU topScale;
    private dU bottomScale;

    public tU getTopScale() {
        return topScale;
    }

    public dU getBottomScale() {
        return bottomScale;
    }

    @Override
    public double getScaleFactor() {
        return topScale.getScaleFactor() / bottomScale.getScaleFactor();
    }

    @Override
    public String getAbbreviation() {
        return topScale.getAbbreviation() + "/" + bottomScale.getAbbreviation();
    }
}
