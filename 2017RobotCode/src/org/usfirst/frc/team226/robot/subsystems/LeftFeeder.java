package org.usfirst.frc.team226.robot.subsystems;

import org.usfirst.frc.team226.robot.RobotMap;
import org.usfirst.frc.team226.robot.commands.cmdMoveLeftFeeder_test;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class LeftFeeder extends Subsystem {
	
	public double multiplier = 0.7;

	private CANTalon motor = new CANTalon(RobotMap.L_FEEDER_MOTOR);
	
	public LeftFeeder() {
		motor.setInverted(true);
	}
	
    public void initDefaultCommand() {
    	setDefaultCommand(new cmdMoveLeftFeeder_test());
    }
    
    public void setFeederSpeed(double speed) {
    	motor.set(speed);
    }
    
    public void log() {
    }
}

