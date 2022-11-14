package Controller.Startup;

import Models.Admin;
import Models.Department;
import Models.Employee;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class RegisterController {

    @FXML
    private Label Confirm;

    @FXML
    private PasswordField confirm_password_pf;

    @FXML
    private TextField confirm_password_tf;

    @FXML
    private TextField emp_id;

    @FXML
    private PasswordField password_pf;

    @FXML
    private TextField password_tf;


    /****************************** FXML ENDS HERE ******************************/
    private AnchorPane container;

    private Department department;
    private Employee employee;
    private Admin admin;

    public void setDepartment(Department department) {
        this.department = department;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void setAdmin(Admin admin) {
        if (admin != null) {
            this.admin = admin;
        }
    }

    public void setRetrievedData(AnchorPane anchorPane) {
        this.container = anchorPane;
    }


}
