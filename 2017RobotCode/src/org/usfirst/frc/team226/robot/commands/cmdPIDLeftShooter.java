package org.usfirst.frc.team226.robot.commands;

import org.usfirst.frc.team226.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class cmdPIDLeftShooter extends Command {

	private double setpoint;

	public cmdPIDLeftShooter(double setpoint) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.leftShooter);
		this.setpoint = setpoint;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.leftShooter.velPID.setSetpoint(setpoint);
		Robot.leftShooter.velPID.enable();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		double output = Robot.leftShooter.velPID.get();
		double error = Robot.leftShooter.velPID.getError();
		
		Robot.leftShooter.errorLog = error;
		Robot.leftShooter.pidOutputLog = output;

		Robot.leftShooter.setShooterSpeed(output);
		
		if (Robot.leftShooter.velPID.onTarget()) {
			Robot.leftFeeder.setFeederSpeed(1.0);
		} else {
			Robot.leftShooter.setShooterSpeed(0.0);
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return Robot.oi.manip.getBButtonPressed();
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.leftFeeder.setFeederSpeed(0.0);
		Robot.leftShooter.velPID.reset();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		Robot.leftFeeder.setFeederSpeed(0.0);
		Robot.leftShooter.velPID.reset();
	}
}
