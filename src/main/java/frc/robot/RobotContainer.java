//----------------------[Package]----------------------//
package frc.robot;

//----------------------[Library]----------------------//
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.DriverProfile;
import frc.robot.Constants.Climb;
import frc.robot.Constants.Functions;
import frc.robot.subsystems.*;
import frc.robot.commands.*;

//------------------[Robot Container]------------------//
public class RobotContainer {
  // ----------------[Robot Subsystems]-----------------//
  private final DriveSubsystem M_Drive;
  private final IntakeSubsystem M_Intake;
  private final ClimbSubsystem M_Climb;
  // -----------------[Robot Controls]-------------------//
  private CommandXboxController M_Controller;
  private Trigger Trigger_DB_Mode_Switch;
  private Trigger Trigger_Additive;
  private Trigger Trigger_Subtractive;
  private Trigger Trigger_Intake;
  private Trigger Trigger_Left_Arm_Movement;
  private Trigger Trigger_Right_Arm_Movement;
  private Trigger Trigger_Control_Mode_Switch;
  private Boolean M_Control_Mode = false;
  // ---------------[Robot Miscellaneous]----------------//
  private final DriveCommand M_AutonomousCommand;
  private final Class<?> M_Driver = DriverProfile.DRIVER_PROFILE;

  // ------------------[Constructors]--------------------//
  /** Constructor */
  public RobotContainer() {
    // Contro    M_Controller = new CommandXboxController(M_Controller_Port);

    // Defining Subsystems and Commands
    M_Drive = new DriveSubsystem(M_Driver);
    M_Drive.setDefaultCommand(new DriveCommand(() -> M_Controller.getLeftY(),
        () -> M_Controller.getRightX(), M_Driver, M_Drive));
    M_AutonomousCommand = new DriveCommand(() -> 0.0, () -> 0.5, M_Driver, M_Drive);
    M_Intake = new IntakeSubsystem();
    M_Climb = new ClimbSubsystem();

    // Triggers
    if (M_Controller == null) {
      Commands.print("Controller Unidentified; Set to Default.");
      M_Controller = DriverProfile.Default.PRIMARY_CONTROLLER;
    }
    Trigger_DB_Mode_Switch = (Trigger) Functions.getMethodAndExecute(M_Controller,
        (String) Functions.getFieldValue(M_Driver, "TRIGGER_MODE_SWITCH"));
    if (Trigger_DB_Mode_Switch == null) {
      Commands.print("TRIGGER_MODE_SWITCH Unidentified; Set to Default.");
      Trigger_DB_Mode_Switch = DriverProfile.Default.TRIGGER_DB_MODE;
    }
    Trigger_Additive = (Trigger) Functions.getMethodAndExecute(M_Controller,
        (String) Functions.getFieldValue(M_Driver, "TRIGGER_INCREMENT"));
    if (Trigger_Additive == null) {
      Commands.print("TRIGGER_INCREMENT Unidentified; Set to Default.");
      Trigger_Additive = DriverProfile.Default.TRIGGER_INCREMENT;
    }
    Trigger_Subtractive = (Trigger) Functions.getMethodAndExecute(M_Controller,
        (String) Functions.getFieldValue(M_Driver, "TRIGGER_DECREMENT"));
    if (Trigger_Subtractive == null) {
      Commands.print("TRIGGER_DECREMENT Unidentified; Set to Default.");
      Trigger_Subtractive = DriverProfile.Default.TRIGGER_DECREMENT;
    }
    Trigger_Intake = (Trigger) Functions.getMethodAndExecute(M_Controller,
        (String) Functions.getFieldValue(M_Driver, "TRIGGER_INTAKE"));
    if (Trigger_Intake == null) {
      Commands.print("TRIGGER_INTAKE Unidentified; Set to Default.");
      Trigger_Intake = DriverProfile.Default.TRIGGER_INTAKE;
    }

    Trigger_Left_Arm_Movement = (Trigger) Functions.getMethodAndExecute(M_Controller,
        (String) Functions.getFieldValue(M_Driver, "TRIGGER_L_ARM_CONTROL"));
    if (Trigger_Left_Arm_Movement == null) {
      Commands.print("TRIGGER_L_ARM_CONTROL Unidentified; Set to Default.");
      Trigger_Left_Arm_Movement = DriverProfile.Default.TRIGGER_L_ARM_CONTROL;
    }

    Trigger_Right_Arm_Movement = (Trigger) Functions.getMethodAndExecute(M_Controller,
        (String) Functions.getFieldValue(M_Driver, "TRIGGER_R_ARM_CONTROL"));
    if (Trigger_Right_Arm_Movement == null) {
      Commands.print("TRIGGER_R_ARM_CONTROL Unidentified; Set to Default.");
      Trigger_Right_Arm_Movement = DriverProfile.Default.TRIGGER_R_ARM_CONTROL;
    }
    Trigger_Control_Mode_Switch = (Trigger) Functions.getMethodAndExecute(M_Controller,
        (String) Functions.getFieldValue(M_Driver, "TRIGGER_MODE_SWITCH"));
    if (Trigger_Control_Mode_Switch == null) {
      Commands.print("TRIGGER_CONTROL_MODE_SWITCH Unidentified; Set to Default.");
      Trigger_Control_Mode_Switch = DriverProfile.Default.TRIGGER_MODE_SWITCH;
    }
    configureButtonBindings();
  }

