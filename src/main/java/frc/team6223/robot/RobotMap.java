package frc.team6223.robot;


import static java.lang.Math.PI;


public class RobotMap {
    static int LEFT_DRIVE_CONTROLLER = 1;
    static int RIGHT_DRIVE_CONTROLLER = 2;

    static int STAGE_TWO_UPWARD_MOTION = 3;
    static int STAGE_TWO_DOWNWARD_MOTION_MASTER = 4;
    static int V_STAGE_TWO_DOWNWARD_MOTION_SECONDARY = 8;

    static int CLAW_OPEN_CLOSE_MOTION = 5;
    static int CLAW_WINCH_MOTION = 6;

    static int STAGE_THREE_UP_DOWN_MOTION_MASTER = 7;
    static int V_STAGE_THREE_UP_DOWN_MOTION_SECONDARY = 9;

    static int PDP_CAN_ID = 0;

    // The approximate circumference of the drive train wheels
    static double wheelRadius = 3.0;
    static double wheelCircumference = 2 * PI * wheelRadius;
    static double wheelBaseWidth = 2.4;
}
