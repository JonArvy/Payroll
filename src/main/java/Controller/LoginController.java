package Controller;

import Classes.Converters;
import Controller.QRCodeScanner.QRScannerController;
import Database.*;
import Models.*;
import cw.payroll.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.util.Comparator;

import static Classes.CustomAlert.callAlert;
import static Classes.IPCamera.checkIfIPCameraIsOnline;

public class LoginController {

    @FXML
    private Button employee_panel_button_clear;

    @FXML
    private Button employee_panel_button_total;

    @FXML
    private Button employee_panel_button_view;

    @FXML
    private TableColumn<Attendance, Date> employee_panel_column_date;

    @FXML
    private TableColumn<Attendance, Time> employee_panel_column_timein;

    @FXML
    private TableColumn<Attendance, Time> employee_panel_column_timeout;

    @FXML
    private TableColumn<Attendance, Integer> employee_panel_column_timeout1;

    @FXML
    private DatePicker employee_panel_datepicker_datefrom;

    @FXML
    private DatePicker employee_panel_datepicker_dateto;

    @FXML
    private TableView employee_panel_tableview;

    @FXML
    private Label employee_panel_text_department;

    @FXML
    private Label employee_panel_text_empid;

    @FXML
    private TextField employee_panel_text_hours;

    @FXML
    private Label employee_panel_text_name;

    @FXML
    private Pane login_pane_employee_panel;

    @FXML
    private Pane login_pane_fingerprint_place;

    @FXML
    private Pane login_pane_fingerprint_re_place;

    @FXML
    private Pane login_pane_login_admin;

    @FXML
    private TextField login_pane_login_admin_empid;

    @FXML
    private Button login_pane_login_admin_login;

    @FXML
    private PasswordField login_pane_login_admin_password;

    @FXML
    private Pane login_pane_login_start;

    @FXML
    private Button login_pane_login_start_button_admin;

    @FXML
    private Button login_pane_login_start_button_attendance;

    @FXML
    private Button login_pane_login_start_button_employee;

    @FXML
    private Button login_pane_fingerprint_re_place_back;

    @FXML
    private Button login_pane_fingerprint_place_back;

    @FXML
    private Button login_pane_login_admin_back;

    @FXML
    private TextField login_pane_fingerprint_place_empid;

    @FXML
    private TextField login_pane_fingerprint_place_empid1;

    @FXML
    private Button login_pane_fingerprint_place_proceed;

    @FXML
    private Button login_pane_fingerprint_place_proceed1;

    @FXML
    private Pane login_pane_fingerprint_place1;

    @FXML
    private Button login_pane_fingerprint_place_back1;

    @FXML
    private TextField login_emp_id;

    @FXML
    private ComboBox<SummarySchema> payslip_combo;

    @FXML
    private void proceed(ActionEvent event) {
        if (event.getSource() == login_pane_fingerprint_place_proceed) {
            try {
                boolean exist = sqlEmployee.checkIfEmployeeIDExists(Integer.parseInt(login_pane_fingerprint_place_empid.getText()));
                if (!exist) {
                    employee = new Employee(Integer.parseInt(login_pane_fingerprint_place_empid.getText()));
                    showEmployeeAttendanceTable();
                } else {
                    login_pane_fingerprint_re_place.toFront();
                }
            } catch (NumberFormatException e) {
                callAlert("Invalid Employee ID", 3);
            }
        } else if (event.getSource() == login_pane_fingerprint_place_proceed1) {
            try {
                boolean exist = sqlEmployee.checkIfEmployeeIDExists(Integer.parseInt(login_pane_fingerprint_place_empid1.getText()));
                if (!exist) {
                    employee = new Employee(Integer.parseInt(login_pane_fingerprint_place_empid1.getText()));
                    showEmployeeAttendanceTable();
                } else {
                    login_pane_fingerprint_place_empid1.setText("");
                }
            } catch (NumberFormatException e) {
                callAlert("Invalid Employee ID", 3);
            }
        }
    }

    @FXML
    private void getTotalHours() {
        int totalHours = 0;
        for (Attendance attendance : attendanceList) {
            totalHours += attendance.getEmployee_TotalHours();
        }

        employee_panel_text_hours.setText(String.valueOf(totalHours));
    }


