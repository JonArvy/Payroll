package Database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static Database.SQLConnection.connect;

public class SQLExecutionTesting {

    public SQLExecutionTesting() {
        //Employees = new Employees();
    }

    public void createTables() {

        // EMPLOYEE TABLE
        String emp_tbl = "CREATE TABLE IF NOT EXISTS tbl_employees (" +
                "emp_id INTEGER PRIMARY KEY NOT NULL UNIQUE, " +

                "emp_nationality VARCHAR(30), " +
                "emp_maritalstatus VARCHAR(30) NOT NULL, " +
                "emp_department INTEGER NOT NULL CONSTRAINT fk_emp_dept_id_employees REFERENCES tbl_department(department_id) ON DELETE SET NULL ON UPDATE CASCADE, " +
                "emp_position VARCHAR(10) NOT NULL, " +
                "emp_employmentstatus VARCHAR(30) NOT NULL, " +

                "emp_fname VARCHAR(30) NOT NULL, " +
                "emp_lname VARCHAR(30) NOT NULL, " +
                "emp_mname VARCHAR(30), " +
                "emp_ext VARCHAR(10), " +
                "emp_address VARCHAR(250), " +
                "emp_gender BOOLEAN NOT NULL, " +
                "emp_contactno VARCHAR(20) NOT NULL, " +
                "emp_bday DATE, " +
                "emp_status BOOLEAN NOT NULL, " +

                "emp_contact_fname VARCHAR(128) NOT NULL, " +
                "emp_contact_relationship VARCHAR(30) NOT NULL, " +
                "emp_contact_address VARCHAR(250), " +
                "emp_contact_contactno VARCHAR(20) NOT NULL, " +

                "emp_biometrics_info VARCHAR(200))";

        // NOTICEBOARD TABLE
        String noticeboard_tbl = "CREATE TABLE IF NOT EXISTS tbl_noticeboard (" +
                "noticeboard_id INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE, " + //Primary key tapos auto increment unique

                "noticeboard_message VARCHAR(500), " +
                "noticeboard_date DATE NOT NULL, " +
                "noticeboard_active BOOLEAN NOT NULL)";

        // DEPARTMENT TABLE
        String department_tbl = "CREATE TABLE IF NOT EXISTS tbl_department (" +
                "department_id INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE, " + //Primary key

                "department_name VARCHAR(50) NOT NULL," +
                "department_monthlyrate REAL NOT NULL," +
                "department_dayspermonth INTEGER NOT NULL," +
                "department_hoursperday INTEGER NOT NULL)";


        // ATTENDANCE TABLE
        String attendance_tbl = "CREATE TABLE IF NOT EXISTS tbl_attendance (" +
                "attendance_id INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE, " +

                "emp_id INTEGER NOT NULL CONSTRAINT fk_emp_id_attendance REFERENCES tbl_employees(emp_id) ON DELETE SET NULL ON UPDATE CASCADE, " +
                "emp_attendance_date DATE NOT NULL," +
                "emp_timein TIME, " +
                "emp_timeout TIME)";

        String admin_tbl = "CREATE TABLE IF NOT EXISTS tbl_admin (" +
                "admin_id INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE, " +
                "emp_id INTEGER NOT NULL CONSTRAINT fk_emp_id_attendance REFERENCES tbl_employees(emp_id) ON DELETE SET NULL ON UPDATE CASCADE, " +
                "admin_password VARCHAR(200) NOT NULL," +
                "admin_grantor INTEGER NOT NULL," +
                "admin_disabler INTEGER)";

        String bonus_tbl = "CREATE TABLE IF NOT EXISTS tbl_bonus (" +
                "bonus_id INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE," +
                "bonus_name VARCHAR(50) NOT NULL," +
                "bonus_amount REAL NOT NULL," +
                "bonus_recipient INTEGER NOT NULL CONSTRAINT fk_department_id_bonus REFERENCES tbl_department(department_id) ON DELETE SET NULL ON UPDATE CASCADE," +
                "bonus_date DATE NOT NULL)";

        String shift_tbl = "CREATE TABLE IF NOT EXISTS tbl_shift (" +
                "shift_id INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE," +
                "shift_type INTEGER NOT NULL," +
                "shift_recipient INTEGER NOT NULL," +
                "shift_breakstart TIME NOT NULL," +
                "shift_breakend TIME NOT NULL," +
                "shift_sunday BOOLEAN NOT NULL," +
                "shift_monday BOOLEAN NOT NULL," +
                "shift_tuesday BOOLEAN NOT NULL," +
                "shift_wednesday BOOLEAN NOT NULL," +
                "shift_thursday BOOLEAN NOT NULL," +
                "shift_friday BOOLEAN NOT NULL," +
                "shift_saturday BOOLEAN NOT NULL)";

        String holiday_tbl = "CREATE TABLE IF NOT EXISTS tbl_holiday (" +
                "holiday_id INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE," +
                "holiday_name VARCHAR(30) NOT NULL," +
                "holiday_date DATE NOT NULL," +
                "holiday_type VARCHAR(30) NOT NULL)";

        ExecuteWithoutReturn(department_tbl);
        ExecuteWithoutReturn(noticeboard_tbl);
        ExecuteWithoutReturn(holiday_tbl);

        ExecuteWithoutReturn(emp_tbl);
        ExecuteWithoutReturn(admin_tbl);
        ExecuteWithoutReturn(attendance_tbl);

        ExecuteWithoutReturn(bonus_tbl);
        ExecuteWithoutReturn(shift_tbl);
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


        ExecuteWithoutReturn(department_tbl);
        ExecuteWithoutReturn(noticeboard_tbl);
        ExecuteWithoutReturn(holiday_tbl);

        ExecuteWithoutReturn(emp_tbl);
        ExecuteWithoutReturn(admin_tbl);
        ExecuteWithoutReturn(attendance_tbl);

        ExecuteWithoutReturn(bonus_tbl);
        ExecuteWithoutReturn(shift_tbl);
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
