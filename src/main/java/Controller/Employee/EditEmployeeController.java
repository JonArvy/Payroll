package Controller.Employee;

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
import java.util.stream.Collectors;

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
        loadManageEmployee();
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

    public StringConverter<Department> departmentConverter() {
        StringConverter<Department> converter = new StringConverter<Department>() {
            @Override
            public String toString(Department department) {
                String s = "";
                try {
                    s = department.getDepartment_Name();
                } catch (NullPointerException e) {
                    s = "";
                }
                return s;
            }

            @Override
            public Department fromString(String s) {
                return departmentList.stream()
                        .filter(item -> item.getDepartment_Name().equals(s))
                        .collect(Collectors.toList()).get(0);
            }
        };
        return converter;
    }

    public StringConverter<BooleanValue> booleanValueConverter(ObservableList<BooleanValue> list) {
        StringConverter<BooleanValue> converter = new StringConverter<BooleanValue>() {
            @Override
            public String toString(BooleanValue booleanValue) {
                String s = "";
                try {
                    s = booleanValue.getName();
                } catch (NullPointerException e) {
                    s = "";
                }
                return s;
            }

            @Override
            public BooleanValue fromString(String s) {
                return list.stream()
                        .filter(item -> item.getName().equals(s))
                        .collect(Collectors.toList()).get(0);
            }
        };
        return converter;
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

        editemployee_department.setConverter(departmentConverter());

        editemployee_gender.setItems(genderList);
        editemployee_status.setItems(statusList);

        editemployee_gender.setConverter(booleanValueConverter(genderList));
        editemployee_status.setConverter(booleanValueConverter(statusList));

        editemployee_maritalstatus.getItems().addAll("Single", "Married", "Widowed", "Annuled");
        editemployee_maritalstatus.getSelectionModel().select(0);

        editemployee_employmentstatus.getItems().addAll("Regular", "Contractual", "Part-Time");
        editemployee_employmentstatus.getSelectionModel().select(0);
    }


}
