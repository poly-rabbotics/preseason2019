package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;

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
    public HatchMechanism(DoubleSolenoid solenoid) {
        this.solenoid = solenoid;
    }
    /**
     * State the parts of the subsystem that move and the parts that take user input.
     * @param solenoid the solenoid associated with the air cylinders that eject hatches from the robot
     * @param joystick the joystick that controls this subsystem
     * @param outtakeChannel the joystick channel that tells the mechanism to eject the hatch it is holding
     */
    public HatchMechanism(DoubleSolenoid solenoid, Joystick joystick, int outtakeChannel) {
        this(solenoid);
        setChannels(joystick, outtakeChannel);
    }
    
    /**
     * Set the joystick channels that control this subsystem.
     * @param joystick the joystick that controls this subsystem
     * @param outtakeChannel the joystick channel that tells the mechanism to eject the hatch it is holding
     */
    public void setChannels(Joystick joystick, int outtakeChannel) {
        this.joystick = joystick;
        this.outtakeChannel = outtakeChannel;
    }


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
        if (joystick.getRawButton(outtakeChannel)) {
            outtake();
        }
        else {
            retractCylinders();
        }
    }
}