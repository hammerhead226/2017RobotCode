package org.usfirst.frc.team226.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class grpShooterAutonRED extends CommandGroup {

    public grpShooterAutonRED() {
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
    	
    	//DRIVE OUT, TURN, DRIVE IN, SHOOT
    	//expand robot
    	addParallel(new cmdExpandRobot());
    	//rev up shooters
    	addParallel(new cmdPIDRightShooter(3075));
    	addParallel(new cmdPIDLeftShooter(3150));
    	
    	//drive out, turn, drive up to boiler
    	addSequential(new cmdPIDDriveInches(-35, 0.6));
    	addSequential(new cmdPIDTurnToAngle(-45, 0.7));
    	addSequential(new cmdPIDDriveInches(36, 0.7), 1.5);
    	
    	//run agitators, feeders :: shoot
    	addParallel(new cmdMoveLeftAgitator_button(10, 0, 0, 0.55));
    	addParallel(new cmdMoveRightAgitator_button(10, 0, 0, 0.55));
    	addParallel(new cmdMoveRightFeeder_button());
    	addParallel(new cmdMoveLeftFeeder_button());

    	
    	
    }
}
