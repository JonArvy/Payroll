package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SettingsController {

    @FXML
    private TextField camera_ip;

    @FXML
    private AnchorPane content_container;

    @FXML
    void update(ActionEvent event) {
        String ip = camera_ip.getText();

        try {
            FileWriter fw = new FileWriter("settings.txt");
            fw.write(ip);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Stage stage = (Stage) content_container.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void initialize() {
        try {
            FileReader fr = new FileReader("settings.txt");
            BufferedReader br = new BufferedReader(fr);
            String firstLine = br.readLine();
            camera_ip.setText(firstLine);
            br.close();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
