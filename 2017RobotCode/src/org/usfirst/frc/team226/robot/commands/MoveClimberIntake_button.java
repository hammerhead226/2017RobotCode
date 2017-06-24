package org.usfirst.frc.team226.robot.commands;

import org.usfirst.frc.team226.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */

public class MoveClimberIntake_button extends Command {

	private double direction;
    public MoveClimberIntake_button(double direction) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.climberIntake);
    	this.direction = direction;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.climberIntake.setMotors(direction * 1.0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.climberIntake.setMotors(0.0);

    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.climberIntake.setMotors(0.0);

    }
}
