package Classes;

import Models.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import javafx.util.StringConverter;
import javafx.util.converter.NumberStringConverter;

import javax.swing.*;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

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
    private Pane main_credentials_panel;
    @FXML
    private Button main_credentials_add_button;

    @FXML
    private Pane main_credentials_add_panel;

    @FXML
    private Button main_credentials_create_button;

    @FXML
    private Pane main_credentials_confirmation_panel;

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
    private ComboBox<Department> main_addemployee_dept;

    @FXML
    private TextField main_addemployee_empid;

    @FXML
    private ComboBox<String> main_addemployee_empstatus;

    @FXML
    private TextField main_addemployee_ext;

    @FXML
    private TextField main_addemployee_fname;

    @FXML
    private ComboBox<BooleanValue> main_addemployee_gender;

    @FXML
    private TextField main_addemployee_lname;

    @FXML
    private ComboBox<String> main_addemployee_maritalstatus;

    @FXML
    private TextField main_addemployee_mname;

    @FXML
    private TextField main_addemployee_nationality;

    @FXML
    private TextField main_addemployee_number;

    @FXML
    private TextField main_addemployee_position;

    @FXML
    private ComboBox<BooleanValue> main_addemployee_status;

    @FXML
    private Button addemployee_upload_button;

    //              ADD EMPLOYEEEE VARIABLES ENDS HERE

    //              MANAGE EMPLOYEE STARTS HERE
    @FXML
    private TableView main_manageemployee_table_view;

    @FXML
    private TableColumn<Employee, Void> main_manageemployee_col_action;

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

    // Add Department Here

    @FXML
    private Button main_adddepartment_addbutton;

    @FXML
    private TextField main_adddepartment_dayspermonth;

    @FXML
    private TextField main_adddepartment_hoursperday;

    @FXML
    private TextField main_adddepartment_monthlyrate;

    @FXML
    private TextField main_adddepartment_name;

    // Add Holiday Here

    @FXML
    private Button main_addholiday_addbutton;

    @FXML
    private DatePicker main_addholiday_date;

    @FXML
    private TextField main_addholiday_name;

    @FXML
    private ComboBox<String> main_addholiday_type;

    // Add Shift Here

    @FXML
    private Button main_addshift_addbutton;

    @FXML
    private CheckBox main_addshift_friday;

    @FXML
    private CheckBox main_addshift_monday;

    //@FXML
    //private TextField main_addshift_name;

    @FXML
    private CheckBox main_addshift_saturday;

    //@FXML
    //private ComboBox<Integer> main_addshift_shifttype;

    @FXML
    private CheckBox main_addshift_sunday;

    @FXML
    private CheckBox main_addshift_thursday;

    @FXML
    private CheckBox main_addshift_tuesday;

    @FXML
    private CheckBox main_addshift_wednesday;

    // Department Table
    @FXML
    private TableView main_department_tableview;

    @FXML
    private TableColumn<Department, Double> main_department_column_dailyrate;

    @FXML
    private TableColumn<Department, Integer> main_department_column_dayspermonth;

    @FXML
    private TableColumn<Department, Double> main_department_column_hourlyrate;

    @FXML
    private TableColumn<Department, Integer> main_department_column_hoursperday;

    @FXML
    private TableColumn<Department, Double> main_department_column_monthlyrate;

    @FXML
    private TableColumn<Department, String> main_department_column_name;

    @FXML
    private TableColumn<?, ?> main_department_column_shift;

    // Shift Table Here
    @FXML
    private TableView main_shift_tableview;

    @FXML
    private TableColumn<?, ?> main_shift_column_action;

    @FXML
    private TableColumn<Shift, String> main_shift_column_name;

    @FXML
    private TableColumn<Shift, Integer> main_shift_column_shifttype;

    // Bonus
    @FXML
    private TableView main_bonus_tableview;

    @FXML
    private TableColumn<Bonus, String> main_bonus_name;

    @FXML
    private TableColumn<Bonus, Double> main_bonus_amount;

    @FXML
    private TableColumn<Bonus, String> main_bonus_recipient;

    @FXML
    private TableColumn<Bonus, Date> main_bonus_dateapplicable;

    @FXML
    private TableColumn<?, ?> main_bonus_action;

    // Add Bonus
    @FXML
    private TextField main_addbonus_name;

    @FXML
    private TextField main_addbonus_amount;

    @FXML
    private ComboBox<Department> main_addbonus_recipient;

    @FXML
    private DatePicker main_addbonus_dateapplicable;

    @FXML
    private Button main_addbonus_addbutton;

    //Holiday Table
    @FXML
    private TableView main_holiday_tableview;

    @FXML
    private TableColumn<Holiday, Date> main_holiday_column_date;

    @FXML
    private TableColumn<Holiday, String> main_holiday_column_holidayname;

    @FXML
    private TableColumn<Holiday, String> main_holiday_column_type;

    @FXML
    private TableColumn<Holiday, String> main_holiday_column_repeat;

    @FXML
    private TableColumn<?, ?> main_holiday_column_action;
    // END DITOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO
    /* VBOX */
    @FXML
    private VBox main_menu_vbox;
    // Daily Attendance ***************************************************//

    @FXML
    private DatePicker main_dailyattendance_datepicker;

    @FXML
    private TableView main_dailyattendance_tableview;

    @FXML
    private TableColumn<Attendance, Date> main_dailyattendance_column_date;

    @FXML
    private TableColumn<Attendance, Integer> main_dailyattendance_column_empid;

    @FXML
    private TableColumn<Attendance, String> main_dailyattendance_column_fullname;

    @FXML
    private TableColumn<Attendance, String> main_dailyattendance_column_department;

    @FXML
    private TableColumn<Attendance, String> main_dailyattendance_column_position;

    @FXML
    private TableColumn<Attendance, Time> main_dailyattendance_column_timein;

    @FXML
    private TableColumn<Attendance, Time> main_dailyattendance_column_timeout;

    @FXML
    private TableColumn<Attendance, Date> main_dailyattendance_column_action;

    @FXML
    private Button main_dailyattendance_nextdate;

    @FXML
    private Button main_dailyattendance_previousdate;


    // Daily Attendance End ***********************************************//
    // Edit Employee ******************************************************//
    @FXML
    private Pane main_editemployee_panel_1;

    @FXML
    private TextField main_editemployee_fname;

    @FXML
    private TextField main_editemployee_lname;

    @FXML
    private TextArea main_editemployee_address;

    @FXML
    private ComboBox<BooleanValue> main_editemployee_gender;

    @FXML
    private TextField main_editemployee_number;

    @FXML
    private TextField main_editemployee_mname;

    @FXML
    private TextField main_editemployee_ext;

    @FXML
    private DatePicker main_editemployee_bdate;

    @FXML
    private ComboBox<BooleanValue> main_editemployee_status;

    @FXML
    private TextField main_editemployee_empid;

    @FXML
    private TextField main_editemployee_nationality;

    @FXML
    private ComboBox<String> main_editemployee_maritalstatus;

    @FXML
    private ComboBox<Department> main_editemployee_department;

    @FXML
    private TextField main_editemployee_position;

    @FXML
    private ComboBox<String> main_editemployee_empstatus;

    @FXML
    private TextField main_editemployee_contactname;

    @FXML
    private TextField main_editemployee_contactrelationship;

    @FXML
    private TextArea main_editemployee_contactaddress;

    @FXML
    private TextField main_editemployee_contactnumber;

    //************End Edit Employee Here **************//
    //************Employee Payslip Here****************//

    @FXML
    private Pane main_payslip_viewpayslip_panel;

    @FXML
    private TableView main_payslip_tableview;

    @FXML
    private TableColumn<Employee, Integer> main_payslip_column_empid;

    @FXML
    private TableColumn<Employee, String> main_payslip_column_fullname;

    @FXML
    private TableColumn<Employee, String> main_payslip_column_department;

    @FXML
    private TableColumn<Employee, String> main_payslip_column_position;

    @FXML
    private TableColumn<?, ?> main_payslip_column_action;

    //************ Admin ****************//
        @FXML
        private CheckBox main_addshift_friday1;

        @FXML
        private CheckBox main_addshift_friday11;

        @FXML
        private CheckBox main_addshift_monday1;

        @FXML
        private CheckBox main_addshift_monday11;


        @FXML
        private CheckBox main_addshift_saturday1;

        @FXML
        private CheckBox main_addshift_saturday11;


        @FXML
        private CheckBox main_addshift_sunday1;

        @FXML
        private CheckBox main_addshift_sunday11;

        @FXML
        private CheckBox main_addshift_thursday1;

        @FXML
        private CheckBox main_addshift_thursday11;

        @FXML
        private CheckBox main_addshift_tuesday1;

        @FXML
        private CheckBox main_addshift_tuesday11;

        @FXML
        private CheckBox main_addshift_wednesday1;

        @FXML
        private CheckBox main_addshift_wednesday11;


    //************ Admin ****************//


    //************Employee Payslip Here****************//

    @FXML
    private Pane main_credentials_panel_11;


    private SQLExecution sql;

    private ObservableList<BooleanValue> genderList;
    private ObservableList<BooleanValue> statusList;

    private ObservableList<Department> departmentList = FXCollections.observableArrayList();
    private ObservableList<Employee> employeeList = FXCollections.observableArrayList();
    private ObservableList<Bonus> bonusList = FXCollections.observableArrayList();
    private ObservableList<Holiday> holidayList = FXCollections.observableArrayList();
    private ObservableList<Shift> shiftList = FXCollections.observableArrayList();
    private ObservableList<Attendance> attendanceList = FXCollections.observableArrayList();




    @FXML
    public void clickNavigation_Choices(ActionEvent event) {
        System.out.println(event.getSource().toString());

        //Add Employee Button
        if (event.getSource() == main_addemployee_button) {
            main_addemployee_panel_1.toFront();
            populateDepartmentBox();

            //Manage Employee Button
        } else if (event.getSource() == main_manageemployee_button) {
            main_manageemployee_panel_1.toFront();
            showEmployeeTable();

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
            main_dailyattendance_datepicker.setValue(LocalDate.now());

        //Attendance Report Button
        } else if (event.getSource() == main_attendancereport_button) {
            main_attendancereport_panel_1.toFront();

        //Payslip Button
        } else if (event.getSource() == main_payslip_button) {
            main_payslip_panel_1.toFront();
            showPayslipList();

        //Payroll Summary Button
        } else if (event.getSource() == main_payrollsummary_button) {
            main_payrollsummary_panel_1.toFront();

        //Department Button page 1
        } else if (event.getSource() == main_department_button) {
            main_department_panel_1.toFront();
            showDepartmentTable();

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
            showShift();

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
            showHolidayList();

        //Holiday Button - Add Holiday
        } else if (event.getSource() == main_holiday_add_holiday_button) {
            main_holiday_add_holiday_panel_1.toFront();

        //Holiday Button
        } else if (event.getSource() == main_holiday_add_holiday_cancel_button) {
            main_holiday_panel_1.toFront();

        //Bonus Button
        } else if (event.getSource() == main_bonus_button) {
            main_bonus_panel_1.toFront();
            showBonus();

        //Bonus Button - Add Bonus
        } else if (event.getSource() == main_bonus_add_bonus_button) {
            main_bonus_add_bonus_panel_1.toFront();
            populateAddBonusBox();

        //Bonus Button - Add Bonus - Cancel button
        } else if (event.getSource() == main_bonus_add_bonus_cancel_button) {
            main_bonus_panel_1.toFront();

        //Credentials Button
        } else if (event.getSource() == main_credentials_button) {
            main_credentials_panel.toFront();

        // Add or Create Admin Credentials
        } else if (event.getSource() == main_credentials_add_button) {
            main_credentials_add_panel.toFront();

        // Asking for credentials for Creating New Credentials / Register
        } else if (event.getSource() == main_credentials_create_button) {
            main_credentials_confirmation_panel.toFront();

        //Logout Button
        } else if (event.getSource() == main_logout_button) {
            System.exit(0);


            //Add Bonus
        } else if (event.getSource() == main_addbonus_addbutton) {
            addBonus();

            //Add Shift
        } else if (event.getSource() == main_addshift_addbutton) {
            addShift();
        }
    }


    @FXML
    public void addEmployee(ActionEvent event) {
//        boolean gender = true;
//        boolean status = true;
//        if (main_addemployee_gender.getValue() == "Male") {
//            gender = true;
//        } else {
//            gender = false;
//        }
//        if (main_addemployee_status.getValue() == "Active") {
//            status = true;
//        } else {
//            status = false;
//        }
        sql.addEmployee(new Employee(
                Integer.parseInt(main_addemployee_empid.getText()),
                main_addemployee_nationality.getText(),
                main_addemployee_maritalstatus.getValue(),
                main_addemployee_dept.getValue().getDepartment_ID(), // Will fix later
                main_addemployee_position.getText(),
                main_addemployee_empstatus.getValue(),
                main_addemployee_fname.getText(),
                main_addemployee_lname.getText(),
                main_addemployee_mname.getText(),
                main_addemployee_ext.getText(),
                main_addemployee_address.getText(),
                main_addemployee_gender.getValue().isBool(), // Boolean
                main_addemployee_number.getText(),
                Date.valueOf(main_addemployee_bdate.getValue()),
                main_addemployee_status.getValue().isBool(), // Boolean
                main_addemployee_contactname.getText(),
                main_addemployee_contactrelationship.getText(),
                main_addemployee_contactaddress.getText(),
                main_addemployee_contactnumber.getText(),
                "Biometrics"
        ));
    }

    @FXML
    public void addHoliday(ActionEvent event) {
        sql.addHoliday(new Holiday(main_addholiday_name.getText(),
                Date.valueOf(main_addholiday_date.getValue()),
                main_addholiday_type.getValue(),
                "Yearly"));
    }

    public void populateDepartmentBox() {
        departmentList.clear();
        departmentList = sql.getDepartment();

        main_addemployee_dept.setItems(departmentList);

        main_addemployee_dept.setConverter(departmentConverter());
        main_addemployee_dept.getSelectionModel().select(0);
    }

    public void populateAddBonusBox() {
        departmentList.clear();
        departmentList = sql.getDepartment();

        main_addbonus_recipient.setItems(departmentList);

        main_addbonus_recipient.setConverter(departmentConverter());
        main_addbonus_recipient.getSelectionModel().select(0);
    }

    public void showHolidayList() {
        holidayList.clear();
        holidayList = sql.getHolidays();

        main_holiday_column_holidayname.setCellValueFactory(new PropertyValueFactory<Holiday, String>("Holiday_Name"));
        main_holiday_column_date.setCellValueFactory(new PropertyValueFactory<Holiday, Date>("Holiday_Date"));
        main_holiday_column_type.setCellValueFactory(new PropertyValueFactory<Holiday, String>("Holiday_Type"));
        main_holiday_column_repeat.setCellValueFactory(new PropertyValueFactory<Holiday, String>("Holiday_Repeat"));
//        main_bonus_action.setCellValueFactory(new PropertyValueFactory<Bonus, Integer>("Department_HoursPerDay"));


        main_holiday_tableview.setItems(holidayList);
    }

    public void showPayslipList() {
        employeeList.clear();
        employeeList = sql.getAllEmployeesInformation();

        main_payslip_column_empid.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("Employee_ID"));
        main_payslip_column_fullname.setCellValueFactory(new PropertyValueFactory<Employee, String>("Last_Name"));
        main_payslip_column_department.setCellValueFactory(new PropertyValueFactory<Employee, String>("Department_Name"));
        main_payslip_column_position.setCellValueFactory(new PropertyValueFactory<Employee, String>("Position"));
