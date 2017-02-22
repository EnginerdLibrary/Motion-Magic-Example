package org.usfirst.frc2337.MotionMagicCommandBasedExample.commands;
import edu.wpi.first.wpilibj.command.CommandGroup;


/**
 *
 */
public class AutonomousCommand extends CommandGroup {


    public AutonomousCommand() {

		addSequential(new MotionMagic(5,2));
		addSequential(new MotionMagic(-5,2));

    }
}
