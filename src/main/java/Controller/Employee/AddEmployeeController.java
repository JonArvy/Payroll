package Controller.Employee;

import Models.Admin;
import cw.payroll.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class AddEmployeeController {

    @FXML
    private Button addemployee_button_next;

    @FXML
    private TextArea main_addemployee_address;

    @FXML
    private DatePicker main_addemployee_bdate;

    @FXML
    private TextArea main_addemployee_contactaddress;

    @FXML
    private TextField main_addemployee_contactname;

    @FXML
    private TextField main_addemployee_contactnumber;

    @FXML
    private TextField main_addemployee_contactrelationship;

    @FXML
    private ComboBox<?> main_addemployee_dept;

    @FXML
    private TextField main_addemployee_empid;

    @FXML
    private ComboBox<?> main_addemployee_empstatus;

    @FXML
    private TextField main_addemployee_ext;

    @FXML
    private TextField main_addemployee_fname;

    @FXML
    private ComboBox<?> main_addemployee_gender;

    @FXML
    private TextField main_addemployee_lname;

    @FXML
    private ComboBox<?> main_addemployee_maritalstatus;

    @FXML
    private TextField main_addemployee_mname;

    @FXML
    private TextField main_addemployee_nationality;

    @FXML
    private TextField main_addemployee_number;

    @FXML
    private TextField main_addemployee_position;

    @FXML
    private ComboBox<?> main_addemployee_status;

    @FXML
    private void nextPage(ActionEvent event) {
        loadAddEmployeeBiometrics();
    }

    /****************************** FXML ENDS HERE ******************************/

    private Admin admin;
    private AnchorPane container;

    public void setRetrievedData(Admin admin, AnchorPane anchorPane) {
        this.admin = admin;
        this.container = anchorPane;
    }

    private void loadAddEmployeeBiometrics() {
        AddEmployeeBiometricsController controller;

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UI/Employee/AddEmployeeBiometrics.fxml"));
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
