package frc.team6223.arsenalFramework.hardware;


import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team6223.arsenalFramework.logging.Loggable;
import frc.team6223.arsenalFramework.software.NetworkTableUtilities;


public class ArsenalNavXMicro extends AHRS implements Gyro, Loggable {

    public ArsenalNavXMicro() {
        super(I2C.Port.kMXP);
    }

    @Override
    public void calibrate() {

    }

    @Override
    public void dashboardPeriodic(NetworkTable table) {
        NetworkTableUtilities.putItemInTable(table, "NavX Yaw", this.getYaw());
        NetworkTableUtilities.putItemInTable(table, "NavX Pitch", this.getPitch());
        NetworkTableUtilities.putItemInTable(table, "NavX Roll", this.getRoll());
    }
}
