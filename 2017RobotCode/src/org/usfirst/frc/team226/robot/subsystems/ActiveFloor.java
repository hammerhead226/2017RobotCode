package org.usfirst.frc.team226.robot.subsystems;

import org.usfirst.frc.team226.robot.RobotMap;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ActiveFloor extends Subsystem {

	private SpeedController activeFloorMotor = new TalonSRX(RobotMap.ACTIVEFLOOR_MOTOR);
	
    public void initDefaultCommand() {
    }
    
    public void setMotor(double val) {
    	activeFloorMotor.set(val);
    }
}

