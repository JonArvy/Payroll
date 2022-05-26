package Controller.Employee;

import Models.Admin;
import cw.payroll.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class ManageEmployeeController {

    @FXML
    private CheckBox manageemployee_checkbox_hideinactive;

    @FXML
    private TableColumn<?, ?> manageemployee_column_action;

    @FXML
    private TableColumn<?, ?> manageemployee_column_department;

    @FXML
    private TableColumn<?, ?> manageemployee_column_empid;

    @FXML
    private TableColumn<?, ?> manageemployee_column_empstatus;

    @FXML
    private TableColumn<?, ?> manageemployee_column_fname;

    @FXML
    private TableColumn<?, ?> manageemployee_column_lname;

    @FXML
    private TableColumn<?, ?> manageemployee_column_status;

    @FXML
    private TextField manageemployee_searchbar;

    @FXML
    private TableView<?> manageemployee_tableview;

    @FXML
    private void search(ActionEvent event) {

    }

    /****************************** FXML ENDS HERE ******************************/
    private Admin admin;
    private AnchorPane container;

    public void setRetrievedData(Admin admin, AnchorPane anchorPane) {
        this.admin = admin;
        this.container = anchorPane;
    }

    private void loadAddEmployee() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UI/Employee/AddEmployee.fxml"));
            fxmlLoader.load();

            AnchorPane anchorPane = fxmlLoader.getRoot();
            container.getChildren().clear();
            container.getChildren().add(anchorPane);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
