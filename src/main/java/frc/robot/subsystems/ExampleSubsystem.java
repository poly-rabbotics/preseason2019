package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.DigitalInput;

/**
 * An example subsystem, which may be used as a template.
 */
public class ExampleSubsystem {
    private DoubleSolenoid solenoid;
    private SpeedController speedController;
    private Joystick joystick;
    private int channel;
    private DigitalInput sensor;

    /**
     * State the parts of the subsystem that move.
     * It will also be necessary to state the parts that take user input.
     * @param solenoid Some solenoid used in this subsystem
     * @param speedController Some speed controller used in this subsystem
     */
    public ExampleSubsystem(DoubleSolenoid solenoid, SpeedController speedController) {
        this.solenoid = solenoid;
        this.speedController = speedController;
    }
    /**
     * State the parts of the subsystem that move and the parts that take user input.
     * @param solenoid Some solenoid used in this subsystem
     * @param speedController Some speed controller used in this subsystem
     * @param joystick The joystick used to control this subsystem
     * @param channel The joystick channel used to control this subsystem
     */
    public ExampleSubsystem(DoubleSolenoid solenoid, SpeedController speedController, Joystick joystick, int channel) {
        this(solenoid, speedController);
        setChannels(joystick, channel);
    }
    /**
     * State the parts of the subsystem that move, the parts that take user input, 
     * and the sensors.
     * @param solenoid Some solenoid used in this subsystem
     * @param speedController Some speed controller used in this subsystem
     * @param joystick The joystick used to control this subsystem
     * @param channel The joystick channel used to control this subsystem
     * @param sensor Some sensor used in this subsystem
     */
    public ExampleSubsystem(DoubleSolenoid solenoid, SpeedController speedController, Joystick joystick, int channel, DigitalInput sensor) {
        this(solenoid, speedController, joystick, channel);
        setSensors(sensor);
    }

    /**
     * Set the sensors used by the subsystem.
     * @param sensor Some sensor used in this subsystem
     */
    public void setSensors(DigitalInput sensor) {
        this.sensor = sensor;
    }
    /**
     * Set the joystick channels that control this subsystem.
     * @param joystick
     * @param channel
     */
    public void setChannels(Joystick joystick, int channel) {
        this.joystick = joystick;
        this.channel = channel;
    }

    public void doSomething() {
        //do something
    }
    public void doSomethingElse() {
        //do something else
    }

    /**
     * Method to be called every 20 milliseconds in TeleopPeriodic and/or AutonomousPeriodic
     * to ensure the subsystem updates its behavior according to user input and sensor input
     */
    public void run() {
        if(joystick.getRawButton(channel)) {
            doSomething();
        }
        else {
            doSomethingElse();
        }
    }
}