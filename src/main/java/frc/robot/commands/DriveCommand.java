//----------------------[Package]----------------------//
package frc.robot.commands;
//----------------------[Library]----------------------//
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.Functions;
import frc.robot.subsystems.DriveSubsystem;
import java.util.function.DoubleSupplier;
//-------------------[Drive Command]-------------------//
/**
 * Magnus's drivebase command
 */
public class DriveCommand extends CommandBase
{
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    //-------------------[Constants]-------------------//    
    protected final DoubleSupplier DemandVelocity, DemandRotation;
    protected final DriveSubsystem Dependent;
    //-------------------[Properties]------------------//
    protected Boolean isFinnished = false;
    //------------------[Constructors]-----------------//
    /**
     * Constructor.
     * @param DemandVelocity - Driving velocity
     * @param DemandRotation - Driving rotation
     * @param Dependent - Subsystem to drive
     */
    public DriveCommand(DoubleSupplier DemandVelocity, DoubleSupplier DemandRotation, Class<?> Driver, DriveSubsystem Dependent)
    {
        this.DemandVelocity = (DemandVelocity.getAsDouble() > (Double)Functions.deriveField(Driver,"JOYSTICK_Y_DEADZONE"))? (DemandVelocity): (() -> 0.0);;
        this.DemandRotation = (DemandRotation.getAsDouble() > (Math.sqrt(Math.pow((Double)Functions.deriveField(Driver,"JOYSTICK_X_DEADZONE"),2) + 
        Math.pow((Double)Functions.deriveField(Driver,"JOYSTICK_Y_DEADZONE"),2))))? (DemandVelocity): (() -> 0.0);
        this.Dependent = Dependent;
        addRequirements(Dependent);
    }
    /**
     * Constructor.
     * @param DemandVelocity - Driving velocity
     * @param DemandRotation - Driving rotation
     * @param Dependent - Subsystem to drive
     */
    public DriveCommand(Double DemandVelocity, Double DemandRotation, Class<?> Driver, DriveSubsystem Dependent)
    {
        this.DemandVelocity = () -> (DemandVelocity > (Double)Functions.deriveField(Driver,"JOYSTICK_Y_DEADZONE"))? (DemandVelocity): (0.0);
        this.DemandRotation = () -> (DemandRotation > (Math.sqrt(Math.pow((Double)Functions.deriveField(Driver,"JOYSTICK_X_DEADZONE"),2) + 
        Math.pow((Double)Functions.deriveField(Driver,"JOYSTICK_Y_DEADZONE"),2))))? (DemandVelocity): (0.0);
        this.Dependent = Dependent;
        addRequirements(Dependent);
    }
    //---------------------[Command]-------------------//

    @Override
    public void initialize() {}

    @Override
    public void execute()
    {
        Dependent.arcadeDrive(DemandVelocity, DemandRotation); isFinnished = true;
    }

    @Override
    public void end(boolean interrupted) {isFinnished = interrupted;}

    @Override
    public boolean isFinished() {return isFinnished;}
}
