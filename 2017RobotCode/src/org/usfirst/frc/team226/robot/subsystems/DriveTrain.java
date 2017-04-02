package org.usfirst.frc.team226.robot.subsystems;

import static org.usfirst.frc.team226.robot.RobotMap.DT_FL_MOTOR;
import static org.usfirst.frc.team226.robot.RobotMap.DT_FR_MOTOR;
import static org.usfirst.frc.team226.robot.RobotMap.DT_RL_MOTOR;
import static org.usfirst.frc.team226.robot.RobotMap.DT_RR_MOTOR;

import org.usfirst.frc.team226.robot.commands.cmdArcadeDrive;
import org.usfirst.frc.team226.robot.extlib.DoubleEncoder;
import org.usfirst.frc.team226.robot.extlib.PIDOutputMimic;

import com.ctre.CANTalon;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveTrain extends Subsystem {

	public boolean wasTurn = true;
	// left encoder
	public CANTalon frontLeftMotor = new CANTalon(DT_FL_MOTOR);
	public CANTalon rearLeftMotor = new CANTalon(DT_RL_MOTOR);
	// right encoder
	public CANTalon frontRightMotor = new CANTalon(DT_FR_MOTOR);
	public CANTalon rearRightMotor = new CANTalon(DT_RR_MOTOR);

	RobotDrive drive = new RobotDrive(frontLeftMotor, rearLeftMotor, frontRightMotor, rearRightMotor);

	// Direction PID
	private static double dirKp = 0.07; //DRIVE STRAIGHT - P=0.003, NO  I OR D //0.11, 0.02, 0.205 
	private static double dirKi = 0;
	private static double dirKd = 0.162;
	//DRIVE STRAIGHT - P=0.003
	//ROBOT 1 CURVE - P=0.42, I=0.00, D=0.92
	//ROBOT 1 POINT TURN - P=0.11, I=0.02, D=0.205
	//ROBOT 2 CURVE - 
	//ROBOT 2 POINT TURN - P= ,I= , D= 
	

	// Alternatives: SerialPort.Port.kMXP, SPI.Port.kMXP, or I2C.Port.kMXP
	public AHRS navX = new AHRS(I2C.Port.kOnboard);
	public PIDOutputMimic dirMimic = new PIDOutputMimic();
	public PIDController dirController = new PIDController(dirKp, dirKi, dirKd, navX, dirMimic);

	private static double distKp = 0.003; //LOCK - 8/10
	private static double distKi = 0.0;
	private static double distKd = 0.0035;

	public DoubleEncoder doubleEncoder = new DoubleEncoder(frontLeftMotor, frontRightMotor,
			PIDSourceType.kDisplacement);
	public PIDOutputMimic distMimic = new PIDOutputMimic();
	public PIDController distController = new PIDController(distKp, distKi, distKd, doubleEncoder, distMimic);

	public DriveTrain() {
//		frontLeftMotor.setPIDSourceType(PIDSourceType.kDisplacement);
//		frontRightMotor.setPIDSourceType(PIDSourceType.kDisplacement);
		frontLeftMotor.reverseSensor(true);
		distController.setOutputRange(-1, 1);
		dirController.setOutputRange(-1, 1);
//		doubleEncoder.setRightEncoderInverted(true);
	}

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
		frontLeftMotor.setPosition(0);
		frontRightMotor.setPosition(0);
	}

	public void log() {
//		SmartDashboard.putNumber("DT_LeftPos", frontLeftMotor.getPosition());
//		SmartDashboard.putNumber("DT_RightPos", frontRightMotor.getPosition());
//		SmartDashboard.putNumber("DT_FLTalon", frontLeftMotor.getOutputVoltage());
//		SmartDashboard.putNumber("DT_RLTalon", rearLeftMotor.getOutputVoltage());
//		SmartDashboard.putNumber("DT_FRTalon", frontRightMotor.getOutputVoltage());
//		SmartDashboard.putNumber("DT_RRTalon", rearRightMotor.getOutputVoltage());
		
		SmartDashboard.putData("DT_DistPID", distController);
		SmartDashboard.putNumber("DT_DistPID Error", distController.getError());
		SmartDashboard.putNumber("DT_DistPID Error GRAPH", distController.getError());
		
		SmartDashboard.putData("DT_DirPID", dirController);
		SmartDashboard.putNumber("DT_DirPID Error", dirController.getError());
		SmartDashboard.putNumber("DT_DirPID Error GRAPH", dirController.getError());
		
//		SmartDashboard.putNumber("DT_DirPIDOutput", dirController.get());
		SmartDashboard.putNumber("DT_DoubleEncoder", doubleEncoder.pidGet());
		SmartDashboard.putNumber("DT_DoubleEncodernum", doubleEncoder.pidGet());
		SmartDashboard.putNumber("DT_NavXHeading", navX.getYaw());
//		SmartDashboard.putNumber("DT_NavXHeadingnum", navX.getYaw());
	}
}
