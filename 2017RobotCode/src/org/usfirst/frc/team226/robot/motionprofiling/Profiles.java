package org.usfirst.frc.team226.robot.motionprofiling;

import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Waypoint;

public class Profiles {

	private static boolean redAlliance = false;
	private static boolean blueAlliance = false;

	// Red right gear
	private static final Waypoint[] redRightGear = new Waypoint[] { new Waypoint(0, 0, 0),
			new Waypoint(2.82, 0, Pathfinder.d2r(60)), new Waypoint(5.79, 3.5865, Pathfinder.d2r(55)) };
	// Red hopper from right gear
	private static final Waypoint[] redHopperFromRightGear = new Waypoint[] { new Waypoint(0, 0, 0),
			new Waypoint(4.33, 1.73, Pathfinder.d2r(-15.92)) };

	private static final Waypoint[] redRightGearTeleopPrep = new Waypoint[] { new Waypoint(0, 0, 0),
			new Waypoint(1, 1.5, Pathfinder.d2r(125)), new Waypoint(-6, 0.5, Pathfinder.d2r(125)) };

	// Blue left gear
	private static final Waypoint[] blueLeftGear = new Waypoint[] { new Waypoint(0, 0, 0),
			new Waypoint(2.82, 0, Pathfinder.d2r(-60)), new Waypoint(5.6214, -3.5865, Pathfinder.d2r(-55)) };

	// blue hopper from left gear
	private static final Waypoint[] blueHopperFromLeftGear = new Waypoint[] { new Waypoint(0, 0, 0),
			new Waypoint(5.9, -1.9894, Pathfinder.d2r(-/*15.92*/35)) };

	private static final Waypoint[] blueLeftGearTeleopPrep = new Waypoint[] { new Waypoint(0, 0, 0),
			new Waypoint(1, -1.5, Pathfinder.d2r(125)), new Waypoint(-6, -0.5, Pathfinder.d2r(125)) };

	// Red right gear + hopper
	public static final double[][] redRightGear_Left = ProfileGenerator
			.generate(RobotSpeed.SLOW, redRightGear, redAlliance).getLeftProfile();
	public static final double[][] redRightGear_Right = ProfileGenerator
			.generate(RobotSpeed.SLOW, redRightGear, redAlliance).getRightProfile();
	public static final double[][] redHopperFromRightGear_Left = ProfileGenerator
			.generate(RobotSpeed.FAST, redHopperFromRightGear, redAlliance).getLeftProfile();
	public static final double[][] redHopperFromRightGear_Right = ProfileGenerator
			.generate(RobotSpeed.FAST, redHopperFromRightGear, redAlliance).getRightProfile();

	public static final double[][] redRightGearTeleopPrep_Left = ProfileGenerator
			.generate(RobotSpeed.FAST, redRightGearTeleopPrep, redAlliance).getLeftProfile();
	public static final double[][] redRightGearTeleopPrep_Right = ProfileGenerator
			.generate(RobotSpeed.FAST, redRightGearTeleopPrep, redAlliance).getRightProfile();

	// Blue left gear + hopper
	public static final double[][] blueLeftGear_Left = ProfileGenerator
			.generate(RobotSpeed.MEDIUM, blueLeftGear, blueAlliance).getLeftProfile();
	public static final double[][] blueLeftGear_Right = ProfileGenerator
			.generate(RobotSpeed.MEDIUM, blueLeftGear, blueAlliance).getRightProfile();
	public static final double[][] blueHopperFromLeftGear_Left = ProfileGenerator
			.generate(RobotSpeed.FAST, blueHopperFromLeftGear, blueAlliance).getLeftProfile();
	public static final double[][] blueHopperFromLeftGear_Right = ProfileGenerator
			.generate(RobotSpeed.FAST, blueHopperFromLeftGear, blueAlliance).getRightProfile();
	
	public static final double[][] blueLeftGearTeleopPrep_Left = ProfileGenerator
			.generate(RobotSpeed.FAST, blueLeftGearTeleopPrep, blueAlliance).getLeftProfile();
	public static final double[][] blueLeftGearTeleopPrep_Right = ProfileGenerator
			.generate(RobotSpeed.FAST, blueLeftGearTeleopPrep, blueAlliance).getRightProfile();
}
