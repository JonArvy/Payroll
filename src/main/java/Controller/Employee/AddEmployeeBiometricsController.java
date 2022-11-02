package Controller.Employee;

import Controller.NoticeBoard.NoticeBoardController;
import Database.SQLEmployee;
import Models.Admin;
import Models.Employee;
import cw.payroll.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.Date;

import static Classes.CustomAlert.callAlert;

public class AddEmployeeBiometricsController {

    @FXML
    private Button addemployee_back_button;

    @FXML
    private AnchorPane biometrics_panel;

    @FXML
    private void addEmployee(ActionEvent event) {
        checkEmployeeIfValid();
    }

    @FXML
    private void goBack(ActionEvent event) {
        loadAddEmployee();
    }

    /****************************** FXML ENDS HERE ******************************/

    private Admin admin;
    private AnchorPane container;
    private Employee employee;

    private SQLEmployee sqlEmployee = new SQLEmployee();

    public void setRetrievedData(Admin admin, AnchorPane anchorPane) {
        this.admin = admin;
        this.container = anchorPane;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    private void loadAddEmployee() {
        AddEmployeeController controller;

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UI/Employee/AddEmployee.fxml"));
            fxmlLoader.load();

            controller = fxmlLoader.getController();
            controller.setRetrievedData(admin, container);

            controller.setEmployee(employee);

            AnchorPane anchorPane = fxmlLoader.getRoot();
            container.getChildren().clear();
            container.getChildren().add(anchorPane);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void loadManageEmployee() {
        ManageEmployeeController controller;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UI/Employee/ManageEmployee.fxml"));
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

    private void checkEmployeeIfValid() {
        try {
            sqlEmployee.addEmployee(employee);

            loadManageEmployee();
        } catch (Exception e) {
            callAlert("An Error Occurred!", 1);
        }
    }
}
