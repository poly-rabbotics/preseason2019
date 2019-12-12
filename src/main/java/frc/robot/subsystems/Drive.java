package frc.robot.subsystems;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Joystick;
import frc.robot.RobotMap2;

/**
 * The subsystem that drives the robot around the field (the west coast drivetrain)
 */
public class Drive {
    private SpeedControllerGroup left, right;
    private Joystick joystick;
    private int velocityChannel, turnChannel;

    private DifferentialDrive drive;
    

    /**
     * State the parts of the subsystem that move.
     * It will also be necessary to state the parts that take user input.
     * @param left the group of motors in the left side of the drivetrain
     * @param right the group of motors in the right side of the drivetrain
     */
    public Drive() {
       joystick = new Joystick(RobotMap2.joystickPort);
       left = new SpeedControllerGroup(new Spark(RobotMap2.left1Port), new Spark(RobotMap2.left2Port));
       right = new SpeedControllerGroup(new Spark(RobotMap2.right1Port), new Spark(RobotMap2.right2Port));
        drive = new DifferentialDrive(left, right);
    }
   
    
    /**
     * Method to be called every 20 milliseconds in TeleopPeriodic and/or AutonomousPeriodic
     * to ensure the subsystem updates its behavior according to user input and sensor input
     */
    public void run() {
       // double velocityRequest = -joystick.getRawAxis(joystick.getRawAxis());
        //double turnRequest = -joystick.getRawAxis(joystick.getRawAxis());
        //drive.arcadeDrive(velocityRequest, turnRequest);
    }
}