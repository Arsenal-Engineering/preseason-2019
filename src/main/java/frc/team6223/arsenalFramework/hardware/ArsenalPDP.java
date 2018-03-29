package frc.team6223.arsenalFramework.hardware;


import edu.wpi.first.wpilibj.PowerDistributionPanel;
import frc.team6223.arsenalFramework.software.math.CanLinearIntegrate;
import frc.team6223.arsenalFramework.software.math.RunningLinearIntegration;


public class ArsenalPDP extends PowerDistributionPanel implements CanLinearIntegrate {

    private RunningLinearIntegration runningLinearIntegration = new RunningLinearIntegration(15);

    public ArsenalPDP(int module) {
        super(module);
    }

    public double getEstimatedPower() {
        return this.getAreaUnderCurve();
    }

    @Override
    public double getAreaUnderCurve() {
        return runningLinearIntegration.getAreaUnderCurve();
    }

    public double getEstimatedResistance() {
        return this.getSlope();
    }

    @Override
    public double getSlope() {
        return runningLinearIntegration.getSlope();
    }

    @Override
    public double getIntercept() {
        return runningLinearIntegration.getIntercept();
    }

    @Override
    public void addPoint(double x, double y) {
        runningLinearIntegration.addPoint(x, y);
    }
}
