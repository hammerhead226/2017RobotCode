package org.usfirst.frc.team226.robot.subsystems;

import static org.usfirst.frc.team226.robot.RobotMap.L_SHOOTER_B_MOTOR;
import static org.usfirst.frc.team226.robot.RobotMap.L_SHOOTER_F_MOTOR;
import static org.usfirst.frc.team226.robot.RobotMap.L_SHOOTER_LINEAR_ACTUATOR;

import org.usfirst.frc.team226.robot.commands.cmdMoveLeftShooter_manual;
import org.usfirst.frc.team226.robot.extlib.LeftMagEncoderVelocityMimic;
import org.usfirst.frc.team226.robot.extlib.PIDOutputMimic;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class LeftShooter extends Subsystem {

	public double multiplier = 0.75;

	// PID logging
	public double errorLog;
	public double pidOutputLog;

	private CANTalon frontMotor = new CANTalon(L_SHOOTER_F_MOTOR);
	private CANTalon backMotor = new CANTalon(L_SHOOTER_B_MOTOR);
	
	private Servo linearActuator = new Servo(L_SHOOTER_LINEAR_ACTUATOR);

	private static double Kp = 0.000016;
	private static double Ki = 0;
	private static double Kd = 0.00005;
	private static double Kf = 0.00005;

	private PIDOutputMimic velMimic = new PIDOutputMimic();
	public LeftMagEncoderVelocityMimic sm = new LeftMagEncoderVelocityMimic(frontMotor, PIDSourceType.kRate);
	public PIDController velPID = new PIDController(Kp, Ki, Kd, Kf, sm, velMimic);

	public LeftShooter() {
		frontMotor.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		frontMotor.setPIDSourceType(PIDSourceType.kRate);
		velPID.setPercentTolerance(5);
		velPID.setOutputRange(-1.0, 1.0);
		
		setLinearActuator(0);
	}

	public void initDefaultCommand() {
		setDefaultCommand(new cmdMoveLeftShooter_manual());
	}

	// Setters

	public void setShooterSpeed(double speed) {
		frontMotor.set(speed);
		backMotor.set(speed);
	}
	
	public void setLinearActuator(double value) {
		linearActuator.set(value);
	}

	// Getters

	public double getShooterRPM() {
		return frontMotor.getEncVelocity() * (600.0/4096.0);
	}

	// Utility

	@Deprecated
	public double calculateFF(double setpoint) {
		return 1 / (setpoint * (1 / 60) * 0.1 * 4096);
	}

	public void log() {
		SmartDashboard.putNumber("LS_RPM", getShooterRPM());
		SmartDashboard.putNumber("LS_RPMnum", getShooterRPM());
		SmartDashboard.putNumber("LS_PIDOutput", velPID.get());
		SmartDashboard.putNumber("LS_PIDSetpoint", velPID.getSetpoint());
		SmartDashboard.putBoolean("LS_PIDEnabled", velPID.isEnabled());
		SmartDashboard.putNumber("LS_LTalon", frontMotor.getBusVoltage());
		SmartDashboard.putNumber("LS_RTalon", backMotor.getBusVoltage());
		SmartDashboard.putData("LS_PID", velPID);
	}
}
