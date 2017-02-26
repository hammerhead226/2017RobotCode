package org.usfirst.frc.team226.robot.commands;

import org.usfirst.frc.team226.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class cmdMoveRightAgitator_button extends Command {

	private Timer timer = new Timer();

	private boolean reverse = false;

	public cmdMoveRightAgitator_button() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.rightFeeder);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		timer.start();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (!reverse && timer.get() >= 2) {
			timer.reset();
			reverse = !reverse;
		} else if (reverse && timer.get() >= 0.5) {
			timer.reset();
			reverse = !reverse;
		}

		double speed = reverse ? -0.8 : 0.8;
		Robot.rightFeeder.setAgitatorSpeed(speed);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.rightFeeder.setAgitatorSpeed(0);
		timer.stop();
		timer.reset();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		Robot.rightFeeder.setAgitatorSpeed(0);
		timer.stop();
		timer.reset();
	}
}
