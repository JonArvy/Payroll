package Controller.Additional;

import Models.Admin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class CredentialsController {

    @FXML
    private Button credentials_button_add;

    @FXML
    private Button credentials_button_deactivate;

    @FXML
    private TableColumn<?, ?> credentials_column_active;

    @FXML
    private TableColumn<?, ?> credentials_column_empid;

    @FXML
    private TableColumn<?, ?> credentials_column_fullname;

    @FXML
    private TableColumn<?, ?> credentials_column_no;

    @FXML
    private TableView<?> credentials_tableview;

    @FXML
    private void clickNavigation_Choices(ActionEvent event) {

    }

    /****************************** FXML ENDS HERE ******************************/

    private Admin admin;
    private AnchorPane container;
    public void setRetrievedData(Admin admin, AnchorPane anchorPane) {
        this.admin = admin;
        this.container = anchorPane;
    }
}
