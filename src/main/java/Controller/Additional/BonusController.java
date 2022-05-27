package Controller.Additional;

import Database.SQLBonus;
import Models.Admin;
import Models.Bonus;
import cw.payroll.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.Date;

public class BonusController {

    @FXML
    private Button bonus_button_add;

    @FXML
    private TableColumn<Bonus, Void> bonus_column_action;

    @FXML
    private TableColumn<Bonus, Double> bonus_column_amount;

    @FXML
    private TableColumn<Bonus, Date> bonus_column_dateapplicable;

    @FXML
    private TableColumn<Bonus, String> bonus_column_department;

    @FXML
    private TableColumn<Bonus, String> bonus_column_name;

    @FXML
    private TableView bonus_tableview;

    @FXML
    private void addBonus(ActionEvent event) {
        loadAddBonus();
    }

    @FXML
    private void initialize() {
        showBonusList();
    }

    /****************************** FXML ENDS HERE ******************************/

    private Admin admin;
    private AnchorPane container;

    private ObservableList<Bonus> bonusList = FXCollections.observableArrayList();

    private SQLBonus sqlBonus = new SQLBonus();

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

    public void showBonusList() {
        bonusList.clear();
        bonusList = sqlBonus.getBonus();

        bonus_column_name.setCellValueFactory(new PropertyValueFactory<Bonus, String>("Bonus_Name"));
        bonus_column_amount.setCellValueFactory(new PropertyValueFactory<Bonus, Double>("Bonus_Amount"));
        bonus_column_department.setCellValueFactory(new PropertyValueFactory<Bonus, String>("Recipient_Name"));
        bonus_column_dateapplicable.setCellValueFactory(new PropertyValueFactory<Bonus, Date>("Bonus_Date"));
//        main_bonus_action.setCellValueFactory(new PropertyValueFactory<Bonus, Integer>("Department_HoursPerDay"));

        bonus_tableview.setItems(bonusList);
    }
}
