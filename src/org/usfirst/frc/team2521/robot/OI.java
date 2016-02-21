
package org.usfirst.frc.team2521.robot;

import org.usfirst.frc.team2521.robot.commands.autoCommands.AutomatedIntake;
import org.usfirst.frc.team2521.robot.commands.intakeCommands.IntakeIn;
import org.usfirst.frc.team2521.robot.commands.intakeCommands.IntakeOut;
import org.usfirst.frc.team2521.robot.commands.intakeCommands.IntakeStop;
import org.usfirst.frc.team2521.robot.commands.intakeCommands.LinkedIntake;
import org.usfirst.frc.team2521.robot.commands.intakeCommands.LinkedIntakeStop;
import org.usfirst.frc.team2521.robot.commands.intakeCommands.SetPusher;
import org.usfirst.frc.team2521.robot.commands.shooterCommands.SetFlyWheels;
import org.usfirst.frc.team2521.robot.commands.shooterCommands.ShootBall;
import org.usfirst.frc.team2521.robot.commands.shooterCommands.StopFlyWheels;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	private Joystick left;
	private Joystick right;
	private Joystick secondary;
	
	private JoystickButton intakeButtonIn;
	private JoystickButton intakeButtonOut;
	private JoystickButton driveIntakeOut;
	
	private JoystickButton shooterButtonIn;
	private JoystickButton shooterButtonOut;
	
	private JoystickButton fireButton;
	private JoystickButton autoIntakeButton;
	
	private JoystickButton pusherButton;
	
	private JoystickButton linkedIntakeButton;
	
	private static OI instance;
	
	public OI() {
		left = new Joystick(RobotMap.LEFT_STICK_PORT);
		right = new Joystick(RobotMap.RIGHT_STICK_PORT);
		secondary = new Joystick(RobotMap.SECONDARY_STICK_PORT);
		initButtons();
	}
	
	public static OI getInstance() {
		if (instance == null) {
			instance = new OI();
		}
		
		return instance;
	}
	
	public Joystick getLeftStick() {
		return left;
	}
	
	public Joystick getRightStick() {
		return right;
	}
	
	public Joystick getSecondaryStick() {
		return secondary;
	}
	
	public void initButtons() {
		intakeButtonIn = new JoystickButton(secondary, RobotMap.INTAKE_BUTTON_IN);
		intakeButtonOut = new JoystickButton(secondary, RobotMap.INTAKE_BUTTON_OUT);
		shooterButtonIn = new JoystickButton(secondary, RobotMap.SHOOTER_BUTTON_IN);
		shooterButtonOut = new JoystickButton(secondary, RobotMap.SHOOTER_BUTTON_OUT);
		fireButton = new JoystickButton(secondary, RobotMap.FIRE_BUTTON);
		autoIntakeButton = new JoystickButton(secondary, RobotMap.AUTO_INTAKE_BUTTON);
		pusherButton = new JoystickButton(secondary, RobotMap.PUSHER_BUTTON);
		linkedIntakeButton = new JoystickButton(right, RobotMap.LINKED_INTAKE_BUTTON);
		driveIntakeOut = new JoystickButton(right, RobotMap.INTAKE_BUTTON_OUT);
		
		tieButtons();
	}
	
	public void tieButtons() {
		fireButton.whenPressed(new ShootBall());
		intakeButtonIn.whenPressed(new IntakeIn());
		intakeButtonIn.whenReleased(new IntakeStop());
		intakeButtonOut.whenPressed(new IntakeOut());
		intakeButtonOut.whenReleased(new IntakeStop());
		autoIntakeButton.whenPressed(new AutomatedIntake());
		shooterButtonIn.whenPressed(new SetFlyWheels(false));
		shooterButtonOut.whenPressed(new SetFlyWheels(true));
		shooterButtonIn.whenReleased(new StopFlyWheels());
		shooterButtonOut.whenReleased(new StopFlyWheels());
		pusherButton.whenPressed(new SetPusher(true));
		pusherButton.whenReleased(new SetPusher(false));
		linkedIntakeButton.whenPressed(new LinkedIntake());
		linkedIntakeButton.whenReleased(new LinkedIntakeStop());
		driveIntakeOut.whenPressed(new IntakeOut());
		driveIntakeOut.whenReleased(new IntakeStop());
	}
}
