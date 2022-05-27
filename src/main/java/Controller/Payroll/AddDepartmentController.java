package Controller.Payroll;

import Models.Admin;
import cw.payroll.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

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
        loadDepartment();
    }

    @FXML
    private void cancel(ActionEvent event) {
        loadDepartment();
    }

    /****************************** FXML ENDS HERE ******************************/

    private Admin admin;
    private AnchorPane container;
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
}
