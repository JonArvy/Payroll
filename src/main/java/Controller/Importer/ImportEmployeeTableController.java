package Controller.Importer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

import java.io.File;

public class ImportEmployeeTableController {

    @FXML
    private TableColumn<?, ?> address;

    @FXML
    private TableColumn<?, ?> birthdate;

    @FXML
    private TableColumn<?, ?> contact_number;

    @FXML
    private TableColumn<?, ?> department;

    @FXML
    private TableColumn<?, ?> emergency_contact_address;

    @FXML
    private TableColumn<?, ?> emergency_contact_name;

    @FXML
    private TableColumn<?, ?> emergency_contact_number;

    @FXML
    private TableColumn<?, ?> emergency_contact_relationship;

    @FXML
    private TableColumn<?, ?> employment_status;

    @FXML
    private TableColumn<?, ?> extension;

    @FXML
    private Text filename;

    @FXML
    private TableColumn<?, ?> first_name;

    @FXML
    private TableColumn<?, ?> gender;

    @FXML
    private TableColumn<?, ?> last_name;

    @FXML
    private TableColumn<?, ?> marital_status;

    @FXML
    private TableColumn<?, ?> middle_name;

    @FXML
    private TableColumn<?, ?> nationality;

    @FXML
    private TableColumn<?, ?> position;

    @FXML
    private TableColumn<?, ?> status;

    @FXML
    void cancel(ActionEvent event) {

    }

    @FXML
    void importFile(ActionEvent event) {
    }

}
