package org.usfirst.frc.team226.robot.commands;

import org.usfirst.frc.team226.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class cmdPIDDriveInches extends Command {

	private double driveSetpoint;
//	
//	private boolean wasOnTarget = false;
//	private long startTime;
//	private int onTargetDuration;
//	
	public cmdPIDDriveInches(double driveSetpoint) {
		// driveSetpoint is in inches
		requires(Robot.driveTrain);
		// Convert inches to encoder ticks (pulses?)
		this.driveSetpoint = driveSetpoint;
		double conversionFactor = 4096.0 / (Math.PI * 6); 
		// 4096 ppr (1024 at 4x) per 8pi inches (wheel circumference)
		this.driveSetpoint = driveSetpoint * conversionFactor;
		SmartDashboard.putNumber("DT_DistSetpoint", this.driveSetpoint);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.driveTrain.resetAllSensors();
		Robot.driveTrain.distController.setSetpoint(driveSetpoint);
		Robot.driveTrain.distController.enable();
		Robot.driveTrain.dirController.setSetpoint(0);
		Robot.driveTrain.dirController.enable();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		double throttle = Robot.driveTrain.distController.get();
		double turn = Robot.driveTrain.dirController.get();

		Robot.driveTrain.arcadeDrive(throttle*0.7, turn);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return Robot.oi.driver.getBButtonPressed() || Math.abs(Robot.driveTrain.distController.getError()) < 25;
		/*if (Math.abs(Robot.driveTrain.dirController.getError()) < 0.1 && Math.abs(Robot.driveTrain.dirController.getError()) < 0.1) {
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
		return false;*/
	}

	// Called once after isFinished returns true
	protected void end() {
//		Robot.driveTrain.distController.reset();
//		Robot.driveTrain.resetAllSensors();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
//		Robot.driveTrain.distController.reset();
	}
}
