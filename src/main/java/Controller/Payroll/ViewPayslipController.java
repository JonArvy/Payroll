package Controller.Payroll;

import Database.SQLEmployee;
import Models.Admin;
import Models.Employee;
import cw.payroll.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class ViewPayslipController {

    @FXML
    private TextField viewpayslip_absent;

    @FXML
    private TextField viewpayslip_basicsalary;

    @FXML
    private TextField viewpayslip_bonus;

    @FXML
    private Button viewpayslip_button_close;

    @FXML
    private TextField viewpayslip_daysrendered;

    @FXML
    private TextField viewpayslip_deductions;

    @FXML
    private TextField viewpayslip_department;

    @FXML
    private TextField viewpayslip_employeeid;

    @FXML
    private TextField viewpayslip_grosssalary;

    @FXML
    private TextField viewpayslip_holiday;

    @FXML
    private TextField viewpayslip_hoursrendered;

    @FXML
    private TextField viewpayslip_late;

    @FXML
    private TextField viewpayslip_less;

    @FXML
    private TextField viewpayslip_monthof;

    @FXML
    private TextField viewpayslip_name;

    @FXML
    private TextField viewpayslip_netpay;

    @FXML
    private TextField viewpayslip_overtime;

    @FXML
    private TextField viewpayslip_pagibig;

    @FXML
    private TextField viewpayslip_position;

    @FXML
    private void close(ActionEvent event) {
        loadPayslip();
    }

    /****************************** FXML ENDS HERE ******************************/

    private Admin admin;
    private AnchorPane container;

    private Employee employee;

    private ObservableList<Employee> employeeList = FXCollections.observableArrayList();

    private SQLEmployee sqlEmployee = new SQLEmployee();

    public void setRetrievedData(Admin admin, AnchorPane anchorPane) {
        this.admin = admin;
        this.container = anchorPane;
    }

    private void loadPayslip() {
        PayslipController controller;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UI/Payroll/Payslip.fxml"));
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

    public void setEmployee(Employee employee) {
        this.employee = employee;
        initializePayslipValues();
    }

    public void initializePayslipValues() {

    }
}

