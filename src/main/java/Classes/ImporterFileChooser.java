package Classes;

import Controller.Importer.ImportAttendanceTableController;
import Controller.Importer.ImportEmployeeTableController;
import cw.payroll.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class ImporterFileChooser {

    public static void callEmployeeImporter() {
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Spreadsheet Files (.xlsx, .xls)", "*.xlsx", "*.xls")
            );

            ImportEmployeeTableController importEmployeeTableController;

            File selectedFile = fileChooser.showOpenDialog(null);

            if (selectedFile != null) {
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UI/Importer/ImportEmployeeTable.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(fxmlLoader.load());
                stage.setScene(scene);
                stage.show();

                importEmployeeTableController = fxmlLoader.getController();
                importEmployeeTableController.setFile(selectedFile);
            } else {
//                System.exit(110);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void callAttendanceImporter() {
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Spreadsheet Files (.xlsx, .xls)", "*.xlsx", "*.xls")
            );

            ImportAttendanceTableController importEmployeeTableController;

            File selectedFile = fileChooser.showOpenDialog(null);

            if (selectedFile != null) {
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UI/Importer/ImportAttendanceTable.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(fxmlLoader.load());
                stage.setScene(scene);
                stage.show();

                importEmployeeTableController = fxmlLoader.getController();
                importEmployeeTableController.setFile(selectedFile);
            } else {
//                System.exit(110);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
