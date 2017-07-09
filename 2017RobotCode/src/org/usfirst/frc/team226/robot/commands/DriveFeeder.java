package org.usfirst.frc.team226.robot.commands;

import org.usfirst.frc.team226.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveFeeder extends Command {
	
	private Timer timer = new Timer();
	private Timer stateTimer = new Timer();
	private double time;
	private double delay;
	private int state = 0;

    public DriveFeeder(double time, double delay) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.feeder);
    	this.time = time;
    	this.delay = delay;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	timer.start();
    	stateTimer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (timer.get() >= delay) {
    		switch (state) {
			case 0:
				if (stateTimer.get() >= 2.5) {
					state = 1;
					stateTimer.reset();
				}
				Robot.feeder.set(1);
				break;
			case 1:
				if (stateTimer.get() >= 0.2) {
					state = 2;
					stateTimer.reset();
				}
				Robot.feeder.set(0);
				break;
			case 2:
				if (stateTimer.get() >= 0.7) {
					state = 3;
					stateTimer.reset();
				}
				Robot.feeder.set(-1);
				break;
			case 3:
				if (stateTimer.get() >= 0.2) {
					state = 0;
					stateTimer.reset();
				}
				Robot.feeder.set(0);
				break;
			default:
				break;
			}
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return timer.get() >= time;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.feeder.set(0);
    			
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.feeder.set(0);
    }
}
