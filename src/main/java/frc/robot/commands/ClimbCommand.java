//----------------------[Package]----------------------//
package frc.robot.commands;

import java.util.function.DoubleSupplier;

//----------------------[Library]----------------------//
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClimbSubsystem;

//-------------------[Climb Command]-------------------//
/** Magnus's drivebase command */
public class ClimbCommand extends CommandBase {
    @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
    // -------------------[Constants]-------------------//
    protected final ClimbSubsystem Dependent;
    // -------------------[Properties]------------------//
    protected Boolean isFinished = false;

    // ------------------[Constructors]-----------------//
    public ClimbCommand(DoubleSupplier DemandArm, DoubleSupplier DemandWinch, Integer DependentSides, Class<?> Driver,
            ClimbSubsystem Dependent) {
        this.Dependent = Dependent;
    }

    public ClimbCommand(Double DemandArm, Double DemandWinch, Integer DependentSides, Class<?> Driver,
            ClimbSubsystem Dependent) {
        this.Dependent = Dependent;
    }

    public ClimbCommand(Integer DependentSides, Class<?> Driver, ClimbSubsystem Dependent) {
        this.Dependent = Dependent;
    }

    // ---------------------[Command]-------------------//
    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        // TODO: Implement Tommorow
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
