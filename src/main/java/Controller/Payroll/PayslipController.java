package Controller.Payroll;

import Database.SQLDepartment;
import Database.SQLEmployee;
import Models.Admin;
import Models.Department;
import Models.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class PayslipController {

    @FXML
    private TableColumn<?, ?> main_payslip_column_action;

    @FXML
    private TableColumn<Employee, String> main_payslip_column_department;

    @FXML
    private TableColumn<Employee, Integer> main_payslip_column_empid;

    @FXML
    private TableColumn<Employee, String> main_payslip_column_fullname;

    @FXML
    private TableColumn<Employee, String> main_payslip_column_position;

    @FXML
    private TableView main_payslip_tableview;

    @FXML
    private void initialize() {
        showPayslipList();
    }

    /****************************** FXML ENDS HERE ******************************/

    private Admin admin;
    private AnchorPane container;

    private ObservableList<Employee> employeeList = FXCollections.observableArrayList();

    private SQLEmployee sqlEmployee = new SQLEmployee();

    public void setRetrievedData(Admin admin, AnchorPane anchorPane) {
        this.admin = admin;
        this.container = anchorPane;
    }

    private void showPayslipList() {
        employeeList.clear();
        employeeList = sqlEmployee.getAllEmployeePayslip();

        main_payslip_column_empid.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("Employee_ID"));
        main_payslip_column_fullname.setCellValueFactory(new PropertyValueFactory<Employee, String>("Full_Name"));
        main_payslip_column_department.setCellValueFactory(new PropertyValueFactory<Employee, String>("Department_Name"));
        main_payslip_column_position.setCellValueFactory(new PropertyValueFactory<Employee, String>("Position"));
//        main_payslip_column_action.setCellValueFactory(new PropertyValueFactory<Employee, String>("Department_Name"));

        main_payslip_tableview.setItems(employeeList);
    }
}
