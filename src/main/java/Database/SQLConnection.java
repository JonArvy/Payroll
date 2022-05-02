package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnection {
    public static Connection connect() {
        String jdbcUrl = "jdbc:sqlite:/C:\\Users\\Arvy Enriquez\\Downloads\\DB\\sqlite-tools-win32-x86-3380200\\payroll.db";
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(jdbcUrl);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }
}
