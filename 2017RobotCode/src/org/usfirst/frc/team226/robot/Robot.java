
package org.usfirst.frc.team226.robot;

import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team226.robot.commands.grpBaselineAuton;
import org.usfirst.frc.team226.robot.commands.grpHopperAutonRED;
import org.usfirst.frc.team226.robot.commands.grpMiddleGearAuton;
import org.usfirst.frc.team226.robot.commands.grpShooterAutonRED;
import org.usfirst.frc.team226.robot.extlib.GRIPPipeline;
import org.usfirst.frc.team226.robot.subsystems.CameraTurret;
import org.usfirst.frc.team226.robot.subsystems.ClimberIntake;
import org.usfirst.frc.team226.robot.subsystems.DriveTrain;
import org.usfirst.frc.team226.robot.subsystems.LeftAgitator;
import org.usfirst.frc.team226.robot.subsystems.LeftFeeder;
import org.usfirst.frc.team226.robot.subsystems.LeftShooter;
import org.usfirst.frc.team226.robot.subsystems.PopoutServos;
import org.usfirst.frc.team226.robot.subsystems.RightAgitator;
import org.usfirst.frc.team226.robot.subsystems.RightFeeder;
import org.usfirst.frc.team226.robot.subsystems.RightShooter;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.ControllerPower;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Utility;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.vision.VisionThread;

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
	public static final LeftShooter leftShooter = new LeftShooter();
	public static final LeftFeeder leftFeeder = new LeftFeeder();
	public static final LeftAgitator leftAgitator = new LeftAgitator();
	public static final RightShooter rightShooter = new RightShooter();
	public static final RightFeeder rightFeeder = new RightFeeder();
	public static final RightAgitator rightAgitator = new RightAgitator();
	public static final CameraTurret cameraTurret = new CameraTurret();
	public static final PopoutServos popoutServos = new PopoutServos();
	public static OI oi;
	

	public static final int IMG_WIDTH = 320;
	public static final int IMG_HEIGHT = 240;
	
	public static int numContours = 0;
	public static int midpoint;
	public static double centerX;
	public static Rect cntRect;
	private VisionThread visionThread;
	private UsbCamera visionCam;
	private UsbCamera driverCam;
	private final Object imgLock = new Object();
	
	public static double angle;
	public static double theta; // horizontal FOV angle (calculated to be 57.175)
	public static double dist; // distance to the plane perpendicular to the camera that the target is on
	public static double delta; // pixel distance from perpendicular plane to target
	public static double lambda; // angle required to turn to face target (angle to make delta = 0);
	public static double turn;

	Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();

	public void robotLog() {
		SmartDashboard.putNumber("time", Timer.getFPGATimestamp());
		SmartDashboard.putNumber("time2", Utility.getFPGATime());
		SmartDashboard.putNumber("time3", Timer.getMatchTime());
		SmartDashboard.putNumber("3.3Voltage", ControllerPower.getVoltage3V3());
		SmartDashboard.putNumber("5Voltage", ControllerPower.getVoltage5V());
		SmartDashboard.putNumber("6Voltage", ControllerPower.getVoltage6V());
		SmartDashboard.putNumber("3.3Current", ControllerPower.getCurrent3V3());
		SmartDashboard.putNumber("5Current", ControllerPower.getCurrent5V());
		SmartDashboard.putNumber("6Current", ControllerPower.getCurrent6V());
		SmartDashboard.putNumber("BatteryVoltage", DriverStation.getInstance().getBatteryVoltage());
		SmartDashboard.putBoolean("BrownedOut", DriverStation.getInstance().isBrownedOut());
	}
	
	public void visionLog() {
		SmartDashboard.putNumber("Lambda", lambda);
		SmartDashboard.putNumber("Delta", delta);
		SmartDashboard.putNumber("Turn", turn);
		SmartDashboard.putNumber("DirPID error", driveTrain.dirController.getError());
		SmartDashboard.putNumber("DirPID error graph", driveTrain.dirController.getError());
		SmartDashboard.putNumber("DirPID direction", driveTrain.navX.pidGet());
		SmartDashboard.putNumber("Number of Contours", numContours);
    	SmartDashboard.putNumber("Midpoint of Gears", midpoint);
    	SmartDashboard.putNumber("Yaw", driveTrain.navX.getYaw());
    	SmartDashboard.putBoolean("Connected", driveTrain.navX.isConnected());
    	SmartDashboard.putBoolean("Calibrating", driveTrain.navX.isCalibrating());
	}
	

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		oi = new OI();
		 chooser.addObject("Middle Gear Auton", new grpMiddleGearAuton());
		 chooser.addObject("Baseline Cross", new grpBaselineAuton());
		 chooser.addObject("Red Shooter Auton", new grpShooterAutonRED());
		 chooser.addDefault("Red Hopper Auton", new grpHopperAutonRED());
		 SmartDashboard.putData("Auto mode", chooser);
		this.robotLog();
		
		// initialize the camera and the vision thread
		visionCam = CameraServer.getInstance().startAutomaticCapture(0);
		visionCam.setResolution(IMG_WIDTH, IMG_HEIGHT);
		visionCam.setExposureManual(0);
		
		driverCam = CameraServer.getInstance().startAutomaticCapture(1);
		driverCam.setResolution(640, 480);
	}

	public void visionInit() {
		if (visionThread == null || !visionThread.isAlive()) {
			visionThread = new VisionThread(visionCam, new GRIPPipeline(), pipeline -> {
				numContours = 0;
	
				if (!pipeline.filterContoursOutput().isEmpty()) {
	
					synchronized (imgLock) {
	
						cntRect = Imgproc.boundingRect(pipeline.filterContoursOutput().get(0));
						numContours = pipeline.filterContoursOutput().size();
	
						Rect[] gearCnt = new Rect[pipeline.filterContoursOutput().size()];
						double max1 = 0, max2 = 0;
						int idx1 = -1, idx2 = -1;
	
						for (int i = 0; i < pipeline.filterContoursOutput().size(); i++) {
							gearCnt[i] = Imgproc.boundingRect(pipeline.filterContoursOutput().get(i));
	//						double area = gearCnt[i].area();
	
							if (gearCnt[i].area() > max1) {
								max2 = max1;
								idx2 = idx1;
								max1 = gearCnt[i].area();
								idx1 = i;
							} else if (gearCnt[i].area() > max2) {
								max2 = gearCnt[i].area();
								idx2 = i;
							}
						}
	
						if (idx1 >= 0 && idx2 >= 0) {
							if (gearCnt[idx1].x < gearCnt[idx2].x) {
								midpoint = (gearCnt[idx1].x + (gearCnt[idx2].x + gearCnt[idx2].width)) / 2;
							} else {
								midpoint = ((gearCnt[idx1].x + gearCnt[idx1].width) + gearCnt[idx2].x) / 2;
							}
						}
						if (idx1 >= 0 && idx2 >= 0) {
							if (gearCnt[idx1].x < gearCnt[idx2].x) {
								midpoint = (gearCnt[idx1].x + (gearCnt[idx2].x + gearCnt[idx2].width)) / 2;
								SmartDashboard.putNumber("Tape 1 Width", gearCnt[idx1].width);
								SmartDashboard.putNumber("Tape 1 Left Axis", gearCnt[idx1].x);
								SmartDashboard.putNumber("Tape 1 Right Axis", gearCnt[idx1].x + gearCnt[idx1].width);
								SmartDashboard.putNumber("Gear Tape 2 Width", gearCnt[idx2].width);
								SmartDashboard.putNumber("Tape 2 Left Axis", gearCnt[idx2].x);
								SmartDashboard.putNumber("Tape 2 Right Axis", gearCnt[idx2].x + gearCnt[idx1].width);
							} else {
								midpoint = ((gearCnt[idx1].x + gearCnt[idx1].width) + gearCnt[idx2].x) / 2;
								SmartDashboard.putNumber("Tape 2 Width", gearCnt[idx2].width);
								SmartDashboard.putNumber("Tape 2 Left Axis", gearCnt[idx2].x);
								SmartDashboard.putNumber("Tape 2 Right Axis", gearCnt[idx2].x + gearCnt[idx1].width);
								SmartDashboard.putNumber("Tape 1 Width", gearCnt[idx1].width);
								SmartDashboard.putNumber("Tape 1 Left Axis", gearCnt[idx1].x);
								SmartDashboard.putNumber("Tape 1 Right Axis", gearCnt[idx1].x + gearCnt[idx1].width);
							}
						}
					}
				}
			});
			visionThread.start();
		}
	}		
	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {
		if(visionThread != null) {
			visionThread.interrupt();
		}
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
		visionInit();
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
		visionInit();
		if (autonomousCommand != null)
			autonomousCommand.cancel();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();

		this.robotLog();
//		this.visionLog();
//		climberIntake.log();
//		leftShooter.log();
//		rightShooter.log();
//		leftFeeder.log();
//		rightFeeder.log();
//		rightAgitator.log();
//		leftAgitator.log();
//		driveTrain.log();
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}
