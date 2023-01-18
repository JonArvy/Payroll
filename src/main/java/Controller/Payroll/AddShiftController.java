package Controller.Payroll;

import Classes.Converters;
import Database.SQLDepartment;
import Database.SQLShift;
import Models.*;
import cw.payroll.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import static Classes.CustomAlert.callAlert;

public class AddShiftController {

    @FXML
    private Spinner<String> addshift_breakend_hour;

    @FXML
    private Spinner<String> addshift_breakend_hour1;

    @FXML
    private Spinner<String> addshift_breakend_minute;

    @FXML
    private Spinner<String> addshift_breakend_minute1;

    @FXML
    private Spinner<String> addshift_breakstart_hour;

    @FXML
    private Spinner<String> addshift_breakstart_hour1;

    @FXML
    private Spinner<String> addshift_breakstart_minute;

    @FXML
    private Spinner<String> addshift_breakstart_minute1;

    @FXML
    private Button addshift_button_add;

    @FXML
    private Button addshift_button_add1;

    @FXML
    private Button addshift_button_cancel;

    @FXML
    private Button addshift_button_cancel1;

    @FXML
    private ComboBox<Department> addshift_department;

    @FXML
    private ComboBox<String> addshift_employee;

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
    private Spinner<String> addshift_timein_hour;

    @FXML
    private Spinner<String> addshift_timein_hour1;

    @FXML
    private Spinner<String> addshift_timein_minute;

