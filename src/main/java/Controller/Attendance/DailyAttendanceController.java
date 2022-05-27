package Controller.Attendance;

import Database.SQLAttendance;
import Database.SQLDepartment;
import Models.Admin;
import Models.Attendance;
import Models.Department;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;

public class DailyAttendanceController {

    @FXML
    private TableColumn<Attendance, Void> main_dailyattendance_column_action;

    @FXML
    private TableColumn<Attendance, Date> main_dailyattendance_column_date;

    @FXML
    private TableColumn<Attendance, String> main_dailyattendance_column_department;

    @FXML
    private TableColumn<Attendance, Integer> main_dailyattendance_column_empid;

    @FXML
    private TableColumn<Attendance, String> main_dailyattendance_column_fullname;

    @FXML
    private TableColumn<Attendance, String> main_dailyattendance_column_position;

    @FXML
    private TableColumn<Attendance, Time> main_dailyattendance_column_timein;

    @FXML
    private TableColumn<Attendance, Time> main_dailyattendance_column_timeout;

    @FXML
    private DatePicker main_dailyattendance_datepicker;

    @FXML
    private Button main_dailyattendance_nextdate;

    @FXML
    private Button main_dailyattendance_previousdate;

    @FXML
    private TableView main_dailyattendance_tableview;

    @FXML
    private TextField dailyattendance_search;

    @FXML
    private void moveAttendanceDate(ActionEvent event) {
        if (event.getSource() == main_dailyattendance_nextdate) {
            main_dailyattendance_datepicker.setValue(main_dailyattendance_datepicker.getValue().plusDays(1));
        } else if (event.getSource() == main_dailyattendance_previousdate) {
            main_dailyattendance_datepicker.setValue(main_dailyattendance_datepicker.getValue().minusDays(1));
        }
    }

    @FXML
    private void search(ActionEvent event) {

    }

    @FXML
    private void initialize() {
        main_dailyattendance_datepicker.setValue(LocalDate.now());
        main_dailyattendance_datepicker.valueProperty().addListener((o, ol, nw) -> {
            showDailyAttendanceTable();
        });
    }

    /****************************** FXML ENDS HERE ******************************/

    private Admin admin;
    private AnchorPane container;

    private ObservableList<Attendance> attendanceList = FXCollections.observableArrayList();

    private SQLAttendance sqlAttendance = new SQLAttendance();

    public void setRetrievedData(Admin admin, AnchorPane anchorPane) {
        this.admin = admin;
        this.container = anchorPane;
    }

    private void showDailyAttendanceTable() {
        attendanceList.clear();
        attendanceList = sqlAttendance.getDailyAttendance(Date.valueOf(main_dailyattendance_datepicker.getValue()),
                Date.valueOf(main_dailyattendance_datepicker.getValue().plusDays(1)));

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
}
