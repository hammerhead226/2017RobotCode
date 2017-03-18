package org.usfirst.frc.team226.robot.commands;

import org.usfirst.frc.team226.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class cmdMoveLeftAgitator_button extends Command {

	private Timer timer = new Timer();

	private boolean reverse = false;
	private boolean wait = true;
	private int state = 0;
	private double forwardTime;
	private double backTime;
	private double waitTime;
	private double speed;

	public cmdMoveLeftAgitator_button(double forwardTime, double backTime, double waitTime, double speed) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		this.forwardTime = forwardTime;
		this.backTime = backTime;
		this.waitTime = waitTime;
		this.speed = speed;
		requires(Robot.leftAgitator);
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
		
		case 0: 
			if (timer.get() >= waitTime) {
				timer.reset();
				wait = false;
				state = 1;
			}
			break;
			
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
				state = 0;
			}
			break;
		}
		
		double output = speed;
		if (reverse) {
			output = 1;
			output *= -1;
		}
		if (wait) {
			output = 0;
		}
//		double gradientOutput = ((forwardTime - timer.get()) / forwardTime) * output;
		Robot.leftAgitator.setAgitatorSpeed(output);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.leftAgitator.setAgitatorSpeed(0);
		timer.stop();
		timer.reset();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		Robot.leftAgitator.setAgitatorSpeed(0);
		timer.stop();
		timer.reset();
	}
}
