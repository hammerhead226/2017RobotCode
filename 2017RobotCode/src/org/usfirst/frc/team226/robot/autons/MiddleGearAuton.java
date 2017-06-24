package org.usfirst.frc.team226.robot.autons;

import org.usfirst.frc.team226.robot.commands.ContractGearMech;
import org.usfirst.frc.team226.robot.commands.ExpandGearMech;
import org.usfirst.frc.team226.robot.commands.ExpandIntake;
import org.usfirst.frc.team226.robot.commands.StraightDrive;
import org.usfirst.frc.team226.robot.commands.Wait;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class MiddleGearAuton extends CommandGroup {

    public MiddleGearAuton() {
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
    	addParallel(new ExpandIntake());
    	addSequential(new StraightDrive(-60, 0, 0.65, 0.4));
    	addSequential(new StraightDrive(-12, 0, 0.45, 0.65), 1.5);
    	
    	addParallel(new ExpandGearMech());
    	addSequential(new Wait(0.5));
    	
    	addSequential(new StraightDrive(38, 0, 0.65, 0.65));
    	addSequential(new ContractGearMech());
    }
}
