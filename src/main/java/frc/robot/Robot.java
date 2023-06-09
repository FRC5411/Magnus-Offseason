//----------------------[Package]----------------------//
package frc.robot;

//----------------------[Library]----------------------//
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj.TimedRobot;

//----------------------[Robot]----------------------//
public class Robot extends TimedRobot {

  private Command M_AutonomousCommand;
  private RobotContainer M_RobotContainer;

  @Override
  public void robotInit() {
    M_RobotContainer = new RobotContainer();
  }

  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();
  }

  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
  }

  @Override
  public void autonomousInit() {
    M_AutonomousCommand = M_RobotContainer.getAutonomousCommand();
    if (M_AutonomousCommand != null) {
      M_AutonomousCommand.schedule();
    }
  }

  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void teleopInit() {
    if (M_AutonomousCommand != null) {
      M_AutonomousCommand.cancel();
    }
  }

  @Override
  public void teleopPeriodic() {
  }

  @Override
  public void testInit() {
    CommandScheduler.getInstance().cancelAll();
  }

  @Override
  public void testPeriodic() {
  }

  @Override
  public void simulationInit() {
  }

  @Override
  public void simulationPeriodic() {
  }
}
