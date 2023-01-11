package Controller.Logs;

import Models.Admin;
import Models.Backup;
import Models.Employee;
import cw.payroll.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

import java.io.IOException;

import static Classes.Zipper.*;

public class BackupController {

    @FXML
    private TableView<Backup> backup_table;

    @FXML
    private TableColumn<Backup, String> created_col;

    @FXML
    private TableColumn<Backup, Void> recovery_col;

    @FXML
    void automaticBackup(ActionEvent event) {

    }

    @FXML
    void createBackup(ActionEvent event) {
        createBackupFromCurrentTimeStamp();
        refreshList();
    }

    @FXML
    void goBack(ActionEvent event) {
        goBackToMenu();
    }

    @FXML
    private void initialize() {

        refreshList();
    }

    /****************************** FXML ENDS HERE ******************************/
    private Admin admin;
    private AnchorPane container;

    private ObservableList<Backup> backupList = FXCollections.observableArrayList();

    public void setRetrievedData(Admin admin, AnchorPane anchorPane) {
        this.admin = admin;
        this.container = anchorPane;
    }



    private void goBackToMenu() {
        MenuController controller;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UI/Logs/Menu.fxml"));

            Node n = (Node) fxmlLoader.load();
            AnchorPane.setTopAnchor(n, 0.0);
            AnchorPane.setBottomAnchor(n, 0.0);
            AnchorPane.setLeftAnchor(n, 0.0);
            AnchorPane.setRightAnchor(n, 0.0);

            controller = fxmlLoader.getController();
            controller.setRetrievedData(admin, container);

//            AnchorPane anchorPane = fxmlLoader.getRoot();

            container.getChildren().clear();
            container.getChildren().add(n);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void refreshList() {
        backupList.clear();
        backupList = listAllFiles();

        created_col.setCellValueFactory(new PropertyValueFactory<Backup, String>("created"));


        Callback<TableColumn<Backup, Void>, TableCell<Backup, Void>> cellFactory = new Callback<TableColumn<Backup, Void>, TableCell<Backup, Void>>() {
            @Override
            public TableCell<Backup, Void> call(final TableColumn<Backup, Void> param) {
                final TableCell<Backup, Void> cell = new TableCell<Backup, Void>() {
                    private final Button btn = new Button("Load Backup");
                    private final Button btn2 = new Button("Delete");

                    {
                        String style = "-fx-background-color: #c3c4c4, linear-gradient(#d6d6d6 50%, white 100%)," +
                                "radial-gradient(center 50% -40%, radius 200%, #e6e6e6 45%, rgba(230,230,230,0) 50%); " +
                                "-fx-background-radius: 30; " +
                                "-fx-background-insets: 0,1,1; " +
                                "-fx-text-fill: black; " +
                                "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.6), 3, 0.0, 0, 1);";

                        btn.setStyle(style);
                        btn.setOnAction((ActionEvent event) -> {
                            Backup backup = getTableView().getItems().get(getIndex());
                            loadBackupUsingRawName(backup.getCreated());
                            refreshList();
                        });

                        btn2.setStyle(style);
                        btn2.setOnAction((ActionEvent event) -> {
                            Backup backup = getTableView().getItems().get(getIndex());
                            deleteBackup(backup.getCreated());
                            refreshList();
                        });
                    }
                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            HBox allbtn = new HBox(btn, btn2);
                            setGraphic(allbtn);
                        }
                    }
                };
                return cell;
            }
        };

        recovery_col.setCellFactory(cellFactory);

        backup_table.setItems(backupList);
    }

}
