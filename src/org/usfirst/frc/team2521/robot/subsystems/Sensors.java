package org.usfirst.frc.team2521.robot.subsystems;

import org.usfirst.frc.team2521.robot.RobotMap;
import org.usfirst.frc.team2521.robot.commands.shooterCommands.DisplaySensors;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Sensors extends Subsystem {
	private AnalogInput lidar;
	private NetworkTable table;
	private double[] blobs_default = {-1};
	private double deltaX = 0;
	private double lastDeltaX = 0;
	
	public Sensors(){
		lidar = new AnalogInput(RobotMap.LIDAR_PORT);
		table = NetworkTable.getTable("SmartDashboard");
	}
    
	public boolean ballInBot(){ //get if we have the ball in the bot
		return getLidarDistance() < RobotMap.LIDAR_IN_BOT_THRESHOLD;
	}
	
	public boolean ballInShooter(){ //get if we have the ball in the shooter
		return getLidarDistance() < RobotMap.LIDAR_IN_SHOOTER_THRESHOLD;
	}
	
	public void display(){
		SmartDashboard.putNumber("Cam distance", getCameraDistance());
		SmartDashboard.putNumber("Lidar distance", getLidarDistance());
		SmartDashboard.putBoolean("Ball in bot", ballInBot());
		SmartDashboard.putBoolean("Ball in shooter", ballInShooter());
	}
	
	public double getCameraDistance(){
    	return RobotMap.HEIGHT_TO_DISTANCE_FACTOR/(getHeight());
    }
	
	/*public double getDeltaX(){
		double[] blobs = getBlobs();
		if(blobs.length > 0){ //makes sure that there is a blob, then calculates distance off center
			deltaX = blobs[1] - RobotMap.IMAGE_WIDTH/2;
			lastDeltaX = deltaX;
		} else deltaX = RobotMap.VISION_SETPOINT;
		return deltaX;
	}*/
	
	private double getHeight(){
    	double height = table.getNumber("HEIGHT", 0);
    	if(height != 0){
    		height = SmartDashboard.getNumber("Height");
    	}
    	return height;
    }
	
	
	public double getLidarDistance(){
		double raw = lidar.getValue();
		return RobotMap.LIDAR_FACTOR/(raw - RobotMap.LIDAR_OFFSET);
	}
	
	private double[] getBlobs(){
		double[] blobs = table.getNumberArray("BLOBS", blobs_default);
		return blobs;
	}
	
	public double getWidth(){
		return 0; //needs to be updated with SmartDashboard -- right now I just wanted to make it so it wouldn't throw an error
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new DisplaySensors());
    }
}

