package org.usfirst.frc.team226.robot.commands;

import org.usfirst.frc.team226.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class cmdMoveRightFeeder_manual extends Command {

	public cmdMoveRightFeeder_manual() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.rightFeeder);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.rightFeeder.setFeederSpeed(Robot.oi.manip.getLeftJoystick_Y());
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
