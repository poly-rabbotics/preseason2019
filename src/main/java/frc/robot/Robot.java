/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

/**
 * This is our main robot code. From here, the classes from the other files are
 * instantiated and their methods are called, so this is the file that makes the
 * robot actually do things instead of just saying how it does things.
 */


package frc.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import frc.robot.subsystems.*;
import frc.robot.sensors.*;
import frc.robot.controllers.*;
import frc.robot.RobotMap;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends IterativeRobot {
  
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();
  //sensors
  PressureTransducer transducer;
  //subsystems
  CargoMechanism cargoMechanism;
  HatchMechanism hatchMechanism;
  Climb climb;
  Drive drive;
  //Joysticks
  Joystick joy0, joy1;

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    //Initialize CargoMechanism
    cargoMechanism = new CargoMechanism(RobotMap.cargoArm, RobotMap.cargoIntakeLeft, RobotMap.cargoIntakeRight);
    cargoMechanism.setSensors(RobotMap.cargoTopSwitch, RobotMap.cargoBottomSwitch);
    cargoMechanism.setChannels(joy1, 6, 5, 4, 1);
    //Initialize HatchMechanism
    hatchMechanism = new HatchMechanism(new DoubleSolenoid(0, 3));
    hatchMechanism.setChannels(joy1, 2);
    //Initialize Climb
    climb = new Climb(new DoubleSolenoid(1, 4), new DoubleSolenoid(2, 5), new VictorSP(3), new VictorSP(2));
    climb.setChannels(joy0, 6, 5, 4);
    //Initialize Drive
    
    drive = new Drive(RobotMap.driveLeftWheels, RobotMap.driveRightWheels);
    drive.setChannels(joy0, 1, 4);
    //Initialize PressureTransducer
    transducer = new PressureTransducer(3);
    //Code to send video to the driver station
    CameraServer.getInstance().startAutomaticCapture(0);
    CameraServer.getInstance().startAutomaticCapture(1);
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to
   * the switch structure below with additional strings. If using the
   * SendableChooser make sure to add them to the chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();
    // autoSelected = SmartDashboard.getString("Auto Selector",
    // defaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    teleopPeriodic();
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    cargoMechanism.run();
    hatchMechanism.run();
    climb.run();
    drive.run();
    transducer.reportToSmartDashboard();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}