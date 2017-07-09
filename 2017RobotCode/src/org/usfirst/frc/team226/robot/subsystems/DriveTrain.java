package org.usfirst.frc.team226.robot.subsystems;

import org.usfirst.frc.team226.robot.RobotMap;
import org.usfirst.frc.team226.robot.commands.ArcadeDrive;
import org.usfirst.frc.team226.robot.extlib.MotionProfileManager;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveTrain extends Subsystem {

	private CANTalon frontLeft = new CANTalon(RobotMap.DT_FL_MOTOR);
	private CANTalon rearLeft = new CANTalon(RobotMap.DT_RL_MOTOR);
	private CANTalon frontRight = new CANTalon(RobotMap.DT_FR_MOTOR);
	private CANTalon rearRight = new CANTalon(RobotMap.DT_RR_MOTOR);

	private MotionProfileManager leftManager = new MotionProfileManager(frontLeft);
	private MotionProfileManager rightManager = new MotionProfileManager(frontRight);
	
	public double getLeftCurrent() {
		return frontLeft.getOutputCurrent();
	}
	
	public double getRightCurrent() {
		return frontRight.getOutputCurrent();
	}

	public DriveTrain() {
		frontLeft.setCurrentLimit(33);
		frontRight.setCurrentLimit(33);
		frontLeft.EnableCurrentLimit(true);
		frontRight.EnableCurrentLimit(true);
		
		rearLeft.changeControlMode(TalonControlMode.Follower);
		rearLeft.set(frontLeft.getDeviceID());
		frontLeft.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		frontLeft.reverseSensor(true);
		
		frontLeft.setProfile(0);
		frontLeft.setP(0.7117);
		frontLeft.setI(0.002);
		frontLeft.setD(7.117);
		frontLeft.setF(0.3237);
		frontLeft.setIZone(65);

		rearRight.changeControlMode(TalonControlMode.Follower);
		rearRight.set(frontRight.getDeviceID());
		frontRight.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		frontRight.reverseOutput(true);
		
		frontRight.setProfile(0);
		frontRight.setP(1);
		frontRight.setI(0.002);
		frontRight.setD(10);
		frontRight.setF(0.3444);
		frontRight.setIZone(50);
	}

	public void set(double value) {
		frontLeft.set(value);
		frontRight.set(value);
	}
	
	public void beginProfile() {
		leftManager.startMotionProfile();
		rightManager.startMotionProfile();
	}
	
	public void manage(int profile) {
		leftManager.manage(profile);
		rightManager.manage(profile);
	}
	
	public void onInterrupt() {
		leftManager.onInterrupt();
		rightManager.onInterrupt();
	}

	public void changeProfiles(double[][] left, double[][] right) {
		leftManager.changeMotionProfile(left);
		rightManager.changeMotionProfile(right);
	}

	public void swapLeftRightProfiles() {
		leftManager.swapProfile(rightManager);
	}

	public boolean motionProfileFinished() {
		return leftManager.isFinished() && rightManager.isFinished();
	}

	public void resetPosition() {
		frontLeft.setPosition(0);
		frontRight.setPosition(0);
	}

	public boolean motionMagicOnTarget(double setpoint) {
		return Math.abs(Math.abs(frontLeft.getEncPosition()) - Math.abs(setpoint)) < 100
				&& Math.abs(Math.abs(frontRight.getEncPosition()) - Math.abs(setpoint)) < 100;
	}

	public void setMMCruiseVelocityAccel(double vel, double accel) {
		frontLeft.setMotionMagicCruiseVelocity(vel);
		frontLeft.setMotionMagicAcceleration(accel);
		frontRight.setMotionMagicCruiseVelocity(vel);
		frontRight.setMotionMagicAcceleration(accel);
	}

	public void initDefaultCommand() {
		setDefaultCommand(new ArcadeDrive());
	}

	public void arcadeDrive(double moveValue, double rotateValue, boolean squaredInputs) {
		double leftMotorSpeed;
		double rightMotorSpeed;

		rotateValue = limit(rotateValue);
		moveValue = limit(moveValue);

		if (squaredInputs) {
			// square the inputs (while preserving the sign) to increase fine
			// control
			// while permitting full power
			if (rotateValue >= 0.0) {
				rotateValue = rotateValue * rotateValue;
			} else {
				rotateValue = -(rotateValue * rotateValue);
			}
			if (moveValue >= 0.0) {
				moveValue = moveValue * moveValue;
			} else {
				moveValue = -(moveValue * moveValue);
			}
		}

		if (rotateValue > 0.0) {
			if (moveValue > 0.0) {
				leftMotorSpeed = rotateValue - moveValue;
				rightMotorSpeed = Math.max(rotateValue, moveValue);
			} else {
				leftMotorSpeed = Math.max(rotateValue, -moveValue);
				rightMotorSpeed = rotateValue + moveValue;
			}
		} else {
			if (moveValue > 0.0) {
				leftMotorSpeed = -Math.max(-rotateValue, moveValue);
				rightMotorSpeed = rotateValue + moveValue;
			} else {
				leftMotorSpeed = rotateValue - moveValue;
				rightMotorSpeed = -Math.max(-rotateValue, -moveValue);
			}
		}

		frontLeft.set(leftMotorSpeed);
		frontRight.set(rightMotorSpeed);
	}

	protected static double limit(double num) {
		if (num > 1.0) {
			return 1.0;
		}
		if (num < -1.0) {
			return -1.0;
		}
		return num;
	}

	public void changeControlMode(TalonControlMode mode) {
		frontLeft.changeControlMode(mode);
		frontRight.changeControlMode(mode);
	}

	public void invertTalons() {
		frontLeft.reverseSensor(false);
		frontLeft.reverseOutput(true);
		frontRight.reverseOutput(false);
		frontRight.reverseSensor(true);
	}

	public void un_invertTalons() {
		frontLeft.reverseSensor(true);
		frontLeft.reverseOutput(false);
		frontRight.reverseOutput(true);
		frontRight.reverseSensor(false);
	}
}

