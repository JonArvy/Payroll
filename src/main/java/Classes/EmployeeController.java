package Classes;

import Models.Attendance;
import Models.Bonus;
import Models.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.sql.Time;
import java.sql.Date;
import java.util.ResourceBundle;

public class EmployeeController implements Initializable {

    @FXML
    private TableColumn<Attendance, Date> employee_attendance_column_date;

    @FXML
    private TableColumn<?, ?> employee_attendance_column_department;

    @FXML
    private TableColumn<Attendance, Time> employee_attendance_column_timein;

    @FXML
    private TableColumn<Attendance, Time> employee_attendance_column_timeout;

    @FXML
    private DatePicker employee_attendance_date_from;

    @FXML
    private DatePicker employee_attendance_date_to;

    @FXML
    private TextField employee_attendance_emp_id_field;

    @FXML
    private TableView employee_attendance_table;

    @FXML
    private TextField employee_attendance_total_field;

    @FXML
    private Pane employee_attendance_login_panel;

    @FXML
    private Pane employee_attendance_main_panel;

    SQLExecution sql;
    private ObservableList<Attendance> attendanceList = FXCollections.observableArrayList();

    @FXML
    void resetButton(ActionEvent event) {

    }

    @FXML
    void setButton(ActionEvent event) {

    }

    @FXML
    void totalButton(ActionEvent event) {

    }

    @FXML
    void searchEmployee(ActionEvent event) {
        employee_attendance_main_panel.toFront();
        attendanceList = sql.getAttendance(Integer.parseInt(employee_attendance_emp_id_field.getText()));
        attendanceList.clear();

        employee_attendance_column_date.setCellValueFactory(new PropertyValueFactory<Attendance, Date>("Employee_Attendance_Date"));
        employee_attendance_column_timein.setCellValueFactory(new PropertyValueFactory<Attendance, Time>("Employee_TimeIn"));
        employee_attendance_column_timeout.setCellValueFactory(new PropertyValueFactory<Attendance, Time>("Employee_TimeOut"));

        employee_attendance_table.setItems(attendanceList);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sql = new SQLExecution();
    }
}
