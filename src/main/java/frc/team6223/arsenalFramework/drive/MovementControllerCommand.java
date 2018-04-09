package frc.team6223.arsenalFramework.drive;


import edu.wpi.first.wpilibj.command.Command;
import frc.team6223.arsenalFramework.drive.ArsenalDrive;
import frc.team6223.arsenalFramework.drive.ControllerInput;
import frc.team6223.arsenalFramework.drive.DriveControllerOutput;
import frc.team6223.arsenalFramework.logging.Loggable;
import frc.team6223.arsenalFramework.software.units.Distance;


public abstract class MovementControllerCommand extends Command implements Loggable {

    private ArsenalDrive driveSubsystem;

    public MovementControllerCommand(ArsenalDrive driveSubsystem) {
        this.driveSubsystem = driveSubsystem;
        requires(driveSubsystem);
    }

    @Override
    public synchronized void start() {
        super.start();
        this.startController(driveSubsystem.getLeftControllerDistance(), driveSubsystem.getRightControllerDistance());
    }

    @Override
    protected void end() {
        super.end();
        this.stopController();
    }

    @Override
    protected void interrupted() {
        super.interrupted();
        this.interruptedController();
    }

    @Override
    protected void execute() {
        super.execute();
        DriveControllerOutput output = this.calculateMotorOutput(driveSubsystem.generateControllerInput());
        driveSubsystem.setOutput(output.getLeftOutput(), output.getRightOutput());
    }

    protected abstract DriveControllerOutput calculateMotorOutput(ControllerInput input);
    protected abstract void startController(Distance leftInitial, Distance rightInitial);
    protected abstract void stopController();
    protected abstract void interruptedController();

}
