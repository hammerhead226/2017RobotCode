package org.usfirst.frc.team226.robot;


import static org.usfirst.frc.team226.robot.RobotMap.DRIVER_CONTROLLER;
import static org.usfirst.frc.team226.robot.RobotMap.MANIP_CONTROLLER;

import org.usfirst.frc.team226.robot.commands.cmdExpandRobot;
import org.usfirst.frc.team226.robot.commands.cmdMoveLeftFeeder_button;
import org.usfirst.frc.team226.robot.commands.cmdPIDDriveInches;
import org.usfirst.frc.team226.robot.commands.cmdPIDLeftShooter;
import org.usfirst.frc.team226.robot.commands.cmdResetDTSensors;
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
		
//		manip.getAButton().whenPressed(new cmdPIDLeftShooter(2950));
//		manip.getAButton().whenPressed(new cmdPIDRightShooter(3000));
//		manip.getYButton().whileHeld(new cmdMoveLeftFeeder_button());
//		manip.getYButton().whileHeld(new cmdMoveRightFeeder_button());
//		manip.getBButton().whenPressed(new cmdToggleCameraTurret());
		driver.getAButton().whenPressed(new cmdPIDDriveInches(-75.0, 0.7));
		driver.getXButton().whenPressed(new cmdPIDDriveInches(-19.0, 0.7));
		driver.getYButton().whenPressed(new cmdResetDTSensors());
		
//		manip.getSTARTButton().whenPressed(new cmdExpandRobot());
//		manip.getBACKButton().whenPressed(new cmdResetRobot());
	  	
	}
	
	/*Controller Bindings
	 * Driver:
	 * A - vision align
	 * B - stop vision align
	 * LT hold - backwards driving toggle & camera servo toggle
	 * RT hold - half speed driving toggle
	 * 
	 * Manip:
	 * A - shooter/feeder pid start
	 * B - shooter/feeder pid stop
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
