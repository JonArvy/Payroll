package Database;
import Models.Department;
import Models.Logs;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.text.ParseException;

import static Database.SQLConnection.connect;

public class SQLLogs {
    public void createTriggers() {
        String createLogTable = "CREATE TABLE IF NOT EXISTS tbl_logs (" +
                "log_id INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL," +
                "log_category TEXT NOT NULL," +
                "log_message TEXT NOT NULL," +
                "log_admin INTEGER NOT NULL," +
                "log_date DATE NOT NULL," +
                "log_time TIME NOT NULL)";

        String insertEmployee = "CREATE TRIGGER IF NOT EXISTS insert_employee " +
                "AFTER INSERT ON tbl_employees " +
                "BEGIN " +
                "    INSERT INTO tbl_logs (log_category, log_admin, log_message, log_date, log_time) " +
                "    VALUES ('Add Employee', (SELECT emp_id FROM tbl_admin WHERE admin_isUsingTheSystem = true), 'New employee added with name ' || new.emp_fname || ' ' || new.emp_lname, date('now'), time('now')); " +
                "END"; //Done

        String updateEmployee = "CREATE TRIGGER IF NOT EXISTS update_employee " +
                "AFTER UPDATE ON tbl_employees " +
                "BEGIN " +
                "    INSERT INTO tbl_logs (log_category, log_admin, log_message, log_date, log_time) " +
                "    VALUES ('Update Employee', (SELECT emp_id FROM tbl_admin WHERE admin_isUsingTheSystem = true), 'Employee with name ' || old.emp_fname || ' ' || old.emp_lname || ' has been updated', date('now'), time('now')); " +
                "END"; //Done

        String insertAttendance = "CREATE TRIGGER IF NOT EXISTS insert_attendance " +
                "AFTER INSERT ON tbl_attendance " +
                "BEGIN " +
                "    INSERT INTO tbl_logs (log_category, log_admin, log_message, log_date, log_time) " +
                "    VALUES ('Add Attendance', (SELECT emp_id FROM tbl_admin WHERE admin_isUsingTheSystem = true), " +
                "        'New attendance added by ' || (" +
                "            SELECT emp_fname || ' ' || emp_lname " +
                "            FROM tbl_employees " +
                "            WHERE " +
                "                emp_id = new.emp_id " +
                "        ) || ' with date ' || (SELECT DATE(new.emp_attendance_date / 1000, 'unixepoch', '+1 days')), date('now'), time('now')); " +
                "END"; //Done

        String updateAttendance = "CREATE TRIGGER IF NOT EXISTS update_attendance " +
                "AFTER UPDATE ON tbl_attendance " +
                "BEGIN " +
                "    INSERT INTO tbl_logs (log_category, log_admin, log_message, log_date, log_time) " +
                "    VALUES ('Update Attendance', (SELECT emp_id FROM tbl_admin WHERE admin_isUsingTheSystem = true), 'Attendance record of employee number ' || old.emp_id || ' updated with ' || (SELECT DATE(old.emp_attendance_date / 1000, 'unixepoch', '+1 days')), date('now'), time('now')); " +
                "END";

        String deleteAttendance = "CREATE TRIGGER IF NOT EXISTS delete_attendance " +
                "AFTER DELETE ON tbl_attendance " +
                "BEGIN " +
                "    INSERT INTO tbl_logs (log_category, log_admin, log_message, log_date, log_time) " +
                "    VALUES ('Delete Attendance', (SELECT emp_id FROM tbl_admin WHERE admin_isUsingTheSystem = true), 'Attendance record of employee number ' || old.emp_id || ' deleted with ' || (SELECT DATE(old.emp_attendance_date / 1000, 'unixepoch', '+1 days')), date('now'), time('now')); " +
                "END";

        String insertBonus = "CREATE TRIGGER IF NOT EXISTS insert_bonus " +
                "AFTER INSERT ON tbl_bonus " +
                "BEGIN " +
                "    INSERT INTO tbl_logs (log_category, log_admin, log_message, log_date, log_time) " +
                "    VALUES ('Add Bonus', (SELECT emp_id FROM tbl_admin WHERE admin_isUsingTheSystem = true), 'New bonus added with amount ' || new.bonus_amount || ' and date ' || (SELECT DATE(new.bonus_date / 1000, 'unixepoch', '+1 days')), date('now'), time('now')); " +
                "END";

        String updateBonus = "CREATE TRIGGER IF NOT EXISTS update_bonus " +
                "AFTER UPDATE ON tbl_bonus " +
                "BEGIN " +
                "    INSERT INTO tbl_logs (log_category, log_admin, log_message, log_date, log_time) " +
                "    VALUES ('Update Bonus', (SELECT emp_id FROM tbl_admin WHERE admin_isUsingTheSystem = true), 'Bonus record with amount ' || old.bonus_amount || ' updated with ' || new.bonus_amount || ' and date ' || (SELECT DATE(new.bonus_date / 1000, 'unixepoch', '+1 days')), date('now'), time('now')); " +
                "END";

        String insertHoliday = "CREATE TRIGGER IF NOT EXISTS insert_holiday " +
                "AFTER INSERT ON tbl_holiday " +
                "BEGIN " +
                "    INSERT INTO tbl_logs (log_category, log_admin, log_message, log_date, log_time) " +
                "    VALUES ('Add Holiday', (SELECT emp_id FROM tbl_admin WHERE admin_isUsingTheSystem = true), 'New holiday added with name ' || new.holiday_name || ' and date ' || (SELECT DATE(new.holiday_date / 1000, 'unixepoch', '+1 days')), date('now'), time('now')); " +
                "END";

        String updateHoliday = "CREATE TRIGGER IF NOT EXISTS update_holiday " +
                "AFTER UPDATE ON tbl_holiday " +
                "BEGIN " +
                "    INSERT INTO tbl_logs (log_category, log_admin, log_message, log_date, log_time) " +
                "    VALUES ('Update Holiday', (SELECT emp_id FROM tbl_admin WHERE admin_isUsingTheSystem = true), 'Holiday record with name ' || old.holiday_name || ' updated with ' || new.holiday_name || ' and date ' || (SELECT DATE(new.holiday_date / 1000, 'unixepoch', '+1 days')), date('now'), time('now')); " +
                "END";

        String deleteHoliday = "CREATE TRIGGER IF NOT EXISTS delete_holiday " +
                "AFTER DELETE ON tbl_holiday " +
                "BEGIN " +
                "    INSERT INTO tbl_logs (log_category, log_admin, log_message, log_date, log_time) " +
                "    VALUES ('Delete Holiday', (SELECT emp_id FROM tbl_admin WHERE admin_isUsingTheSystem = true), 'Holiday record with name ' || old.holiday_name || ' deleted.', date('now'), time('now')); " +
                "END";

        String insertDepartment = "CREATE TRIGGER IF NOT EXISTS insert_department " +
                "AFTER INSERT ON tbl_department " +
                "BEGIN " +
                "    INSERT INTO tbl_logs (log_category, log_admin, log_message, log_date, log_time) " +
                "    VALUES ('Add Department', (SELECT emp_id FROM tbl_admin WHERE admin_isUsingTheSystem = true), 'New department added with name ' || new.department_name, date('now'), time('now')); " +
                "END";

        String updateDepartment = "CREATE TRIGGER IF NOT EXISTS update_department " +
                "AFTER UPDATE ON tbl_department " +
                "BEGIN " +
                "    INSERT INTO tbl_logs (log_category, log_admin, log_message, log_date, log_time) " +
                "    VALUES ('Update Department', (SELECT emp_id FROM tbl_admin WHERE admin_isUsingTheSystem = true), 'Department record with name ' || old.department_name || ' updated.', date('now'), time('now')); " +
                "END";

        String deleteDepartment = "CREATE TRIGGER IF NOT EXISTS delete_department " +
                "AFTER DELETE ON tbl_department " +
                "BEGIN " +
                "    INSERT INTO tbl_logs (log_category, log_admin, log_message, log_date, log_time) " +
                "    VALUES ('Delete Department', (SELECT emp_id FROM tbl_admin WHERE admin_isUsingTheSystem = true), 'Department record with name ' || old.department_name || ' deleted.', date('now'), time('now')); " +
                "END";

        String insertAdmin = "CREATE TRIGGER IF NOT EXISTS insert_admin " +
                "AFTER INSERT ON tbl_admin " +
                "BEGIN " +
                "    INSERT INTO tbl_logs (log_category, log_admin, log_message, log_date, log_time) " +
                "    VALUES ('Add Admin', (SELECT emp_id FROM tbl_admin WHERE admin_isUsingTheSystem = true), 'New admin added with employee id ' || new.emp_id || ' granted by ' || new.admin_grantor," +
                "            date('now')," +
                "            time('now')); " +
                "END";

        String updateAdmin = "CREATE TRIGGER IF NOT EXISTS update_admin " +
                "AFTER UPDATE ON tbl_admin " +
                "BEGIN " +
                "    INSERT INTO tbl_logs (log_category, log_admin, log_message, log_date, log_time) " +
                "    VALUES ('Update Admin', (SELECT emp_id FROM tbl_admin WHERE admin_isUsingTheSystem = true), 'Updated admin with employee id ' || old.emp_id," +
                "            date('now')," +
                "            time('now')); " +
                "END";


        ExecuteWithoutReturn(createLogTable);

        ExecuteWithoutReturn(insertEmployee);
        ExecuteWithoutReturn(updateEmployee);

        ExecuteWithoutReturn(insertAttendance);
        ExecuteWithoutReturn(updateAttendance);
        ExecuteWithoutReturn(deleteAttendance);

        ExecuteWithoutReturn(insertBonus);
        ExecuteWithoutReturn(updateBonus);

        ExecuteWithoutReturn(insertHoliday);
        ExecuteWithoutReturn(updateHoliday);
        ExecuteWithoutReturn(deleteHoliday);

        ExecuteWithoutReturn(insertDepartment);
        ExecuteWithoutReturn(updateDepartment);
        ExecuteWithoutReturn(deleteDepartment);

        //Do I need this?
//        ExecuteWithoutReturn(insertAdmin);
//        ExecuteWithoutReturn(updateAdmin);

    }

