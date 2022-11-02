package Controller.Attendance;

import Database.SQLAttendance;
import Models.Admin;
import Models.AttendanceReport;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.sql.Date;
import java.time.LocalDate;

import static Classes.CustomAlert.callAlert;

public class AttendanceReportController {

    @FXML
    private TableColumn<AttendanceReport, Integer> main_attendancereport_column_absent;

    @FXML
    private TableColumn<AttendanceReport, String> main_attendancereport_column_department;

    @FXML
    private TableColumn<AttendanceReport, Integer> main_attendancereport_column_empid;

    @FXML
    private TableColumn<AttendanceReport, String> main_attendancereport_column_fullname;

    @FXML
    private TableColumn<AttendanceReport, Integer> main_attendancereport_column_holiday;

    @FXML
    private TableColumn<AttendanceReport, Integer> main_attendancereport_column_late;

    @FXML
    private TableColumn<AttendanceReport, Integer> main_attendancereport_column_number;

    @FXML
    private TableColumn<AttendanceReport, String> main_attendancereport_column_position;

    @FXML
    private TableColumn<AttendanceReport, Integer> main_attendancereport_column_present;

    @FXML
    private TableView main_attendancereport_tableview;

    @FXML
    private DatePicker attendancereport_from;

    @FXML
    private DatePicker attendancereport_to;

    @FXML
    private Button attendancereport_show;

    @FXML
    private void show(ActionEvent event) {
        searchAttendance();
    }

    @FXML
    private void initialize() {
        attendancereport_from.setValue(LocalDate.now().minusMonths(1).plusDays(1));
        attendancereport_to.setValue(LocalDate.now());
        attendancereport_from.valueProperty().addListener((o, ol, nw) -> {
            attendancereport_to.setValue(attendancereport_from.getValue().plusMonths(1).minusDays(1));
        });
        searchAttendance();
    }

    /****************************** FXML ENDS HERE ******************************/

    private Admin admin;
    private AnchorPane container;

    private ObservableList<AttendanceReport> attendanceReportList = FXCollections.observableArrayList();

    private SQLAttendance sqlAttendance = new SQLAttendance();

    public void setRetrievedData(Admin admin, AnchorPane anchorPane) {
        this.admin = admin;
        this.container = anchorPane;
    }

    public void showAttendanceReport(Date from, Date to) {
        attendanceReportList.clear();

        attendanceReportList = sqlAttendance.getAttendanceReport(from, to);

        main_attendancereport_column_number.setCellValueFactory(new PropertyValueFactory<AttendanceReport, Integer>("Number"));
        main_attendancereport_column_fullname.setCellValueFactory(new PropertyValueFactory<AttendanceReport, String>("Full_Name"));
        main_attendancereport_column_empid.setCellValueFactory(new PropertyValueFactory<AttendanceReport, Integer>("Employee_ID"));
        main_attendancereport_column_department.setCellValueFactory(new PropertyValueFactory<AttendanceReport, String>("Department"));
        main_attendancereport_column_position.setCellValueFactory(new PropertyValueFactory<AttendanceReport, String>("Position"));
        main_attendancereport_column_present.setCellValueFactory(new PropertyValueFactory<AttendanceReport, Integer>("Present"));
        main_attendancereport_column_absent.setCellValueFactory(new PropertyValueFactory<AttendanceReport, Integer>("Absent"));
//        main_attendancereport_column_late.setCellValueFactory(new PropertyValueFactory<AttendanceReport, Integer>("Late"));
        main_attendancereport_column_holiday.setCellValueFactory(new PropertyValueFactory<AttendanceReport, Integer>("Holiday"));

        main_attendancereport_tableview.setItems(attendanceReportList);
    }

    private void searchAttendance() {
        try {
            attendancereport_from.getConverter().fromString(attendancereport_from.getEditor().getText());

            Date from = Date.valueOf(attendancereport_from.getValue());
            Date to = Date.valueOf(attendancereport_to.getValue());

            showAttendanceReport(from, to);
        } catch (Exception e) {
            e.printStackTrace();
            callAlert("Invalid Date Value", 3);
        }
    }
}
