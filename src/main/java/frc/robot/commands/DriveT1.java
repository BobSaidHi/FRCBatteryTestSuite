// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.Robot;
import frc.robot.subsystems.Drivetrain;
//import frc.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;
import java.util.logging.Logger;

/** An example command that uses an example subsystem. */
public class DriveT1 extends CommandBase {
  
  private static final Logger customLogger = Logger.getLogger(DriveT1.class.getName());

  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final Drivetrain dt;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public DriveT1(Drivetrain dt) {
    this.dt = dt;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(dt);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    dt.zeroLocalTimer();
    dt.startLocalTimer();
    dt.startTestDurationTimer();
    customLogger.info("initialize complete");
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double left = 0, right = 0;
    // TO DO: change values based on time [Done]
    //double localTimer = 0;
    int rest1time = 30;
    int go2time = 45;
    int rest2time = 75;
    int go3time = 90;
    int rest3time = 120;
    int go4time = 135;
    int rest4time = 150;
    int endCycleTime = 165;

    if(dt.getLocalTimer() < rest1time ) {
      left = 1.0;
      right = 1.0;
    }
    else if(dt.getLocalTimer() >= rest1time && dt.getLocalTimer() < go2time) {
      left = 0.0; right =0.0;
    }
    else if(dt.getLocalTimer() >= go2time && dt.getLocalTimer() < rest2time) {
      left = -1.0; right = 0.0;
    }
    else if(dt.getLocalTimer() >= rest2time && dt.getLocalTimer() < go3time) {
      left = 0.0; right = 0.0;
    }
    else if(dt.getLocalTimer() >= go3time && dt.getLocalTimer() < rest3time) {
      left = -0.0; right = -1.0;
    }
    else if(dt.getLocalTimer() >= rest3time && dt.getLocalTimer() < go4time) {
      left = 0.0; right = 0.0;
    }
    else if(dt.getLocalTimer() >= go4time && dt.getLocalTimer() < rest4time) {
      left = -1.0; right = -1.0;
    }
    else if(rest4time >= go4time && dt.getLocalTimer() < endCycleTime) {
      left = 0.0; right = 0.0;
    }
    else if(dt.getLocalTimer() >= endCycleTime) {
      customLogger.info("Reseting Cycle");
      left = 0.0; right = 0.0;
      dt.zeroLocalTimer();
    }
    else {
      customLogger.warning("Somethings wrong...");
      left = 0.0; right =0.0;
    }

    Robot.drivetrain.simpleDrive(left, right);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(Robot.drivetrain.getPDPVoltage() <= 6.8) {
      Robot.drivetrain.simpleDrive(0, 0);
      dt.stopTestDurationTimer();
      //System.out.println("FPGA Time: " + getFPGATimestamp());
      //System.out.println("Total test Duration: " + dt.getTestDurationTimer());
      customLogger.info("Total test Duration: " + dt.getTestDurationTimer());
      return true;
    }
    else {
      return false;
    }
  }
}
