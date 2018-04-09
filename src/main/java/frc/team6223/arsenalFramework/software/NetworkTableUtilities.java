package frc.team6223.arsenalFramework.software;


import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;


public class NetworkTableUtilities {

    public static NetworkTable getRobotTable() {
        return NetworkTableInstance.getDefault().getTable("SmartDashboard");
    }

    public static NetworkTable getRobotSubtable(String name) {
        return getRobotTable().getSubTable(name);
    }

    public static NetworkTable getGeneralSubtable(NetworkTable table, String name) {
        return table.getSubTable(name);
    }

    public static void putItemInTable(NetworkTable table, String name, String entry) {
        table.getEntry(name).setString(entry);
    }

    public static void putItemInTable(NetworkTable table, String name, Number entry) {
        table.getEntry(name).setNumber(entry);
    }

    public static void putItemInTable(NetworkTable table, String name, boolean bool) {
        table.getEntry(name).setBoolean(bool);
    }

}
