package org.usfirst.frc.team226.robot.commands;

import org.usfirst.frc.team226.robot.Robot;

import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class cmdPIDTurnWithVision extends Command {

	private boolean wasOnTarget = false;
	private long startTime;
	private int onTargetDuration = 500;

	public cmdPIDTurnWithVision() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.driveTrain);

	}

	// Called just before this Command runs the first time
	protected void initialize() {

		Robot.driveTrain.frontLeftMotor.changeControlMode(TalonControlMode.Voltage);
		Robot.driveTrain.rearLeftMotor.changeControlMode(TalonControlMode.Voltage);
		Robot.driveTrain.frontRightMotor.changeControlMode(TalonControlMode.Voltage);
		Robot.driveTrain.rearRightMotor.changeControlMode(TalonControlMode.Voltage);
		
		Robot.driveTrain.frontLeftMotor.setVoltageCompensationRampRate(0.0);
		Robot.driveTrain.rearLeftMotor.setVoltageCompensationRampRate(0.0);
		Robot.driveTrain.frontRightMotor.setVoltageCompensationRampRate(0.0);
		Robot.driveTrain.rearRightMotor.setVoltageCompensationRampRate(0.0);
		
		Robot.driveTrain.resetAllSensors();

		Robot.theta = Math.toRadians(57.175 / 2); // "static" number: 57.175
		Robot.dist = (Robot.IMG_WIDTH / 2) / Math.tan(Robot.theta); // "static" number
		Robot.delta = (Robot.IMG_WIDTH / 2) - Robot.midpoint;

		if (Robot.delta > 0) {
			Robot.lambda = Math.toDegrees(Math.atan(Robot.delta / Robot.dist)) - 4.4668; // 7.1468
		} else {
			Robot.lambda = Math.toDegrees(Math.atan(Robot.delta / Robot.dist)) + 4.4668;
		}
		
		Robot.driveTrain.dirController.setSetpoint(-Robot.lambda);
		Robot.driveTrain.dirController.enable();
		
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {

		Robot.turn = Robot.driveTrain.dirController.get();

		if (Robot.turn > 0 && Robot.turn < 0.18) {
			Robot.turn = 0.18;
		} else if (Robot.turn < 0 && Robot.turn > -0.18) {
			Robot.turn = -0.18;
		}
		Robot.driveTrain.voltageDrive(Robot.turn, Robot.turn);

	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {

		if (Math.abs(Robot.driveTrain.dirController.getError()) < 1.3) {
			if (!wasOnTarget) {
				startTime = System.currentTimeMillis();
				wasOnTarget = true;
			}
			long timeOnTarget = System.currentTimeMillis() - startTime;
			if (timeOnTarget >= onTargetDuration) {
				wasOnTarget = false;
				return true;
			}
			
		} else wasOnTarget = false;
		
		if (Robot.oi.driver.getBACKButtonPressed())
			return true;
		return false;

	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.driveTrain.dirController.reset();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
