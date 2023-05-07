//----------------------[Package]----------------------//
package frc.robot.subsystems;
//----------------------[Library]----------------------//
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.Intake.SolenoidChannels;
import frc.robot.Constants.Intake.MotorPorts;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.InvertType;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid;
//------------------[Intake Subsystem]-----------------//
/**
 * Magnus' intake subsystem
 */
public class IntakeSubsystem extends SubsystemBase
{
    //-------------------[Constants]-------------------//
    private final WPI_TalonSRX I_INTAKE; 
    private final WPI_TalonSRX I_INDEXLEFT; private final WPI_TalonSRX I_INDEXRIGHT;
    private final DoubleSolenoid I_SOLENOID_ONE; private final DoubleSolenoid I_SOLENOID_TWO;
    private final DoubleSolenoid I_SOLENOID_THR; private final DoubleSolenoid I_SOLENOID_FOU;
    //------------------[Constructors]-----------------//
    /**
     * Constructor.
     */
    public IntakeSubsystem()
    {
        I_INTAKE = new WPI_TalonSRX(MotorPorts.II);
        I_INDEXLEFT = new WPI_TalonSRX(MotorPorts.IL); I_INDEXRIGHT = new WPI_TalonSRX(MotorPorts.IR);
        I_SOLENOID_ONE = new DoubleSolenoid(MotorPorts.IM,PneumaticsModuleType.CTREPCM,SolenoidChannels.SONE[0],SolenoidChannels.SONE[1]);
        I_SOLENOID_TWO = new DoubleSolenoid(MotorPorts.IM,PneumaticsModuleType.CTREPCM,SolenoidChannels.STWO[0],SolenoidChannels.STWO[1]);
        I_SOLENOID_THR = new DoubleSolenoid(MotorPorts.IM,PneumaticsModuleType.CTREPCM,SolenoidChannels.STHR[0],SolenoidChannels.STHR[1]);
        I_SOLENOID_FOU = new DoubleSolenoid(MotorPorts.IM,PneumaticsModuleType.CTREPCM,SolenoidChannels.SFOU[0],SolenoidChannels.SFOU[1]);
    }
    /**
     * Constructor.
     * @param II - Intake motorcontroller port
     * @param IL - Intake Indexer Left motorcontroller port
     * @param IR - Intake Indexer Right motorcontroller port
     * @param IM - Intake PCM port
     * @param SONE - Solenoid One's Left and Right channel ports
     * @param STWO- Solenoid Two's Left and Right channel ports
     * @param STHR- Solenoid Three's Left and Right channel ports
     * @param SFOU- Solenoid Four's Left and Right channel ports
     */
    public IntakeSubsystem(Integer II, Integer IL, Integer IR, Integer IM, Integer[] SONE, Integer[] STWO, Integer[] STHR, Integer[] SFOU)
    {
        I_INTAKE = new WPI_TalonSRX(II);
        I_INDEXLEFT = new WPI_TalonSRX(IL); I_INDEXRIGHT = new WPI_TalonSRX(IR);
        I_SOLENOID_ONE = new DoubleSolenoid(IM,PneumaticsModuleType.CTREPCM,SONE[0],SONE[1]);
        I_SOLENOID_TWO = new DoubleSolenoid(IM,PneumaticsModuleType.CTREPCM,STWO[0],STWO[1]);
        I_SOLENOID_THR = new DoubleSolenoid(IM,PneumaticsModuleType.CTREPCM,STHR[0],STHR[1]);
        I_SOLENOID_FOU = new DoubleSolenoid(IM,PneumaticsModuleType.CTREPCM,SFOU[0],SFOU[1]);
    }
    //-----------------[Intake Control]----------------//

    public void configureIntake()
    {
        I_INTAKE.configFactoryDefault();
        I_INDEXLEFT.configFactoryDefault(); I_INDEXRIGHT.configFactoryDefault();
        I_INTAKE.setNeutralMode(NeutralMode.Brake);
        I_INDEXLEFT.setNeutralMode(NeutralMode.Coast); I_INDEXRIGHT.setNeutralMode(NeutralMode.Coast);
        I_INDEXRIGHT.setInverted(InvertType.InvertMotorOutput);
    }
    /**
     * Set all solenoids to a target value
     * @param Demand - Solenoid Target
     */
    public void setAllSolenoids(DoubleSolenoid.Value Demand)
    {
        I_SOLENOID_ONE.set(Demand); I_SOLENOID_TWO.set(Demand);
        I_SOLENOID_THR.set(Demand); I_SOLENOID_FOU.set(Demand);
    }

    /** Set intake to inwards */
    public void setIntakeInwards()
    {
        if(checkSolenoidStates(DoubleSolenoid.Value.kReverse))
        {I_INTAKE.set(TalonSRXControlMode.PercentOutput, 1.0); 
        I_INDEXLEFT.set(TalonSRXControlMode.PercentOutput, 1.0); I_INDEXRIGHT.set(TalonSRXControlMode.PercentOutput, 1.0);}
    }

    /** Set intake to outwards */
    public void setIntakeOutwards()
    {
        if(checkSolenoidStates(DoubleSolenoid.Value.kReverse))
        {I_INTAKE.set(TalonSRXControlMode.PercentOutput, -1.0);
        I_INDEXLEFT.set(TalonSRXControlMode.PercentOutput, -1.0); I_INDEXRIGHT.set(TalonSRXControlMode.PercentOutput, -1.0);}
    }
    /** Stop intake */
    public void setIntakeStop()
    {
        I_INTAKE.set(TalonSRXControlMode.PercentOutput, 0);
        I_INDEXLEFT.set(TalonSRXControlMode.PercentOutput, 0); I_INDEXRIGHT.set(TalonSRXControlMode.PercentOutput, 0);
    }
    /**Toggle all solenoids and if any solenoid off, set to reverse value*/
    public void toggleAllSolenoids()
    {
        I_SOLENOID_ONE.toggle(); I_SOLENOID_TWO.toggle();
        I_SOLENOID_THR.toggle(); I_SOLENOID_FOU.toggle();
        if(checkSolenoidStates(DoubleSolenoid.Value.kOff)) {setAllSolenoids(DoubleSolenoid.Value.kReverse);}

    }
    /**
     * Check all solenoids against a target value
     * @param Target - Target to check against
     * @return - True if all solenoids are equal to the target
     */
    public Boolean checkSolenoidStates(DoubleSolenoid.Value Target)
    {
        return (I_SOLENOID_ONE.get().equals(Target) && I_SOLENOID_TWO.get().equals(Target) 
        && I_SOLENOID_THR.get().equals(Target) && I_SOLENOID_FOU.get().equals(Target));
    }
    @Override
    public void periodic() {}
}
