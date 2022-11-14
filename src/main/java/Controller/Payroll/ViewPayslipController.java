//package Controller.Payroll;
//
//import Database.SQLEmployee;
//import Models.Admin;
//import Models.Employee;
//import cw.payroll.Main;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.control.Button;
//import javafx.scene.control.TextField;
//import javafx.scene.layout.AnchorPane;
//
//import java.io.IOException;
//
//public class ViewPayslipController {
//
//    @FXML
//    private TextField viewpayslip_absent;
//
//    @FXML
//    private TextField viewpayslip_basicsalary;
//
//    @FXML
//    private TextField viewpayslip_bonus;
//
//    @FXML
//    private Button viewpayslip_button_close;
//
//    @FXML
//    private TextField viewpayslip_daysrendered;
//
//    @FXML
//    private TextField viewpayslip_deductions;
//
//    @FXML
//    private TextField viewpayslip_department;
//
//    @FXML
//    private TextField viewpayslip_employeeid;
//
//    @FXML
//    private TextField viewpayslip_grosssalary;
//
//    @FXML
//    private TextField viewpayslip_holiday;
//
//    @FXML
//    private TextField viewpayslip_hoursrendered;
//
//    @FXML
//    private TextField viewpayslip_late;
//
//    @FXML
//    private TextField viewpayslip_less;
//
//    @FXML
//    private TextField viewpayslip_monthof;
//
//    @FXML
//    private TextField viewpayslip_name;
//
//    @FXML
//    private TextField viewpayslip_netpay;
//
//    @FXML
//    private TextField viewpayslip_overtime;
//
//    @FXML
//    private TextField viewpayslip_pagibig;
//
//    @FXML
//    private TextField viewpayslip_position;
//
//    @FXML
//    private void close(ActionEvent event) {
//        loadPayslip();
//    }
//
//    /****************************** FXML ENDS HERE ******************************/
//
//    private Admin admin;
//    private AnchorPane container;
//
//    private Employee employee;
//
//    private ObservableList<Employee> employeeList = FXCollections.observableArrayList();
//
//    private SQLEmployee sqlEmployee = new SQLEmployee();
//
//    public void setRetrievedData(Admin admin, AnchorPane anchorPane) {
//        this.admin = admin;
//        this.container = anchorPane;
//    }
//
//    private void loadPayslip() {
//        PayslipController controller;
//        try {
//            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UI/Payroll/Payslip.fxml"));
//            fxmlLoader.load();
//
//            controller = fxmlLoader.getController();
//            controller.setRetrievedData(admin, container);
//
//            AnchorPane anchorPane = fxmlLoader.getRoot();
//
//            container.getChildren().clear();
//            container.getChildren().add(anchorPane);
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public void setEmployee(Employee employee) {
//        this.employee = employee;
//        initializePayslipValues();
//    }
//
//    public void initializePayslipValues() {
//
//    }
//}
//



package Controller.Payroll;

import Classes.PDFWriter;
import Database.SQLDepartment;
import Database.SQLEmployee;
import Models.Admin;
import Models.Department;
import Models.Employee;
import Models.Summary;
import cw.payroll.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

import static Classes.PDFWriter.*;

public class ViewPayslipController {

    @FXML
    private TextField absent_days;

    @FXML
    private Button close_button;

    @FXML
    private Text date;

    @FXML
    private TextField department;

    @FXML
    private TextField emp_id;

    @FXML
    private TextField emp_name;

    @FXML
    private TextField less;

    @FXML
    private TextField monthly_rate;

    @FXML
    private TextField netpay;

    @FXML
    private TextField pagibig;

    @FXML
    private Pane payslip;

    @FXML
    private TextField position;

    @FXML
    private TextField present_days;

    @FXML
    private Button print_button;

    @FXML
    private TextField total;

    @FXML
    private TextField wages;


    @FXML
    private void close(ActionEvent event) {
//        createTablePDF();
        loadPayslip();
    }

    @FXML
    private void print(ActionEvent event) {
        loadPayslip();
        getImage(payslip);
        writePDF(summary.getName() + " " + LocalDate.now().toString());
    }


    /****************************** FXML ENDS HERE ******************************/

    private Admin admin;
    private AnchorPane container;

    private Summary summary;

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
//            container.getChildren().clear();
            container.getChildren().add(anchorPane);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void setSummary(Summary summary) {
        this.summary = summary;
        initializePayslipValues();
    }

    public void initializePayslipValues() {
        emp_id.setText(String.valueOf(summary.getEmployeeNumber()));
        emp_name.setText(summary.getName());
        department.setText(summary.getDepartment());
        date.setText(String.valueOf(summary.getDateCreated()));
        position.setText(summary.getPosition());
        wages.setText(String.valueOf(summary.getWage()));
        present_days.setText(String.valueOf(summary.getPresentDays()));
        absent_days.setText(String.valueOf(summary.getAbsentDays()));
        total.setText(String.valueOf(summary.getTotalCompensation()));
        less.setText(String.valueOf(summary.getLess()));
        netpay.setText(String.valueOf(summary.getNetAmount()));
        date.setText(String.valueOf(LocalDate.now()));
        monthly_rate.setText(String.valueOf(summary.getMonthlyRate()));

//        monthly_rate.setText(String.valueOf(summary.getNetAmount()));
//        basic_salary.setText(String.valueOf(summary.getWage()));
//        gross_salary.setText(String.valueOf(summary.getTotalCompensation()));
//        deductions.setText(String.valueOf(summary.getTotalDeduction()));
//        netpay.setText(String.valueOf(summary.getNetAmount()));
//        absent.setText(String.valueOf(summary.getAbsentDays()));
//        ut_late.setText(String.valueOf(summary.getLateUT()));
        pagibig.setText("---");
    }

}
