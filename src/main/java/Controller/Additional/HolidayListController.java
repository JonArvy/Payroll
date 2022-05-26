package Controller.Additional;

import Models.Admin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

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

    }

    /****************************** FXML ENDS HERE ******************************/

    private Admin admin;
    private AnchorPane container;
    public void setRetrievedData(Admin admin, AnchorPane anchorPane) {
        this.admin = admin;
        this.container = anchorPane;
    }
}
