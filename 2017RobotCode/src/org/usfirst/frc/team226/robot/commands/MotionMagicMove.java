package org.usfirst.frc.team226.robot.commands;

import org.usfirst.frc.team226.robot.Constants;
import org.usfirst.frc.team226.robot.Robot;
import org.usfirst.frc.team226.robot.motionprofiling.RobotSpeed;

import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Command;

public class MotionMagicMove extends Command {
	
	private double setpoint;
	private double setpointNative;
	private boolean instantFinish;
	private RobotSpeed speed;

    public MotionMagicMove(RobotSpeed speed, double inches, boolean reverse, boolean instantFinish) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.setpoint = inches / Constants.WHEEL_CIRCUMFERENCE;
    	if (reverse) {
    		this.setpoint *= -1;
    	}
    	this.setpointNative = this.setpoint * 4096.0;
    	this.instantFinish = instantFinish;
    	this.speed = speed;
    	requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveTrain.resetPosition();
    	Robot.driveTrain.changeControlMode(TalonControlMode.MotionMagic);
    	Robot.driveTrain.setMMCruiseVelocityAccel(speed.getMMCruiseVel(), speed.getMMCruiseAccel());
    	Robot.driveTrain.set(setpoint);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (!instantFinish) {
    		return Robot.driveTrain.motionMagicOnTarget(setpointNative);
    	}
    	else {
    		return true;
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
