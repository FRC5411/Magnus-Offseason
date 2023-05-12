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
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;

//------------------[Intake Subsystem]-----------------//
/** Magnus' intake subsystem */
public class IntakeSubsystem extends SubsystemBase {
    // -------------------[Constants]-------------------//
    private final WPI_TalonSRX I_INTAKE;
    private final WPI_TalonSRX I_INDEXLEFT;
    private final WPI_TalonSRX I_INDEXRIGHT;
    private final Compressor I_COMPRESSOR;
    private final DoubleSolenoid I_SOLENOID_ONE;
    private final DoubleSolenoid I_SOLENOID_TWO;

    // ------------------[Constructors]-----------------//
    /**
     * Constructor.
     */
    public IntakeSubsystem() {
        I_INTAKE = new WPI_TalonSRX(MotorPorts.II);
        I_INDEXLEFT = new WPI_TalonSRX(MotorPorts.IL);
        I_INDEXRIGHT = new WPI_TalonSRX(MotorPorts.IR);
        I_COMPRESSOR = new Compressor(MotorPorts.IM, PneumaticsModuleType.CTREPCM);
        I_COMPRESSOR.enableDigital();
        I_SOLENOID_ONE = new DoubleSolenoid(MotorPorts.SM, PneumaticsModuleType.CTREPCM, SolenoidChannels.SONE[0],
                SolenoidChannels.SONE[1]);
        I_SOLENOID_TWO = new DoubleSolenoid(MotorPorts.SM, PneumaticsModuleType.CTREPCM, SolenoidChannels.STWO[0],
                SolenoidChannels.STWO[1]);
        configureIntake();
    }

    /**
     * Constructor.
     * 
     * @param II   - Intake motorcontroller port
     * @param IL   - Intake Indexer Left motorcontroller port
     * @param IR   - Intake Indexer Right motorcontroller port
     * @param IM   - Intake PCM port
     * @param SONE - Solenoid One's Left and Right channel ports
     * @param STWO - Solenoid Two's Left and Right channel ports
     */
    public IntakeSubsystem(Integer II, Integer IL, Integer IR, Integer IM, Integer[] SONE, Integer[] STWO) {
        I_INTAKE = new WPI_TalonSRX(II);
        I_INDEXLEFT = new WPI_TalonSRX(IL);
        I_INDEXRIGHT = new WPI_TalonSRX(IR);
        I_COMPRESSOR = new Compressor(IM, PneumaticsModuleType.CTREPCM);
        I_COMPRESSOR.enableDigital();
        I_SOLENOID_ONE = new DoubleSolenoid(IM, PneumaticsModuleType.CTREPCM, SONE[0], SONE[1]);
        I_SOLENOID_TWO = new DoubleSolenoid(IM, PneumaticsModuleType.CTREPCM, STWO[0], STWO[1]);
        configureIntake();
    }
    // -----------------[Intake Control]----------------//

    public void configureIntake() {
        I_INTAKE.configFactoryDefault();
        I_INDEXLEFT.configFactoryDefault();
        I_INDEXRIGHT.configFactoryDefault();
        I_INTAKE.setNeutralMode(NeutralMode.Brake);
        I_INDEXLEFT.setNeutralMode(NeutralMode.Coast);
        I_INDEXRIGHT.setNeutralMode(NeutralMode.Coast);
        I_INDEXRIGHT.setInverted(InvertType.InvertMotorOutput);
        setAllSolenoids(DoubleSolenoid.Value.kOff);
    }

    /**
     * Set all solenoids to a target value
     * 
     * @param Demand - Solenoid Target
     */
    public void setAllSolenoids(DoubleSolenoid.Value Demand) {
        I_SOLENOID_ONE.set(Demand);
        I_SOLENOID_TWO.set(Demand);
    }

    /** Set intake to inwards */
    public void setIntakeInwards() {
        if (checkSolenoidStates(DoubleSolenoid.Value.kForward) | checkSolenoidStates(DoubleSolenoid.Value.kOff)) {
            I_INTAKE.set(TalonSRXControlMode.PercentOutput, 1.0);
            I_INDEXLEFT.set(TalonSRXControlMode.PercentOutput, 1.0);
            I_INDEXRIGHT.set(TalonSRXControlMode.PercentOutput, 1.0);
        }
    }

    /** Set intake to outwards */
    public void setIntakeOutwards() {
        if (checkSolenoidStates(DoubleSolenoid.Value.kReverse) | checkSolenoidStates(DoubleSolenoid.Value.kOff)) {
            I_INTAKE.set(TalonSRXControlMode.PercentOutput, -1.0);
            I_INDEXLEFT.set(TalonSRXControlMode.PercentOutput, -1.0);
            I_INDEXRIGHT.set(TalonSRXControlMode.PercentOutput, -1.0);
        }
    }

    /** Stop intake */
    public void setIntakeStop() {
        I_INTAKE.set(TalonSRXControlMode.PercentOutput, 0);
        I_INDEXLEFT.set(TalonSRXControlMode.PercentOutput, 0);
        I_INDEXRIGHT.set(TalonSRXControlMode.PercentOutput, 0);
        toggleAllSolenoids();
    }

    /** Toggle all solenoids and if any solenoid off, set to reverse value */
    public void toggleAllSolenoids() {
        I_SOLENOID_ONE.toggle();
        I_SOLENOID_TWO.toggle();
        if (checkSolenoidStates(DoubleSolenoid.Value.kOff)) {
            setAllSolenoids(DoubleSolenoid.Value.kReverse);
        }

    }

    /**
     * Check all solenoids against a target value
     * 
     * @param Target - Target to check against
     * @return - True if all solenoids are equal to the target
     */
    public Boolean checkSolenoidStates(DoubleSolenoid.Value Target) {
        return (I_SOLENOID_ONE.get().equals(Target) && I_SOLENOID_TWO.get().equals(Target));
    }

    @Override
    public void periodic() {
    }
}
