package org.usfirst.frc.team2521.robot.commands.turretCommands;

import org.usfirst.frc.team2521.robot.OI;
import org.usfirst.frc.team2521.robot.Robot;
import org.usfirst.frc.team2521.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class YawTeleop extends Command {

    public YawTeleop() {
    	requires(Robot.yaw);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//Robot.yaw.teleopInit();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.yaw.set(OI.getInstance().getSecondaryStick().getX()*RobotMap.YAW_SENSITIVITY);
    	//SmartDashboard.putBoolean("Yaw called", true);
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
