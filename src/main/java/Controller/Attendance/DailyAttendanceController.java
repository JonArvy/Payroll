package Controller.Attendance;

import Models.Admin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class DailyAttendanceController {

    @FXML
    private TableColumn<?, ?> main_dailyattendance_column_action;

    @FXML
    private TableColumn<?, ?> main_dailyattendance_column_date;

    @FXML
    private TableColumn<?, ?> main_dailyattendance_column_department;

    @FXML
    private TableColumn<?, ?> main_dailyattendance_column_empid;

    @FXML
    private TableColumn<?, ?> main_dailyattendance_column_fullname;

    @FXML
    private TableColumn<?, ?> main_dailyattendance_column_position;

    @FXML
    private TableColumn<?, ?> main_dailyattendance_column_timein;

    @FXML
    private TableColumn<?, ?> main_dailyattendance_column_timeout;

    @FXML
    private DatePicker main_dailyattendance_datepicker;

    @FXML
    private Button main_dailyattendance_nextdate;

    @FXML
    private Button main_dailyattendance_previousdate;

    @FXML
    private TableView<?> main_dailyattendance_tableview;

    @FXML
    private void moveAttendanceDate(ActionEvent event) {

    }

    /****************************** FXML ENDS HERE ******************************/

    private Admin admin;
    private AnchorPane container;
    public void setRetrievedData(Admin admin, AnchorPane anchorPane) {
        this.admin = admin;
        this.container = anchorPane;
    }


}
