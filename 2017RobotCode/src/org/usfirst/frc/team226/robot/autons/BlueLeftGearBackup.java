package org.usfirst.frc.team226.robot.autons;

import org.usfirst.frc.team226.robot.commands.ExecuteProfile;
import org.usfirst.frc.team226.robot.commands.ExpandGearMech;
import org.usfirst.frc.team226.robot.commands.MotionMagicMove;
import org.usfirst.frc.team226.robot.commands.ReleaseIntake;
import org.usfirst.frc.team226.robot.motionprofiling.Profiles;
import org.usfirst.frc.team226.robot.motionprofiling.RobotSpeed;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class BlueLeftGearBackup extends CommandGroup {

    public BlueLeftGearBackup() {
    	addSequential(new ExecuteProfile(Profiles.blueLeftGear_Left, Profiles.blueLeftGear_Right, true, false));
    	addSequential(new ReleaseIntake());
    	//    	addSequential(new ExecuteProfile(Profiles.blueLeftGearTeleopPrep_Left, Profiles.blueLeftGearTeleopPrep_Right, false, false));
    	addSequential(new MotionMagicMove(RobotSpeed.MEDIUM, 6, false, false));
    	addSequential(new ExpandGearMech());
    }
}
