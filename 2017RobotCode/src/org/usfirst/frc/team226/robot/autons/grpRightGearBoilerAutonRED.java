package org.usfirst.frc.team226.robot.autons;

import org.usfirst.frc.team226.robot.RobotMap;
import org.usfirst.frc.team226.robot.commands.cmdContractGearMech;
import org.usfirst.frc.team226.robot.commands.cmdExpandGearMech;
import org.usfirst.frc.team226.robot.commands.cmdMoveLeftAgitator_button;
import org.usfirst.frc.team226.robot.commands.cmdMoveLeftFeeder_button;
import org.usfirst.frc.team226.robot.commands.cmdMoveRightAgitator_button;
import org.usfirst.frc.team226.robot.commands.cmdMoveRightFeeder_button;
import org.usfirst.frc.team226.robot.commands.cmdStraightDrive;
import org.usfirst.frc.team226.robot.commands.cmdPIDLeftShooter;
import org.usfirst.frc.team226.robot.commands.cmdPIDRightShooter;
import org.usfirst.frc.team226.robot.commands.cmdPIDTurnToAngle;
import org.usfirst.frc.team226.robot.commands.cmdStraightDrive;
import org.usfirst.frc.team226.robot.commands.cmdWait;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class grpRightGearBoilerAutonRED extends CommandGroup {

    public grpRightGearBoilerAutonRED() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()s
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	addSequential(new cmdStraightDrive(-72, 0, 0.6, 0.65));
    	addSequential(new cmdPIDTurnToAngle(-60, 0.8), 2);
    	addSequential(new cmdStraightDrive(-47, 0, 0.6, 0.65));
    	addSequential(new cmdStraightDrive(-7, 0, 0.45, 0.65), 2);
    	
    	addSequential(new cmdExpandGearMech());
    	addSequential(new cmdWait(0.5));
    	
    	addSequential(new cmdStraightDrive(15, 0, 0.6, 0.65), 0.8);
    	addParallel(new cmdContractGearMech());
    	addSequential(new cmdStraightDrive(20, 0, 0.7, 0.65));
    	addParallel(new cmdPIDLeftShooter(RobotMap.L_SHOOTER_SETPOINT));
    	addParallel(new cmdPIDRightShooter(RobotMap.R_SHOOTER_SETPOINT));
    	addSequential(new cmdPIDTurnToAngle(-15,  0.65));
    	addSequential(new cmdStraightDrive(140, 0, 0.9, 0.65), 1.5);
    	
    	addParallel(new cmdMoveLeftAgitator_button(10, 0, 0, 0.6));
    	addParallel(new cmdMoveRightAgitator_button(10, 0, 0, 0.6));
    	addParallel(new cmdMoveRightFeeder_button());
    	addParallel(new cmdMoveLeftFeeder_button());
    }
}
