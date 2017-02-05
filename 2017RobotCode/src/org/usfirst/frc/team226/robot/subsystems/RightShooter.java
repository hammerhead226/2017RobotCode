package org.usfirst.frc.team226.robot.subsystems;

import org.usfirst.frc.team226.robot.RobotMap;
import org.usfirst.frc.team226.robot.commands.cmdMoveLeftShooter_test;
import org.usfirst.frc.team226.robot.commands.cmdMoveRightShooter_test;
import org.usfirst.frc.team226.robot.extlib.MagEncoderMimic;
import org.usfirst.frc.team226.robot.extlib.PIDOutputMimic;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class RightShooter extends Subsystem {
	public double multiplier = 0.65;

	// PID logging
	public double errorLog;
	public double pidOutputLog;

	private CANTalon leftMotor = new CANTalon(RobotMap.R_SHOOTER_L_MOTOR);
	private CANTalon rightMotor = new CANTalon(RobotMap.R_SHOOTER_R_MOTOR);

	private static double Kp = 0;
	private static double Ki = 0;
	private static double Kd = 0;
	private static double Kf = 0;
	// 3550 = 0.00004126
	// 4550 = 0.00003219

	private PIDOutputMimic velMimic = new PIDOutputMimic();
	public MagEncoderMimic sm = new MagEncoderMimic(leftMotor, PIDSourceType.kRate);
	public PIDController shooterPID = new PIDController(Kp, Ki, Kd, Kf, sm, velMimic);

	public RightShooter() {
		leftMotor.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		leftMotor.setPIDSourceType(PIDSourceType.kRate);
		shooterPID.setOutputRange(-1.0, 1.0);
	}

	public void initDefaultCommand() {
		setDefaultCommand(new cmdMoveRightShooter_test());
	}

	// Setters

	public void setShooterSpeed(double speed) {
		leftMotor.set(speed);
		rightMotor.set(speed);
	}

	// Getters

	public int getShooterVelocity() {
		return leftMotor.getEncVelocity();
	}

	public double getShooterRPM() {
		return leftMotor.getSpeed();
	}

	// Utility

	@Deprecated
	public double calculateFF(double setpoint) {
		return 1 / (setpoint * (1 / 60) * 0.1 * 4096);
	}

	public void log() {
	}
}

