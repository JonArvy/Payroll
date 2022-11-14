package Controller.Startup;

import Models.Admin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class AddFirstEmployeeController {

    @FXML
    private TextArea addemployee_address;

    @FXML
    private DatePicker addemployee_bdate;

    @FXML
    private Button addemployee_button_next;

    @FXML
    private Button addemployee_button_reset;

    @FXML
    private TextArea addemployee_contactaddress;

    @FXML
    private TextField addemployee_contactname;

    @FXML
    private TextField addemployee_contactnumber;

    @FXML
    private TextField addemployee_contactrelationship;

    @FXML
    private ComboBox<?> addemployee_department;

    @FXML
    private TextField addemployee_empid;

    @FXML
    private ComboBox<?> addemployee_empstatus;

    @FXML
    private TextField addemployee_extension;

    @FXML
    private TextField addemployee_firstname;

    @FXML
    private ComboBox<?> addemployee_gender;

    @FXML
    private TextField addemployee_lastname;

    @FXML
    private ComboBox<?> addemployee_maritalstatus;

    @FXML
    private TextField addemployee_middlename;

    @FXML
    private TextField addemployee_nationality;

    @FXML
    private TextField addemployee_number;

    @FXML
    private TextField addemployee_position;

    @FXML
    private ComboBox<?> addemployee_status;

    @FXML
    void nextPage(ActionEvent event) {

    }

    @FXML
    void reset(ActionEvent event) {

    }

    /****************************** FXML ENDS HERE ******************************/

    private AnchorPane container;

    public void setRetrievedData(AnchorPane anchorPane) {
        this.container = anchorPane;
    }

}
