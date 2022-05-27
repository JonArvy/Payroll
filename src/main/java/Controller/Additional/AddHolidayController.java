package Controller.Additional;

import Controller.Payroll.ShiftController;
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
    private ComboBox<?> addholiday_type;

    @FXML
    private void addHoliday(ActionEvent event) {
        loadHolidayList();
    }

    @FXML
    private void cancel(ActionEvent event) {
        loadHolidayList();
    }

    /****************************** FXML ENDS HERE ******************************/

    private Admin admin;
    private AnchorPane container;
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
}
