package org.usfirst.frc.team226.robot.extlib;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.Notifier;

/**
 * Basic motion profile execution manager for use with CTRE Talon SRXs.
 * 
 * @author Alec Minchington
 * @version 1.4
 */
public class MotionProfileManager {

	protected double[][] profile;
	private int numPoints;
	private CANTalon talon;
	private int state;
	private double currentMode;
	private boolean start = false;
	private boolean finished = false;
	private boolean noProfile;

	private final int MIN_POINTS_IN_TALON = 5;
	private final int TALON_NEUTRAL = 0;
	private final int TALON_EXECUTE = 1;
	private final int TALON_HOLD = 2;

	private CANTalon.MotionProfileStatus status = new CANTalon.MotionProfileStatus();

	/**
	 * Construct a new MotionProfileManager for 10ms profiles (each point lasts
	 * 10ms)
	 * 
	 * @param profile
	 *            The motion profile to use
	 * @param talon
	 *            The Talon to follow the motion profile
	 */
	public MotionProfileManager(double[][] profile, CANTalon talon) {
		this.profile = profile;
		this.noProfile = false;
		this.numPoints = profile.length;
		this.talon = talon;
		state = 0;

		notifier.startPeriodic(0.005);
		this.talon.changeMotionControlFramePeriod(5);
	}

	/**
	 * Create a new MotionProfileManager for 10ms profiles (each point lasts
	 * 10ms)
	 * <p>
	 * You must use changeMotionProfile before starting execution as the manager
	 * has no profile loaded by default
	 * 
	 * @param talon
	 *            The Talon to follow the motion profile
	 */
	public MotionProfileManager(CANTalon talon) {
		this.profile = new double[][] { {} };
		this.noProfile = true;
		this.numPoints = profile.length;
		this.talon = talon;
		state = 0;

		notifier.startPeriodic(0.005);
		this.talon.changeMotionControlFramePeriod(5);
	}

	/**
	 * Call this to start the execution of the currently selected motion
	 * profile.
	 */
	public void startMotionProfile() {
		start = true;
	}

	/**
	 * Call this periodically while the motion profile is being executed
	 * 
	 * @param gainsProfile
	 *            The set of PIDF gains to be used (0 or 1)
	 */
	public void manage(int gainsProfile) {
		talon.getMotionProfileStatus(status);

		switch (state) {
		case 0:
			setMode(TALON_NEUTRAL);
			if (start) {
				if (noProfile) {
					state = 0;
					System.out.println("NO PROFILE LOADED");
				}
				start = false;
				finished = false;
				talon.changeControlMode(TalonControlMode.MotionProfile);
				fillTalonWithMotionProfile(gainsProfile);
				state = 1;
			}
			break;
		case 1:
			if (status.btmBufferCnt > MIN_POINTS_IN_TALON) {
				setMode(TALON_EXECUTE);
				state = 2;
			}
			break;
		case 2:
			if (status.activePointValid && status.activePoint.isLastPoint) {
				setMode(TALON_NEUTRAL);
				reset();
				finished = true;
			}
			break;
		}
	}

	private void setMode(double mode) {
		this.currentMode = mode;
		talon.set(mode);
	}

	/**
	 * Reset the manager, prepare to run a profile again
	 */
	private void reset() {
		state = 0;
		talon.clearMotionProfileTrajectories();
		System.out.println("RESET MPM ID " + talon.getDeviceID());
	}

	public void onInterrupt() {
		reset();
		System.out.println("INTERRUPTED MPM ID " + talon.getDeviceID());
	}

	/**
	 * Fill the Talon's top-level buffer with a given motion profile
	 * 
	 * @param gainsProfile
	 */
	private void fillTalonWithMotionProfile(int gainsProfile) {

		CANTalon.TrajectoryPoint point = new CANTalon.TrajectoryPoint();

		talon.clearMotionProfileTrajectories();

		for (int i = 0; i < numPoints; ++i) {
			point.position = profile[i][0];
			point.velocity = profile[i][1];
			point.timeDurMs = (int) profile[i][2];
			point.profileSlotSelect = gainsProfile;
			point.velocityOnly = false;
			point.zeroPos = false;
			if (i == 0) {
				point.zeroPos = true;
			}

			point.isLastPoint = false;
			if ((i + 1) == numPoints) {
				point.isLastPoint = true;
			}

			talon.pushMotionProfileTrajectory(point);
		}
	}

	/**
	 * @return the status object of the Talon being controlled
	 */
	public CANTalon.MotionProfileStatus getStatus() {
		return status;
	}

	public String getMode() {
		String str;
		switch ((int) currentMode) {
		case 0:
			str = "NUETRAL";
			break;
		case 1:
			str = "EXECUTE";
			break;
		case 2:
			str = "HOLD";
			break;
		default:
			str = "NOT SET";
			break;
		}
		return str;
	}

	/**
	 * @return {@code true} when the motion profile is finshed executing
	 */
	public boolean isFinished() {
		return finished;
	}

	/**
	 * Change the motion profile that will be executed
	 * 
	 * @param newProfile
	 *            the new profile to be executed
	 */
	public void changeMotionProfile(double[][] newProfile) {
		talon.set(TALON_NEUTRAL);
		profile = newProfile;
		numPoints = newProfile.length;
	}

	/**
	 * Swap motion profiles with another MotionProfileManager. Useful for
	 * running profiles in reverse.
	 * 
	 * @param m
	 *            MotionProfileManger to swap profiles with
	 */
	public void swapProfile(MotionProfileManager m) {
		double[][] temp = this.profile;
		this.profile = m.profile;
		m.profile = temp;
	}

	/**
	 * Class to periodically run processMotionProfileBuffer for the Talon
	 */
	class PeriodicRunnable implements java.lang.Runnable {
		public void run() {
			talon.processMotionProfileBuffer();
		}
	}

	Notifier notifier = new Notifier(new PeriodicRunnable());
}
