package frc.robot.vision;

import com.fasterxml.jackson.databind.ser.std.EnumSerializer;

import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.VisionConstants;
import frc.robot.vision.VisionClient.VisionClientException;

public class VisionSystem extends SubsystemBase {
    /**
     * TCP client
     */
    private VisionClient client;
    private PowerDistribution pdh;

    public VisionSystem(PowerDistribution pdh) {
        this.pdh = pdh;
        try {
            client = new VisionClient(VisionConstants.kVisionIPAddress, VisionConstants.kVisionPort);
            client.start(); // start vision client thread
            Shuffleboard.getTab("Vision").addNumber("Distance", client::getDistance);
            Shuffleboard.getTab("Vision").addNumber("Angle", client::getAngle);
        } catch (VisionClientException e) {
            e.printStackTrace();
        }
        this.setLightOff();
        // if (isVisionClientHavingProblems()) this.setLightOff();
        // else this.setLightOn();
    }

    /**
     * @return if the client is null
     */
    public boolean isVisionClientOperational() {
        return client != null;
    }
    
    public boolean isVisionClientHavingProblems() {
        return client == null;
    }
    
    /**
     * @return vision distance value; -1 if client is null
     */
    public double getDistance() {
        if (isVisionClientOperational()) return client.getDistance();
        else return -1;
    }

    /**
     * @return vision angle value; -360 if client is null
     * 
     */
    public double getAngle() {
        if (isVisionClientOperational()) return client.getAngle();
        else return -360;
    }

    /**
     * Prints out values from the vision client
     */
    public void printValues() {
        System.out.println(client.toString());
    }

    /**
     * @return true if vision light is on
     */
    public boolean isVisionLightOn() {
        return pdh.getSwitchableChannel();
    }
    
    /**
     * Sets the value of the light
     * @param on true for light on
     */
    public void setLight(boolean on) {
        pdh.setSwitchableChannel(on);
    }

    public void setLightOff() {
        pdh.setSwitchableChannel(false);
    }

    public void setLightOn() {
        pdh.setSwitchableChannel(true);
    }

    /**
     * Toggles the light on/off
     */
    public void toggleLight() {
        setLight(!pdh.getSwitchableChannel());
    }

    @Override
    public void periodic() {
        // if (client != null) {
        //     SmartDashboard.putNumber("Vision Distance", getDistance());
        //     SmartDashboard.putNumber("Vision Angle", getAngle());
        // }
    }
}
