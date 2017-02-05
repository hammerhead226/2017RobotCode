package org.usfirst.frc.team226.robot.subsystems;

import static org.usfirst.frc.team226.robot.RobotMap.DT_FL_MOTOR;
import static org.usfirst.frc.team226.robot.RobotMap.DT_FR_MOTOR;
import static org.usfirst.frc.team226.robot.RobotMap.DT_RL_MOTOR;
import static org.usfirst.frc.team226.robot.RobotMap.DT_RR_MOTOR;

import org.usfirst.frc.team226.robot.commands.cmdArcadeDrive;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveTrain extends Subsystem {

	public CANTalon frontLeftMotor = new CANTalon(DT_FL_MOTOR);
	public CANTalon rearLeftMotor = new CANTalon(DT_RL_MOTOR);

	public CANTalon frontRightMotor = new CANTalon(DT_FR_MOTOR);
	public CANTalon rearRightMotor = new CANTalon(DT_RR_MOTOR);
	
	public DigitalInput photoeye = new DigitalInput(5);

	RobotDrive drive = new RobotDrive(frontLeftMotor, rearLeftMotor, frontRightMotor, rearRightMotor);
	
	// Direction PID
	// private static double dirKp = 0.0;
	// private static double dirKi = 0.0;
	// private static double dirKd = 0.0;

	// Alternatives: SerialPort.Port.kMXP, SPI.Port.kMXP, or I2C.Port.kMXP
	// public AHRS navX = new AHRS(SerialPort.Port.kUSB);
	// public PIDOutputMimic dirMimic = new PIDOutputMimic();
	// public PIDController dirController = new PIDController(dirKp, dirKi,
	// dirKd, navX, dirMimic);

	public DriveTrain() {
		// putTurnCommand(45, 2);
		// putTurnCommand(90, 2);
		// putTurnCommand(180, 2);
		// configureDirPID();
		// putDirPID();
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

	// public void configureDirPID() {
	// dirController.setInputRange(-180.0f, 180.0f);
	// dirController.setOutputRange(-1.0, 1.0);
	// }
	//
	// public void putDirPID() {
	// LiveWindow.addActuator("DriveTrain", "Direction PID", dirController);
	// SmartDashboard.putData("Direction PID", dirController);
	// }

	// public void putTurnCommand(int angle, int seconds) {
	// SmartDashboard.putData(new cmdPIDTurnToAngle(angle, seconds));
	// }

	public void log() {
		// Drivetrain
		// # -L/R encoder counts
		// # -Average encoder count
		// # -Value/voltage of all Talons
		SmartDashboard.putNumber("DT_LeftEncoder", 0);
		SmartDashboard.putNumber("DT_RightEncoder", 0);
		SmartDashboard.putNumber("DT_AvgEncoder", 0);
		SmartDashboard.putNumber("DT_FLTalon", frontLeftMotor.getOutputVoltage());
		SmartDashboard.putNumber("DT_RLTalon", rearLeftMotor.getOutputVoltage());
		SmartDashboard.putNumber("DT_FRTalon", frontRightMotor.getOutputVoltage());
		SmartDashboard.putNumber("DT_RRTalon", rearRightMotor.getOutputVoltage());
	}
}
