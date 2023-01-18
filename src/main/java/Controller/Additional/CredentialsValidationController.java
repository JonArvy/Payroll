package Controller.Additional;

import Database.SQLAdmin;
import Database.SQLBonus;
import Database.SQLNewAdmin;
import Models.Admin;
import Models.Bonus;
import Models.NewAdmin;
import cw.payroll.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

public class CredentialsValidationController {

    @FXML
    private TextField emp_id;

    @FXML
    private TextField pass_unhash;

    @FXML
    private Button login;

    @FXML
    private PasswordField password;

    @FXML
    private CheckBox show_password;

    @FXML
    void showpassword(ActionEvent event) {


    }

    @FXML
    void login(ActionEvent event) {
        confirm();
    }

    @FXML
    private void initialize() {
        pass_unhash.managedProperty().bind(show_password.selectedProperty());
        pass_unhash.visibleProperty().bind(show_password.selectedProperty());

        password.managedProperty().bind(show_password.selectedProperty().not());
        password.visibleProperty().bind(show_password.selectedProperty().not());

        pass_unhash.textProperty().bindBidirectional(password.textProperty());
    }

    /****************************** FXML ENDS HERE ******************************/

    private NewAdmin admin;
    private AnchorPane container;

    private ObservableList<Bonus> bonusList = FXCollections.observableArrayList();

    private SQLNewAdmin sqlAdmin = new SQLNewAdmin();

    public void setRetrievedData(NewAdmin admin, AnchorPane anchorPane) {
        this.admin = admin;
        this.container = anchorPane;
    }

    private void confirm() {
        try {
            System.out.println(emp_id.getText() + " " + password.getText());
            boolean logged = sqlAdmin.getAdminByID(admin, emp_id.getText(), password.getText());
            if (logged) {
                loadCredentials();
            } else {
                callAlert("Please enter the currently logged on account credentials and try again", 1);
            }
        } catch (NumberFormatException e) {
            callAlert("Invalid Format", 1);
        }
    }

    private void loadCredentials() {
        CredentialsController controller;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UI/Additional/Credentials.fxml"));
//            fxmlLoader.load();

            Node n = (Node) fxmlLoader.load();
            AnchorPane.setTopAnchor(n, 0.0);
            AnchorPane.setBottomAnchor(n, 0.0);
            AnchorPane.setLeftAnchor(n, 0.0);
            AnchorPane.setRightAnchor(n, 0.0);

            controller = fxmlLoader.getController();
            controller.setRetrievedData(admin, container);

//            AnchorPane anchorPane = fxmlLoader.getRoot();

            container.getChildren().clear();
            container.getChildren().add(n);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
