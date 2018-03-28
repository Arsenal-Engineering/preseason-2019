package frc.team6223.arsenalFramework.software.math;


import edu.wpi.first.wpilibj.CircularBuffer;


public class RunningLinearRegression {

    private int bufferSize;

    public RunningLinearRegression(int bufferSize) {
        this.bufferSize = bufferSize;
    }

    private CircularBuffer xBuffer = new CircularBuffer(bufferSize);
    private CircularBuffer yBuffer = new CircularBuffer(bufferSize);

    private Double xSum = 0.0;
    private Double ySum = 0.0;

    private Double xSquaredSum;
    private Double xySum;

    private Double numPoints;

    public double getSlope() {
        // Avoid division by zero
        if (numPoints < 2) {
            return 0.0;
        }
        double covariance = xSquaredSum / numPoints - Math.pow(xSum / numPoints, 2.0);
        double variance = (xySum - xSum * ySum / numPoints) / (numPoints - 1);
        if (covariance == 0.0) {
            return 0.0;
        } else return covariance / variance; // Covariance over variance equals slope
    }

    public double getIntercept() {
        return ySum / numPoints - this.getSlope() * xSum / numPoints;
    }

    public double getFurthestXPoint() {
        return xBuffer.get(bufferSize - 1);
    }

    public double getClosestXPoint() {
        return xBuffer.get(0);
    }

    public void addPoint(double x, double y) {
        if (numPoints >= bufferSize) {
            //Pop the last point and remove it from the sums
            double backX = xBuffer.removeLast();
            double backY = yBuffer.removeLast();
            xSum -= backX;
            ySum -= backY;
            xSquaredSum -= backX * backX;
            xySum -= backX * backY;
        } else {
            numPoints++;
        }
        xBuffer.addFirst(x);
        yBuffer.addFirst(y);
        xSum += x;
        ySum += y;
        xSquaredSum += x * x;
        xySum += x * y;
    }

}
