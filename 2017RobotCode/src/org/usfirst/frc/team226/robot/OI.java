package org.usfirst.frc.team226.robot;


import org.usfirst.frc.team226.robot.extlib.Controller;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	public Controller driver = new Controller(RobotMap.DRIVER_CONTROLLER);
	public Controller manip = new Controller(RobotMap.MANIP_CONTROLLER);
	
	public OI() {
	}
}
