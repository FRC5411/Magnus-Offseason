//----------------------[Package]----------------------//
package frc.robot.subsystems;
//----------------------[Library]----------------------//
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import java.util.function.DoubleSupplier;

import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
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

    public void configureDevices()
    {
        C_LEFT_ARM.setInverted(InvertType.InvertMotorOutput);
        C_LEFT_WIN.setInverted(InvertType.InvertMotorOutput);
    }

    public void winchControl(DoubleSupplier Demand)
    {
        C_LEFT_WIN.set(TalonSRXControlMode.PercentOutput,Demand.getAsDouble());
        C_RIGHT_WIN.set(TalonSRXControlMode.PercentOutput,Demand.getAsDouble());
    }
    public void winchControl(Double Demand)
    {
        C_LEFT_WIN.set(TalonSRXControlMode.PercentOutput,Demand);
        C_RIGHT_WIN.set(TalonSRXControlMode.PercentOutput,Demand);
    }
    public void winchStop()
    {
        C_LEFT_WIN.set(TalonSRXControlMode.Velocity,0.0);
        C_RIGHT_WIN.set(TalonSRXControlMode.Velocity,0.0);
    }
    public void armControl(DoubleSupplier Demand)
    {
        C_LEFT_ARM.set(TalonSRXControlMode.PercentOutput,Demand.getAsDouble());
        C_RIGHT_ARM.set(TalonSRXControlMode.PercentOutput,Demand.getAsDouble());
    }
    public void armControl(Double Demand)
    {
        C_LEFT_ARM.set(TalonSRXControlMode.PercentOutput,Demand);
        C_RIGHT_ARM.set(TalonSRXControlMode.PercentOutput,Demand);
    }

    public void armStop()
    {
        C_LEFT_ARM.set(TalonSRXControlMode.Velocity,0.0);
        C_RIGHT_ARM.set(TalonSRXControlMode.Velocity,0.0);
    }

    public void ControlLeft(DoubleSupplier DemandWinch, DoubleSupplier DemandArm)
    {
        C_LEFT_WIN.set(TalonSRXControlMode.PercentOutput,DemandWinch.getAsDouble());
        C_LEFT_ARM.set(TalonSRXControlMode.PercentOutput,DemandArm.getAsDouble());
    }

    public void ControlRight(DoubleSupplier DemandWinch, DoubleSupplier DemandArm)
    {
        C_RIGHT_WIN.set(TalonSRXControlMode.PercentOutput,DemandWinch.getAsDouble());
        C_RIGHT_ARM.set(TalonSRXControlMode.PercentOutput,DemandArm.getAsDouble());
    }
}
