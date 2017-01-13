# 2017RobotCode
Code for team 226's Steamworks robot.

## Development Software
#### JDK and Eclipse
1. Install the latest [Java SE Development Kit 8 (Windows x64)](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html).
2. Install the latest [Eclipse IDE for Java Developers (64 bit)](https://eclipse.org/downloads/eclipse-packages/).
3. Follow the [2017 FRC Control System](https://wpilib.screenstepslive.com/s/4485/m/13503/l/599679-installing-eclipse-c-java) Eclipse setup instructions **for Java only.**
    1. The code deployment error "roboRIO Image does not match plugin, allowed image version: ##" means you need to update your Eclipse plugins.
    2. Numerous "(class) cannot be resolved to a type" errors may indicate a bad project build path, you should re-create the project.
4. Install the latest [CTRE Libraries and Software for FRC](http://www.ctr-electronics.com/control-system/hro.html#product_tabs_technical_resources) (for the Talon SRX speed controller).
5. Install the latest build of [Kauai Labs Libraries](http://www.pdocs.kauailabs.com/navx-mxp/software/roborio-libraries/java/) (for the navX-MXP gyro/accelerometer).

#### Installing National Instruments Software (FRC Driver Station)
1. Strongly consider uninstalling all existing National Instruments products from the "National Instruments Software" installer.
2. Install the [FRC 2017 Update Suite](http://www.ni.com/download/first-robotics-software-2015/5112/en/) using the serial number from your most recent kit of parts, and activate the software when prompted.
    1. If asked for a password it is `&Full$team^Ahead!`.
    2. You may need to enable [.NET Framework 3.5](https://wpilib.screenstepslive.com/s/4485/m/13503/l/599670-installing-the-frc-2017-update-suite-all-languages).
3. Run "NI Update Service" ([manual download](http://search.ni.com/nisearch/app/main/p/bot/no/ap/tech/lang/en/pg/1/sn/catnav:du/q/ni%20update%20service/)) and install all critical updates, but no patches.
4. Open FRC Driver Station and set your team number on the Setup tab.

#### Importing the Eclipse Project
1. Switch your workspace to the cloned folder (where this README is).
2. Import the Java project into the workspace.
3. Troubleshooting:
    1. `Unable to find a javac compiler` / `com.sun.tools.javac.Main is not on the classpath` - you need to configure JDK in your build path (above WPILib instructions).
    2. `Property 'team-number' doesn't exist in this project` - follow the instructions in the error message.
    3. `The import java.* cannot be resolved` - you need to add "JRE System Library" to the project.

## Additional
#### Updating the roboRIO
1. Follow the [Imaging your roboRIO](https://wpilib.screenstepslive.com/s/4485/m/13503/l/144984-imaging-your-roborio) guide.
2. Follow the [Installing Java 8 on the roboRIO using the FRC roboRIO Java Installer](https://wpilib.screenstepslive.com/s/4485/m/13503/l/599747-installing-java-8-on-the-roborio-using-the-frc-roborio-java-installer-java-only) guide.

#### Configuring the Robot Radio
* [OpenMesh OM5P-AC / OM5P-AN](https://wpilib.screenstepslive.com/s/4485/m/13503/l/144986?data-resolve-url=true&data-manual-id=13503) (2017 / 2016)
* [D-Link DAP-1522](http://wpilib.screenstepslive.com/s/3120/m/8559/l/91405-programming-your-radio-for-home-use) (2015) (NI install with tools no longer available)
