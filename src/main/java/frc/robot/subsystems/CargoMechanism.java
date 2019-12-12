package frc.robot.subsystems;

import edu.wpi.first.wpilibj.VictorSP;
import frc.robot.controllers.UnoJoy2;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import frc.robot.RobotMap2;

/**
 * The subsystem that intakes, holds, lifts, and shoots cargo. It includes both the "arm" that lifts the intake and the intake itself.
 */
public class CargoMechanism {
    private final Joystick joystick;
    // private int intakeChannel, outtakeChannel;
    // private int raiseChannel, lowerChannel;
    private final VictorSP arm, intakeLeft, intakeRight;
    private final DigitalInput topSwitch, bottomSwitch;


    public CargoMechanism() {
        joystick = new Joystick(RobotMap2.joystickPort);
        arm = new VictorSP(RobotMap2.hatchPort);
        intakeLeft = new VictorSP(RobotMap2.intakeLeftPort);
        intakeRight = new VictorSP(RobotMap2.intakeRightPort);
        topSwitch = new DigitalInput(RobotMap2.topSwitchPort);
        bottomSwitch = new DigitalInput(RobotMap2.bottomSwitchPort);
    }

  

    /*public void setSensors(DigitalInput topSwitch, DigitalInput bottomSwitch) {
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
        if(joystick.getRawButton(UnoJoy2.armButtonUp)) {
            raiseArm();
        }
        else if(joystick.getRawButton(UnoJoy2.armButtonDown)) {
            lowerArm();
        }

        //fill each one in like the above with the correct buttons

        if(joystick.getRawButton(UnoJoy2.ballIntakeButton)) {
            intake();
        }
        else if(joystick.getRawButton(UnoJoy2.ballOuttakeButton)) {
            outtake();
        }
        else {
            hold();
        }
    }
}