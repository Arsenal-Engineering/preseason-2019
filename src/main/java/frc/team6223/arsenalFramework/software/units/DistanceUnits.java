package frc.team6223.arsenalFramework.software.units;


public enum DistanceUnits implements ScaleUnit {
    KILOMETERS(3280.84, "km"),
    CENTIMETERS(0.0328084, "cm"),
    MILLIMETERS(0.00328084, "mm"),
    METERS(39.37, "m"),
    INCHES(12.0, "in"),
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