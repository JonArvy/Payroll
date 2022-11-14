package Controller.Startup;

import cw.payroll.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class WelcomeController {

    @FXML
    void proceed(ActionEvent event) {
        loadCreateFirstEmployee();
    }

    /****************************** FXML ENDS HERE ******************************/

    private AnchorPane container;

    public void setRetrievedData(AnchorPane anchorPane) {
        this.container = anchorPane;
    }


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
            controller.setRetrievedData(container);

            container.getChildren().clear();
            container.getChildren().add(n);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