  /** Configure Controller triggers bindings with Null protection */
  private void configureButtonBindings() {
    try {
      Trigger_DB_Mode_Switch.onTrue(Commands.run(M_Drive::toggleDrivingMode, M_Drive));
    } catch (NullPointerException exception) {
      Commands
          .print("TRIGGER_MODE_SWITCH default failed; check Constants.DriverProfile.Default, could not find default");
    }
    try {
      Trigger_Additive.whileTrue(Commands.run(M_Drive::incrementCoefficient, M_Drive));
    } catch (NullPointerException exception) {
      Commands.print("TRIGGER_INCREMENT default failed; check Constants.DriverProfile.Default, could not find default");
    }
    try {
      Trigger_Subtractive.whileTrue(Commands.run(M_Drive::decrementCoefficient, M_Drive));
    } catch (NullPointerException exception) {
      Commands.print("TRIGGER_DECREMENT default failed; check Constants.DriverProfile.Default, could not find default");
    }
    try {
      Trigger_Intake.onTrue(Commands.run(M_Intake::setIntakeOutwards, M_Intake));
      Trigger_Intake.onFalse(Commands.run(M_Intake::setIntakeStop,M_Intake));
    } catch (NullPointerException exception) {
      Commands
          .print("TRIGGER_INTAKE default failed; check Constants.DriverProfile.Default, could not find default");
    }
    try {
      Trigger_Left_Arm_Movement.whileTrue(new ClimbCommand(
          (Trigger_Left_Arm_Movement.and(M_Controller.povUp()).getAsBoolean()) ? (Climb.Values.C_ARM_UP)
              : (Climb.Values.C_ARM_DOWN),
          ((Trigger_Left_Arm_Movement.and(M_Controller.povDown()).getAsBoolean()) ? (Climb.Values.C_WIN_IN)
              : (Climb.Values.C_WIN_OUT)),
          (M_Control_Mode) ? (2) : (0), (!M_Control_Mode), M_Climb));
      Trigger_Left_Arm_Movement.onFalse(new ClimbCommand(0.0, 0.0, 2, false, M_Climb));
    } catch (NullPointerException exception) {
      Commands
          .print("TRIGGER_L_ARM_CONTROL default failed; check Constants.DriverProfile.Default, could not find default");
    }

    try {
      Trigger_Right_Arm_Movement.whileTrue(new ClimbCommand(
          (Trigger_Right_Arm_Movement.and(M_Controller.povUp()).getAsBoolean()) ? (Climb.Values.C_ARM_UP)
              : (Climb.Values.C_ARM_DOWN),
          ((Trigger_Right_Arm_Movement.and(M_Controller.povDown()).getAsBoolean()) ? (Climb.Values.C_WIN_IN)
              : (Climb.Values.C_WIN_OUT)),
          (M_Control_Mode) ? (2) : (1), (!M_Control_Mode), M_Climb));
      Trigger_Right_Arm_Movement.onFalse(new ClimbCommand(0.0, 0.0, 2, false, M_Climb));
    } catch (NullPointerException exception) {
      Commands
          .print("TRIGGER_R_ARM_CONTROL default failed; check Constants.DriverProfile.Default, could not find default");
    }

    try {
      Trigger_Control_Mode_Switch.onTrue(Commands.run(this::toggleControlMode));
    } catch (NullPointerException exception) {
      Commands
          .print("TRIGGER_MODE_SWITCH default failed; check Constants.DriverProfile.Default, could not find default");
    }
  }

  /** @return The selected autonomous command */
  public Command getAutonomousCommand() {
    return M_AutonomousCommand;
  }

  /** Toggle operating mode */
  public void toggleControlMode() {
    this.M_Control_Mode = !M_Control_Mode;
  }
}