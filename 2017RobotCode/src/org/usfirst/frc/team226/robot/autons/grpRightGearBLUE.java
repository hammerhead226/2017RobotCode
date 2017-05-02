package org.usfirst.frc.team226.robot.autons;

import org.usfirst.frc.team226.robot.commands.cmdContractGearMech;
import org.usfirst.frc.team226.robot.commands.cmdExpandGearMech;
import org.usfirst.frc.team226.robot.commands.cmdExpandIntake;
import org.usfirst.frc.team226.robot.commands.cmdPIDTurnToAngle;
import org.usfirst.frc.team226.robot.commands.cmdStraightDrive;
import org.usfirst.frc.team226.robot.commands.cmdWait;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class grpRightGearBLUE extends CommandGroup {

    public grpRightGearBLUE() {
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
    	addSequential(new cmdStraightDrive(-73, 0, 0.6, 0.50), 2);
    	addSequential(new cmdPIDTurnToAngle(-65, 0.6), 2.0);
    	addSequential(new cmdStraightDrive(-45, 0, 0.6, 0.50), 2);
    	addSequential(new cmdStraightDrive(-12, 0, 0.4, 0.45), 1.5);
    	
    	addSequential(new cmdExpandGearMech());
    	addSequential(new cmdWait(0.5));
    	addSequential(new cmdStraightDrive(20, 0, 0.7, 0.6), 1.5);
    	addParallel(new cmdContractGearMech());
    	
    	//teleop prep
    	addSequential(new cmdPIDTurnToAngle(70, 0.6), 1);
    	addSequential(new cmdStraightDrive(-150, 0, 0.7, 0.6), 3);
    }
}
