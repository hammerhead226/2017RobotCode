package org.usfirst.frc.team226.robot.commands;

import org.usfirst.frc.team226.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class cmdPIDDriveInches extends Command {

	private double driveSetpoint;
	
	private boolean wasOnTarget = false;
	private long startTime;
	private int onTargetDuration;
	
	public cmdPIDDriveInches(double driveSetpoint, int secondsOnTarget) {
		// driveSetpoint is in inches
		requires(Robot.driveTrain);
		// Convert inches to encoder ticks (pulses?)
		double conversionFactor = 4096.0 / (Math.PI * 8); 
		// 4096 ppr (1024 at 4x) per 8pi inches (wheel circumference)
		this.driveSetpoint = driveSetpoint * conversionFactor;
		this.onTargetDuration = secondsOnTarget * 1000;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.driveTrain.dirController.setSetpoint(driveSetpoint);
		Robot.driveTrain.dirController.enable();
		Robot.driveTrain.dirController.setSetpoint(0);
		Robot.driveTrain.dirController.enable();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		double throttle = Robot.driveTrain.dirController.get();
		double turn = Robot.driveTrain.dirController.get();

		Robot.driveTrain.arcadeDrive(throttle, -turn);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
//		return Robot.oi.driver.getBACKButtonPressed();
		// && Math.abs(Robot.driveTrain.distController.getError()) < 10;
		if (Math.abs(Robot.driveTrain.dirController.getError()) < 0.1 && Math.abs(Robot.driveTrain.dirController.getError()) < 0.1) {
			if (!wasOnTarget) {
				startTime = System.currentTimeMillis();
				wasOnTarget = true;
			}
			long timeOnTarget = System.currentTimeMillis() - startTime;
			if (timeOnTarget >= onTargetDuration) {
				wasOnTarget = false;
				return true;
			}
		}
		wasOnTarget = false;
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		// Robot.driveTrain.dirController.disable();
		// Robot.driveTrain.distController.disable();
		Robot.driveTrain.resetAllSensors();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
