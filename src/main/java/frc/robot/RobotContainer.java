//----------------------[Package]----------------------//
package frc.robot;
//----------------------[Library]----------------------//
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.Constants.DriverProfile;
import frc.robot.commands.DriveCommand;
import frc.robot.Constants.Functions;
//------------------[Robot Container]------------------//
public class RobotContainer 
{
  private final DriveSubsystem M_Drive;
  private final DriveCommand M_AutonomousCommand;
  private final CommandXboxController M_Controller;
  private final Trigger Trigger_ModeSwitch;
  private final Trigger Trigger_Additive;
  private final Trigger Trigger_Subtractve;
  private final Class<?> M_Driver = DriverProfile.Cody_W.class; // <--------- TODO: Change as Needed

  /** Constructor.*/
  public RobotContainer() 
  {
    M_Drive = new DriveSubsystem(M_Driver); M_AutonomousCommand = new DriveCommand(0.0, 0.0, M_Driver, M_Drive);    
    M_Controller = new CommandXboxController(0); 
    Trigger_ModeSwitch = Functions.deriveButton(M_Controller.getClass(),(String)Functions.deriveField(M_Driver,"TRIGGER_MODE_SWITCH"));
    Trigger_Additive = Functions.deriveButton(M_Controller.getClass(),(String)Functions.deriveField(M_Driver,"TRIGGER_INCREMENT"));
    Trigger_Subtractve = Functions.deriveButton(M_Controller.getClass(),(String)Functions.deriveField(M_Driver,"TRIGGER_DECREMENT"));
    M_Drive.setDefaultCommand(new DriveCommand(M_Controller.getLeftY(), Math.atan(M_Controller.getLeftY()/M_Controller.getLeftX()),M_Driver, M_Drive));
    configureButtonBindings();
  }
  private void configureButtonBindings() 
  {
    Trigger_ModeSwitch.onTrue(Commands.run(M_Drive::toggleDrivingMode,M_Drive));
    Trigger_Additive.whileTrue(Commands.run(M_Drive::incrementCoefficient));
    Trigger_Subtractve.whileTrue(Commands.run(M_Drive::decrementCoefficient));
  }
  public Command getAutonomousCommand() {return M_AutonomousCommand;}
}
