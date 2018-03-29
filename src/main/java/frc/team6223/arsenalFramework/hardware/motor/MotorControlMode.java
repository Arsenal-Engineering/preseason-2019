package frc.team6223.arsenalFramework.hardware.motor;


public enum MotorControlMode {
    /**
     * Open Loop Voltage Percentage Out
     */
    VoltagePercentOut,
    /**
     * Closed Loop [frc.team6223.utils.pid.PIDFController] Distance
     */
    PIDDistance,
    /**
     * Closed Loop [frc.team6223.utils.pid.PIDFController] Velocity
     */
    PIDVelocity,
    /**
     * Closed Loop [frc.team6223.utils.pid.PIDFController] Turn to Relative Angle
     */
    PIDRelativeAngle,
    /**
     * Closed Loop Motion Profiling (experimental!)
     */
    ExperimentalMotionProfiling,


}
