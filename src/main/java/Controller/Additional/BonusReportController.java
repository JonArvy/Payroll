package Controller.Additional;

import Database.SQLBonus;
import Database.SQLEmployee;
import Models.Admin;
import Models.Bonus;
import Models.BonusSummary;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class BonusReportController {

    @FXML
    private TableColumn<?, ?> amount;

    @FXML
    private TextField date;

    @FXML
    private TableColumn<?, ?> department;

    @FXML
    private TableColumn<?, ?> emp_id;

    @FXML
    private TableColumn<?, ?> fullname;

    @FXML
    private TableView<?> main_attendancereport_tableview;

    @FXML
    private TextField name;

    @FXML
    private TableColumn<?, ?> number;

    @FXML
    void print(ActionEvent event) {

    }

    /****************************** FXML ENDS HERE ******************************/

    private Admin admin;
    private AnchorPane container;
    private Bonus bonus;

    private ObservableList<BonusSummary> bonusSummaryList = FXCollections.observableArrayList();

    private SQLEmployee sqlEmployee = new SQLEmployee();
    private SQLBonus sqlBonus = new SQLBonus();

    public void setRetrievedData(Admin admin, AnchorPane anchorPane) {
        this.admin = admin;
        this.container = anchorPane;
    }

    public void setBonus(Bonus bonus) {
        this.bonus = bonus;
        initializeContent();
    }

    private void initializeContent() {
        name.setText(bonus.getBonus_Name());
        date.setText(bonus.getBonus_Date().toString());

        bonusSummaryList = sqlBonus.getBonusSummary(bonus);
    }

}
