package Controller.Payroll;

import Controller.Additional.AddBonusController;
import Database.SQLDepartment;
import Database.SQLEmployee;
import Models.Admin;
import Models.Department;
import Models.Employee;
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

public class PayslipController {

    @FXML
    private TableColumn<Employee, Void> main_payslip_column_action;

    @FXML
    private TableColumn<Employee, String> main_payslip_column_department;

    @FXML
    private TableColumn<Employee, Integer> main_payslip_column_empid;

    @FXML
    private TableColumn<Employee, String> main_payslip_column_fullname;

    @FXML
    private TableColumn<Employee, String> main_payslip_column_position;

    @FXML
    private TableView main_payslip_tableview;

    @FXML
    private void initialize() {
        showPayslipList();
    }

    /****************************** FXML ENDS HERE ******************************/

    private Admin admin;
    private AnchorPane container;

    private ObservableList<Employee> employeeList = FXCollections.observableArrayList();

    private SQLEmployee sqlEmployee = new SQLEmployee();

    public void setRetrievedData(Admin admin, AnchorPane anchorPane) {
        this.admin = admin;
        this.container = anchorPane;
    }

    private void loadViewPayslip(Employee employee) {
        ViewPayslipController controller;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UI/Payroll/ViewPayslip.fxml"));
            fxmlLoader.load();

            controller = fxmlLoader.getController();
            controller.setRetrievedData(admin, container);
            controller.setEmployee(employee);

            AnchorPane anchorPane = fxmlLoader.getRoot();
//            container.getChildren().clear();
            container.getChildren().add(anchorPane);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void showPayslipList() {
        employeeList.clear();
        employeeList = sqlEmployee.getAllEmployeePayslip();

        main_payslip_column_empid.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("Employee_ID"));
        main_payslip_column_fullname.setCellValueFactory(new PropertyValueFactory<Employee, String>("Full_Name"));
        main_payslip_column_department.setCellValueFactory(new PropertyValueFactory<Employee, String>("Department_Name"));
        main_payslip_column_position.setCellValueFactory(new PropertyValueFactory<Employee, String>("Position"));

        Callback<TableColumn<Employee, Void>, TableCell<Employee, Void>> cellFactory = new Callback<TableColumn<Employee, Void>, TableCell<Employee, Void>>() {
            @Override
            public TableCell<Employee, Void> call(final TableColumn<Employee, Void> param) {
                final TableCell<Employee, Void> cell = new TableCell<Employee, Void>() {
                    private final Button btn = new Button("View");

                    {
                        String style = "-fx-background-color: #c3c4c4, linear-gradient(#d6d6d6 50%, white 100%)," +
                                "radial-gradient(center 50% -40%, radius 200%, #e6e6e6 45%, rgba(230,230,230,0) 50%); " +
                                "-fx-background-radius: 30; " +
                                "-fx-background-insets: 0,1,1; " +
                                "-fx-text-fill: black; " +
                                "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.6), 3, 0.0, 0, 1);";

                        btn.setStyle(style);
                        btn.setOnAction((ActionEvent event) -> {
                            Employee emp = getTableView().getItems().get(getIndex());
                            loadViewPayslip(emp);
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
        main_payslip_column_action.setCellFactory(cellFactory);

        main_payslip_tableview.setItems(employeeList);
    }
}
