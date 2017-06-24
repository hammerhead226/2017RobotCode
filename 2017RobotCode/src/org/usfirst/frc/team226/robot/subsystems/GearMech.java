package org.usfirst.frc.team226.robot.subsystems;

import org.usfirst.frc.team226.robot.RobotMap;
import org.usfirst.frc.team226.robot.commands.ControlGearMech;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class GearMech extends Subsystem {

	private Servo leftServo = new Servo(RobotMap.LEFT_GEARMECH_SERVO);
	private Servo rightServo = new Servo(RobotMap.RIGHT_GEARMECH_SERVO);


    public void initDefaultCommand() {
    	setDefaultCommand(new ControlGearMech());
    }
    
    private void expandLeftServo() {
    	leftServo.set(0.6);
    }
    
    private void contractLeftServo() {
    	leftServo.set(0.06);
    }
    
    private void expandRightServo() {
    	rightServo.set(0.3);
    }
    
    private void contractRightServo() {
    	rightServo.set(0.84);
    }
    
    public void expandGearMech() {
    	expandLeftServo();
    	expandRightServo();
    }
    
    public void contractGearMech() {
    	contractLeftServo();
    	contractRightServo();
    }
}

