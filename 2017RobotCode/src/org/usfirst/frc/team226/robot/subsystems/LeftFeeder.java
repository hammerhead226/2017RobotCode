package org.usfirst.frc.team226.robot.subsystems;

import static org.usfirst.frc.team226.robot.RobotMap.L_FEEDER_MOTOR;

import org.usfirst.frc.team226.robot.commands.cmdMoveLeftFeeder_test;
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
public class LeftFeeder extends Subsystem {

	public double multiplier = 0.7;

	private CANTalon motor = new CANTalon(L_FEEDER_MOTOR);

	private static double Kp = 0;
	private static double Ki = 0;
	private static double Kd = 0;
	private static double Kf = 0;

	private PIDOutputMimic velMimic = new PIDOutputMimic();
	private MagEncoderVelocityMimic sm = new MagEncoderVelocityMimic(motor, PIDSourceType.kRate);
	public PIDController velPID = new PIDController(Kp, Ki, Kd, Kf, sm, velMimic);

	public LeftFeeder() {
		motor.setInverted(true);
	}

	public void initDefaultCommand() {
		setDefaultCommand(new cmdMoveLeftFeeder_test());
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
		SmartDashboard.putNumber("LF_RPM", getFeederVelocity());
		SmartDashboard.putNumber("LF_PIDOutput", velPID.get());
		SmartDashboard.putNumber("LF_PIDSetpoint", velPID.getSetpoint());
		SmartDashboard.putBoolean("LF_PIDEnabled", velPID.isEnabled());
		SmartDashboard.putNumber("LF_Talon", motor.getBusVoltage());
	}
}
