package Classes;

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
                "department_hoursperday INTEGER)";


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
                "bonus_recipient INTEGER CONSTRAINT fk_department_id_bonus REFERENCES tbl_department(department_id) ON DELETE SET NULL ON UPDATE CASCADE," +
                "bonus_date DATE)";

        String shift_tbl = "CREATE TABLE IF NOT EXISTS tbl_shift (" +
                "shift_id INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE," +
                "shift_type INTEGER," +
                "shift_recipient INTEGER," +
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

            //ResultSet result = statement.executeQuery(sql);
            statement.executeUpdate(sql);


        } catch (SQLException e) {
            System.out.println("Error connecting to SQLite database");
            e.printStackTrace();
        }
    }

    //Select Statements
    //Get all employee information and all info
    public ObservableList<Employee> getAllEmployeesInformation() {
        ObservableList<Employee> employeeList = FXCollections.observableArrayList();
        String command = "SELECT * FROM tbl_employees";
        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(command)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                employeeList.add(new Employee(
                        resultSet.getInt("emp_id"),

                        resultSet.getString("emp_nationality"),
                        resultSet.getString("emp_maritalstatus"),
                        resultSet.getInt("emp_department"),
                        resultSet.getString("emp_position"),
                        resultSet.getString("emp_employmentstatus"),

                        resultSet.getString("emp_fname"),
                        resultSet.getString("emp_lname"),
                        resultSet.getString("emp_mname"),
                        resultSet.getString("emp_ext"),
                        resultSet.getString("emp_address"),
                        resultSet.getBoolean("emp_gender"),
                        resultSet.getString("emp_contactno"),
                        resultSet.getDate("emp_bday"),
                        resultSet.getBoolean("emp_status"),

                        resultSet.getString("emp_contact_fname"),
                        resultSet.getString("emp_contact_relationship"),
                        resultSet.getString("emp_contact_address"),
                        resultSet.getString("emp_contact_contactno"),
                        resultSet.getString("emp_biometrics_info")

                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employeeList;

    }

    //Getting Bonus
    public ObservableList<Bonus> getBonus() {
        ObservableList<Bonus> bonusList = FXCollections.observableArrayList();
        String command = "SELECT bonus_name," +
                "bonus_amount," +
                "department_name," +
                "bonus_date " +
                "FROM tbl_bonus JOIN tbl_department " +
                "ON bonus_recipient = department_id";

        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(command)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                bonusList.add(new Bonus(
                                resultSet.getString("bonus_name"),
                                resultSet.getDouble("bonus_amount"),
                                resultSet.getString("department_name"),
                                resultSet.getDate("bonus_date")
                        )
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bonusList;

    }

    //Getting Shift
    public ObservableList<Shift> getShift() {
        ObservableList<Shift> shiftList = FXCollections.observableArrayList();

        String command = "SELECT shift_id," +
                "shift_type," +

                "shift_recipient," +
                "department_name," +

                "shift_sunday," +
                "shift_monday," +
                "shift_tuesday," +
                "shift_wednesday," +
                "shift_thursday," +
                "shift_friday," +
                "shift_saturday " +

                "FROM tbl_shift JOIN tbl_department " +
                "ON shift_recipient = department_id " +
                "WHERE shift_type = 1";

        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(command)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                shiftList.add(new Shift(
                                resultSet.getInt("shift_id"),
                                resultSet.getInt("shift_type"),
                                resultSet.getInt("shift_recipient"),
                                resultSet.getString("department_name"),
                                resultSet.getBoolean("shift_sunday"),
                                resultSet.getBoolean("shift_monday"),
                                resultSet.getBoolean("shift_tuesday"),
                                resultSet.getBoolean("shift_wednesday"),
                                resultSet.getBoolean("shift_thursday"),
                                resultSet.getBoolean("shift_friday"),
                                resultSet.getBoolean("shift_saturday")
                        )
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        command = "SELECT shift_id," +
                "shift_type," +

                "shift_recipient," +
                "emp_lname," +

                "shift_sunday," +
                "shift_monday," +
                "shift_tuesday," +
                "shift_wednesday," +
                "shift_thursday," +
                "shift_friday," +
                "shift_saturday " +

                "FROM tbl_shift JOIN tbl_employees " +
                "ON shift_recipient = emp_id " +
                "WHERE shift_type = 2";

        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(command)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                shiftList.add(new Shift(
                                resultSet.getInt("shift_id"),
                                resultSet.getInt("shift_type"),
                                resultSet.getInt("shift_recipient"),
                                resultSet.getString("emp_lname"),
                                resultSet.getBoolean("shift_sunday"),
                                resultSet.getBoolean("shift_monday"),
                                resultSet.getBoolean("shift_tuesday"),
                                resultSet.getBoolean("shift_wednesday"),
                                resultSet.getBoolean("shift_thursday"),
                                resultSet.getBoolean("shift_friday"),
                                resultSet.getBoolean("shift_saturday")
                        )
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return shiftList;

    }

    //Getting Department
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

    //Only getting needed data
    public ObservableList<Employee> getAllEmployees() {
        ObservableList<Employee> employeeList = FXCollections.observableArrayList();
        String command = "SELECT emp_id," +
                "tbl_employees.emp_lname," +
                "tbl_employees.emp_fname," +
                "tbl_employees.emp_employmentstatus," +
                "tbl_department.department_name," +
                "tbl_employees.emp_status " +
                "FROM tbl_employees JOIN tbl_department " +
                "ON tbl_employees.emp_department = tbl_department.department_id";
        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(command)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                employeeList.add(new Employee(
                        resultSet.getInt("emp_id"),

                        resultSet.getString("emp_lname"),
                        resultSet.getString("emp_fname"),
                        resultSet.getString("emp_employmentstatus"),
                        resultSet.getString("department_name"),
                        resultSet.getBoolean("emp_status")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employeeList;

    }

    //Getting Employee Attendance
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

    public ObservableList<Attendance> getDailyAttendance(Date dt1, Date dt2) {
        ObservableList<Attendance> attendanceList = FXCollections.observableArrayList();
//        String command = "SELECT * FROM tbl_attendance " +
//                "WHERE emp_attendance_date >= ? " +
//                "AND emp_attendance_date < ?";
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
                "AND emp_attendance_date < ?";
        System.out.println(dt1);
        System.out.println(dt2);
        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(command)) {
            preparedStatement.setDate(1, dt1);
            preparedStatement.setDate(2, dt2);

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
//                attendanceList.add(new Attendance(
//                        resultSet.getInt("emp_id"),
//
//                        resultSet.getDate("emp_attendance_date"),
//                        resultSet.getTime("emp_timein"),
//                        resultSet.getTime("emp_timeout")
//
//                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return attendanceList;
    }

    public ObservableList<Holiday> getHolidays() {
        ObservableList<Holiday> holidayList = FXCollections.observableArrayList();
        String command = "SELECT * FROM tbl_holiday";
        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(command)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                holidayList.add(new Holiday(
                                resultSet.getInt("holiday_id"),
                                resultSet.getString("holiday_name"),
                                resultSet.getDate("holiday_date"),
                                resultSet.getString("holiday_type"),
                                resultSet.getString("holiday_repeat")
                        )
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return holidayList;
    }

    //Getting single employee full details
    public Employee getEmployee(Employee emp) {
        String command = "SELECT * FROM tbl_employees WHERE emp_id = ?";
        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(command)) {
            preparedStatement.setInt(1, emp.getEmployee_ID());

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                emp.setEmployee_ID(resultSet.getInt("emp_id"));
                emp.setNationality(resultSet.getString("emp_nationality"));
                emp.setMarital_Status(resultSet.getString("emp_maritalstatus"));
                emp.setDepartment(resultSet.getInt("emp_department"));
                emp.setPosition(resultSet.getString("emp_position"));
                emp.setEmployment_Status(resultSet.getString("emp_employmentstatus"));
                emp.setFirst_Name(resultSet.getString("emp_fname"));
                emp.setLast_Name(resultSet.getString("emp_lname"));
                emp.setMiddle_Name(resultSet.getString("emp_mname"));
                emp.setExtension(resultSet.getString("emp_ext"));
                emp.setAddress(resultSet.getString("emp_address"));
                emp.setGender(resultSet.getBoolean("emp_gender"));
                emp.setNumber(resultSet.getString("emp_contactno"));
                emp.setBirthdate(resultSet.getDate("emp_bday"));
                emp.setActive(resultSet.getBoolean("emp_status"));

                emp.setContact_Name(resultSet.getString("emp_contact_fname"));
                emp.setContact_Relationship(resultSet.getString("emp_contact_relationship"));
                emp.setContact_Address(resultSet.getString("emp_contact_address"));
                emp.setContact_Number(resultSet.getString("emp_contact_contactno"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return emp;
    }

    //Add
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
        } catch (SQLException e) {
            System.out.println("Error connecting to SQLite database");
            e.printStackTrace();
        }
    }

    //Add Holiday
    public void addHoliday(Holiday holiday) {
        String command = "INSERT INTO tbl_holiday (holiday_name, holiday_date, holiday_type, holiday_repeat) " +
                "VALUES (?, ?, ?, ?)";
        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(command)) {

            preparedStatement.setString(1, holiday.getHoliday_Name());
            preparedStatement.setDate(2, holiday.getHoliday_Date()); //
            preparedStatement.setString(3, holiday.getHoliday_Type());
            preparedStatement.setString(4, holiday.getHoliday_Repeat());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error connecting to SQLite database");
            e.printStackTrace();
        }
    }

    public void addBonus(Bonus bonus) {
        String command = "INSERT INTO tbl_bonus (bonus_name, bonus_amount, bonus_recipient, bonus_date)" +
                "VALUES (?, ?, ?, ?);";
        try (Connection conn = connect();
             PreparedStatement prep = conn.prepareStatement(command)) {
            prep.setString(1, bonus.getBonus_Name());
            prep.setDouble(2, bonus.getBonus_Amount());
            prep.setInt(3, bonus.getBonus_Recipient());
            prep.setDate(4, bonus.getBonus_Date());

            prep.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error connecting to SQLite Database");
        }
    }

    public void addShift(Shift shift) {

        String command = "INSERT INTO tbl_shift (shift_type, shift_recipient, shift_sunday, shift_monday, shift_tuesday, shift_wednesday, shift_thursday, shift_friday, shift_saturday)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = connect();
             PreparedStatement prep = conn.prepareStatement(command)) {
            prep.setInt(1, shift.getShift_Type());
            prep.setInt(2, shift.getShift_Recipient());
            prep.setBoolean(3, shift.isShift_Sunday());
            prep.setBoolean(4, shift.isShift_Monday());
            prep.setBoolean(5, shift.isShift_Tuesday());
            prep.setBoolean(6, shift.isShift_Wednesday());
            prep.setBoolean(7, shift.isShift_Thursday());
            prep.setBoolean(8, shift.isShift_Friday());
            prep.setBoolean(9, shift.isShift_Saturday());

            prep.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error connecting to SQLite Database");
        }
    }

    public void addEmployee(Employee employee) {
        String command = "INSERT INTO `tbl_employees`" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(command)) {

            preparedStatement.setInt(1, employee.getEmployee_ID());
            preparedStatement.setString(2, employee.getNationality()); //
            preparedStatement.setString(3, employee.getMarital_Status());
            preparedStatement.setInt(4, employee.getDepartment()); // Need to connect to dept table
            preparedStatement.setString(5, employee.getPosition());
            preparedStatement.setString(6, employee.getEmployment_Status());
            preparedStatement.setString(7, employee.getFirst_Name());
            preparedStatement.setString(8, employee.getLast_Name());
            preparedStatement.setString(9, employee.getMiddle_Name());
            preparedStatement.setString(10, employee.getExtension());
            preparedStatement.setString(11, employee.getAddress());
            preparedStatement.setBoolean(12, employee.isGender());
            preparedStatement.setString(13, employee.getNumber());
            preparedStatement.setDate(14, new Date(employee.getBirthdate().getTime()));
            preparedStatement.setBoolean(15, employee.isActive());

            preparedStatement.setString(16, employee.getContact_Name());
            preparedStatement.setString(17, employee.getContact_Relationship());
            preparedStatement.setString(18, employee.getContact_Address());
            preparedStatement.setString(19, employee.getContact_Number());
            preparedStatement.setString(20, employee.getEmployee_Biometrics());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error connecting to SQLite database");
            e.printStackTrace();
        }
    }

    public void addAdmin(Admin admin) {
        String command = "INSERT INTO tbl_admin" +
                "VALUES (?, ?, ?, ?)";
        try (Connection conn = connect();
             PreparedStatement preparedStatement = conn.prepareStatement(command)) {

            preparedStatement.setInt(1, admin.getEmployee_ID());
            preparedStatement.setString(2, admin.getAdmin_Password()); //
            preparedStatement.setInt(3, admin.getAdmin_Grantor());
            preparedStatement.setInt(4, admin.getAdmin_Disabler());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error connecting to SQLite database");
            e.printStackTrace();
        }
    }

    //Edits
    public void editDepartment(Department department) {
        String command = "UPDATE tbl_deparment" +
                "SET department_name = ?," +
                "    department_monthlyrate = ?," +
                "    department_dayspermonth = ?, " +
                "    department_hoursperday = ?" +
                "WHERE deparment_id = ?";
        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(command)) {

            preparedStatement.setString(1, department.getDepartment_Name());
            preparedStatement.setDouble(2, department.getDepartment_MonthlyRate()); //
            preparedStatement.setInt(3, department.getDepartment_DaysPerMonth());
            preparedStatement.setInt(4, department.getDepartment_HoursPerDay());
            preparedStatement.setInt(5, department.getDepartment_ID());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error connecting to SQLite database");
            e.printStackTrace();
        }
    }

    public void editBonus(Bonus bonus) {
        String command = "UPDATE tbl_bonus" +
                "SET bonus_name = ?," +
                "    bonus_amount = ?," +
                "    bonus_recipient = ?, " +
                "    bonus_date = ?" +
                "WHERE bonus_id = ?";

        try (Connection conn = connect();
             PreparedStatement prep = conn.prepareStatement(command)) {
            prep.setString(1, bonus.getBonus_Name());
            prep.setDouble(2, bonus.getBonus_Amount());
            prep.setInt(3, bonus.getBonus_Recipient());
            prep.setDate(4, bonus.getBonus_Date());
            prep.setInt(5, bonus.getBonus_ID());

            prep.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error connecting to SQLite Database");
        }
    }

    public void editShift(Shift shift) {
        String command = "UPDATE tbl_bonus" +
                "SET shift_type = ?," +
                "    shift_recipient = ?," +
                "    shift_sunday = ?, " +
                "    shift_monday = ?," +
                "    shift_tuesday = ?," +
                "    shift_wednesday = ?," +
                "    shift_thursday = ?," +
                "    shift_friday = ?," +
                "    shift_saturday = ?" +
                "WHERE shift_id = ?";

        try (Connection conn = connect();
             PreparedStatement prep = conn.prepareStatement(command)) {
            prep.setInt(1, shift.getShift_Type());
            prep.setInt(2, shift.getShift_Recipient());
            prep.setBoolean(3, shift.isShift_Sunday());
            prep.setBoolean(4, shift.isShift_Monday());
            prep.setBoolean(5, shift.isShift_Tuesday());
            prep.setBoolean(6, shift.isShift_Wednesday());
            prep.setBoolean(7, shift.isShift_Thursday());
            prep.setBoolean(8, shift.isShift_Friday());
            prep.setBoolean(9, shift.isShift_Saturday());
            prep.setInt(10, shift.getShift_ID());

            prep.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error connecting to SQLite Database");
        }
    }

    public void updateEmployee(Employee employee) {
        String command = "UPDATE tbl_employees " +
                "SET emp_nationality = ?," +
                "emp_maritalstatus = ?," +
                "emp_department = ?," +
                "emp_position = ?," +
                "emp_employmentstatus = ?," +
                "emp_fname = ?," +
                "emp_lname = ?," +
                "emp_mname = ?," +
                "emp_ext = ?," +
                "emp_address = ?," +
                "emp_gender = ?," +
                "emp_contactno = ?," +
                "emp_bday = ?," +
                "emp_status = ?," +
                "emp_contact_fname = ?," +
                "emp_contact_relationship = ?," +
                "emp_contact_address = ?," +
                "emp_contact_contactno = ? " +
                "WHERE emp_id = ?";
        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(command)) {

            preparedStatement.setString(1, employee.getNationality()); //
            preparedStatement.setString(2, employee.getMarital_Status());
            preparedStatement.setInt(3, employee.getDepartment()); // Need to connect to dept table
            preparedStatement.setString(4, employee.getPosition());
            preparedStatement.setString(5, employee.getEmployment_Status());
            preparedStatement.setString(6, employee.getFirst_Name());
            preparedStatement.setString(7, employee.getLast_Name());
            preparedStatement.setString(8, employee.getMiddle_Name());
            preparedStatement.setString(9, employee.getExtension());
            preparedStatement.setString(10, employee.getAddress());
            preparedStatement.setBoolean(11, employee.isGender());
            preparedStatement.setString(12, employee.getNumber());
            preparedStatement.setDate(13, new Date(employee.getBirthdate().getTime()));
            preparedStatement.setBoolean(14, employee.isActive());

            preparedStatement.setString(15, employee.getContact_Name());
            preparedStatement.setString(16, employee.getContact_Relationship());
            preparedStatement.setString(17, employee.getContact_Address());
            preparedStatement.setString(18, employee.getContact_Number());
            preparedStatement.setInt(19, employee.getEmployee_ID());

            preparedStatement.executeUpdate();
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
