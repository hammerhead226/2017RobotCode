package org.usfirst.frc.team226.robot.subsystems;

import org.usfirst.frc.team226.robot.RobotMap;
import org.usfirst.frc.team226.robot.commands.DriveShooter;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Shooter extends Subsystem {

	public CANTalon frontLeft = new CANTalon(RobotMap.SHOOTER_FL_MOTOR);
	public CANTalon rearLeft = new CANTalon(RobotMap.SHOOTER_BL_MOTOR);
	public CANTalon frontRight = new CANTalon(RobotMap.SHOOTER_FR_MOTOR);
	public CANTalon rearRight = new CANTalon(RobotMap.SHOOTER_BR_MOTOR);
	
	public Shooter() {
		frontLeft.changeControlMode(TalonControlMode.Speed);
		frontLeft.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		frontLeft.configPeakOutputVoltage(+12.0f, 0.0f);
		frontLeft.reverseOutput(false);
		
		rearLeft.changeControlMode(TalonControlMode.Follower);
		rearLeft.set(frontLeft.getDeviceID());
		rearLeft.reverseOutput(false);
		
		frontRight.changeControlMode(TalonControlMode.Follower);
		frontRight.set(frontLeft.getDeviceID());
		frontRight.reverseOutput(true);
		
		rearRight.changeControlMode(TalonControlMode.Follower);
		rearRight.set(frontLeft.getDeviceID());
		rearRight.reverseOutput(true);

		frontLeft.setProfile(0);
		frontLeft.setP(0.081);
		frontLeft.setI(0.0007);
		frontLeft.setD(0.81);
		frontLeft.setF(0.03367);
//		frontLeft.setP(0.08);
//		frontLeft.setI(0);
//		frontLeft.setD(0);
//		frontLeft.setF(0);
		frontLeft.setIZone(1000); //Native units per 100ms
		
	}
	
	public void directDrive(double speed) {
		changeMotorControlModes(TalonControlMode.PercentVbus);
		frontLeft.set(speed);
		frontRight.set(speed);
		rearLeft.set(-speed);
		rearRight.set(-speed);
	}
	
	public void setSpeedRPM(double setpoint) {
		frontLeft.set(setpoint);
	}
	
	public void changeShooterControlMode(TalonControlMode mode) {
		frontLeft.changeControlMode(mode);
	}
	
	private void changeMotorControlModes(TalonControlMode mode) {
		frontLeft.changeControlMode(mode);
		frontRight.changeControlMode(mode);
		rearLeft.changeControlMode(mode);
		rearRight.changeControlMode(mode);
	}
	
	public double getSpeed() {
		return frontLeft.getEncVelocity() * (75.0 / 512.0);
	}
	
    public void initDefaultCommand() {
    	setDefaultCommand(new DriveShooter());
    }
}

