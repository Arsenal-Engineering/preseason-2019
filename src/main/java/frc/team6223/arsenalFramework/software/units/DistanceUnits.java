package frc.team6223.arsenalFramework.software.units;


public enum DistanceUnits implements ScaleUnit {
    METERS(3.28084, "m"),
    INCHES(0.0833333, "in"),
    FEET(1.0, "ft");

    private String abbreviation;
    private double scaleFactor;

    DistanceUnits(double scaleFactor, String abbreviation) {
        this.abbreviation = abbreviation;
        this.scaleFactor = scaleFactor;
    }

    @Override
    public double getScaleFactor() {
        return this.scaleFactor;
    }

    @Override
    public String getAbbreviation() {
        return this.abbreviation;
    }
}