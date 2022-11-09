package Controller.Payroll;

import Classes.TimeSpinner;
import Database.SQLDepartment;
import Models.Admin;
import Models.Bonus;
import Models.Department;
import cw.payroll.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;

import static Classes.CustomAlert.callAlert;

public class AddDepartmentController {

    @FXML
    private Button adddepartment_button_add;

    @FXML
    private Button adddepartment_button_cancel;

    @FXML
    private TextField adddepartment_dayspermonth;

    @FXML
    private TextField adddepartment_hoursperday;

    @FXML
    private TextField adddepartment_monthlyrate;

    @FXML
    private TextField adddepartment_name;

    @FXML
    private CheckBox addshift_friday;

    @FXML
    private CheckBox addshift_monday;

    @FXML
    private CheckBox addshift_saturday;

    @FXML
    private CheckBox addshift_sunday;

    @FXML
    private CheckBox addshift_thursday;

    @FXML
    private CheckBox addshift_tuesday;

    @FXML
    private CheckBox addshift_wednesday;

    @FXML
    private Pane main_pane;

    @FXML
    private void addDepartment(ActionEvent event) {
        checkDepartmentIfValid();
    }

    @FXML
    private void cancel(ActionEvent event) {
        loadDepartment();
    }

    @FXML
    private void initialize() {
        addSpinner();
    }

    /****************************** FXML ENDS HERE ******************************/

    private Admin admin;
    private AnchorPane container;

    private SQLDepartment sqlDepartment = new SQLDepartment();

    TimeSpinner spinner1 = new TimeSpinner();

    TimeSpinner spinner2 = new TimeSpinner();

    TimeSpinner spinner3 = new TimeSpinner();

    TimeSpinner spinner4 = new TimeSpinner();

    public void setRetrievedData(Admin admin, AnchorPane anchorPane) {
        this.admin = admin;
        this.container = anchorPane;
    }

    private void addSpinner() {
        spinner1.setLayoutX(147);
        spinner1.setLayoutY(142);

        spinner2.setLayoutX(412);
        spinner2.setLayoutY(142);
        spinner2.setDisable(true);

        spinner3.setLayoutX(147);
        spinner3.setLayoutY(175);

        spinner4.setLayoutX(412);
        spinner4.setLayoutY(175);

        spinner1.getEditor().setText("08:00");
        spinner2.getEditor().setText("17:00");
        spinner3.getEditor().setText("12:00");
        spinner4.getEditor().setText("13:00");

        spinner1.setPrefWidth(142);
        spinner2.setPrefWidth(142);
        spinner3.setPrefWidth(142);
        spinner4.setPrefWidth(142);

        main_pane.getChildren().addAll(spinner1, spinner2, spinner3, spinner4);

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

    private void checkDepartmentIfValid() {
        if (adddepartment_name.getText() == null || adddepartment_name.getText().trim().equals("")) {
            callAlert("Invalid Department Name", 3);
        } else {
            try {
                int dayspermonth = Integer.parseInt(adddepartment_dayspermonth.getText());
                int hoursperday = Integer.parseInt(adddepartment_hoursperday.getText());
                double monthlyrate = Double.parseDouble(adddepartment_monthlyrate.getText());
                String name = adddepartment_name.getText();

                Time timein = Time.valueOf(spinner1.getEditor().getText() + ":00");
                Time timeout = Time.valueOf(spinner2.getEditor().getText() + ":00");
                Time breakstart = Time.valueOf(spinner3.getEditor().getText() + ":00");
                Time breakend = Time.valueOf(spinner4.getEditor().getText() + ":00");

                boolean sunday = addshift_sunday.isSelected();
                boolean monday = addshift_monday.isSelected();
                boolean tuesday = addshift_tuesday.isSelected();
                boolean wednesday = addshift_wednesday.isSelected();
                boolean thursday = addshift_thursday.isSelected();
                boolean friday = addshift_friday.isSelected();
                boolean saturday = addshift_saturday.isSelected();


                boolean exist = sqlDepartment.checkIfDepartmentNameExists(name);
                if (exist == true) {
                    callAlert("A department with same name already exists", 3);
                } else {
                    sqlDepartment.addDepartment(new Department(name, monthlyrate, dayspermonth, hoursperday,
                            timein, timeout, breakstart, breakend,
                            sunday, monday, tuesday, wednesday, thursday, friday, saturday));
                    loadDepartment();
                }
            } catch (NumberFormatException o) {
                callAlert("Invalid Value/s", 3);
            } catch (Exception e) {
                callAlert("Invalid Value/s", 3);
            }
        }
    }
}
