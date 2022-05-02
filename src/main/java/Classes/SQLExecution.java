package Classes;

import Models.Employee;
import javafx.collections.ObservableList;

import java.sql.*;

import static Database.SQLConnection.connect;

public class SQLExecution {
    private String Employee[];

    public SQLExecution() {
        //Employees = new Employees();
    }

    public String[] get() {
        return Employee;
    }

    public void createTables() {
        //String createifnotexists = "CREATE TABLE IF NOT EXISTS user_info";
        //String droptable = "DROP TABLE IF EXISTS users";

        // EMPLOYEE TABLE
        String emp_tbl = "CREATE TABLE IF NOT EXISTS tbl_employees (" +
                "emp_id int, " + //Di sya nakaset pag create, need ni admin ilagay to// also primary key sa ibang tables

                "emp_nationality varchar(30), " +
                "emp_maritalstatus varchar(30), " +
                "emp_department int, " + //Eto foreign key galing sa tbl_department value neto
                "emp_position int, " +
                "emp_employmentstatus varchar(3), " +

                "emp_fname varchar(30), " +
                "emp_lname varchar(30), " +
                "emp_mname varchar(30), " +
                "emp_ext varchar(10), " +
                "emp_address varchar(250), " +
                "emp_gender boolean, " +
                "emp_contactno varchar(20), " +
                "emp_bday date, " +
                "emp_email varchar(128), " +

                "emp_contact_fname varchar(128), " +
                "emp_contact_relationship varchar(30), " +
                "emp_contact_address varchar(250), " +
                "emp_contact_contactno varchar(20), " +

                //"emp_biometrics_info varchar(200), " + //Wag muna to

                "PRIMARY KEY(emp_id))";

        // NOTICEBOARD TABLE
        String noticeboard_tbl = "CREATE TABLE IF NOT EXISTS tbl_noticeboard (" +
                "noticeboard_id int, " + //Primary key tapos auto increment unique

                "noticeboard_message varchar(100), " +
                "noticeboard_date int, " +
                "noticeboard_active boolean, " +
                "PRIMARY KEY(noticeboard_id))";

        // BENEFITS TABLE //di na ata to
//        String benefits_tbl = "CREATE TABLE IF NOT EXISTS tbl_benefits (" +
//                "benefits_emp_id int, " +
//
//                "benefits_type int, " +
//                "benefits_acct_number varchar(50), " +
//                "benefits_amount double, " +
//                "benefits_active boolean, " +
//                "PRIMARY KEY(benefits_emp_id))";

        // DEPARTMENT TABLE
        String department_tbl = "CREATE TABLE IF NOT EXISTS tbl_department (" +
                "department_id int, " + //Primary key

                "department_name varchar(50), " +
                "PRIMARY KEY(department_id))";


        // ATTENDANCE TABLE
        String attendance_tbl = "CREATE TABLE IF NOT EXISTS tbl_attendance (" +
                "attendance_id int, " + //Primary key auto increment unique
                "emp_id int, " + //Foreign key //galing sa tbl_employee yung emp_id
                "emp_timein datetime, " +
                "emp_timeout datetime, " +
                //"emp_total "  teeeeeeeeeeeeeemppppppppppp

                "PRIMARY KEY(attendance_id))";

        String admin_tbl = "CREATE TABLE IF NOT EXISTS tbl_admin (" +
                "admin_id int, " + //Primary key auto increment unique
                "emp_id int, " + //Foreign key //galing sa tbl_employee yung emp_id //For ewan to
                "username varchar(30), " +
                "admin_id varchar(200), " +

                "PRIMARY KEY(admin_id))";

        String emp_noticeboard = "";

//        ExecuteWithoutReturn(emp_tbl);
//        ExecuteWithoutReturn(noticeboard_tbl);
//        ExecuteWithoutReturn(benefits_tbl);
//        ExecuteWithoutReturn(department_tbl);
//        ExecuteWithoutReturn(attendance_tbl);
    }


    public void dropTables() {

    }

    public void getAllEmployees(ObservableList<Models.Employee> employeeList) {

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
                        resultSet.getInt("emp_position"),
                        resultSet.getString("emp_employmentstatus"),

                        resultSet.getString("emp_fname"),
                        resultSet.getString("emp_lname"),
                        resultSet.getString("emp_mname"),
                        resultSet.getString("emp_ext"),
                        resultSet.getString("emp_address"),
                        resultSet.getBoolean("emp_gender"),
                        resultSet.getString("emp_contactno"),
                        resultSet.getDate("emp_bday"),
                        resultSet.getString("emp_email"),

                        resultSet.getString("emp_contact_fname"),
                        resultSet.getString("emp_contact_relationship"),
                        resultSet.getString("emp_contact_address"),
                        resultSet.getString("emp_contact_contactno")

                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

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

//    public void addEmployee(Employee employee) {
//        //Get data here
//        String command = "INSERT INTO `tbl_employees`(emp_id int, emp_nationality varchar(30), emp_maritalstatus varchar(30), emp_department int, emp_position int, emp_employmentstatus varchar(3), emp_fname varchar(30), emp_lname varchar(30), emp_mname varchar(30), emp_ext varchar(10), emp_address varchar(250), emp_gender boolean, emp_contactno varchar(20), emp_bday date, emp_email varchar(128), emp_contact_fname varchar(128), emp_contact_relationship varchar(30), emp_contact_address varchar(250), emp_contact_contactno varchar(20))" +
//                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
//
//        ExecuteInsert(command, employee);
//    }

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

    public void addEmployee(Employee employee) {
        String command = "INSERT INTO `tbl_employees`" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(command)) {

            preparedStatement.setInt(1, employee.getEmployee_ID());
            preparedStatement.setString(2, employee.getNationality()); //
            preparedStatement.setString(3, employee.getMarital_Status());
            preparedStatement.setInt(4, employee.getDepartment()); // Need to connect to dept table
            preparedStatement.setInt(5, employee.getPosition());
            preparedStatement.setString(6, employee.getEmployment_Status());
            preparedStatement.setString(7, employee.getFirst_Name());
            preparedStatement.setString(8, employee.getLast_Name());
            preparedStatement.setString(9, employee.getMiddle_Name());
            preparedStatement.setString(10, employee.getExtension());
            preparedStatement.setString(11, employee.getAddress());
            preparedStatement.setBoolean(12, employee.isGender());
            preparedStatement.setString(13, employee.getNumber());
            preparedStatement.setDate(14, new Date(employee.getBirthdate().getTime()));
            preparedStatement.setString(15, employee.getEmail());

            preparedStatement.setString(16, employee.getContact_Name());
            preparedStatement.setString(17, employee.getContact_Relationship());
            preparedStatement.setString(18, employee.getContact_Address());
            preparedStatement.setString(19, employee.getContact_Number());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error connecting to SQLite database");
            e.printStackTrace();
        }
    }

        //For testing
}
