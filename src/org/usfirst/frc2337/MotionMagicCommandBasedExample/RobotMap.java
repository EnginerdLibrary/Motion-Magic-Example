package org.usfirst.frc2337.MotionMagicCommandBasedExample;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
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
    public static CANTalon chassisCANTalonFrontLeft;
    public static RobotDrive chassisRobotDrive21;
    
    public static AHRS chassisPID_gyro;
    
    public static StringBuilder _sb;


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
        chassisCANTalonFrontRight.setF(0.2481);
        chassisCANTalonFrontRight.setP(3);
        chassisCANTalonFrontRight.setI(0.001);
        chassisCANTalonFrontRight.setD(30);
		/* set acceleration and vcruise velocity - see documentation */  //10.7 p.71  & p.97
        //chassisCANTalonFrontRight.setMotionMagicCruiseVelocity(0);
        //chassisCANTalonFrontRight.setMotionMagicAcceleration(0);
        
        
        
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
        
        try {
            /* Communicate w/navX MXP via the MXP SPI Bus.                                     */
            /* Alternatively:  I2C.Port.kMXP, SerialPort.Port.kMXP or SerialPort.Port.kUSB     */
            /* See http://navx-mxp.kauailabs.com/guidance/selecting-an-interface/ for details. */
        	chassisPID_gyro = new AHRS(SerialPort.Port.kMXP);
        } catch (RuntimeException ex ) {
            DriverStation.reportError("Instantiating navX-MXP failed:  " + ex.getMessage(), true);
        }
        

    }
}
