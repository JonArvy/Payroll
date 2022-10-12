package Controller.Attendance;

import Classes.Converters;
import Classes.TimeSpinner;
import Database.SQLEmployee;
import Models.Admin;
import Models.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.StringConverter;

import java.util.Comparator;


public class AddAttendanceController {
    @FXML
    private Button button_command;
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
    private TextField position;

    @FXML
    void cancel(ActionEvent event) {

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
    }


    /****************************** FXML ENDS HERE ******************************/

    private Admin admin;
    private AnchorPane container;

    TimeSpinner spinner = new TimeSpinner();
    TimeSpinner spinner2 = new TimeSpinner();

    SQLEmployee sqlEmployee = new SQLEmployee();

    Converters converters = new Converters();

    ObservableList<Employee> employees = FXCollections.observableArrayList();
//    VBox vBox = new VBox();

    public void setRetrievedData(Admin admin, AnchorPane anchorPane) {
        this.admin = admin;
        this.container = anchorPane;

        spinner.setLayoutX(261);
        spinner.setLayoutY(238);

        spinner2.setLayoutX(261);
        spinner2.setLayoutY(273);

        spinner.setPrefWidth(142);
        spinner2.setPrefWidth(142);

//        spinner.setPrefWidth();

        spinner.getEditor().setText("08:00");
        spinner2.getEditor().setText("17:00");

        main_pane.setVisible(true);

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
        main_pane.getChildren().add(spinner);
        main_pane.getChildren().add(spinner2);


    }

    private void initializeComponents() {
        employees.clear();
        employees = sqlEmployee.getAllEmployees();
        FXCollections.sort(employees, Comparator.comparing(Employee::getFull_Name));
        full_name.setItems(employees);
        full_name.setConverter(converters.employeeStringConverter(employees));



        full_name.valueProperty().addListener((a, o, n) -> {
            if (n != null) {
                emp_id.setText(String.valueOf(n.getEmployee_ID()));
                department.setText(String.valueOf(n.getDepartment_Name()));
                position.setText(n.getPosition());
            }
        });

        full_name.getSelectionModel().select(0);
    }
}
