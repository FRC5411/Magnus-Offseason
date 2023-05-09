//----------------------[Package]----------------------//
package frc.robot.subsystems;
//----------------------[Library]----------------------//
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import java.util.function.DoubleSupplier;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import frc.robot.Constants.Climb.MotorPorts;
//------------------[Climb Subsystem]------------------//
/** Magnus' climb subsystem */
public class ClimbSubsystem extends SubsystemBase
{
    //-------------------[Constants]-------------------//
    private final WPI_TalonSRX C_LEFT_ARM; private final WPI_TalonSRX C_RIGHT_ARM;
    private final WPI_TalonSRX C_LEFT_WIN; private final WPI_TalonSRX C_RIGHT_WIN;
    //------------------[Constructors]-----------------//
    public ClimbSubsystem()
    {
        C_LEFT_ARM = new WPI_TalonSRX(MotorPorts.LA); C_RIGHT_ARM = new WPI_TalonSRX(MotorPorts.RA);
        C_LEFT_WIN = new WPI_TalonSRX(MotorPorts.LW); C_RIGHT_WIN = new WPI_TalonSRX(MotorPorts.RW);
    }
    public ClimbSubsystem(Integer LA, Integer RA, Integer LW, Integer RW)
    {
        C_LEFT_ARM = new WPI_TalonSRX(LA); C_RIGHT_ARM = new WPI_TalonSRX(RA);
        C_LEFT_WIN = new WPI_TalonSRX(LW); C_RIGHT_WIN = new WPI_TalonSRX(RW);
    }
    //-----------------[Climb Control]-----------------//

    public void winchControl(DoubleSupplier Demand)
    {
        
    }
    public void winchControl(Double Demand)
    {

    }
    public void winchStop()
    {

    }
    public void armControl(DoubleSupplier Demand)
    {

    }
    public void armControl(Double Demand)
    {

    }

    public void armStop()
    {

    }
}
