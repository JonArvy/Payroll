package Classes;

import Controller.CustomAlertController;
import cw.payroll.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class CustomAlert {
    public static void callAlert(String content, int type) {
        CustomAlertController customAlertController;
        String fxml = "UI/InfoAlert.fxml";
        if (type == 1) {
            fxml = "UI/ErrorAlert.fxml";
        } else if (type == 2) {
            fxml = "UI/SuccessAlert.fxml";
        } else if (type == 3) {
            fxml = "UI/WarningAlert.fxml";
        }
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxml));
            AnchorPane pane = fxmlLoader.load();
            Scene scene = new Scene(pane);

            customAlertController = fxmlLoader.getController();
            customAlertController.setAlertMessage(content);

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(scene);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
