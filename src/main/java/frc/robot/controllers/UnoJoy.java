package frc.robot.controllers;

import edu.wpi.first.wpilibj.Joystick;

/**
 * A special kind of Joystick that is actually an Arduino Uno with firmware on it that makes it behave like a joystick.
 */
public class UnoJoy extends Joystick {
    /**
     * The error thrown when we attempt to access the value of a joystick channel that does not exist.
     */
    public class nonExistentChannelError extends Error {}
    /**
     * No different from the constructor of any Joystick.
     */
    public UnoJoy(int port) {
        super(port);
    }

    
    @Override
    public boolean getRawButton(int channel) throws nonExistentChannelError {
        switch (channel) {
            case 0: throw new nonExistentChannelError();
            case 1: throw new nonExistentChannelError();
            case 2: return super.getRawButton(4);
            case 3: return super.getRawButton(3);
            case 4: return super.getRawButton(1);
            case 5: return super.getRawButton(2);
            case 6: return super.getRawButton(5);
            case 7: return super.getRawButton(7);
            case 8: return super.getRawButton(11);
            case 9: return super.getRawButton(6);
            case 10:return super.getRawButton(8);
            case 11:return super.getRawButton(12);
            case 12:return super.getRawButton(9);
            case 13: throw new nonExistentChannelError();
            default: throw new nonExistentChannelError();
        }
    }

    /**
     * Get the value of a pushbutton (any Digital IO or DIO pin in the interval [2, 7])
     * @param index a number in the interval [0, 5], mapping directly to a DIO pin in the interval [2, 7]
     * @return whether the button is pressed
     */
    public boolean getRawPushbutton(int index) {
        return this.getRawButton(index + 2);
    }
    /**
     * Get the value of a toggle switch (any Digital IO or DIO pin in the interval [8, 12])
     * @param index a number in the interval [0, 4], mapping directly to a DIO pin in the interval [8, 12]
     * @return whether the toggle switch is flipped to a given position
     */
    public boolean getRawToggle(int index) {
        return this.getRawButton(index + 8);
    }
    /**
     * Get the position of a potentiometer.
     * @param index a number in the interval [0, 3], mapping directly to an Analog In (AI) pin in the interval [0, 3]
     * @return the position of the potentiometer as a number in the interval [-1, 1]
     */
    public double getPot(int index) {
        return getRawAxis(index);
    }
}