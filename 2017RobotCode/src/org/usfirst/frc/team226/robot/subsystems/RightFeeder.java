package org.usfirst.frc.team226.robot.subsystems;

import static org.usfirst.frc.team226.robot.RobotMap.R_FEEDER_MOTOR;

import org.usfirst.frc.team226.robot.commands.cmdMoveRightFeeder_test;
import org.usfirst.frc.team226.robot.extlib.MagEncoderVelocityMimic;
import org.usfirst.frc.team226.robot.extlib.PIDOutputMimic;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class RightFeeder extends Subsystem {
	public double multiplier = 0.7;

	private CANTalon motor = new CANTalon(R_FEEDER_MOTOR);

	private static double Kp = 0;
	private static double Ki = 0;
	private static double Kd = 0;
	private static double Kf = 0;

	private PIDOutputMimic velMimic = new PIDOutputMimic();
	private MagEncoderVelocityMimic sm = new MagEncoderVelocityMimic(motor, PIDSourceType.kRate);
	public PIDController velPID = new PIDController(Kp, Ki, Kd, Kf, sm, velMimic);


	public void initDefaultCommand() {
		setDefaultCommand(new cmdMoveRightFeeder_test());
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
		SmartDashboard.putNumber("RF_RPM", getFeederVelocity());
		SmartDashboard.putNumber("RF_PIDOutput", velPID.get());
		SmartDashboard.putNumber("RF_PIDSetpoint", velPID.getSetpoint());
		SmartDashboard.putBoolean("RF_PIDEnabled", velPID.isEnabled());
		SmartDashboard.putNumber("RF_Talon", motor.getBusVoltage());
	}
}
