package Controller.Additional;

import Database.SQLAdmin;
import Database.SQLEmployee;
import Database.SQLNewAdmin;
import Models.Admin;
import Models.Employee;
import Models.NewAdmin;
import cw.payroll.Main;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

import static Classes.CustomAlert.callAlert;

public class AddCredentialsController {

    @FXML
    private Button create_button;

    @FXML
    private TextField name;

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
    private ComboBox<String> superadmin;

    @FXML
    private TextField username;

    @FXML
    void cancel(ActionEvent event) {
        loadCredentials();
    }

    @FXML
    void create(ActionEvent event) {
        addAdmin();
    }

    @FXML
    private void initialize() {
        superadmin.getItems().addAll("Admin", "Super Admin");
        superadmin.getSelectionModel().select(0);

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

    }


    /****************************** FXML ENDS HERE ******************************/

    private NewAdmin admin;
    private AnchorPane container;

    private SQLEmployee sqlEmployee = new SQLEmployee();
    private SQLNewAdmin sqlAdmin = new SQLNewAdmin();

    public void setRetrievedData(NewAdmin admin, AnchorPane anchorPane) {
        this.admin = admin;
        this.container = anchorPane;
    }

    private void addAdmin() {
        if (sqlAdmin.checkIfAlreadyAdmin(new NewAdmin(username.getText()))) {
            callAlert("Username already exists", 3);
            return;
        }
        if (username.getText().isEmpty() || password_passwordfield.getText().isEmpty() || password_confirm_passwordfield.getText().isEmpty() || name.getText().isEmpty()) {
            callAlert("Please fill in all fields", 3);
            return;
        }
        if (password_textfield.getText().equals(password_confirm_textfield.getText()) && !password_textfield.getText().equals("")) {
            NewAdmin admin = new NewAdmin();

            admin.setUsername(username.getText());
            admin.setName(name.getText());
            admin.setPassword(password_textfield.getText());

            admin.setGrantor(this.admin.getUsername());
//            admin.setDisabler();
            admin.setUsingTheSystem(false);
            admin.setSuperAdmin(superadmin.getSelectionModel().getSelectedIndex() == 1);

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
