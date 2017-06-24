package org.usfirst.frc.team226.robot.commands;

import org.usfirst.frc.team226.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class StableDrive extends Command {
	private double incrementAmount = 10;
	private double increment = 1 / incrementAmount;
	private double incrementRotate = increment;
	private double throttle = 0;
	private double rotate = 0;
	private boolean gyroUpdated = true;
	private double kp = .05;
	private double angle = 0;

	public StableDrive() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.driveTrain);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		double speed = Robot.oi.driver.getLeftJoystick_Y();
		double turn = Robot.oi.driver.getRightJoystick_X();

		if (throttle < speed) {
			throttle += increment;
		}

		if (speed < throttle) {
			throttle -= increment;
		}

		if (rotate < turn) {
			rotate += incrementRotate;
		}

		if (turn < rotate) {
			rotate -= incrementRotate;
		}

		if (turn == 0 && speed != 0) {
			if (Robot.driveTrain.wasTurn == true) {
				Robot.driveTrain.wasTurn = false;
				Robot.driveTrain.navX.reset();
			}
			angle = Robot.driveTrain.getGyroAngle();
			Robot.driveTrain.arcadeDrive(.75 * throttle, angle * kp);
		}

		else {

			Robot.driveTrain.wasTurn = true;
			Robot.driveTrain.arcadeDrive(throttle, rotate);

		}
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
