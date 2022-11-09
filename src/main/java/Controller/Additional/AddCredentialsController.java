package Controller.Additional;

import Database.SQLAdmin;
import Database.SQLEmployee;
import Models.Admin;
import Models.Employee;
import cw.payroll.Main;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

import static Classes.CustomAlert.callAlert;

public class AddCredentialsController {

    @FXML
    private TextField employee_id;

    @FXML
    private TextField employee_name;

    @FXML
    private PasswordField password_confirm_passwordfield;

    @FXML
    private TextField password_confirm_textfield;

    @FXML
    private PasswordField password_passwordfield;

    @FXML
    private TextField password_textfield;

    @FXML
    private CheckBox show_password;

    @FXML
    private Button create_button;

    @FXML
    void cancel(ActionEvent event) {

    }

    @FXML
    void create(ActionEvent event) {
        addAdmin(sqlEmployee.getEmployee(new Employee(Integer.parseInt(employee_id.getText()))));
    }

    @FXML
    void search(ActionEvent event) {
        searchEmployee();
    }

    @FXML
    private void initialize() {
        password_textfield.managedProperty().bind(show_password.selectedProperty());
        password_textfield.visibleProperty().bind(show_password.selectedProperty());

        password_passwordfield.managedProperty().bind(show_password.selectedProperty().not());
        password_passwordfield.visibleProperty().bind(show_password.selectedProperty().not());

        password_textfield.textProperty().bindBidirectional(password_passwordfield.textProperty());


        password_confirm_textfield.managedProperty().bind(show_password.selectedProperty());
        password_confirm_textfield.visibleProperty().bind(show_password.selectedProperty());

        password_confirm_passwordfield.managedProperty().bind(show_password.selectedProperty().not());
        password_confirm_passwordfield.visibleProperty().bind(show_password.selectedProperty().not());

        password_confirm_textfield.textProperty().bindBidirectional(password_confirm_passwordfield.textProperty());


        employee_id.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    employee_id.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        employee_id.textProperty().addListener((a, o, n) -> {
            create_button.setDisable(true);
            employee_name.setText("");
        });
    }


    /****************************** FXML ENDS HERE ******************************/

    private Admin admin;
    private AnchorPane container;

    private SQLEmployee sqlEmployee = new SQLEmployee();
    private SQLAdmin sqlAdmin = new SQLAdmin();

    public void setRetrievedData(Admin admin, AnchorPane anchorPane) {
        this.admin = admin;
        this.container = anchorPane;
    }

    private void searchEmployee() {
        if (!sqlEmployee.checkIfEmployeeIDExists(Integer.parseInt(employee_id.getText()))) {
            if (sqlEmployee.checkIfEmployeeIsActive(Integer.parseInt(employee_id.getText()))) {
                if (!sqlAdmin.checkIfEmployeeIsAdmin(new Employee(Integer.parseInt(employee_id.getText())))) {
                    Employee emp = sqlEmployee.getEmployee(new Employee(Integer.parseInt(employee_id.getText())));
                    employee_name.setText(emp.getFull_Name());
                    create_button.setDisable(false);
                } else {
                    callAlert("Employee is already an admin", 3);
                }
            } else {
                callAlert("Employee is not active", 3);
            }
        } else {
            callAlert("Employee does not exist.", 3);
        }
    }

    private void addAdmin(Employee employee) {
        if (password_textfield.getText().equals(password_confirm_textfield.getText()) && !password_textfield.getText().equals("")) {
            Admin admin = new Admin(employee.getEmployee_ID(), password_textfield.getText());
            sqlAdmin.addAdmin(admin);
            loadCredentials();
        } else {
            callAlert("Passwords do not match", 3);
        }

    }

    private void loadCredentials() {
        CredentialsController controller;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UI/Additional/Credentials.fxml"));

            Node n = (Node) fxmlLoader.load();
            AnchorPane.setTopAnchor(n, 0.0);
            AnchorPane.setBottomAnchor(n, 0.0);
            AnchorPane.setLeftAnchor(n, 0.0);
            AnchorPane.setRightAnchor(n, 0.0);

            controller = fxmlLoader.getController();
            controller.setRetrievedData(admin, container);
            container.getChildren().clear();
            container.getChildren().add(n);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
