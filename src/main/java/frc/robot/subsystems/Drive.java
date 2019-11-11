package frc.robot.subsystems;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Joystick;

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
    public Drive(SpeedControllerGroup left, SpeedControllerGroup right) {
        this.left = left;
        this.right = right;
        drive = new DifferentialDrive(left, right);
    }
    /**
     * State the parts of the subsystem that move and the parts that take user input.
     * @param left the group of motors in the left side of the drivetrain
     * @param right the group of motors in the right side of the drivetrain
     * @param joystick the joystick used to control this subsystem
     * @param velocityChannel the joystick channel that controls the forward-backward velocity of the robot.
     * @param turnChannel the joystick channel that controls the rotational velocity of the robot
     */
    public Drive(SpeedControllerGroup left, SpeedControllerGroup right, Joystick joystick, int velocityChannel, int turnChannel) {
        this(left, right);
        setChannels(joystick, velocityChannel, turnChannel);
    }
    
    /**
     * Set the joystick channels that control this subsystem.
     * @param joystick the joystick used to control this subsystem
     * @param velocityChannel the joystick channel that controls the forward-backward velocity of the robot.
     * @param turnChannel the joystick channel that controls the rotational velocity of the robot
     */
    public void setChannels(Joystick joystick, int velocityChannel, int turnChannel) {
        this.joystick = joystick;
        this.velocityChannel = velocityChannel;
        this.turnChannel = turnChannel;
    }

    
    /**
     * Method to be called every 20 milliseconds in TeleopPeriodic and/or AutonomousPeriodic
     * to ensure the subsystem updates its behavior according to user input and sensor input
     */
    public void run() {
        double velocityRequest = -joystick.getRawAxis(velocityChannel);
        double turnRequest = -joystick.getRawAxis(turnChannel);
        drive.arcadeDrive(velocityRequest, turnRequest);
    }
}