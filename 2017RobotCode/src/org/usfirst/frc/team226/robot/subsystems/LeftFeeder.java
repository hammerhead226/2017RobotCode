package org.usfirst.frc.team226.robot.subsystems;

import static org.usfirst.frc.team226.robot.RobotMap.L_FEEDER_MOTOR;

import org.usfirst.frc.team226.robot.commands.cmdMoveLeftFeeder_manual;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class LeftFeeder extends Subsystem {

	public double multiplier = 0.7;

	private CANTalon motor = new CANTalon(L_FEEDER_MOTOR);

	public LeftFeeder() {
		motor.setInverted(true);
	}

	public void initDefaultCommand() {
		setDefaultCommand(new cmdMoveLeftFeeder_manual());

	}

	// Getters

	public int getFeederVelocity() {
		return motor.getEncVelocity();
	}

	// Setters

	public void setFeederSpeed(double speed) {
		motor.set(speed);
	}

	// Utility

	public void log() {
//		SmartDashboard.putNumber("LF_RPM", getFeederVelocity());
		SmartDashboard.putNumber("LF_Talon", motor.getOutputVoltage());
	}
}
