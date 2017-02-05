package org.usfirst.frc.team226.robot.subsystems;

import org.usfirst.frc.team226.robot.RobotMap;
import org.usfirst.frc.team226.robot.commands.cmdMoveLeftFeeder_test;
import org.usfirst.frc.team226.robot.commands.cmdMoveRightFeeder_test;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class RightFeeder extends Subsystem {
	public double multiplier = 0.7;

	private CANTalon motor = new CANTalon(RobotMap.R_FEEDER_MOTOR);

	public RightFeeder() {
		motor.setInverted(true);
	}

	public void initDefaultCommand() {
		setDefaultCommand(new cmdMoveRightFeeder_test());
	}

	public void setFeederSpeed(double speed) {
		motor.set(speed);
	}

	public void log() {
	}
}
