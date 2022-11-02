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

import static Classes.CustomAlert.callAlert;

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
    private ComboBox<String> addbonus_recipient;


    @FXML
    private void addBonus(ActionEvent event) {
        checkBonusIfValid();
    }

    @FXML
    private void cancel(ActionEvent event) {
        loadBonus();
    }

    @FXML
    private void initialize() {
        initializeContainers();
    }

    /****************************** FXML ENDS HERE ******************************/

    private Admin admin;
    private AnchorPane container;

    private ObservableList<Department> departmentList = FXCollections.observableArrayList();

    private SQLDepartment sqlDepartment = new SQLDepartment();
    private SQLBonus sqlBonus = new SQLBonus();

    Converters converters = new Converters();

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

    private void initializeContainers() {

        departmentList.clear();
        departmentList = sqlDepartment.getDepartment();

        addbonus_recipient.getItems().addAll("Regular", "Contractual", "Part-Time");
        addbonus_recipient.getSelectionModel().select(0);



//        addbonus_recipient.setConverter(converters.departmentConverter());
    }

    private void checkBonusIfValid() {
        if (addbonus_name.getText() == null || addbonus_name.getText().trim().equals("")) {
            callAlert("Invalid Bonus Name", 3);
        } else {
            try {
                addbonus_dateapplicable.getConverter().fromString(addbonus_dateapplicable.getEditor().getText());

                String name = addbonus_name.getText();
                double amount = Double.parseDouble(addbonus_amount.getText());
                String recipient = addbonus_recipient.getValue();
                Date date = Date.valueOf(addbonus_dateapplicable.getValue());

                sqlBonus.addBonus(new Bonus(name, amount, recipient, date));

                loadBonus();
            } catch (NumberFormatException o) {
                callAlert("Invalid Amount", 3);
            } catch (Exception e) {
                callAlert("Invalid Date Value", 3);
            }
        }
    }
}
