package cw.payroll;

import Database.SQLAdmin;
import Database.SQLDepartment;
import Database.SQLExecution;
import Database.SQLLogs;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Startup {
    public static void checkStartup(Stage stage) throws IOException {
        SQLAdmin sqlAdmin = new SQLAdmin();
        SQLDepartment sqlDepartment = new SQLDepartment();

        //Creation of tables if it doesn't exist
        SQLExecution sqlExecution = new SQLExecution();
        sqlExecution.createTables();

        Startup startup = new Startup();

        if (sqlAdmin.getAdminCount() == 0 && sqlDepartment.getDepartmentCount() == 0) {
            sqlDepartment.addFirstDepartment();
            startup.loadStartup(stage);
        } else if (sqlAdmin.getAdminCount() == 0 && sqlDepartment.getDepartmentCount() > 0) {
            startup.loadStartup(stage);
        } else {
            //Creation of triggers if it doesn't exist
            SQLLogs sqlLogs = new SQLLogs();
            sqlLogs.createTriggers();

            startup.loadSystem(stage);
        }
    }

    public void loadStartup(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UI/Startup/Startup.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        scene.getStylesheets().add(Main.class.getResource("/cw/payroll/css/Style.css").toExternalForm());

        stage.setScene(scene);
        stage.setMinHeight(540);
        stage.setMinWidth(717);
//        stage.initStyle(StageStyle.UNDECORATED);
//        stage.setResizable(false);

        stage.show();
    }


    public void loadSystem(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UI/Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        scene.getStylesheets().add(Main.class.getResource("/cw/payroll/css/Style.css").toExternalForm());

        stage.setScene(scene);
//        stage.setMinHeight(580);
//        stage.setMinWidth(975);
//        stage.initStyle(StageStyle.UNDECORATED);
//        stage.setResizable(false);

        stage.show();
    }
}
