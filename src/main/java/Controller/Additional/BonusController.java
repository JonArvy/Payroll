package Controller.Additional;

import Models.Admin;
import cw.payroll.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

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
        loadAddBonus();
    }

    /****************************** FXML ENDS HERE ******************************/

    private Admin admin;
    private AnchorPane container;
    public void setRetrievedData(Admin admin, AnchorPane anchorPane) {
        this.admin = admin;
        this.container = anchorPane;
    }

    private void loadAddBonus() {
        AddBonusController controller;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UI/Additional/AddBonus.fxml"));
            fxmlLoader.load();

            controller = fxmlLoader.getController();
            controller.setRetrievedData(admin, container);

            AnchorPane anchorPane = fxmlLoader.getRoot();
//            container.getChildren().clear();
            container.getChildren().add(anchorPane);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
