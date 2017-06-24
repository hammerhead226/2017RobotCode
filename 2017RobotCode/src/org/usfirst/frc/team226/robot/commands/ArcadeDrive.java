package org.usfirst.frc.team226.robot.commands;

import org.usfirst.frc.team226.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ArcadeDrive extends Command {
	
	private boolean halfSpeed = false;
	private boolean backwards = false;

    public ArcadeDrive() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
//    	if (Robot.oi.driver.getLSButtonPressed()) {
//    		halfSpeed = !halfSpeed;
//    		Timer.delay(0.25);
//    	}
//    	if (Robot.oi.driver.getRSButtonPressed()) {
//    		backwards = !backwards;
//    		Timer.delay(0.25);
//    	}
    	double throttle = Robot.oi.driver.getLeftJoystick_Y();
    	double turn = Robot.oi.driver.getRightJoystick_X();
    	
    	if (Robot.oi.driver.getRightTrigger() > 0.5) {
    		throttle *= 0.55;
    		turn *= 0.55;
    	}
//    	if (Robot.oi.driver.getLeftTrigger() > 0.5) {
//    		throttle *= -1;
//    	}
    	
    	Robot.driveTrain.arcadeDrive(throttle, turn);
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
