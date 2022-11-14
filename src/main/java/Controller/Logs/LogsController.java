package Controller.Logs;

import Database.SQLLogs;
import Models.Admin;
import Models.Logs;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.sql.Date;
import java.sql.Time;

public class LogsController {

    @FXML
    private TableColumn<Logs, Time> time;

    @FXML
    private ComboBox<?> choices;

    @FXML
    private TableColumn<Logs, Date> date;

    @FXML
    private TableColumn<Logs, String> message;

    @FXML
    private TableView<Logs> table;

    @FXML
    void export(ActionEvent event) {

    }

    @FXML
    void refresh(ActionEvent event) {

    }

    @FXML
    private void initialize() {
        loadLogs();
    }

    /****************************** FXML ENDS HERE ******************************/
    private Admin admin;
    private AnchorPane container;

    private SQLLogs sqlLogs = new SQLLogs();
    private ObservableList<Logs> logsList = FXCollections.observableArrayList();

    public void setRetrievedData(Admin admin, AnchorPane anchorPane) {
        this.admin = admin;
        this.container = anchorPane;
    }

    private void loadLogs() {
        logsList.clear();
        logsList = sqlLogs.getLogs();

        message.setCellValueFactory(new PropertyValueFactory<Logs, String>("log_message"));
        date.setCellValueFactory(new PropertyValueFactory<Logs, Date>("log_date"));
        time.setCellValueFactory(new PropertyValueFactory<Logs, Time>("log_time"));

        table.setItems(logsList);
    }

}