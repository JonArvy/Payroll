package Controller.Attendance;

import Classes.Converters;
import Classes.TimeSpinner;
import Database.SQLAttendance;
import Database.SQLEmployee;
import Models.Admin;
import Models.Attendance;
import Models.Employee;
import cw.payroll.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;

import static Classes.CustomAlert.callAlert;


public class AddAttendanceController {
    @FXML
    private Pane main_pane;

    @FXML
    private DatePicker date;

    @FXML
    private TextField department;

    @FXML
    private TextField emp_id;

    @FXML
    private ComboBox<Employee> full_name;

    @FXML
    void cancel(ActionEvent event) {
        loadAttendance();
    }

    @FXML
    private void initialize() {
        initializeComponents();
    }

    @FXML
    private void command(ActionEvent event) {
//        vBox.getChildren().forEach(node -> {
//            if (node instanceof CheckBox) {
//                CheckBox checkBox = (CheckBox) node;
//                if (checkBox.isSelected()) {
//                    System.out.println(checkBox.getText());
//                }
//            }
//        });
        addAttendance();
    }


    /****************************** FXML ENDS HERE ******************************/

    private Admin admin;
    private AnchorPane container;

    TimeSpinner spinner = new TimeSpinner();
    TimeSpinner spinner2 = new TimeSpinner();

    SQLEmployee sqlEmployee = new SQLEmployee();

    SQLAttendance sqlAttendance = new SQLAttendance();

    Converters converters = new Converters();

    ObservableList<Employee> employees = FXCollections.observableArrayList();
//    VBox vBox = new VBox();

    public void setRetrievedData(Admin admin, AnchorPane anchorPane) {
        this.admin = admin;
        this.container = anchorPane;



//        List<Button> arr = new ArrayList<Button>();
//        arr.add(new Button("1"));
//        arr.add(new Button("2"));
//        arr.add(new Button("3"));

//        String[] name = {"Admin", "User", "New User", "ad", "gh", "jyg"};
//
//        for (int i = 0; i < name.length; i++) {
//            CheckBox checkBox = new CheckBox(name[i]);
//            vBox.getChildren().add(checkBox);
//
//
//        }

//        vBox.getChildren().addAll(arr);


//        main_pane.getChildren().add(vBox);
    }

    public void setDate(LocalDate localDate) {
        date.setValue(localDate);
    }

    private void addSpinner() {
        spinner.setLayoutX(261);
        spinner.setLayoutY(204);

        spinner2.setLayoutX(261);
        spinner2.setLayoutY(238);

        spinner.setPrefWidth(142);
        spinner2.setPrefWidth(142);

//        spinner.setPrefWidth();

        spinner.getEditor().setText("08:00");
        spinner2.getEditor().setText("17:00");

//        main_pane.setVisible(true);
        main_pane.getChildren().add(spinner);
        main_pane.getChildren().add(spinner2);

    }

    private void initializeComponents() {
        addSpinner();
        employees.clear();
        employees = sqlEmployee.getAllEmployees(true);
        FXCollections.sort(employees, Comparator.comparing(Employee::getFull_Name));
        full_name.setItems(employees);
        full_name.setConverter(converters.employeeStringConverter(employees));



        full_name.valueProperty().addListener((a, o, n) -> {
            if (n != null) {
                emp_id.setText(String.valueOf(n.getEmployee_ID()));
                department.setText(String.valueOf(n.getDepartment_Name()));
            }
        });

        full_name.getSelectionModel().select(0);
    }

    private void addAttendance() {
        int id = full_name.valueProperty().getValue().getEmployee_ID();
        Date datevalue = Date.valueOf(date.getValue());
        Time in = Time.valueOf(spinner.getEditor().getText() + ":00");
        Time out = Time.valueOf(spinner2.getEditor().getText() + ":00");
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss a");
        Attendance attd = new Attendance(id, datevalue, in, out);
        boolean success = sqlAttendance.checkIfAttendanceExist(attd);
        if (success) {
            sqlAttendance.registerAttendance(attd);
            loadAttendance();
        } else {
            callAlert("Error!", "Attendance already exist!");
        }
    }

    private void loadAttendance() {
        DailyAttendanceController controller;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UI/Attendance/DailyAttendance.fxml"));
            fxmlLoader.load();
            controller = fxmlLoader.getController();
            controller.setRetrievedData(admin, container);
            controller.setDateInfo(Date.valueOf(date.getValue()));
            AnchorPane anchorPane = fxmlLoader.getRoot();
            container.getChildren().add(anchorPane);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