    public void dropTriggers() {
        String createLogTable = "DROP TABLE tbl_logs";

        String insertEmployee = "DROP TRIGGER insert_employee";
        String updateEmployee = "DROP TRIGGER update_employee";

        String insertAttendance = "DROP TRIGGER insert_attendance";
        String updateAttendance = "DROP TRIGGER update_attendance";
        String deleteAttendance = "DROP TRIGGER delete_attendance";

        String insertBonus = "DROP TRIGGER insert_bonus";
        String updateBonus = "DROP TRIGGER update_bonus";

        String insertHoliday = "DROP TRIGGER insert_holiday";
        String updateHoliday = "DROP TRIGGER update_holiday";
        String deleteHoliday = "DROP TRIGGER delete_holiday";

        String insertDepartment = "DROP TRIGGER insert_department";
        String updateDepartment = "DROP TRIGGER update_department";
        String deleteDepartment = "DROP TRIGGER delete_department";

        String insertAdmin = "DROP TRIGGER insert_admin";
        String updateAdmin = "DROP TRIGGER update_admin";

        ExecuteWithoutReturn(createLogTable);

        ExecuteWithoutReturn(insertEmployee);
        ExecuteWithoutReturn(updateEmployee);

        ExecuteWithoutReturn(insertAttendance);
        ExecuteWithoutReturn(updateAttendance);
        ExecuteWithoutReturn(deleteAttendance);

        ExecuteWithoutReturn(insertBonus);
        ExecuteWithoutReturn(updateBonus);

        ExecuteWithoutReturn(insertHoliday);
        ExecuteWithoutReturn(updateHoliday);
        ExecuteWithoutReturn(deleteHoliday);

        ExecuteWithoutReturn(insertDepartment);
        ExecuteWithoutReturn(updateDepartment);
        ExecuteWithoutReturn(deleteDepartment);

        ExecuteWithoutReturn(insertAdmin);
        ExecuteWithoutReturn(updateAdmin);
    }

