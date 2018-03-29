package frc.team6223.arsenalFramework.hardware;


import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team6223.arsenalFramework.logging.Loggable;


public class ArsenalNavXMicro extends AHRS implements Gyro, Loggable {

    public ArsenalNavXMicro() {
        super(I2C.Port.kMXP);
    }

    @Override
    public void calibrate() {

    }

    @Override
    public void dashboardPeriodic() {
        SmartDashboard.putNumber("NavX Yaw", this.getYaw());
        SmartDashboard.putNumber("NavX Pitch", this.getPitch());
        SmartDashboard.putNumber("NavX Roll", this.getRoll());
    }
}
