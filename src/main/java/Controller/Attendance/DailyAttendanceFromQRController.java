package Controller.Attendance;

import Database.SQLAdminAttendance;
import Database.SQLAttendance;
import Models.Attendance;
import Models.NewAdmin;
import cw.payroll.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;

import static Classes.CustomAlert.callAlert;
import static Classes.ImporterFileChooser.callAttendanceImporter;

public class DailyAttendanceFromQRController {

    @FXML
    private TableColumn<Attendance, Void> main_dailyattendance_column_action;

    @FXML
    private TableColumn<Attendance, Date> main_dailyattendance_column_date;

    @FXML
    private TableColumn<Attendance, String> main_dailyattendance_column_department;

    @FXML
    private TableColumn<Attendance, Integer> main_dailyattendance_column_empid;

    @FXML
    private TableColumn<Attendance, String> main_dailyattendance_column_fullname;

    @FXML
    private TableColumn<Attendance, String> main_dailyattendance_column_position;

    @FXML
    private TableColumn<Attendance, Time> main_dailyattendance_column_timein;

    @FXML
    private TableColumn<Attendance, Time> main_dailyattendance_column_timeout;

    @FXML
    private DatePicker main_dailyattendance_datepicker;

    @FXML
    private Button main_dailyattendance_nextdate;

    @FXML
    private Button main_dailyattendance_previousdate;

    @FXML
    private TableView main_dailyattendance_tableview;

    @FXML
    private TextField dailyattendance_search;

    @FXML
    private void moveAttendanceDate(ActionEvent event) {
        if (event.getSource() == main_dailyattendance_nextdate) {
            main_dailyattendance_datepicker.setValue(main_dailyattendance_datepicker.getValue().plusDays(1));
        } else if (event.getSource() == main_dailyattendance_previousdate) {
            main_dailyattendance_datepicker.setValue(main_dailyattendance_datepicker.getValue().minusDays(1));
        }
    }

    @FXML
    private void search(ActionEvent event) {

    }


    @FXML
    private void initialize() {
        main_dailyattendance_datepicker.valueProperty().addListener((o, ol, nw) -> {
            showDailyAttendanceTable();
        });
        main_dailyattendance_datepicker.setValue(LocalDate.now());
    }

    @FXML
    void viewQRAttendance(ActionEvent event) {
        loadAdminAttendance();
    }

    /****************************** FXML ENDS HERE ******************************/

    private NewAdmin admin;
    private AnchorPane container;

    private ObservableList<Attendance> attendanceList = FXCollections.observableArrayList();
    private SQLAdminAttendance sqlAdminAttendance = new SQLAdminAttendance();

    public void setRetrievedData(NewAdmin admin, AnchorPane anchorPane) {
        this.admin = admin;
        this.container = anchorPane;
    }

    private void showDailyAttendanceTable() {
        attendanceList.clear();
        attendanceList = sqlAdminAttendance.getDailyAttendance(Date.valueOf(main_dailyattendance_datepicker.getValue()),
                Date.valueOf(main_dailyattendance_datepicker.getValue().plusDays(1)));

        main_dailyattendance_column_date.setCellValueFactory(new PropertyValueFactory<Attendance, Date>("Employee_Attendance_Date"));
        main_dailyattendance_column_empid.setCellValueFactory(new PropertyValueFactory<Attendance, Integer>("Employee_ID"));
        main_dailyattendance_column_fullname.setCellValueFactory(new PropertyValueFactory<Attendance, String>("Employee_FullName"));
        main_dailyattendance_column_department.setCellValueFactory(new PropertyValueFactory<Attendance, String>("Department_Name"));
        main_dailyattendance_column_position.setCellValueFactory(new PropertyValueFactory<Attendance, String>("Employee_Position"));
        main_dailyattendance_column_timein.setCellValueFactory(new PropertyValueFactory<Attendance, Time>("Employee_TimeIn"));
        main_dailyattendance_column_timeout.setCellValueFactory(new PropertyValueFactory<Attendance, Time>("Employee_TimeOut"));

        Callback<TableColumn<Attendance, Void>, TableCell<Attendance, Void>> cellFactory = new Callback<TableColumn<Attendance, Void>, TableCell<Attendance, Void>>() {
            @Override
            public TableCell<Attendance, Void> call(final TableColumn<Attendance, Void> param) {
                final TableCell<Attendance, Void> cell = new TableCell<Attendance, Void>() {
                    private final Button btn = new Button("Edit");

                    {
                        String style = "-fx-background-color: #c3c4c4, linear-gradient(#d6d6d6 50%, white 100%)," +
                                "radial-gradient(center 50% -40%, radius 200%, #e6e6e6 45%, rgba(230,230,230,0) 50%); " +
                                "-fx-background-radius: 30; " +
                                "-fx-background-insets: 0,1,1; " +
                                "-fx-text-fill: black; " +
                                "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.6), 3, 0.0, 0, 1);";

                        btn.setStyle(style);
                        btn.setDisable(true);
                        btn.setOnAction((ActionEvent event) -> {
                            Attendance attendance = getTableView().getItems().get(getIndex());
                            System.out.println(attendance.getAttendance_ID());
                            loadEditDailyAttendance(attendance);
                        });
                    }
                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            HBox allbtn = new HBox(btn);
                            setGraphic(allbtn);
                        }
                    }
                };
                return cell;
            }
        };
        main_dailyattendance_column_action.setCellFactory(cellFactory);

        setFilters();
//        main_dailyattendance_tableview.setItems(attendanceList);
    }

    private void setFilters() {
        FilteredList<Attendance> filteredList = new FilteredList<>(attendanceList, p -> true);

        dailyattendance_search.textProperty().addListener((a, o, n) -> {
            filteredList.setPredicate(myObject -> {
                if (n == null || n.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = n.toLowerCase();

                if (String.valueOf(myObject.getEmployee_Attendance_Date()).contains(lowerCaseFilter)) {
                    return true;
                } else if (String.valueOf(myObject.getEmployee_ID()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (String.valueOf(myObject.getEmployee_FullName()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (String.valueOf(myObject.getDepartment_Name()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (String.valueOf(myObject.getEmployee_Position()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (String.valueOf(myObject.getEmployee_TimeIn()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (String.valueOf(myObject.getEmployee_TimeOut()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });

        SortedList<Attendance> sortedData = new SortedList<>(filteredList);

        sortedData.comparatorProperty().bind(main_dailyattendance_tableview.comparatorProperty());
        main_dailyattendance_tableview.setItems(sortedData);
    }

    private void loadEditDailyAttendance(Attendance attendance) {
        EditDailyAttendanceController controller;
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UI/Attendance/EditDailyAttendance.fxml"));
            fxmlLoader.load();

            controller = fxmlLoader.getController();
            controller.setRetrievedData(admin, container);

//            controller.setEmployee(employee);
            controller.setInfo(attendance);

            AnchorPane anchorPane = fxmlLoader.getRoot();
//            container.getChildren().clear();
            container.getChildren().add(anchorPane);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    private void loadAdminAttendance() {
        DailyAttendanceController controller;
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UI/Attendance/DailyAttendance.fxml"));
            fxmlLoader.load();

            controller = fxmlLoader.getController();
            controller.setRetrievedData(admin, container);

            AnchorPane anchorPane = fxmlLoader.getRoot();
            container.getChildren().add(anchorPane);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
