package org.usfirst.frc.team226.robot.autons;

import org.usfirst.frc.team226.robot.Constants;
import org.usfirst.frc.team226.robot.commands.ContractGearMech;
import org.usfirst.frc.team226.robot.commands.DriveFeeder;
import org.usfirst.frc.team226.robot.commands.ExpandGearMech;
import org.usfirst.frc.team226.robot.commands.MotionMagicMove;
import org.usfirst.frc.team226.robot.commands.ReleaseIntake;
import org.usfirst.frc.team226.robot.commands.SetShooterSpeedRPM;
import org.usfirst.frc.team226.robot.motionprofiling.RobotSpeed;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class MidGear extends CommandGroup {

    public MidGear() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.
    	addSequential(new MotionMagicMove(RobotSpeed.SLOW, Constants.MID_GEAR_DISTANCE, true, false));
    	addParallel(new ExpandGearMech());
    	addSequential(new ReleaseIntake());
    	addSequential(new MotionMagicMove(RobotSpeed.SLOW, Constants.MID_GEAR_DISTANCE, false, false));
    	addParallel(new ContractGearMech());
    	addParallel	(new SetShooterSpeedRPM(3650, 10));
    	addSequential(new DriveFeeder(10, 1.7));
    }
}
