package org.usfirst.frc.team226.robot.commands;

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
    	addSequential(new cmdPIDDriveInches(-60, 0.55));
//    	addParallel(new cmdExpandRobot());
    	addSequential(new cmdPIDDriveInches(-9.5, 0.40), 3);
//    	addSequential(new cmdPIDDriveInchesCurved(1.25, 0.7, 0.2));
//    	addSequential(new cmdPIDDriveInchesCurved(-1.25, 0.7, 0.2));
    	addSequential(new cmdWait(2.0));
    	addSequential(new cmdPIDDriveInches(-10, 0.5));
    	addSequential(new cmdPIDDriveInches(40, 0.6));
    }
}
