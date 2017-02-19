package org.usfirst.frc2337.MotionMagicCommandBasedExample.commands;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc2337.MotionMagicCommandBasedExample.Robot;
import org.usfirst.frc2337.MotionMagicCommandBasedExample.RobotMap;

import com.ctre.CANTalon.TalonControlMode;

/**
 *
 */
public class DriveNerdy extends Command {
	
	final Joystick driverJoystick = Robot.oi.getJoystickDriver();
	
	double deadband = 0.1;

	public DriveNerdy() {

        requires(Robot.chassis);

    }

    // Called just before this Command runs the first time
    protected void initialize() {

		/* Percent voltage mode */
    	RobotMap.chassisCANTalonFrontLeft.changeControlMode(TalonControlMode.PercentVbus);
    	RobotMap.chassisCANTalonFrontRight.changeControlMode(TalonControlMode.PercentVbus);
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
		double moveSpeed = driverJoystick.getRawAxis(1); //Left Y
		double turnSpeed = driverJoystick.getRawAxis(4); //Right X
		
		if (Math.abs(moveSpeed) < deadband) moveSpeed = 0;
		if (Math.abs(turnSpeed) < deadband) turnSpeed = 0;
		
		if (driverJoystick.getRawButton(3)) {
			moveSpeed = moveSpeed/2;
			turnSpeed = turnSpeed/2;
		}
		
    	Robot.chassis.arcadeDrive(moveSpeed, turnSpeed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}



