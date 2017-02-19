package org.usfirst.frc2337.MotionMagicCommandBasedExample.commands;
import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc2337.MotionMagicCommandBasedExample.Instrum;
import org.usfirst.frc2337.MotionMagicCommandBasedExample.Robot;
import org.usfirst.frc2337.MotionMagicCommandBasedExample.RobotMap;

import com.ctre.CANTalon.TalonControlMode;

/**
 *
 */
public class MotionMagic extends Command {


    private double targetPos;

	public MotionMagic() {
        requires(Robot.chassis);
        targetPos = 10;

    }
    
    public MotionMagic(double revolutions) {
        requires(Robot.chassis);
        targetPos = revolutions;
        
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
		RobotMap.chassisCANTalonFrontLeft.changeControlMode(TalonControlMode.MotionMagic);
		RobotMap.chassisCANTalonFrontRight.changeControlMode(TalonControlMode.MotionMagic);
    	
		/* calculate the percent motor output */
		double motorOutput = RobotMap.chassisCANTalonFrontLeft.getOutputVoltage() / RobotMap.chassisCANTalonFrontLeft.getBusVoltage();
		/* prepare line to print */
		RobotMap._sb.append("\tout:");
		RobotMap._sb.append(motorOutput);
		RobotMap._sb.append("\tspd:");
		RobotMap._sb.append(RobotMap.chassisCANTalonFrontLeft.getSpeed());
		
		
		// IN ROBOR MAP OR INDIVIDUALLY??? XXX
		/* set acceleration and vcruise velocity - see documentation */
		RobotMap.chassisCANTalonFrontLeft.setMotionMagicCruiseVelocity(600);
		RobotMap.chassisCANTalonFrontRight.setMotionMagicCruiseVelocity(600);
		RobotMap.chassisCANTalonFrontLeft.setMotionMagicAcceleration(100);
		RobotMap.chassisCANTalonFrontRight.setMotionMagicAcceleration(100);
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
		/* Motion Magic */
		
		RobotMap.chassisCANTalonFrontLeft.set(this.targetPos); /* Rotations in either direction */
		RobotMap.chassisCANTalonFrontRight.set(this.targetPos);

		/* append more signals to print when in speed mode. */
		RobotMap._sb.append("\terr:");
		RobotMap._sb.append(RobotMap.chassisCANTalonFrontLeft.getClosedLoopError());
		RobotMap._sb.append("\ttrg:");
		RobotMap._sb.append(targetPos);
		
		
		
		/* instrumentation */
		Instrum.Process(RobotMap.chassisCANTalonFrontLeft, RobotMap._sb);
		
		
		//  not implemented yet...
		//SmartDashboard.putNumber("ActTrajVelocity", RobotMap.chassisCANTalonFrontLeft.getMotionMagicActTrajVelocity());
		//SmartDashboard.putNumber("ActTrajPosition", RobotMap.chassisCANTalonFrontLeft.getMotionMagicActTrajPosition());
		
		
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
		RobotMap.chassisCANTalonFrontLeft.changeControlMode(TalonControlMode.PercentVbus);
		RobotMap.chassisCANTalonFrontRight.changeControlMode(TalonControlMode.PercentVbus);
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
