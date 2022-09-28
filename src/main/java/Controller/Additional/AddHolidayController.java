package Controller.Additional;

import Controller.Payroll.ShiftController;
import Database.SQLHoliday;
import Models.Admin;
import Models.Holiday;
import cw.payroll.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.w3c.dom.ls.LSOutput;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static Classes.CustomAlert.callAlert;

public class AddHolidayController {

    @FXML
    private Button addholiday_button_add;

    @FXML
    private Button addholiday_button_cancel;

    @FXML
    private DatePicker addholiday_date;

    @FXML
    private TextField addholiday_name;

    @FXML
    private ComboBox<String> addholiday_type;

    @FXML
    private void addHoliday(ActionEvent event) {
        checkHolidayIfValid();
    }

    @FXML
    private void cancel(ActionEvent event) {
        loadHolidayList();
    }

    @FXML
    private void initialize() {
        addholiday_type.getItems().addAll( "Suspension", "Regular Holiday", "Special Working Public Holiday", "Special Non-working Holiday", "Common local holiday", "Season", "Observance");
        addholiday_type.getSelectionModel().select(0);
    }

    /****************************** FXML ENDS HERE ******************************/

    private Admin admin;
    private AnchorPane container;

    private SQLHoliday sqlHoliday = new SQLHoliday();

    public void setRetrievedData(Admin admin, AnchorPane anchorPane) {
        this.admin = admin;
        this.container = anchorPane;
    }

    private void loadHolidayList() {
        HolidayListController controller;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UI/Additional/HolidayList.fxml"));
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

    private void checkHolidayIfValid() {
        if (addholiday_name.getText() == null || addholiday_name.getText().trim().equals("")) {
            callAlert("Invalid", "Invalid Holiday Name");
        } else {
            try {
                addholiday_date.getConverter().fromString(addholiday_date.getEditor().getText());

                String name = addholiday_name.getText();
                Date date = Date.valueOf(addholiday_date.getValue());
                String holiday_type = addholiday_type.getValue();

                sqlHoliday.addHoliday(new Holiday(name, date, holiday_type));

                loadHolidayList();
            } catch (Exception e) {
                callAlert("Invalid", "Invalid Date Value");
            }
        }
    }
}
