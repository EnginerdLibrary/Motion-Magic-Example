// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc2337.MotionMagicCommandBasedExample;

// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;

import edu.wpi.first.wpilibj.RobotDrive;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static CANTalon chassisCANTalonFrontRight;
    public static CANTalon chassisCANTalonFrontLeft;
    public static RobotDrive chassisRobotDrive21;
    
    public static StringBuilder _sb;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public static void init() {
   
    	_sb = new StringBuilder();
    	
        chassisCANTalonFrontRight = new CANTalon(2);
        LiveWindow.addActuator("Chassis", "CAN Talon Front Right", chassisCANTalonFrontRight);
		/* first choose the sensor */
        chassisCANTalonFrontRight.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
        chassisCANTalonFrontRight.reverseSensor(true);
		// chassisCANTalonFrontRight.configEncoderCodesPerRev(XXX), // if using
		// FeedbackDevice.QuadEncoder
		// chassisCANTalonFrontRight.configPotentiometerTurns(XXX), // if using
		// FeedbackDevice.AnalogEncoder or AnalogPot

		/* set the peak and nominal outputs, 12V means full */
        chassisCANTalonFrontRight.configNominalOutputVoltage(+0.0f, -0.0f);
        chassisCANTalonFrontRight.configPeakOutputVoltage(+12.0f, -12.0f);
		/* set closed loop gains in slot0 - see documentation */
        chassisCANTalonFrontRight.setProfile(0);
        chassisCANTalonFrontRight.setF(0);
        chassisCANTalonFrontRight.setP(0);
        chassisCANTalonFrontRight.setI(0);
        chassisCANTalonFrontRight.setD(0);
		/* set acceleration and vcruise velocity - see documentation */
        chassisCANTalonFrontRight.setMotionMagicCruiseVelocity(0);
        chassisCANTalonFrontRight.setMotionMagicAcceleration(0);
        
        
        
        chassisCANTalonFrontLeft = new CANTalon(13);
        LiveWindow.addActuator("Chassis", "CAN Talon Front Left", chassisCANTalonFrontLeft);
        
		/* first choose the sensor */
        chassisCANTalonFrontLeft.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
        chassisCANTalonFrontLeft.reverseSensor(true);
		// chassisCANTalonFrontLeft.configEncoderCodesPerRev(XXX), // if using
		// FeedbackDevice.QuadEncoder
		// chassisCANTalonFrontLeft.configPotentiometerTurns(XXX), // if using
		// FeedbackDevice.AnalogEncoder or AnalogPot

		/* set the peak and nominal outputs, 12V means full */
        chassisCANTalonFrontLeft.configNominalOutputVoltage(+0.0f, -0.0f);
        chassisCANTalonFrontLeft.configPeakOutputVoltage(+12.0f, -12.0f);
		/* set closed loop gains in slot0 - see documentation */
        chassisCANTalonFrontLeft.setProfile(0);
        chassisCANTalonFrontLeft.setF(0);
        chassisCANTalonFrontLeft.setP(0);
        chassisCANTalonFrontLeft.setI(0);
        chassisCANTalonFrontLeft.setD(0);
		/* set acceleration and vcruise velocity - see documentation */
        chassisCANTalonFrontLeft.setMotionMagicCruiseVelocity(0);
        chassisCANTalonFrontLeft.setMotionMagicAcceleration(0);
        
        chassisRobotDrive21 = new RobotDrive(chassisCANTalonFrontRight, chassisCANTalonFrontLeft);
        
        chassisRobotDrive21.setSafetyEnabled(true);
        chassisRobotDrive21.setExpiration(0.1);
        chassisRobotDrive21.setSensitivity(0.5);
        chassisRobotDrive21.setMaxOutput(1.0);

        chassisRobotDrive21.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    }
}
