package cw.payroll;

import Models.Employee;
import Classes.SQLExecution;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Employee.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        scene.getStylesheets().add(Main.class.getResource("/cw/payroll/css/Style.css").toExternalForm());

        stage.setScene(scene);
        stage.show();

    }


    public static void main(String[] args) {
        launch();
    }
}