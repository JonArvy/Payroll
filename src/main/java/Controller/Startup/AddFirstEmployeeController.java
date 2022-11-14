package Controller.Startup;

import Classes.Converters;
import Controller.Employee.AddEmployeeBiometricsController;
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

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static Classes.CustomAlert.callAlert;

public class AddFirstEmployeeController {

    @FXML
    private TextArea addemployee_address;

    @FXML
    private DatePicker addemployee_bdate;

    @FXML
    private Button addemployee_button_next;

    @FXML
    private Button addemployee_button_reset;

    @FXML
    private TextArea addemployee_contactaddress;

    @FXML
    private TextField addemployee_contactname;

    @FXML
    private TextField addemployee_contactnumber;

    @FXML
    private TextField addemployee_contactrelationship;

    @FXML
    private ComboBox<Department> addemployee_department;

    @FXML
    private TextField addemployee_empid;

    @FXML
    private ComboBox<String> addemployee_empstatus;

    @FXML
    private TextField addemployee_extension;

    @FXML
    private TextField addemployee_firstname;

    @FXML
    private ComboBox<BooleanValue> addemployee_gender;

    @FXML
    private TextField addemployee_lastname;

    @FXML
    private ComboBox<String> addemployee_maritalstatus;

    @FXML
    private TextField addemployee_middlename;

    @FXML
    private TextField addemployee_nationality;

    @FXML
    private TextField addemployee_number;

    @FXML
    private TextField addemployee_position;

    @FXML
    private ComboBox<BooleanValue> addemployee_status;

    @FXML
    void nextPage(ActionEvent event) {
        validateFields();
    }

    @FXML
    void reset(ActionEvent event) {

    }

    @FXML
    private void initialize() {
        initializeContainers();
    }
    /****************************** FXML ENDS HERE ******************************/

    private AnchorPane container;

    private Department department;
    private Employee employee;
    private Admin admin;

    private ObservableList<BooleanValue> genderList;
    private ObservableList<BooleanValue> statusList;
    private ObservableList<Department> departmentList = FXCollections.observableArrayList();

    private SQLDepartment sqlDepartment = new SQLDepartment();
    private SQLEmployee sqlEmployee = new SQLEmployee();
    private Converters converters = new Converters();

    public void setDepartment(Department department) {
        this.department = department;
    }

