package Controller.Attendance;

import Classes.TimeSpinner;
import Database.SQLAttendance;
import Models.Admin;
import Models.Attendance;
import Models.NewAdmin;
import cw.payroll.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

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
    private Pane main_pane;

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
        addLoadSpinner();
    }

    /****************************** FXML ENDS HERE ******************************/

    private NewAdmin admin;
    private AnchorPane container;

    private Attendance main_attendance;

    private SQLAttendance sqlAttendance = new SQLAttendance();

    private TimeSpinner spinner = new TimeSpinner();
    private TimeSpinner spinner2 = new TimeSpinner();


    public void setRetrievedData(NewAdmin admin, AnchorPane anchorPane) {
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
        String timein = main_attendance.getEmployee_TimeIn().toString();
        timein = timein.substring(0, timein.length() - 3);

        String timeout = main_attendance.getEmployee_TimeOut().toString();
        timeout = timeout.substring(0, timeout.length() - 3);

        spinner.getEditor().setText(timein);
        spinner2.getEditor().setText(timeout);
    }

    private void addLoadSpinner() {
        spinner.setLayoutX(261);
        spinner.setLayoutY(238);

        spinner2.setLayoutX(261);
        spinner2.setLayoutY(273);

        spinner.setPrefWidth(142);
        spinner2.setPrefWidth(142);

        main_pane.getChildren().addAll(spinner, spinner2);
    }

    private void updateAttendance() {
        Attendance attnd = new Attendance();
        attnd.setAttendance_ID(main_attendance.getAttendance_ID());
        attnd.setEmployee_ID(main_attendance.getEmployee_ID());
        attnd.setEmployee_FullName(main_attendance.getEmployee_FullName());
        attnd.setDepartment_Name(main_attendance.getDepartment_Name());
        attnd.setEmployee_Position(main_attendance.getEmployee_Position());

        attnd.setEmployee_Attendance_Date(Date.valueOf(editattendance_datepicker.getValue()));
        attnd.setEmployee_TimeIn(Time.valueOf(spinner.getEditor().getText() + ":00"));
        attnd.setEmployee_TimeOut(Time.valueOf(spinner2.getEditor().getText() + ":00"));

        sqlAttendance.updateAttendance(attnd);

        closeEditAttendance();
    }

}
