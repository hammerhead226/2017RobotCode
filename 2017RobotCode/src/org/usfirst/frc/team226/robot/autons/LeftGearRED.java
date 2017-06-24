package org.usfirst.frc.team226.robot.autons;

import org.usfirst.frc.team226.robot.commands.ContractGearMech;
import org.usfirst.frc.team226.robot.commands.ExpandGearMech;
import org.usfirst.frc.team226.robot.commands.ExpandIntake;
import org.usfirst.frc.team226.robot.commands.PIDTurnToAngle;
import org.usfirst.frc.team226.robot.commands.StraightDrive;
import org.usfirst.frc.team226.robot.commands.Wait;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LeftGearRED extends CommandGroup {

    public LeftGearRED() {
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
    	addSequential(new StraightDrive(-73, 0, 0.6, 0.5), 2);
    	addSequential(new PIDTurnToAngle(65, 0.6), 2.0);
    	addSequential(new StraightDrive(-45, 0, 0.6, 0.5), 2);
    	addSequential(new StraightDrive(-12, 0, 0.4, 0.45), 1.5);
    	
    	addSequential(new ExpandGearMech());
    	addSequential(new Wait(0.5));
    	addSequential(new StraightDrive(20, 0, 0.7, 0.6), 1.5);
    	addParallel(new ContractGearMech());
    	
    	//teleop prep
    	addSequential(new PIDTurnToAngle(-70, 0.6), 1);
    	addSequential(new StraightDrive(-150, 0, 0.7, 0.6), 3);
    }
}
