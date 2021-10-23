// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import java.util.logging.Logger;

public class Drivetrain extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */
  
  private static final Logger customLogger = Logger.getLogger(Drivetrain.class.getName());

  Spark leftMotorFront = new Spark(Constants.MOTOR_LEFT_FRONT_ID);
  Spark leftMotorBack = new Spark(Constants.MOTOR_LEFT_BACK_ID);
  Spark rightMotorFront = new Spark(Constants.MOTOR_RIGHT_FRONT_ID);
  Spark rightMotorBack = new Spark(Constants.MOTOR_RIGHT_BACK_ID);
  SpeedControllerGroup leftMotors = new SpeedControllerGroup(leftMotorFront, leftMotorBack);
  SpeedControllerGroup rightMotors = new SpeedControllerGroup(rightMotorFront, rightMotorBack);

  DifferentialDrive dualDrive = new DifferentialDrive(leftMotors, rightMotors);

  public void simpleDrive(double leftSpeed, double rightSpeed) {
    customLogger.info("simpleDrive called with leftSpeed: " + leftSpeed + " + rightSpeed: " +  rightSpeed);
    dualDrive.tankDrive(leftSpeed, rightSpeed);
  }

  PowerDistributionPanel PDP = new PowerDistributionPanel(0);

  public double getPDPVoltage() {
    customLogger.info("getPDPVoltage called, returning: " + PDP.getVoltage());
    return PDP.getVoltage();
  }

  Timer localTimer = new Timer();

  public void startLocalTimer() {
    localTimer.start();
  }

  public double getLocalTimer() {
    customLogger.info("getLocalTimer called, returning: " + localTimer.get());
    return localTimer.get();
  }

  public void zeroLocalTimer() {
    customLogger.info("zeroLocalTimer called");
    localTimer.reset();
  }

  Timer testDurationTimer = new Timer();

  public void startTestDurationTimer() {
    customLogger.info("startTestDurationTimer called");
    testDurationTimer.reset();
    testDurationTimer.start();
  }

  public void stopTestDurationTimer() {
    customLogger.info("stopTestDurationTimer called");
    testDurationTimer.stop();
  }

  public double getTestDurationTimer() {
    customLogger.info("getTestDurationTimer called, returning: " + testDurationTimer.get());
    return testDurationTimer.get();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
