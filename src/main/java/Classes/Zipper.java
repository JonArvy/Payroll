package Classes;

import Database.SQLAdminAttendance;
import Models.Attendance;
import Models.Backup;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;

import java.io.File;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static Classes.CustomAlert.callAlert;

public class Zipper {
    private static String database_path = "Database/";
    private static String database_backup_path = "Database/Backup/";

    public static void main(String[] args) {
        SQLAdminAttendance sqlAdminAttendance = new SQLAdminAttendance();
        Attendance attendance = new Attendance();
        attendance.setEmployee_ID(1000);
        attendance.setEmployee_Attendance_Date(Date.valueOf(LocalDate.now()));
        attendance.setEmployee_TimeIn(Time.valueOf(LocalTime.now()));
        attendance.setEmployee_TimeOut(Time.valueOf(LocalTime.now()));
        sqlAdminAttendance.registerAttendanceUsingQR(attendance);
    }

    public static ObservableList<Backup> listAllFiles() {
        ObservableList<Backup> filelist = FXCollections.observableArrayList();
        File folder = new File(database_backup_path);
        filelist = listFilesForFolder(folder);

        return filelist;
    }

    public static ObservableList<Backup> listFilesForFolder(final File folder) {
        ObservableList<Backup> filelist = FXCollections.observableArrayList();
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesForFolder(fileEntry);
            } else {
                String filename = fileEntry.getName().replace(".zip", "").replace("-", "/").replace(";", ":");
                filelist.add(new Backup(filename));
//                System.out.println(filename);
            }
        }
        return filelist;
    }


    public static void createBackupFromCurrentTimeStamp() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH;mm;ss");
        LocalDateTime now = LocalDateTime.now();
        startDatabaseBackupZipping(dtf.format(now));
//        System.out.println(dtf.format(now));
    }

    public static void startDatabaseBackupZipping(String name) {
        try {
            new ZipFile(database_backup_path + name + ".zip").addFile(new File(database_path + "payroll.db"));
        } catch (ZipException e) {
            throw new RuntimeException(e);
        }
    }

    public static void loadBackupUsingRawName(String name) {
        startDatabaseRecoveryUnZipping(name.replace("/", "-").replace(":", ";"));
        callAlert("Backup has been loaded: " + name, 2);
    }

    public static void startDatabaseRecoveryUnZipping(String name) {
        try {
            new ZipFile(database_backup_path+ name + ".zip").extractAll(database_path);

        } catch (ZipException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteBackup(String name) {
        String path = database_backup_path + name.replace("/", "-").replace(":", ";") + ".zip";
        File backup = new File(path);
        if (backup.delete()) {
//            System.out.println();
            callAlert("Deleted the backup: " + backup.getName(), 2);
        } else {
            System.out.println("Failed to delete the backup.");
        }
    }
}