    private SQLExecution sql = new SQLExecution();
    private SQLAdmin sqlAdmin = new SQLAdmin();
    private SQLAttendance sqlAttendance = new SQLAttendance();
    private SQLBonus sqlBonus = new SQLBonus();
    private SQLDepartment sqlDepartment = new SQLDepartment();
    private SQLEmployee sqlEmployee = new SQLEmployee();
    private SQLHoliday sqlHoliday = new SQLHoliday();
    private SQLNoticeboard sqlNoticeboard = new SQLNoticeboard();
    private SQLShift sqlShift = new SQLShift();

    private Employee employee = new Employee();

    private ObservableList<Attendance> attendanceList = FXCollections.observableArrayList();

    @FXML
    void clickNavigate(ActionEvent event) {
        if (event.getSource() == login_pane_login_start_button_attendance) {
            login_pane_fingerprint_place1.toFront();

        } else if (event.getSource() == login_pane_login_start_button_employee) {
//            login_pane_fingerprint_place.toFront();
            //Dito
            if (checkIfIPCameraIsOnline()) {
                callQRCodePanel();
            }

        } else if (event.getSource() == login_pane_login_start_button_admin) {
            login_pane_login_admin.toFront();

        } else if (event.getSource() == login_pane_fingerprint_place_back ||
                event.getSource() == login_pane_fingerprint_place_back1 ||
                event.getSource() == login_pane_fingerprint_re_place_back ||
                event.getSource() == login_pane_login_admin_back) {
            login_pane_login_start.toFront();
        } else if (event.getSource() == employee_panel_button_clear) {
            attendanceList.clear();
            login_pane_login_start.toFront();
            employee_panel_text_hours.setText("");

            //dito
        }
    }

    @FXML
    void logIn(ActionEvent event) {
        try {
            int id = Integer.parseInt(login_pane_login_admin_empid.getText());
            String pass = login_pane_login_admin_password.getText();
            Admin adm = new Admin(id, pass);
            boolean valid = sqlAdmin.checkIfActiveValidAdmin(adm);
            if (valid) {
                sqlAdmin.setAdminIsUsingTheSystem(sqlAdmin.getAdmin(adm));
                logInAsAdmin(sqlAdmin.getAdmin(adm));
            } else {
                callAlert("Invalid Username or Password", 3);
            }
        } catch (NumberFormatException e) {
            callAlert("Invalid Username or Password", 3);
        }

    }

