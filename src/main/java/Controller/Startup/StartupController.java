package Controller.Startup;

import Controller.Employee.ManageEmployeeController;
import Models.Admin;
import cw.payroll.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class StartupController {

    @FXML
    private AnchorPane content_container;

    @FXML
    private void initialize() {

    }


    /****************************** FXML ENDS HERE ******************************/

    private void loadCreateFirstEmployee() {
        AddFirstEmployeeController controller;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UI/Startup/AddFirstEmployee.fxml"));

            Node n = (Node) fxmlLoader.load();
            AnchorPane.setTopAnchor(n, 0.0);
            AnchorPane.setBottomAnchor(n, 0.0);
            AnchorPane.setLeftAnchor(n, 0.0);
            AnchorPane.setRightAnchor(n, 0.0);

            controller = fxmlLoader.getController();
            controller.setRetrievedData(content_container);

            content_container.getChildren().clear();
            content_container.getChildren().add(n);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
