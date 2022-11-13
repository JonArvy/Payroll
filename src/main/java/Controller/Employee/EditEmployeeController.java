package Controller.Employee;

import Classes.Converters;
import Database.SQLDepartment;
import Database.SQLEmployee;
import Models.Admin;
import Models.BooleanValue;
import Models.Department;
import Models.Employee;
import cw.payroll.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.util.StringConverter;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.stream.Collectors;

import static Classes.CustomAlert.callAlert;

public class EditEmployeeController {

    @FXML
    private AnchorPane biometrics_panel;

    @FXML
    private TextArea editemployee_address;

    @FXML
    private DatePicker editemployee_birthdate;

    @FXML
    private Button editemployee_button_cancel;

    @FXML
    private Button editemployee_button_save;

    @FXML
    private TextArea editemployee_contactaddress;

    @FXML
    private TextField editemployee_contactname;

    @FXML
    private TextField editemployee_contactnumber;

    @FXML
    private TextField editemployee_contactrelationship;

    @FXML
    private ComboBox<Department> editemployee_department;

    @FXML
    private TextField editemployee_employeeid;

    @FXML
    private ComboBox<String> editemployee_employmentstatus;

    @FXML
    private TextField editemployee_extension;

    @FXML
    private TextField editemployee_firstname;

    @FXML
    private ComboBox<BooleanValue> editemployee_gender;

    @FXML
    private TextField editemployee_lastname;

    @FXML
    private ComboBox<String> editemployee_maritalstatus;

    @FXML
    private TextField editemployee_middlename;

    @FXML
    private TextField editemployee_nationality;

    @FXML
    private TextField editemployee_number;

    @FXML
    private TextField editemployee_position;

    @FXML
    private ComboBox<BooleanValue> editemployee_status;

    @FXML
    private void cancel(ActionEvent event) {
        loadManageEmployee();
    }

    @FXML
    private void saveEmployee(ActionEvent event) {
        validateFields();
    }

    @FXML
    private void initialize() {
        initializeContainers();
    }

    /****************************** FXML ENDS HERE ******************************/

    private Admin admin;
    private AnchorPane container;

    private Employee emp;

    private ObservableList<BooleanValue> genderList;
    private ObservableList<BooleanValue> statusList;
    private ObservableList<Department> departmentList = FXCollections.observableArrayList();

    private SQLEmployee sqlEmployee = new SQLEmployee();
    private SQLDepartment sqlDepartment = new SQLDepartment();

    private Converters converters = new Converters();

    public void setRetrievedData(Admin admin, AnchorPane anchorPane) {
        this.admin = admin;
        this.container = anchorPane;
    }

    public void setEmployee(Employee employee) {
        this.emp = employee;
        loadEmployeeFields();
    }

