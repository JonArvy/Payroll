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
                department.setDaily_Rate(resultSet.getDouble("department_monthlyrate") / resultSet.getInt("department_dayspermonth"));
                department.setHourly_Rate(resultSet.getDouble("department_monthlyrate") / resultSet.getInt("department_dayspermonth") / resultSet.getInt("department_hoursperday"));
                department.setDepartment_DaysPerMonth(resultSet.getInt("department_dayspermonth"));
                department.setDepartment_HoursPerDay(resultSet.getInt("department_hoursperday"));

                department.setTime_In(resultSet.getTime("shift_in"));
                department.setTime_Out(resultSet.getTime("shift_out"));
                department.setBreak_Start(resultSet.getTime("shift_breakstart"));
                department.setBreak_End(resultSet.getTime("shift_breakend"));

                department.setShift_Sunday(resultSet.getBoolean("shift_sunday"));
                department.setShift_Monday(resultSet.getBoolean("shift_monday"));
                department.setShift_Tuesday(resultSet.getBoolean("shift_tuesday"));
                department.setShift_Wednesday(resultSet.getBoolean("shift_wednesday"));
                department.setShift_Thursday(resultSet.getBoolean("shift_thursday"));
                department.setShift_Friday(resultSet.getBoolean("shift_friday"));
                department.setShift_Saturday(resultSet.getBoolean("shift_saturday"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return department;

    }

    public void addDepartment(Department department) {
        String command = "INSERT INTO tbl_department (department_name, department_monthlyrate, department_dayspermonth, department_hoursperday," +
                "shift_in, shift_out, shift_breakstart, shift_breakend," +
                "shift_sunday, shift_monday, shift_tuesday, shift_wednesday, shift_thursday, shift_friday, shift_saturday)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(command)) {

            preparedStatement.setString(1, department.getDepartment_Name());
            preparedStatement.setDouble(2, department.getDepartment_MonthlyRate()); //
            preparedStatement.setInt(3, department.getDepartment_DaysPerMonth());
            preparedStatement.setInt(4, department.getDepartment_HoursPerDay());
            preparedStatement.setTime(5, department.getTime_In());
            preparedStatement.setTime(6, department.getTime_Out());
            preparedStatement.setTime(7, department.getBreak_Start());
            preparedStatement.setTime(8, department.getBreak_End());
            preparedStatement.setBoolean(9, department.isShift_Sunday());
            preparedStatement.setBoolean(10, department.isShift_Monday());
            preparedStatement.setBoolean(11, department.isShift_Tuesday());
            preparedStatement.setBoolean(12, department.isShift_Wednesday());
            preparedStatement.setBoolean(13, department.isShift_Thursday());
            preparedStatement.setBoolean(14, department.isShift_Friday());
            preparedStatement.setBoolean(15, department.isShift_Saturday());



            preparedStatement.executeUpdate();

            callAlert("Department " + department.getDepartment_Name() + " has been added", 2);
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
                "    department_hoursperday = ?," +
                "    shift_in = ?," +
                "    shift_out = ?," +
                "    shift_breakstart = ?," +
                "    shift_breakend = ?," +
                "    shift_sunday = ?," +
                "    shift_monday = ?," +
                "    shift_tuesday = ?," +
                "    shift_wednesday = ?," +
                "    shift_thursday = ?," +
                "    shift_friday = ?," +
                "    shift_saturday = ?" +
                "WHERE department_id = ?";
        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(command)) {

            preparedStatement.setString(1, department.getDepartment_Name());
            preparedStatement.setDouble(2, department.getDepartment_MonthlyRate()); //
            preparedStatement.setInt(3, department.getDepartment_DaysPerMonth());
            preparedStatement.setInt(4, department.getDepartment_HoursPerDay());
            preparedStatement.setTime(5, department.getTime_In());
            preparedStatement.setTime(6, department.getTime_Out());
            preparedStatement.setTime(7, department.getBreak_Start());
            preparedStatement.setTime(8, department.getBreak_End());
            preparedStatement.setBoolean(9, department.isShift_Sunday());
            preparedStatement.setBoolean(10, department.isShift_Monday());
            preparedStatement.setBoolean(11, department.isShift_Tuesday());
            preparedStatement.setBoolean(12, department.isShift_Wednesday());
            preparedStatement.setBoolean(13, department.isShift_Thursday());
            preparedStatement.setBoolean(14, department.isShift_Friday());
            preparedStatement.setBoolean(15, department.isShift_Saturday());
            preparedStatement.setInt(16, department.getDepartment_ID());

            preparedStatement.executeUpdate();

            callAlert("Department " + department.getDepartment_Name() + " has been updated", 2);
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

    public void deleteDepartment(Department dept) {
        String command = "DELETE FROM tbl_department " +
                "WHERE department_id = ?";
        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(command)) {

            preparedStatement.setInt(1, dept.getDepartment_ID());

            preparedStatement.executeUpdate();

            callAlert("Department " + dept.getDepartment_Name() + " has been deleted", 2);
        } catch (SQLException e) {
            System.out.println("Error connecting to SQLite database");
            e.printStackTrace();
        }
    }


    public ObservableList<Department> getDepartmentsWithDaySchedule(String shiftDay) {
        ObservableList<Department> departments = FXCollections.observableArrayList();
        String command = "SELECT * " +
                "FROM tbl_department td " +
                "WHERE td.shift_" + shiftDay + " = 1";

        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(command)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Department department = new Department();
                department.setDepartment_ID(resultSet.getInt("department_id"));
                department.setDepartment_Name(resultSet.getString("department_name"));
                department.setDepartment_MonthlyRate(resultSet.getDouble("department_monthlyrate"));
                department.setDepartment_DaysPerMonth(resultSet.getInt("department_dayspermonth"));
                department.setDepartment_HoursPerDay(resultSet.getInt("department_hoursperday"));
                department.setTime_In(resultSet.getTime("shift_in"));
                department.setTime_Out(resultSet.getTime("shift_out"));
                department.setBreak_Start(resultSet.getTime("shift_breakstart"));
                department.setBreak_End(resultSet.getTime("shift_breakend"));
                department.setShift_Sunday(resultSet.getBoolean("shift_sunday"));
                department.setShift_Monday(resultSet.getBoolean("shift_monday"));
                department.setShift_Tuesday(resultSet.getBoolean("shift_tuesday"));
                department.setShift_Wednesday(resultSet.getBoolean("shift_wednesday"));
                department.setShift_Thursday(resultSet.getBoolean("shift_thursday"));
                department.setShift_Friday(resultSet.getBoolean("shift_friday"));
                department.setShift_Saturday(resultSet.getBoolean("shift_saturday"));

                departments.add(department);
            }
        } catch (SQLException e) {
            System.out.println("Error connecting to SQLite database");
            e.printStackTrace();
        }
        return departments;
    }
}
