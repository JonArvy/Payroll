package Controller.Payroll;


import Classes.Converters;
import Database.SQLPayrollSummary;
import Models.AttendanceReport;
import Models.Summary;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;

import Models.Admin;

import java.sql.Date;
import java.time.LocalDate;

import static Classes.CustomAlert.callAlert;
import static Classes.PDFWriter.createTablePDF;


public class PayrollSummaryController {

    @FXML
    private TableColumn<Summary, Double> col_benefits;

    @FXML
    private TableColumn<Summary, Void> col_bir;

    @FXML
    private TableColumn<Summary, Void> col_edit;

    @FXML
    private TableColumn<Summary, Double> col_less;

    @FXML
    private TableColumn<Summary, String> col_name;

    @FXML
    private TableColumn<Summary, Double> col_netamount;

    @FXML
    private TableColumn<Summary, Integer> col_noofdaysabsent;

    @FXML
    private TableColumn<Summary, Integer> col_noofdayspresent;

    @FXML
    private TableColumn<Summary, Integer> col_number;

    @FXML
    private TableColumn<Summary, Void> col_pagibig;

    @FXML
    private TableColumn<Summary, String> col_position;

    @FXML
    private TableColumn<Summary, Void> col_signature;

    @FXML
    private TableColumn<Summary, Double> col_totaldeduction;

    @FXML
    private TableColumn<Summary, Double> col_totalcompensation;

    @FXML
    private TableColumn<Summary, Double> col_wage;

    @FXML
    private DatePicker date_from;

    @FXML
    private DatePicker date_to;

    @FXML
    private TableView payrollSummary_table;

    @FXML
    private Button loadActiveEmployees_button;

    @FXML
    private Button generateReport_button;

    @FXML
    private Button pdf_button;
    @FXML
    private void loadEmployees(ActionEvent event) {
        generatePayroll();
        generateReport_button.setDisable(false);
        pdf_button.setDisable(false);
    }

    @FXML
    private void generateReport() {
        generatePayrollSummary(Date.valueOf(date_from.getValue()), Date.valueOf(date_to.getValue()));
    }


    @FXML
    private void toPDF() {
        createTablePDF(summaryList);
    }

    @FXML
    private void initialize() {
        date_from.setValue(LocalDate.now().minusMonths(1).plusDays(1));
        date_to.setValue(LocalDate.now());
        date_from.valueProperty().addListener((o, ol, nw) -> {
            date_to.setValue(date_from.getValue().plusMonths(1).minusDays(1));
        });
//        searchAttendance();
    }

    /****************************** FXML ENDS HERE ******************************/

    private Admin admin;
    private AnchorPane container;

    private ObservableList<Summary> summaryList = FXCollections.observableArrayList();

    private SQLPayrollSummary sqlPayrollSummary = new SQLPayrollSummary();


    public void setRetrievedData(Admin admin, AnchorPane anchorPane) {
        this.admin = admin;
        this.container = anchorPane;
    }

    private void generatePayroll() {
        try {
            date_from.getConverter().fromString(date_from.getEditor().getText());

            Date from = Date.valueOf(date_from.getValue());
            Date to = Date.valueOf(date_to.getValue());

            showPayrollSummary(from, to);
        } catch (Exception e) {
            e.printStackTrace();
            callAlert("Invalid", "Invalid Date Value");
        }
    }

    private void showPayrollSummary(Date from, Date to) {
        summaryList.clear();

        summaryList = sqlPayrollSummary.loadPayrollSummary(from, to);

        col_number.setCellValueFactory(new PropertyValueFactory<Summary, Integer>("Number"));
        col_name.setCellValueFactory(new PropertyValueFactory<Summary, String>("Name"));
        col_position.setCellValueFactory(new PropertyValueFactory<Summary, String>("Position"));

        col_wage.setCellValueFactory(new PropertyValueFactory<Summary, Double>("Wage"));
        col_benefits.setCellValueFactory(new PropertyValueFactory<Summary, Double>("Benefits"));
        col_noofdayspresent.setCellValueFactory(new PropertyValueFactory<Summary, Integer>("PresentDays"));
        col_noofdaysabsent.setCellValueFactory(new PropertyValueFactory<Summary, Integer>("AbsentDays"));
        col_totalcompensation.setCellValueFactory(new PropertyValueFactory<Summary, Double>("TotalCompensation"));

        col_less.setCellValueFactory(new PropertyValueFactory<Summary, Double>("Less"));
        col_totaldeduction.setCellValueFactory(new PropertyValueFactory<Summary, Double>("TotalDeduction"));
        col_netamount.setCellValueFactory(new PropertyValueFactory<Summary, Double>("NetAmount"));

        payrollSummary_table.setItems(summaryList);

//        col_bir;
//        col_edit;
//        col_pagibig;
//        col_signature;
    }


    private void generatePayrollSummary(Date from, Date to) {
        sqlPayrollSummary.savePayrollSummary(summaryList, from, to);
//        summaryList.clear();
//
//        summaryList = sqlPayrollSummary.loadPayrollSummary(from, to);
//
//        col_number.setCellValueFactory(new PropertyValueFactory<Summary, Integer>("Number"));
//        col_name.setCellValueFactory(new PropertyValueFactory<Summary, String>("Name"));
//        col_position.setCellValueFactory(new PropertyValueFactory<Summary, String>("Position"));
//
//        col_wage.setCellValueFactory(new PropertyValueFactory<Summary, Double>("Wage"));
//        col_benefits.setCellValueFactory(new PropertyValueFactory<Summary, Double>("Benefits"));
//        col_noofdayspresent.setCellValueFactory(new PropertyValueFactory<Summary, Integer>("PresentDays"));
//        col_noofdaysabsent.setCellValueFactory(new PropertyValueFactory<Summary, Integer>("AbsentDays"));
//        col_totalcompensation.setCellValueFactory(new PropertyValueFactory<Summary, Double>("TotalCompensation"));
//
//        col_less.setCellValueFactory(new PropertyValueFactory<Summary, Double>("Less"));
//        col_totaldeduction.setCellValueFactory(new PropertyValueFactory<Summary, Double>("TotalDeduction"));
//        col_netamount.setCellValueFactory(new PropertyValueFactory<Summary, Double>("NetAmount"));
//
//        payrollSummary_table.setItems(summaryList);

//        col_bir;
//        col_edit;
//        col_pagibig;
//        col_signature;
    }



}
