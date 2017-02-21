package org.usfirst.frc.team226.robot.extlib;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

/**
 * Wraps 2 encoders into one PIDSource, for use in PIDControllers.
 * The encoders' values are averaged to produce the output.
 * <p>
 * 
 * @author Alec Minchington, Team 226
 * 
 * @version 1.2
 */

public class DoubleEncoder implements PIDSource{
	
	private CANTalon leftEncoder;
	private CANTalon rightEncoder;
	
	private boolean leftEncoderInverted = false;
	private boolean rightEncoderInverted = false;
	
	private PIDSourceType pidSource;
	
	public DoubleEncoder(CANTalon leftEncoder, CANTalon rightEncoder, PIDSourceType pidSource) {
		this.leftEncoder = leftEncoder;
		this.rightEncoder = rightEncoder;
		this.pidSource = pidSource;
//		setTalonControlModes();
	}
	
	public DoubleEncoder(CANTalon leftEncoder, CANTalon rightEncoder, boolean leftInverted, boolean rightInverted, PIDSourceType pidSource) {
		this.leftEncoder = leftEncoder;
		this.rightEncoder = rightEncoder;
		this.pidSource = pidSource;
		leftEncoder.reverseSensor(leftInverted);
		rightEncoder.reverseSensor(rightInverted);
//		setTalonControlModes();
	}
	
	@Override
	public void setPIDSourceType(PIDSourceType pidSource) {
		this.pidSource = pidSource;
	}

	@Override
	public PIDSourceType getPIDSourceType() {
		// TODO Auto-generated method stub
		return pidSource;
	}

	@Override
	public double pidGet() {
//		double leftEncoderValue;
//		double rightEncoderValue;
		
//		if (leftEncoderInverted) {
//			leftEncoderValue = -leftEncoder.getPosition();
//		}
//		else {
//			leftEncoderValue = leftEncoder.getPosition();
//		}
//		if (rightEncoderInverted) {
//			rightEncoderValue = -rightEncoder.getPosition();
//		}
//		else {
//			rightEncoderValue = rightEncoder.getPosition();
//		}
		
//		return (leftEncoderValue + rightEncoderValue) / 2.0;
		return (leftEncoder.getPosition() + rightEncoder.getPosition()) / 2.0;
	}

	public void reset() {
		leftEncoder.setPosition(0);
		rightEncoder.setPosition(0);
	}
	
	public void setLeftEncoderInverted(boolean isInverted) {
		leftEncoderInverted = isInverted;
	}
	
	public void setRightEncoderInverted(boolean isInverted) {
		rightEncoderInverted = isInverted;
	}
	
	public void setTalonControlModes() {
		if (this.pidSource == PIDSourceType.kDisplacement) {
			leftEncoder.changeControlMode(TalonControlMode.Position);
			rightEncoder.changeControlMode(TalonControlMode.Position);
		}
		if (this.pidSource == PIDSourceType.kRate) {
			leftEncoder.changeControlMode(TalonControlMode.Speed);
			rightEncoder.changeControlMode(TalonControlMode.Speed);
		}
	}
}
