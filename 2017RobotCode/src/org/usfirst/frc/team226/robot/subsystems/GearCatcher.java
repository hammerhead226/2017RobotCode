package org.usfirst.frc.team226.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class GearCatcher extends Subsystem {

	public DigitalInput leftLimitSwitch = new DigitalInput(8);
	public DigitalInput rightLimitSwitch = new DigitalInput(9);

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

