//----------------------[Package]----------------------//
package frc.robot.subsystems;

//----------------------[Library]----------------------//
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import frc.robot.Constants.Climb.MotorPorts;
import java.util.function.DoubleSupplier;

//------------------[Climb Subsystem]------------------//
/** Magnus' climb subsystem */
public class ClimbSubsystem extends SubsystemBase {
    // -------------------[Constants]-------------------//
    private final WPI_TalonSRX C_LEFT_ARM;
    private final WPI_TalonSRX C_RIGHT_ARM;
    private final WPI_TalonSRX C_LEFT_WIN;
    private final WPI_TalonSRX C_RIGHT_WIN;

    // ------------------[Constructors]-----------------//
    /** Constructor */
    public ClimbSubsystem() {
        C_LEFT_ARM = new WPI_TalonSRX(MotorPorts.LA);
        C_RIGHT_ARM = new WPI_TalonSRX(MotorPorts.RA);
        C_LEFT_WIN = new WPI_TalonSRX(MotorPorts.LW);
        C_RIGHT_WIN = new WPI_TalonSRX(MotorPorts.RW);
    }

    /**
     * Constructor
     * 
     * @param LA - Left Arm TalonSRX port
     * @param RA - Right Arm TalonSRX port
     * @param LW - Left Winch TalonSRX port
     * @param RW - Right Winch TalonSRX port
     */
    public ClimbSubsystem(Integer LA, Integer RA, Integer LW, Integer RW) {
        C_LEFT_ARM = new WPI_TalonSRX(LA);
        C_RIGHT_ARM = new WPI_TalonSRX(RA);
        C_LEFT_WIN = new WPI_TalonSRX(LW);
        C_RIGHT_WIN = new WPI_TalonSRX(RW);
    }
    // -----------------[Climb Control]-----------------//

    /** Configure climb devices */
    public void configureDevices() {
        C_LEFT_ARM.setInverted(InvertType.InvertMotorOutput);
        C_LEFT_WIN.setInverted(InvertType.InvertMotorOutput);
    }

    /**
     * Control both winches
     * 
     * @param Demand - Target velocity of winches
     */
    public void winchVelocityControl(DoubleSupplier Demand) {
        C_LEFT_WIN.set(TalonSRXControlMode.PercentOutput, Demand.getAsDouble());
        C_RIGHT_WIN.set(TalonSRXControlMode.PercentOutput, Demand.getAsDouble());
    }

    /**
     * Control both winches
     * 
     * @param Demand - Target velocity of winches
     */
    public void winchVelocityControl(Double Demand) {
        C_LEFT_WIN.set(TalonSRXControlMode.PercentOutput, Demand);
        C_RIGHT_WIN.set(TalonSRXControlMode.PercentOutput, Demand);
    }

    /**
     * Control both winches
     * 
     * @param Demand - Target position of winches
     */
    public void winchPositionControl(DoubleSupplier Demand) {
        C_LEFT_WIN.set(TalonSRXControlMode.PercentOutput, Demand.getAsDouble());
        C_RIGHT_WIN.set(TalonSRXControlMode.PercentOutput, Demand.getAsDouble());
    }

    /**
     * Control both winches
     * 
     * @param Demand - Target position of winches
     */
    public void winchPositionControl(Double Demand) {
        C_LEFT_WIN.set(TalonSRXControlMode.PercentOutput, Demand);
        C_RIGHT_WIN.set(TalonSRXControlMode.PercentOutput, Demand);
    }

    /** Stop winch movement */
    public void winchStop() {
        C_LEFT_WIN.set(TalonSRXControlMode.Velocity, 0.0);
        C_RIGHT_WIN.set(TalonSRXControlMode.Velocity, 0.0);
    }

    /**
     * Control both arms
     * 
     * @param Demand - Target velocity of arms
     */
    public void armVelocityControl(DoubleSupplier Demand) {
        C_LEFT_ARM.set(TalonSRXControlMode.PercentOutput, Demand.getAsDouble());
        C_RIGHT_ARM.set(TalonSRXControlMode.PercentOutput, Demand.getAsDouble());
    }

    /**
     * Control both arms
     * 
     * @param Demand - Target velocity of arms
     */
    public void armVelocityControl(Double Demand) {
        C_LEFT_ARM.set(TalonSRXControlMode.PercentOutput, Demand);
        C_RIGHT_ARM.set(TalonSRXControlMode.PercentOutput, Demand);
    }

    /**
     * Control both arms
     * 
     * @param Demand - Target position of arms
     */
    public void armPositionControl(DoubleSupplier Demand) {
        C_LEFT_ARM.set(TalonSRXControlMode.PercentOutput, Demand.getAsDouble());
        C_RIGHT_ARM.set(TalonSRXControlMode.PercentOutput, Demand.getAsDouble());
    }

