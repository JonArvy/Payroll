package Controller.Payroll;

import Classes.TimeSpinner;
import Database.SQLDepartment;
import Models.Admin;
import Models.Department;
import Models.Employee;
import Models.NewAdmin;
import cw.payroll.Main;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.sql.Time;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import static Classes.CustomAlert.callAlert;

public class EditDepartmentController {

    @FXML
    private TextField edit_department_days;

    @FXML
    private TextField edit_department_hours;

    @FXML
    private TextField edit_department_name;

    @FXML
    private TextField edit_department_rate;

    @FXML
    private CheckBox editshift_monday;

    @FXML
    private CheckBox editshift_friday;

    @FXML
    private CheckBox editshift_saturday;

    @FXML
    private CheckBox editshift_sunday;

    @FXML
    private CheckBox editshift_thursday;

    @FXML
    private CheckBox editshift_tuesday;

    @FXML
    private CheckBox editshift_wednesday;

    @FXML
    private Button update_button;

    @FXML
    private Pane main_pane;

    @FXML
    void update(ActionEvent event) {
        checkDepartmentIfValid();
    }


    @FXML
    private void cancel(ActionEvent event) {
        loadDepartment();
    }

    @FXML
    private void initialize() {
        edit_department_days.setText("20");
        edit_department_hours.setText("8");
        addSpinner();
        edit_department_days.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    edit_department_days.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        edit_department_hours.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    edit_department_hours.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }

    /****************************** FXML ENDS HERE ******************************/

    private NewAdmin admin;
    private AnchorPane container;

    private Department department;

    private SQLDepartment sqlDepartment = new SQLDepartment();

    private final TimeSpinner spinner1 = new TimeSpinner();

    private final TimeSpinner spinner2 = new TimeSpinner();

    private final TimeSpinner spinner3 = new TimeSpinner();

    private final TimeSpinner spinner4 = new TimeSpinner();

    public void setRetrievedData(NewAdmin admin, AnchorPane anchorPane) {
        this.admin = admin;
        this.container = anchorPane;
    }

    public void setDepartment(Department department) {
        this.department = department;
        loadDepartmentFields();
    }

