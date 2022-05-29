package Database;

import Models.Department;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

import static Classes.CustomAlert.callAlert;
import static Database.SQLConnection.connect;

public class SQLDepartment {
    public ObservableList<Department> getDepartment() {
        ObservableList<Department> departmentList = FXCollections.observableArrayList();
        String command = "SELECT * FROM tbl_department";
        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(command)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                departmentList.add(new Department(
                                resultSet.getInt("department_id"),

                                resultSet.getString("department_name"),
                                resultSet.getDouble("department_monthlyrate"),
                                resultSet.getInt("department_dayspermonth"),
                                resultSet.getInt("department_hoursperday")
                        )
                );
            }

        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
        return departmentList;

    }

    public Department getDepartment(Department department) {
        String command = "SELECT * FROM tbl_department " +
                "WHERE department_id = ?";
        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(command)) {

            preparedStatement.setInt(1, department.getDepartment_ID());

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                department.setDepartment_ID(resultSet.getInt("department_id"));
                department.setDepartment_Name(resultSet.getString("department_name"));
                department.setDepartment_MonthlyRate(resultSet.getDouble("department_monthlyrate"));
                department.setDepartment_DaysPerMonth(resultSet.getInt("department_dayspermonth"));
                department.setDepartment_HoursPerDay(resultSet.getInt("department_hoursperday"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return department;

    }

    public void addDepartment(Department department) {
        String command = "INSERT INTO tbl_department (department_name, department_monthlyrate, department_dayspermonth, department_hoursperday)" +
                "VALUES (?, ?, ?, ?)";
        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(command)) {

            preparedStatement.setString(1, department.getDepartment_Name());
            preparedStatement.setDouble(2, department.getDepartment_MonthlyRate()); //
            preparedStatement.setInt(3, department.getDepartment_DaysPerMonth());
            preparedStatement.setInt(4, department.getDepartment_HoursPerDay());

            preparedStatement.executeUpdate();

            callAlert("Success", "Department " + department.getDepartment_Name() + " has been added");
        } catch (SQLException e) {
            System.out.println("Error connecting to SQLite database");
            e.printStackTrace();
        }
    }

    public void editDepartment(Department department) {
        String command = "UPDATE tbl_department " +
                "SET department_name = ?," +
                "    department_monthlyrate = ?," +
                "    department_dayspermonth = ?, " +
                "    department_hoursperday = ?" +
                "WHERE department_id = ?";
        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(command)) {

            preparedStatement.setString(1, department.getDepartment_Name());
            preparedStatement.setDouble(2, department.getDepartment_MonthlyRate()); //
            preparedStatement.setInt(3, department.getDepartment_DaysPerMonth());
            preparedStatement.setInt(4, department.getDepartment_HoursPerDay());
            preparedStatement.setInt(5, department.getDepartment_ID());

            preparedStatement.executeUpdate();

            callAlert("Success", "Department " + department.getDepartment_Name() + " has been updated");
        } catch (SQLException e) {
            System.out.println("Error connecting to SQLite database");
            e.printStackTrace();
        }
    }

    public boolean checkIfDepartmentNameExists(int deptid, String deptname) {
        boolean exist = false;
        String command = "SELECT * " +
                "FROM tbl_department te " +
                "WHERE department_name = ?";

        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(command)) {

            preparedStatement.setString(1, deptname);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                exist = true;
                if (resultSet.getInt("department_id") == deptid) {
                    exist = false;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error connecting to SQLite database");
            e.printStackTrace();
        }
        return exist;
    }

    public boolean checkIfDepartmentNameExists(String deptname) {
        boolean exist = false;
        String command = "SELECT * " +
                "FROM tbl_department te " +
                "WHERE department_name = ?";

        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(command)) {

            preparedStatement.setString(1, deptname);

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
}
