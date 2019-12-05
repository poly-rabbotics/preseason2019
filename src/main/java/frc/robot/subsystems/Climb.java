package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Joystick;

/**
 * The subsystem that raises the robot off the ground and drives it forward onto the platform.
 */
public class Climb {
    private DoubleSolenoid back, front;
    private SpeedController lowWheel1, lowWheel2;
    private Joystick joystick;
    private int raiseBackChannel, raiseFrontChannel, driveForwardChannel;
    /**
     * State the parts of the subsystem that move.
     * It will also be necessary to state the parts that take user input.
     * @param back the solenoid associated with the rear climbing air cylinders
     * @param front the solenoid associated with the front climbing air cylinders
     * @param lowWheel1 one of the wheels that drives the elevated robot forward
     * @param lowWheel2 one of the wheels that drives the elevated robot forward
     */
    public Climb() {
        joystick = new Joystick(RobotMap2.joystickPort);
        back = new DoubleSolenoid(RobotMap2.backSolenoidPort);
        front = new DoubleSolenoid(RobotMap2.frontSolenoidPort);
        lowWheel1 = new SpeedController(RobotMap2.lowWheel1Port);
        lowWheel2 = new SpeedController(RobotMap2.lowWheel2Port);

    }
    /**
     * Set the joystick channels that control this subsystem.
     * @param joystick the joystick used to control the climbing subsystem
     * @param raiseBackChannel the joystick channel associated with raising the back of the robot
     * @param raiseFrontChannel the joystick channel associated with raising the front of the robot
     * @param driveForwardChannel the joystick channel associated with driving forward when the robot is elevated
     */
  


    /**
     * Raise the front of the robot
     */
    public void raiseFront() {
        front.set(Value.kForward);
    }
    /**
     * Raise the back of the robot
     */
    public void raiseBack() {
        back.set(Value.kForward);
    }
    /**
     * Lower the front of the robot.
     */
    public void lowerFront() {
        front.set(Value.kReverse);
    }
    /**
     * Lower the back of the robot.
     */
    public void lowerBack() {
        back.set(Value.kReverse);
    }
    /**
     * Drive forward while the robot is elevated.
     */
    public void driveForward() {
        lowWheel1.set(0.7);
        lowWheel2.set(0.7);
    }
    /**
     * Stop driving forward while the robot is elevated.
     */
    public void stop() {
        lowWheel1.set(0);
        lowWheel2.set(0);
    }
    
    
    /**
     * Method to be called every 20 milliseconds in TeleopPeriodic and/or AutonomousPeriodic
     * to ensure the subsystem updates its behavior according to user input and sensor input
     */
    public void run() {
        if(joystick.getRawButton(raiseBackChannel)){// right bumper
            raiseBack();
        }
        else {
          lowerBack();
        }//holding deploys back lift
      
        if(joystick.getRawButton(raiseFrontChannel)){// left bumper
          raiseFront();
        }
        else {
          lowerFront();
        }//holding deploys front lift

        if(joystick.getRawButton(driveForwardChannel) && joystick.getRawButton(raiseBackChannel) ){ 
            driveForward();
        }
        else {
            stop();
        } //if back lift is deployed and Y is pressed, Y drives low wheels forward
    }
}