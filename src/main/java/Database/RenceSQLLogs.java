package Database;

import java.sql.SQLException;

public class RenceSQLLogs {
    public void createTriggers() throws SQLException {
        String createLogTable = "CREATE TABLE IF NOT EXISTS tbl_logs (" +
                "log_id INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL," +
                "log_message TEXT NOT NULL," +
                "log_date DATE NOT NULL," +
                "log_time TIME NOT NULL);";

        String insertEmployee = "CREATE TRIGGER IF NOT EXISTS insert_employee" +
                "AFTER INSERT ON tbl_employees" +
                "BEGIN" +
                "    INSERT INTO tbl_logs (log_message, log_date, log_time)" +
                "    VALUES ('New employee added with name ' || new.emp_fname || ' ' || new.emp_lname, date('now'), time('now'));" +
                "END;";

        String updateEmployee = "CREATE TRIGGER IF NOT EXISTS update_employee" +
                "AFTER UPDATE ON tbl_employees" +
                "BEGIN" +
                "    INSERT INTO tbl_logs (log_message, log_date, log_time)" +
                "    VALUES ('Employee with name ' || old.emp_fname || ' ' || old.emp_lname || ' has been updated', date('now'), time('now'));" +
                "END;";

        String insertAttendance = "CREATE TRIGGER IF NOT EXISTS insert_attendance" +
                "AFTER INSERT ON tbl_attendance" +
                "BEGIN" +
                "    INSERT INTO tbl_logs (log_message, log_date, log_time)" +
                "    VALUES (" +
                "        'New attendance added by ' || (" +
                "            SELECT emp_fname || ' ' || emp_lname" +
                "            FROM tbl_employees" +
                "            WHERE" +
                "                emp_id = new.emp_id" +
                "        ) || ' with date ' || new.emp_attendance_date || ' and time ' || new.emp_timein || ' ' || new.emp_timeout, date('now'), time('now'));" +
                "END;";

        String updateAttendance = "CREATE TRIGGER IF NOT EXISTS update_attendance" +
                "AFTER UPDATE ON tbl_attendance\n" +
                "BEGIN" +
                "    INSERT INTO tbl_logs (log_message, log_date, log_time)" +
                "    VALUES ('Attendance record of employee number ' || old.emp_id || ' updated with ' || new.emp_attendance_date || ' ' || new.emp_timein || ' ' || new.emp_timeout, date('now'), time('now'));" +
                "END;";

        String insertBonus = "CREATE TRIGGER IF NOT EXISTS insert_bonus" +
                "AFTER INSERT ON tbl_bonus" +
                "BEGIN" +
                "    INSERT INTO tbl_logs (log_message, log_date, log_time)" +
                "    VALUES ('New bonus added with amount ' || new.bonus_amount || ' and date ' || new.bonus_date, date('now'), time('now'));" +
                "END;";

        String updateBonus = "CREATE TRIGGER IF NOT EXISTS update_bonus" +
                "AFTER UPDATE ON tbl_bonus" +
                "BEGIN" +
                "    INSERT INTO tbl_logs (log_message, log_date, log_time)" +
                "    VALUES ('Bonus record with amount ' || old.bonus_amount || ' updated with ' || new.bonus_amount || ' and date ' || new.bonus_date, date('now'), time('now'));" +
                "END;";

        String insertHoliday = "CREATE TRIGGER IF NOT EXISTS insert_holiday" +
                "AFTER INSERT ON tbl_holiday" +
                "BEGIN" +
                "    INSERT INTO tbl_logs (log_message, log_date, log_time)" +
                "    VALUES ('New holiday added with name ' || new.holiday_name || ' and date ' || new.holiday_date, date('now'), time('now'));" +
                "END;";

        String updateHoliday = "CREATE TRIGGER IF NOT EXISTS update_holiday" +
                "AFTER UPDATE ON tbl_holiday" +
                "BEGIN" +
                "    INSERT INTO tbl_logs (log_message, log_date, log_time)" +
                "    VALUES ('Holiday record with name ' || old.holiday_name || ' updated with ' || new.holiday_name || ' and date ' || new.holiday_date, date('now'), time('now'));" +
                "END;";

        String insertDepartment = "CREATE TRIGGER IF NOT EXISTS insert_department" +
                "AFTER INSERT ON tbl_department" +
                "BEGIN" +
                "    INSERT INTO tbl_logs (log_message, log_date, log_time)" +
                "    VALUES ('New department added with name ' || new.dept_name, date('now'), time('now'));" +
                "END;";

        String updateDepartment = "CREATE TRIGGER IF NOT EXISTS update_department" +
                "AFTER UPDATE ON tbl_department" +
                "BEGIN" +
                "    INSERT INTO tbl_logs (log_message, log_date, log_time)" +
                "    VALUES ('Department record with name ' || old.dept_name || ' updated.', date('now'), time('now'));" +
                "END;";

        String insertAdmin = "CREATE TRIGGER IF NOT EXISTS insert_admin" +
                "AFTER INSERT ON tbl_admin" +
                "BEGIN\n" +
                "    INSERT INTO tbl_logs (log_message, log_date, log_time)" +
                "    VALUES ('New admin added with employee id ' || new.emp_id || ' granted by ' || new.admin_grantor," +
                "            date('now')," +
                "            time('now'));" +
                "END;";

        String updateAdmin = "CREATE TRIGGER IF NOT EXISTS update_admin" +
                "AFTER UPDATE ON tbl_admin" +
                "BEGIN" +
                "    INSERT INTO tbl_logs (log_message, log_date, log_time)" +
                "    VALUES ('Updated admin with employee id ' || old.emp_id," +
                "            date('now')," +
                "            time('now'));" +
                "END;";
    }
}
