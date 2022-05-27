package Controller.Additional;

import Models.Admin;
import cw.payroll.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class AddBonusController {

    @FXML
    private TextField addbonus_amount;

    @FXML
    private Button addbonus_button_add;

    @FXML
    private Button addbonus_button_cancel;

    @FXML
    private DatePicker addbonus_dateapplicable;

    @FXML
    private TextField addbonus_name;

    @FXML
    private ComboBox<?> addbonus_recipient;

    @FXML
    private ComboBox<?> addbonus_recipienttype;

    @FXML
    private void addBonus(ActionEvent event) {
        loadBonus();
    }

    @FXML
    private void cancel(ActionEvent event) {
        loadBonus();
    }

    /****************************** FXML ENDS HERE ******************************/

    private Admin admin;
    private AnchorPane container;
    public void setRetrievedData(Admin admin, AnchorPane anchorPane) {
        this.admin = admin;
        this.container = anchorPane;
    }

    private void loadBonus() {
        BonusController controller;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UI/Additional/Bonus.fxml"));
            fxmlLoader.load();

            controller = fxmlLoader.getController();
            controller.setRetrievedData(admin, container);

            AnchorPane anchorPane = fxmlLoader.getRoot();
            container.getChildren().clear();
            container.getChildren().add(anchorPane);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
