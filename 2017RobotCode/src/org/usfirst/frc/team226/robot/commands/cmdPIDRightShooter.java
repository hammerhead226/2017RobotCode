package org.usfirst.frc.team226.robot.commands;

import org.usfirst.frc.team226.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class cmdPIDRightShooter extends Command {

	private double setpoint;

	public cmdPIDRightShooter(double setpoint) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.rightShooter);
		this.setpoint = setpoint;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.rightShooter.velPID.setSetpoint(setpoint);
		Robot.rightShooter.velPID.enable();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		double output = Robot.rightShooter.velPID.get();
		double error = Robot.rightShooter.velPID.getError();
		Robot.rightShooter.errorLog = error;
		Robot.rightShooter.pidOutputLog = output;

		Robot.rightShooter.setShooterSpeed(output);
		
		if (Robot.rightShooter.velPID.onTarget()) {
			Robot.rightFeeder.setFeederSpeed(1.0);
		} else {
			Robot.rightShooter.setShooterSpeed(0.0);
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return Robot.oi.manip.getBButtonPressed();
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.rightFeeder.setFeederSpeed(0.0);
		Robot.rightShooter.velPID.reset();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		Robot.rightFeeder.setFeederSpeed(0.0);
		Robot.rightShooter.velPID.reset();
	}
}
