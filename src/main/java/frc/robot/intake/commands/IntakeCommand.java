package frc.robot.intake.commands;

import java.lang.reflect.Executable;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.OIConstants;
import frc.robot.intake.Intake;

public class IntakeCommand extends CommandBase {

    private Intake intake;
    private Joystick operator;

    private double intakeAxis;

    public IntakeCommand(Intake intake, Joystick operator) {
        this.intake = intake;
        this.operator = operator;
    }

    @Override
    public void execute() {
        intakeAxis = -operator.getRawAxis(OIConstants.rightTriggerPS4);
        if (Math.abs(intakeAxis) > 0.07 && !intake.isDeployed())
            intake.deploy();
        else if (Math.abs(intakeAxis) < 0.07 && intake.isDeployed())
            intake.retract();
        
        intake.setIntake(-intakeAxis);
    }
}