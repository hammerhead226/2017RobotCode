package org.usfirst.frc.team226.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class grpRightGearAutonRED extends CommandGroup {

    public grpRightGearAutonRED() {
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
    	addSequential(new cmdPIDDriveInches(-61, 0.65));
    	addSequential(new cmdPIDTurnToAngle(-60, 0.6));
    	addSequential(new cmdPIDDriveInches(-70, 0.65));
    	addSequential(new cmdPIDDriveInches(-10, 0.45), 2);
    	addSequential(new cmdWait(2.0));
    	addSequential(new cmdPIDDriveInches(-15, 0.45), 2);
    	addSequential(new cmdPIDDriveInches(40, 0.6));
   	
    	
    }
}
