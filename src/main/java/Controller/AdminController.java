package Controller;

import Controller.Additional.BonusController;
import Controller.Additional.CredentialsController;
import Controller.Additional.CredentialsValidationController;
import Controller.Additional.HolidayListController;
import Controller.Attendance.AttendanceReportController;
import Controller.Attendance.DailyAttendanceController;
import Controller.Employee.AddEmployeeController;
import Controller.Employee.ManageEmployeeController;
import Controller.Logs.LogsController;
import Controller.Logs.MenuController;
import Controller.NoticeBoard.NoticeBoardController;
import Controller.Payroll.DepartmentController;
import Controller.Payroll.PayrollSummaryController;
import Controller.Payroll.PayslipController;
import Controller.Startup.WelcomeController;
import Database.SQLNewAdmin;
import Models.Admin;
import Models.NewAdmin;
import cw.payroll.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

import static Classes.QRCodeGenerator.deleteAllQRImages;

public class AdminController {

    @FXML
    private AnchorPane content_container;


    @FXML
    private Button main_admin_button;

    @FXML
    private Button main_attendancereport_button;

    @FXML
    private Button main_bonus_button;

    @FXML
    private Button main_credentials_button;

    @FXML
    private Button main_dailyattendance_button;

    @FXML
    private Button main_department_button;

    @FXML
    private Button main_holiday_button;

    @FXML
    private Button main_logout_button;

    @FXML
    private Button main_manageemployee_button;

    @FXML
    private VBox main_menu_vbox;

    @FXML
    private Button main_payrollsummary_button;

    @FXML
    private Button main_payslip_button;

    @FXML
    private Button showLogs;


    private NewAdmin admin;

    @FXML
    private void clickNavigation_Choices(ActionEvent event) {
        if (event.getSource() == main_admin_button) {
            loadNoticeBoard();
        } else if (event.getSource() == main_manageemployee_button) {
            loadManageEmployee();
        } else if (event.getSource() == main_dailyattendance_button) {
            loadDailyAttendance();
        } else if (event.getSource() == main_attendancereport_button) {
            loadAttendanceReport();
        } else if (event.getSource() == main_payslip_button) {
            loadPayslip();
        } else if (event.getSource() == main_payrollsummary_button) {
            loadPayrollSummary();
        } else if (event.getSource() == main_department_button) {
            loadDepartment();
        } else if (event.getSource() == main_holiday_button) {
            loadHolidayList();
        } else if (event.getSource() == main_bonus_button) {
            loadBonus();
        } else if (event.getSource() == main_credentials_button) {
            loadCredentials();
        } else if (event.getSource() == showLogs) {
            logs();
        } else if (event.getSource() == main_logout_button) {
            logOut();
        }
    }

    @FXML
    private void initialize() {
//        setAdmin(new NewAdmin());

    }

    /****************************** FXML ENDS HERE ******************************/

    public void setAdmin(NewAdmin admin) {
        this.admin = admin;
        if (admin.isSuperAdmin()) {
            main_admin_button.setText("SUPER ADMIN");
        } else {
            main_menu_vbox.getChildren().remove(main_credentials_button);
        }
        loadNoticeBoard();
    }

