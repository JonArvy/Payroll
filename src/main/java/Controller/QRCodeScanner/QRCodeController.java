package Controller.QRCodeScanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;

public class QRCodeController {

    @FXML
    private AnchorPane content_container;

    @FXML
    private ImageView qr_image;

    @FXML
    private Text password_text;

    @FXML
    void close(ActionEvent event) {
        Stage stage = (Stage) content_container.getScene().getWindow();
        stage.close();
    }

    public void setImage(String path, String password) {
        qr_image.setImage(new Image(new File(path).toURI().toString()));
        password_text.setText(password);
    }

}
