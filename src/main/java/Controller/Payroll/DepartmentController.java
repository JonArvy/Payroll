package Controller.Payroll;

import Models.Admin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class DepartmentController {

    @FXML
    private Button department_button_add;

    @FXML
    private Button department_button_delete;

    @FXML
    private Button department_button_edit;

    @FXML
    private Button department_button_next;

    @FXML
    private TableColumn<?, ?> department_column_dailyrate;

    @FXML
    private TableColumn<?, ?> department_column_dayspermonth;

    @FXML
    private TableColumn<?, ?> department_column_hourlyrate;

    @FXML
    private TableColumn<?, ?> department_column_hoursperday;

    @FXML
    private TableColumn<?, ?> department_column_monthlyrate;

    @FXML
    private TableColumn<?, ?> department_column_name;

    @FXML
    private TableColumn<?, ?> department_column_shift;

    @FXML
    private TableView<?> department_tableview;

    @FXML
    private void addDepartment(ActionEvent event) {

    }

    @FXML
    private void deleteDepartment(ActionEvent event) {

    }

    @FXML
    private void editDepartment(ActionEvent event) {

    }

    @FXML
    private void nextPage(ActionEvent event) {

    }

    /****************************** FXML ENDS HERE ******************************/

    private Admin admin;
    private AnchorPane container;
    public void setRetrievedData(Admin admin, AnchorPane anchorPane) {
        this.admin = admin;
        this.container = anchorPane;
    }
}
