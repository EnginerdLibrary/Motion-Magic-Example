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
	
	//final Joystick driverJoystick = Robot.oi.getJoystickDriver();
	
	public double speed;
	public double Kp = 0.03;
	public double yaw;
	private Joystick joystickMain = Robot.oi.joystickDriver;
	double turnOutput;
	double leftJoystick, turnJoystick, turnReduction;
	double absTurn, absSpeed, actualTurnMagnitude, actualTurn;
	double maxTurnFullSpeed;
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
    	
		leftJoystick = -joystickMain.getRawAxis(1);
		turnJoystick = joystickMain.getRawAxis(4);
		absTurn = Math.abs(turnJoystick);
		absSpeed = Math.abs(leftJoystick);
		
		turnReduction = (1 - maxTurnFullSpeed) * ((absTurn - deadband) / (1 - deadband));
    	actualTurnMagnitude = absTurn - (((absSpeed - deadband) / (1 - deadband)) * turnReduction);
    	
    	if (turnJoystick == 0) {
    		actualTurn = 0;
    	} else {
    		actualTurn = (absTurn/turnJoystick) * actualTurnMagnitude;
    	}
   	
    	Robot.chassis.arcadeDrive(leftJoystick, actualTurn);
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



