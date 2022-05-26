package Controller.Payroll;

import Models.Admin;
import javafx.scene.layout.AnchorPane;

public class PayrollSummaryController {

    /****************************** FXML ENDS HERE ******************************/

    private Admin admin;
    private AnchorPane container;
    public void setRetrievedData(Admin admin, AnchorPane anchorPane) {
        this.admin = admin;
        this.container = anchorPane;
    }
}
