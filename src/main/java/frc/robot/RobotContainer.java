//----------------------[Package]----------------------//
package frc.robot;
//----------------------[Library]----------------------//
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.Constants.DriverProfile;
import frc.robot.commands.DriveCommand;
import frc.robot.Constants.Functions;
//------------------[Robot Container]------------------//
public class RobotContainer 
{
  private final DriveSubsystem M_Drive;
  private final IntakeSubsystem M_Intake;
  private final DriveCommand M_AutonomousCommand;
  private final CommandXboxController M_Controller;
  private final Trigger Trigger_ModeSwitch;
  private final Trigger Trigger_Additive;
  private final Trigger Trigger_Subtractive;
  private final Trigger Trigger_Intake_In;
  private final Trigger Trigger_Intake_Out;
  private final Class<?> M_Driver = DriverProfile.Cody_W.class; // <--------- TODO: Change as Needed

  /** Constructor.*/
  public RobotContainer() 
  {
    M_Drive = new DriveSubsystem(M_Driver); M_AutonomousCommand = new DriveCommand(0.0, 0.0, M_Driver, M_Drive);    
    M_Intake = new IntakeSubsystem();
    M_Controller = new CommandXboxController(0); 
    Trigger_ModeSwitch = Functions.deriveButton(M_Controller.getClass(),(String)Functions.deriveField(M_Driver,"TRIGGER_MODE_SWITCH"));
    Trigger_Additive = Functions.deriveButton(M_Controller.getClass(),(String)Functions.deriveField(M_Driver,"TRIGGER_INCREMENT"));
    Trigger_Subtractive = Functions.deriveButton(M_Controller.getClass(),(String)Functions.deriveField(M_Driver,"TRIGGER_DECREMENT"));
    Trigger_Intake_In = Functions.deriveButton(M_Controller.getClass(),(String)Functions.deriveField(M_Driver,"TRIGGER_INTAKE_IN"));
    Trigger_Intake_Out = Functions.deriveButton(M_Controller.getClass(),(String)Functions.deriveField(M_Driver,"TRIGGER_INTAKE_OUT"));
    M_Drive.setDefaultCommand(new DriveCommand(M_Controller.getLeftY(), Math.atan(M_Controller.getLeftY()/M_Controller.getLeftX()),M_Driver, M_Drive));
    configureButtonBindings();
  }
  private void configureButtonBindings() 
  {
    Trigger_ModeSwitch.onTrue(Commands.run(M_Drive::toggleDrivingMode,M_Drive));
    Trigger_Additive.whileTrue(Commands.run(M_Drive::incrementCoefficient));
    Trigger_Subtractive.whileTrue(Commands.run(M_Drive::decrementCoefficient));
    Trigger_Intake_In.onTrue(Commands.run(M_Intake::setIntakeInwards));
    Trigger_Intake_Out.onTrue(Commands.run(M_Intake::setIntakeOutwards));
  }
  public Command getAutonomousCommand() {return M_AutonomousCommand;}
}
