package Controller.Additional;

import Controller.Employee.ManageEmployeeController;
import Models.Admin;
import cw.payroll.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class CredentialsController {

    @FXML
    private TableColumn<?, ?> credentials_column_active;

    @FXML
    private TableColumn<?, ?> credentials_column_empid;

    @FXML
    private TableColumn<?, ?> credentials_column_fullname;

    @FXML
    private TableColumn<?, ?> credentials_column_no;

    @FXML
    private TableView<?> credentials_tableview;

    @FXML
    void add(ActionEvent event) {
        loadAddCredentials();
    }

    @FXML
    void deactivate(ActionEvent event) {

    }


    /****************************** FXML ENDS HERE ******************************/

    private Admin admin;
    private AnchorPane container;
    public void setRetrievedData(Admin admin, AnchorPane anchorPane) {
        this.admin = admin;
        this.container = anchorPane;
    }



    private void loadAddCredentials() {
        AddCredentialsController controller;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UI/Additional/AddCredentials.fxml"));

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
