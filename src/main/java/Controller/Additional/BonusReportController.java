package Controller.Additional;

import Models.Admin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class BonusReportController {

    @FXML
    private Button attendancereport_show;

    @FXML
    private TableColumn<?, ?> column_amount;

    @FXML
    private TableColumn<?, ?> column_dept;

    @FXML
    private TableColumn<?, ?> column_empid;

    @FXML
    private TableColumn<?, ?> column_fullname;

    @FXML
    private TableColumn<?, ?> column_number;

    @FXML
    private TableView<?> main_attendancereport_tableview;

    @FXML
    void show(ActionEvent event) {

    }

    /****************************** FXML ENDS HERE ******************************/

    private Admin admin;
    private AnchorPane container;

    public void setRetrievedData(Admin admin, AnchorPane anchorPane) {
        this.admin = admin;
        this.container = anchorPane;
    }


}
