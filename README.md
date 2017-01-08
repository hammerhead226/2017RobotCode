# 2017RobotCode
Code for team 226's Steamworks robot.

## Development Software
#### JDK and Eclipse
1. Install the latest [Java SE Development Kit 8 (Windows x64)](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html).
2. Install the latest [Eclipse IDE for Java Developers (64 bit)](https://eclipse.org/downloads/eclipse-packages/).
3. Follow the [2017 FRC Control System](https://wpilib.screenstepslive.com/s/4485/m/13503/l/599679-installing-eclipse-c-java) Eclipse setup instructions **for Java only.**
	1. The code deployment error "roboRIO Image does not match plugin, allowed image version: ##" means you need to update your Eclipse plugins.
    2. Numerous "(class) cannot be resolved to a type" errors may indicate a bad project build path, you should re-create the project.
4. Install the [CTRE Toolsuite] (http://www.ctr-electronics.com/control-system/hro.html#product_tabs_technical_resources).
	1. Download the [latest build] (http://www.ctr-electronics.com/downloads/installers/CTRE%20Toolsuite%20v4.4.1.8.zip) of the installer (4.4.1.8).
5. Install the [Kauai Labs Libraries] (http://www.pdocs.kauailabs.com/navx-mxp/software/roborio-libraries/java/).
	1. Download the [latest build] (http://www.kauailabs.com/public_files/navx-mxp/navx-mxp.zip)
	
#### Installing National Instruments Software (FRC Driver Station)
1. Uninstall all existing National Instruments products from the "National Instruments Software" installer.
2. Follow the [Installing the FRC 2017 Update Suite] (https://wpilib.screenstepslive.com/s/4485/m/13503/l/599670-installing-the-frc-2017-update-suite-all-languages) install instructions.
	1. Install [FRC 2017 Update Suite](http://www.ni.com/download/first-robotics-software-2015/5112/en/) using the serial number from your most recent kit of parts, and activate the software when prompted.
    2. If asked for a password it is `&Full$team^Ahead!`.
3. Open FRC Driver Station and set your team number on the Setup tab.

#### Updating the RoboRIO
1. Follow the [Imaging your roboRIO] (https://wpilib.screenstepslive.com/s/4485/m/13503/l/144984-imaging-your-roborio) guide.
2. Follow the [Installing Java 8 on the roboRIO using the FRC roboRIO Java Installer] (https://wpilib.screenstepslive.com/s/4485/m/13503/l/599747-installing-java-8-on-the-roborio-using-the-frc-roborio-java-installer-java-only) guide.

#### Configuring the Robot Radio ([OpenMesh OM5P-AN] (https://www.amazon.com/Managed-Enabled-Wireless-Access-Point/dp/B00XU65LFA) or [OpenMesh OM5P-AC] (http://www.open-mesh.com/products/access-points/grp-om5p-ac-cloud-access-point.html))
1. Follow the [Programming your radio for home use] (https://wpilib.screenstepslive.com/s/4485/m/13503/l/144986?data-resolve-url=true&data-manual-id=13503) guide.
