package org.usfirst.frc.team226.robot.subsystems;

import static org.usfirst.frc.team226.robot.RobotMap.CAMERA_SERVO;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class CameraTurret extends Subsystem {

	private boolean forward = true;

	private Servo cameraServo = new Servo(CAMERA_SERVO);

	public void initDefaultCommand() {
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
		cameraServo.set(0);
	}

	public void servoBackward() {
		cameraServo.set(1);
	}
}