package org.usfirst.frc.team226.robot.subsystems;

import static org.usfirst.frc.team226.robot.RobotMap.INTAKE_SERVO;
import static org.usfirst.frc.team226.robot.RobotMap.LEFT_HOPPER_SERVO;
import static org.usfirst.frc.team226.robot.RobotMap.RIGHT_HOPPER_SERVO;

import org.usfirst.frc.team226.robot.commands.cmdToggleHoppers;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class CruncherServos extends Subsystem {

	private Servo leftHopperServo = new Servo(LEFT_HOPPER_SERVO);
	private Servo rightHopperServo = new Servo(RIGHT_HOPPER_SERVO);
	private Servo intakeServo = new Servo(INTAKE_SERVO);
	
    public void initDefaultCommand() {
    	setDefaultCommand(new cmdToggleHoppers());
    }
    
    public void expandHopper() {
    	expandLeftHopperServo();
    	expandRightHopperServo();
    }
    
    public void contractHopper() {
    	contractLeftHopperServo();
    	contractRightHopperServo();
    }
    
    public void expandLeftHopperServo() {
    	leftHopperServo.set(1);
    }
    
    public void contractLeftHopperServo() {
    	leftHopperServo.set(0.15);
    }
    
    public void expandRightHopperServo() {
    	rightHopperServo.set(0);
    }
    
    public void contractRightHopperServo() {
    	rightHopperServo.set(1.0);
    }
    
    public void expandIntakeServo() {
    	intakeServo.set(0.7);
    }
    
    public void contractIntakeServo() {
    	intakeServo.set(0.32);
    }
}

