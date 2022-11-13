package Controller;

import Database.*;
import Models.Admin;
import Models.Attendance;
import Models.Department;
import Models.Employee;
import cw.payroll.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;

import static Classes.CustomAlert.callAlert;

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

    private void showEmployeeAttendanceTable() {
        Employee emp = sqlEmployee.getEmployee(employee);
        Department dept = sqlDepartment.getDepartment(new Department(emp.getDepartment()));
        employee_panel_text_empid.setText(Integer.toString(emp.getEmployee_ID()));
        employee_panel_text_name.setText(emp.getFirst_Name() + " " + emp.getLast_Name());
        employee_panel_text_department.setText(dept.getDepartment_Name());
        login_pane_employee_panel.toFront();
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
            login_pane_fingerprint_place.toFront();

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
            boolean valid = sqlAdmin.checkIfValidAdmin(adm);
            if (valid) {
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
//            stage.setResizable(false);
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
}
