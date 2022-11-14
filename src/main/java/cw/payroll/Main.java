package cw.payroll;

import Database.SQLLogs;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static cw.payroll.Startup.checkStartup;


public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
//
//        Tester tester = new Tester();
//        tester.createTables();
//        SQLLogs sqlLogs = new SQLLogs();
//        sqlLogs.createTriggers();

        checkStartup(stage);

    }

    public static void main(String[] args) {
        launch();
    }
}