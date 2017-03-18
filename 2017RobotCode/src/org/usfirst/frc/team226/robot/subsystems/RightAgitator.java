package org.usfirst.frc.team226.robot.subsystems;

import org.usfirst.frc.team226.robot.RobotMap;
import org.usfirst.frc.team226.robot.commands.cmdMoveRightAgitator_manual;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class RightAgitator extends Subsystem {
	
	private SpeedController agitatorMotor = new TalonSRX(RobotMap.R_AGITATOR_MOTOR);
	
//	private CANTalon agitatorMotor = new CANTalon(RobotMap.R_AGITATOR_MOTOR);
	
	public RightAgitator() {
		agitatorMotor.setInverted(true);
	}
	
    public void initDefaultCommand() {
    	setDefaultCommand(new cmdMoveRightAgitator_manual());
    }
    
    public void setAgitatorSpeed(double speed) {
		agitatorMotor.set(speed);
	}
    
    public void log() {
//		SmartDashboard.putNumber("RAg_Talon", agitatorMotor.getOutputVoltage());
    }
}

