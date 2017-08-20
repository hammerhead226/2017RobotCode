package org.usfirst.frc.team226.robot.subsystems;

import org.usfirst.frc.team226.robot.RobotMap;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Shooter extends Subsystem {

	private CANTalon frontLeft = new CANTalon(RobotMap.SHOOTER_FL_MOTOR);
	private CANTalon rearLeft = new CANTalon(RobotMap.SHOOTER_BL_MOTOR);
	private CANTalon frontRight = new CANTalon(RobotMap.SHOOTER_FR_MOTOR);
	private CANTalon rearRight = new CANTalon(RobotMap.SHOOTER_BR_MOTOR);
	
	private Servo leftAct = new Servo(RobotMap.LEFT_ACTUATOR);
	private Servo rightAct = new Servo(RobotMap.RIGHT_ACTUATOR);
	
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
		frontLeft.setIZone(1000); //Native units per 100ms
		
		closeGate();
	}
	
	public void directDrive(double speed) {
		frontLeft.set(speed);
		frontRight.set(speed);
		rearLeft.set(speed);
		rearRight.set(speed);
	}
	
	public void setSpeedRPM(double setpoint) {
		frontLeft.set(setpoint);
	}
	
	public void changeShooterControlMode(TalonControlMode mode) {
		frontLeft.changeControlMode(mode);
	}
	
	public double getSpeed() {
		return frontLeft.getEncVelocity() * (75.0 / 512.0);
	}
	
	private boolean open = false;
	
	public void openGate() {
		open = true;
		leftAct.set(0.8);
		rightAct.set(0.8);
	}
	
	public void closeGate() {
		open = false;
		leftAct.set(0.2);
		rightAct.set(0.2);
	}
	
	public void toggleGate() {
		if (open) {
			closeGate();
		}
		else {
			openGate();
		}
	}
	
    public void initDefaultCommand() {
    }
}

