package org.usfirst.frc.team226.robot.subsystems;

import static org.usfirst.frc.team226.robot.RobotMap.R_SHOOTER_B_MOTOR;
import static org.usfirst.frc.team226.robot.RobotMap.R_SHOOTER_F_MOTOR;

import org.usfirst.frc.team226.robot.Robot;
import org.usfirst.frc.team226.robot.commands.cmdMoveRightShooter_manual;
import org.usfirst.frc.team226.robot.extlib.PIDOutputMimic;
import org.usfirst.frc.team226.robot.extlib.RightMagEncoderVelocityMimic;

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
	
	public double multiplier = 0.75;

	// PID logging
	public double errorLog;
	public double pidOutputLog;

	private CANTalon backMotor = new CANTalon(R_SHOOTER_B_MOTOR);
	private CANTalon frontMotor = new CANTalon(R_SHOOTER_F_MOTOR);
	

	private static double Kp = 0.000016;
	private static double Ki = 0;
	private static double Kd = 0.000025; 
	private static double Kf = 0.00005;

	private PIDOutputMimic velMimic = new PIDOutputMimic();
	private RightMagEncoderVelocityMimic sm = new RightMagEncoderVelocityMimic(frontMotor, PIDSourceType.kRate);
	public PIDController velPID = new PIDController(Kp, Ki, Kd, Kf, sm, velMimic);

	public RightShooter() {
		backMotor.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		backMotor.setPIDSourceType(PIDSourceType.kRate);
		velPID.setOutputRange(-1.0, 1.0);
		velPID.setPercentTolerance(5);
		backMotor.setInverted(true);
		frontMotor.setInverted(true);
		
	}

	public void initDefaultCommand() {
		setDefaultCommand(new cmdMoveRightShooter_manual());
	}

	// Setters

	public void setShooterSpeed(double speed) {
		backMotor.set(speed);
		frontMotor.set(speed);
	}
	

	// Getters

	public double getShooterRPM() {
		return -frontMotor.getEncVelocity() * (600.0/4096.0);
	}

	// Utility

	@Deprecated
	public double calculateFF(double setpoint) {
		return 1 / (setpoint * (1 / 60) * 0.1 * 4096);
	}

	public void log() {
		SmartDashboard.putNumber("RS_RPM", getShooterRPM());
		SmartDashboard.putNumber("RS_RPMnum", getShooterRPM());
//		SmartDashboard.putNumber("RS_PIDOutput", velPID.get());
//		SmartDashboard.putNumber("RS_PIDSetpoint", velPID.getSetpoint());
//		SmartDashboard.putBoolean("RS_PIDEnabled", velPID.isEnabled());
		SmartDashboard.putNumber("RS_LTalon", backMotor.getOutputVoltage());
		SmartDashboard.putNumber("RS_RTalon", frontMotor.getOutputVoltage());
		SmartDashboard.putData("RS_PID", velPID);
	}
	
//	public void sharkLog() {
//		Robot.getSharkLogTable().putNumber("RS_RPM", getShooterRPM());
//		Robot.getSharkLogTable().putNumber("RS_PIDOutput", velPID.get());
//		Robot.getSharkLogTable().putNumber("RS_PIDSetpoint", velPID.getSetpoint());
//		Robot.getSharkLogTable().putBoolean("RS_PIDEnabled", velPID.isEnabled());
//		Robot.getSharkLogTable().putNumber("RS_BTalonVoltage", backMotor.getOutputVoltage());
//		Robot.getSharkLogTable().putNumber("RS_BTalonCurrent", backMotor.getOutputCurrent());
//		Robot.getSharkLogTable().putNumber("RS_FTalonVoltage", frontMotor.getOutputVoltage());
//		Robot.getSharkLogTable().putNumber("RS_FTalonCurrent", frontMotor.getOutputCurrent());
//	}
}
