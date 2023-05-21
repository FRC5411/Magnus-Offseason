//----------------------[Package]----------------------//
package frc.robot.subsystems;

//----------------------[Library]----------------------//
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.Shooter.Data;
import frc.robot.Constants.Shooter.MotorPorts;

//-----------------[Shooter Subsystem]-----------------//
/** Magnus' shooter subsystem */
public class ShooterSubsystem extends SubsystemBase {
    // -------------------[Constants]-------------------//
    private final WPI_TalonFX S_LEFT_ROLLER;
    private final WPI_TalonFX S_RIGHT_ROLLER;
    private final WPI_TalonSRX S_LEFT_HOPPER;
    private final WPI_TalonSRX S_RIGHT_HOPPER;

    // ------------------[Constructors]-----------------//
    /**
     * Constructor.
     */
    public ShooterSubsystem() {
        S_LEFT_ROLLER = new WPI_TalonFX(MotorPorts.LR);
        S_RIGHT_ROLLER = new WPI_TalonFX(MotorPorts.RR);
        S_LEFT_HOPPER = new WPI_TalonSRX(MotorPorts.LH);
        S_RIGHT_HOPPER = new WPI_TalonSRX(MotorPorts.RH);
        configure();
    }

    /**
     * Constructor.
     * 
     * @param LR - Left roller motorcontroller port
     * @param RR - Right roller motorcontroller port
     * @param LH - Left hopper motorcontroller port
     * @param RH - Right hopper motorcontroller port
     */
    public ShooterSubsystem(Integer LR, Integer RR, Integer LH, Integer RH) {
        S_LEFT_ROLLER = new WPI_TalonFX(LR);
        S_RIGHT_ROLLER = new WPI_TalonFX(RR);
        S_LEFT_HOPPER = new WPI_TalonSRX(LH);
        S_RIGHT_HOPPER = new WPI_TalonSRX(RH);
        configure();
    }

    // -----------------[Shooter Control]----------------//

    /** Configure magnus' shooter devices */
    public void configure() {
        S_LEFT_ROLLER.configFactoryDefault();
        S_RIGHT_ROLLER.configFactoryDefault();
        S_LEFT_HOPPER.configFactoryDefault();
        S_RIGHT_HOPPER.configFactoryDefault();
        S_RIGHT_HOPPER.setInverted(InvertType.InvertMotorOutput);
        S_LEFT_ROLLER.setInverted(InvertType.InvertMotorOutput);
        S_LEFT_ROLLER.setNeutralMode(NeutralMode.Coast);
        S_RIGHT_ROLLER.setNeutralMode(NeutralMode.Coast);
        S_LEFT_ROLLER.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, 0, 0);
    }

    /** Set the hopper in */
    public void HopperInwards() {
        S_LEFT_HOPPER.set(TalonSRXControlMode.PercentOutput, Data.HOPPER_IN);
        S_RIGHT_HOPPER.set(TalonSRXControlMode.PercentOutput, Data.HOPPER_IN);
    }

    /** Set the hopper out */
    public void HopperOutwards() {
        S_LEFT_HOPPER.set(TalonSRXControlMode.PercentOutput, Data.HOPPER_OUT);
        S_RIGHT_HOPPER.set(TalonSRXControlMode.PercentOutput, Data.HOPPER_OUT);
    }

    /** Set the hoppet to stop */
    public void HopperStop() {
        S_LEFT_HOPPER.set(TalonSRXControlMode.PercentOutput, 0);
        S_RIGHT_HOPPER.set(TalonSRXControlMode.PercentOutput, 0);
    }

    /**
     * Set the shooter rollers to Demand speed
     * 
     * @param Mode   - Control mode type
     * @param Demand - Demand speed
     */
    public void setRollerSpeeds(TalonFXControlMode Mode, Double Demand) {
        S_LEFT_ROLLER.set(Mode, Demand);
        S_RIGHT_ROLLER.set(Mode, Demand);
    }

    /** Set shooter rollers to stop */
    public void setRollerStop() {
        S_LEFT_ROLLER.set(TalonFXControlMode.PercentOutput, 0);
        S_RIGHT_ROLLER.set(TalonFXControlMode.PercentOutput, 0);
    }

    /** Set rollers to regular firing speed */
    public void shooterFireRegular() {
        setRollerSpeeds(TalonFXControlMode.PercentOutput, Data.REGULAR_FIRE);
    }

    /** Set rollers to rally firing speed */
    public void shooterFireRally() {
        setRollerSpeeds(TalonFXControlMode.PercentOutput, Data.RALLY_FIRE);
    }
}
