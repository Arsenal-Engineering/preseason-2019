package frc.team6223.arsenalFramework.software.units;


public enum TimeUnits implements ScaleUnit {
    MICROSECONDS(.001, "microseconds"),
    MILLISECONDS(1.0, "ms"),
    SECONDS(1000.0, "s"),
    MINUTE(60000.0, "min");

    private double scaleFactor;
    private String abbreviation;

    TimeUnits(double scaleFactor, String abbreviation) {
        this.scaleFactor = scaleFactor;
        this.abbreviation = abbreviation;
    }

    @Override
    public double getScaleFactor() {
        return scaleFactor;
    }

    @Override
    public String getAbbreviation() {
        return abbreviation;
    }
}