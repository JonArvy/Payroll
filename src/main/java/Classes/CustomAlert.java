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
    public static void callAlert(String title, String content) {
        CustomAlertController customAlertController;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UI/CustomAlert.fxml"));
            AnchorPane pane = fxmlLoader.load();
            Scene scene = new Scene(pane);

            customAlertController = fxmlLoader.getController();
            customAlertController.setAlertMessage(title, content);

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(scene);
            stage.showAndWait();
        } catch (IOException e) {

        }
    }
}
