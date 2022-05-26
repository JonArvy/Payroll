package Controller.Payroll;

import Models.Admin;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class PayslipController {

    @FXML
    private TableColumn<?, ?> main_payslip_column_action;

    @FXML
    private TableColumn<?, ?> main_payslip_column_department;

    @FXML
    private TableColumn<?, ?> main_payslip_column_empid;

    @FXML
    private TableColumn<?, ?> main_payslip_column_fullname;

    @FXML
    private TableColumn<?, ?> main_payslip_column_position;

    @FXML
    private TableView<?> main_payslip_tableview;

    /****************************** FXML ENDS HERE ******************************/

    private Admin admin;
    private AnchorPane container;
    public void setRetrievedData(Admin admin, AnchorPane anchorPane) {
        this.admin = admin;
        this.container = anchorPane;
    }
}
