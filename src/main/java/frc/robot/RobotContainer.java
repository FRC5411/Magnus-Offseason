//----------------------[Package]----------------------//
package frc.robot;
//----------------------[Library]----------------------//
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ClimbSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.Constants.DriverProfile;
import frc.robot.commands.DriveCommand;
import frc.robot.Constants.Functions;
//------------------[Robot Container]------------------//
public class RobotContainer 
{
  //----------------[Robot Subsystems]-----------------//
  private final DriveSubsystem M_Drive;
  private final IntakeSubsystem M_Intake;
  private final ClimbSubsystem M_Climb;
  //-----------------[Robot Controls]-------------------//
  private CommandXboxController M_Controller;
  private Trigger Trigger_ModeSwitch;
  private Trigger Trigger_Additive;
  private Trigger Trigger_Subtractive;
  private Trigger Trigger_Intake_In;
  private Trigger Trigger_Intake_Out;
  //---------------[Robot Miscellaneous]----------------//
  private final DriveCommand M_AutonomousCommand;
  private final Integer M_Controller_Port = DriverProfile.DRIVER_CONTROLLER_PORT;
  private final Class<?> M_Driver = DriverProfile.DRIVER_PROFILE;
  //------------------[Constructors]--------------------//
  /** Constructor*/
  public RobotContainer() 
  {
    //Defining Subsystems and Commands
    M_Drive = new DriveSubsystem(M_Driver); M_AutonomousCommand = new DriveCommand(() -> 0.0, () -> 0.5, M_Driver, M_Drive);   
    M_Drive.setDefaultCommand(new DriveCommand(() -> M_Controller.getRawAxis(1),
     () -> Math.atan(M_Controller.getRawAxis(4)/M_Controller.getRawAxis(1)),M_Driver, M_Drive));     
    M_Intake = new IntakeSubsystem(); 
    M_Climb = new ClimbSubsystem();
    
    //Controller(S)
    M_Controller = new CommandXboxController(M_Controller_Port);

    //Triggers
    if(M_Controller == null) {System.out.println("Controller Unidentified; Set to Default."); M_Controller = DriverProfile.Default.PRIMARY_CONTROLLER;}
    Trigger_ModeSwitch = (Trigger)Functions.getMethodAndExecute(M_Controller, (String)Functions.getFieldValue(M_Driver, "TRIGGER_MODE_SWITCH"));
    if(Trigger_ModeSwitch == null) {System.out.println("TRIGGER_MODE_SWITCH Unidentified; Set to Default.");Trigger_ModeSwitch = DriverProfile.Default.TRIGGER_MODE_SWITCH;}
    Trigger_Additive = (Trigger)Functions.getMethodAndExecute(M_Controller, (String)Functions.getFieldValue(M_Driver, "TRIGGER_INCREMENT"));
    if(Trigger_Additive == null) {System.out.println("TRIGGER_INCREMENT Unidentified; Set to Default.");Trigger_Additive = DriverProfile.Default.TRIGGER_INCREMENT;}
    Trigger_Subtractive = (Trigger)Functions.getMethodAndExecute(M_Controller, (String)Functions.getFieldValue(M_Driver, "TRIGGER_DECREMENT"));
    if(Trigger_Subtractive == null) {System.out.println("TRIGGER_DECREMENT Unidentified; Set to Default.");Trigger_Subtractive = DriverProfile.Default.TRIGGER_DECREMENT;}
    Trigger_Intake_In = (Trigger)Functions.getMethodAndExecute(M_Controller, (String)Functions.getFieldValue(M_Driver, "TRIGGER_INTAKE_IN"));
    if(Trigger_Intake_In == null) {System.out.println("TRIGGER_INTAKE_IN Unidentified; Set to Default.");Trigger_Intake_In = DriverProfile.Default.TRIGGER_INTAKE_IN;}
    Trigger_Intake_Out = (Trigger)Functions.getMethodAndExecute(M_Controller, (String)Functions.getFieldValue(M_Driver, "TRIGGER_INTAKE_OUT"));
    if(Trigger_Intake_Out == null) {System.out.println("TRIGGER_INTAKE_OUT Unidentified; Set to Default.");Trigger_Intake_Out = DriverProfile.Default.TRIGGER_INTAKE_OUT;}
    
    configureButtonBindings();
  }
  /** Configure Controller triggers bindings with Null protection*/
  private void configureButtonBindings() 
  {
    try {Trigger_ModeSwitch.onTrue(Commands.run(M_Drive::toggleDrivingMode,M_Drive));}
    catch(NullPointerException exception) {System.out.println("TRIGGER_MODE_SWITCH default failed; check Constants.DriverProfile.Default, could not find default");}
    try {Trigger_Additive.whileTrue(Commands.run(M_Drive::incrementCoefficient,M_Drive));}
    catch(NullPointerException exception) {System.out.println("TRIGGER_INCREMENT default failed; check Constants.DriverProfile.Default, could not find default");}
    try {Trigger_Subtractive.whileTrue(Commands.run(M_Drive::decrementCoefficient,M_Drive));}
    catch(NullPointerException exception) {System.out.println("TRIGGER_DECREMENT default failed; check Constants.DriverProfile.Default, could not find default");}
    try {Trigger_Intake_In.onTrue(Commands.run(M_Intake::setIntakeInwards,M_Intake));}
    catch(NullPointerException exception) {System.out.println("TRIGGER_INTAKE_IN default failed; check Constants.DriverProfile.Default, could not find default");}
    try {Trigger_Intake_Out.onTrue(Commands.run(M_Intake::setIntakeOutwards,M_Intake));}
    catch(NullPointerException exception) {System.out.println("TRIGGER_INTAKE_OUT default failed; check Constants.DriverProfile.Default, could not find default");}
  }
  /**
   * 
   * @return The selected autonomous command
   */
  public Command getAutonomousCommand() {return M_AutonomousCommand;}
}
