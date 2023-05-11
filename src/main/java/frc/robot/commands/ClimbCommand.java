//----------------------[Package]----------------------//
package frc.robot.commands;

//----------------------[Library]----------------------//
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClimbSubsystem;
import java.util.function.DoubleSupplier;

//-------------------[Climb Command]-------------------//
/** Magnus's drivebase command */
public class ClimbCommand extends CommandBase {
    @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
    // -------------------[Constants]-------------------//
    protected final DoubleSupplier DemandArm, DemandWinch;
    protected final Integer DemandSides;
    protected final Boolean DemandType;
    protected final ClimbSubsystem Dependent;
    // -------------------[Properties]------------------//
    protected Boolean isFinished = false;
    // ------------------[Constructors]-----------------//
    public ClimbCommand(DoubleSupplier DemandArm, DoubleSupplier DemandWinch, Integer DemandSides, Boolean DemandType,
            ClimbSubsystem Dependent) {
        this.DemandArm = DemandArm;
        this.DemandWinch = DemandWinch;
        this.DemandSides = DemandSides;
        this.DemandType = DemandType;
        this.Dependent = Dependent;
        addRequirements(Dependent);
    }

    public ClimbCommand(Double DemandArm, Double DemandWinch, Integer DemandSides, Boolean DemandType,
            ClimbSubsystem Dependent) {
        this.DemandArm = () -> DemandArm;
        this.DemandWinch = () -> DemandWinch;
        this.DemandSides = DemandSides;
        this.DemandType = DemandType;
        this.Dependent = Dependent;
        addRequirements(Dependent);
    }

    public ClimbCommand(Integer DemandSides, Boolean DemandType, ClimbSubsystem Dependent) {
        this.DemandArm = () -> (0.0);
        this.DemandWinch = () -> (0.0);
        this.DemandSides = DemandSides;
        this.DemandType = DemandType;
        this.Dependent = Dependent;
        addRequirements(Dependent);
    }

    // ---------------------[Command]-------------------//
    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        switch (DemandSides) {
            // Left Side
            case 0:
                if (DemandType) {
                    Dependent.controlPositionLeft(DemandWinch, DemandArm);
                    break;
                }
                Dependent.controlVelocityLeft(DemandWinch, DemandArm);
                break;

            // Right Side
            case 1:
                if (this.DemandType) {
                    Dependent.controlPositionRight(DemandWinch, DemandArm);
                    break;
                }
                Dependent.controlVelocityRight(DemandWinch, DemandArm);
                break;

            // Both Sides
            case 2:
                if (this.DemandType) {
                    Dependent.controlPositionRight(DemandWinch, DemandArm);
                    Dependent.controlPositionLeft(DemandWinch, DemandArm);
                    break;
                }
                Dependent.controlVelocityRight(DemandWinch, DemandArm);
                Dependent.controlVelocityLeft(DemandWinch, DemandArm);
                break;
        }
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
