package org.usfirst.frc.team226.robot.extlib;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

public class LeftMagEncoderVelocityMimic implements PIDSource {
	
	private CANTalon ct;
	
	private PIDSourceType st;
	
	public LeftMagEncoderVelocityMimic(CANTalon talon, PIDSourceType st) {
		ct = talon;
		this.st = st;
	}

	@Override
	public void setPIDSourceType(PIDSourceType pidSource) {
		// TODO Auto-generated method stub
		this.st = pidSource;
	}

	@Override
	public PIDSourceType getPIDSourceType() {
		// TODO Auto-generated method stub
		return this.st;
	}

	@Override
	public double pidGet() {
		// TODO Auto-generated method stub
		return ct.getEncVelocity() * (600.0/4096.0);
	}
	
	
}
