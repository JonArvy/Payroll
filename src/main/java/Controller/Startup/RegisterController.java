package Controller.Startup;

import Database.SQLAdmin;
import Database.SQLDepartment;
import Database.SQLEmployee;
import Database.SQLLogs;
import Models.Admin;
import Models.Department;
import Models.Employee;
import cw.payroll.Main;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

import static Classes.CustomAlert.callAlert;

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

    @FXML
    private Button show_pass_1;

    @FXML
    private Button show_pass_2;

    @FXML
    void createAccount(ActionEvent event) {
        registerFirstAccount();
    }

    @FXML
    void goBack(ActionEvent event) {
        goBack();
    }

    @FXML
    private void initialize() {
        password_tf.textProperty().bindBidirectional(password_pf.textProperty());
        show_pass_1.setOnMousePressed((event) -> {
            password_tf.toFront();
            show_pass_1.toFront();
        });

        show_pass_1.setOnMouseReleased((event) -> {
            password_pf.toFront();
            show_pass_1.toFront();
        });


        confirm_password_tf.textProperty().bindBidirectional(confirm_password_pf.textProperty());
        show_pass_2.setOnMousePressed((event) -> {
            confirm_password_tf.toFront();
            show_pass_2.toFront();
        });

        show_pass_2.setOnMouseReleased((event) -> {
            confirm_password_pf.toFront();
            show_pass_2.toFront();
        });
    }

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
        emp_id.setText(String.valueOf(employee.getEmployee_ID()));
    }

    public void setAdmin(Admin admin) {
        if (admin != null) {
            this.admin = admin;
        }
    }

    public void setRetrievedData(AnchorPane anchorPane) {
        this.container = anchorPane;
    }

    private void goBack() {
        AddFirstDepartmentController controller;

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UI/Startup/AddFirstDepartment.fxml"));
            fxmlLoader.load();

            controller = fxmlLoader.getController();
            controller.setRetrievedData(container);

            controller.setEmployee(employee);
            controller.setDepartment(department);
            controller.setAdmin(admin);

            AnchorPane anchorPane = fxmlLoader.getRoot();
            container.getChildren().clear();
            container.getChildren().add(anchorPane);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void registerFirstAccount() {
        if (password_pf.getText().trim().equals("")) {
            callAlert("Password cannot be empty", 3);
        } else {
            if (password_pf.getText().equals(confirm_password_pf.getText())) {

                this.admin = new Admin();

                admin.setEmployee_ID(employee.getEmployee_ID());
                admin.setAdmin_Password(password_pf.getText());
                admin.setAdmin_Grantor(0);
                admin.setAdmin_Disabler(0);


                createTheFirstAccount();

            } else {
                callAlert("Passwords do not match!", 3);
//                Confirm.setText("Passwords do not match!");
            }
        }
    }

    private void createTheFirstAccount() {
        SQLEmployee sqlEmployee = new SQLEmployee();
        SQLDepartment sqlDepartment = new SQLDepartment();
        SQLAdmin sqlAdmin = new SQLAdmin();

        sqlEmployee.addEmployee(employee);
        sqlDepartment.editDepartment(department);
        sqlAdmin.addAdmin(admin);

        SQLLogs sqlLogs = new SQLLogs();
        sqlLogs.createTriggers();


        Stage stage = (Stage) container.getScene().getWindow();
        stage.close();
        Parent root;

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UI/Login.fxml"));
            root = fxmlLoader.load();
            Stage stage2 = new Stage();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(Main.class.getResource("/cw/payroll/css/Style.css").toExternalForm());
            stage2.setScene(scene);
            stage2.initStyle(StageStyle.UNDECORATED);
            stage2.setResizable(false);
            stage2.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
