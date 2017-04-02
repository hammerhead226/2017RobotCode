package org.usfirst.frc.team226.robot.subsystems;

import org.usfirst.frc.team226.robot.RobotMap;
import org.usfirst.frc.team226.robot.commands.cmdControlGearMech;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class GearMech extends Subsystem {

	private Servo leftServo = new Servo(RobotMap.LEFT_GEARMECH_SERVO);
	private Servo rightServo = new Servo(RobotMap.RIGHT_GEARMECH_SERVO);


    public void initDefaultCommand() {
    	setDefaultCommand(new cmdControlGearMech());
    }
    
    private void expandLeftServo() {
    	
    }
    
    private void contractLeftServo() {
    	
    }
    
    private void expandRightServo() {
    	
    }
    
    private void contractRightServo() {
    	
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

