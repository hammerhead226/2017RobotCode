package org.usfirst.frc.team226.robot.subsystems;

import static org.usfirst.frc.team226.robot.RobotMap.L_AGITATOR_MOTOR;

import org.usfirst.frc.team226.robot.commands.cmdMoveLeftAgitator_manual;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class LeftAgitator extends Subsystem {
	
	private CANTalon agitatorMotor = new CANTalon(L_AGITATOR_MOTOR);

    public void initDefaultCommand() {
//    	setDefaultCommand(new cmdMoveLeftAgitator_manual());
    }
    
    public void setAgitatorSpeed(double speed) {
		agitatorMotor.set(speed);
	}
    
    public void log() {
		SmartDashboard.putNumber("LAg_Talon", agitatorMotor.getOutputVoltage());
    }
}

