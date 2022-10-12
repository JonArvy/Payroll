package cw.payroll;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UI/Admin.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        scene.getStylesheets().add(Main.class.getResource("/cw/payroll/css/Style.css").toExternalForm());

        stage.setScene(scene);
        stage.setMinHeight(580);
        stage.setMinWidth(975);
//        stage.initStyle(StageStyle.UNDECORATED);
//        stage.setResizable(false);

        stage.show();


    }


    public static void main(String[] args) {
        launch();
    }
}