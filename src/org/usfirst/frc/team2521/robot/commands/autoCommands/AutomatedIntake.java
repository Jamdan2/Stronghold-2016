
package org.usfirst.frc.team2521.robot.commands.autoCommands;

import org.usfirst.frc.team2521.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutomatedIntake extends Command {
	
	public AutomatedIntake() {
		requires(Robot.intake);
		requires(Robot.flyWheels);
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
	}
	
	// Called just before this Command runs the first time
	protected void initialize() {
	}
	
	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (!Robot.sensors.ballInBot()) {
			Robot.intake.in();
			Robot.flyWheels.in();
		}
	}
	
	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return Robot.sensors.ballInShooter();
	}
	
	// Called once after isFinished returns true
	protected void end() {
		Robot.intake.stop();
		Robot.flyWheels.stop();
	}
	
	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
