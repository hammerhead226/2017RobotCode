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
	private boolean wait = false;
	private int state = 1;
	private double forwardTime;
	private double backTime;
	private double waitTime;
	private double speed;

	public cmdMoveRightAgitator_button(double forwardTime, double backTime, double waitTime, double speed) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		this.forwardTime = forwardTime;
		this.backTime = backTime;
		this.waitTime = waitTime;
		this.speed = speed;
		requires(Robot.rightAgitator);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		timer.start();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
//		if (!reverse && timer.get() >= forwardTime) {
//			timer.reset();
//			reverse = !reverse;
//		} else if (reverse && timer.get() >= backTime) {
//			timer.reset();
//			reverse = !reverse;
//		}
		switch (state) {
		case 1: 
			if (timer.get() >= forwardTime) {
				timer.reset();
				wait = true;
				state = 2;
			}
			break;
		case 2:
			if (timer.get() >= waitTime) {
				timer.reset();
				wait = false;
				reverse = true;
				state = 3;
			}
			break;
		case 3:
			if (timer.get() >= backTime) {
				timer.reset();
				reverse = false;
				state = 1;
			}
			break;
		}
		
		double output = speed;
		if (reverse) {
			output *= -1;
		}
		if (wait) {
			output = 0;
		}
//		double gradientOutput = ((forwardTime - timer.get()) / forwardTime) * output;
		Robot.rightAgitator.setAgitatorSpeed(output);
	}


	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.rightAgitator.setAgitatorSpeed(0);
		timer.stop();
		timer.reset();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		Robot.rightAgitator.setAgitatorSpeed(0);
		timer.stop();
		timer.reset();
	}
}
