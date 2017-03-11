package org.usfirst.frc.team226.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class grpRedAllianceHopperAuton extends CommandGroup {

    public grpRedAllianceHopperAuton() {
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
    	addSequential(new cmdPIDDriveInches(90, 0.80)); //93,0.8
    	addSequential(new cmdPIDDriveInchesCurved(41, 0.80, 0.42)); //40, 0.8, 0.42
    	addSequential(new cmdPIDDriveInches(9, 0.5));
    	addSequential(new cmdWait(1));
    	addSequential(new cmdPIDDriveInchesCurved(-35, 0.45, 0.8));
//    	
    }
}
