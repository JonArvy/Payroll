package Controller.Logs;

import Database.SQLLogs;
import Models.Admin;
import Models.Employee;
import Models.Logs;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.sql.Date;
import java.sql.Time;

public class LogsController {

    @FXML
    private TableColumn<Logs, String> admin_col;

    @FXML
    private TableColumn<Logs, Date> date;

    @FXML
    private TableColumn<Logs, String> message;

    @FXML
    private TextField search;

    @FXML
    private TableView<Logs> table;

    @FXML
    private TableColumn<Logs, Time> time;

    @FXML
    private TableColumn<Logs, String> type;

    @FXML
    void export(ActionEvent event) {

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

        type.setCellValueFactory(new PropertyValueFactory<Logs, String>("log_type"));
        message.setCellValueFactory(new PropertyValueFactory<Logs, String>("log_message"));
        admin_col.setCellValueFactory(new PropertyValueFactory<Logs, String>("log_admin"));
        date.setCellValueFactory(new PropertyValueFactory<Logs, Date>("log_date"));
        time.setCellValueFactory(new PropertyValueFactory<Logs, Time>("log_time"));

        table.setItems(logsList);

        setFilters();
    }


    private void setFilters() {
        FilteredList<Logs> filteredList = new FilteredList<>(logsList, p -> true);

        search.textProperty().addListener((a, o, n) -> {
            filteredList.setPredicate(myObject -> {
                if (n == null || n.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = n.toLowerCase();

                if (String.valueOf(myObject.getLog_Admin()).contains(lowerCaseFilter)) {
                    return true;
                } else if (String.valueOf(myObject.getLog_date()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (String.valueOf(myObject.getLog_time()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (String.valueOf(myObject.getLog_message()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (String.valueOf(myObject.getLog_type()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });

        SortedList<Logs> sortedData = new SortedList<>(filteredList);

        sortedData.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(sortedData);
    }
}
