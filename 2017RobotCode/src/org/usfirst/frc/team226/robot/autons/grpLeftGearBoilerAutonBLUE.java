package org.usfirst.frc.team226.robot.autons;

import org.usfirst.frc.team226.robot.RobotMap;
import org.usfirst.frc.team226.robot.commands.cmdContractGearMech;
import org.usfirst.frc.team226.robot.commands.cmdCrunchHoppers;
import org.usfirst.frc.team226.robot.commands.cmdExpandGearMech;
import org.usfirst.frc.team226.robot.commands.cmdExpandIntake;
import org.usfirst.frc.team226.robot.commands.cmdMoveLeftAgitator_button;
import org.usfirst.frc.team226.robot.commands.cmdMoveLeftFeeder_button;
import org.usfirst.frc.team226.robot.commands.cmdMoveRightAgitator_button;
import org.usfirst.frc.team226.robot.commands.cmdMoveRightFeeder_button;
import org.usfirst.frc.team226.robot.commands.cmdPIDLeftShooter;
import org.usfirst.frc.team226.robot.commands.cmdPIDRightShooter;
import org.usfirst.frc.team226.robot.commands.cmdPIDTurnToAngle;
import org.usfirst.frc.team226.robot.commands.cmdStraightDrive;
import org.usfirst.frc.team226.robot.commands.cmdWait;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class grpLeftGearBoilerAutonBLUE extends CommandGroup {

    public grpLeftGearBoilerAutonBLUE() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	addParallel(new cmdExpandIntake());
    	addSequential(new cmdStraightDrive(-74, 0, 0.7, 0.6), 2);
    	addSequential(new cmdPIDTurnToAngle(65, 0.6), 2.5);
    	addSequential(new cmdStraightDrive(-73, 0, 0.7, 0.6), 2);
    	
    	addSequential(new cmdExpandGearMech());
    	addSequential(new cmdWait(0.5));
    	addSequential(new cmdStraightDrive(20, 0, 0.7, 0.6), 1.5);
    	addParallel(new cmdContractGearMech());
    	
    	addParallel(new cmdPIDLeftShooter(RobotMap.L_SHOOTER_SETPOINT));
    	addParallel(new cmdPIDRightShooter(RobotMap.R_SHOOTER_SETPOINT));
    	addSequential(new cmdPIDTurnToAngle(-15,  0.65));
    	addSequential(new cmdStraightDrive(140, 0, 0.9, 0.6), 1.5);
    	
    	addParallel(new cmdCrunchHoppers());
    	addParallel(new cmdMoveLeftAgitator_button(10, 0, 0, 0.6));
    	addParallel(new cmdMoveRightAgitator_button(10, 0, 0, 0.6));
    	addParallel(new cmdMoveRightFeeder_button());
    	addSequential(new cmdMoveLeftFeeder_button());
    	
    	//teleop prep
    	addSequential(new cmdStraightDrive(-20, 0, 0.7, 0.0), 1.5);
    	addSequential(new cmdPIDTurnToAngle(-45, 0.7), 2);
    	addSequential(new cmdStraightDrive(-150, 0, 0.8, 0.4));
    }
}
