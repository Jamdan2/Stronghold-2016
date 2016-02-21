package org.usfirst.frc.team2521.robot.commands;

import org.usfirst.frc.team2521.robot.OI;
import org.usfirst.frc.team2521.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class PitchTeleop extends Command {

    public PitchTeleop() {
    	requires(Robot.pitch);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//Robot.pitch.teleopInit();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.pitch.set(-OI.getInstance().getSecondaryStick().getY());
    	//SmartDashboard.putBoolean("Pitch called no PID", true);
    	//Robot.turret.setYaw(OI.getInstance().getSecondaryStick().getX());
    	//System.out.println("Pitch called");
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
