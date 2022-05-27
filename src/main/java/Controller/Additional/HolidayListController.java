package Controller.Additional;

import Database.SQLDepartment;
import Database.SQLHoliday;
import Models.Admin;
import Models.Department;
import Models.Holiday;
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

public class HolidayListController {

    @FXML
    private Button holiday_button_add;

    @FXML
    private TableColumn<Holiday, Void> holiday_column_action;

    @FXML
    private TableColumn<Holiday, Date> holiday_column_date;

    @FXML
    private TableColumn<Holiday, String> holiday_column_holidayname;

    @FXML
    private TableColumn<Holiday, String> holiday_column_type;

    @FXML
    private TableView holiday_tableview;

    @FXML
    private void addHoliday(ActionEvent event) {
        loadAddHoliday();
    }

    @FXML
    private void initialize() {
        showHolidayList();
    }

    /****************************** FXML ENDS HERE ******************************/

    private Admin admin;
    private AnchorPane container;

    private ObservableList<Holiday> holidayList = FXCollections.observableArrayList();

    private SQLHoliday sqlHoliday = new SQLHoliday();

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

    public void showHolidayList() {
        holidayList.clear();
        holidayList = sqlHoliday.getHolidays();

        holiday_column_holidayname.setCellValueFactory(new PropertyValueFactory<Holiday, String>("Holiday_Name"));
        holiday_column_date.setCellValueFactory(new PropertyValueFactory<Holiday, Date>("Holiday_Date"));
        holiday_column_type.setCellValueFactory(new PropertyValueFactory<Holiday, String>("Holiday_Type"));
//        holiday_column_action.setCellValueFactory(new PropertyValueFactory<Holiday, Integer>("Department_HoursPerDay"));

        holiday_tableview.setItems(holidayList);
    }
}
