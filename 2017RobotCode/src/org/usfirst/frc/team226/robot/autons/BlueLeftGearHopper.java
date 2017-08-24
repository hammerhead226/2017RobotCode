package org.usfirst.frc.team226.robot.autons;

import org.usfirst.frc.team226.robot.Constants;
import org.usfirst.frc.team226.robot.commands.ContractGearMech;
import org.usfirst.frc.team226.robot.commands.DriveClimberIntake;
import org.usfirst.frc.team226.robot.commands.ExecuteProfile;
import org.usfirst.frc.team226.robot.commands.ReleaseIntake;
import org.usfirst.frc.team226.robot.commands.SetShooterSpeedRPM;
import org.usfirst.frc.team226.robot.motionprofiling.Profiles;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class BlueLeftGearHopper extends CommandGroup {

    public BlueLeftGearHopper() {
    	addSequential(new ExecuteProfile(Profiles.blueLeftGear_Left, Profiles.blueLeftGear_Right, true, false));
//    	addSequential(new ExpandGearMech());
    	addSequential(new ReleaseIntake());
    	addParallel(new ExecuteProfile(Profiles.blueHopperFromLeftGear_Left, Profiles.blueHopperFromLeftGear_Right, false, false));
    	addParallel(new ContractGearMech());
    	addParallel(new SetShooterSpeedRPM(Constants.SHOOTER_SETPOINT, 10));
//    	addParallel(new DriveFeeder(7, 1.7));
    	addSequential(new DriveClimberIntake(10));
    }
}
