package org.usfirst.frc.team226.robot.subsystems;

import org.usfirst.frc.team226.robot.RobotMap;
import org.usfirst.frc.team226.robot.commands.DriveFeeder;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Feeder extends Subsystem {

	private SpeedController feeder = new TalonSRX(RobotMap.FEEDER_MOTOR);
//	private CANTalon feeder = new CANTalon(RobotMap.FEEDER_MOTOR);
	
	public Feeder() {
		feeder.setInverted(true);
	}
	
    public void initDefaultCommand() {
    }
    
    public void set(double value) {
    	feeder.set(value);
    }
}

