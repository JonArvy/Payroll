package Controller.Payroll;

import Database.SQLDepartment;
import Models.Admin;
import Models.Department;
import cw.payroll.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

import static Classes.CustomAlert.callAlert;

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
    private TableColumn<Department, Double> department_column_dailyrate;

    @FXML
    private TableColumn<Department, Integer> department_column_dayspermonth;

    @FXML
    private TableColumn<Department, Double> department_column_hourlyrate;

    @FXML
    private TableColumn<Department, Integer> department_column_hoursperday;

    @FXML
    private TableColumn<Department, Double> department_column_monthlyrate;

    @FXML
    private TableColumn<Department, String> department_column_name;

    @FXML
    private TableColumn<?, ?> department_column_shift;

    @FXML
    private TableView department_tableview;

    @FXML
    private void addDepartment(ActionEvent event) {
        loadAddDepartment();
    }

    @FXML
    private void deleteDepartment(ActionEvent event) {
        loadTableEntry2();
    }

    @FXML
    private void editDepartment(ActionEvent event) {
        loadTableEntry();
    }

    @FXML
    private void nextPage(ActionEvent event) {
        loadShift();
    }

    @FXML
    private void initialize() {
        showDepartmentTable();
    }

    /****************************** FXML ENDS HERE ******************************/

    private Admin admin;
    private AnchorPane container;

    private ObservableList<Department> departmentList = FXCollections.observableArrayList();

    private SQLDepartment sqlDepartment = new SQLDepartment();

    public void setRetrievedData(Admin admin, AnchorPane anchorPane) {
        this.admin = admin;
        this.container = anchorPane;
    }

    private void loadShift() {
        ShiftController controller;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UI/Payroll/Shift.fxml"));
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

    private void loadAddDepartment() {
        AddDepartmentController controller;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UI/Payroll/AddDepartment.fxml"));
            fxmlLoader.load();

            controller = fxmlLoader.getController();
            controller.setRetrievedData(admin, container);

            AnchorPane anchorPane = fxmlLoader.getRoot();
//            container.getChildren().clear();
            container.getChildren().add(anchorPane);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void loadEditDepartment(Department dept) {
        EditDepartmentController controller;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UI/Payroll/EditDepartment.fxml"));
            fxmlLoader.load();

            controller = fxmlLoader.getController();
            controller.setRetrievedData(admin, container);

            controller.setDepartment(dept);

            AnchorPane anchorPane = fxmlLoader.getRoot();
//            container.getChildren().clear();
            container.getChildren().add(anchorPane);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void showDepartmentTable() {
        departmentList.clear();
        departmentList = sqlDepartment.getDepartment();

        department_column_name.setCellValueFactory(new PropertyValueFactory<Department, String>("Department_Name"));
        department_column_monthlyrate.setCellValueFactory(new PropertyValueFactory<Department, Double>("Department_MonthlyRate"));
        department_column_dayspermonth.setCellValueFactory(new PropertyValueFactory<Department, Integer>("Department_DaysPerMonth"));
        department_column_dailyrate.setCellValueFactory(new PropertyValueFactory<Department, Double>("Daily_Rate"));
        department_column_hoursperday.setCellValueFactory(new PropertyValueFactory<Department, Integer>("Department_HoursPerDay"));
        department_column_hourlyrate.setCellValueFactory(new PropertyValueFactory<Department, Double>("Hourly_Rate"));


        department_tableview.setItems(departmentList);
    }

    private void loadTableEntry() {
        try {
            Department dept = (Department) department_tableview.getSelectionModel().getSelectedItem();
//            System.out.println(dept.getDepartment_Name());
            loadEditDepartment(dept);
        } catch (Exception e) {
            e.printStackTrace();
            callAlert("No row/entry selected", 4);
        }
    }

    private void loadTableEntry2() {
        try {
            Department dept = (Department) department_tableview.getSelectionModel().getSelectedItem();
//            System.out.println(dept.getDepartment_Name());
            delete(dept);
        } catch (Exception e) {
            callAlert("No row/entry selected", 4);
        }
    }

    private void delete(Department dept) {
        sqlDepartment.deleteDepartment(dept);
        showDepartmentTable();
    }
}
