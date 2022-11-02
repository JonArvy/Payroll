package Controller.Additional;

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

import java.io.IOException;
import java.sql.Date;

import static Classes.CustomAlert.callAlert;

public class EditHolidayController {

    @FXML
    private Button editholiday_button_add;

    @FXML
    private Button editholiday_button_cancel;

    @FXML
    private DatePicker editholiday_date;

    @FXML
    private TextField editholiday_name;

    @FXML
    private ComboBox<String> editholiday_type;

    @FXML
    private void updateHoliday(ActionEvent event) {
        checkHolidayIfValid();
    }

    @FXML
    private void cancel(ActionEvent event) {
        loadHolidayList();
    }

    @FXML
    private void initialize() {
        editholiday_type.getItems().addAll( "Regular Holiday", "Special Working Public Holiday", "Special Non-working Holiday", "Common local holiday", "Season", "Observance");
        editholiday_type.getSelectionModel().select(0);
        editholiday_button_add.setDisable(true);
    }

    /****************************** FXML ENDS HERE ******************************/

    private Admin admin;
    private AnchorPane container;

    private Holiday holiday;

    private SQLHoliday sqlHoliday = new SQLHoliday();

    public void setRetrievedData(Admin admin, AnchorPane anchorPane) {
        this.admin = admin;
        this.container = anchorPane;
    }

    public void setHoliday(Holiday holiday) {
        this.holiday = holiday;
        loadHolidayFields();
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
        if (editholiday_name.getText() == null || editholiday_name.getText().trim().equals("")) {
            callAlert("Invalid Holiday Name", 3);
        } else {
            try {
                editholiday_date.getConverter().fromString(editholiday_date.getEditor().getText());

                String name = editholiday_name.getText();
                Date date = Date.valueOf(editholiday_date.getValue());
                String holiday_type = editholiday_type.getValue();

                sqlHoliday.editHoliday(new Holiday(name, date, holiday_type));

                loadHolidayList();
            } catch (Exception e) {
                callAlert("Invalid Date Value", 3);
            }
        }
    }

    private void loadHolidayFields() {
        Holiday hold = sqlHoliday.getHoliday(new Holiday(holiday.getHoliday_ID()));

        editholiday_name.setText(hold.getHoliday_Name());
        editholiday_date.setValue(hold.getHoliday_Date().toLocalDate());
        editholiday_type.setValue(hold.getHoliday_Type());
    }
}
