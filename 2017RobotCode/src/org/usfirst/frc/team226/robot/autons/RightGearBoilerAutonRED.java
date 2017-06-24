package org.usfirst.frc.team226.robot.autons;

import org.usfirst.frc.team226.robot.RobotMap;
import org.usfirst.frc.team226.robot.commands.ContractGearMech;
import org.usfirst.frc.team226.robot.commands.cmdCrunchHoppers;
import org.usfirst.frc.team226.robot.commands.ExpandGearMech;
import org.usfirst.frc.team226.robot.commands.ExpandIntake;
import org.usfirst.frc.team226.robot.commands.cmdMoveLeftAgitator_button;
import org.usfirst.frc.team226.robot.commands.cmdMoveLeftFeeder_button;
import org.usfirst.frc.team226.robot.commands.cmdMoveRightAgitator_button;
import org.usfirst.frc.team226.robot.commands.cmdMoveRightFeeder_button;
import org.usfirst.frc.team226.robot.commands.StraightDrive;
import org.usfirst.frc.team226.robot.commands.cmdPIDLeftShooter;
import org.usfirst.frc.team226.robot.commands.cmdPIDRightShooter;
import org.usfirst.frc.team226.robot.commands.PIDTurnToAngle;
import org.usfirst.frc.team226.robot.commands.StraightDrive;
import org.usfirst.frc.team226.robot.commands.Wait;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RightGearBoilerAutonRED extends CommandGroup {

    public RightGearBoilerAutonRED() {
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
    	
    	addParallel(new ExpandIntake());
    	addSequential(new StraightDrive(-74, 0, 0.7, 0.6), 2);
    	addSequential(new PIDTurnToAngle(-65, 0.6), 2.5);
    	addSequential(new StraightDrive(-73, 0, 0.7, 0.6), 2);
    	
    	addSequential(new ExpandGearMech());
    	addSequential(new Wait(0.5));
    	addSequential(new StraightDrive(20, 0, 0.7, 0.6), 1.5);
    	addParallel(new ContractGearMech());
    	
    	addParallel(new cmdPIDLeftShooter(RobotMap.L_SHOOTER_SETPOINT));
    	addParallel(new cmdPIDRightShooter(RobotMap.R_SHOOTER_SETPOINT));
    	addSequential(new PIDTurnToAngle(15, 0.65));
    	addSequential(new StraightDrive(140, 0, 0.9, 0.6), 1.5);
    	
    	addParallel(new cmdCrunchHoppers());
    	addParallel(new cmdMoveLeftAgitator_button(10, 0, 0, 0.6));
    	addParallel(new cmdMoveRightAgitator_button(10, 0, 0, 0.6));
    	addParallel(new cmdMoveRightFeeder_button());
    	addParallel(new cmdMoveLeftFeeder_button());
    	
    	//teleop prep
    	addSequential(new StraightDrive(-20, 0, 0.7, 0.0), 1.5);
    	addSequential(new PIDTurnToAngle(45, 0.7), 2);
    	addSequential(new StraightDrive(-150, 0, 0.8, 0.4));
    }
}
