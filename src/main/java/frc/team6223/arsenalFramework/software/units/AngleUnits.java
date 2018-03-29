package frc.team6223.arsenalFramework.software.units;


public enum AngleUnits implements ScaleUnit {
    DEGREES(1.0, "deg"),
    RADIANS(180/Math.PI, "rad");

    private double scaleFactor;
    private String abbreviation;

    AngleUnits(double scaleFactor, String abbreviation) {
        this.scaleFactor = scaleFactor;
        this.abbreviation = abbreviation;
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