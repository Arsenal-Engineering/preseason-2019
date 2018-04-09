package frc.team6223.arsenalFramework.hardware.motor;


import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team6223.arsenalFramework.software.NetworkTableUtilities;

import java.util.ArrayList;
import java.util.List;


public class ArsenalVictor implements ArsenalCANMotorController {

    private final int victorId;
    private final VictorSPX internalMotorController;
    private MotorControlMode internalControlMode;

    private List<ArsenalCANMotorController> followers = new ArrayList<>();

    public ArsenalVictor(int victorId, boolean inverted) {
        this.victorId = victorId;

        this.internalMotorController = new VictorSPX(victorId);
        this.internalMotorController.setInverted(inverted);
    }

    public int getVictorId() {
        return victorId;
    }

    @Override
    public VictorSPX getInternalMotorController() {
        return internalMotorController;
    }

    public MotorControlMode getInternalMotorControlMode() {
        return internalControlMode;
    }

    @Override
    public boolean isInverted() {
        return this.internalMotorController.getInverted();
    }

    @Override
    public void addFollower(ArsenalCANMotorController controller) {
        controller.getInternalMotorController().follow(this.internalMotorController);
        followers.add(controller);
    }

    @Override
    public void set(MotorControlMode mode, double output) {
        this.internalControlMode = mode;
        this.internalMotorController.set(ControlMode.PercentOutput, output);
    }

    @Override
    public void dashboardPeriodic(NetworkTable table) {
        NetworkTableUtilities.putItemInTable(table, "Victor " + this.victorId + " Inverted", this.isInverted());
        NetworkTableUtilities.putItemInTable(table, "Victor " + this.victorId + " MCM", internalControlMode.toString());
    }
}
