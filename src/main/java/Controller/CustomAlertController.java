package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CustomAlertController {

    @FXML
    private Button alert_button_close;

    @FXML
    private Text alert_content;

    @FXML
    private Text alert_header;

    @FXML
    private void close(ActionEvent event) {
        Stage stage = (Stage) alert_button_close.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void initialize() {
        setMessage();
    }

    /****************************** FXML ENDS HERE ******************************/
    private String title = "Error!";
    private String content = "Error!";

    public void setAlertMessage(String content) {
//        this.title = title;
        this.content = content;
        setMessage();
    }

    private void setMessage() {
//        alert_header.setText(title);
        alert_content.setText(content);
    }
}
