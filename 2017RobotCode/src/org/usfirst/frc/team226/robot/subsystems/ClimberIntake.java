package org.usfirst.frc.team226.robot.subsystems;

import org.usfirst.frc.team226.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ClimberIntake extends Subsystem {

	CANTalon one = new CANTalon(RobotMap.CLIMBERINTAKE_LEFT_MOTOR);
	CANTalon two = new CANTalon(RobotMap.CLIMBERINTAKE_RIGHT_MOTOR);
	
    public void initDefaultCommand() {
        //setDefaultCommand(new MySpecialCommand());
    }
    
    
}

