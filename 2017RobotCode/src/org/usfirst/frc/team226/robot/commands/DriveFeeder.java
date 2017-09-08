package org.usfirst.frc.team226.robot.commands;

import org.usfirst.frc.team226.robot.Constants;
import org.usfirst.frc.team226.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveFeeder extends Command {

	private double time = 0;
	private double delay = 0;

	private Timer timer = new Timer();

	public DriveFeeder() {
		requires(Robot.feeder);
	}

	public DriveFeeder(double time) {
		this.time = time;
		requires(Robot.feeder);
	}

	public DriveFeeder(double delay, double time) {
		this.time = time;
		this.delay = delay;
		requires(Robot.feeder);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		setTimeout(time + delay);
		timer.start();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (timer.get() >= delay) {
			Robot.feeder.setMotor(Constants.FEEDER_SPEED);
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return time == 0 ? false : isTimedOut();
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.feeder.setMotor(0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		Robot.feeder.setMotor(0);
	}
}
