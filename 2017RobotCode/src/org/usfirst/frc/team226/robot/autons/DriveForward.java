package org.usfirst.frc.team226.robot.autons;

import org.usfirst.frc.team226.robot.commands.MotionMagicMove;
import org.usfirst.frc.team226.robot.commands.ReleaseIntake;
import org.usfirst.frc.team226.robot.motionprofiling.RobotSpeed;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DriveForward extends CommandGroup {

    public DriveForward() {
    	addSequential(new MotionMagicMove(RobotSpeed.FAST, 100, false, false));
    	addSequential(new ReleaseIntake());
    }
}
