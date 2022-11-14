package Controller;

import Controller.Payroll.PayslipController;
import Database.SQLEmployee;
import Models.Admin;
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

import static Classes.PDFWriter.getImage;
import static Classes.PDFWriter.writePDF;

public class ViewPayslipInEmployeeController {

    @FXML
    private TextField absent_days;

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
    private void print(ActionEvent event) {
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
