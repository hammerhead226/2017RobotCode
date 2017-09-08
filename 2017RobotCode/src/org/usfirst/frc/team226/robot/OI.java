package org.usfirst.frc.team226.robot;


import static org.usfirst.frc.team226.robot.RobotMap.DRIVER_CONTROLLER;
import static org.usfirst.frc.team226.robot.RobotMap.MANIP_CONTROLLER;

import org.usfirst.frc.team226.robot.commands.SetShooterSpeedRPM;
import org.usfirst.frc.team226.robot.commands.ToggleShooterGate;
import org.usfirst.frc.team226.robot.extlib.Controller;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	public Controller driver = new Controller(DRIVER_CONTROLLER, 0.2);
	public Controller manip = new Controller(MANIP_CONTROLLER, 0.2);
	
	public OI() {
		manip.getYButton().whileHeld(new SetShooterSpeedRPM(Constants.SHOOTER_SETPOINT));
		manip.getRBButton().whileHeld(new DriveFeeder());
		manip.getRBButton().whileHeld(new DriveActiveFloor());
	}
	
	public void sharkLog() {
		Robot.getSharkLogTable().putNumber("DC_LS_X", driver.getLeftJoystick_X());
		Robot.getSharkLogTable().putNumber("DC_LS_Y", driver.getLeftJoystick_Y());
		Robot.getSharkLogTable().putNumber("DC_RS_X", driver.getRightJoystick_X());
		Robot.getSharkLogTable().putNumber("DC_RS_Y", driver.getRightJoystick_Y());
		Robot.getSharkLogTable().putNumber("DC_LT", driver.getLeftTrigger());
		Robot.getSharkLogTable().putNumber("DC_RT", driver.getRightTrigger());
		Robot.getSharkLogTable().putBoolean("DC_A", driver.getAButtonPressed());
		Robot.getSharkLogTable().putBoolean("DC_B", driver.getBButtonPressed());
		Robot.getSharkLogTable().putBoolean("DC_X", driver.getXButtonPressed());
		Robot.getSharkLogTable().putBoolean("DC_Y", driver.getYButtonPressed());
		Robot.getSharkLogTable().putBoolean("DC_SELECT", driver.getSELECTButtonPressed());
		Robot.getSharkLogTable().putBoolean("DC_START", driver.getSTARTButtonPressed());
		Robot.getSharkLogTable().putBoolean("DC_LB", driver.getLBButtonPressed());
		Robot.getSharkLogTable().putBoolean("DC_RB", driver.getRBButtonPressed());
		Robot.getSharkLogTable().putBoolean("DC_LSClick", driver.getLSButtonPressed());
		Robot.getSharkLogTable().putBoolean("DC_RSClick", driver.getRSButtonPressed());
		
		Robot.getSharkLogTable().putNumber("MC_LS_X", manip.getLeftJoystick_X());
		Robot.getSharkLogTable().putNumber("MC_LS_Y", manip.getLeftJoystick_Y());
		Robot.getSharkLogTable().putNumber("MC_RS_X", manip.getRightJoystick_X());
		Robot.getSharkLogTable().putNumber("MC_RS_Y", manip.getRightJoystick_Y());
		Robot.getSharkLogTable().putNumber("MC_LT", manip.getLeftTrigger());
		Robot.getSharkLogTable().putNumber("MC_RT", manip.getRightTrigger());
		Robot.getSharkLogTable().putBoolean("MC_A", manip.getAButtonPressed());
		Robot.getSharkLogTable().putBoolean("MC_B", manip.getBButtonPressed());
		Robot.getSharkLogTable().putBoolean("MC_X", manip.getXButtonPressed());
		Robot.getSharkLogTable().putBoolean("MC_Y", manip.getYButtonPressed());
		Robot.getSharkLogTable().putBoolean("MC_SELECT", manip.getSELECTButtonPressed());
		Robot.getSharkLogTable().putBoolean("MC_START", manip.getSTARTButtonPressed());
		Robot.getSharkLogTable().putBoolean("MC_LB", manip.getLBButtonPressed());
		Robot.getSharkLogTable().putBoolean("MC_RB", manip.getRBButtonPressed());
		Robot.getSharkLogTable().putBoolean("MC_LSClick", manip.getLSButtonPressed());
		Robot.getSharkLogTable().putBoolean("MC_RSClick", manip.getRSButtonPressed());
	}
}