//        main_payslip_column_action.setCellValueFactory(new PropertyValueFactory<Employee, String>("Department_Name"));

        main_payslip_tableview.setItems(employeeList);

    }

    public void showBonus() {
        bonusList.clear();
        bonusList = sql.getBonus();

        main_bonus_name.setCellValueFactory(new PropertyValueFactory<Bonus, String>("Bonus_Name"));
        main_bonus_amount.setCellValueFactory(new PropertyValueFactory<Bonus, Double>("Bonus_Amount"));
        main_bonus_recipient.setCellValueFactory(new PropertyValueFactory<Bonus, String>("Recipient_Name"));
        main_bonus_dateapplicable.setCellValueFactory(new PropertyValueFactory<Bonus, Date>("Bonus_Date"));
//        main_bonus_action.setCellValueFactory(new PropertyValueFactory<Bonus, Integer>("Department_HoursPerDay"));


        main_bonus_tableview.setItems(bonusList);
    }

    public void showShift() {
        shiftList.clear();
        shiftList = sql.getShift();

        main_shift_column_shifttype.setCellValueFactory(new PropertyValueFactory<Shift, Integer>("Shift_Type"));
        main_shift_column_name.setCellValueFactory(new PropertyValueFactory<Shift, String>("Recipient_Name"));
//        main_shift_column_action.setCellValueFactory(new PropertyValueFactory<Bonus, String>("Recipient_Name"));
//        main_bonus_dateapplicable.setCellValueFactory(new PropertyValueFactory<Bonus, Date>("Bonus_Date"));
//        main_bonus_action.setCellValueFactory(new PropertyValueFactory<Bonus, Integer>("Department_HoursPerDay"));


        main_shift_tableview.setItems(shiftList);
    }

    public void showDepartmentTable() {
        departmentList.clear();
        departmentList = sql.getDepartment();
        main_department_column_name.setCellValueFactory(new PropertyValueFactory<Department, String>("Department_Name"));

        main_department_column_monthlyrate.setCellValueFactory(new PropertyValueFactory<Department, Double>("Department_MonthlyRate"));
        main_department_column_dayspermonth.setCellValueFactory(new PropertyValueFactory<Department, Integer>("Department_DaysPerMonth"));
        main_department_column_dailyrate.setCellValueFactory(new PropertyValueFactory<Department, Double>("Daily_Rate"));
        main_department_column_hoursperday.setCellValueFactory(new PropertyValueFactory<Department, Integer>("Department_HoursPerDay"));
        main_department_column_hourlyrate.setCellValueFactory(new PropertyValueFactory<Department, Double>("Hourly_Rate"));


        main_department_tableview.setItems(departmentList);
    }

    public void showEmployeeTable() {
        employeeList.clear();
        employeeList = sql.getAllEmployees();

        main_manageemployee_col_empid.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("Employee_ID"));
        main_manageemployee_col_lname.setCellValueFactory(new PropertyValueFactory<Employee, String>("Last_Name"));
        main_manageemployee_col_fname.setCellValueFactory(new PropertyValueFactory<Employee, String>("First_Name"));
        main_manageemployee_col_empstatus.setCellValueFactory(new PropertyValueFactory<Employee, String>("Employment_Status"));
        main_manageemployee_col_dept.setCellValueFactory(new PropertyValueFactory<Employee, String>("Department_Name"));
        main_manageemployee_col_status.setCellValueFactory(new PropertyValueFactory<Employee, Boolean>("Active"));

