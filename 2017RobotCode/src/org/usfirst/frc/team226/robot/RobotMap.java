package org.usfirst.frc.team226.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

	// Motor Ports - ROBOT 1

	public static final int DT_FL_MOTOR = 8;
	public static final int DT_FR_MOTOR = 2;
	public static final int DT_RL_MOTOR = 7;
	public static final int DT_RR_MOTOR = 3;

	public static final int SHOOTER_FL_MOTOR = 10;
	public static final int SHOOTER_BL_MOTOR = 4;

	public static final int SHOOTER_FR_MOTOR = 33; //5
	public static final int SHOOTER_BR_MOTOR = 6;
	
	public static final int FEEDER_MOTOR = 5;
	public static final int ACTIVEFLOOR_MOTOR = 2; // PWM

	public static final int CLIMBERINTAKE_LEFT_MOTOR = 9;
	public static final int CLIMBERINTAKE_RIGHT_MOTOR = 1;

	// Motor Ports - ROBOT 2

	/*
	 * public static final int DT_FL_MOTOR = 8; public static final int
	 * DT_FR_MOTOR = 2; public static final int DT_RL_MOTOR = 9; public static
	 * final int DT_RR_MOTOR = 3;
	 * 
	 * public static final int L_SHOOTER_F_MOTOR = 10; public static final int
	 * L_SHOOTER_B_MOTOR = 11; public static final int L_FEEDER_MOTOR = 12;
	 * public static final int L_AGITATOR_MOTOR = 7; //PWM
	 * 
	 * public static final int R_SHOOTER_F_MOTOR = 4; public static final int
	 * R_SHOOTER_B_MOTOR = 5; public static final int R_FEEDER_MOTOR = 6; public
	 * static final int R_AGITATOR_MOTOR = 6; //PWM
	 * 
	 * public static final int CLIMBERINTAKE_LEFT_MOTOR = 7; public static final
	 * int CLIMBERINTAKE_RIGHT_MOTOR = 1;
	 */

	// Servo Ports
	public static final int INTAKE_SERVO = 1;
	public static final int LEFT_GEARMECH_SERVO = 8;
	public static final int RIGHT_GEARMECH_SERVO = 9;
	public static final int LEFT_ACTUATOR = 4;
	public static final int RIGHT_ACTUATOR = 7;

	// Controller Ports
	public static final int DRIVER_CONTROLLER = 0;
	public static final int MANIP_CONTROLLER = 1;

}