    private void loadManageEmployee() {
        ManageEmployeeController controller;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UI/Employee/ManageEmployee.fxml"));
            fxmlLoader.load();

            controller = fxmlLoader.getController();
            controller.setRetrievedData(admin, container);

            AnchorPane anchorPane = fxmlLoader.getRoot();
            container.getChildren().clear();
            container.getChildren().add(anchorPane);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void loadEmployeeFields() {
        Employee employee = sqlEmployee.getEmployee(new Employee(emp.getEmployee_ID()));

        editemployee_employeeid.setText(employee.getEmployee_ID() + "");
        editemployee_nationality.setText(employee.getNationality());
        editemployee_maritalstatus.setValue(employee.getMarital_Status());
        editemployee_department.getSelectionModel().select(employee.getDepartment() - 1); //Will Fix Later
        editemployee_position.setText(employee.getPosition());
        editemployee_employmentstatus.setValue(employee.getEmployment_Status());
        editemployee_firstname.setText(employee.getFirst_Name());
        editemployee_lastname.setText(employee.getLast_Name());
        editemployee_middlename.setText(employee.getMiddle_Name());
        editemployee_extension.setText(employee.getExtension());
        editemployee_address.setText(employee.getAddress());
        editemployee_gender.getSelectionModel().select(employee.isGender() ? 0 : 1);  //Will Fix Later
        editemployee_number.setText(employee.getNumber());
        editemployee_birthdate.setValue(employee.getBirthdate().toLocalDate());
        editemployee_status.getSelectionModel().select(employee.isActive() ? 0 : 1);  //Will Fix Later
        editemployee_contactname.setText(employee.getContact_Name());
        editemployee_contactrelationship.setText(employee.getContact_Relationship());
        editemployee_contactaddress.setText(employee.getContact_Address());
        editemployee_contactnumber.setText(employee.getContact_Number());

    }

    private void validateFields() {
        try {
            editemployee_birthdate.getConverter().fromString(editemployee_birthdate.getEditor().getText());

            int empid = Integer.parseInt(editemployee_employeeid.getText());
            String marital_status = editemployee_maritalstatus.getValue();
            int department = editemployee_department.getValue().getDepartment_ID(); // Will fix later
            String position = editemployee_position.getText();
            String employment_status = editemployee_employmentstatus.getValue();
            String first_name = editemployee_firstname.getText();
            String last_name = editemployee_lastname.getText();
            String address = editemployee_address.getText();
            boolean isMale = editemployee_gender.getValue().isBool();
            String number = editemployee_number.getText();
            boolean isActive = editemployee_status.getValue().isBool();
            String contact_full_name = editemployee_contactname.getText();
            String contact_relationship = editemployee_contactrelationship.getText();
            String contact_number = editemployee_contactnumber.getText();

            String nationality = editemployee_nationality.getText();
            String middle_name = editemployee_middlename.getText();
            String extension_name = editemployee_extension.getText();
            String contact_address = editemployee_contactaddress.getText();

            Date birthdate = Date.valueOf(LocalDate.of(2000, 1, 1));
            try {
                birthdate = Date.valueOf(editemployee_birthdate.getValue());
            } catch (RuntimeException e) {
                birthdate = Date.valueOf(LocalDate.of(2000, 1, 1));
            }

            if (empid > 100000 || empid <= 0) {
                callAlert("Invalid Employee ID", 3);
            } else if (first_name.trim().equals("")) {
                callAlert("The first name is empty!", 3);
            } else if (last_name.trim().equals("")) {
                callAlert("The last name is empty!", 3);
            } else if (number.trim().equals("")) {
                callAlert("The contact number is empty!", 3);
            } else if (position.trim().equals("")) {
                callAlert("The position is empty!", 3);
            } else {
                if (address.trim().equals("")) {
                    address.equals("");
                }
                if (contact_full_name.trim().equals("")) {
                    contact_full_name.equals("");
                }
                if (contact_relationship.trim().equals("")) {
                    contact_relationship.equals("");
                }
                if (contact_number.trim().equals("")) {
                    contact_number.equals("");
                }
                sqlEmployee.updateEmployee(new Employee(empid, nationality,
                    marital_status, department, position, employment_status, first_name, last_name,
                    middle_name, extension_name, address, isMale, number, birthdate, isActive,
                    contact_full_name, contact_relationship, contact_address, contact_number
                ));

                loadManageEmployee();
            }

        } catch (NumberFormatException e) {
            callAlert("Invalid ID", 1);
        } catch (DateTimeParseException o) {
            callAlert("Invalid Date", 1);
        } catch (NullPointerException e) {
            callAlert("Please fill up all the fields", 1);
        }
    }

    private void initializeContainers() {
        genderList = FXCollections.observableArrayList(
                new BooleanValue("Male", true),
                new BooleanValue("Female", false));
        statusList = FXCollections.observableArrayList(
                new BooleanValue("Active", true),
                new BooleanValue("Inactive", false));



        departmentList.clear();
        departmentList = sqlDepartment.getDepartment();

        editemployee_department.setItems(departmentList);

        editemployee_department.setConverter(converters.departmentConverter());

        editemployee_gender.setItems(genderList);
        editemployee_status.setItems(statusList);

        editemployee_gender.setConverter(converters.booleanValueConverter(genderList));
        editemployee_status.setConverter(converters.booleanValueConverter(statusList));

        editemployee_maritalstatus.getItems().addAll("Single", "Married", "Widowed", "Annuled");
        editemployee_maritalstatus.getSelectionModel().select(0);

        editemployee_employmentstatus.getItems().addAll("Regular", "Contractual", "Part-Time");
        editemployee_employmentstatus.getSelectionModel().select(0);
    }


}
