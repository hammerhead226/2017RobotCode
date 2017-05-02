package org.usfirst.frc.team226.robot.subsystems;

import static org.usfirst.frc.team226.robot.RobotMap.R_AGITATOR_MOTOR;
import static org.usfirst.frc.team226.robot.RobotMap.R_FEEDER_MOTOR;

import org.usfirst.frc.team226.robot.Robot;
import org.usfirst.frc.team226.robot.commands.cmdMoveRightFeeder_manual;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class RightFeeder extends Subsystem {
	
	public double multiplier = 0.7;

	private CANTalon motor = new CANTalon(R_FEEDER_MOTOR);
	private CANTalon agitatorMotor = new CANTalon(R_AGITATOR_MOTOR);

	public void initDefaultCommand() {
		setDefaultCommand(new cmdMoveRightFeeder_manual());
	}

	// Getters

	public int getFeederVelocity() {
		return motor.getEncVelocity();
	}

	// Setters

	public void setFeederSpeed(double speed) {
		motor.set(speed);
	}

	public void setAgitatorSpeed(double speed) {
		agitatorMotor.set(speed);
	}

	public void log() {
//		SmartDashboard.putNumber("RF_RPM", getFeederVelocity());
		SmartDashboard.putNumber("RF_Talon", motor.getOutputVoltage());
	}
	
//	public void sharkLog() {
//		Robot.getSharkLogTable().putNumber("RF_RPM", getFeederVelocity());
//		Robot.getSharkLogTable().putNumber("RF_TalonVoltage", motor.getOutputVoltage());
//		Robot.getSharkLogTable().putNumber("RF_TalonCurrent", motor.getOutputCurrent());
//	}
}
