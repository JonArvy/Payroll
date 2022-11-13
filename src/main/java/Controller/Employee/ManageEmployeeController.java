package Controller.Employee;

import Database.SQLEmployee;
import Models.Admin;
import Models.Employee;
import cw.payroll.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
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
    private TableColumn<Employee, String> manageemployee_column_status;

    @FXML
    private TextField manageemployee_searchbar;

    @FXML
    private TableView manageemployee_tableview;

    @FXML
    private void search(ActionEvent event) {

    }

    @FXML
    private void addEmployee() {
        loadAddEmployee();
    }

    @FXML
    private void initialize() {
        manageemployee_checkbox_hideinactive.selectedProperty().addListener((a, o, n) -> {
            showEmployeeList();
        });
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
//
//    private void loadAddEmployee() {
//        try {
//            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UI/Employee/AddEmployee.fxml"));
//            fxmlLoader.load();
//
//            AnchorPane anchorPane = fxmlLoader.getRoot();
//            container.getChildren().clear();
//            container.getChildren().add(anchorPane);
//
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//    }


    private void searchItem() {


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
        employeeList = sqlEmployee.getAllEmployees(manageemployee_checkbox_hideinactive.isSelected());

        manageemployee_column_empid.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("Employee_ID"));
        manageemployee_column_lname.setCellValueFactory(new PropertyValueFactory<Employee, String>("Last_Name"));
        manageemployee_column_fname.setCellValueFactory(new PropertyValueFactory<Employee, String>("First_Name"));
        manageemployee_column_empstatus.setCellValueFactory(new PropertyValueFactory<Employee, String>("Employment_Status"));
        manageemployee_column_department.setCellValueFactory(new PropertyValueFactory<Employee, String>("Department_Name"));
        manageemployee_column_status.setCellValueFactory(new PropertyValueFactory<Employee, String>("Active_Message"));

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


        setFilters();
//        manageemployee_tableview.setItems(employeeList);
    }

    private void setFilters() {
        FilteredList<Employee> filteredList = new FilteredList<>(employeeList, p -> true);

        manageemployee_searchbar.textProperty().addListener((a, o, n) -> {
            filteredList.setPredicate(myObject -> {
                if (n == null || n.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = n.toLowerCase();

                if (String.valueOf(myObject.getEmployee_ID()).contains(lowerCaseFilter)) {
                    return true;
                } else if (String.valueOf(myObject.getFirst_Name()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (String.valueOf(myObject.getLast_Name()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (String.valueOf(myObject.getEmployment_Status()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (String.valueOf(myObject.getDepartment_Name()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });

        SortedList<Employee> sortedData = new SortedList<>(filteredList);

        sortedData.comparatorProperty().bind(manageemployee_tableview.comparatorProperty());
        manageemployee_tableview.setItems(sortedData);
    }

    private void loadAddEmployee() {
        AddEmployeeController controller;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UI/Employee/AddEmployee.fxml"));
//            fxmlLoader.load();

            Node n = (Node) fxmlLoader.load();
            AnchorPane.setTopAnchor(n, 0.0);
            AnchorPane.setBottomAnchor(n, 0.0);
            AnchorPane.setLeftAnchor(n, 0.0);
            AnchorPane.setRightAnchor(n, 0.0);

            controller = fxmlLoader.getController();
            controller.setRetrievedData(admin, container);

//            AnchorPane anchorPane = fxmlLoader.getRoot();
            container.getChildren().clear();
            container.getChildren().add(n);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
