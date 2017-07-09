package org.usfirst.frc.team226.robot.motionprofiling;

import org.usfirst.frc.team226.robot.Constants;

import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.modifiers.TankModifier;

public class ProfileGenerator {

	public static TrajArray generate(RobotSpeed speed, Waypoint[] waypoints, boolean generate) {
		if (generate) {
		System.out.println("***PROFILE GENERATION STARTED***");
		Trajectory.Config config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_QUINTIC,
				Trajectory.Config.SAMPLES_HIGH, 0.01, speed.getMaxVel(), speed.getMaxAccel(), 60.0);

		TankModifier mod = new TankModifier(Pathfinder.generate(waypoints, config))
				.modify(Constants.WHEELBASE_WIDTH / Constants.WHEEL_CIRCUMFERENCE); // Width of the robot in rotations

		return new TrajArray(mod.getLeftTrajectory(), mod.getRightTrajectory());
		}
		else {
			return new TrajArray(new Trajectory(0), new Trajectory(0));
		}
	}

}

class TrajArray {

	Trajectory[] traj;

	public TrajArray(Trajectory leftProfile, Trajectory rightProfile) {
		traj = new Trajectory[] { leftProfile, rightProfile };
	}

	double[][] getLeftProfile() {
		double[][] profile = new double[traj[0].length()][3];

		for (int i = 0; i < traj[0].length(); i++) {
			Trajectory.Segment left = traj[0].get(i);

			profile[i][0] = left.position;
			profile[i][1] = left.velocity * 60.0;
			profile[i][2] = 10;
		}
		System.out.println("***LEFT PROFILE GENERATED***");
		return profile;
	}

	double[][] getRightProfile() {
		double[][] profile = new double[traj[1].length()][3];

		for (int i = 0; i < traj[1].length(); i++) {
			Trajectory.Segment right = traj[1].get(i);

			profile[i][0] = right.position;
			profile[i][1] = right.velocity * 60.0;
			profile[i][2] = 10;
		}
		System.out.println("***RIGHT PROFILE GENERATED***");
		return profile;
	}
}
