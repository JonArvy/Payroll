package Classes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static Classes.CustomAlert.callAlert;

public class DirectoryWriter {
    public static void createDirectory() {
        String temp_path = "output/temp/";
        String bonus_path = "output/Bonus Summary/";
        String payroll_path = "output/Payroll Summary/";
        String payslip_path = "output/Payslips/";
        String database_path = "Database/";
        String database_backup_path = "Database/Backup/";
        String qr_path = "output/qr/";
        try {

            Path path = Paths.get(temp_path);
            Path path1 = Paths.get(bonus_path);
            Path path2 = Paths.get(payroll_path);
            Path path3 = Paths.get(payslip_path);
            Path path4 = Paths.get(database_path);
            Path path5 = Paths.get(database_backup_path);
            Path path6 = Paths.get(qr_path);

            //java.nio.file.Files;
            Files.createDirectories(path);
            Files.createDirectories(path1);
            Files.createDirectories(path2);
            Files.createDirectories(path3);
            Files.createDirectories(path4);
            Files.createDirectories(path5);
            Files.createDirectories(path6);

//            callAlert("Directory is created!", 2);

        } catch (IOException e) {

            System.err.println("Failed to create directory!" + e.getMessage());

        }
    }
}
