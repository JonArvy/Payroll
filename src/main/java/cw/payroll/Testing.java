package cw.payroll;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class Testing extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(null);
        System.out.println(selectedFile.getPath());
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UI/Importer/ImportEmployeeTable.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        primaryStage.setScene(scene);
        primaryStage.show();
    }



    public static void main(String[] args) {
        launch(args);
    }
}
