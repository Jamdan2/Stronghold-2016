package org.usfirst.frc.team2521.robot.subsystems;

import org.usfirst.frc.team2521.robot.OI;
import org.usfirst.frc.team2521.robot.Robot;
import org.usfirst.frc.team2521.robot.RobotMap;
import org.usfirst.frc.team2521.robot.commands.TeleoperatedDrive;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DrivetrainPID extends PIDSubsystem {
	private RobotDrive frontDrive;
	private RobotDrive rearDrive;
	private boolean clockwise = false;
	
	private final double traverseOffset = 0.1;
	
	private double targetAngle = 0; 
	
	private CANTalon frontLeft, frontRight, rearLeft, rearRight;
	
    // Initialize your subsystem here
    public DrivetrainPID() {
    	super(RobotMap.DRIVE_TURN_P, RobotMap.DRIVE_TURN_I, RobotMap.DRIVE_TURN_D);
    	frontLeft = new CANTalon(RobotMap.FRONT_LEFT_MOTOR);
		frontRight = new CANTalon(RobotMap.FRONT_RIGHT_MOTOR);
		rearLeft = new CANTalon(RobotMap.REAR_LEFT_MOTOR);
		rearRight = new CANTalon(RobotMap.REAR_RIGHT_MOTOR);
		
		frontLeft.enableControl();
		frontRight.enableControl();
		rearLeft.enableControl();
		rearRight.enableControl();
		
		frontDrive = new RobotDrive(rearLeft, rearRight);
		rearDrive = new RobotDrive(rearLeft, rearRight);
		
		SmartDashboard.putNumber("Front right", 0);
		SmartDashboard.putNumber("Front left", 0);
    }
    
    public void tankDrive() {
		double left = OI.getInstance().getLeftStick().getY();
		double right = OI.getInstance().getRightStick().getY();
		
		frontDrive.tankDrive(right, left); // Switched to make it work
		rearDrive.tankDrive(right, left);
	}
	
	public void arcadeDrive() {
		Joystick left = OI.getInstance().getLeftStick();
		
		frontDrive.arcadeDrive(left);
		rearDrive.arcadeDrive(left);
	}
	
	public void teleoperatedDrive() {
		frontLeft.changeControlMode(TalonControlMode.PercentVbus);
		frontRight.changeControlMode(TalonControlMode.PercentVbus);
		rearLeft.changeControlMode(TalonControlMode.PercentVbus);
		rearRight.changeControlMode(TalonControlMode.PercentVbus);
		
		tankDrive();
	}
	
	public void setClockwise(boolean clockwise){
		this.clockwise = clockwise;
	}
	
	public void setPosition(int leftPosition, int rightPosition) {
		Robot.talonLeft.setSetpoint(leftPosition);
		Robot.talonRight.setSetpoint(rightPosition);
	}
	
	private void setLeft(double value){
		frontLeft.set(value);
		rearLeft.changeControlMode(TalonControlMode.Follower);
		rearLeft.set(RobotMap.FRONT_LEFT_MOTOR);
	}
	
	private void setRight(double value){
		frontRight.set(value);
		rearRight.changeControlMode(TalonControlMode.Follower);
		rearRight.set(RobotMap.FRONT_RIGHT_MOTOR);
	}
	
	public void set(double leftValue, double rightValue) {
		frontRight.set(leftValue); 
		frontLeft.set(-rightValue); 
		
		rearRight.changeControlMode(TalonControlMode.Follower);
		rearRight.set(RobotMap.FRONT_RIGHT_MOTOR);
		
		rearLeft.changeControlMode(TalonControlMode.Follower);
		rearLeft.set(RobotMap.FRONT_LEFT_MOTOR);
	}
	
	public void setTargetAngle(double angle){
		targetAngle = angle;
	}
    
    public void initDefaultCommand() {
    	setDefaultCommand(new TeleoperatedDrive());
        // Set the default command for a subsystem here.
    }
    
    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
    	double a = targetAngle - Robot.sensors.getYaw();
    	a = (a + 180) % 360 - 180;
    	return a;
    }
    
    protected void usePIDOutput(double output) {
    	setRight(-output);
    	setLeft(-output);
   // 		setRight(output);
    //		setLeft(output);
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    }
}
