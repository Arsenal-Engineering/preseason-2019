package frc.team6223.arsenalFramework.software.math;


public class RunningLinearIntegration implements CanLinearIntegrate {

    private int bufferSize;

    private RunningLinearRegression linearRegression = new RunningLinearRegression(bufferSize);

    public RunningLinearIntegration(int bufferSize) {
        this.bufferSize = bufferSize;
    }

    private static double solveLinear(double slope, double yIntercept, double xTerm) {
        return (slope * xTerm) + yIntercept;
    }

    private static double linearIntegration(double slope, double yIntercept, double aTerm, double bTerm) {
        // Integrate y=mx+b using the area of a trapezoid (since the graph is linear) 0.5(y(a)+y(b))*b
        return 0.5 * (solveLinear(slope, yIntercept, aTerm) + solveLinear(slope, yIntercept, bTerm)) * bTerm;
    }

    @Override
    public double getSlope() {
        return linearRegression.getSlope();
    }

    @Override
    public double getIntercept() {
        return linearRegression.getIntercept();
    }

    @Override
    public void addPoint(double x, double y) {
        linearRegression.addPoint(x, y);
    }

    @Override
    public double getAreaUnderCurve() {
        return linearIntegration(
          this.getSlope(),
          this.getIntercept(),
          linearRegression.getClosestXPoint(),
          linearRegression.getFurthestXPoint()
        );
    }
}
