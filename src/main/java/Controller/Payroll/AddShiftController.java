package Controller.Payroll;

import Models.Admin;
import cw.payroll.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class AddShiftController {

    @FXML
    private Spinner<?> addshift_breakend_hour;

    @FXML
    private Spinner<?> addshift_breakend_hour1;

    @FXML
    private Spinner<?> addshift_breakend_minute;

    @FXML
    private Spinner<?> addshift_breakend_minute1;

    @FXML
    private Spinner<?> addshift_breakstart_hour;

    @FXML
    private Spinner<?> addshift_breakstart_hour1;

    @FXML
    private Spinner<?> addshift_breakstart_minute;

    @FXML
    private Spinner<?> addshift_breakstart_minute1;

    @FXML
    private Button addshift_button_add;

    @FXML
    private Button addshift_button_add1;

    @FXML
    private Button addshift_button_cancel;

    @FXML
    private Button addshift_button_cancel1;

    @FXML
    private ComboBox<?> addshift_department;

    @FXML
    private ComboBox<?> addshift_employee;

    @FXML
    private CheckBox addshift_friday;

    @FXML
    private CheckBox addshift_friday1;

    @FXML
    private CheckBox addshift_monday;

    @FXML
    private CheckBox addshift_monday1;

    @FXML
    private CheckBox addshift_saturday;

    @FXML
    private CheckBox addshift_saturday1;

    @FXML
    private CheckBox addshift_sunday;

    @FXML
    private CheckBox addshift_sunday1;

    @FXML
    private CheckBox addshift_thursday;

    @FXML
    private CheckBox addshift_thursday1;

    @FXML
    private Spinner<?> addshift_timein_hour;

    @FXML
    private Spinner<?> addshift_timein_hour1;

    @FXML
    private Spinner<?> addshift_timein_minute;

    @FXML
    private Spinner<?> addshift_timein_minute1;

    @FXML
    private TextField addshift_timeout;

    @FXML
    private TextField addshift_timeout1;

    @FXML
    private CheckBox addshift_tuesday;

    @FXML
    private CheckBox addshift_tuesday1;

    @FXML
    private CheckBox addshift_wednesday;

    @FXML
    private CheckBox addshift_wednesday1;

    @FXML
    private void addShift(ActionEvent event) {
        loadShift();
    }

    @FXML
    private void cancel(ActionEvent event) {
        loadShift();
    }

    /****************************** FXML ENDS HERE ******************************/

    private Admin admin;
    private AnchorPane container;
    public void setRetrievedData(Admin admin, AnchorPane anchorPane) {
        this.admin = admin;
        this.container = anchorPane;
    }

    private void loadShift() {
        ShiftController controller;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UI/Payroll/Shift.fxml"));
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
