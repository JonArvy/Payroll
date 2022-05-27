package Controller;

import Controller.Additional.BonusController;
import Controller.Additional.CredentialsController;
import Controller.Additional.HolidayListController;
import Controller.Attendance.AttendanceReportController;
import Controller.Attendance.DailyAttendanceController;
import Controller.Employee.AddEmployeeController;
import Controller.Employee.ManageEmployeeController;
import Controller.NoticeBoard.NoticeBoardController;
import Controller.Payroll.DepartmentController;
import Controller.Payroll.PayrollSummaryController;
import Controller.Payroll.PayslipController;
import Models.Admin;
import cw.payroll.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminController {

    @FXML
    private AnchorPane content_container;

    @FXML
    private Button main_addemployee_button;

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


    private Admin admin;

    @FXML
    private void clickNavigation_Choices(ActionEvent event) {
        if (event.getSource() == main_admin_button) {
            loadNoticeBoard();
        } else if (event.getSource() == main_addemployee_button) {
            loadAddEmployee();
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
        }
    }

    @FXML
    private void initialize() {
        admin = new Admin(1, 1);
        loadNoticeBoard();
    }

    /****************************** FXML ENDS HERE ******************************/

    private void loadNoticeBoard() {
        NoticeBoardController controller;

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UI/NoticeBoard/NoticeBoard.fxml"));
            fxmlLoader.load();

            controller = fxmlLoader.getController();
            controller.setRetrievedData(admin, content_container);

            AnchorPane anchorPane = fxmlLoader.getRoot();
            content_container.getChildren().clear();
            content_container.getChildren().add(anchorPane);
        } catch (IOException ex) {
            ex.printStackTrace();
//            Logger.getLogger(this.class).log(Level.SEVERE, null, ex);
        }
    }

    private void loadAddEmployee() {
        AddEmployeeController controller;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UI/Employee/AddEmployee.fxml"));
            fxmlLoader.load();

            controller = fxmlLoader.getController();
            controller.setRetrievedData(admin, content_container);

            AnchorPane anchorPane = fxmlLoader.getRoot();
            content_container.getChildren().clear();
            content_container.getChildren().add(anchorPane);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void loadManageEmployee() {
        ManageEmployeeController controller;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UI/Employee/ManageEmployee.fxml"));
            fxmlLoader.load();

            controller = fxmlLoader.getController();
            controller.setRetrievedData(admin, content_container);

            AnchorPane anchorPane = fxmlLoader.getRoot();
            content_container.getChildren().clear();
            content_container.getChildren().add(anchorPane);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void loadDailyAttendance() {
        DailyAttendanceController controller;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UI/Attendance/DailyAttendance.fxml"));
            fxmlLoader.load();

            controller = fxmlLoader.getController();
            controller.setRetrievedData(admin, content_container);

            AnchorPane anchorPane = fxmlLoader.getRoot();

            content_container.getChildren().clear();
            content_container.getChildren().add(anchorPane);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void loadAttendanceReport() {
        AttendanceReportController controller;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UI/Attendance/AttendanceReport.fxml"));
            fxmlLoader.load();

            controller = fxmlLoader.getController();
            controller.setRetrievedData(admin, content_container);

            AnchorPane anchorPane = fxmlLoader.getRoot();

            content_container.getChildren().clear();
            content_container.getChildren().add(anchorPane);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void loadPayslip() {
        PayslipController controller;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UI/Payroll/Payslip.fxml"));
            fxmlLoader.load();

            controller = fxmlLoader.getController();
            controller.setRetrievedData(admin, content_container);

            AnchorPane anchorPane = fxmlLoader.getRoot();

            content_container.getChildren().clear();
            content_container.getChildren().add(anchorPane);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void loadPayrollSummary() {
        PayrollSummaryController controller;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UI/Payroll/PayrollSummary.fxml"));
            fxmlLoader.load();

            controller = fxmlLoader.getController();
            controller.setRetrievedData(admin, content_container);

            AnchorPane anchorPane = fxmlLoader.getRoot();

            content_container.getChildren().clear();
            content_container.getChildren().add(anchorPane);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void loadDepartment() {
        DepartmentController controller;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UI/Payroll/Department.fxml"));
            fxmlLoader.load();

            controller = fxmlLoader.getController();
            controller.setRetrievedData(admin, content_container);

            AnchorPane anchorPane = fxmlLoader.getRoot();

            content_container.getChildren().clear();
            content_container.getChildren().add(anchorPane);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void loadHolidayList() {
        HolidayListController controller;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UI/Additional/HolidayList.fxml"));
            fxmlLoader.load();

            controller = fxmlLoader.getController();
            controller.setRetrievedData(admin, content_container);

            AnchorPane anchorPane = fxmlLoader.getRoot();

            content_container.getChildren().clear();
            content_container.getChildren().add(anchorPane);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void loadBonus() {
        BonusController controller;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UI/Additional/Bonus.fxml"));
            fxmlLoader.load();

            controller = fxmlLoader.getController();
            controller.setRetrievedData(admin, content_container);

            AnchorPane anchorPane = fxmlLoader.getRoot();

            content_container.getChildren().clear();
            content_container.getChildren().add(anchorPane);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void loadCredentials() {
        CredentialsController controller;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UI/Additional/Credentials.fxml"));
            fxmlLoader.load();

            controller = fxmlLoader.getController();
            controller.setRetrievedData(admin, content_container);

            AnchorPane anchorPane = fxmlLoader.getRoot();

            content_container.getChildren().clear();
            content_container.getChildren().add(anchorPane);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

}
