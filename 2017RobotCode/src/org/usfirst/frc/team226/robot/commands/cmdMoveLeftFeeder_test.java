package org.usfirst.frc.team226.robot.commands;

import org.usfirst.frc.team226.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class cmdMoveLeftFeeder_test extends Command {

    public cmdMoveLeftFeeder_test() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.leftFeeder);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (Robot.oi.driver.getDPad() == 180) {
			Robot.leftFeeder.multiplier -= 0.01;
			Timer.delay(0.1);
		}
		if (Robot.oi.driver.getDPad() == 0) {
			Robot.leftFeeder.multiplier += 0.01;
			Timer.delay(0.1);
		}
		if (Robot.oi.driver.getRBButtonPressed()) {
			Robot.leftFeeder.setFeederSpeed(Robot.oi.driver.getRightJoystick_Y() * Robot.leftFeeder.multiplier);
			SmartDashboard.putNumber("Right Joystick", Robot.oi.driver.getRightJoystick_Y() * Robot.leftFeeder.multiplier);
		} else {
			Robot.leftFeeder.setFeederSpeed(Robot.oi.driver.getRightJoystick_Y());
			SmartDashboard.putNumber("Right Joystick", Robot.oi.driver.getRightJoystick_Y());
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
