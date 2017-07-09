package org.usfirst.frc.team226.robot.commands;

import org.usfirst.frc.team226.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ExecuteProfile extends Command {

	private double[][] lp;
	private double[][] rp;
	private boolean reverse;
	private boolean instantFinish;

	public ExecuteProfile(double[][] leftProfile, double[][] rightProfile, boolean reverse, boolean instantFinish) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		lp = leftProfile;
		rp = rightProfile;
		this.reverse = reverse;
		this.instantFinish = instantFinish;
		requires(Robot.driveTrain);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.driveTrain.changeProfiles(lp, rp);
		Robot.driveTrain.resetPosition();

		if (reverse) {
			Robot.driveTrain.invertTalons();
			Robot.driveTrain.swapLeftRightProfiles();
		}

		Robot.driveTrain.beginProfile();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.driveTrain.manage(0);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if (instantFinish) {
			return true;
		} else {
			return Robot.driveTrain.motionProfileFinished();
		}
	}

	// Called once after isFinished returns true
	protected void end() {
		if (reverse) {
			Robot.driveTrain.un_invertTalons();
			Robot.driveTrain.swapLeftRightProfiles();
		}
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		Robot.driveTrain.onInterrupt();

		if (reverse) {
			Robot.driveTrain.un_invertTalons();
			Robot.driveTrain.swapLeftRightProfiles();
		}
	}
}
