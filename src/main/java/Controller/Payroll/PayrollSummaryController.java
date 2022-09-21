package Controller.Payroll;

        import javafx.fxml.FXML;
        import javafx.scene.control.TableColumn;
        import Models.Admin;
        import javafx.scene.layout.AnchorPane;

public class PayrollSummaryController {

    @FXML
    private TableColumn<?, ?> col_benefits;

    @FXML
    private TableColumn<?, ?> col_bir;

    @FXML
    private TableColumn<?, ?> col_edit;

    @FXML
    private TableColumn<?, ?> col_less;

    @FXML
    private TableColumn<?, ?> col_name;

    @FXML
    private TableColumn<?, ?> col_netamount;

    @FXML
    private TableColumn<?, ?> col_noofdaysabsent;

    @FXML
    private TableColumn<?, ?> col_noofdayspresent;

    @FXML
    private TableColumn<?, ?> col_number;

    @FXML
    private TableColumn<?, ?> col_pagibig;

    @FXML
    private TableColumn<?, ?> col_position;

    @FXML
    private TableColumn<?, ?> col_signature;

    @FXML
    private TableColumn<?, ?> col_total;

    @FXML
    private TableColumn<?, ?> col_wage;

    /****************************** FXML ENDS HERE ******************************/

    private Admin admin;
    private AnchorPane container;
    public void setRetrievedData(Admin admin, AnchorPane anchorPane) {
        this.admin = admin;
        this.container = anchorPane;
    }

}
