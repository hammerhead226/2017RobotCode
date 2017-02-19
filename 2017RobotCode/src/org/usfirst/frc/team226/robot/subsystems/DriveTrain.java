package org.usfirst.frc.team226.robot.subsystems;

import static org.usfirst.frc.team226.robot.RobotMap.DT_FL_MOTOR;
import static org.usfirst.frc.team226.robot.RobotMap.DT_FR_MOTOR;
import static org.usfirst.frc.team226.robot.RobotMap.DT_RL_MOTOR;
import static org.usfirst.frc.team226.robot.RobotMap.DT_RR_MOTOR;

import org.usfirst.frc.team226.robot.commands.cmdArcadeDrive;
import org.usfirst.frc.team226.robot.extlib.PIDOutputMimic;

import com.ctre.CANTalon;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveTrain extends Subsystem {

	public boolean wasTurn = true;

	public CANTalon frontLeftMotor = new CANTalon(DT_FL_MOTOR);
	public CANTalon rearLeftMotor = new CANTalon(DT_RL_MOTOR);

	public CANTalon frontRightMotor = new CANTalon(DT_FR_MOTOR);
	public CANTalon rearRightMotor = new CANTalon(DT_RR_MOTOR);

	RobotDrive drive = new RobotDrive(frontLeftMotor, rearLeftMotor, frontRightMotor, rearRightMotor);

	// Direction PID
	private static double dirKp = 0.0;
	private static double dirKi = 0.0;
	private static double dirKd = 0.0;

	// Alternatives: SerialPort.Port.kMXP, SPI.Port.kMXP, or I2C.Port.kMXP
	public AHRS navX = new AHRS(I2C.Port.kOnboard);
	public PIDOutputMimic dirMimic = new PIDOutputMimic();
	public PIDController dirController = new PIDController(dirKp, dirKi, dirKd, navX, dirMimic);

	public void initDefaultCommand() {
		setDefaultCommand(new cmdArcadeDrive());
	}

	public void tankDrive(double left, double right) {
		drive.tankDrive(left, right);
	}

	public void arcadeDrive(double throttle, double turn) {
		drive.arcadeDrive(throttle, turn, true);
	}
	
	public void voltageDrive(double left, double right) {
	    	left *= 12.5;
	    	right *= 12.5;
	    	frontLeftMotor.set(left);
	    	rearLeftMotor.set(left);
	    	frontRightMotor.set(right);
	    	rearRightMotor.set(right);
	    }

	public double getGyroAngle() {
		return navX.getYaw();
	}
	
	public void resetAllSensors() {
		navX.reset();
		rearLeftMotor.setPosition(0);
		rearRightMotor.setPosition(0);
		dirController.reset();
	}
	

	public void log() {
		SmartDashboard.putNumber("DT_LeftEncoder", 0);
		SmartDashboard.putNumber("DT_RightEncoder", 0);
		SmartDashboard.putNumber("DT_FLTalon", frontLeftMotor.getOutputVoltage());
		SmartDashboard.putNumber("DT_RLTalon", rearLeftMotor.getOutputVoltage());
		SmartDashboard.putNumber("DT_FRTalon", frontRightMotor.getOutputVoltage());
		SmartDashboard.putNumber("DT_RRTalon", rearRightMotor.getOutputVoltage());
	}
}
