package org.usfirst.frc.team226.robot.subsystems;

import org.usfirst.frc.team226.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Feeder extends Subsystem {

	private CANTalon feederMotor = new CANTalon(RobotMap.FEEDER_MOTOR);
	
    public void initDefaultCommand() {
    }
    
    public void setMotor(double val) {
    	feederMotor.set(val);
    }
}

