package org.usfirst.frc.team226.robot.subsystems;

import static org.usfirst.frc.team226.robot.RobotMap.CLIMBERINTAKE_LEFT_MOTOR;
import static org.usfirst.frc.team226.robot.RobotMap.CLIMBERINTAKE_RIGHT_MOTOR;

import org.usfirst.frc.team226.robot.RobotMap;
import org.usfirst.frc.team226.robot.commands.ControlClimberIntake;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ClimberIntake extends Subsystem {

	private CANTalon leftMotor = new CANTalon(CLIMBERINTAKE_LEFT_MOTOR);
	private CANTalon rightMotor = new CANTalon(CLIMBERINTAKE_RIGHT_MOTOR);
	
	private Servo intakeServo = new Servo(RobotMap.INTAKE_SERVO);

	public ClimberIntake() {
		rightMotor.setInverted(true);
	}

	public void initDefaultCommand() {
		setDefaultCommand(new ControlClimberIntake());
	}

	public void setMotors(double throttle) {
		leftMotor.set(throttle);
		rightMotor.set(throttle);
	}
	
	private void releaseServo() {
		intakeServo.set(0.75);
	}
	
	private void resetServo() {
		intakeServo.set(0.3);
	}
	
	private boolean released = false;
	public void toggleIntakeServo() {
		if (released) {
			resetServo();
		}
		else {
			releaseServo();
		}
		released = !released;
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
