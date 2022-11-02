package Controller.Payroll;

import Database.SQLDepartment;
import Models.Admin;
import Models.Bonus;
import Models.Department;
import cw.payroll.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.Date;

import static Classes.CustomAlert.callAlert;

public class AddDepartmentController {

    @FXML
    private Button adddepartment_button_add;

    @FXML
    private Button adddepartment_button_cancel;

    @FXML
    private TextField adddepartment_dayspermonth;

    @FXML
    private TextField adddepartment_hoursperday;

    @FXML
    private TextField adddepartment_monthlyrate;

    @FXML
    private TextField adddepartment_name;

    @FXML
    private void addDepartment(ActionEvent event) {
        checkDepartmentIfValid();
    }

    @FXML
    private void cancel(ActionEvent event) {
        loadDepartment();
    }

    /****************************** FXML ENDS HERE ******************************/

    private Admin admin;
    private AnchorPane container;

    private SQLDepartment sqlDepartment = new SQLDepartment();

    public void setRetrievedData(Admin admin, AnchorPane anchorPane) {
        this.admin = admin;
        this.container = anchorPane;
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
        if (adddepartment_name.getText() == null || adddepartment_name.getText().trim().equals("")) {
            callAlert("Invalid Department Name", 3);
        } else {
            try {
                int dayspermonth = Integer.parseInt(adddepartment_dayspermonth.getText());
                int hoursperday = Integer.parseInt(adddepartment_hoursperday.getText());
                double monthlyrate = Double.parseDouble(adddepartment_monthlyrate.getText());
                String name = adddepartment_name.getText();

                boolean exist = sqlDepartment.checkIfDepartmentNameExists(name);
                if (exist == true) {
                    callAlert("A department with same name already exists", 3);
                } else {
                    sqlDepartment.addDepartment(new Department(name, monthlyrate, dayspermonth, hoursperday));
                    loadDepartment();
                }
            } catch (NumberFormatException o) {
                callAlert("Invalid Value/s", 3);
            } catch (Exception e) {
                callAlert("Invalid Value/s", 3);
            }
        }
    }
}
