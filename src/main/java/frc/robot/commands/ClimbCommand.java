package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class ClimbCommand extends CommandBase
{
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    //-------------------[Constants]-------------------// 
    //-------------------[Properties]------------------//
    protected Boolean isFinished = false;
    //------------------[Constructors]-----------------//

    //---------------------[Command]-------------------//
    @Override
    public void initialize() {}

    @Override
    public void execute() {isFinished = true;}

    @Override
    public void end(boolean interrupted) {isFinished = interrupted;}

    @Override
    public boolean isFinished() {return isFinished;}
}
