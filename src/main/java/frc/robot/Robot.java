
package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.CommandScheduler;


public class Robot extends TimedRobot {
  RobotContainer robotContainer;

  String ip = "pi@wpilibpi";
  short port = 5050;

  @Override
  public void robotInit() {
    CameraServer.startAutomaticCapture("Camera 0", 0);
    CameraServer.startAutomaticCapture("Camera 1", 1);
    robotContainer = new RobotContainer();
    robotContainer.initRobotCommands();
    CommandScheduler.getInstance().run();
  }

  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();
    robotContainer.periodic();
  }

  @Override
  public void autonomousInit() {
    robotContainer.getAuto().schedule();
  }

  @Override
  public void autonomousExit() {
    // cancel all autonomous commands so they don't continue into tele-op :)
    CommandScheduler.getInstance().cancelAll();
  }

  @Override
  public void teleopInit() {}

  @Override
  public void teleopPeriodic() {}

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void testInit() {}

  @Override
  public void testPeriodic() {}
}
