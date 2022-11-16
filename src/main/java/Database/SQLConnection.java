package Database;

import cw.payroll.Main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnection {
    public static Connection connect() {
//        String raw = Main.class.getClassLoader().getResource("").getPath();
//        String filepath = raw.replace("target/classes/", "src/main/java/Tools/");
//        filepath = filepath.substring(1).replace("%20", " ");
//        filepath = filepath.replace("/", "\\");

        String jdbcUrl = "jdbc:sqlite:Database/payroll.db";
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(jdbcUrl);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }
}
