//----------------------[Package]----------------------//
package frc.robot.subsystems;
//----------------------[Library]----------------------//
import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.Constants.Functions;
import frc.robot.Constants.Drive.HardwareInformation;
import frc.robot.Constants.Drive.MotorPorts;
import frc.robot.Constants.Drive.Data.PID;
import com.ctre.phoenix.motorcontrol.TalonFXInvertType;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import java.util.Objects;
import java.util.function.DoubleSupplier;
//------------------[Drive Subsystem]------------------//
/**
 * Magnus' drivetrain subsystem
 * TODO: Add Simulator support.
 * TODO: Obtain drivetrain measurements
 * TODO: Obtain drivetain wheel measurements
 * TODO: Obtain drivetrain PIDS
 */
public class DriveSubsystem extends SubsystemBase {
    //-------------------[Constants]-------------------//
    protected final WPI_TalonFX FRONT_LEFT; protected final WPI_TalonFX FRONT_RIGHT;
    protected final WPI_TalonFX REAR_LEFT; protected final WPI_TalonFX REAR_RIGHT; 
    protected final MotorControllerGroup DB_LEFT; protected final MotorControllerGroup DB_RIGHT;
    protected final PIDController DB_L_PID; protected final PIDController DB_R_PID;
    protected final DifferentialDrive DB_DRIVEBASE;  protected final DifferentialDriveKinematics DB_KINEMATICS;
    protected final DifferentialDriveWheelSpeeds DB_WHEEL_SPEEDS; protected final Timer DB_PERIODIC_TIME;
    protected static final Double DB_TICK_CONVERSION = (2.0 * Math.PI * (HardwareInformation.WHEEL_DIAMETER/2) / HardwareInformation.ENCODER_RESOLUTION);
    //-------------------[Properties]------------------//   
    protected Rotation2d DB_Heading; protected Boolean DB_Mode; protected Double DB_Speed_Coefficienct; protected Class<?> DB_Driver;
    //------------------[Constructors]-----------------//
    /**
     * Constructor.
     */
    public DriveSubsystem(Class<?> Driver)
    {
        FRONT_LEFT = new WPI_TalonFX(MotorPorts.FL); FRONT_RIGHT = new WPI_TalonFX(MotorPorts.FR);
        REAR_LEFT = new WPI_TalonFX(MotorPorts.RL); REAR_RIGHT = new WPI_TalonFX(MotorPorts.RR);
        DB_LEFT = new MotorControllerGroup(FRONT_LEFT,REAR_LEFT); DB_RIGHT = new MotorControllerGroup(FRONT_RIGHT,REAR_RIGHT);
        DB_L_PID = new PIDController(PID.L_KP,PID.L_KI,PID.L_KP); DB_R_PID = new PIDController(PID.R_KP,PID.R_KI,PID.R_KP);
        DB_DRIVEBASE = new DifferentialDrive(DB_LEFT, DB_RIGHT); DB_KINEMATICS = new DifferentialDriveKinematics(HardwareInformation.DIMENSIONS[0]);
        DB_WHEEL_SPEEDS = new DifferentialDriveWheelSpeeds(0.0,0.0); DB_PERIODIC_TIME = new Timer();
        DB_Heading = new Rotation2d(0.0);
        DB_Mode = false;
        DB_Speed_Coefficienct = 1.0;
        DB_Driver = Driver;
    }
    /**
     * Constructor.
     * @param FL - Front left motorcontroller port
     * @param FR - Front right motorcontroller port
     * @param RL - Rear left motorcontroller port
     * @param RR - Rear right motorcontroller port
     */
    public DriveSubsystem(Integer FL, Integer FR, Integer RL, Integer RR)
    {
        Objects.requireNonNull(FL,"Front left motor controller port cannot be Null");
        Objects.requireNonNull(FR,"Front right motor controller port Cannot be Null");
        Objects.requireNonNull(RL,"Rear left motor controller port Cannot be Null");
        Objects.requireNonNull(RR,"Rear right motor controller port Cannot be Null");
        FRONT_LEFT = new WPI_TalonFX(FL); FRONT_RIGHT = new WPI_TalonFX(FR);
        REAR_LEFT = new WPI_TalonFX(RL); REAR_RIGHT = new WPI_TalonFX(RR);
        DB_LEFT = new MotorControllerGroup(FRONT_LEFT,REAR_LEFT); DB_RIGHT = new MotorControllerGroup(FRONT_RIGHT,REAR_RIGHT);
        DB_L_PID = new PIDController(PID.L_KP,PID.L_KI,PID.L_KP); DB_R_PID = new PIDController(PID.R_KP,PID.R_KI,PID.R_KP);
        DB_DRIVEBASE = new DifferentialDrive(DB_LEFT, DB_RIGHT); DB_KINEMATICS = new DifferentialDriveKinematics(HardwareInformation.DIMENSIONS[0]);
        DB_WHEEL_SPEEDS = new DifferentialDriveWheelSpeeds(0.0,0.0); DB_PERIODIC_TIME = new Timer();
        DB_Heading = new Rotation2d(0.0);
        DB_Mode = false;
    }
    //-----------------[Drive Control]-----------------//
    /**
     * Basic arcade drive control
     * @param Velocity
     * @param Rotation
     */
    public void arcadeDrive(Double Velocity, Double Rotation) 
    {DB_DRIVEBASE.arcadeDrive((DB_Mode)? (-Velocity * 0.20): (-Velocity * DB_Speed_Coefficienct),(DB_Mode)? (-Rotation * 0.20): (-Rotation));}
    /**
     * Basic arcade drive control
     * @param Velocity
     * @param Rotation
     */
    public void arcadeDrive(DoubleSupplier Velocity, DoubleSupplier Rotation) 
    {DB_DRIVEBASE.arcadeDrive((DB_Mode)? (-Velocity.getAsDouble() * 0.20): (-Velocity.getAsDouble() * DB_Speed_Coefficienct),(DB_Mode)? (-Rotation.getAsDouble() * 0.20): (-Rotation.getAsDouble()));}
    /**
     * Pose2d arcade drive control
     * @param Target - Pose2d Target
     */
    public void arcadeDrive(Pose2d Demand) 
    {
        var Velocity = (Math.sqrt(Math.pow(Demand.getY(),2) + Math.pow(Demand.getX(),2)));
        var Rotation = Demand.getRotation().getDegrees();
        DB_DRIVEBASE.arcadeDrive((DB_Mode)? (-Velocity * 0.20): (-Velocity * DB_Speed_Coefficienct),(DB_Mode)? (-Velocity * 0.20): (-Rotation));
    }
    /**
     * Configure magnus' drivebase devices
     */
    public void configureDrivebase()
    {
        FRONT_LEFT.configFactoryDefault(); FRONT_RIGHT.configFactoryDefault();
        REAR_LEFT.configFactoryDefault(); REAR_RIGHT.configFactoryDefault();
        REAR_LEFT.follow(FRONT_LEFT); REAR_RIGHT.follow(FRONT_RIGHT);        
        FRONT_LEFT.setNeutralMode(NeutralMode.Brake); FRONT_RIGHT.setNeutralMode(NeutralMode.Brake);
        REAR_LEFT.setNeutralMode(NeutralMode.Brake); REAR_LEFT.setNeutralMode(NeutralMode.Brake);
        FRONT_LEFT.setInverted(TalonFXInvertType.Clockwise); FRONT_RIGHT.setInverted(TalonFXInvertType.CounterClockwise);
        REAR_LEFT.setInverted(TalonFXInvertType.Clockwise); REAR_RIGHT.setInverted(TalonFXInvertType.CounterClockwise); 
    }
    /** Toggle the current driving mode */
    public void toggleDrivingMode() {this.DB_Mode = !this.DB_Mode;}
    /**
     * Set a new driving mode
     * @param Mode - Desired driving mode
     */
    public void setDrivingMode(Boolean Mode) {this.DB_Mode = Mode;}

