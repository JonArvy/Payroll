package Controller;

import Controller.Payroll.PayslipController;
import Database.SQLEmployee;
import Database.SQLPayrollSummary;
import Models.Admin;
import Models.Employee;
import Models.NewAdmin;
import Models.Summary;
import cw.payroll.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

import static Classes.BooleanAlerts.booleanAlert;
import static Classes.PDFWriter.*;

public class ViewPayslipInEmployeeController {

    @FXML
    private Label bonus_amount;

    @FXML
    private Button close_button;

    @FXML
    private Label department;

    @FXML
    private Label emp_id;

    @FXML
    private Label emp_name;

    @FXML
    private Label holiday_pay_amount;

    @FXML
    private Label holiday_pay_hours;

    @FXML
    private Label holiday_pay_rate;

    @FXML
    private Label less;

    @FXML
    private TextField netpay;

    @FXML
    private Label pagibig;

    @FXML
    private Label payroll_no;

    @FXML
    private Pane payslip;

    @FXML
    private Label position;

    @FXML
    private Button print_button;

    @FXML
    private Button claimed_button;

    @FXML
    private Label standard_pay_amount;

    @FXML
    private Label standard_pay_hours;

    @FXML
    private Label standard_pay_rate;

    @FXML
    private TextField total;


    @FXML
    private void close(ActionEvent event) {
//        createTablePDF();
        Stage stage = (Stage) total.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void print(ActionEvent event) {
        getImage(payslip);
        writePDF(summary.getName() + " " + LocalDate.now().toString());
    }


    /****************************** FXML ENDS HERE ******************************/

    private NewAdmin admin;
    private AnchorPane container;

    private Summary summary;

    private ObservableList<Employee> employeeList = FXCollections.observableArrayList();

    private SQLEmployee sqlEmployee = new SQLEmployee();

    public void setRetrievedData(NewAdmin admin, AnchorPane anchorPane) {
        this.admin = admin;
        this.container = anchorPane;
    }

    public void setSummary(Summary summary) {
        this.summary = summary;
        initializePayslipValues();
    }

    public void initializePayslipValues() {
        if (summary.isClaimed()) {
            claimed_button.setDisable(true);
        }
        emp_id.setText(String.valueOf(summary.getEmployeeNumber()));
        emp_name.setText(summary.getName());
        department.setText(summary.getDepartment());
        position.setText(summary.getPosition());
        total.setText(String.valueOf(summary.getTotalCompensation()));
        less.setText(String.valueOf(summary.getLess()));
        netpay.setText(String.valueOf(summary.getNetAmount()));

        SQLPayrollSummary sqlPayrollSummary = new SQLPayrollSummary();
        emp_id.setText(sqlPayrollSummary.getDateForSummary(summary.getSummaryID()));


        standard_pay_hours.setText(String.valueOf(summary.getTotalHour()));
        standard_pay_rate.setText(String.valueOf(summary.getTotalHourlyRate()));
        standard_pay_amount.setText(String.valueOf(summary.getWage()));


    }

    @FXML
    private void markAsClaimed(ActionEvent actionEvent) {
        SQLPayrollSummary sqlPayrollSummary = new SQLPayrollSummary();
        if (booleanAlert("Are you sure that you want to mark this payslip as claimed?")) {
            sqlPayrollSummary.markPayAsClaimed(summary.getDatabaseID());
            Stage stage = (Stage) total.getScene().getWindow();
            stage.close();
        }
    }

}
