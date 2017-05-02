package org.usfirst.frc.team226.robot.commands;

import org.usfirst.frc.team226.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class cmdCrunchHoppers extends Command {
	
	private Timer timer = new Timer();
	private boolean out = false;
	private boolean firstCrunch = true;

    public cmdCrunchHoppers() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.cruncherServos);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	firstCrunch = true;
    	timer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (firstCrunch) {
    		Robot.cruncherServos.expandHopper();
    		firstCrunch = false;
    		out = !out;
    	}
    	if (timer.get() >= 0.7) {
    		if (out) {
    			Robot.cruncherServos.contractHopper();
    		}
    		else {
    			Robot.cruncherServos.expandHopper();
    		}
    		out = !out;
    		timer.reset();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	timer.stop();
    	timer.reset();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	timer.stop();
    	timer.reset();
    }
}
