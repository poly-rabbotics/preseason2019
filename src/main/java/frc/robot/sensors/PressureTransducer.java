package frc.robot.sensors;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.smartdashboard.*;

/**
 * Interprets the analog readings from the pressure transducer to determine PSI.
 */
public class PressureTransducer {
    /**
     * Source of the voltage measurement used to determine pressure
     */
    AnalogInput input;
    /**
     * @param port the number of the analog port on the RoboRIO
     */
    public PressureTransducer(int port) {
        input = new AnalogInput(port);
    }
    /**
     * Get PSI based on voltage measurement
     * @return the air pressure at the location of the transducer in pounds per square inch (PSI)
     */
    public double getPSI() {
        return 187.0 * input.getVoltage() / 5.0 + 15.0;
    }
    /**
     * Put the PSI measurement to the SmartDashboard
     */
    public void reportToSmartDashboard() {
        SmartDashboard.putNumber("Storage pressure: ", getPSI());
    }
}