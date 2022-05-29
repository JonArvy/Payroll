package Database;

import Models.Attendance;
import Models.AttendanceReport;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

import static Database.SQLConnection.connect;

public class SQLAttendance {
    public ObservableList<Attendance> getAttendance(int id) {
        ObservableList<Attendance> attendanceList = FXCollections.observableArrayList();
        String command = "SELECT * FROM tbl_attendance WHERE emp_id = ?";
        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(command)) {
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                attendanceList.add(new Attendance(
                        resultSet.getInt("emp_id"),

                        resultSet.getDate("emp_attendance_date"),
                        resultSet.getTime("emp_timein"),
                        resultSet.getTime("emp_timeout")

                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return attendanceList;
    }

    public ObservableList<Attendance> getAttendance(int id, Date from, Date to) {
        ObservableList<Attendance> attendanceList = FXCollections.observableArrayList();
        String command = "SELECT * FROM tbl_attendance " +
                "WHERE emp_id = ? " +
                "AND emp_attendance_date BETWEEN ? AND ?";
        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(command)) {
            preparedStatement.setInt(1, id);
            preparedStatement.setDate(2, from);
            preparedStatement.setDate(3, to);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                attendanceList.add(new Attendance(
                        resultSet.getInt("emp_id"),

                        resultSet.getDate("emp_attendance_date"),
                        resultSet.getTime("emp_timein"),
                        resultSet.getTime("emp_timeout")

                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return attendanceList;
    }

    public ObservableList<Attendance> getDailyAttendance(Date dt1, Date dt2) {
        ObservableList<Attendance> attendanceList = FXCollections.observableArrayList();
        String command = "SELECT attendance_id, " +
                "tbl_attendance.emp_id, " +

                "tbl_employees.emp_lname, " +
                "tbl_employees.emp_fname, " +

                "tbl_department.department_name, " +

                "tbl_employees.emp_position, " +

                "tbl_attendance.emp_attendance_date, " +
                "tbl_attendance.emp_timein, " +
                "tbl_attendance.emp_timeout " +

                "FROM tbl_attendance " +
                "JOIN tbl_employees " +
                "ON tbl_attendance.emp_id = tbl_employees.emp_id " +
                "JOIN tbl_department " +
                "ON tbl_employees.emp_department = tbl_department.department_id " +
                "WHERE emp_attendance_date >= ? " +
                "AND emp_attendance_date < ? " +
                "AND tbl_employees.emp_status = ?";
        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(command)) {
            preparedStatement.setDate(1, dt1);
            preparedStatement.setDate(2, dt2);
            preparedStatement.setBoolean(3, true);

            ResultSet resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) {
                attendanceList.add(new Attendance(resultSet.getInt("attendance_id"),
                        resultSet.getInt("emp_id"),
                        resultSet.getString("emp_lname") + " " + resultSet.getString("emp_fname"),
                        resultSet.getString("department_name"),
                        resultSet.getString("emp_position"),
                        resultSet.getDate("emp_attendance_date"),
                        resultSet.getTime("emp_timein"),
                        resultSet.getTime("emp_timeout")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return attendanceList;
    }

    public void registerAttendance(Attendance attendance) {
        String command = "INSERT INTO tbl_attendance (emp_id, emp_attendance_date, emp_timein, emp_timeout) " +
                "VALUES (?, ?, ?, ?)";
        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(command)) {

            preparedStatement.setInt(1, attendance.getEmployee_ID());
            preparedStatement.setDate(2, attendance.getEmployee_Attendance_Date()); //
            preparedStatement.setTime(3, attendance.getEmployee_TimeIn());
            preparedStatement.setTime(4, attendance.getEmployee_TimeOut()); // Need to connect to dept table

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error connecting to SQLite database");
            e.printStackTrace();
        }
    }

    public ObservableList<AttendanceReport> getAttendanceReport() {
        ObservableList<AttendanceReport> attendanceReportList = FXCollections.observableArrayList();
        int id = 1;
        String command = "SELECT " +
                "te.emp_fname || ' ' || te.emp_mname || ' ' || te.emp_lname as full_name," +
                "te.emp_id as employee_id," +
                "td.department_name as department," +
                "te.emp_position as employee_position," +
                "(" +
                "   SELECT " +
                "   COUNT(*) " +
                "   FROM tbl_attendance ta " +
                "   WHERE ta.emp_id = te.emp_id " +
                ") as present_days," +
                "(" +
                "   SELECT " +
                "   tq.department_dayspermonth - " +
                "   (" +
                "       SELECT " +
                "       COUNT(*) " +
                "       FROM tbl_attendance ta " +
                "       WHERE ta.emp_id = te.emp_id " +
                "   )" +
                "   FROM tbl_department tq " +
                "   WHERE tq.department_id = td.department_id " +
                ") as absent_days, " +
                "( " +
                "   SELECT " +
                "   tq.department_dayspermonth - " +
                "   ( " +
                "       SELECT " +
                "       COUNT(*) " +
                "       FROM tbl_attendance ta " +
                "       WHERE ta.emp_id = te.emp_id " +
                "   ) " +
                "   FROM tbl_department tq " +
                "   WHERE tq.department_id = td.department_id " +
                ") as late_hours," +
                "( " +
                "   SELECT " +
                "   tm.department_dayspermonth - " +
                "   ( " +
                "       SELECT " +
                "       COUNT(*) " +
                "       FROM tbl_attendance ta " +
                "       WHERE ta.emp_id = te.emp_id " +
                "   ) " +
                "   FROM tbl_department tm " +
                "   WHERE tm.department_id = td.department_id " +
                ") as holiday " +
                "FROM tbl_employees te " +
                "JOIN tbl_department td " +
                "ON te.emp_department = td.department_id";
//        String command2 = "SELECT COUNT(*) " +
//                "FROM tbl_attendance " +
//                "JOIN tbl_employees " +
//                "ON tbl_attendance.emp_id = tbl_employees.emp_id " +
//                "WHERE emp_id = ? " +
//                "AND emp_attendance_date >= ? " +
//                "AND emp_attendance_date < ?";
//
        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(command)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                attendanceReportList.add(new AttendanceReport(
                                id,
                                resultSet.getString("full_name"),
                                resultSet.getInt("employee_id"),
                                resultSet.getString("department"),
                                resultSet.getString("employee_position"),
                                resultSet.getInt("present_days"),
                                resultSet.getInt("absent_days"),
                                resultSet.getInt("late_hours"),
                                resultSet.getInt("holiday")
                        )
                );
                id++;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return attendanceReportList;

    }

    public ObservableList<AttendanceReport> getAttendanceReport(Date from, Date to) {
        ObservableList<AttendanceReport> attendanceReportList = FXCollections.observableArrayList();
        int id = 1;
        String command = "SELECT " +
                "te.emp_fname || ' ' || te.emp_mname || ' ' || te.emp_lname as full_name," +
                "te.emp_id as employee_id," +
                "td.department_name as department," +
                "te.emp_position as employee_position," +
                "(" +
                "   SELECT " +
                "   COUNT(*) " +
                "   FROM tbl_attendance ta " +
                "   WHERE ta.emp_id = te.emp_id " +
                "   AND emp_attendance_date BETWEEN ? AND ? " +
                ") as present_days," +
                "(" +
                "   SELECT " +
                "   tq.department_dayspermonth - " +
                "   (" +
                "       SELECT " +
                "       COUNT(*) " +
                "       FROM tbl_attendance ta " +
                "       WHERE ta.emp_id = te.emp_id " +
                "       AND emp_attendance_date BETWEEN ? AND ? " +
                "   )" +
                "   FROM tbl_department tq " +
                "   WHERE tq.department_id = td.department_id " +
                ") as absent_days, " +
                "( " +
                "   SELECT " +
                "   tq.department_dayspermonth - " +
                "   ( " +
                "       SELECT " +
                "       COUNT(*) " +
                "       FROM tbl_attendance ta " +
                "       WHERE ta.emp_id = te.emp_id " +
                "   ) " +
                "   FROM tbl_department tq " +
                "   WHERE tq.department_id = td.department_id " +
                ") as late_hours," +
                "( " +
                "   SELECT " +
                "   tm.department_dayspermonth - " +
                "   ( " +
                "       SELECT " +
                "       COUNT(*) " +
                "       FROM tbl_attendance ta " +
                "       WHERE ta.emp_id = te.emp_id " +
                "   ) " +
                "   FROM tbl_department tm " +
                "   WHERE tm.department_id = td.department_id " +
                ") as holiday " +
                "FROM tbl_employees te " +
                "JOIN tbl_department td " +
                "ON te.emp_department = td.department_id " +
                "WHERE te.emp_status = true";
        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(command)) {
            preparedStatement.setDate(1, from);
            preparedStatement.setDate(2, to);
            preparedStatement.setDate(3, from);
            preparedStatement.setDate(4, to);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                attendanceReportList.add(new AttendanceReport(
                                id,
                                resultSet.getString("full_name"),
                                resultSet.getInt("employee_id"),
                                resultSet.getString("department"),
                                resultSet.getString("employee_position"),
                                resultSet.getInt("present_days"),
                                resultSet.getInt("absent_days"),
                                resultSet.getInt("late_hours"),
                                resultSet.getInt("holiday")
                        )
                );
                id++;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return attendanceReportList;

    }

    public void deleteAttendance(Attendance attendance) {
        String command = "DELETE FROM tbl_attendance " +
                "WHERE emp_attendance_date = ?";
        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(command)) {

            preparedStatement.setDate(1, attendance.getEmployee_Attendance_Date());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error connecting to SQLite database");
            e.printStackTrace();
        }
    }
}
