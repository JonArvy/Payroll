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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.sql.Date;

import static Classes.PDFWriter.createBonusSummaryPDF;

public class BonusReportController {

    @FXML
    private TableColumn<BonusSummary, Double> amount;

    @FXML
    private TextField date;

    @FXML
    private TableColumn<BonusSummary, String> department;

    @FXML
    private TableColumn<BonusSummary, Integer> emp_id;

    @FXML
    private TableColumn<BonusSummary, String> fullname;

    @FXML
    private TableView main_attendancereport_tableview;

    @FXML
    private TextField name;

    @FXML
    private TableColumn<BonusSummary, Integer> number;

    @FXML
    void print(ActionEvent event) {
        createBonusSummaryPDF(bonusSummaryList, Date.valueOf(date.getText()), name.getText());
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

        number.setCellValueFactory(new PropertyValueFactory<BonusSummary, Integer>("Bonus_ID"));
        fullname.setCellValueFactory(new PropertyValueFactory<BonusSummary, String>("Fullname"));
        emp_id.setCellValueFactory(new PropertyValueFactory<BonusSummary, Integer>("Emp_ID"));
        department.setCellValueFactory(new PropertyValueFactory<BonusSummary, String>("Department"));
        amount.setCellValueFactory(new PropertyValueFactory<BonusSummary, Double>("Amount"));

        main_attendancereport_tableview.setItems(bonusSummaryList);
    }

}