    public void setEmployee(Employee employee) {
        if (employee != null) {
            this.employee = employee;
        }
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public void setRetrievedData(AnchorPane anchorPane) {
        this.container = anchorPane;
    }



    private void validateFields() {
        try {
            addemployee_bdate.getConverter().fromString(addemployee_bdate.getEditor().getText());

            int empid = Integer.parseInt(addemployee_empid.getText());
            String marital_status = addemployee_maritalstatus.getValue();
            int department = addemployee_department.getValue().getDepartment_ID(); // Will fix later
            String position = addemployee_position.getText();
            String employment_status = addemployee_empstatus.getValue();
            String first_name = addemployee_firstname.getText();
            String last_name = addemployee_lastname.getText();
            String address = addemployee_address.getText();
            boolean isMale = addemployee_gender.getValue().isBool();
            String number = addemployee_number.getText();
            boolean isActive = addemployee_status.getValue().isBool();
            String contact_full_name = addemployee_contactname.getText();
            String contact_relationship = addemployee_contactrelationship.getText();
            String contact_number = addemployee_contactnumber.getText();

            String nationality = addemployee_nationality.getText();
            String middle_name = addemployee_middlename.getText();
            String extension_name = addemployee_extension.getText();
            String contact_address = addemployee_contactaddress.getText();

            Date birthdate = Date.valueOf(LocalDate.of(2000, 1, 1));
            try {
                birthdate = Date.valueOf(addemployee_bdate.getValue());
            } catch (RuntimeException e) {
                birthdate = Date.valueOf(LocalDate.of(2000, 1, 1));
            }

            if (first_name.trim().equals("")) {
                callAlert("The first name is empty!", 4);
            } else if (last_name.trim().equals("")) {
                callAlert("The last name is empty!", 4);
            } else if (number.trim().equals("")) {
                callAlert("The contact number is empty!", 4);
            } else if (position.trim().equals("")) {
                callAlert("The position is empty!", 4);
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
                if (sqlEmployee.checkIfEmployeeIDExists(empid)) {
                    this.employee = new Employee( empid, nationality, marital_status,
                            department, position, employment_status, first_name, last_name, middle_name,
                            extension_name, address, isMale, number, birthdate, isActive, contact_full_name,
                            contact_relationship, contact_address, contact_number, "Biometrics");

//                    loadAddEmployeeBiometrics();
                    loadAddFirstDepartment(employee);
                } else {
                    callAlert("Employee with same ID already exists!", 3);
                }
            }

        } catch (NumberFormatException e) {
            callAlert("Invalid ID", 3);
        } catch (DateTimeParseException o) {
            callAlert("Invalid Date", 3);
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

        addemployee_department.setItems(departmentList);
        addemployee_department.getSelectionModel().select(0);

        addemployee_department.setConverter(converters.departmentConverter());

        addemployee_gender.setItems(genderList);
        addemployee_gender.getSelectionModel().select(0);
        addemployee_status.setItems(statusList);
        addemployee_status.getSelectionModel().select(0);

        addemployee_gender.setConverter(converters.booleanValueConverter(genderList));
        addemployee_status.setConverter(converters.booleanValueConverter(statusList));

        addemployee_maritalstatus.getItems().addAll("Single", "Married", "Widowed", "Annuled");
        addemployee_maritalstatus.getSelectionModel().select(0);

        addemployee_empstatus.getItems().addAll("Regular", "Contractual", "Part-Time");
        addemployee_empstatus.getSelectionModel().select(0);

        int currentid = sqlEmployee.getFinalEmployeeIDFromDatabase(new Employee()).getEmployee_ID() + 1;
        addemployee_empid.setText(Integer.toString(currentid));
    }

    private void fillFields() {
        addemployee_empid.setText(Integer.toString(employee.getEmployee_ID()));
        addemployee_nationality.setText(employee.getNationality());
        addemployee_maritalstatus.setValue(employee.getMarital_Status());
        addemployee_department.getSelectionModel().select(employee.getDepartment());
        addemployee_position.setText(employee.getPosition());
        addemployee_empstatus.setValue(employee.getEmployment_Status());
        addemployee_firstname.setText(employee.getFirst_Name());
        addemployee_lastname.setText(employee.getLast_Name());
        addemployee_middlename.setText(employee.getMiddle_Name());
        addemployee_extension.setText(employee.getExtension());
        addemployee_address.setText(employee.getAddress());
        addemployee_gender.getSelectionModel().select(employee.isGender() ? 0 : 1);
        addemployee_number.setText(employee.getNumber());
        addemployee_bdate.setValue(employee.getBirthdate().toLocalDate());
        addemployee_status.getSelectionModel().select(employee.isActive() ? 0 : 1);
        addemployee_contactname.setText(employee.getContact_Name());
        addemployee_contactrelationship.setText(employee.getContact_Relationship());
        addemployee_contactaddress.setText(employee.getContact_Address());
        addemployee_contactnumber.setText(employee.getContact_Number());
    }

    private void loadAddFirstDepartment(Employee employee) {
        AddFirstDepartmentController controller;

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UI/Startup/AddFirstEmployee.fxml"));
            fxmlLoader.load();

            controller = fxmlLoader.getController();
            controller.setRetrievedData(container);

            controller.setEmployee(employee);
            controller.setDepartment(department);
            controller.setAdmin(admin);

            AnchorPane anchorPane = fxmlLoader.getRoot();
            container.getChildren().clear();
            container.getChildren().add(anchorPane);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
