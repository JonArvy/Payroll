package Controller.Employee;

import Database.SQLEmployee;
import Models.Admin;
import Models.Employee;
import cw.payroll.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

import java.io.IOException;

public class ManageEmployeeController {

    @FXML
    private CheckBox manageemployee_checkbox_hideinactive;

    @FXML
    private TableColumn<Employee, Void> manageemployee_column_action;

    @FXML
    private TableColumn<Employee, String> manageemployee_column_department;

    @FXML
    private TableColumn<Employee, Integer> manageemployee_column_empid;

    @FXML
    private TableColumn<Employee, String> manageemployee_column_empstatus;

    @FXML
    private TableColumn<Employee, String> manageemployee_column_fname;

    @FXML
    private TableColumn<Employee, String> manageemployee_column_lname;

    @FXML
    private TableColumn<Employee, Boolean> manageemployee_column_status;

    @FXML
    private TextField manageemployee_searchbar;

    @FXML
    private TableView manageemployee_tableview;

    @FXML
    private void search(ActionEvent event) {

    }

    @FXML
    private void initialize() {
        showEmployeeList();
    }

    /****************************** FXML ENDS HERE ******************************/
    private Admin admin;
    private AnchorPane container;

    private SQLEmployee sqlEmployee = new SQLEmployee();
    private ObservableList<Employee> employeeList = FXCollections.observableArrayList();

    public void setRetrievedData(Admin admin, AnchorPane anchorPane) {
        this.admin = admin;
        this.container = anchorPane;
    }

    private void loadAddEmployee() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UI/Employee/AddEmployee.fxml"));
            fxmlLoader.load();

            AnchorPane anchorPane = fxmlLoader.getRoot();
            container.getChildren().clear();
            container.getChildren().add(anchorPane);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void loadEditEmployee(Employee employee) {
        EditEmployeeController controller;
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UI/Employee/EditEmployee.fxml"));
            fxmlLoader.load();

            controller = fxmlLoader.getController();
            controller.setRetrievedData(admin, container);

            controller.setEmployee(employee);

            AnchorPane anchorPane = fxmlLoader.getRoot();
            container.getChildren().clear();
            container.getChildren().add(anchorPane);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void showEmployeeList() {
        employeeList.clear();
        employeeList = sqlEmployee.getAllEmployees();

        manageemployee_column_empid.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("Employee_ID"));
        manageemployee_column_lname.setCellValueFactory(new PropertyValueFactory<Employee, String>("Last_Name"));
        manageemployee_column_fname.setCellValueFactory(new PropertyValueFactory<Employee, String>("First_Name"));
        manageemployee_column_empstatus.setCellValueFactory(new PropertyValueFactory<Employee, String>("Employment_Status"));
        manageemployee_column_department.setCellValueFactory(new PropertyValueFactory<Employee, String>("Department_Name"));
        manageemployee_column_status.setCellValueFactory(new PropertyValueFactory<Employee, Boolean>("Active"));

        Callback<TableColumn<Employee, Void>, TableCell<Employee, Void>> cellFactory = new Callback<TableColumn<Employee, Void>, TableCell<Employee, Void>>() {
            @Override
            public TableCell<Employee, Void> call(final TableColumn<Employee, Void> param) {
                final TableCell<Employee, Void> cell = new TableCell<Employee, Void>() {
                    private final Button btn = new Button("View");
                    private final Button btn2 = new Button("Edit");

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
                            Employee emp = getTableView().getItems().get(getIndex());
                            System.out.println("Edit" + emp.getFirst_Name() + " " + emp.getLast_Name());
                        });

                        btn2.setStyle(style);
                        btn2.setOnAction((ActionEvent event) -> {
                            Employee emp = getTableView().getItems().get(getIndex());
                            loadEditEmployee(emp);
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
        manageemployee_column_action.setCellFactory(cellFactory);

        manageemployee_tableview.setItems(employeeList);
    }
}