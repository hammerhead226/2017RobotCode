package org.usfirst.frc.team226.robot.commands;

import org.usfirst.frc.team226.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class cmdPIDTurnToAngle extends Command {

	private int angle;
	private double multiplier;

	 private boolean wasOnTarget = false;
	 private long startTime;
	 private int onTargetDuration = 100;

	public cmdPIDTurnToAngle(int angle, double multiplier) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.driveTrain);
		this.multiplier = multiplier;
		this.angle = angle;
//		this.onTargetDuration = secondsOnTarget * 1000;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		// Different PID control for different angles
		// if (Math.abs(angle) <= 15) {
//		 Robot.driveTrain.dirController.setPID(0.11, 0.02, 0.205); //ROBOT 1
		// Robot.driveTrain.dirController.setAbsoluteTolerance(0.05);
		// }
		// else {
		// Robot.driveTrain.dirController.setPID(0.075, 0.008, 0.1);
		// Robot.driveTrain.dirController.setAbsoluteTolerance(0.2);
		// }
		Robot.driveTrain.resetAllSensors();
		Robot.driveTrain.dirController.setSetpoint(angle);
		Robot.driveTrain.dirController.enable();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		double turn = Robot.driveTrain.dirController.get();

		Robot.driveTrain.tankDrive(turn * multiplier, -turn * multiplier);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if (Math.abs(Robot.driveTrain.dirController.getError()) < 1.5) {
			if (!wasOnTarget) {
				startTime = System.currentTimeMillis();
				wasOnTarget = true;
			}
			long timeOnTarget = System.currentTimeMillis() - startTime;
			if (timeOnTarget >= onTargetDuration) {
				wasOnTarget = false;
				return true;
			}
			
		} else wasOnTarget = false;
		
		if (Robot.oi.driver.getBACKButtonPressed())
			return true;
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.driveTrain.dirController.disable();
		Robot.driveTrain.dirController.reset();

	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		Robot.driveTrain.dirController.disable();
	}
}
