package frc.team6223.arsenalFramework.hardware;


import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import frc.team6223.arsenalFramework.operator.ArsenalOperatorInterface;


public abstract class ArsenalRobot extends TimedRobot {

    private final double updaterPeriod;
    private ArsenalOperatorInterface operatorInterface;
    private SendableChooser<Command> autonomousChooser;

    public ArsenalRobot(double robotPeriod, double updaterPeriod) {
        super.setPeriod(robotPeriod);
        this.updaterPeriod = updaterPeriod;
    }

    @Override
    public void robotInit() {
        super.robotInit();
        this.allocateSubsystems(Preferences.getInstance());
        this.operatorInterface = this.allocateOperatorInterface(Preferences.getInstance());
        autonomousChooser = this.injectAutonomousChooser();
        this.injectSendableChoosers();
    }

    @Override
    public void disabledInit() {
        super.disabledInit();
        this.clearScheduler();
    }

    @Override
    public void autonomousInit() {
        super.autonomousInit();
        this.clearScheduler();
        autonomousChooser.getSelected().start();
    }

    @Override
    public void teleopInit() {
        super.teleopInit();
        this.clearScheduler();
        this.setTeleoperatedCommand();
    }

    @Override
    public void disabledPeriodic() {
        super.disabledPeriodic();
        this.dashboardPeriodic();
        this.runScheduler();
    }

    @Override
    public void autonomousPeriodic() {
        super.autonomousPeriodic();
        this.dashboardPeriodic();
        this.runScheduler();
    }

    @Override
    public void teleopPeriodic() {
        super.teleopPeriodic();
        this.dashboardPeriodic();
        this.runScheduler();
    }

    protected abstract void dashboardPeriodic();
    protected abstract SendableChooser<Command> injectAutonomousChooser();
    protected abstract void injectSendableChoosers();
    protected abstract void allocateSubsystems(Preferences prefs);
    protected abstract ArsenalOperatorInterface allocateOperatorInterface(Preferences prefs);
    protected abstract void setTeleoperatedCommand();

    private void runScheduler() {
        Scheduler.getInstance().run();
    }

    private void clearScheduler() {
        Scheduler.getInstance().removeAll();
    }

    public double getUpdaterPeriod() {
        return updaterPeriod;
    }

    public ArsenalOperatorInterface getOperatorInterface() {
        return operatorInterface;
    }


}
