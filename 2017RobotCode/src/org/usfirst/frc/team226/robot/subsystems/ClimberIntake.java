package org.usfirst.frc.team226.robot.subsystems;

import static org.usfirst.frc.team226.robot.RobotMap.CLIMBERINTAKE_LEFT_MOTOR;
import static org.usfirst.frc.team226.robot.RobotMap.CLIMBERINTAKE_RIGHT_MOTOR;

import org.usfirst.frc.team226.robot.Robot;
import org.usfirst.frc.team226.robot.commands.MoveClimberIntake;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ClimberIntake extends Subsystem {

	public double multiplier = 0.7;

	CANTalon leftMotor = new CANTalon(CLIMBERINTAKE_LEFT_MOTOR);
	CANTalon rightMotor = new CANTalon(CLIMBERINTAKE_RIGHT_MOTOR);

	public ClimberIntake() {
		rightMotor.setInverted(true);
	}

	public void initDefaultCommand() {
		setDefaultCommand(new MoveClimberIntake());
	}

	public void setMotors(double throttle) {
		leftMotor.set(throttle);
		rightMotor.set(throttle);
	}

	public void log() {
		SmartDashboard.putNumber("CI_LTalon", leftMotor.getOutputVoltage());
		SmartDashboard.putNumber("CI_RTalon", rightMotor.getOutputVoltage());
	}
	
//	public void sharkLog() {
//		Robot.getSharkLogTable().putNumber("CI_LTalonVoltage", leftMotor.getOutputVoltage());
//		Robot.getSharkLogTable().putNumber("CI_LTalonCurrent", leftMotor.getOutputCurrent());
//		Robot.getSharkLogTable().putNumber("CI_RTalonVoltage", rightMotor.getOutputVoltage());
//		Robot.getSharkLogTable().putNumber("CI_RTalonCurrent", rightMotor.getOutputCurrent());
//	}
}
