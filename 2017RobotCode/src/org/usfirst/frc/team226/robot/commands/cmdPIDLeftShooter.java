package org.usfirst.frc.team226.robot.commands;

import org.usfirst.frc.team226.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
		Robot.leftShooter.shooterPID.setSetpoint(setpoint);
		Robot.leftShooter.shooterPID.enable();
//		Robot.timer.start();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		double output = Robot.leftShooter.shooterPID.get();
		double error = Robot.leftShooter.shooterPID.getError();
		
		Robot.leftShooter.errorLog = error;
		Robot.leftShooter.pidOutputLog = output;

		Robot.leftShooter.setShooterSpeed(output);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return Robot.oi.driver.getBButtonPressed();
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.leftShooter.shooterPID.reset();
//		double time = Robot.timer.get();
//		SmartDashboard.putNumber("Final time", time);
//		Robot.timer.stop();
//		Robot.timer.reset();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		Robot.leftShooter.shooterPID.disable();
	}
}
