package Controller.Additional;

import Classes.Converters;
import Database.SQLBonus;
import Database.SQLDepartment;
import Models.Admin;
import Models.Bonus;
import Models.Department;
import cw.payroll.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

import static Classes.CustomAlert.callAlert;

public class EditBonusController {

    @FXML
    private TextField editbonus_amount;

    @FXML
    private Button editbonus_button_add;

    @FXML
    private Button editbonus_button_cancel;

    @FXML
    private DatePicker editbonus_dateapplicable;

    @FXML
    private TextField editbonus_name;

    @FXML
    private ComboBox<String> editbonus_recipient;

    @FXML
    private ComboBox<String> editbonus_recipienttype;

    @FXML
    private void updateBonus(ActionEvent event) {
        checkBonusIfValid();
    }

    @FXML
    private void cancel(ActionEvent event) {
        loadBonus();
    }

    @FXML
    private void initialize() {
        initializeContainers();
//        editbonus_button_add.setDisable(true);
    }

    /****************************** FXML ENDS HERE ******************************/

    private Admin admin;
    private AnchorPane container;

    private Bonus bonus;

    private ObservableList<Department> departmentList = FXCollections.observableArrayList();

    private SQLDepartment sqlDepartment = new SQLDepartment();
    private SQLBonus sqlBonus = new SQLBonus();

    Converters converters = new Converters();

    public void setRetrievedData(Admin admin, AnchorPane anchorPane) {
        this.admin = admin;
        this.container = anchorPane;
    }

    public void setBonus(Bonus bonus) {
        this.bonus = bonus;
        initializeFields();
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

    private void initializeContainers() {
        editbonus_recipienttype.getItems().addAll( "By Department");
        editbonus_recipienttype.getSelectionModel().select(0);

        departmentList.clear();
        departmentList = sqlDepartment.getDepartment();

        editbonus_recipient.getItems().addAll("Regular", "Contractual", "Part-Time");
        editbonus_recipient.getSelectionModel().select(0);

    }

    private void checkBonusIfValid() {
        if (editbonus_name.getText() == null || editbonus_name.getText().trim().equals("")) {
            callAlert("Invalid", "Invalid Bonus Name");
        } else {
            try {
                editbonus_dateapplicable.getConverter().fromString(editbonus_dateapplicable.getEditor().getText());

                String name = editbonus_name.getText();
                double amount = Double.parseDouble(editbonus_amount.getText());
                String recipient = editbonus_recipient.getValue();
                Date date = Date.valueOf(editbonus_dateapplicable.getValue());

                sqlBonus.editBonus(new Bonus(bonus.getBonus_ID(), name, amount, recipient, date));

                loadBonus();
            } catch (NumberFormatException o) {
                callAlert("Invalid", "Invalid Amount");
            } catch (Exception e) {
                callAlert("Invalid", "Invalid Date Value");
            }
        }
    }

    private void initializeFields() {
        editbonus_name.setText(bonus.getBonus_Name());
        editbonus_amount.setText(Double.toString(bonus.getBonus_Amount()));
        editbonus_recipient.getSelectionModel().select(bonus.getBonus_Recipient());
        editbonus_dateapplicable.setValue(bonus.getBonus_Date().toLocalDate());
    }
}
