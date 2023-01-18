package Controller.Additional;

import Database.SQLAdmin;
import Database.SQLEmployee;
import Database.SQLNewAdmin;
import Models.Admin;
import Models.NewAdmin;
import cw.payroll.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

import static Classes.CustomAlert.callAlert;

public class CredentialsDeactivationController {

    @FXML
    private Button login;

    @FXML
    private PasswordField password;

    @FXML
    void goBack(ActionEvent event) {
        loadCredentials();
    }

    @FXML
    void login(ActionEvent event) {
        NewAdmin na = new NewAdmin();
        na.setUsername(this.admin.getUsername());
        na.setPassword(password.getText());
        if (sqlAdmin.checkIfValidAdmin(na)) {
            sqlAdmin.deactivateAdmin(this.admin, adminToDeactivate);
            loadCredentials();
        } else {
            callAlert("Please enter the correct password", 3);
        }
    }

    /****************************** FXML ENDS HERE ******************************/

    private NewAdmin admin;
    private NewAdmin adminToDeactivate;
    private AnchorPane container;

    private SQLNewAdmin sqlAdmin = new SQLNewAdmin();

    public void setRetrievedData(NewAdmin admin, AnchorPane anchorPane) {
        this.admin = admin;
        this.container = anchorPane;
    }

    public void setAdminToDeactivate(NewAdmin adminToDeactivate) {
        this.adminToDeactivate = adminToDeactivate;
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
