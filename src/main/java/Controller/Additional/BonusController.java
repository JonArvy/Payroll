package Controller.Additional;

import Models.Admin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class BonusController {

    @FXML
    private Button bonus_button_add;

    @FXML
    private TableColumn<?, ?> bonus_column_action;

    @FXML
    private TableColumn<?, ?> bonus_column_amount;

    @FXML
    private TableColumn<?, ?> bonus_column_dateapplicable;

    @FXML
    private TableColumn<?, ?> bonus_column_department;

    @FXML
    private TableColumn<?, ?> bonus_column_name;

    @FXML
    private TableView<?> bonus_tableview;

    @FXML
    private void addBonus(ActionEvent event) {

    }

    /****************************** FXML ENDS HERE ******************************/

    private Admin admin;
    private AnchorPane container;
    public void setRetrievedData(Admin admin, AnchorPane anchorPane) {
        this.admin = admin;
        this.container = anchorPane;
    }

}
