package Classes;

import Database.*;
import Models.Attendance;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

import java.sql.Date;
import java.sql.Time;

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

    private SQLExecution sql = new SQLExecution();
    private SQLAdmin sqlAdmin = new SQLAdmin();
    private SQLAttendance sqlAttendance = new SQLAttendance();
    private SQLBonus sqlBonus = new SQLBonus();
    private SQLDepartment sqlDepartment = new SQLDepartment();
    private SQLEmployee sqlEmployee = new SQLEmployee();
    private SQLHoliday sqlHoliday = new SQLHoliday();
    private SQLNoticeboard sqlNoticeboard = new SQLNoticeboard();
    private SQLShift sqlShift = new SQLShift();

    private ObservableList<Attendance> attendanceList = FXCollections.observableArrayList();

    @FXML
    void clickNavigate(ActionEvent event) {
        if (event.getSource() == login_pane_login_start_button_attendance) {
            login_pane_fingerprint_place.toFront();

        } else if (event.getSource() == login_pane_login_start_button_employee) {
            login_pane_fingerprint_place.toFront();

        } else if (event.getSource() == login_pane_login_start_button_admin) {
            login_pane_login_admin.toFront();

        } else if (event.getSource() == login_pane_fingerprint_place_back) {
            login_pane_login_start.toFront();

        } else if (event.getSource() == login_pane_fingerprint_re_place_back) {
            login_pane_login_start.toFront();

        } else if (event.getSource() == login_pane_login_admin_back) {
            login_pane_login_start.toFront();
        }
    }

    @FXML
    void logIn(ActionEvent event) {

    }

    @FXML
    void view(ActionEvent event) {
        attendanceList.clear();
        attendanceList = sqlAttendance.getAttendance(Integer.parseInt(employee_panel_text_empid.getText()));

        employee_panel_column_date.setCellValueFactory(new PropertyValueFactory<Attendance, Date>("Employee_Attendance_Date"));
        employee_panel_column_timein.setCellValueFactory(new PropertyValueFactory<Attendance, Time>("Employee_TimeIn"));
        employee_panel_column_timeout.setCellValueFactory(new PropertyValueFactory<Attendance, Time>("Employee_TimeOut"));

        employee_panel_tableview.setItems(attendanceList);
    }

}
