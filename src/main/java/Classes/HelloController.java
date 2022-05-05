package Classes;

import Enums.ShiftType;
import Models.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private Button main_admin_button;

    @FXML
    private Pane main_admin_panel;

    @FXML
    private Button main_addemployee_button;

    @FXML
    private Pane main_addemployee_panel_1;

    @FXML
    private Button addemployee_button_next;

    @FXML
    private Pane main_addemployee_panel_2;

    @FXML
    private Button main_manageemployee_button;

    @FXML
    private Pane main_manageemployee_panel_1;

    @FXML
    private Button addemployee_back_button;

    @FXML
    private Button main_dailyattendance_button;

    @FXML
    private Pane main_dailyattendance_panel_1;

    @FXML
    private Button main_attendancereport_button;

    @FXML
    private Pane main_attendancereport_panel_1;

    @FXML
    private Button main_payslip_button;

    @FXML
    private Pane main_payslip_panel_1;

    @FXML
    private Button main_payrollsummary_button;

    @FXML
    private Pane main_payrollsummary_panel_1;

    @FXML
    private Button main_department_button;

    @FXML
    private Pane main_department_panel_1;

    @FXML
    private Button main_department_shift_button;

    @FXML
    private Pane main_department_shift_panel_1;

    @FXML
    private Button main_department_shift_back_button;

    @FXML
    private Button main_department_add_department_button;

    @FXML
    private Pane main_department_add_department_panel;

    @FXML
    private Button main_department_add_department_cancel_button;

    @FXML
    private Button main_department_edit_department_button;

    @FXML
    private Pane main_department_edit_department_panel;

    @FXML
    private Button main_department_edit_department_cancel_button;

    @FXML
    private Button main_department_add_shift_button;

    @FXML
    private Pane main_department_add_shift_panel;

    @FXML
    private Button main_department_add_shift_cancel_button;

    @FXML
    private Button main_holiday_button;

    @FXML
    private Pane main_holiday_panel_1;

    @FXML
    private Button main_holiday_add_holiday_button;

    @FXML
    private Pane main_holiday_add_holiday_panel_1;

    @FXML
    private Button main_holiday_add_holiday_cancel_button;

    @FXML
    private Button main_bonus_button;

    @FXML
    private Pane main_bonus_panel_1;

    @FXML
    private Button main_bonus_add_bonus_button;

    @FXML
    private Pane main_bonus_add_bonus_panel_1;

    @FXML
    private Button main_bonus_add_bonus_cancel_button;

    @FXML
    private Button main_credentials_button;

    @FXML
    private Pane main_credentials_panel_1;

    @FXML
    private Button main_logout_button;

    //              ADD EMPLOYEEEE VARIABLES START HERE
    @FXML
    private TextArea main_addemployee_address;

    @FXML
    private DatePicker main_addemployee_bdate;

    @FXML
    private TextArea main_addemployee_contactaddress;

    @FXML
    private TextField main_addemployee_contactname;

    @FXML
    private TextField main_addemployee_contactnumber;

    @FXML
    private TextField main_addemployee_contactrelationship;

    @FXML
    private ComboBox<?> main_addemployee_dept;

    @FXML
    private TextField main_addemployee_empid;

    @FXML
    private ComboBox<?> main_addemployee_empstatus;

    @FXML
    private TextField main_addemployee_ext;

    @FXML
    private TextField main_addemployee_fname;

    @FXML
    private ComboBox<String> main_addemployee_gender;

    @FXML
    private TextField main_addemployee_lname;

    @FXML
    private ComboBox<?> main_addemployee_maritalstatus;

    @FXML
    private TextField main_addemployee_mname;

    @FXML
    private TextField main_addemployee_nationality;

    @FXML
    private TextField main_addemployee_number;

    @FXML
    private TextField main_addemployee_position;

    @FXML
    private ComboBox<String> main_addemployee_status;

    @FXML
    private Pane main_admin_panel1;

    @FXML
    private Button addemployee_upload_button;

    //              ADD EMPLOYEEEE VARIABLES ENDS HERE

    //              MANAGE EMPLOYEE STARTS HERE
    @FXML
    private TableView main_manageemployee_table_view;

    @FXML
    private TableColumn<Employee, Integer> main_manageemployee_col_action;

    @FXML
    private TableColumn<Employee, String> main_manageemployee_col_dept;

    @FXML
    private TableColumn<Employee, Integer> main_manageemployee_col_empid;

    @FXML
    private TableColumn<Employee, String> main_manageemployee_col_empstatus;

    @FXML
    private TableColumn<Employee, String> main_manageemployee_col_fname;

    @FXML
    private TableColumn<Employee, String> main_manageemployee_col_lname;

    @FXML
    private TableColumn<Employee, Boolean> main_manageemployee_col_status;



    private SQLExecution sql;
    private ObservableList<Employee> departmentList = FXCollections.observableArrayList();
    private ObservableList<Employee> employeeList = FXCollections.observableArrayList();




    @FXML
    public void clickNavigation_Choices(ActionEvent event) {
        System.out.println(event.getSource().toString());

        //Add Employee Button
        if (event.getSource() == main_addemployee_button) {
            main_addemployee_panel_1.toFront();

            //Manage Employee Button
        } else if (event.getSource() == main_manageemployee_button) {
            main_manageemployee_panel_1.toFront();
            showManageEmployeeTable();

        //Add Employee Next Button (to second page)
        } else if (event.getSource() == addemployee_button_next) {
            main_addemployee_panel_2.toFront();

        //Add Employee Back Button (back to first page)
        } else if (event.getSource() == addemployee_back_button) {
            main_addemployee_panel_1.toFront();

        //Admin Button
        } else if (event.getSource() == main_admin_button) {
            main_admin_panel.toFront();

        //Daily Attendance Button
        } else if (event.getSource() == main_dailyattendance_button) {
            main_dailyattendance_panel_1.toFront();

        //Attendance Report Button
        } else if (event.getSource() == main_attendancereport_button) {
            main_attendancereport_panel_1.toFront();

        //Payslip Button
        } else if (event.getSource() == main_payslip_button) {
            main_payslip_panel_1.toFront();

        //Payroll Summary Button
        } else if (event.getSource() == main_payrollsummary_button) {
            main_payrollsummary_panel_1.toFront();

        //Department Button page 1
        } else if (event.getSource() == main_department_button) {
            main_department_panel_1.toFront();

        //Department Button page 1 - Add Department Button
        } else if (event.getSource() == main_department_add_department_button) {
            main_department_add_department_panel.toFront();

        //Department Button page 1 - Add Department - Cancel Button
        } else if (event.getSource() == main_department_add_department_cancel_button) {
            main_department_panel_1.toFront();

        //Department Button page 1 - Edit Department Button
        } else if (event.getSource() == main_department_edit_department_button) {
            main_department_edit_department_panel.toFront();

        //Department Button page 1 - Edit Department - Cancel Button
        } else if (event.getSource() == main_department_edit_department_cancel_button) {
            main_department_panel_1.toFront();

        //Department Button page 2 - Shift page
        } else if (event.getSource() == main_department_shift_button) {
            main_department_shift_panel_1.toFront();

        //Department Button back to Page 1 from Page 2
        } else if (event.getSource() == main_department_shift_back_button) {
            main_department_panel_1.toFront();

        //Department Button page 2 - Shift page - Add Shift
        } else if (event.getSource() == main_department_add_shift_button) {
            main_department_add_shift_panel.toFront();

        //Department Button page 2 - Shift page - Add Shift - Cancel button
        } else if (event.getSource() == main_department_add_shift_cancel_button) {
            main_department_shift_panel_1.toFront();

        //Holiday Button
        } else if (event.getSource() == main_holiday_button) {
            main_holiday_panel_1.toFront();

        //Holiday Button - Add Holiday
        } else if (event.getSource() == main_holiday_add_holiday_button) {
            main_holiday_add_holiday_panel_1.toFront();

        //Holiday Button
        } else if (event.getSource() == main_holiday_add_holiday_cancel_button) {
            main_holiday_panel_1.toFront();

        //Bonus Button
        } else if (event.getSource() == main_bonus_button) {
            main_bonus_panel_1.toFront();

        //Bonus Button - Add Bonus
        } else if (event.getSource() == main_bonus_add_bonus_button) {
            main_bonus_add_bonus_panel_1.toFront();

        //Bonus Button - Add Bonus - Cancel button
        } else if (event.getSource() == main_bonus_add_bonus_cancel_button) {
            main_bonus_panel_1.toFront();

        //Credentials Button
        } else if (event.getSource() == main_credentials_button) {
            main_credentials_panel_1.toFront();

        //Logout Button
        } else if (event.getSource() == main_logout_button) {
            System.exit(0);
        }
    }

    @FXML
    public void addEmployee(ActionEvent event) {
        boolean gender = true;
        boolean status = true;
        if (main_addemployee_gender.getValue() == "Male") {
            gender = true;
        } else {
            gender = false;
        }
        if (main_addemployee_status.getValue() == "Active") {
            status = true;
        } else {
            status = false;
        }
        new Employee(
                Integer.parseInt(main_addemployee_empid.getText()),
                main_addemployee_nationality.getText(),
                main_addemployee_maritalstatus.getValue().toString(),
                Integer.parseInt(main_addemployee_dept.getValue().toString()), // Integer
                main_addemployee_position.getText(),
                main_addemployee_empstatus.getValue().toString(),
                main_addemployee_fname.getText(),
                main_addemployee_lname.getText(),
                main_addemployee_mname.getText(),
                main_addemployee_ext.getText(),
                main_addemployee_address.getText(),
                gender, // Boolean
                main_addemployee_number.getText(),
                Date.valueOf(main_addemployee_bdate.getValue()),
                status, // Boolean
                main_addemployee_contactname.getText(),
                main_addemployee_contactrelationship.getText(),
                main_addemployee_contactaddress.getText(),
                main_addemployee_contactnumber.getText(),
                "Biometrics"
        );

        main_addemployee_dept.getValue();
        main_addemployee_gender.getValue();
        main_addemployee_status.getValue();

        main_addemployee_bdate.getValue();
        System.out.println(main_addemployee_bdate.getValue());
    }

    public void showManageEmployeeTable() {
        employeeList.clear();
        employeeList = sql.getAllEmployees();

        main_manageemployee_col_empid.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("Employee_ID"));
        main_manageemployee_col_lname.setCellValueFactory(new PropertyValueFactory<Employee, String>("Last_Name"));
        main_manageemployee_col_fname.setCellValueFactory(new PropertyValueFactory<Employee, String>("First_Name"));
        main_manageemployee_col_empstatus.setCellValueFactory(new PropertyValueFactory<Employee, String>("Employment_Status"));
        main_manageemployee_col_dept.setCellValueFactory(new PropertyValueFactory<Employee, String>("Department"));
        main_manageemployee_col_status.setCellValueFactory(new PropertyValueFactory<Employee, Boolean>("Active"));

        main_manageemployee_col_action.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("Employee_ID"));

        main_manageemployee_table_view.setItems(employeeList);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Initialize Variables
        sql = new SQLExecution();

        //Add Employee Initialize constants
