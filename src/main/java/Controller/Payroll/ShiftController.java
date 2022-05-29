package Controller.Payroll;

import Database.SQLDepartment;
import Database.SQLShift;
import Models.Admin;
import Models.Department;
import Models.Employee;
import Models.Shift;
import cw.payroll.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

import java.io.IOException;

public class ShiftController {

    @FXML
    private Button shift_button_add;

    @FXML
    private Button shift_button_back;

    @FXML
    private TableColumn<Shift, Void> shift_column_action;

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

        Callback<TableColumn<Shift, Void>, TableCell<Shift, Void>> cellFactory = new Callback<TableColumn<Shift, Void>, TableCell<Shift, Void>>() {
            @Override
            public TableCell<Shift, Void> call(final TableColumn<Shift, Void> param) {
                final TableCell<Shift, Void> cell = new TableCell<Shift, Void>() {
                    private final Button btn = new Button("Edit");
                    private final Button btn2 = new Button("Delete");

                    {
                        String style = "-fx-background-color: #c3c4c4, linear-gradient(#d6d6d6 50%, white 100%)," +
                                "radial-gradient(center 50% -40%, radius 200%, #e6e6e6 45%, rgba(230,230,230,0) 50%); " +
                                "-fx-background-radius: 30; " +
                                "-fx-background-insets: 0,1,1; " +
                                "-fx-text-fill: black; " +
                                "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.6), 3, 0.0, 0, 1);";

                        btn.setStyle(style);
                        btn.setDisable(true);
                        btn.setOnAction((ActionEvent event) -> {
//                            Employee emp = getTableView().getItems().get(getIndex());
                        });
                        btn2.setDisable(true);
                        btn2.setStyle(style);
                        btn2.setOnAction((ActionEvent event) -> {
//                            Employee emp = getTableView().getItems().get(getIndex());
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
        shift_column_action.setCellFactory(cellFactory);
        shift_tableview.setItems(shiftList);
    }
}
