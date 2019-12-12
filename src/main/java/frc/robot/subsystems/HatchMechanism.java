package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import frc.robot.controllers.UnoJoy2;
import frc.robot.RobotMap2;
/**
 * The hatch subsystem, which passively intakes hatches and actively outtakes (ejects) them.
 */
public class HatchMechanism {
    private Joystick joystick;
    private DoubleSolenoid solenoid;
    private int outtakeChannel;
    /**
     * State the parts of the subsystem that move.
     * It will also be necessary to state the parts that take user input.
     * @param solenoid the solenoid associated with the air cylinders that eject hatches from the robot
     */
    public HatchMechanism() {
        joystick = new Joystick(RobotMap2.joystickPort);
        solenoid = new DoubleSolenoid(RobotMap2.SolenoidPort1, RobotMap2.SolenoidPort2);
    }
    /**
     * State the parts of the subsystem that move and the parts that take user input.
     * @param solenoid the solenoid associated with the air cylinders that eject hatches from the robot
     * @param joystick the joystick that controls this subsystem
     * @param outtakeChannel the joystick channel that tells the mechanism to eject the hatch it is holding
     */


    /**
     * Eject a hatch from the robot
     */
    public void outtake() {
        solenoid.set(Value.kForward);
    }
    /**
     * Retract the air cylinders after using them to eject a hatch
     */
    public void retractCylinders() {
        solenoid.set(Value.kReverse);
    }

    
    /**
     * Method to be called every 20 milliseconds in TeleopPeriodic and/or AutonomousPeriodic
     * to ensure the subsystem updates its behavior according to user input and sensor input
     */
    public void run() {
        if (joystick.getRawButton(UnoJoy2.hatchOutButton)) {
            outtake();
        }
        else {
            retractCylinders();
        }
    }
}