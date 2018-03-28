package frc.team6223.arsenalFramework.hardware.motor;


import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team6223.arsenalFramework.software.units.*;

import java.util.ArrayList;
import java.util.List;


public class ArsenalTalon implements ArsenalCANMotorController {

    private final int talonId;
    private final TalonSRX internalMotorController;
    private final boolean quadratureEnabled;
    private boolean invertedSensorOutput;

    private List<ArsenalCANMotorController> followers = new ArrayList<>();
    private MotorControlMode internalControlMode;

    public ArsenalTalon(int talonId) {
        this(talonId, false, false);
    }

    public ArsenalTalon(int talonId, boolean quadratureEnabled, boolean startInverted) {
        this(talonId, quadratureEnabled, startInverted, false, false);
    }

    public ArsenalTalon(int talonId, boolean quadratureEnabled, boolean startInverted, boolean startingSensorPhase) {
        this(talonId, quadratureEnabled, startInverted, startingSensorPhase, false);
    }

    public ArsenalTalon(int talonId, boolean quadratureEnabled, boolean startInverted, boolean startingSensorPhase,
      boolean invertedSensorOutput) {
        this.talonId = talonId;
        this.quadratureEnabled = quadratureEnabled;
        this.invertedSensorOutput = invertedSensorOutput;
        this.internalMotorController = new TalonSRX(talonId);

        if (quadratureEnabled) {
            this.internalMotorController.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
            this.internalMotorController.setStatusFramePeriod(StatusFrameEnhanced.Status_3_Quadrature, 20, 0);
            this.resetEncoder();
        }

        this.internalMotorController.setInverted(startInverted);
        this.internalMotorController.setSensorPhase(startingSensorPhase);

        this.internalControlMode = MotorControlMode.VoltagePercentOut;
    }

    public Distance getPosition() {
        if (invertedSensorOutput) {
            return Distance.Companion.convertMagPulseToDistance(-this.internalMotorController.getSelectedSensorPosition(0));
        } else {
            return Distance.Companion.convertMagPulseToDistance(this.internalMotorController.getSelectedSensorPosition(0));
        }
    }

    public Double getRawPosition() {
        if (invertedSensorOutput) {
            return (double)-this.internalMotorController.getSelectedSensorPosition(0);
        } else {
            return (double)this.internalMotorController.getSelectedSensorPosition(0);
        }
    }

    public Velocity getVelocity() {
        return Velocity.Companion.convertMagPulseRateToVelocity(this.internalMotorController.getSelectedSensorVelocity(0));
    }

    @Override
    public void set(MotorControlMode mode, double output) {
        this.internalControlMode = mode;
        internalMotorController.set(ControlMode.PercentOutput, output);
    }

    @Override
    public void addFollower(ArsenalCANMotorController controller) {
        controller.getInternalMotorController().follow(this.internalMotorController);
        followers.add(controller);
    }

    @Override
    public void dashboardPeriodic() {
        SmartDashboard.putNumber("Talon " + this.talonId + " Position", getPosition().numericValue(DistanceUnits.FEET));
        SmartDashboard.putNumber("Talon " + this.talonId + " Velocity (ft/sec)",
          getVelocity().numericValue(new RateScaleFactor<>(DistanceUnits.FEET, TimeUnits.SECONDS)));
        SmartDashboard.putBoolean("Talon " + this.talonId + " Inverted", this.internalMotorController.getInverted());
        SmartDashboard.putString("Talon " + this.talonId + " MCM", internalControlMode.toString());
    }

    public void setEncoderPhase(boolean phase) {
        this.internalMotorController.setSensorPhase(phase);
    }

    public void resetEncoder() {
        if (this.quadratureEnabled) {
            this.internalMotorController.getSensorCollection().setQuadraturePosition(0, 0);
        }
    }

    public double getOutputCurrent() {
        return this.internalMotorController.getOutputCurrent();
    }

    public int getTalonId() {
        return talonId;
    }

    public boolean isQuadratureEnabled() {
        return quadratureEnabled;
    }

    public boolean isInvertedSensorOutput() {
        return invertedSensorOutput;
    }

    public List<ArsenalCANMotorController> getFollowers() {
        return followers;
    }

    public MotorControlMode getInternalMotorControlMode() {
        return internalControlMode;
    }

    public boolean isInverted() {
        return this.internalMotorController.getInverted();
    }

    @Override
    public TalonSRX getInternalMotorController() {
        return internalMotorController;
    }
}