    private void loadNoticeBoard() {
        NoticeBoardController controller;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UI/Noticeboard/NoticeBoard.fxml"));
            Node n = (Node) fxmlLoader.load();
            AnchorPane.setTopAnchor(n, 0.0);
            AnchorPane.setBottomAnchor(n, 0.0);
            AnchorPane.setLeftAnchor(n, 0.0);
            AnchorPane.setRightAnchor(n, 0.0);

            controller = fxmlLoader.getController();
            controller.setRetrievedData(admin, content_container);
            content_container.getChildren().clear();
            content_container.getChildren().add(n);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    private void loadManageEmployee() {
        ManageEmployeeController controller;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UI/Employee/ManageEmployee.fxml"));
//            fxmlLoader.load();

            Node n = (Node) fxmlLoader.load();
            AnchorPane.setTopAnchor(n, 0.0);
            AnchorPane.setBottomAnchor(n, 0.0);
            AnchorPane.setLeftAnchor(n, 0.0);
            AnchorPane.setRightAnchor(n, 0.0);

            controller = fxmlLoader.getController();
            controller.setRetrievedData(admin, content_container);

//            AnchorPane anchorPane = fxmlLoader.getRoot();
            content_container.getChildren().clear();
            content_container.getChildren().add(n);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void loadDailyAttendance() {
        DailyAttendanceController controller;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UI/Attendance/DailyAttendance.fxml"));
//            fxmlLoader.load();

            Node n = (Node) fxmlLoader.load();
            AnchorPane.setTopAnchor(n, 0.0);
            AnchorPane.setBottomAnchor(n, 0.0);
            AnchorPane.setLeftAnchor(n, 0.0);
            AnchorPane.setRightAnchor(n, 0.0);

            controller = fxmlLoader.getController();
            controller.setRetrievedData(admin, content_container);

//            AnchorPane anchorPane = fxmlLoader.getRoot();

            content_container.getChildren().clear();
            content_container.getChildren().add(n);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void loadAttendanceReport() {
        AttendanceReportController controller;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UI/Attendance/AttendanceReport.fxml"));
//            fxmlLoader.load();

            Node n = (Node) fxmlLoader.load();
            AnchorPane.setTopAnchor(n, 0.0);
            AnchorPane.setBottomAnchor(n, 0.0);
            AnchorPane.setLeftAnchor(n, 0.0);
            AnchorPane.setRightAnchor(n, 0.0);

            controller = fxmlLoader.getController();
            controller.setRetrievedData(admin, content_container);

//            AnchorPane anchorPane = fxmlLoader.getRoot();

            content_container.getChildren().clear();
            content_container.getChildren().add(n);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void loadPayslip() {
        PayslipController controller;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UI/Payroll/Payslip.fxml"));
//            fxmlLoader.load();

            Node n = (Node) fxmlLoader.load();
            AnchorPane.setTopAnchor(n, 0.0);
            AnchorPane.setBottomAnchor(n, 0.0);
            AnchorPane.setLeftAnchor(n, 0.0);
            AnchorPane.setRightAnchor(n, 0.0);

            controller = fxmlLoader.getController();
            controller.setRetrievedData(admin, content_container);

//            AnchorPane anchorPane = fxmlLoader.getRoot();

            content_container.getChildren().clear();
            content_container.getChildren().add(n);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void loadPayrollSummary() {
        PayrollSummaryController controller;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UI/Payroll/PayrollSummary.fxml"));
//            fxmlLoader.load();

            Node n = (Node) fxmlLoader.load();
            AnchorPane.setTopAnchor(n, 0.0);
            AnchorPane.setBottomAnchor(n, 0.0);
            AnchorPane.setLeftAnchor(n, 0.0);
            AnchorPane.setRightAnchor(n, 0.0);

            controller = fxmlLoader.getController();
            controller.setRetrievedData(admin, content_container);

//            AnchorPane anchorPane = fxmlLoader.getRoot();

            content_container.getChildren().clear();
            content_container.getChildren().add(n);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void loadDepartment() {
        DepartmentController controller;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UI/Payroll/Department.fxml"));
//            fxmlLoader.load();

            Node n = (Node) fxmlLoader.load();
            AnchorPane.setTopAnchor(n, 0.0);
            AnchorPane.setBottomAnchor(n, 0.0);
            AnchorPane.setLeftAnchor(n, 0.0);
            AnchorPane.setRightAnchor(n, 0.0);

            controller = fxmlLoader.getController();
            controller.setRetrievedData(admin, content_container);

//            AnchorPane anchorPane = fxmlLoader.getRoot();

            content_container.getChildren().clear();
            content_container.getChildren().add(n);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void loadHolidayList() {
        HolidayListController controller;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UI/Additional/HolidayList.fxml"));
//            fxmlLoader.load();

            Node n = (Node) fxmlLoader.load();
            AnchorPane.setTopAnchor(n, 0.0);
            AnchorPane.setBottomAnchor(n, 0.0);
            AnchorPane.setLeftAnchor(n, 0.0);
            AnchorPane.setRightAnchor(n, 0.0);

            controller = fxmlLoader.getController();
            controller.setRetrievedData(admin, content_container);

//            AnchorPane anchorPane = fxmlLoader.getRoot();

            content_container.getChildren().clear();
            content_container.getChildren().add(n);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void loadBonus() {
        BonusController controller;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UI/Additional/Bonus.fxml"));
//            fxmlLoader.load();

            Node n = (Node) fxmlLoader.load();
            AnchorPane.setTopAnchor(n, 0.0);
            AnchorPane.setBottomAnchor(n, 0.0);
            AnchorPane.setLeftAnchor(n, 0.0);
            AnchorPane.setRightAnchor(n, 0.0);

            controller = fxmlLoader.getController();
            controller.setRetrievedData(admin, content_container);

//            AnchorPane anchorPane = fxmlLoader.getRoot();

            content_container.getChildren().clear();
            content_container.getChildren().add(n);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void loadCredentials() {
        CredentialsValidationController controller;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UI/Additional/CredentialsValidation.fxml"));
//            fxmlLoader.load();

            Node n = (Node) fxmlLoader.load();
            AnchorPane.setTopAnchor(n, 0.0);
            AnchorPane.setBottomAnchor(n, 0.0);
            AnchorPane.setLeftAnchor(n, 0.0);
            AnchorPane.setRightAnchor(n, 0.0);

            controller = fxmlLoader.getController();
            controller.setRetrievedData(admin, content_container);

//            AnchorPane anchorPane = fxmlLoader.getRoot();

            content_container.getChildren().clear();
            content_container.getChildren().add(n);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void logs() {
        MenuController controller;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UI/Logs/Menu.fxml"));

            Node n = (Node) fxmlLoader.load();
            AnchorPane.setTopAnchor(n, 0.0);
            AnchorPane.setBottomAnchor(n, 0.0);
            AnchorPane.setLeftAnchor(n, 0.0);
            AnchorPane.setRightAnchor(n, 0.0);

            controller = fxmlLoader.getController();
            controller.setRetrievedData(admin, content_container);

//            AnchorPane anchorPane = fxmlLoader.getRoot();

            content_container.getChildren().clear();
            content_container.getChildren().add(n);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void logOut() {
        Stage stage = (Stage) main_logout_button.getScene().getWindow();
        stage.close();

        deleteAllQRImages();
        SQLNewAdmin sqlNewAdmin = new SQLNewAdmin();
        sqlNewAdmin.setAdminIsNotUsingTheSystem();

        Parent root;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UI/Login.fxml"));
            root = fxmlLoader.load();
            Stage stage2 = new Stage();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(Main.class.getResource("/cw/payroll/css/Style.css").toExternalForm());
            stage2.setScene(scene);
            stage2.initStyle(StageStyle.UNDECORATED);
            stage2.getIcons().add(new Image(Main.class.getResourceAsStream("/cw/payroll/Assets/CWBH Logo.png")));
            stage2.setResizable(false);
            stage2.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
