package Controller.Additional;

import Database.SQLBonus;
import Models.Admin;
import Models.Bonus;
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
import java.sql.Date;

public class BonusController {

    @FXML
    private Button bonus_button_add;

    @FXML
    private TableColumn<Bonus, Void> bonus_column_action;

    @FXML
    private TableColumn<Bonus, Double> bonus_column_amount;

    @FXML
    private TableColumn<Bonus, Date> bonus_column_dateapplicable;

    @FXML
    private TableColumn<Bonus, String> bonus_column_recipient;

    @FXML
    private TableColumn<Bonus, String> bonus_column_name;

    @FXML
    private TableView bonus_tableview;

    @FXML
    private void addBonus(ActionEvent event) {
        loadAddBonus();
    }

    @FXML
    private void initialize() {
        showBonusList();
    }

    /****************************** FXML ENDS HERE ******************************/

    private Admin admin;
    private AnchorPane container;

    private ObservableList<Bonus> bonusList = FXCollections.observableArrayList();

    private SQLBonus sqlBonus = new SQLBonus();

    public void setRetrievedData(Admin admin, AnchorPane anchorPane) {
        this.admin = admin;
        this.container = anchorPane;
    }

    private void loadAddBonus() {
        AddBonusController controller;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UI/Additional/AddBonus.fxml"));
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

    private void loadEditBonus(Bonus bonus) {
        EditBonusController controller;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UI/Additional/EditBonus.fxml"));
            fxmlLoader.load();

            controller = fxmlLoader.getController();
            controller.setRetrievedData(admin, container);
            controller.setBonus(bonus);

            AnchorPane anchorPane = fxmlLoader.getRoot();
//            container.getChildren().clear();
            container.getChildren().add(anchorPane);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void showBonusList() {
        bonusList.clear();
        bonusList = sqlBonus.getBonus();

        bonus_column_name.setCellValueFactory(new PropertyValueFactory<Bonus, String>("Bonus_Name"));
        bonus_column_amount.setCellValueFactory(new PropertyValueFactory<Bonus, Double>("Bonus_Amount"));
        bonus_column_recipient.setCellValueFactory(new PropertyValueFactory<Bonus, String>("Bonus_Recipient"));
        bonus_column_dateapplicable.setCellValueFactory(new PropertyValueFactory<Bonus, Date>("Bonus_Date"));
//        main_bonus_action.setCellValueFactory(new PropertyValueFactory<Bonus, Integer>("Department_HoursPerDay"));
        Callback<TableColumn<Bonus, Void>, TableCell<Bonus, Void>> cellFactory = new Callback<TableColumn<Bonus, Void>, TableCell<Bonus, Void>>() {
            @Override
            public TableCell<Bonus, Void> call(final TableColumn<Bonus, Void> param) {
                final TableCell<Bonus, Void> cell = new TableCell<Bonus, Void>() {
                    private final Button btn = new Button("Edit");
                    private final Button btn2 = new Button("Summary");

                    {
                        String style = "-fx-background-color: #c3c4c4, linear-gradient(#d6d6d6 50%, white 100%)," +
                                "radial-gradient(center 50% -40%, radius 200%, #e6e6e6 45%, rgba(230,230,230,0) 50%); " +
                                "-fx-background-radius: 30; " +
                                "-fx-background-insets: 0,1,1; " +
                                "-fx-text-fill: black; " +
                                "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.6), 3, 0.0, 0, 1);";

                        btn.setStyle(style);
                        btn.setOnAction((ActionEvent event) -> {
//                            System.out.println(getTableView().getItems().get(getIndex()).getBonus_Name());
                            Bonus bonus = getTableView().getItems().get(getIndex());
                            loadEditBonus(bonus);
//                            Employee emp = getTableView().getItems().get(getIndex());
                        });
                        btn2.setDisable(false);
                        btn2.setStyle(style);
                        btn2.setOnAction((ActionEvent event) -> {
//                            Employee emp = getTableView().getItems().get(getIndex());
//                            getTableView().getItems().get(getIndex());
                            showBonusReport(getTableView().getItems().get(getIndex()));
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
        bonus_column_action.setCellFactory(cellFactory);

        bonus_tableview.setItems(bonusList);
    }

    private void showBonusReport(Bonus bonus) {
        BonusReportController controller;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UI/Additional/BonusReport.fxml"));
            fxmlLoader.load();

            controller = fxmlLoader.getController();
            controller.setRetrievedData(admin, container);
            controller.setBonus(bonus);

            AnchorPane anchorPane = fxmlLoader.getRoot();
//            container.getChildren().clear();
            container.getChildren().add(anchorPane);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
