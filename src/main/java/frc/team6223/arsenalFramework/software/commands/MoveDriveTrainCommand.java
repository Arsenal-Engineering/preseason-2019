package frc.team6223.arsenalFramework.software.commands;


import edu.wpi.first.wpilibj.command.Command;
import frc.team6223.arsenalFramework.drive.ArsenalDrive;
import frc.team6223.arsenalFramework.drive.DriveController;
import frc.team6223.arsenalFramework.software.controllers.NoMovementController;


public class MoveDriveTrainCommand extends Command {

    private DriveController controller;
    private ArsenalDrive driveSubsystem;

    public MoveDriveTrainCommand(DriveController controller, ArsenalDrive driveSubsystem) {
        this.controller = controller;
        this.driveSubsystem = driveSubsystem;

        requires(driveSubsystem);
        this.setInterruptible(true);
    }

    @Override
    public synchronized void start() {
        super.start();
        driveSubsystem.setDriveController(controller);
    }

    @Override
    protected void end() {
        super.end();
        driveSubsystem.setDriveController(new NoMovementController());
    }

    @Override
    protected void execute() {
        super.execute();
        driveSubsystem.driveMotors();
    }

    @Override
    protected void interrupted() {
        super.interrupted();
    }

    @Override
    protected boolean isFinished() {
        return controller.isMovementCompleted();
    }
}
