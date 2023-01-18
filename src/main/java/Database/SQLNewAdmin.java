package Database;

import Models.Admin;
import Models.Employee;
import Models.NewAdmin;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static Classes.CustomAlert.callAlert;
import static Classes.Encryptor.encryptString;
import static Database.SQLConnection.connect;

public class SQLNewAdmin {
    public NewAdmin getAdmin(NewAdmin admin) {
        String command = "SELECT * " +
                "FROM tbl_new_admin " +
                "WHERE admin_username = ? " +
                "AND admin_password = ?";
        try (Connection conn = connect();
             PreparedStatement preparedStatement = conn.prepareStatement(command)) {

            preparedStatement.setString(1, admin.getUsername());
            preparedStatement.setString(2, encryptString(admin.getPassword()));

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                admin.setID(resultSet.getInt("admin_id"));
                admin.setUsername(resultSet.getString("admin_username"));
                admin.setName(resultSet.getString("admin_name"));
                admin.setPassword(resultSet.getString("admin_password"));
                admin.setGrantor(resultSet.getString("admin_grantor"));
                admin.setDisabler(resultSet.getString("admin_disabler"));
                admin.setUsingTheSystem(resultSet.getBoolean("admin_isUsingTheSystem"));
            }

        } catch (SQLException e) {
            System.out.println("Error connecting to SQLite database");
            e.printStackTrace();
        }
        return admin;
    }

    public ObservableList<NewAdmin> getAdminList() {
        ObservableList<NewAdmin> adminList = FXCollections.observableArrayList();
        String command = "SELECT * " +
                "FROM tbl_new_admin";

        try (Connection conn = connect();
             PreparedStatement preparedStatement = conn.prepareStatement(command)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                NewAdmin admin = new NewAdmin();
                admin.setID(resultSet.getInt("admin_id"));
                admin.setUsername(resultSet.getString("admin_username"));
                admin.setName(resultSet.getString("admin_name"));
                admin.setPassword(resultSet.getString("admin_password"));
                admin.setGrantor(resultSet.getString("admin_grantor"));
                admin.setDisabler(resultSet.getString("admin_disabler"));
                admin.setUsingTheSystem(resultSet.getBoolean("admin_isUsingTheSystem"));

                admin.setItems();


                adminList.add(admin);
            }

        } catch (SQLException e) {
            System.out.println("Error connecting to SQLite database");
            e.printStackTrace();
        }
        return adminList;
    }

//    public ObservableList<Admin> getAdminList()

    public boolean getAdminByID(NewAdmin admin, String username, String password) {
        boolean valid = false;
        String command = "SELECT * " +
                "FROM tbl_new_admin " +
                "WHERE admin_id = ? " +
                "AND admin_username = ? " +
                "AND admin_password = ?";
        try (Connection conn = connect();
             PreparedStatement preparedStatement = conn.prepareStatement(command)) {

            preparedStatement.setInt(1, admin.getID());
            preparedStatement.setString(2, username);
            preparedStatement.setString(3, encryptString(password));

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                valid = true;
            }

        } catch (SQLException e) {
            System.out.println("Error connecting to SQLite database");
            e.printStackTrace();
        }
        return valid;
    }

    public boolean checkIfValidAdmin(NewAdmin admin) {
        boolean correct = false;
        String command = "SELECT * " +
                "FROM tbl_new_admin " +
                "WHERE admin_username = ? " +
                "AND admin_password = ?";
        try (Connection conn = connect();
             PreparedStatement preparedStatement = conn.prepareStatement(command)) {

            preparedStatement.setString(1, admin.getUsername());
            preparedStatement.setString(2, encryptString(admin.getPassword()));

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

    public boolean checkIfActiveValidAdmin(NewAdmin admin) {
        boolean correct = false;
        String command = "SELECT * " +
                "FROM tbl_new_admin " +
                "WHERE admin_username = ? " +
                "AND admin_password = ? " +
                "AND admin_disabler = ''";
        try (Connection conn = connect();
             PreparedStatement preparedStatement = conn.prepareStatement(command)) {

            preparedStatement.setString(1, admin.getUsername());
            preparedStatement.setString(2, encryptString(admin.getPassword()));

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

    public void addAdmin(NewAdmin admin) {
        String command = "INSERT INTO tbl_new_admin (admin_username, admin_name, admin_password, admin_grantor, admin_disabler, admin_isUsingTheSystem, admin_isSuperAdmin) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = connect();
             PreparedStatement preparedStatement = conn.prepareStatement(command)) {

            preparedStatement.setString(1, admin.getUsername());
            preparedStatement.setString(2, admin.getName());
            preparedStatement.setString(3, encryptString(admin.getPassword())); //
            preparedStatement.setString(4, admin.getGrantor());
            preparedStatement.setString(5, admin.getDisabler());
            preparedStatement.setBoolean(6, false);
            preparedStatement.setBoolean(7, admin.isSuperAdmin());

            preparedStatement.executeUpdate();
            callAlert("New admin added!", 2);
        } catch (SQLException e) {
            System.out.println("Error connecting to SQLite database");
            e.printStackTrace();
        }
    }

    public void setAdminIsNotUsingTheSystem() {
        String command = "UPDATE tbl_new_admin SET admin_isUsingTheSystem = 0";

        try (Connection conn = connect();
             PreparedStatement preparedStatement = conn.prepareStatement(command)) {

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error connecting to SQLite database");
            e.printStackTrace();
        }
    }

    public void setAdminIsUsingTheSystem(NewAdmin admin) {
        String command = "UPDATE tbl_new_admin SET admin_isUsingTheSystem = 1 WHERE admin_username = ?";

        try (Connection conn = connect();
             PreparedStatement preparedStatement = conn.prepareStatement(command)) {
            preparedStatement.setString(1, admin.getUsername());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error connecting to SQLite database");
            e.printStackTrace();
        }
    }

    public boolean checkIfEmployeeIsAdmin(NewAdmin employee) {
        boolean exist = false;
        String command = "SELECT * " +
                "FROM tbl_new_admin " +
                "WHERE admin_username = ?";

        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(command)) {
            preparedStatement.setString(1, employee.getUsername());

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                exist = true;
            }
        } catch (SQLException e) {
            System.out.println("Error connecting to SQLite database");
            e.printStackTrace();
        }
        return exist;
    }


    public int getAdminCount() {
        int count = 0;
        String command = "SELECT COUNT(*) as admin_count " +
                "FROM tbl_new_admin";

        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(command)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            count = resultSet.getInt("admin_count");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public void deactivateAdmin(NewAdmin admin, NewAdmin adminToDeactivate) {
        String command = "UPDATE tbl_new_admin SET admin_disabler = ? WHERE admin_username = ?";

        try (Connection conn = connect();
             PreparedStatement preparedStatement = conn.prepareStatement(command)) {
            preparedStatement.setString(1, admin.getUsername());
            preparedStatement.setString(2, adminToDeactivate.getUsername());

            preparedStatement.executeUpdate();
            callAlert("Admin deactivated!", 2);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
