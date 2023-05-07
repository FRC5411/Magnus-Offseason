//----------------------[Package]----------------------//
package frc.robot;
//----------------------[Library]----------------------//
import edu.wpi.first.wpilibj.RobotBase;
//------------------------[Main]-----------------------//
public final class Main { private Main() {}
  public static void main(String... args) {RobotBase.startRobot(Robot::new);}
}