    @FXML
    private Spinner<String> addshift_timein_minute1;

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
        if (event.getSource() == addshift_button_add) {
            checkShiftIfValid(1);
        } else {
            checkShiftIfValid(2);
        }
    }

    @FXML
    private void cancel(ActionEvent event) {
        loadShift();
    }

    @FXML
    private void initialize() {
        initializeContainers();
        addListeners();
    }

    /****************************** FXML ENDS HERE ******************************/

    private NewAdmin admin;
    private AnchorPane container;

    private Department department;

    private ObservableList<Department> departmentList = FXCollections.observableArrayList();

    private SQLDepartment sqlDepartment = new SQLDepartment();
    private SQLShift sqlShift = new SQLShift();

    Converters converters = new Converters();

    private ObservableList<String> hour = FXCollections.observableArrayList("00", "01", "02", "03" , "04", "05", "06", "07", "08" , "09",
            "10", "11", "12", "13" , "14", "15", "16", "17", "18", "19",
            "20", "21", "22", "23"
    );
    private ObservableList<String> minute = FXCollections.observableArrayList("00", "01", "02", "03" , "04", "05", "06", "07", "08" , "09",
            "10", "11", "12", "13" , "14", "15", "16", "17", "18", "19",
            "20", "21", "22", "23" , "24", "25", "26", "27", "28", "29",
            "30", "31", "32", "33" , "34", "35", "36", "37", "38", "39",
            "40", "41", "42", "43" , "44", "45", "46", "47", "48", "49",
            "50", "51", "52", "53" , "54", "55", "56", "57", "58", "59"
    );

    public void setRetrievedData(NewAdmin admin, AnchorPane anchorPane) {
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

    private void checkShiftIfValid(int shifttype) {
        if (addshift_timeout.getText().equals("Invalid Time Values")) {
            callAlert("Invalid Time Values", 3);
        } else {
            try {
                int recepient = addshift_department.getValue().getDepartment_ID();
                boolean sunday = addshift_sunday.isSelected();
                boolean monday = addshift_monday.isSelected();
                boolean tuesday = addshift_tuesday.isSelected();
                boolean wednesday = addshift_wednesday.isSelected();
                boolean thursday = addshift_thursday.isSelected();
                boolean friday = addshift_friday.isSelected();
                boolean saturday = addshift_saturday.isSelected();

                Time now = Time.valueOf(LocalTime.now());
                Time in = Time.valueOf(LocalTime.parse(addshift_timein_hour.getValue() + ":" + addshift_timein_minute.getValue()));
                Time out = Time.valueOf(LocalTime.parse(addshift_timeout.getText()));

                Time breakstart = Time.valueOf(LocalTime.parse(addshift_breakstart_hour.getValue() + ":" + addshift_breakstart_minute.getValue()));
                Time breakend = Time.valueOf(LocalTime.parse(addshift_breakend_hour.getValue() + ":" + addshift_breakend_minute.getValue()));

                if (sqlShift.checkIfShiftExist(recepient)) {
                    callAlert("A shift already exists for that recipient", 3);
                } else {
                    sqlShift.addShift(new Shift(shifttype, recepient, in, out, breakstart, breakend, sunday, monday, tuesday, wednesday, thursday, friday, saturday));
                    loadShift();
                }
                System.out.println(now);

                System.out.println(in);
                System.out.println(out);
                System.out.println(breakstart);
                System.out.println(breakend);

            } catch (Exception e) {
                callAlert("Error!", 1);
            }
        }
    }

    private void initializeContainers() {
        addshift_timein_hour.setValueFactory(new SpinnerValueFactory.ListSpinnerValueFactory<String>(hour));
        addshift_timein_minute.setValueFactory(new SpinnerValueFactory.ListSpinnerValueFactory<String>(minute));
        addshift_breakstart_hour.setValueFactory(new SpinnerValueFactory.ListSpinnerValueFactory<String>(hour));
        addshift_breakstart_minute.setValueFactory(new SpinnerValueFactory.ListSpinnerValueFactory<String>(minute));
        addshift_breakend_hour.setValueFactory(new SpinnerValueFactory.ListSpinnerValueFactory<String>(hour));
        addshift_breakend_minute.setValueFactory(new SpinnerValueFactory.ListSpinnerValueFactory<String>(minute));

        addshift_timein_hour.getValueFactory().setValue("08");
        addshift_timein_minute.getValueFactory().setValue("00");
        addshift_breakstart_hour.getValueFactory().setValue("12");
        addshift_breakstart_minute.getValueFactory().setValue("00");
        addshift_breakend_hour.getValueFactory().setValue("13");
        addshift_breakend_minute.getValueFactory().setValue("00");

        departmentList.clear();
        departmentList = sqlDepartment.getDepartment();

        addshift_department.setItems(departmentList);
        addshift_department.getSelectionModel().select(0);

        addshift_department.setConverter(converters.departmentConverter());

        getEmployeeHoursToRender(new Department(addshift_department.getValue().getDepartment_ID()));
        updateTimeOutValue();
    }

    private void getEmployeeHoursToRender(Department dept) {
        department = sqlDepartment.getDepartment(dept);
    }

    private void updateTimeOutValue() {
        String timein_timeString = addshift_timein_hour.getValue() + ":" + addshift_timein_minute.getValue() + ":" + "00";
        String breaktimein_timeString = addshift_breakstart_hour.getValue() + ":" + addshift_breakstart_minute.getValue() + ":" + "00";
        String breaktimeout_timeString = addshift_breakend_hour.getValue() + ":" + addshift_breakend_minute.getValue() + ":" + "00";

        LocalTime timein = LocalTime.parse(timein_timeString, DateTimeFormatter.ISO_TIME);
        LocalTime breaktimein = LocalTime.parse(breaktimein_timeString, DateTimeFormatter.ISO_TIME);
        LocalTime breaktimeout = LocalTime.parse(breaktimeout_timeString, DateTimeFormatter.ISO_TIME);
        long totalbreakminutes = breaktimein.until(breaktimeout, ChronoUnit.MINUTES);
        LocalTime timeinwithbreak = timein.plusMinutes(totalbreakminutes);
        long timetorender = department.getDepartment_HoursPerDay();
        LocalTime totalout = timeinwithbreak.plusHours(timetorender);
        long timedifferencetimebreak = timein.until(breaktimein, ChronoUnit.HOURS);
        int timevalidation = timein.plusHours(timetorender).compareTo(breaktimein);

        if (totalbreakminutes <= 0 || timedifferencetimebreak <= 0 || timevalidation < 0) {
            addshift_timeout.setText("Invalid Time Values");
        } else {
            addshift_timeout.setText(totalout.toString());
        }
    }

    private void addListeners() {
        addshift_department.valueProperty().addListener((a, o, n) ->{
            getEmployeeHoursToRender(new Department(addshift_department.getValue().getDepartment_ID()));
        });
        addshift_timein_hour.valueProperty().addListener((a, o, n) -> {
            updateTimeOutValue();
        });
        addshift_timein_minute.valueProperty().addListener((a, o, n) -> {
            updateTimeOutValue();
        });
        addshift_breakstart_hour.valueProperty().addListener((a, o, n) -> {
            updateTimeOutValue();
        });
        addshift_breakstart_minute.valueProperty().addListener((a, o, n) -> {
            updateTimeOutValue();
        });
        addshift_breakend_hour.valueProperty().addListener((a, o, n) -> {
            updateTimeOutValue();
        });
        addshift_breakend_minute.valueProperty().addListener((a, o, n) -> {
            updateTimeOutValue();
        });
    }
}
