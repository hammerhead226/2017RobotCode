package org.usfirst.frc.team226.robot.commands;

import org.usfirst.frc.team226.robot.Robot;

import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetShooterSpeedRPM extends Command {

	private double setpoint;

	public SetShooterSpeedRPM(double setpoint, double time) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		this.setpoint = setpoint;
		setTimeout(time);
		requires(Robot.shooter);
	}

	public SetShooterSpeedRPM(double setpoint) {
		requires(Robot.shooter);
		this.setpoint = setpoint;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.shooter.changeShooterControlMode(TalonControlMode.Speed);
		Robot.shooter.setSpeedRPM(setpoint);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return isTimedOut() || Robot.oi.driver.getBButtonPressed();
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.shooter.setSpeedRPM(0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		Robot.shooter.setSpeedRPM(0);
	}
}
