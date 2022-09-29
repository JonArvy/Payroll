package Controller.Attendance;

import Database.SQLAttendance;
import Models.Admin;
import Models.Attendance;
import cw.payroll.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;

public class EditDailyAttendanceController {

    @FXML
    private Button editattendance_button_cancel;

    @FXML
    private Button editattendance_button_save;

    @FXML
    private DatePicker editattendance_datepicker;

    @FXML
    private TextField editattendance_department;

    @FXML
    private TextField editattendance_empid;

    @FXML
    private TextField editattendance_fullname;

    @FXML
    private TextField editattendance_position;

    @FXML
    private Spinner<String> editattendance_timein_hour;

    @FXML
    private Spinner<String> editattendance_timein_minute;

    @FXML
    private Spinner<String> editattendance_timeout_hour;

    @FXML
    private Spinner<String> editattendance_timeout_minute;

    @FXML
    private void cancel(ActionEvent event) {
        closeEditAttendance();
    }

    @FXML
    private void save(ActionEvent event) {
        updateAttendance();
    }

    @FXML
    private void initialize() {
    }

    /****************************** FXML ENDS HERE ******************************/

    private Admin admin;
    private AnchorPane container;

    private Attendance main_attendance;

    private SQLAttendance sqlAttendance = new SQLAttendance();


    public void setRetrievedData(Admin admin, AnchorPane anchorPane) {
        this.admin = admin;
        this.container = anchorPane;
    }

    public void setInfo(Attendance attendance) {
        main_attendance = attendance;
        editattendance_empid.setText(String.valueOf(attendance.getEmployee_ID()));
        editattendance_fullname.setText(attendance.getEmployee_FullName());
        editattendance_department.setText(attendance.getDepartment_Name());
        editattendance_position.setText(attendance.getEmployee_Position());
        editattendance_datepicker.setValue(attendance.getEmployee_Attendance_Date().toLocalDate());


        initializeContainers();
    }

    private void loadShift() {
        DailyAttendanceController controller;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UI/Attendance/DailyAttendance.fxml"));
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

    private ObservableList<String> hour = FXCollections.observableArrayList("0", "1", "2", "3" , "4", "5", "6", "7", "8" , "9",
            "10", "11", "12", "13" , "14", "15", "16", "17", "18", "19",
            "20", "21", "22", "23"
    );
    private ObservableList<String> minute = FXCollections.observableArrayList("0", "1", "2", "3" , "4", "5", "6", "7", "8" , "9",
            "10", "11", "12", "13" , "14", "15", "16", "17", "18", "19",
            "20", "21", "22", "23" , "24", "25", "26", "27", "28", "29",
            "30", "31", "32", "33" , "34", "35", "36", "37", "38", "39",
            "40", "41", "42", "43" , "44", "45", "46", "47", "48", "49",
            "50", "51", "52", "53" , "54", "55", "56", "57", "58", "59"
    );

    private void closeEditAttendance() {
        DailyAttendanceController controller;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UI/Attendance/DailyAttendance.fxml"));
            fxmlLoader.load();

            controller = fxmlLoader.getController();
            controller.setRetrievedData(admin, container);

            controller.setDateInfo(main_attendance.getEmployee_Attendance_Date());

            AnchorPane anchorPane = fxmlLoader.getRoot();
            container.getChildren().clear();
            container.getChildren().add(anchorPane);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void initializeContainers() {
        editattendance_timein_hour.setValueFactory(new SpinnerValueFactory.ListSpinnerValueFactory<String>(hour));
        editattendance_timein_minute.setValueFactory(new SpinnerValueFactory.ListSpinnerValueFactory<String>(minute));
        editattendance_timeout_hour.setValueFactory(new SpinnerValueFactory.ListSpinnerValueFactory<String>(hour));
        editattendance_timeout_minute.setValueFactory(new SpinnerValueFactory.ListSpinnerValueFactory<String>(minute));

        editattendance_timein_hour.getEditor().setText(String.valueOf(main_attendance.getEmployee_TimeIn().getHours()));
        editattendance_timein_minute.getEditor().setText(String.valueOf(main_attendance.getEmployee_TimeIn().getMinutes()));
        editattendance_timeout_hour.getEditor().setText(String.valueOf(main_attendance.getEmployee_TimeOut().getHours()));
        editattendance_timeout_minute.getEditor().setText(String.valueOf(main_attendance.getEmployee_TimeOut().getMinutes()));
    }

    private void updateAttendance() {
        Attendance attnd = new Attendance();
        attnd.setAttendance_ID(main_attendance.getAttendance_ID());
        attnd.setEmployee_ID(main_attendance.getEmployee_ID());
        attnd.setEmployee_FullName(main_attendance.getEmployee_FullName());
        attnd.setDepartment_Name(main_attendance.getDepartment_Name());
        attnd.setEmployee_Position(main_attendance.getEmployee_Position());

        attnd.setEmployee_Attendance_Date(Date.valueOf(editattendance_datepicker.getValue()));
        attnd.setEmployee_TimeIn(Time.valueOf(editattendance_timein_hour.getEditor().getText() + ":" + editattendance_timein_minute.getEditor().getText() + ":00"));
        attnd.setEmployee_TimeOut(Time.valueOf(editattendance_timeout_hour.getEditor().getText() + ":" + editattendance_timeout_minute.getEditor().getText() + ":00"));

        sqlAttendance.updateAttendance(attnd);

        closeEditAttendance();
    }

}
