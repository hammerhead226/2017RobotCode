package org.usfirst.frc.team226.robot.commands;

import org.usfirst.frc.team226.robot.Constants;
import org.usfirst.frc.team226.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveActiveFloor extends Command {

	private double time = 0;
	private double delay = 0;

	private Timer timer = new Timer();

	public DriveActiveFloor() {
		requires(Robot.activeFloor);
	}

	public DriveActiveFloor(double time) {
		this.time = time;
		requires(Robot.activeFloor);
	}

	public DriveActiveFloor(double delay, double time) {
		this.time = time;
		this.delay = delay;
		requires(Robot.activeFloor);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		setTimeout(time + delay);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (timer.get() >= delay) {
			Robot.activeFloor.setMotor(Constants.ACTIVEFLOOR_SPEED);
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return time == 0 ? false : isTimedOut();
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.activeFloor.setMotor(0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		Robot.activeFloor.setMotor(0);
	}
}
