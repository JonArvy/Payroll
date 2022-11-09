package Database;

import Models.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.text.ParseException;
import java.time.LocalDate;

import static Database.SQLConnection.connect;

public class SQLExecution {

    public SQLExecution() {
        //Employees = new Employees();
    }

    public void createTables() {

        // EMPLOYEE TABLE
        String emp_tbl = "CREATE TABLE IF NOT EXISTS tbl_employees (" +
                "emp_id INTEGER PRIMARY KEY UNIQUE, " +

                "emp_nationality VARCHAR(30), " +
                "emp_maritalstatus VARCHAR(30), " +
                "emp_department INTEGER CONSTRAINT fk_emp_dept_id_employees REFERENCES tbl_department(department_id) ON DELETE SET NULL ON UPDATE CASCADE, " +
                "emp_position VARCHAR(10), " +
                "emp_employmentstatus VARCHAR(30), " +

                "emp_fname VARCHAR(30), " +
                "emp_lname VARCHAR(30), " +
                "emp_mname VARCHAR(30), " +
                "emp_ext VARCHAR(10), " +
                "emp_address VARCHAR(250), " +
                "emp_gender BOOLEAN, " +
                "emp_contactno VARCHAR(20), " +
                "emp_bday DATE, " +
                "emp_status BOOLEAN, " +

                "emp_contact_fname VARCHAR(128), " +
                "emp_contact_relationship VARCHAR(30), " +
                "emp_contact_address VARCHAR(250), " +
                "emp_contact_contactno VARCHAR(20), " +

                "emp_biometrics_info VARCHAR(200))";

        // NOTICEBOARD TABLE
        String noticeboard_tbl = "CREATE TABLE IF NOT EXISTS tbl_noticeboard (" +
                "noticeboard_id INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE, " + //Primary key tapos auto increment unique

                "noticeboard_message VARCHAR(100), " +
                "noticeboard_date INTEGER, " +
                "noticeboard_active BOOLEAN)";

        // DEPARTMENT TABLE
        String department_tbl = "CREATE TABLE IF NOT EXISTS tbl_department (" +
                "department_id INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE, " + //Primary key

                "department_name VARCHAR(50)," +
                "department_monthlyrate REAL," +
                "department_dayspermonth INTEGER," +
                "department_hoursperday INTEGER," +
                "shift_in TIME," +
                "shift_out TIME," +
                "shift_breakstart TIME," +
                "shift_breakend TIME," +
                "shift_sunday BOOLEAN," +
                "shift_monday BOOLEAN," +
                "shift_tuesday BOOLEAN," +
                "shift_wednesday BOOLEAN," +
                "shift_thursday BOOLEAN," +
                "shift_friday BOOLEAN," +
                "shift_saturday BOOLEAN)";


        // ATTENDANCE TABLE
        String attendance_tbl = "CREATE TABLE IF NOT EXISTS tbl_attendance (" +
                "attendance_id INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE, " +
                "emp_id INTEGER CONSTRAINT fk_emp_id_attendance REFERENCES tbl_employees(emp_id) ON DELETE SET NULL ON UPDATE CASCADE, " +
                "emp_attendance_date DATE," +
                "emp_timein TIME, " +
                "emp_timeout TIME)";

        String admin_tbl = "CREATE TABLE IF NOT EXISTS tbl_admin (" +
                "admin_id INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE, " +
                "emp_id INTEGER CONSTRAINT fk_emp_id_attendance REFERENCES tbl_employees(emp_id) ON DELETE SET NULL ON UPDATE CASCADE, " +
                "admin_password VARCHAR(200)," +
                "admin_grantor INTEGER," +
                "admin_disabler INTEGER)";

        String bonus_tbl = "CREATE TABLE IF NOT EXISTS tbl_bonus (" +
                "bonus_id INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE," +
                "bonus_name VARCHAR(50)," +
                "bonus_amount REAL," +
                "bonus_recipient VARCHAR(50)," +
                "bonus_date DATE)";

        String shift_tbl = "CREATE TABLE IF NOT EXISTS tbl_shift (" +
                "shift_id INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE," +
                "shift_type INTEGER," +
                "shift_recipient INTEGER," +
                "shift_in TIME," +
                "shift_out TIME," +
                "shift_breakstart TIME," +
                "shift_breakend TIME," +
                "shift_sunday BOOLEAN," +
                "shift_monday BOOLEAN," +
                "shift_tuesday BOOLEAN," +
                "shift_wednesday BOOLEAN," +
                "shift_thursday BOOLEAN," +
                "shift_friday BOOLEAN," +
                "shift_saturday BOOLEAN)";

        String holiday_tbl = "CREATE TABLE IF NOT EXISTS tbl_holiday (" +
                "holiday_id INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE," +
                "holiday_name VARCHAR(30)," +
                "holiday_date DATE," +
                "holiday_type VARCHAR(30)," +
                "holiday_repeat VARCHAR(30))";

        String payrollsummary_tbl = "CREATE TABLE IF NOT EXISTS payroll_summary ( " +
                "    summary_individual_id INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE, " +
                "    summary_id INTEGER NOT NULL, " +

                "    summary_number INTEGER NOT NULL, " +
                "    summary_employee_number INTEGER NOT NULL, " +
                "    summary_name VARCHAR(100) NOT NULL, " +

                "    summary_date_created DATE NOT NULL, " +
                "    summary_department VARCHAR(30) NOT NULL, " +

                "    summary_position VARCHAR(30) NOT NULL, " +

                "    summary_late_ut INTEGER NOT NULL, " +
                "    summary_wage DOUBLE NOT NULL, " +
                "    summary_benefits DOUBLE NOT NULL, " +

                "    summary_present_days INTEGER NOT NULL, " +
                "    summary_absent_days INTEGER NOT NULL, " +

                "    summary_total_compensation DOUBLE NOT NULL, " +
                "    summary_less DOUBLE NOT NULL, " +
                "    summary_total_deduction DOUBLE NOT NULL, " +
                "    summary_net_amount DOUBLE NOT NULL)";

        String payrollsummaryschema_tbl = "CREATE TABLE IF NOT EXISTS payroll_summary_schema ( " +
                "    summary_id INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE, " +
                "    summary_date_from DATE NOT NULL, " +
                "    summary_date_to DATE NOT NULL)";

        ExecuteWithoutReturn(department_tbl);
        ExecuteWithoutReturn(noticeboard_tbl);
        ExecuteWithoutReturn(holiday_tbl);

        ExecuteWithoutReturn(emp_tbl);
        ExecuteWithoutReturn(admin_tbl);
        ExecuteWithoutReturn(attendance_tbl);

        ExecuteWithoutReturn(bonus_tbl);
//        ExecuteWithoutReturn(shift_tbl);

        ExecuteWithoutReturn(payrollsummary_tbl);
        ExecuteWithoutReturn(payrollsummaryschema_tbl);
    }

