package org.usfirst.frc.team226.robot.commands;

import org.usfirst.frc.team226.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class cmdMoveRightFeeder_button extends Command {

    public cmdMoveRightFeeder_button() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.rightFeeder);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.rightFeeder.setFeederSpeed(0.5);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.rightFeeder.setFeederSpeed(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.rightFeeder.setFeederSpeed(0);
    }
}
