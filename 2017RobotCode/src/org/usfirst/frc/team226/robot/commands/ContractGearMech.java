
package org.usfirst.frc.team226.robot.commands;

import org.usfirst.frc.team226.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ContractGearMech extends Command {

	public ContractGearMech() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.gearMech);
		setTimeout(0);
	}
	
	public ContractGearMech(double delay) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.gearMech);
		setTimeout(delay);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return isTimedOut();
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.gearMech.contractGearMech();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}