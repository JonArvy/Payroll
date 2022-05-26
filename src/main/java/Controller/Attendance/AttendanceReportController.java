package Controller.Attendance;

import Models.Admin;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class AttendanceReportController {

    @FXML
    private TableColumn<?, ?> main_attendancereport_column_absent;

    @FXML
    private TableColumn<?, ?> main_attendancereport_column_department;

    @FXML
    private TableColumn<?, ?> main_attendancereport_column_empid;

    @FXML
    private TableColumn<?, ?> main_attendancereport_column_fullname;

    @FXML
    private TableColumn<?, ?> main_attendancereport_column_holiday;

    @FXML
    private TableColumn<?, ?> main_attendancereport_column_late;

    @FXML
    private TableColumn<?, ?> main_attendancereport_column_number;

    @FXML
    private TableColumn<?, ?> main_attendancereport_column_position;

    @FXML
    private TableColumn<?, ?> main_attendancereport_column_present;

    @FXML
    private TableView<?> main_attendancereport_tableview;

    /****************************** FXML ENDS HERE ******************************/

    private Admin admin;
    private AnchorPane container;
    public void setRetrievedData(Admin admin, AnchorPane anchorPane) {
        this.admin = admin;
        this.container = anchorPane;
    }
}
