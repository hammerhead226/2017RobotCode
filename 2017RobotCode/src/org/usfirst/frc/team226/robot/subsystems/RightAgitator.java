package org.usfirst.frc.team226.robot.subsystems;

import static org.usfirst.frc.team226.robot.RobotMap.R_AGITATOR_MOTOR;

import org.usfirst.frc.team226.robot.commands.cmdMoveRightAgitator_manual;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class RightAgitator extends Subsystem {
	
	private CANTalon agitatorMotor = new CANTalon(R_AGITATOR_MOTOR);

    public void initDefaultCommand() {
    	setDefaultCommand(new cmdMoveRightAgitator_manual());
    }
    
    public void setAgitatorSpeed(double speed) {
		agitatorMotor.set(speed);
	}
    
    public void log() {
		SmartDashboard.putNumber("RAg_Talon", agitatorMotor.getOutputVoltage());
    }
}

