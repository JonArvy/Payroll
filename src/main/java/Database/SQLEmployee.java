package Database;

import Models.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

import static Classes.CustomAlert.callAlert;
import static Classes.QRCodeGenerator.createQR;
import static Classes.RandomStringGenerator.generateRandomString;
import static Database.SQLConnection.connect;

public class SQLEmployee {

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

    public ObservableList<Employee> getAllEmployees(boolean active) {
        ObservableList<Employee> employeeList = FXCollections.observableArrayList();
        if (active == true) {
            String command = "SELECT emp_id," +
                    "tbl_employees.emp_lname," +
                    "tbl_employees.emp_fname," +
                    "tbl_employees.emp_employmentstatus," +
                    "tbl_employees.emp_position," +
                    "tbl_department.department_id," +
                    "tbl_department.department_name," +
                    "tbl_employees.emp_status " +
                    "FROM tbl_employees LEFT JOIN tbl_department " +
                    "ON tbl_employees.emp_department = tbl_department.department_id " +
                    "WHERE tbl_employees.emp_status = ?";
            try (Connection connection = connect();
                 PreparedStatement preparedStatement = connection.prepareStatement(command)) {

                preparedStatement.setBoolean(1, active);

                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    Employee employee = new Employee(
                            resultSet.getInt("emp_id"),
                            resultSet.getString("emp_lname"),
                            resultSet.getString("emp_fname"),
                            resultSet.getString("emp_employmentstatus"),
                            resultSet.getString("department_name"),
                            resultSet.getBoolean("emp_status")
                    );
                    employee.setDepartment(resultSet.getInt("department_id"));
                    employee.setPosition(resultSet.getString("emp_position"));
                    employeeList.add(employee);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            String command = "SELECT emp_id," +
                    "tbl_employees.emp_lname," +
                    "tbl_employees.emp_fname," +
                    "tbl_employees.emp_employmentstatus," +
                    "tbl_department.department_name," +
                    "tbl_employees.emp_status " +
                    "FROM tbl_employees LEFT JOIN tbl_department " +
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
        }
        return employeeList;

    }

    public ObservableList<Employee> getAllEmployeesExcept(boolean active, Date dt1, Date dt2, int department) {
        ObservableList<Employee> employeeList = FXCollections.observableArrayList();
        if (active == true) {
            String command = "SELECT emp_id," +
                    "tbl_employees.emp_lname," +
                    "tbl_employees.emp_fname," +
                    "tbl_employees.emp_employmentstatus," +
                    "tbl_department.department_name," +
                    "tbl_employees.emp_status " +
                    "FROM tbl_employees JOIN tbl_department " +
                    "ON tbl_employees.emp_department = tbl_department.department_id " +
                    "WHERE tbl_employees.emp_status = ? " +

                    "AND tbl_employees.emp_id NOT IN " +
                    "(SELECT emp_id " +
                    "FROM tbl_attendance " +
                    "WHERE tbl_attendance.emp_attendance_date >= ? " +
                    "AND tbl_attendance.emp_attendance_date < ?) " +
                    "AND tbl_department.department_id = ?";
            try (Connection connection = connect();
                 PreparedStatement preparedStatement = connection.prepareStatement(command)) {

                preparedStatement.setBoolean(1, active);
                preparedStatement.setDate(2, dt1);
                preparedStatement.setDate(3, dt2);
                preparedStatement.setInt(4, department);

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
        } else {
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
        }
        return employeeList;

    }

    public ObservableList<Employee> getAllEmployees() {
        ObservableList<Employee> employeeList = FXCollections.observableArrayList();
        String command = "SELECT emp_id," +
                "tbl_employees.emp_lname," +
                "tbl_employees.emp_fname," +
                "tbl_employees.emp_employmentstatus," +
                "tbl_department.department_name," +
                "tbl_employees.emp_status " +
                "FROM tbl_employees LEFT JOIN tbl_department " +
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

    public ObservableList<Employee> getAllEmployeePayslip() {
        ObservableList<Employee> employeeList = FXCollections.observableArrayList();
        String command = "SELECT te.emp_id as id," +
                "te.emp_lname || ' ' || te.emp_fname || ' ' || te.emp_mname as fullname," +
                "td.department_name as department," +
                "te.emp_position as position " +
                "FROM tbl_employees te " +
                "JOIN tbl_department td " +
                "ON te.emp_department = td.department_id " +
                "WHERE te.emp_status = ?";
        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(command)) {
            preparedStatement.setBoolean(1, true);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                employeeList.add(new Employee(
                        resultSet.getInt("id"),
                        resultSet.getString("fullname"),
                        resultSet.getString("department"),
                        resultSet.getString("position")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employeeList;

    }

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

    public Employee getFinalEmployeeIDFromDatabase(Employee emp) {
        String command = "SELECT * FROM tbl_employees";
        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(command)) {

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                emp.setEmployee_ID(resultSet.getInt("emp_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return emp;
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

            callAlert("Employee " + employee.getFirst_Name() + " " + employee.getLast_Name() + " has been added!", 2);
        } catch (SQLException e) {
            System.out.println("Error connecting to SQLite database");
            e.printStackTrace();
        }
    }

    public void addMultipleEmployee(ObservableList<Employee> employeeList) {
        String command = "INSERT INTO `tbl_employees`" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(command)) {
            int employeeCount = employeeList.size();
            int currentid = getFinalEmployeeIDFromDatabase(new Employee()).getEmployee_ID() + 1;
            int i = 0;
            for (Employee emp : employeeList) {
                preparedStatement.setInt(1, currentid + i);
                preparedStatement.setString(2, emp.getNationality()); //
                preparedStatement.setString(3, emp.getMarital_Status());
                preparedStatement.setInt(4, emp.getDepartment()); // Need to connect to dept table
                preparedStatement.setString(5, emp.getPosition());
                preparedStatement.setString(6, emp.getEmployment_Status());
                preparedStatement.setString(7, emp.getFirst_Name());
                preparedStatement.setString(8, emp.getLast_Name());
                preparedStatement.setString(9, emp.getMiddle_Name());
                preparedStatement.setString(10, emp.getExtension());
                preparedStatement.setString(11, emp.getAddress());
                preparedStatement.setBoolean(12, emp.isGender());
                preparedStatement.setString(13, emp.getNumber());
                preparedStatement.setDate(14, emp.getBirthdate());
                preparedStatement.setBoolean(15, emp.isActive());

                preparedStatement.setString(16, emp.getContact_Name());
                preparedStatement.setString(17, emp.getContact_Relationship());
                preparedStatement.setString(18, emp.getContact_Address());
                preparedStatement.setString(19, emp.getContact_Number());
                preparedStatement.setString(20, emp.getEmployee_Biometrics());

                preparedStatement.executeUpdate();
                i++;
            }


            callAlert(employeeCount + " Employee has been successfully added!", 2);
        } catch (SQLException e) {
            System.out.println("Error connecting to SQLite database");
            e.printStackTrace();
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

            callAlert("Employee " + employee.getFirst_Name() + " " + employee.getLast_Name() + " has been updated!", 2);
        } catch (SQLException e) {
            System.out.println("Error connecting to SQLite database");
            callAlert("Error connecting to SQLite database", 1);
            e.printStackTrace();
        }
    }

    public boolean checkIfEmployeeIDExists(int empid) {
        boolean exist = true;
        String command = "SELECT * " +
                "FROM tbl_employees te " +
                "WHERE emp_id = ?";

        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(command)) {
            preparedStatement.setInt(1, empid);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                exist = false;
            }
        } catch (SQLException e) {
            System.out.println("Error connecting to SQLite database");
            e.printStackTrace();
        }
        return exist;
    }

    public boolean checkIfEmployeeIsActive(int empid) {
        boolean active = false;
        String command = "SELECT * " +
                "FROM tbl_employees te " +
                "WHERE emp_id = ?";

        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(command)) {
            preparedStatement.setInt(1, empid);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                active = resultSet.getBoolean("emp_status");
            }
        } catch (SQLException e) {
            System.out.println("Error connecting to SQLite database");
            e.printStackTrace();
        }
        return active;
    }

    public void updateBiometrics(Employee emp) {
        String command = "UPDATE tbl_employees " +
                "SET emp_biometrics_info = ? " +
                "WHERE emp_id = ?";
        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(command)) {

            String generatedRandomString = emp.getEmployee_ID() + generateRandomString();
            preparedStatement.setString(1, generatedRandomString);
            preparedStatement.setInt(2, emp.getEmployee_ID());

            preparedStatement.executeUpdate();

            callAlert("Employee " + emp.getFull_Name_Without_Middle() + " QR code has been re-regenerated!", 2);

            createQR(generatedRandomString, emp.getEmployee_ID() + "");
        } catch (SQLException e) {
            System.out.println("Error connecting to SQLite database");
            callAlert("Error connecting to SQLite database", 1);
            e.printStackTrace();
        }
    }

    public void regenerateAllBiometricsInfo() {
        String command = "UPDATE tbl_employees " +
                "SET emp_biometrics_info = ? " +
                "WHERE emp_id = ?";
        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(command)) {

            for (Employee emp : getAllEmployees()) {
                preparedStatement.setString(1, emp.getEmployee_ID() + generateRandomString());
                preparedStatement.setInt(2, emp.getEmployee_ID());

                preparedStatement.executeUpdate();
            }


            preparedStatement.executeUpdate();

            callAlert("All Employees QR code has been re-regenerated!", 2);
        } catch (SQLException e) {
            System.out.println("Error connecting to SQLite database");
            callAlert("Error connecting to SQLite database", 1);
            e.printStackTrace();
        }
    }

}
