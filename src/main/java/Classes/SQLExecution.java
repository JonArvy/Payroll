package Classes;

import Models.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

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
                "emp_department INTEGER CONSTRAINT fk_emp_dept_id_employes REFERENCES tbl_department(department_id) ON DELETE SET NULL ON UPDATE CASCADE, " +
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

                "department_name VARCHAR(50))";


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
                "admin_password VARCHAR(200))";


        ExecuteWithoutReturn(department_tbl);
        ExecuteWithoutReturn(noticeboard_tbl);

        ExecuteWithoutReturn(emp_tbl);
        ExecuteWithoutReturn(admin_tbl);
        ExecuteWithoutReturn(attendance_tbl);
    }


    public void dropTables() {
        String emp_tbl = "DROP TABLE IF EXISTS tbl_employees";
        String noticeboard_tbl = "DROP TABLE IF EXISTS tbl_noticeboard";
        String department_tbl = "DROP TABLE IF EXISTS tbl_department";
        String attendance_tbl = "DROP TABLE IF EXISTS tbl_attendance";
        String admin_tbl = "DROP TABLE IF EXISTS tbl_admin";

        ExecuteWithoutReturn(department_tbl);
        ExecuteWithoutReturn(noticeboard_tbl);

        ExecuteWithoutReturn(emp_tbl);
        ExecuteWithoutReturn(admin_tbl);
        ExecuteWithoutReturn(attendance_tbl);
    }

    private void ExecuteWithoutReturn(String sql) {
        try (Connection connection = connect();
             Statement statement = connection.createStatement();) {

            //ResultSet result = statement.executeQuery(sql);
            statement.executeUpdate(sql);


        } catch (SQLException e) {
            System.out.println("Error connecting to SQLite database");
            e.printStackTrace();
        }
    }

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

    public void getAllEmployees(ObservableList<Employee> employeeList) {
        //ObservableList<Employee> employeeList = FXCollections.observableArrayList();
        String command = "SELECT emp_id," +
                "emp_lname," +
                "emp_fname," +
                "emp_employmentstatus," +
                "emp_department," +
                "emp_status FROM tbl_employees";
        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(command)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                employeeList.add(new Employee(
                        resultSet.getInt("emp_id"),

                        resultSet.getString("emp_lname"),
                        resultSet.getString("emp_fname"),
                        resultSet.getString("emp_employmentstatus"),
                        resultSet.getInt("emp_department"),
                        resultSet.getBoolean("emp_status")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        //return employeeList;

    }

    public Employee getEmployee(int emp_id) {
        Employee curemp = null;
        String command = "SELECT emp_department, emp_fname, emp_lname FROM tbl_employees WHERE emp_id = ?";
        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(command)) {
            preparedStatement.setString(1, String.valueOf(emp_id));

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

            }
            curemp = new Employee(emp_id,
                    resultSet.getInt("emp_department"),
                    resultSet.getString("emp_fname"),
                    resultSet.getString("emp_lname")
                    );
        } catch (SQLException e) {

        }
        return curemp;

    }

    // Admin Database here
    public void getAllAdmin() {
        String command = "SELECT * FROM admins";
        //ExecuteWithReturn(command);
    }

    public void addDepartment() {
        String command = "INSERT INTO tbl_department(department_id, department_name)" +
                "VALUES (?, ?)";

    }

    private void ExecuteWithReturn (String sql) { //Will change to return later
        try (Connection connection = connect();) {

            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);

            while (result.next()) {
                String name = result.getString("name");
                int emp_id = result.getInt("emp_id");
                // Arraylist?????????
                // IDK prob

                // return items
                // IDK
                System.out.println(name + " " + emp_id);
            }

        } catch (SQLException e) {
            System.out.println("Error connecting to SQLite database");
            e.printStackTrace();
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

        //For testing
}
