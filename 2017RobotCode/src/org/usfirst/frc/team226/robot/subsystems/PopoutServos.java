package org.usfirst.frc.team226.robot.subsystems;

import static org.usfirst.frc.team226.robot.RobotMap.INTAKE_SERVO;
import static org.usfirst.frc.team226.robot.RobotMap.LEFT_HOPPER_SERVO;
import static org.usfirst.frc.team226.robot.RobotMap.RIGHT_HOPPER_SERVO;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class PopoutServos extends Subsystem {

	private Servo leftHopperServo = new Servo(LEFT_HOPPER_SERVO);
	private Servo rightHopperServo = new Servo(RIGHT_HOPPER_SERVO);
	private Servo intakeServo = new Servo(INTAKE_SERVO);
	
	public PopoutServos() {
		leftHopperServo.set(0.75);
	}
	
    public void initDefaultCommand() {
    }
    
    public void expandRobot() {
    	releaseLeftHopperServo();
    	releaseRightHopperServo();
    	releaseIntakeServo();
    }
    
    public void resetRobot() {
    	resetLeftHopperServo();
    	resetRightHopperServo();
    	resetIntakeServo();
    }
    
    public void releaseLeftHopperServo() {
    	leftHopperServo.set(0);
    }
    
    public void resetLeftHopperServo() {
    	leftHopperServo.set(0.75);
    }
    
    public void releaseRightHopperServo() {
    	rightHopperServo.set(0.75);
    }
    
    public void resetRightHopperServo() {
    	rightHopperServo.set(0);
    }
    
    public void releaseIntakeServo() {
    	intakeServo.set(0.7);
    }
    
    public void resetIntakeServo() {
    	intakeServo.set(0.32);
    }
}

