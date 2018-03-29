package frc.team6223.robot;


import static java.lang.Math.PI;


public class RobotMap {
    public static int LEFT_DRIVE_CONTROLLER = 1;
    public static int RIGHT_DRIVE_CONTROLLER = 2;

    public static int STAGE_TWO_UPWARD_MOTION = 3;
    public static int STAGE_TWO_DOWNWARD_MOTION_MASTER = 4;
    public static int V_STAGE_TWO_DOWNWARD_MOTION_SECONDARY = 8;

    public static int CLAW_OPEN_CLOSE_MOTION = 5;
    public static int CLAW_WINCH_MOTION = 6;

    public static int STAGE_THREE_UP_DOWN_MOTION_MASTER = 7;
    public static int V_STAGE_THREE_UP_DOWN_MOTION_SECONDARY = 9;

    public static int PDP_CAN_ID = 0;

    // The approximate circumference of the drive train wheels
    public static double wheelRadius = 3.0;
    public static double wheelCircumference = 2 * PI * wheelRadius;
    public static double wheelBaseWidth = 2.4;
}
