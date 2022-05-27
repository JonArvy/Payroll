package Controller.Payroll;

import Database.SQLDepartment;
import Database.SQLShift;
import Models.Admin;
import Models.Department;
import Models.Shift;
import cw.payroll.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class ShiftController {

    @FXML
    private Button shift_button_add;

    @FXML
    private Button shift_button_back;

    @FXML
    private TableColumn<?, ?> shift_column_action;

    @FXML
    private TableColumn<Shift, String> shift_column_recepient;

    @FXML
    private TableColumn<Shift, String> shift_column_schema;

    @FXML
    private TableColumn<Shift, String> shift_column_shifttype;

    @FXML
    private TableView shift_tableview;

    @FXML
    private void addShift(ActionEvent event) {
        loadAddShift();
    }

    @FXML
    private void goBackToDepartment(ActionEvent event) {
        loadDepartment();
    }

    @FXML
    private void initialize() {
        showShiftTable();
    }

    /****************************** FXML ENDS HERE ******************************/

    private Admin admin;
    private AnchorPane container;

    private ObservableList<Shift> shiftList = FXCollections.observableArrayList();

    private SQLShift sqlShift = new SQLShift();

    public void setRetrievedData(Admin admin, AnchorPane anchorPane) {
        this.admin = admin;
        this.container = anchorPane;
    }

    private void loadDepartment() {
        DepartmentController controller;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UI/Payroll/Department.fxml"));
            fxmlLoader.load();

            controller = fxmlLoader.getController();
            controller.setRetrievedData(admin, container);

            AnchorPane anchorPane = fxmlLoader.getRoot();
            container.getChildren().clear();
            container.getChildren().add(anchorPane);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void loadAddShift() {
        AddShiftController controller;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UI/Payroll/AddShift.fxml"));
            fxmlLoader.load();

            controller = fxmlLoader.getController();
            controller.setRetrievedData(admin, container);

            AnchorPane anchorPane = fxmlLoader.getRoot();
//            container.getChildren().clear();
            container.getChildren().add(anchorPane);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void showShiftTable() {
        shiftList.clear();
        shiftList = sqlShift.getShift();

        shift_column_shifttype.setCellValueFactory(new PropertyValueFactory<Shift, String>("Shift_Type_Name"));
        shift_column_recepient.setCellValueFactory(new PropertyValueFactory<Shift, String>("Recipient_Name"));
        shift_column_schema.setCellValueFactory(new PropertyValueFactory<Shift, String>("Shift_Schema"));

        shift_tableview.setItems(shiftList);
    }
}
