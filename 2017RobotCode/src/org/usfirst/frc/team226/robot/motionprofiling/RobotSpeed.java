package org.usfirst.frc.team226.robot.motionprofiling;

import org.usfirst.frc.team226.robot.Constants;

public enum RobotSpeed {
	SLOW(Constants.MAX_RPS * 0.4, Constants.MAX_RPS * 0.4, Constants.MAX_RPM * 0.4, Constants.MAX_RPM * 0.4), MEDIUM(
			Constants.MAX_RPS * 0.6, Constants.MAX_RPS * 0.6, Constants.MAX_RPM * 0.6,
			Constants.MAX_RPM * 0.6), FAST(Constants.MAX_RPS * 0.75, Constants.MAX_RPS * 0.75, Constants.MAX_RPM * 0.75,
					Constants.MAX_RPM * 0.75);

	private double maxVel;
	private double maxAccel;
	private double MMCruiseVel;
	private double MMCruiseAccel;

	RobotSpeed(double maxVel, double maxAccel, double MMCruiseVel, double MMCruiseAccel) {
		this.maxVel = maxVel;
		this.maxAccel = maxAccel;
		this.MMCruiseVel = MMCruiseVel;
		this.MMCruiseAccel = MMCruiseAccel;
	}

	public double getMaxVel() {
		return maxVel;
	}

	public double getMaxAccel() {
		return maxAccel;
	}

	public double getMMCruiseVel() {
		return MMCruiseVel;
	}

	public double getMMCruiseAccel() {
		return MMCruiseAccel;
	}

}
