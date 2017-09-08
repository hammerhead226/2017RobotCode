
package org.usfirst.frc.team226.robot;

import org.usfirst.frc.team226.robot.autons.BlueLeftGearHopper;
import org.usfirst.frc.team226.robot.autons.BlueLeftGearTeleopPrep;
import org.usfirst.frc.team226.robot.autons.MidGear;
import org.usfirst.frc.team226.robot.autons.RedRightGearHopper;
import org.usfirst.frc.team226.robot.autons.RedRightGearTeleopPrep;
import org.usfirst.frc.team226.robot.subsystems.ActiveFloor;
import org.usfirst.frc.team226.robot.subsystems.ClimberIntake;
import org.usfirst.frc.team226.robot.subsystems.DriveTrain;
import org.usfirst.frc.team226.robot.subsystems.Feeder;
import org.usfirst.frc.team226.robot.subsystems.GearMech;
import org.usfirst.frc.team226.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.ControllerPower;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static final DriveTrain driveTrain = new DriveTrain();
	public static final ClimberIntake climberIntake = new ClimberIntake();
	public static final GearMech gearMech = new GearMech();
	public static final Shooter shooter = new Shooter();
	public static final ActiveFloor activeFloor = new ActiveFloor();
	public static final Feeder feeder = new Feeder();
	public static OI oi;

	Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();

	public static NetworkTable sharklogTable;

	public static NetworkTable getSharkLogTable() {
		return sharklogTable;
	}
	
	@Override
	public void robotPeriodic() {
		
		if (oi.manip.getBButtonPressed()) {
			Constants.SHOOTER_SETPOINT += 25;
			Timer.delay(0.2);
		}
		else if (oi.manip.getXButtonPressed()) {
			Constants.SHOOTER_SETPOINT -= 25;
			Timer.delay(0.2);
		}
		
		SmartDashboard.putNumber("Shooter Setpoint", Constants.SHOOTER_SETPOINT);
	}

	public void robotSharkLog() {
		sharklogTable.putNumber("3.3Voltage", ControllerPower.getVoltage3V3());
		sharklogTable.putNumber("5Voltage", ControllerPower.getVoltage5V());
		sharklogTable.putNumber("6Voltage", ControllerPower.getVoltage6V());
		sharklogTable.putNumber("3.3Current", ControllerPower.getCurrent3V3());
		sharklogTable.putNumber("5Current", ControllerPower.getCurrent5V());
		sharklogTable.putNumber("6Current", ControllerPower.getCurrent6V());
		sharklogTable.putNumber("BatteryVoltage", DriverStation.getInstance().getBatteryVoltage());
		sharklogTable.putBoolean("BrownedOut", DriverStation.getInstance().isBrownedOut());

		sharklogTable.putBoolean("TeleopEnabled", DriverStation.getInstance().isOperatorControl());
		sharklogTable.putBoolean("AutonEnabled", DriverStation.getInstance().isAutonomous());

		sharklogTable.putNumber("MatchTime", Timer.getMatchTime());
	}

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		oi = new OI();
		chooser.addDefault("Mid Gear", new MidGear());
		chooser.addObject("Red Right Gear + Hopper", new RedRightGearHopper());
		chooser.addObject("Red Right Gear + Teleop Prep", new RedRightGearTeleopPrep());
		chooser.addDefault("Blue Left Gear + Hopper", new BlueLeftGearHopper());
		chooser.addObject("Blue Left Gear + Teleop Prep", new BlueLeftGearTeleopPrep());
		SmartDashboard.putData("Auto mode", chooser);
		sharklogTable = NetworkTable.getTable("sharklog");
	}
	

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		autonomousCommand = chooser.getSelected();

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null)
			autonomousCommand.cancel();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}
}