    /**
     * Control both arms
     * 
     * @param Demand - Target position of arms
     */
    public void armPositionControl(Double Demand) {
        C_LEFT_ARM.set(TalonSRXControlMode.PercentOutput, Demand);
        C_RIGHT_ARM.set(TalonSRXControlMode.PercentOutput, Demand);
    }

    /** Stop arm movement */
    public void armStop() {
        C_LEFT_ARM.set(TalonSRXControlMode.Velocity, 0.0);
        C_RIGHT_ARM.set(TalonSRXControlMode.Velocity, 0.0);
    }

    /**
     * Control left arm
     * 
     * @param DemandWinch - Target velocity of winch
     * @param DemandArm   - Target velocity of arm
     */
    public void controlVelocityLeft(DoubleSupplier DemandWinch, DoubleSupplier DemandArm) {
        C_LEFT_WIN.set(TalonSRXControlMode.PercentOutput, DemandWinch.getAsDouble());
        C_LEFT_ARM.set(TalonSRXControlMode.PercentOutput, DemandArm.getAsDouble());
    }

    /**
     * Control left arm
     * 
     * @param DemandWinch - Target velocity of winch
     * @param DemandArm   - Target velocity of arm
     */
    public void controlVelocityLeft(Double DemandWinch, Double DemandArm) {
        C_LEFT_WIN.set(TalonSRXControlMode.PercentOutput, DemandWinch);
        C_LEFT_ARM.set(TalonSRXControlMode.PercentOutput, DemandArm);
    }

    /**
     * Control left arm
     * 
     * @param DemandWinch - Target position of winch
     * @param DemandArm   - Target position of arm
     */
    public void controlPositionLeft(DoubleSupplier DemandWinch, DoubleSupplier DemandArm) {
        C_LEFT_WIN.set(TalonSRXControlMode.Position, DemandWinch.getAsDouble());
        C_LEFT_ARM.set(TalonSRXControlMode.Position, DemandArm.getAsDouble());
    }

    /**
     * Control left arm
     * 
     * @param DemandWinch - Target position of winch
     * @param DemandArm   - Target position of arm
     */
    public void controlPositionLeft(Double DemandWinch, Double DemandArm) {
        C_LEFT_WIN.set(TalonSRXControlMode.Position, DemandWinch);
        C_LEFT_ARM.set(TalonSRXControlMode.Position, DemandArm);
    }

    /**
     * Control right arm
     * 
     * @param DemandWinch - Target velocity of winch
     * @param DemandArm   - Target velocity of arm
     */
    public void controlVelocityRight(DoubleSupplier DemandWinch, DoubleSupplier DemandArm) {
        C_RIGHT_WIN.set(TalonSRXControlMode.PercentOutput, DemandWinch.getAsDouble());
        C_RIGHT_ARM.set(TalonSRXControlMode.PercentOutput, DemandArm.getAsDouble());
    }

    /**
     * Control right arm
     * 
     * @param DemandWinch - Target velocity of winch
     * @param DemandArm   - Target velocity of arm
     */
    public void controlVelocityRight(Double DemandWinch, Double DemandArm) {
        C_RIGHT_WIN.set(TalonSRXControlMode.PercentOutput, DemandWinch);
        C_RIGHT_ARM.set(TalonSRXControlMode.PercentOutput, DemandArm);
    }

    /**
     * Control right arm
     * 
     * @param DemandWinch - Target position of winch
     * @param DemandArm   - Target position of arm
     */
    public void controlPositionRight(DoubleSupplier DemandWinch, DoubleSupplier DemandArm) {
        C_RIGHT_WIN.set(TalonSRXControlMode.Position, DemandWinch.getAsDouble());
        C_RIGHT_ARM.set(TalonSRXControlMode.Position, DemandArm.getAsDouble());
    }

    /**
     * Control right arm
     * 
     * @param DemandWinch - Target position of winch
     * @param DemandArm   - Target position of arm
     */
    public void controlPositionRight(Double DemandWinch, Double DemandArm) {
        C_RIGHT_WIN.set(TalonSRXControlMode.Position, DemandWinch);
        C_RIGHT_ARM.set(TalonSRXControlMode.Position, DemandArm);
    }

    /** @return Sensor position of Left arm winch */
    public void getLeftWinchPosition() {
        C_LEFT_WIN.getSelectedSensorPosition();
    }

    /** @return Sensor position of Right arm winch */
    public void getRightWinchPosition() {
        C_RIGHT_WIN.getSelectedSensorPosition();
    }

    /** @return Sensor position of Right arm */
    public void getRightArmPosition() {
        C_RIGHT_ARM.getSelectedSensorPosition();
    }

    /** @return Sensor position of Left arm */
    public void getLeftArmPosition() {
        C_LEFT_ARM.getSelectedSensorPosition();
    }
}
