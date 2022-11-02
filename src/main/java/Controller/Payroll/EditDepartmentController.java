package Controller.Payroll;

import Database.SQLDepartment;
import Models.Admin;
import Models.Department;
import Models.Employee;
import cw.payroll.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

import static Classes.CustomAlert.callAlert;

public class EditDepartmentController {

    @FXML
    private Button editdepartment_button_add;

    @FXML
    private Button editdepartment_button_cancel;

    @FXML
    private TextField editdepartment_dayspermonth;

    @FXML
    private TextField editdepartment_hoursperday;

    @FXML
    private TextField editdepartment_monthlyrate;

    @FXML
    private TextField editdepartment_name;

    @FXML
    private void editdepartment(ActionEvent event) {
        checkDepartmentIfValid();
    }

    @FXML
    private void cancel(ActionEvent event) {
        loadDepartment();
    }

    /****************************** FXML ENDS HERE ******************************/

    private Admin admin;
    private AnchorPane container;

    private Department department;

    private SQLDepartment sqlDepartment = new SQLDepartment();

    public void setRetrievedData(Admin admin, AnchorPane anchorPane) {
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
        if (editdepartment_name.getText() == null || editdepartment_name.getText().trim().equals("")) {
            callAlert("Invalid Department Name", 3);
        } else {
            try {
                int dayspermonth = Integer.parseInt(editdepartment_dayspermonth.getText());
                int hoursperday = Integer.parseInt(editdepartment_hoursperday.getText());
                double monthlyrate = Double.parseDouble(editdepartment_monthlyrate.getText());
                String name = editdepartment_name.getText();

                boolean exist = sqlDepartment.checkIfDepartmentNameExists(department.getDepartment_ID(), name);
                if (exist == true) {
                    callAlert("A department with same name already exists", 3);
                } else {
                    sqlDepartment.editDepartment(new Department(department.getDepartment_ID(), name, monthlyrate, dayspermonth, hoursperday));
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

        editdepartment_name.setText(dept.getDepartment_Name());
        editdepartment_monthlyrate.setText(Double.toString(dept.getDepartment_MonthlyRate()));
        editdepartment_dayspermonth.setText(Integer.toString(dept.getDepartment_DaysPerMonth()));
        editdepartment_hoursperday.setText(Integer.toString(dept.getDepartment_HoursPerDay()));
    }
}
