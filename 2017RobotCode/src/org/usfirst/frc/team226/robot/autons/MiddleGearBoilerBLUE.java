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
import org.usfirst.frc.team226.robot.commands.cmdPIDLeftShooter;
import org.usfirst.frc.team226.robot.commands.cmdPIDRightShooter;
import org.usfirst.frc.team226.robot.commands.PIDTurnToAngle;
import org.usfirst.frc.team226.robot.commands.StraightDrive;
import org.usfirst.frc.team226.robot.commands.Wait;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class MiddleGearBoilerBLUE extends CommandGroup {

    public MiddleGearBoilerBLUE() {
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
    	
    	addSequential(new MiddleGearAuton());
    	addSequential(new PIDTurnToAngle(80, 0.80), 1.0);
    	addParallel(new cmdPIDLeftShooter(RobotMap.L_SHOOTER_SETPOINT));
    	addParallel(new cmdPIDRightShooter(RobotMap.R_SHOOTER_SETPOINT));
    	
    	addSequential(new StraightDrive(120, 0, 0.7, 0.55), 3.0);
    	addSequential(new StraightDrive(30, 0, 0.7, 0), 1.0);
    	
    	addParallel(new cmdCrunchHoppers(), 5);
    	addParallel(new cmdMoveLeftAgitator_button(10, 0, 0, 0.6), 5);
    	addParallel(new cmdMoveRightAgitator_button(10, 0, 0, 0.6), 5);
    	addParallel(new cmdMoveRightFeeder_button(), 5);
    	addSequential(new cmdMoveLeftFeeder_button(), 5);
    	
    	//teleop prep
    	addSequential(new StraightDrive(-20, 0, 0.7, 0.0), 1.5);
    	addSequential(new PIDTurnToAngle(-45, 0.7), 2);
    	addSequential(new StraightDrive(-150, 0, 0.8, 0.4));
    }
}
