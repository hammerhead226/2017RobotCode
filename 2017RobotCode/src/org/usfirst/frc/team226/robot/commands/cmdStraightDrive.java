package org.usfirst.frc.team226.robot.commands;

import org.usfirst.frc.team226.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class cmdStraightDrive extends Command {

	private double driveSetpoint;
	private double angle;
	private double multiplier;
	private double turnMultiplier;

	public cmdStraightDrive(double driveSetpoint, double angle, double multiplier, double turnMultiplier) {
		// driveSetpoint is in inches
		requires(Robot.driveTrain);

		this.multiplier = multiplier;
		this.turnMultiplier = turnMultiplier;
		this.angle = angle;
		// Convert inches to encoder ticks (pulses?)
		this.driveSetpoint = driveSetpoint;
		double conversionFactor = 4096.0 / (Math.PI * 6);
		// 4096 ppr (1024 at 4x) per 6pi inches (wheel circumference)
		this.driveSetpoint = driveSetpoint * conversionFactor;
		SmartDashboard.putNumber("DT_DistSetpoint", this.driveSetpoint);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.driveTrain.resetAllSensors();
		Robot.driveTrain.distController.setSetpoint(driveSetpoint);
		Robot.driveTrain.distController.enable();
		Robot.driveTrain.dirController.setSetpoint(angle);
		Robot.driveTrain.dirController.enable();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		double throttle = Robot.driveTrain.distController.get();
		double turn = Robot.driveTrain.dirController.get();

		Robot.driveTrain.tankDrive(turn * turnMultiplier + throttle * multiplier,
				-turn * turnMultiplier + throttle * multiplier);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return Math.abs(Robot.driveTrain.dirController.getError()) < 1
				&& Math.abs(Robot.driveTrain.distController.getError()) < 25;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.driveTrain.distController.reset();
		Robot.driveTrain.dirController.reset();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		Robot.driveTrain.distController.reset();
		Robot.driveTrain.dirController.reset();
	}
}