    private void loadDepartment() {
        DepartmentController controller;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UI/Payroll/Department.fxml"));
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

    private void checkDepartmentIfValid() {
        if (edit_department_name.getText() == null || edit_department_name.getText().trim().equals("")) {
            callAlert("Invalid Department Name", 3);
        } else {
            try {
                int dayspermonth = Integer.parseInt(edit_department_days.getText());
                int hoursperday = Integer.parseInt(edit_department_hours.getText());
                double monthlyrate = Double.parseDouble(edit_department_rate.getText());
                String name = edit_department_name.getText();

                boolean exist = sqlDepartment.checkIfDepartmentNameExists(department.getDepartment_ID(), name);
                if (exist == true) {
                    callAlert("A department with same name already exists", 3);
                } else {
                    Department dept = new Department(department.getDepartment_ID(), name, monthlyrate, dayspermonth, hoursperday);

                    dept.setTime_In(Time.valueOf(spinner1.getEditor().getText() + ":00"));
                    dept.setTime_Out(Time.valueOf(spinner2.getEditor().getText() + ":00"));
                    dept.setBreak_Start(Time.valueOf(spinner3.getEditor().getText() + ":00"));
                    dept.setBreak_End(Time.valueOf(spinner4.getEditor().getText() + ":00"));

                    dept.setShift_Sunday(editshift_sunday.isSelected());
                    dept.setShift_Monday(editshift_monday.isSelected());
                    dept.setShift_Tuesday(editshift_tuesday.isSelected());
                    dept.setShift_Wednesday(editshift_wednesday.isSelected());
                    dept.setShift_Thursday(editshift_thursday.isSelected());
                    dept.setShift_Friday(editshift_friday.isSelected());
                    dept.setShift_Saturday(editshift_saturday.isSelected());
                    sqlDepartment.editDepartment(dept);
                    loadDepartment();
                }
            } catch (NumberFormatException o) {
                callAlert("Invalid Value/s", 3);
            } catch (Exception e) {
                callAlert("Invalid Value/s", 3);
            }
        }
    }

    private void loadDepartmentFields() {
        Department dept = sqlDepartment.getDepartment(new Department(department.getDepartment_ID()));

        edit_department_name.setText(dept.getDepartment_Name());
        edit_department_rate.setText(Double.toString(dept.getDepartment_MonthlyRate()));
        edit_department_days.setText(Integer.toString(dept.getDepartment_DaysPerMonth()));
        edit_department_hours.setText(Integer.toString(dept.getDepartment_HoursPerDay()));

        spinner1.getEditor().setText(dept.getTime_In().toString().substring(0, dept.getTime_In().toString().length() - 3));
        spinner2.getEditor().setText(dept.getTime_Out().toString().substring(0, dept.getTime_Out().toString().length() - 3));
        spinner3.getEditor().setText(dept.getBreak_Start().toString().substring(0, dept.getBreak_Start().toString().length() - 3));
        spinner4.getEditor().setText(dept.getBreak_End().toString().substring(0, dept.getBreak_End().toString().length() - 3));

        editshift_sunday.setSelected(dept.isShift_Sunday());
        editshift_monday.setSelected(dept.isShift_Monday());
        editshift_tuesday.setSelected(dept.isShift_Tuesday());
        editshift_wednesday.setSelected(dept.isShift_Wednesday());
        editshift_thursday.setSelected(dept.isShift_Thursday());
        editshift_friday.setSelected(dept.isShift_Friday());
        editshift_saturday.setSelected(dept.isShift_Saturday());

    }





    private void addSpinner() {
        spinner1.setLayoutX(157);
        spinner1.setLayoutY(142);

        spinner2.setLayoutX(422);
        spinner2.setLayoutY(142);
        spinner2.setDisable(true);

        spinner3.setLayoutX(157);
        spinner3.setLayoutY(175);

        spinner4.setLayoutX(422);
        spinner4.setLayoutY(175);

        spinner1.getEditor().setText("08:00");
        spinner2.getEditor().setText("17:00");
        spinner3.getEditor().setText("12:00");
        spinner4.getEditor().setText("13:00");

        spinner1.setPrefWidth(142);
        spinner2.setPrefWidth(142);
        spinner3.setPrefWidth(142);
        spinner4.setPrefWidth(142);

        main_pane.getChildren().addAll(spinner1, spinner2, spinner3, spinner4);
        addListener();
    }

    private void addListener() {
        edit_department_hours.textProperty().addListener((a, o, n) -> {
            updateTimeOutValue();
        });
        spinner1.valueProperty().addListener((a, o, n) -> {
            updateTimeOutValue();
        });
        spinner2.valueProperty().addListener((a, o, n) -> {
            updateTimeOutValue();
        });
        spinner3.valueProperty().addListener((a, o, n) -> {
            updateTimeOutValue();
        });
        spinner4.valueProperty().addListener((a, o, n) -> {
            updateTimeOutValue();
        });
    }

    private void updateTimeOutValue() {
        String timein_timeString = spinner1.getEditor().getText() + ":" + "00";
        String breaktimein_timeString = spinner3.getEditor().getText() + ":" + "00";
        String breaktimeout_timeString = spinner4.getEditor().getText() + ":" + "00";

        LocalTime timein = LocalTime.parse(timein_timeString, DateTimeFormatter.ISO_TIME);
        LocalTime breaktimein = LocalTime.parse(breaktimein_timeString, DateTimeFormatter.ISO_TIME);
        LocalTime breaktimeout = LocalTime.parse(breaktimeout_timeString, DateTimeFormatter.ISO_TIME);
        long totalbreakminutes = breaktimein.until(breaktimeout, ChronoUnit.MINUTES);
        LocalTime timeinwithbreak = timein.plusMinutes(totalbreakminutes);
//        long timetorender = department.getDepartment_HoursPerDay();
        long timetorender = Long.parseLong(edit_department_hours.getText());
        LocalTime totalout = timeinwithbreak.plusHours(timetorender);
        long timedifferencetimebreak = timein.until(breaktimein, ChronoUnit.HOURS);
        int timevalidation = timein.plusHours(timetorender).compareTo(breaktimein);

        if (totalbreakminutes <= 0 || timedifferencetimebreak <= 0 || timevalidation < 0) {
            spinner2.getEditor().setText("00:00");
            update_button.setDisable(true);
        } else {
            update_button.setDisable(false);
            spinner2.getEditor().setText(totalout.toString());
        }
    }
}