    private void logInAsAdmin(Admin admin) {
        Parent root;
        AdminController adminController;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UI/Admin.fxml"));
            root = fxmlLoader.load();
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(Main.class.getResource("/cw/payroll/css/Style.css").toExternalForm());
            stage.setScene(scene);
//            stage.initStyle(StageStyle.UNDECORATED);
            stage.setResizable(false);
            stage.setTitle("Admin Panel");
            stage.getIcons().add(new Image(Main.class.getResourceAsStream("/cw/payroll/Assets/CWBH Logo.png")));
            stage.show();

            adminController = fxmlLoader.getController();
            adminController.setAdmin(admin);

            Stage currentstage = (Stage) login_pane_login_admin_empid.getScene().getWindow();
            currentstage.close();
        } catch (IOException e) {

        }
    }

    @FXML
    void view(ActionEvent event) {
        try {
            employee_panel_datepicker_datefrom.getConverter().fromString(employee_panel_datepicker_datefrom.getEditor().getText());
            employee_panel_datepicker_dateto.getConverter().fromString(employee_panel_datepicker_dateto.getEditor().getText());

            Date datefrom = Date.valueOf(employee_panel_datepicker_datefrom.getValue());
            Date dateto = Date.valueOf(employee_panel_datepicker_dateto.getValue());

            if (datefrom.before(dateto)) {
                showData(datefrom, dateto);
            } else {
                callAlert("Invalid Date Values", 4);
            }

        } catch (Exception e) {
            callAlert("Invalid Date Values", 4);
        }
    }

    private void showData(Date datefrom, Date dateto) {
        attendanceList.clear();
        attendanceList = sqlAttendance.getAttendance(employee.getEmployee_ID(), datefrom, dateto);

        employee_panel_column_date.setCellValueFactory(new PropertyValueFactory<Attendance, Date>("Employee_Attendance_Date"));
        employee_panel_column_timein.setCellValueFactory(new PropertyValueFactory<Attendance, Time>("Employee_TimeIn"));
        employee_panel_column_timeout.setCellValueFactory(new PropertyValueFactory<Attendance, Time>("Employee_TimeOut"));
        employee_panel_column_timeout1.setCellValueFactory(new PropertyValueFactory<Attendance, Integer>("Employee_TotalHours"));

        employee_panel_tableview.setItems(attendanceList);
    }

    @FXML
    private void initialize() {
        SQLAdmin sqlAdmin = new SQLAdmin();
        sqlAdmin.setAdminIsNotUsingTheSystem();

        employee_panel_datepicker_datefrom.setValue(LocalDate.now().minusMonths(1));
        employee_panel_datepicker_dateto.setValue(LocalDate.now());
    }

    @FXML
    private void loginAttendancce() {
        try {
            int id = Integer.parseInt(login_emp_id.getText());
            if (!sqlEmployee.checkIfEmployeeIDExists(id)) {

                callAlert("Successfully Logged in", 2);
            } else {
                callAlert("Employee does not exist", 4);
                login_emp_id.setText("");
            }
        } catch (NumberFormatException e) {
            callAlert("Invalid Employee ID", 1);
        }
    }

    private void callQRCodePanel() {
        System.out.println("Call QRCode Scanner");
        Parent root;
        QRScannerController qrScannerController;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UI/QRCodeScanner/QRScanner.fxml"));
            root = fxmlLoader.load();
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(Main.class.getResource("/cw/payroll/css/Style.css").toExternalForm());
            stage.setScene(scene);

            stage.show();

            qrScannerController = fxmlLoader.getController();
            qrScannerController.setLoginController(this);
            qrScannerController.getCalled();
        } catch (IOException e) {

        }
    }

    public void TimeInAsEmployee(Employee employee) {

    }

    public void loginAsEmployee(Employee employee) {
        this.employee = employee;
        showEmployeeAttendanceTable();
    }

    private void showEmployeeAttendanceTable() {
        Employee emp = sqlEmployee.getEmployee(employee);
        Department dept = sqlDepartment.getDepartment(new Department(emp.getDepartment()));

        ObservableList<SummarySchema> summarySchemaList = FXCollections.observableArrayList();
        Converters converters = new Converters();
        SQLPayrollSummary sqlPayrollSummary = new SQLPayrollSummary();

        summarySchemaList.clear();

        summarySchemaList = sqlPayrollSummary.getSchemaSummaryListForSingleEmployee(emp);

        Comparator<SummarySchema> comparator = Comparator.comparingInt(SummarySchema::getSummary_id);
        comparator = comparator.reversed();
        FXCollections.sort(summarySchemaList, comparator);

        payslip_combo.setItems(summarySchemaList);
        payslip_combo.setConverter(converters.summarySchemaStringConverter(summarySchemaList));
        payslip_combo.getSelectionModel().selectFirst();

        employee_panel_text_empid.setText(Integer.toString(emp.getEmployee_ID()));
        employee_panel_text_name.setText(emp.getFirst_Name() + " " + emp.getLast_Name());
        employee_panel_text_department.setText(dept.getDepartment_Name());
        login_pane_employee_panel.toFront();
        temp_employee = emp;
    }

    private Employee temp_employee;

    @FXML
    private void viewPayslip() {
        int summary_id = payslip_combo.getSelectionModel().getSelectedItem().getSummary_id();
        Employee emp = temp_employee;

        SQLPayrollSummary sqlPayrollSummary = new SQLPayrollSummary();
        Summary sm = sqlPayrollSummary.getSummary(summary_id, emp);

        Parent root;
        PayslipContainerController payslipContainerController;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UI/PayslipContainer.fxml"));
            root = fxmlLoader.load();
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(Main.class.getResource("/cw/payroll/css/Style.css").toExternalForm());
            stage.setScene(scene);
//            stage.initStyle(StageStyle.UNDECORATED);
//            stage.setResizable(false);
            stage.show();

            payslipContainerController = fxmlLoader.getController();
            payslipContainerController.setSummary(sm);
        } catch (IOException e) {

        }
    }
}
