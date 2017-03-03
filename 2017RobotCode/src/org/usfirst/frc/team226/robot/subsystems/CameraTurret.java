package org.usfirst.frc.team226.robot.subsystems;

import static org.usfirst.frc.team226.robot.RobotMap.CAMERA_SERVO;

import org.usfirst.frc.team226.robot.commands.cmdToggleCameraTurret;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class CameraTurret extends Subsystem {

	private boolean forward = true;

	private Servo cameraServo = new Servo(CAMERA_SERVO);
	
	public CameraTurret() {
//		cameraServo.set(1);
	}

	public void initDefaultCommand() {
		setDefaultCommand(new cmdToggleCameraTurret());
	}

	public void toggle() {
		if (forward) {
			servoBackward();
		}
		else {
			servoForward();
		}
		forward = !forward;
	}

	public void servoForward() {
		cameraServo.set(0.1); //Robot 1: 0
	}

	public void servoBackward() {
		cameraServo.set(0.9);
	}
}
