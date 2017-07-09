package org.usfirst.frc.team226.robot.commands;

import org.usfirst.frc.team226.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ReleaseIntake extends Command {
	
	private Timer timer = new Timer();

    public ReleaseIntake() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.climberIntake);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	timer.start();
    	Robot.climberIntake.toggleIntakeServo();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return timer.get() >= 0.7;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.climberIntake.toggleIntakeServo();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
