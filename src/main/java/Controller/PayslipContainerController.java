package Controller;

import Controller.Payroll.ViewPayslipController;
import Models.Summary;
import cw.payroll.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class PayslipContainerController {

    @FXML
    private AnchorPane content_container;

    private Summary summary;

    public void setSummary(Summary summary) {
        this.summary = summary;
        loadPayslip();
    }

    private void loadPayslip() {
        ViewPayslipInEmployeeController controller;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UI/ViewPayslipInEmployee.fxml"));
            fxmlLoader.load();

            controller = fxmlLoader.getController();
            controller.setSummary(summary);

            AnchorPane anchorPane = fxmlLoader.getRoot();
//            container.getChildren().clear();
            content_container.getChildren().add(anchorPane);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
