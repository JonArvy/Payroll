package Controller.Employee;

import Classes.Converters;
import Database.SQLDepartment;
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

public class AddEmployeeController {

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
    private void nextPage(ActionEvent event) {
        loadAddEmployeeBiometrics();
    }

    @FXML
    private void reset(ActionEvent event) {

    }

    @FXML
    private void initialize() {
        initializeContainers();
    }

    /****************************** FXML ENDS HERE ******************************/

    private Admin admin;
    private AnchorPane container;
    private Employee employee;

    private ObservableList<BooleanValue> genderList;
    private ObservableList<BooleanValue> statusList;
    private ObservableList<Department> departmentList = FXCollections.observableArrayList();

    private SQLDepartment sqlDepartment = new SQLDepartment();

    Converters converters = new Converters();

    public void setRetrievedData(Admin admin, AnchorPane anchorPane) {
        this.admin = admin;
        this.container = anchorPane;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    private void loadAddEmployeeBiometrics() {
        AddEmployeeBiometricsController controller;

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UI/Employee/AddEmployeeBiometrics.fxml"));
            fxmlLoader.load();

            controller = fxmlLoader.getController();
            controller.setRetrievedData(admin, container);

            controller.setEmployee(employee);

            AnchorPane anchorPane = fxmlLoader.getRoot();
            container.getChildren().clear();
            container.getChildren().add(anchorPane);
        } catch (IOException ex) {
            ex.printStackTrace();
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
    }
}