    private void ExecuteWithoutReturn(String sql) {
        try (Connection connection = connect();
             Statement statement = connection.createStatement()) {

            statement.executeUpdate(sql);


        } catch (SQLException e) {
            System.out.println("Error connecting to SQLite database");
            e.printStackTrace();
        }
    }

    public ObservableList<Logs> getLogs() {
        ObservableList<Logs> logsList = FXCollections.observableArrayList();
        String command = "SELECT log_id, " +
                "tbl_logs.log_message, " +
                "tbl_logs.log_date, " +
                "tbl_logs.log_time, " +
                "tbl_logs.log_category, " +
                "tbl_employees.emp_fname || ' ' || tbl_employees.emp_lname AS admin_name " +
                "FROM tbl_logs JOIN tbl_employees " +
                "ON tbl_logs.log_admin = tbl_employees.emp_id ";

        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(command)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Logs logs = new Logs();
                logs.setLog_id(resultSet.getInt("log_id"));
                logs.setLog_message(resultSet.getString("log_message"));
                logs.setLog_type(resultSet.getString("log_category"));
                logs.setLog_Admin(resultSet.getString("admin_name"));
                logs.setLog_date(resultSet.getString("log_date"));
                logs.setLog_time(resultSet.getString("log_time"));
                logsList.add(logs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return logsList;
    }
}
