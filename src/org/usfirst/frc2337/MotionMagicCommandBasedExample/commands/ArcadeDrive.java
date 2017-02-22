package org.usfirst.frc2337.MotionMagicCommandBasedExample.commands;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc2337.MotionMagicCommandBasedExample.Robot;
import org.usfirst.frc2337.MotionMagicCommandBasedExample.RobotMap;

import com.ctre.CANTalon.TalonControlMode;

/**
 *
 */
public class ArcadeDrive extends Command {
	
	private Joystick joystickMain = Robot.oi.getJoystickDriver();
	double deadband = 0.1;

    public ArcadeDrive() {

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
    	
	
    	//To Drive (using deadband)
    	double leftJoystick = -joystickMain.getRawAxis(1);
    	leftJoystick = ((Math.abs(leftJoystick) > deadband) ? leftJoystick : 0);
	    double turnJoystick = joystickMain.getRawAxis(4);
	    turnJoystick = ((Math.abs(turnJoystick) > deadband) ? turnJoystick : 0);
	    
	    Robot.chassis.arcadeDrive(leftJoystick, turnJoystick);
    
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
