package frc.team6223.robot;


import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import frc.team6223.arsenalFramework.drive.ArsenalDrive;
import frc.team6223.arsenalFramework.hardware.ArsenalNavXMicro;
import frc.team6223.arsenalFramework.hardware.ArsenalRobot;
import frc.team6223.arsenalFramework.hardware.motor.ArsenalTalon;
import frc.team6223.arsenalFramework.operator.ArsenalOperatorInterface;
import frc.team6223.arsenalFramework.software.commands.ArcadeDriveController;
import frc.team6223.arsenalFramework.software.commands.ForceMovementController;
import frc.team6223.arsenalFramework.software.units.Time;
import frc.team6223.arsenalFramework.software.units.TimeUnits;
import frc.team6223.robot.subsystem.Climber;


public class Robot extends ArsenalRobot {

    private ArsenalDrive drive;

    private Climber climberSubsystem = new Climber(
        new ArsenalTalon(3, false, false, false),
        new ArsenalTalon(7, false, false, false),
        new ArsenalTalon(4, false, false, false)
    );

    public Robot() {
        super(DEFAULT_PERIOD, 0.05);
    }

    @Override
    protected SendableChooser<Command> injectAutonomousChooser() {
        SendableChooser<Command> sendableChooser = new SendableChooser<>();
        sendableChooser.addDefault(
          "Move forward for 2 sec",
          new ForceMovementController(
            drive,
            new Time(4.0, TimeUnits.SECONDS),
            0.5, 0.5
          )
        );
        return sendableChooser;
    }

    @Override
    protected void injectSendableChoosers() {
        // no-op
    }

    @Override
    protected void allocateSubsystems(Preferences prefs) {
        drive = new ArsenalDrive(
          new ArsenalNavXMicro(),
          new ArsenalTalon(1),
          new ArsenalTalon(2)
        );
    }

    @Override
    protected ArsenalOperatorInterface allocateOperatorInterface(Preferences prefs) {
        return new OI(climberSubsystem);
    }

    @Override
    protected void setTeleoperatedCommand() {
          new ArcadeDriveController(drive, this.operatorInterface.getPrimaryJoystick(), 1.0).start();
    }

    public void autonomousInit() {
        super.autonomousInit();
        this.drive.resetEncoders();
    }

    public void teleopInit() {
        super.teleopInit();
        this.drive.resetEncoders();
    }

    @Override
    public void dashboardPeriodic() {
        this.drive.dashboardPeriodic();
    }
}
