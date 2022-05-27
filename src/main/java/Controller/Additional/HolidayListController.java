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

public class HolidayListController {

    @FXML
    private Button holiday_button_add;

    @FXML
    private TableColumn<?, ?> holiday_column_action;

    @FXML
    private TableColumn<?, ?> holiday_column_date;

    @FXML
    private TableColumn<?, ?> holiday_column_holidayname;

    @FXML
    private TableColumn<?, ?> holiday_column_repeat;

    @FXML
    private TableColumn<?, ?> holiday_column_type;

    @FXML
    private TableView<?> holiday_tableview;

    @FXML
    private void addHoliday(ActionEvent event) {
        loadAddHoliday();
    }

    @FXML
    private void initialize() {

    }

    /****************************** FXML ENDS HERE ******************************/

    private Admin admin;
    private AnchorPane container;
    public void setRetrievedData(Admin admin, AnchorPane anchorPane) {
        this.admin = admin;
        this.container = anchorPane;
    }

    private void loadAddHoliday() {
        AddHolidayController controller;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UI/Additional/AddHoliday.fxml"));
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
