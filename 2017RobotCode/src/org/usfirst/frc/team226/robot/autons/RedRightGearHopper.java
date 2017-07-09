package org.usfirst.frc.team226.robot.autons;

import org.usfirst.frc.team226.robot.Constants;
import org.usfirst.frc.team226.robot.RobotMap;
import org.usfirst.frc.team226.robot.commands.ContractGearMech;
import org.usfirst.frc.team226.robot.commands.DriveClimberIntake;
import org.usfirst.frc.team226.robot.commands.DriveFeeder;
import org.usfirst.frc.team226.robot.commands.ExecuteProfile;
import org.usfirst.frc.team226.robot.commands.ExpandGearMech;
import org.usfirst.frc.team226.robot.commands.ReleaseIntake;
import org.usfirst.frc.team226.robot.commands.SetShooterSpeedRPM;
import org.usfirst.frc.team226.robot.motionprofiling.Profiles;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RedRightGearHopper extends CommandGroup {

    public RedRightGearHopper() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.
    	
    	addSequential(new ExecuteProfile(Profiles.redRightGear_Left, Profiles.redRightGear_Right, true, false));
    	addSequential(new ExpandGearMech());
    	addSequential(new ReleaseIntake());
    	addParallel(new ExecuteProfile(Profiles.redHopperFromRightGear_Left, Profiles.redHopperFromRightGear_Right, false, false));
    	addParallel(new ContractGearMech());
    	addParallel(new SetShooterSpeedRPM(Constants.SHOOTER_SETPOINT, 10));
    	addParallel(new DriveFeeder(7, 2));
    	addSequential(new DriveClimberIntake(10));
    }
}
