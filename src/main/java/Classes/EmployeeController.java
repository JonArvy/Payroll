package Classes;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class EmployeeController {

    @FXML
    private TableColumn<?, ?> employee_attendance_column_date;

    @FXML
    private TableColumn<?, ?> employee_attendance_column_department;

    @FXML
    private TableColumn<?, ?> employee_attendance_column_timein;

    @FXML
    private TableColumn<?, ?> employee_attendance_column_timeout;

    @FXML
    private DatePicker employee_attendance_date_from;

    @FXML
    private DatePicker employee_attendance_date_to;

    @FXML
    private Button employee_attendance_emp_id_button;

    @FXML
    private TextField employee_attendance_emp_id_field;

    @FXML
    private Button employee_attendance_reset;

    @FXML
    private Button employee_attendance_set;

    @FXML
    private TableView<?> employee_attendance_table;

    @FXML
    private Button employee_attendance_total_button;

    @FXML
    private TextField employee_attendance_total_field;

    @FXML
    void actionButton(ActionEvent event) {
        if (event.getSource() == employee_attendance_emp_id_button) {

            String o = employee_attendance_emp_id_field.getText();
            System.out.println(o);

        }
    }

}
