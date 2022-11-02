package Database;

import Models.Admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static Classes.CustomAlert.callAlert;
import static Database.SQLConnection.connect;

public class SQLAdmin {
    public Admin getAdmin(Admin admin) {
        String command = "SELECT * " +
                "FROM tbl_admin " +
                "WHERE emp_id = ? " +
                "AND admin_password = ?";
        try (Connection conn = connect();
             PreparedStatement preparedStatement = conn.prepareStatement(command)) {

            preparedStatement.setInt(1, admin.getEmployee_ID());
            preparedStatement.setString(2, admin.getAdmin_Password());

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                admin.setAdmin_ID(resultSet.getInt("admin_id"));
                admin.setEmployee_ID(resultSet.getInt("emp_id"));
                admin.setAdmin_Password(resultSet.getString("admin_password"));
                admin.setAdmin_Grantor(resultSet.getInt("admin_grantor"));
                admin.setAdmin_Disabler(resultSet.getInt("admin_disabler"));
            }

        } catch (SQLException e) {
            System.out.println("Error connecting to SQLite database");
            e.printStackTrace();
        }
        return admin;
    }

    public boolean checkIfValidAdmin(Admin admin) {
        boolean correct = false;
        String command = "SELECT * " +
                "FROM tbl_admin " +
                "WHERE emp_id = ? " +
                "AND admin_password = ?";
        try (Connection conn = connect();
             PreparedStatement preparedStatement = conn.prepareStatement(command)) {

            preparedStatement.setInt(1, admin.getEmployee_ID());
            preparedStatement.setString(2, admin.getAdmin_Password());

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                correct = true;
            }

        } catch (SQLException e) {
            System.out.println("Error connecting to SQLite database");
            e.printStackTrace();
        }
        return correct;
    }

    public void addAdmin(Admin admin) {
        String command = "INSERT INTO tbl_admin (emp_id, admin_password, admin_grantor, admin_disabler) " +
                "VALUES (?, ?, ?, ?)";
        try (Connection conn = connect();
             PreparedStatement preparedStatement = conn.prepareStatement(command)) {

            preparedStatement.setInt(1, admin.getEmployee_ID());
            preparedStatement.setString(2, admin.getAdmin_Password()); //
            preparedStatement.setInt(3, admin.getAdmin_Grantor());
            preparedStatement.setInt(4, admin.getAdmin_Disabler());

            preparedStatement.executeUpdate();
            callAlert("New admin added!", 2);
        } catch (SQLException e) {
            System.out.println("Error connecting to SQLite database");
            e.printStackTrace();
        }
    }

    public void editAdmin(Admin admin) {
        String command = "UPDATE tbl_bonus" +
                "SET emp_id = ?," +
                "    admin_password = ?," +
                "    admin_grantor = ?, " +
                "    admin_disabler = ?" +
                "WHERE admin_id = ?";

        String admin_tbl = "CREATE TABLE IF NOT EXISTS tbl_admin (" +
                "admin_id INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE, " +
                "emp_id INTEGER CONSTRAINT fk_emp_id_attendance REFERENCES tbl_employees(emp_id) ON DELETE SET NULL ON UPDATE CASCADE, " +
                "admin_password VARCHAR(200)," +
                "admin_grantor INTEGER," +
                "admin_disabler INTEGER)";
        try (Connection conn = connect();
             PreparedStatement preparedStatement = conn.prepareStatement(command)) {

            preparedStatement.setInt(1, admin.getEmployee_ID());
            preparedStatement.setString(2, admin.getAdmin_Password()); //
            preparedStatement.setInt(3, admin.getAdmin_Grantor());
            preparedStatement.setInt(4, admin.getAdmin_Disabler());
            preparedStatement.setInt(5, admin.getAdmin_ID());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error connecting to SQLite database");
            e.printStackTrace();
        }
    }
}