//        main_manageemployee_col_action.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("Employee_ID"));

        main_manageemployee_table_view.setItems(employeeList);

        Callback<TableColumn<Employee, Void>, TableCell<Employee, Void>> cellFactory = new Callback<TableColumn<Employee, Void>, TableCell<Employee, Void>>() {
            @Override
            public TableCell<Employee, Void> call(final TableColumn<Employee, Void> param) {
                final TableCell<Employee, Void> cell = new TableCell<Employee, Void>() {
                    private final Button btn = new Button("View");
                    private final Button btn2 = new Button("Edit");


                    {
                        btn.setStyle("-fx-background-color: #c3c4c4, linear-gradient(#d6d6d6 50%, white 100%)," +
                                "radial-gradient(center 50% -40%, radius 200%, #e6e6e6 45%, rgba(230,230,230,0) 50%); " +
                                "-fx-background-radius: 30; " +
                                "-fx-background-insets: 0,1,1; " +
                                "-fx-text-fill: black; " +
                                "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.6), 3, 0.0, 0, 1);");
                        btn.setDisable(true);
                        btn.setOnAction((ActionEvent event) -> {
                            Employee emp = getTableView().getItems().get(getIndex());
                            System.out.println("Edit" + emp.getFirst_Name() + " " + emp.getLast_Name());
                        });

                        btn2.setStyle("-fx-background-color: #c3c4c4, linear-gradient(#d6d6d6 50%, white 100%)," +
                                "radial-gradient(center 50% -40%, radius 200%, #e6e6e6 45%, rgba(230,230,230,0) 50%); " +
                                "-fx-background-radius: 30; " +
                                "-fx-background-insets: 0,1,1; " +
                                "-fx-text-fill: black; " +
                                "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.6), 3, 0.0, 0, 1);");

                        btn2.setOnAction((ActionEvent event) -> {
                            Employee emp = getTableView().getItems().get(getIndex());
//                            System.out.println("Delete" + emp.getFirst_Name() + " " + emp.getLast_Name());
                            main_editemployee_panel_1.toFront();
                            loadEmployeePanel(emp);
                        });
                    }
                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            HBox allbtn = new HBox(btn, btn2);
                            setGraphic(allbtn);
                        }
                    }
                };
                return cell;
            }
        };

        main_manageemployee_col_action.setCellFactory(cellFactory);
    }

    public void loadEmployeePanel(Employee emp) {
        initializeComboboxOnEditEmployee();
        Employee employee = sql.getEmployee(new Employee(emp.getEmployee_ID()));
        main_editemployee_empid.setText(employee.getEmployee_ID() + "");
        main_editemployee_nationality.setText(employee.getNationality());
        main_editemployee_maritalstatus.setValue(employee.getMarital_Status());
        main_editemployee_department.getSelectionModel().select(employee.getDepartment() - 1); //Will Fix Later
        main_editemployee_position.setText(employee.getPosition());
        main_editemployee_number.setText(employee.getNumber());
        main_editemployee_empstatus.setValue(employee.getEmployment_Status());
        main_editemployee_fname.setText(employee.getFirst_Name());
        main_editemployee_lname.setText(employee.getLast_Name());
        main_editemployee_mname.setText(employee.getMiddle_Name());
        main_editemployee_ext.setText(employee.getExtension());
        main_editemployee_address.setText(employee.getAddress());
        main_editemployee_gender.getSelectionModel().select(employee.isGender() ? 0 : 1);  //Will Fix Later
        main_editemployee_number.setText(employee.getNumber());
        main_editemployee_bdate.setValue(employee.getBirthdate().toLocalDate());
        main_editemployee_status.getSelectionModel().select(employee.isActive() ? 0 : 1);  //Will Fix Later
        main_editemployee_contactname.setText(employee.getContact_Name());
        main_editemployee_contactrelationship.setText(employee.getContact_Relationship());
        main_editemployee_contactaddress.setText(employee.getContact_Address());
        main_editemployee_contactnumber.setText(employee.getContact_Number());
    }

    public void initializeComboboxOnEditEmployee() {
        //Edit Employee Initialize constants

        departmentList.clear();
        departmentList = sql.getDepartment();

        main_editemployee_department.setItems(departmentList);

        main_editemployee_department.setConverter(departmentConverter());

        main_editemployee_gender.setItems(genderList);
        main_editemployee_status.setItems(statusList);

        main_editemployee_gender.setConverter(booleanValueConverter(genderList));
        main_editemployee_status.setConverter(booleanValueConverter(statusList));
    }

    @FXML
    public void saveEmployee(ActionEvent event) {
        sql.updateEmployee(
        new Employee(
                Integer.parseInt(main_editemployee_empid.getText()),
                main_editemployee_nationality.getText(),
                main_editemployee_maritalstatus.getValue(),
                main_editemployee_department.getValue().getDepartment_ID(),
                main_editemployee_position.getText(),
                main_editemployee_empstatus.getValue(),
                main_editemployee_fname.getText(),
                main_editemployee_lname.getText(),
                main_editemployee_mname.getText(),
                main_editemployee_ext.getText(),
                main_editemployee_address.getText(),
                main_editemployee_gender.getValue().isBool(),
                main_editemployee_number.getText(),
                Date.valueOf(main_editemployee_bdate.getValue()),
                main_editemployee_status.getValue().isBool(),
                main_editemployee_contactname.getText(),
                main_editemployee_contactrelationship.getText(),
                main_editemployee_contactaddress.getText(),
                main_editemployee_contactnumber.getText()
        ));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        main_editemployee_empid.textProperty().addListener(new ChangeListener<String>() {
//            @Override
//            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
//                if (!newValue.matches("\\d*")) {
//                    main_editemployee_empid.setText(newValue.replaceAll("[^\\d]", ""));
//                }
//            }
//        });
        //Initialize Variables
        sql = new SQLExecution();

        genderList = FXCollections.observableArrayList(
                new BooleanValue("Male", true),
                new BooleanValue("Female", false));
        statusList = FXCollections.observableArrayList(
                new BooleanValue("Active", true),
                new BooleanValue("Inactive", false));


        //Add Employee Initialize constants
        main_addemployee_gender.setItems(genderList);
        main_addemployee_status.setItems(statusList);

        main_addemployee_gender.setConverter(booleanValueConverter(genderList));
        main_addemployee_status.setConverter(booleanValueConverter(statusList));

        main_addemployee_gender.getSelectionModel().select(0);
        main_addemployee_status.getSelectionModel().select(0);

        //main_addshift_shifttype.getItems().addAll(1, 2);
        //main_addshift_shifttype.getSelectionModel().select(0);

        main_addemployee_maritalstatus.getItems().addAll("Single", "Married", "Widowed", "Annuled");
        main_addemployee_maritalstatus.getSelectionModel().select(0);

        main_addemployee_empstatus.getItems().addAll("Regular", "Contractual", "Part-Time");
        main_addemployee_empstatus.getSelectionModel().select(0);

        main_addholiday_type.getItems().addAll( "Regular Holiday", "Special Working Public Holiday", "Special Non-working Holiday", "Common local holiday", "Season", "Observance");
        main_addholiday_type.getSelectionModel().select(0);

        actionListeners();
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

    public void actionListeners() {
        main_dailyattendance_datepicker.valueProperty().addListener((o, ol, nw) -> {
            showDailyAttendanceTable();
        });
    }

    @FXML
    public void moveAttendanceDate(ActionEvent event) {
        if (event.getSource() == main_dailyattendance_nextdate) {
            main_dailyattendance_datepicker.setValue(main_dailyattendance_datepicker.getValue().plusDays(1));
        } else if (event.getSource() == main_dailyattendance_previousdate) {
            main_dailyattendance_datepicker.setValue(main_dailyattendance_datepicker.getValue().minusDays(1));
        }
    }


    public void showDailyAttendanceTable() {
        System.out.println(main_dailyattendance_datepicker.getValue());
        attendanceList.clear();
        attendanceList = sql.getDailyAttendance(Date.valueOf(main_dailyattendance_datepicker.getValue()),
                Date.valueOf(main_dailyattendance_datepicker.getValue().plusDays(1)));
        System.out.println(attendanceList.size() + " Size");

        main_dailyattendance_column_date.setCellValueFactory(new PropertyValueFactory<Attendance, Date>("Employee_Attendance_Date"));
        main_dailyattendance_column_empid.setCellValueFactory(new PropertyValueFactory<Attendance, Integer>("Employee_ID"));
        main_dailyattendance_column_fullname.setCellValueFactory(new PropertyValueFactory<Attendance, String>("Employee_FullName"));
        main_dailyattendance_column_department.setCellValueFactory(new PropertyValueFactory<Attendance, String>("Department_Name"));
        main_dailyattendance_column_position.setCellValueFactory(new PropertyValueFactory<Attendance, String>("Employee_Position"));
        main_dailyattendance_column_timein.setCellValueFactory(new PropertyValueFactory<Attendance, Time>("Employee_TimeIn"));
        main_dailyattendance_column_timeout.setCellValueFactory(new PropertyValueFactory<Attendance, Time>("Employee_TimeOut"));
//        main_dailyattendance_column_action.setCellValueFactory(new PropertyValueFactory<Department, Double>("Hourly_Rate"));


        main_dailyattendance_tableview.setItems(attendanceList);


    }


    public void addDepartment() throws ParseException {
        int dayspermonth = Integer.parseInt(main_adddepartment_dayspermonth.getText());
        int hoursperday = Integer.parseInt(main_adddepartment_hoursperday.getText());
        double monthlyrate = Double.parseDouble(main_adddepartment_monthlyrate.getText());
        String name = main_adddepartment_name.getText();

        int id = 0;

        sql.addDepartment(new Department(id, name, monthlyrate, dayspermonth, hoursperday));
    }


    public void addBonus() {
        String bonusName = main_addbonus_name.getText();
        double bonusAmount = Double.parseDouble(main_addbonus_amount.getText());
        int bonusRecipient = main_addbonus_recipient.getValue().getDepartment_ID();
        Date bonusDate = Date.valueOf(main_addbonus_dateapplicable.getValue());

        int id = 0;


        sql.addBonus(new Bonus(id, bonusName, bonusAmount, bonusRecipient, bonusDate));
    }

    public void addShift() {
        int id = 0;
        //int shiftType = (int) main_addshift_shifttype.getValue();
        //int recipient = Integer.parseInt(main_addshift_name.getText());
        boolean shiftSunday = main_addshift_sunday.isSelected();
        boolean shiftMonday = main_addshift_monday.isSelected();
        boolean shiftTuesday = main_addshift_tuesday.isSelected();
        boolean shiftWednesday = main_addshift_wednesday.isSelected();
        boolean shiftThursday = main_addshift_thursday.isSelected();
        boolean shiftFriday = main_addshift_friday.isSelected();
        boolean shiftSaturday = main_addshift_saturday.isSelected();

        /*sql.addShift(new Shift(
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

         */
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