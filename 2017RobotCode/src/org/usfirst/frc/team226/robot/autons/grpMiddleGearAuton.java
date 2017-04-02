package org.usfirst.frc.team226.robot.autons;

import org.usfirst.frc.team226.robot.commands.cmdContractGearMech;
import org.usfirst.frc.team226.robot.commands.cmdExpandGearMech;
import org.usfirst.frc.team226.robot.commands.cmdExpandRobot;
import org.usfirst.frc.team226.robot.commands.cmdStraightDrive;
import org.usfirst.frc.team226.robot.commands.cmdWait;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class grpMiddleGearAuton extends CommandGroup {

    public grpMiddleGearAuton() {
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
    	addParallel(new cmdExpandRobot());
    	addSequential(new cmdStraightDrive(-60, 0, 0.65, 0.4));
    	addSequential(new cmdStraightDrive(-12, 0, 0.45, 0.65), 1.5);
    	
    	addParallel(new cmdExpandGearMech());
    	addSequential(new cmdWait(0.5));
    	
    	addSequential(new cmdStraightDrive(40, 0, 0.65, 0.65));
    	addSequential(new cmdContractGearMech());
    }
}
