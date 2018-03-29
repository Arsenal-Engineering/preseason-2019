package frc.team6223.arsenalFramework.software.math;


public interface CanLinearRegress {

    double getSlope();
    double getIntercept();

    void addPoint(double x, double y);

}
