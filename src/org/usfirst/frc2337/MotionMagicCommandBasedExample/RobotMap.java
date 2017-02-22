package org.usfirst.frc2337.MotionMagicCommandBasedExample;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SerialPort;

import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

    public static CANTalon chassisCANTalonFrontRight;
    public static CANTalon chassisCANTalonMiddleRight;
    public static CANTalon chassisCANTalonRearRight;
    public static CANTalon chassisCANTalonFrontLeft;
    public static CANTalon chassisCANTalonMiddleLeft;
    public static CANTalon chassisCANTalonRearLeft;
    
    public static RobotDrive chassisRobotDrive;
    
    public static AHRS gyro;
    
    public static StringBuilder _sb;


    public static void init() {
   
    	_sb = new StringBuilder();
    	
        chassisCANTalonFrontRight = new CANTalon(2);
        LiveWindow.addActuator("Chassis", "CAN Talon Front Right", chassisCANTalonFrontRight);
		/* first choose the sensor */
        chassisCANTalonFrontRight.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
        chassisCANTalonFrontRight.reverseSensor(true);
        chassisCANTalonFrontRight.setEncPosition(0);
        chassisCANTalonFrontRight.reverseOutput(false);
		// chassisCANTalonFrontRight.configEncoderCodesPerRev(XXX), // if using
		// FeedbackDevice.QuadEncoder
		// chassisCANTalonFrontRight.configPotentiometerTurns(XXX), // if using
		// FeedbackDevice.AnalogEncoder or AnalogPot

		/* set the peak and nominal outputs, 12V means full */
        chassisCANTalonFrontRight.configNominalOutputVoltage(+0.0f, -0.0f);
        chassisCANTalonFrontRight.configPeakOutputVoltage(+12.0f, -12.0f);
		/* set closed loop gains in slot0 - see documentation */
        chassisCANTalonFrontRight.setProfile(0);
        chassisCANTalonFrontRight.setF(0.2481);
        chassisCANTalonFrontRight.setP(3);
        chassisCANTalonFrontRight.setI(0.001);
        chassisCANTalonFrontRight.setD(30);
		/* set acceleration and vcruise velocity - see documentation */  //10.7 p.71  & p.97
        //....setting in command/subsystem....
        //chassisCANTalonFrontRight.setMotionMagicCruiseVelocity(0);
        //chassisCANTalonFrontRight.setMotionMagicAcceleration(0);
        
        chassisCANTalonMiddleRight = new CANTalon(3);
        chassisCANTalonMiddleRight.changeControlMode(TalonControlMode.Follower);
        chassisCANTalonMiddleRight.set(chassisCANTalonFrontRight.getDeviceID());
        chassisCANTalonMiddleRight.reverseOutput(false);
        
        chassisCANTalonRearRight = new CANTalon(4);
        chassisCANTalonRearRight.changeControlMode(TalonControlMode.Follower);
        chassisCANTalonRearRight.set(chassisCANTalonFrontRight.getDeviceID());
        chassisCANTalonRearRight.reverseOutput(false);
        
        
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
        //....setting in command/subsystem....
        //chassisCANTalonFrontLeft.setMotionMagicCruiseVelocity(0);
        //chassisCANTalonFrontLeft.setMotionMagicAcceleration(0);
        
        chassisCANTalonMiddleLeft = new CANTalon(14);
        chassisCANTalonMiddleLeft.changeControlMode(TalonControlMode.Follower);
        chassisCANTalonMiddleLeft.set(chassisCANTalonFrontLeft.getDeviceID());
        //chassisLefttMiddleTalon.reverseOutput(true);
        
        chassisCANTalonRearLeft = new CANTalon(15);
        chassisCANTalonRearLeft.changeControlMode(TalonControlMode.Follower);
        chassisCANTalonRearLeft.set(chassisCANTalonFrontLeft.getDeviceID());
        //chassisLefttRearTalon.reverseOutput(true);
        
        chassisRobotDrive = new RobotDrive(chassisCANTalonFrontRight, chassisCANTalonFrontLeft);
        
        chassisRobotDrive.setSafetyEnabled(true);
        chassisRobotDrive.setExpiration(0.1);
        chassisRobotDrive.setSensitivity(0.5);
        chassisRobotDrive.setMaxOutput(1.0);
        
        //chassisRobotDrive.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);
        
        try {
            /* Communicate w/navX MXP via the MXP SPI Bus.                                     */
            /* Alternatively:  I2C.Port.kMXP, SerialPort.Port.kMXP or SerialPort.Port.kUSB     */
            /* See http://navx-mxp.kauailabs.com/guidance/selecting-an-interface/ for details. */
        	gyro = new AHRS(SerialPort.Port.kMXP);
        } catch (RuntimeException ex ) {
            DriverStation.reportError("Instantiating navX-MXP failed:  " + ex.getMessage(), true);
        }
        

    }
}
