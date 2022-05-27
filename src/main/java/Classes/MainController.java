package Classes;

import Database.*;
import Models.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.util.Callback;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
        public MainController() {

        }



        @FXML
        private Button main_addemployee_button;

        @FXML
        private Pane main_addemployee_panel_1;

        @FXML
        private Pane main_addemployee_panel_2;

        @FXML
        private Button main_attendancereport_button;

        @FXML
        private Pane main_attendancereport_panel_1;

        @FXML
        private Button main_dailyattendance_button;

        @FXML
        private Pane main_dailyattendance_panel_1;

        @FXML
        private Button main_holidaylist_button;

        @FXML
        private Button main_logout_button;

        @FXML
        private Button main_manageemployee_button;

        @FXML
        private Pane main_manageemployee_panel_1;

        @FXML
        private Button main_payrollsummary_button;

        @FXML
        private Button main_payslip_button;

        @FXML
        private Pane main_payslip_panel_1;

        @FXML
        private Button main_rates_button;

        @FXML
        private Pane main_rates_panel_1;

        @FXML
        private Pane main_rates_panel_2;

        @FXML
        private Pane main_holiday_panel_1;

        @FXML
        private Button addemployee_next_button, addemployee_back_button;

        @FXML
        private TableView manageemployee_table;

        @FXML
        private TableColumn<Employee, Integer> manageemployee_column_empid;

        @FXML
        private TableColumn<Employee, String> manageemployee_column_lastname;

        @FXML
        private TableColumn<Employee, String> manageemployee_column_fname;

        @FXML
        private TableColumn<Employee, String> manageemployee_column_empstatus;

        @FXML
        private TableColumn<Employee, String> manageemployee_column_dept;

        @FXML
        private TableColumn<Employee, Integer> manageemployee_column_action;


        private TableColumn<Employee, Void> colBtn;


        private ObservableList<Employee> employeeList = FXCollections.observableArrayList();
        private SQLExecution sqlExecution = new SQLExecution();
        private SQLAdmin sqlAdmin = new SQLAdmin();
        private SQLAttendance sqlAttendance = new SQLAttendance();
        private SQLBonus sqlBonus = new SQLBonus();
        private SQLDepartment sqlDepartment = new SQLDepartment();
        private SQLEmployee sqlEmployee = new SQLEmployee();
        private SQLHoliday sqlHoliday = new SQLHoliday();
        private SQLNoticeboard sqlNoticeboard = new SQLNoticeboard();
        private SQLShift sqlShift = new SQLShift();

        @FXML
        protected void clickNavigation_Choices(ActionEvent event) {
                System.out.println(event.getSource().toString());
                //Add Employee Button
                if (event.getSource() == main_addemployee_button || event.getSource() == addemployee_back_button) {
                        main_addemployee_panel_1.toFront();

                //Next add employee panel
                } else if (event.getSource() == addemployee_next_button) {
                        main_addemployee_panel_2.toFront();

                //Manage Employee Button
                } else if (event.getSource() == main_manageemployee_button) {
                        main_manageemployee_panel_1.toFront();
                        loadManageEmployeeTable();

                //Daily Attendance Button
                } else if (event.getSource() == main_dailyattendance_button) {
                        main_dailyattendance_panel_1.toFront();

                //Attendance Report Button
                } else if (event.getSource() == main_attendancereport_button) {
                        main_attendancereport_panel_1.toFront();

                //Payslip Button
                } else if (event.getSource() == main_payslip_button) {
                        main_payslip_panel_1.toFront();

                //Rates Button
                } else if (event.getSource() == main_rates_button) {
                        main_rates_panel_1.toFront();

                //Holiday Button
                } else if (event.getSource() == main_holidaylist_button) {
                        main_holiday_panel_1.toFront();

                //Logout Button
                } else if (event.getSource() == main_logout_button) {
                        System.exit(0);
                }
        }

        @FXML
        private void loadManageEmployeeTable() {
                employeeList.clear();
                employeeList = sqlEmployee.getAllEmployeesInformation();

                manageemployee_column_empid.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("Employee_ID"));
                manageemployee_column_lastname.setCellValueFactory(new PropertyValueFactory<Employee, String>("Last_Name"));
                manageemployee_column_fname.setCellValueFactory(new PropertyValueFactory<Employee, String>("First_Name"));
                manageemployee_column_empstatus.setCellValueFactory(new PropertyValueFactory<Employee, String>("Employment_Status"));
                manageemployee_column_dept.setCellValueFactory(new PropertyValueFactory<Employee, String>("Department"));

                manageemployee_column_action.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("Employee_ID"));

                if (colBtn == null) {
                        addButtonToTable();
                }

                manageemployee_table.setItems(employeeList);
        }

        public void addButtonToTable() {
                colBtn = new TableColumn("Action");

                Callback<TableColumn<Employee, Void>, TableCell<Employee, Void>> cellFactory = new Callback<TableColumn<Employee, Void>, TableCell<Employee, Void>>() {
                        @Override
                        public TableCell<Employee, Void> call(final TableColumn<Employee, Void> param) {
                                final TableCell<Employee, Void> cell = new TableCell<Employee, Void>() {
                                        private final Button btn = new Button("Edit");
                                        private final Button btn2 = new Button("Delete");

                                        {
                                                btn.setOnAction((ActionEvent event) -> {
                                                        Employee emp = getTableView().getItems().get(getIndex());
                                                        System.out.println("Edit" + emp.getFirst_Name() + " " + emp.getLast_Name());
                                                });

                                                btn2.setOnAction((ActionEvent event) -> {
                                                        Employee emp = getTableView().getItems().get(getIndex());
                                                        System.out.println("Delete" + emp.getFirst_Name() + " " + emp.getLast_Name());
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

                colBtn.setCellFactory(cellFactory);
                manageemployee_table.getColumns().add(colBtn);
        }


        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
                loadOnStartUp();

        }


        private void loadOnStartUp() {
                //Load NoticeBoard
        }

}