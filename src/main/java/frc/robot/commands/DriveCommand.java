//----------------------[Package]----------------------//
package frc.robot.commands;

//----------------------[Library]----------------------//
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.Constants.Functions;
import edu.wpi.first.wpilibj2.command.CommandBase;
import java.util.function.DoubleSupplier;

//-------------------[Drive Command]-------------------//
/** Magnus's drivebase command */
public class DriveCommand extends CommandBase {
    @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
    // -------------------[Constants]-------------------//
    protected final DoubleSupplier DemandVelocity, DemandRotation;
    protected final DriveSubsystem Dependent;
    // -------------------[Properties]------------------//
    protected Boolean isFinished = false;

    // ------------------[Constructors]-----------------//
    /**
     * Constructor.
     * 
     * @param DemandVelocity - Driving velocity
     * @param DemandRotation - Driving rotation
     * @param Dependent      - Subsystem to drive
     */
    public DriveCommand(DoubleSupplier DemandVelocity, DoubleSupplier DemandRotation, Class<?> Driver,
            DriveSubsystem Dependent) {
        this.DemandVelocity = (DemandVelocity
                .getAsDouble() < (Double) Functions.getFieldValue(Driver, "JOYSTICK_Y_DEADZONE")) ? (DemandVelocity)
                        : (() -> 0.0);
        this.DemandRotation = ((DemandRotation.getAsDouble() < Math
                .atan((Double) Functions.getFieldValue(Driver, "JOYSTICK_Y_DEADZONE")
                        / (Double) Functions.getFieldValue(Driver, "JOYSTICK_X_DEADZONE"))) ? (DemandRotation)
                                : (() -> 0.0));
        this.Dependent = Dependent;
        addRequirements(Dependent);
    }

    /**
     * Constructor.
     * 
     * @param DemandVelocity - Driving velocity
     * @param DemandRotation - Driving rotation
     * @param Dependent      - Subsystem to drive
     */
    public DriveCommand(Double DemandVelocity, Double DemandRotation, Class<?> Driver, DriveSubsystem Dependent) {
        this.DemandVelocity = () -> (DemandVelocity < (Double) Functions.getFieldValue(Driver, "JOYSTICK_Y_DEADZONE"))
                ? (DemandVelocity)
                : (0.0);
        this.DemandRotation = () -> ((DemandRotation < Math
                .atan((Double) Functions.getFieldValue(Driver, "JOYSTICK_Y_DEADZONE")
                        / (Double) Functions.getFieldValue(Driver, "JOYSTICK_X_DEADZONE"))) ? (DemandRotation) : (0.0));
        this.Dependent = Dependent;
        addRequirements(Dependent);
    }
    // ---------------------[Command]-------------------//

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        Dependent.arcadeDrive(DemandVelocity, DemandRotation);
        isFinished = true;
    }

    @Override
    public void end(boolean interrupted) {
        isFinished = interrupted;
    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }
}
