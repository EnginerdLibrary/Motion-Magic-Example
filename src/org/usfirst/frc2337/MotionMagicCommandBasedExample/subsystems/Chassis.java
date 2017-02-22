package org.usfirst.frc2337.MotionMagicCommandBasedExample.subsystems;

import org.usfirst.frc2337.MotionMagicCommandBasedExample.RobotMap;
import org.usfirst.frc2337.MotionMagicCommandBasedExample.commands.*;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.RobotDrive;

import edu.wpi.first.wpilibj.command.Subsystem;


/**
 *
 */
public class Chassis extends Subsystem {

    private final CANTalon cANTalonFrontRight = RobotMap.chassisCANTalonFrontRight;
    //private final CANTalon cANTalonMiddleRight = RobotMap.chassisCANTalonMiddleRight;
    //private final CANTalon cANTalonRearRight = RobotMap.chassisCANTalonRearRight;
    private final CANTalon cANTalonFrontLeft = RobotMap.chassisCANTalonFrontLeft;
    //private final CANTalon cANTalonMiddleLeft = RobotMap.chassisCANTalonMiddleLeft;
    //private final CANTalon cANTalonRearLeft = RobotMap.chassisCANTalonRearLeft;
   
    private final RobotDrive robotDrive = RobotMap.chassisRobotDrive;

	private final AHRS gyro	= RobotMap.gyro;
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
    	setDefaultCommand(new ArcadeDrive());


        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }
    
	public void tankDrive(double leftValue, double rightValue) {
		robotDrive.tankDrive(leftValue, rightValue, true);
	}
	
	/**
	 * Uses RobotDrive to move using a speed and rotation rate.
	 * 
	 * @param speedValue The speed at which to move.
	 * @param turnValue The speed at which to turn.
	 */
	public void arcadeDrive(double speedValue, double turnValue) {
		robotDrive.arcadeDrive(speedValue, turnValue, true);
	}
	
	public void stopMotors() {
		robotDrive.stopMotor();  //errors???
	}
	
	public void setVBus() {
		cANTalonFrontRight.changeControlMode(TalonControlMode.PercentVbus);
		cANTalonFrontLeft.changeControlMode(TalonControlMode.PercentVbus);
	}
	
	public void setMotionMagic() {
		cANTalonFrontRight.changeControlMode(TalonControlMode.MotionMagic);
		cANTalonFrontLeft.changeControlMode(TalonControlMode.MotionMagic);
	}
	
	/**
	 * Sets the mode of the CANTalons to Brake or Coast
	 * @param mode true = brake, false = coast
	 */
	public void setBrakeMode(boolean mode) {
		cANTalonFrontRight.enableBrakeMode(mode);
		cANTalonFrontLeft.enableBrakeMode(mode);

	}
	
	
	/**
	 * Resets the current angle of the Gyro to 0.
	 */
	public void resetGyro() {
		gyro.reset();
	}
	/**
	 * Get yaw angle of gyro
	 * @return Yaw of gyro
	 */
    public double getGyroYaw() {
    	return gyro.getYaw();
    }
	
	public void resetDriveEncoder() {
		cANTalonFrontRight.setEncPosition(0);
		cANTalonFrontLeft.setEncPosition(0);
	}

	public int readLeftEncoder() {
		// return (leftEncoder.get());
		return cANTalonFrontLeft.getEncPosition();
    }
    
}

