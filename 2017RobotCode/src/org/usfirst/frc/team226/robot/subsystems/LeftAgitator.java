package org.usfirst.frc.team226.robot.subsystems;

import org.usfirst.frc.team226.robot.Robot;
import org.usfirst.frc.team226.robot.RobotMap;
import org.usfirst.frc.team226.robot.commands.cmdMoveLeftAgitator_manual;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class LeftAgitator extends Subsystem {
	
	private SpeedController agitatorMotor = new TalonSRX(RobotMap.L_AGITATOR_MOTOR);
	
//	private CANTalon agitatorMotor = new CANTalon(RobotMap.R_AGITATOR_MOTOR);
	
	public LeftAgitator() {
//		agitatorMotor.setInverted(true);
	}

    public void initDefaultCommand() {
    	setDefaultCommand(new cmdMoveLeftAgitator_manual());
    }
    
    public void setAgitatorSpeed(double speed) {
		agitatorMotor.set(speed);
	}
    
    public void log() {
//		SmartDashboard.putNumber("LAg_Talon", agitatorMotor.getOutputVoltage());
    }
    
//    public void sharkLog() {
//		Robot.getSharkLogTable().putNumber("LAg_Talon_get", agitatorMotor.get());
//    }
}

