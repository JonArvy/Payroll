package Controller.Additional;

import Database.SQLDepartment;
import Database.SQLHoliday;
import Models.Admin;
import Models.Bonus;
import Models.Department;
import Models.Holiday;
import cw.payroll.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

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

    private void loadEditHoliday(Holiday holiday) {
        EditHolidayController controller;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UI/Additional/EditHoliday.fxml"));
            fxmlLoader.load();

            controller = fxmlLoader.getController();
            controller.setRetrievedData(admin, container);
            controller.setHoliday(holiday);

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

        Callback<TableColumn<Holiday, Void>, TableCell<Holiday, Void>> cellFactory = new Callback<TableColumn<Holiday, Void>, TableCell<Holiday, Void>>() {
            @Override
            public TableCell<Holiday, Void> call(final TableColumn<Holiday, Void> param) {
                final TableCell<Holiday, Void> cell = new TableCell<Holiday, Void>() {
                    private final Button btn = new Button("Edit");
                    private final Button btn2 = new Button("Delete");

                    {
                        String style = "-fx-background-color: #c3c4c4, linear-gradient(#d6d6d6 50%, white 100%)," +
                                "radial-gradient(center 50% -40%, radius 200%, #e6e6e6 45%, rgba(230,230,230,0) 50%); " +
                                "-fx-background-radius: 30; " +
                                "-fx-background-insets: 0,1,1; " +
                                "-fx-text-fill: black; " +
                                "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.6), 3, 0.0, 0, 1);";

                        btn.setStyle(style);
                        btn.setOnAction((ActionEvent event) -> {
                            loadEditHoliday(getTableView().getItems().get(getIndex()));
//                            Employee emp = getTableView().getItems().get(getIndex());
                        });
                        btn2.setDisable(false);
                        btn2.setStyle(style);
                        btn2.setOnAction((ActionEvent event) -> {
                            sqlHoliday.deleteHoliday(getTableView().getItems().get(getIndex()));
                            showHolidayList();
//                            Employee emp = getTableView().getItems().get(getIndex());
                        });
                    }
                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            HBox allbtn = new HBox(btn, btn2);
                            setGraphic(allbtn);
                        }
                    }
                };
                return cell;
            }
        };
        holiday_column_action.setCellFactory(cellFactory);
        holiday_tableview.setItems(holidayList);
    }
}
