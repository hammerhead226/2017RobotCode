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
public class RedRightGearTeleopPrep extends CommandGroup {

    public RedRightGearTeleopPrep() {
    	addSequential(new ExecuteProfile(Profiles.redRightGear_Left, Profiles.redRightGear_Right, true, false));
//    	addSequential(new ExpandGearMech());
    	addSequential(new ReleaseIntake());
//    	addSequential(new ExecuteProfile(Profiles.redRightGearTeleopPrep_Left, Profiles.redRightGearTeleopPrep_Right, false, false));
    	addSequential(new MotionMagicMove(RobotSpeed.MEDIUM, 6, false, false));
    }
}