    /** Decrement driving speed coefficent */
    public void decrementCoefficient()
    {
        DB_Speed_Coefficienct -= (Double)Functions.deriveField(DB_Driver,"SPEED_COEFFICENT_SENSITIVIY");
    }
    /** Increment driving speed coefficent */
    public void incrementCoefficient()
    {DB_Speed_Coefficienct += (Double)Functions.deriveField(DB_Driver,"SPEED_COEFFICENT_SENSITIVIY");
    }
    //-------------------[Subsystem]-------------------//
    @Override
    public void periodic()
    {
        DB_WHEEL_SPEEDS.leftMetersPerSecond = Math.max(FRONT_LEFT.getSelectedSensorVelocity(),REAR_LEFT.getSelectedSensorVelocity()) * DB_TICK_CONVERSION;
        DB_WHEEL_SPEEDS.rightMetersPerSecond = Math.max(FRONT_LEFT.getSelectedSensorVelocity(),FRONT_RIGHT.getSelectedSensorVelocity()) * DB_TICK_CONVERSION;
        var DB_ChassisSpeeds = DB_KINEMATICS.toChassisSpeeds(DB_WHEEL_SPEEDS);
        DB_Heading = new Rotation2d(DB_ChassisSpeeds.omegaRadiansPerSecond + (DB_Heading.getRadians()));
    }
    @Override
    public void simulationPeriodic()
    {

    }
    //-------------------[Accessors]-------------------//

    //-------------------[Mutators]--------------------// 
}

