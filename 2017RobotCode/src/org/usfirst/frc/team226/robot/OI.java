package org.usfirst.frc.team226.robot;


import static org.usfirst.frc.team226.robot.RobotMap.DRIVER_CONTROLLER;
import static org.usfirst.frc.team226.robot.RobotMap.MANIP_CONTROLLER;

import org.usfirst.frc.team226.robot.autons.grpBackOut2;
import org.usfirst.frc.team226.robot.commands.cmdExpandRobot;
import org.usfirst.frc.team226.robot.commands.cmdMoveLeftAgitator_button;
import org.usfirst.frc.team226.robot.commands.cmdMoveLeftFeeder_button;
import org.usfirst.frc.team226.robot.commands.cmdMoveRightAgitator_button;
import org.usfirst.frc.team226.robot.commands.cmdMoveRightFeeder_button;
import org.usfirst.frc.team226.robot.commands.cmdPIDLeftShooter;
import org.usfirst.frc.team226.robot.commands.cmdPIDRightShooter;
import org.usfirst.frc.team226.robot.extlib.Controller;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	public Controller driver = new Controller(DRIVER_CONTROLLER, 0.2);
	public Controller manip = new Controller(MANIP_CONTROLLER, 0.2);
	
	public OI() {
		
		driver.getAButton().whenPressed(new grpBackOut2());
		
		manip.getAButton().whenPressed(new cmdPIDLeftShooter(RobotMap.L_SHOOTER_SETPOINT)); //3075 maybe decrease to 3000?
		manip.getAButton().whenPressed(new cmdPIDRightShooter(RobotMap.R_SHOOTER_SETPOINT)); //3150 maybe decrease to 3075?
		
		manip.getYButton().whileHeld(new cmdMoveLeftFeeder_button());
		manip.getYButton().whileHeld(new cmdMoveRightFeeder_button());
		manip.getYButton().whileHeld(new cmdMoveRightAgitator_button(3, 0.5, 0.5, 0.75));
		manip.getYButton().whileHeld(new cmdMoveLeftAgitator_button(3, 0.5, 0.5, 0.75));
		
		manip.getSTARTButton().whenPressed(new cmdExpandRobot());
	  	
	}
	
	/*Controller Bindings
	 * Driver:
	 * LT hold - backwards driving toggle & camera servo toggle
	 * RT hold - half speed driving toggle
	 * 
	 * Manip:
	 * A - shooter/feeder pid start
	 * B - shooter/feeder pid stop
	 * Y(HOLD) - feeder/agitator move on button
	 * START - toggle servos in and out
	 * LT(HOLD) - intake forwards
	 * RT(HOLD) - intake backwards
	 * RS - manual shooter/feeder control
	 */
}
