package org.usfirst.frc.team226.robot.autons;

import org.usfirst.frc.team226.robot.commands.ExecuteProfile;
import org.usfirst.frc.team226.robot.commands.ExpandGearMech;
import org.usfirst.frc.team226.robot.commands.ReleaseIntake;
import org.usfirst.frc.team226.robot.motionprofiling.Profiles;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class BlueLeftGearTeleopPrep extends CommandGroup {

    public BlueLeftGearTeleopPrep() {
    	addSequential(new ExecuteProfile(Profiles.blueLeftGear_Left, Profiles.blueLeftGear_Right, true, false));
    	addSequential(new ExpandGearMech());
    	addSequential(new ReleaseIntake());
    	addSequential(new ExecuteProfile(Profiles.blueLeftGearTeleopPrep_Left, Profiles.blueLeftGearTeleopPrep_Right, false, false));
    }
}