//        main_addemployee_gender.setItems(FXCollections.observableArrayList(
//                new BooleanValue("Male", true),
//                new BooleanValue("Female", false)));
//        main_addemployee_status.setItems(FXCollections.observableArrayList(
//                new BooleanValue("Active", true),
//                new BooleanValue("Inactive", false)));
//
//        main_addemployee_gender.setConverter(new StringConverter<BooleanValue>() {
//            @Override
//            public String toString(BooleanValue booleanValue) {
//                return booleanValue.getName();
//            }
//
//            @Override
//            public BooleanValue fromString(String s) {
//                return null;
//            }
//        });


        main_addemployee_gender.getItems().addAll("Male", "Female");
        main_addemployee_status.getItems().addAll("Active", "Inactive");
        main_addemployee_gender.getSelectionModel().select(0);
        main_addemployee_status.getSelectionModel().select(0);

    }


    public void addDepartment() {
        int id = 0;
        String name = "a";
        double monthlyrate = 20000;
        int dayspermonth = 20;
        int hoursperday = 8;

        sql.addDepartment(new Department(id, name, monthlyrate, dayspermonth, hoursperday));
    }


    public void addBonus() {
        int id = 0;
        String bonusName = "";

        int bonusRecipient = 0;
        Date bonusDate = new Date(2022, 3, 29);
        double bonusAmount = 1500;

        sql.addBonus(new Bonus(id, bonusName, bonusAmount, bonusRecipient, bonusDate));
    }

    public void addShift() {
        int id = 0;
        ShiftType shiftType = ShiftType.DEPARTMENT;
        int recipient = 1;
        boolean shiftSunday = true;
        boolean shiftMonday = true;
        boolean shiftTuesday = false;
        boolean shiftWednesday = true;
        boolean shiftThursday = true;
        boolean shiftFriday = true;
        boolean shiftSaturday = true;

        sql.addShift(new Shift(
                id,
                shiftType,
                recipient,
                shiftSunday,
                shiftMonday,
                shiftTuesday,
                shiftWednesday,
                shiftThursday,
                shiftFriday,
                shiftSaturday
        ));
    }

    public void addAdmin() {
        int adminId = 0;
        int empId = 0;
        int adminGrantor = 0;
        int adminDisabler = 0;
        String adminPassword = "Hawo";

        sql.addAdmin(new Admin(
                adminId,
                empId,
                adminPassword,
                adminGrantor,
                adminDisabler
        ));

    }
}