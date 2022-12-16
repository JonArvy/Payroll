package Controller.Payroll;


import Classes.Converters;
import Database.SQLPayrollSummary;
import Models.Admin;
import Models.Summary;
import Models.SummarySchema;
import cw.payroll.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Comparator;

import static Classes.CustomAlert.callAlert;
import static Classes.PDFWriter.createPayrollSummaryPDF;


public class PastPayrollSummaryController {
    @FXML
    private ComboBox<SummarySchema> reportlist;

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
    private TableView payrollSummary_table;

    @FXML
    private Button load_report_button;

    @FXML
    private Button pdf_button;

    @FXML
    private void loadReport(ActionEvent event) {
        generatePayroll();
        pdf_button.setDisable(false);
    }
//
//    @FXML
//    private void generateReport() {
//        generatePayrollSummary(Date.valueOf(date_from.getValue()), Date.valueOf(date_to.getValue()));
//    }


    @FXML
    private void toPDF() {
        createPayrollSummaryPDF(summaryList, reportlist.getSelectionModel().getSelectedItem().getSummary_date_from(), reportlist.getSelectionModel().getSelectedItem().getSummary_date_to());
    }

    @FXML
    private void initialize() {
        loadComboField();
    }

    @FXML
    private void loadNew(ActionEvent event) {
        loadNewSummary();
    }

    /****************************** FXML ENDS HERE ******************************/

    private Admin admin;
    private AnchorPane container;

    private ObservableList<Summary> summaryList = FXCollections.observableArrayList();
    private ObservableList<SummarySchema> summarySchemas = FXCollections.observableArrayList();

    private SQLPayrollSummary sqlPayrollSummary = new SQLPayrollSummary();

    private Converters converters = new Converters();


    public void setRetrievedData(Admin admin, AnchorPane anchorPane) {
        this.admin = admin;
        this.container = anchorPane;
    }

    private void generatePayroll() {
        showPayrollSummary();
    }

    private void showPayrollSummary() {
        int id = reportlist.getSelectionModel().getSelectedItem().getSummary_id();

        summaryList.clear();

        summaryList = sqlPayrollSummary.getSummaryList(id);

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
    }

//
//    private void generatePayrollSummary(Date from, Date to) {
//        sqlPayrollSummary.savePayrollSummary(summaryList, from, to);
//    }

    private void loadComboField() {
        summarySchemas.clear();

        summarySchemas = sqlPayrollSummary.getSchemaSummaryList();
        Comparator<SummarySchema> comparator = Comparator.comparingInt(SummarySchema::getSummary_id);
        comparator = comparator.reversed();
        FXCollections.sort(summarySchemas, comparator);

        reportlist.setItems(summarySchemas);
        reportlist.setConverter(converters.summarySchemaStringConverter(summarySchemas));

        reportlist.getSelectionModel().select(0);
    }

    private void loadNewSummary() {
        PayrollSummaryController controller;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UI/Payroll/PayrollSummary.fxml"));
            fxmlLoader.load();

            controller = fxmlLoader.getController();
            controller.setRetrievedData(admin, container);

            AnchorPane anchorPane = fxmlLoader.getRoot();
            container.getChildren().add(anchorPane);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