    public void dropTables() {
        String emp_tbl = "DROP TABLE IF EXISTS tbl_employees";
        String noticeboard_tbl = "DROP TABLE IF EXISTS tbl_noticeboard";
        String department_tbl = "DROP TABLE IF EXISTS tbl_department";
        String attendance_tbl = "DROP TABLE IF EXISTS tbl_attendance";
        String admin_tbl = "DROP TABLE IF EXISTS tbl_admin";

        String holiday_tbl = "DROP TABLE IF EXISTS tbl_holiday";

        String bonus_tbl = "DROP TABLE IF EXISTS tbl_bonus";
        String shift_tbl = "DROP TABLE IF EXISTS tbl_shift";

        String payrollsummary_tbl = "DROP TABLE IF EXISTS payroll_summary";
        String payrollsummaryschema_tbl = "DROP TABLE IF EXISTS payroll_summary_schema";


        ExecuteWithoutReturn(department_tbl);
        ExecuteWithoutReturn(noticeboard_tbl);
        ExecuteWithoutReturn(holiday_tbl);

        ExecuteWithoutReturn(emp_tbl);
        ExecuteWithoutReturn(admin_tbl);
        ExecuteWithoutReturn(attendance_tbl);

        ExecuteWithoutReturn(bonus_tbl);
        ExecuteWithoutReturn(shift_tbl);

        ExecuteWithoutReturn(payrollsummary_tbl);
        ExecuteWithoutReturn(payrollsummaryschema_tbl);
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
}

//SELECT
//        te.emp_fname || ' ' || te.emp_mname || ' ' || te.emp_lname as full_name,
//        te.emp_id,
//        td.department_name,
//        te.emp_position,
//        (
//        SELECT
//        COUNT(*)
//        FROM tbl_attendance ta
//        WHERE ta.emp_id = te.emp_id
//        ) as present_days,
//        (
//        SELECT
//        tq.department_dayspermonth -
//        (
//        SELECT
//        COUNT(*)
//        FROM tbl_attendance ta
//        WHERE ta.emp_id = te.emp_id
//        )
//        FROM tbl_department tq
//        WHERE tq.department_id = td.department_id
//        ) as absent_days,
//        (
//        SELECT
//        tq.department_dayspermonth -
//        (
//        SELECT
//        COUNT(*)
//        FROM tbl_attendance ta
//        WHERE ta.emp_id = te.emp_id
//        )
//        FROM tbl_department tq
//        WHERE tq.department_id = td.department_id
//        ) as late_hours
//
//
//        FROM tbl_employees te
//        JOIN tbl_department td
//        ON te.emp_department = td.department_id
