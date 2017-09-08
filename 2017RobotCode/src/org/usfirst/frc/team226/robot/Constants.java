package org.usfirst.frc.team226.robot;

public class Constants {
	
	// Robot properties
	
	public static final double WHEEL_DIAMETER = 6; // Inches
	public static final double WHEEL_CIRCUMFERENCE = Math.PI * WHEEL_DIAMETER;
	public static final double WHEELBASE_WIDTH = 31.25; // Inches
	
	public static final double MAX_RPM = 462.0; // Free wheel RPM
	public static final double MAX_RPS = MAX_RPM / 60.0;
	
	public static final double MOTION_MAGIC_ONTARGET_TOLERANCE = 100; // Native units
	
	public static double SHOOTER_SETPOINT = 3400; // RPM
	
	public static final double FEEDER_SPEED = 0.7; //PercentVBus
	public static final double ACTIVEFLOOR_SPEED = 0.7; //PercentVBus

	// Autonomous properties - all distances in inches
	
	public static final double MID_GEAR_DISTANCE = 72.5;
	public static final double MID_GEAR_BACKUP = 33.0;
	
}
