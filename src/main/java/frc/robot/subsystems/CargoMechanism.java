package frc.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;

/**
 * The subsystem that intakes, holds, lifts, and shoots cargo. It includes both the "arm" that lifts the intake and the intake itself.
 */
public class CargoMechanism {
    private Joystick joystick;
    private int intakeChannel, outtakeChannel;
    private int raiseChannel, lowerChannel;
    private SpeedController arm, intakeLeft, intakeRight;
    private DigitalInput topSwitch, bottomSwitch;

    /**
     * State the parts of the subsystem that move.
     * It will also be necessary to state the parts that take user input.
     * @param arm the part that raises and lowers the entire mechanism.
     * @param intakeLeft associated with left intake wheel
     * @param intakeRight associated with right intake wheel
     */
    public CargoMechanism(SpeedController arm, SpeedController intakeLeft, SpeedController intakeRight) {
        this.arm = arm;
        this.intakeLeft = intakeLeft;
        this.intakeRight = intakeRight;
    }
    /**
     * State the parts of the subsystem that move and the parts that take user input.
     * @param arm the part that raises and lowers the entire mechanism.
     * @param intakeLeft associated with left intake wheel
     * @param intakeRight associated with right intake wheel
     * @param joystick the joystick used to control this subsystem
     * @param intakeChannel the channel on the joystick that tells the subsystem to intake
     * @param outtakeChannel the channel on the joystick that tells the subsystem to outtake
     * @param raiseChannel the channel on the joystick that tells the arm to raise the subsystem upwards
     * @param lowerChannel the channel on the joystick that tells the arm to lower the subsystem downwards
     */
    public CargoMechanism(SpeedController arm, SpeedController intakeLeft, SpeedController intakeRight, Joystick joystick, int intakeChannel, int outtakeChannel, int raiseChannel, int lowerChannel) {
        this(arm, intakeLeft, intakeRight);
        setChannels(joystick, intakeChannel, outtakeChannel, raiseChannel, lowerChannel);
    }
    /**
     * State the parts of the subsystem that move, the parts that take user input, 
     * and the sensors.
     * @param arm the part that raises and lowers the entire mechanism.
     * @param intakeLeft associated with left intake wheel
     * @param intakeRight associated with right intake wheel
     * @param joystick the joystick used to control this subsystem
     * @param intakeChannel the channel on the joystick that tells the subsystem to intake
     * @param outtakeChannel the channel on the joystick that tells the subsystem to outtake
     * @param raiseChannel the channel on the joystick that tells the arm to raise the subsystem upwards
     * @param lowerChannel the channel on the joystick that tells the arm to lower the subsystem downwards
     * @param topSwitch the input from the top limit switch
     * @param bottomSwitch the input from the bottom limit switch
     */
    public CargoMechanism(SpeedController arm, SpeedController intakeLeft, SpeedController intakeRight, Joystick joystick, int intakeChannel, int outtakeChannel, int raiseChannel, int lowerChannel, DigitalInput topSwitch, DigitalInput bottomSwitch) {
        this(arm, intakeLeft, intakeRight);
        setChannels(joystick, intakeChannel, outtakeChannel, raiseChannel, lowerChannel);
        setSensors(topSwitch, bottomSwitch);
    }
    
    /**
     * Set the joystick channels that control this subsystem.
     * @param joystick the joystick used to control this subsystem
     * @param intakeChannel the channel on the joystick that tells the subsystem to intake
     * @param outtakeChannel the channel on the joystick that tells the subsystem to outtake
     * @param raiseChannel the channel on the joystick that tells the arm to raise the subsystem upwards
     * @param lowerChannel the channel on the joystick that tells the arm to lower the subsystem downwards
     */
    public void setChannels(Joystick joystick, int intakeChannel, int outtakeChannel, int raiseChannel, int lowerChannel) {
        this.joystick = joystick;
        this.intakeChannel = intakeChannel;
        this.outtakeChannel = outtakeChannel;
        this.raiseChannel = raiseChannel;
        this.lowerChannel = lowerChannel;
    }
    /**
     * State the parts of the subsystem that move, the parts that take user input, 
     * and the sensors.
     * @param topSwitch the input from the top limit switch
     * @param bottomSwitch the input from the bottom limit switch
     */
    public void setSensors(DigitalInput topSwitch, DigitalInput bottomSwitch) {
        this.topSwitch = topSwitch;
        this.bottomSwitch = bottomSwitch;
    }


    /**
     * Try to raise the arm. If the arm appears to be lifted to maximum height,
     * raising the arm is impossible.
     */
    public void raiseArm() {
        if (topSwitch.get()) { //arm appears to be lifted to max height
            arm.set(0.1);
            // stops arm angle motor at top
        } else {
            arm.set(.7); // set to 0.7 because it is working against gravity
            // arm angle goes up when Y is held
        }
    }
    /**
     * Try to lower the arm. If the arm appears to be lifted to minimum height,
     * lowering the arm is impossible.
     */
    public void lowerArm() {
        if (bottomSwitch.get()) { //arm appears to be lowered to min height
            arm.set(0);
            // stops arm angle motor at bottom
        } else {
            arm.set(-.4);// set to 0.4 because its working with gravity
        }
    }
    public void relaxArm() {
        arm.set(0.05);
    }
    

    /**
     * Attempt to intake cargo.
     */
    public void intake() {
        intakeLeft.set(-.5);
        intakeRight.set(.5);
    }
    /**
     * Attempt to outtake cargo.
     */
    public void outtake() {
        intakeLeft.set(1);  //Absolute value if intake value is set to 1 so that cargo will be emitted as rapidly as possible
        intakeRight.set(-1);
    }
    /**
     * Resist motion of the intake wheels that allows cargo to be dropped.
     */
    public void hold() {
        intakeLeft.set(-0.05);
        intakeRight.set(0.05);
    }


    /**
     * Method to be called every 20 milliseconds in TeleopPeriodic and/or AutonomousPeriodic
     * to ensure the subsystem updates its behavior according to user input and sensor input
     */
    public void run() {
        if(joystick.getRawButton(raiseChannel)) {
            raiseArm();
        }
        else if(joystick.getRawButton(lowerChannel)) {
            lowerArm();
        }
        else {
            relaxArm(); //edit relaxArm() if you find the arm drifts up when it should hold still
        }

        if(joystick.getRawButton(intakeChannel)) {
            intake();
        }
        else if(joystick.getRawButton(outtakeChannel)) {
            outtake();
        }
        else {
            hold();
        }
    }
}