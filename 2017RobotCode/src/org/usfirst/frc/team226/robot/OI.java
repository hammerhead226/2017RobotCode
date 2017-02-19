package org.usfirst.frc.team226.robot;


import static org.usfirst.frc.team226.robot.RobotMap.DRIVER_CONTROLLER;
import static org.usfirst.frc.team226.robot.RobotMap.MANIP_CONTROLLER;

import org.usfirst.frc.team226.robot.commands.cmdExpandRobot;
import org.usfirst.frc.team226.robot.commands.cmdPIDLeftShooter;
import org.usfirst.frc.team226.robot.commands.cmdPIDTurnWithVision;
import org.usfirst.frc.team226.robot.commands.cmdResetRobot;
import org.usfirst.frc.team226.robot.commands.cmdToggleCameraTurret;
import org.usfirst.frc.team226.robot.extlib.Controller;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	public Controller driver = new Controller(DRIVER_CONTROLLER, 0.2);
	public Controller manip = new Controller(MANIP_CONTROLLER, 0.2);
	
	public OI() {
		driver.getRSButton().whenPressed(new cmdToggleCameraTurret());

		manip.getAButton().whenPressed(new cmdPIDLeftShooter(10000));
		manip.getSTARTButton().whenPressed(new cmdExpandRobot());
		manip.getBACKButton().whenPressed(new cmdResetRobot());
	  	
		manip.getAButton().whenPressed(new cmdPIDTurnWithVision());

	}
	
	/*Controller Bindings
	 * Driver:
	 * A - vision align
	 * B - stop vision align
	 * RS Click - backwards driving toggle & camera servo toggle
	 * LS Click - half speed driving toggle
	 * 
	 * Manip:
	 * A - shooter/feeder start
	 * B - shooter/feeder stop
	 * START - release servos
	 * BACK - reset servos
	 * LT - intake forwards
	 * RT - intake backwards
	 * RS - manual shooter/feeder control
	 * Dpad left - switch to hopper shooting angle
	 * Dpad right - switch to airship shooting angle
	 * Dpad down - switch to boiler shooting angle
	 */
}
