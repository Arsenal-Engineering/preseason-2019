package frc.team6223.arsenalFramework.software.pid;


import frc.team6223.arsenalFramework.hardware.TimeUtilities;
import frc.team6223.arsenalFramework.software.units.Time;
import frc.team6223.arsenalFramework.software.units.TimeUnits;


public class PIDFController {

    private PIDFConstants constants;
    private double setPoint;

    private double currentError = 0.0;
    private double lastError = Double.NaN;
    private double integralGain = 0.0;
    private Time lastTime = new Time(Double.NaN, TimeUnits.SECONDS);

    public double runController(double currentInput) {
        // calculate constants for this iteration of the loop
        this.currentError = setPoint - currentInput;
        Time deltaTime = TimeUtilities.currentTimeSec().minus(lastTime);
        this.lastTime = TimeUtilities.currentTimeSec();

        double calculated = this.constants.getkD() * (currentError - this.lastError / deltaTime.numericValue(TimeUnits.SECONDS));
        double derivative;
        if (calculated != 0.0 && calculated != Double.NaN) {
            derivative = calculated;
        } else {
            derivative = 0.0;
        }

        integralGain += currentError;

        // first calculate the P, which should always exist
        double output = (this.constants.getkP() * currentError);
        // next check for the I gain
        output += (this.constants.getkI() * integralGain);
        // next check for the D gain
        output += derivative;
        // next apply the kF gain
        output += (this.constants.getkF() * setPoint);
        return output;
    }

    public void resetController() {
        integralGain = 0.0;
        lastError = Double.NaN;
        lastTime = new Time(Double.NaN, TimeUnits.SECONDS);
    }

    public PIDFConstants getConstants() {
        return constants;
    }

    public double getSetPoint() {
        return setPoint;
    }

    public double getCurrentError() {
        return currentError;
    }

    public double getLastError() {
        return lastError;
    }

    public double getIntegralGain() {
        return integralGain;
    }

    public Time getLastTime() {
        return lastTime;
    }

    public boolean isFinished() {
        return setPoint == currentError;
    }
}
