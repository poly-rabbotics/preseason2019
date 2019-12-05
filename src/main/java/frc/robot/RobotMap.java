ppackage frc.robot;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedControllerGroup;

/**
 * The purpose of the RobotMap is to state what the parts of the 
 * robot are. That's it: we are just declaring and constructing every part
 * of the robot. There is supposed to be no logic in this file, which means
 * the file isn't supposed to do any math or decisionmaking.
 * 
 * This lets us to refer to the parts of the robot by what we called them in RobotMap.
 * So, we can call the hatch solenoid hatchSolenoid instead of new DoubleSolenoid(0, 3),
 * making the code more readable.
 */
public final class RobotMap {
    //JOYSTICKS
    public Joystick joyDrive0, joyManipulators1;


    //CARGO MECHANISM
    /**
     * The motor controller for raising/lowering the cargo mechanism
     */
    public static VictorSP cargoArm         = new VictorSP(4);
    /**
     * The motor controller for left cargo intake wheel
     */
    public static VictorSP cargoIntakeLeft  = new VictorSP(0);
    /**
     * The motor controller for right cargo intake wheel
     */
    public static VictorSP cargoIntakeRight = new VictorSP(1);

    /**
     * Top limit switch for the cargo mechanism
     */
     static DigitalInput cargoTopSwitch    = new DigitalInput(2);
     /**
      * Bottom limit switch for the cargo mechanism
      */
    public static DigitalInput cargoBottomSwitch = new DigitalInput(3);

    /**
     * Joystick channels used to control cargo mechanism
     */
    public static int  cargoIntakeChannel  = 6, 
                cargoOuttakeChannel = 5, 
                cargoRaiseChannel   = 4, 
                cargoLowerChannel   = 1;


    //HATCH 
    /**
     * Solenoid that controls the air cylinders that eject/outtake hatch
     */
    public static DoubleSolenoid hatchSolenoid = new DoubleSolenoid(0, 3);
    /**
     * Joystick channel used to outtake hatch
     */
    public static int hatchOuttakeChannel = 2;


    //CLIMB MECHANISM
    /**
     * Solenoid used to raise the robot off the ground
     */
    public static DoubleSolenoid    climbBackSolenoid   = new DoubleSolenoid(1, 4),
                                    climbFrontSolenoid  = new DoubleSolenoid(2, 5);
    /**
     * Wheel in secondary drivetrain in the rear climbing mechanism
     */
    public static VictorSP  climbLowWheel1 = new VictorSP(3),
                            climbLowWheel2 = new VictorSP(2);
    /**
     * Joystick channel
     */
    public static int   climbRaiseBackChannel    = 6, 
                        climbRaiseFrontChannel   = 5,
                        climbDriveForwardChannel = 0;

    
    //DRIVE
    /**
     * Group of speed controllers controlling the drive motors on one side of the robot
     */
    public static SpeedControllerGroup 
                driveLeftWheels = new SpeedControllerGroup(new Spark(8), new Spark(7)),
                driveRightWheels = new SpeedControllerGroup(new Spark(6), new Spark(5));
    /**
     * Joystick channels
     */
    public static int   driveVelocityChannel = 1,
                        driveTurnChannel     = 4;
}