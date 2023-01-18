package Controller.Additional;

import Controller.Employee.ManageEmployeeController;
import Database.SQLAdmin;
import Database.SQLNewAdmin;
import Models.Admin;
import Models.BonusSummary;
import Models.NewAdmin;
import cw.payroll.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

import static Classes.CustomAlert.callAlert;

public class CredentialsController {

    @FXML
    private TableColumn<NewAdmin, String> credentials_column_active;

    @FXML
    private TableColumn<NewAdmin, String> credentials_column_empid;

    @FXML
    private TableColumn<NewAdmin, String> credentials_column_fullname;

    @FXML
    private TableColumn<NewAdmin, Integer> credentials_column_no;

    @FXML
    private TableColumn<NewAdmin, String> credentials_column_admintype;

    @FXML
    private TableView<NewAdmin> credentials_tableview;

    @FXML
    void add(ActionEvent event) {
        loadAddCredentials();
    }

    @FXML
    void deactivate(ActionEvent event) {
        if (credentials_tableview.getSelectionModel().getSelectedItem() != null ) {
            if (!credentials_tableview.getSelectionModel().getSelectedItem().getDisabler().equals("")) {
                callAlert("Employee is already inactive", 3);
            } else {
                deactivateCredentials();
            }
        } else {
            callAlert("Please select a row to deactivate", 4);
        }

    }

    @FXML
    void initialize() {
        loadAdminList();
    }


    /****************************** FXML ENDS HERE ******************************/

    private NewAdmin admin;
    private AnchorPane container;
    public void setRetrievedData(NewAdmin admin, AnchorPane anchorPane) {
        this.admin = admin;
        this.container = anchorPane;
    }

    SQLNewAdmin sqlAdmin = new SQLNewAdmin();

    ObservableList<NewAdmin> adminList = FXCollections.observableArrayList();


    private void loadAddCredentials() {
        AddCredentialsController controller;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UI/Additional/AddCredentials.fxml"));

            Node n = (Node) fxmlLoader.load();
            AnchorPane.setTopAnchor(n, 0.0);
            AnchorPane.setBottomAnchor(n, 0.0);
            AnchorPane.setLeftAnchor(n, 0.0);
            AnchorPane.setRightAnchor(n, 0.0);

            controller = fxmlLoader.getController();
            controller.setRetrievedData(admin, container);

            container.getChildren().clear();
            container.getChildren().add(n);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void deactivateCredentials() {
        CredentialsDeactivationController controller;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UI/Additional/CredentialsDeactivation.fxml"));

            Node n = (Node) fxmlLoader.load();
            AnchorPane.setTopAnchor(n, 0.0);
            AnchorPane.setBottomAnchor(n, 0.0);
            AnchorPane.setLeftAnchor(n, 0.0);
            AnchorPane.setRightAnchor(n, 0.0);

            controller = fxmlLoader.getController();
            controller.setRetrievedData(admin, container);
            controller.setAdminToDeactivate(credentials_tableview.getSelectionModel().getSelectedItem());
//            container.getChildren().clear();
            container.getChildren().add(n);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void loadAdminList() {
        adminList.clear();

        adminList = sqlAdmin.getAdminList();

        credentials_column_no.setCellValueFactory(new PropertyValueFactory<NewAdmin, Integer>("ID"));
        credentials_column_empid.setCellValueFactory(new PropertyValueFactory<NewAdmin, String>("username"));
        credentials_column_fullname.setCellValueFactory(new PropertyValueFactory<NewAdmin, String>("name"));
        credentials_column_admintype.setCellValueFactory(new PropertyValueFactory<NewAdmin, String>("adminTypeMessage"));
        credentials_column_active.setCellValueFactory(new PropertyValueFactory<NewAdmin, String>("activeMessage"));
        credentials_tableview.setItems(adminList);
    }
}
