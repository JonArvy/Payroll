package Classes;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

public class HelloController {

    @FXML
    private Button main_admin_button;

    @FXML
    private Pane main_admin_panel;

    @FXML
    private Button main_addemployee_button;

    @FXML
    private Pane main_addemployee_panel_1;

    @FXML
    private Button addemployee_button_next;

    @FXML
    private Pane main_addemployee_panel_2;

    @FXML
    private Button main_manageemployee_button;

    @FXML
    private Pane main_manageemployee_panel_1;

    @FXML
    private Button addemployee_back_button;

    @FXML
    private Button main_dailyattendance_button;

    @FXML
    private Pane main_dailyattendance_panel_1;

    @FXML
    private Button main_attendancereport_button;

    @FXML
    private Pane main_attendancereport_panel_1;

    @FXML
    private Button main_payslip_button;

    @FXML
    private Pane main_payslip_panel_1;

    @FXML
    private Button main_payrollsummary_button;

    @FXML
    private Pane main_payrollsummary_panel_1;

    @FXML
    private Button main_department_button;

    @FXML
    private Pane main_department_panel_1;

    @FXML
    private Button main_department_shift_button;

    @FXML
    private Pane main_department_shift_panel_1;

    @FXML
    private Button main_department_shift_back_button;

    @FXML
    private Button main_department_add_department_button;

    @FXML
    private Pane main_department_add_department_panel;

    @FXML
    private Button main_department_add_department_cancel_button;

    @FXML
    private Button main_department_edit_department_button;

    @FXML
    private Pane main_department_edit_department_panel;

    @FXML
    private Button main_department_edit_department_cancel_button;

    @FXML
    private Button main_department_add_shift_button;

    @FXML
    private Pane main_department_add_shift_panel;

    @FXML
    private Button main_department_add_shift_cancel_button;

    @FXML
    private Button main_holiday_button;

    @FXML
    private Pane main_holiday_panel_1;

    @FXML
    private Button main_holiday_add_holiday_button;

    @FXML
    private Pane main_holiday_add_holiday_panel_1;

    @FXML
    private Button main_holiday_add_holiday_cancel_button;

    @FXML
    private Button main_bonus_button;

    @FXML
    private Pane main_bonus_panel_1;

    @FXML
    private Button main_bonus_add_bonus_button;

    @FXML
    private Pane main_bonus_add_bonus_panel_1;

    @FXML
    private Button main_bonus_add_bonus_cancel_button;

    @FXML
    private Button main_credentials_button;

    @FXML
    private Pane main_credentials_panel_1;

    @FXML
    private Button main_logout_button;

    //              ADD EMPLOYEEEE VARIABLES START HERE
    @FXML
    private TextArea main_addemployee_address;

    @FXML
    private DatePicker main_addemployee_bdate;

    @FXML
    private TextArea main_addemployee_contactaddress;

    @FXML
    private TextField main_addemployee_contactname;

    @FXML
    private TextField main_addemployee_contactnumber;

    @FXML
    private TextField main_addemployee_contactrelationship;

    @FXML
    private ComboBox<?> main_addemployee_dept;

    @FXML
    private TextField main_addemployee_empid;

    @FXML
    private ComboBox<?> main_addemployee_empstatus;

    @FXML
    private TextField main_addemployee_ext;

    @FXML
    private TextField main_addemployee_fname;

    @FXML
    private ComboBox<?> main_addemployee_gender;

    @FXML
    private TextField main_addemployee_lname;

    @FXML
    private ComboBox<?> main_addemployee_maritalstatus;

    @FXML
    private TextField main_addemployee_mname;

    @FXML
    private TextField main_addemployee_nationality;

    @FXML
    private TextField main_addemployee_number;

    @FXML
    private TextField main_addemployee_position;

    @FXML
    private ComboBox<?> main_addemployee_status;

    @FXML
    private Pane main_admin_panel1;

    @FXML
    private Button addemployee_upload_button;

    //              ADD EMPLOYEEEE VARIABLES ENDS HERE

    @FXML
    void clickNavigation_Choices(ActionEvent event) {
        System.out.println(event.getSource().toString());

        //Add Employee Button
        if (event.getSource() == main_addemployee_button) {
            main_addemployee_panel_1.toFront();

            //Manage Employee Button
        } else if (event.getSource() == main_manageemployee_button) {
            main_manageemployee_panel_1.toFront();

        //Add Employee Next Button (to second page)
        } else if (event.getSource() == addemployee_button_next) {
            main_addemployee_panel_2.toFront();

        //Add Employee Back Button (back to first page)
        } else if (event.getSource() == addemployee_back_button) {
            main_addemployee_panel_1.toFront();

        //Admin Button
        } else if (event.getSource() == main_admin_button) {
            main_admin_panel.toFront();

        //Daily Attendance Button
        } else if (event.getSource() == main_dailyattendance_button) {
            main_dailyattendance_panel_1.toFront();

        //Attendance Report Button
        } else if (event.getSource() == main_attendancereport_button) {
            main_attendancereport_panel_1.toFront();

        //Payslip Button
        } else if (event.getSource() == main_payslip_button) {
            main_payslip_panel_1.toFront();

        //Payroll Summary Button
        } else if (event.getSource() == main_payrollsummary_button) {
            main_payrollsummary_panel_1.toFront();

        //Department Button page 1
        } else if (event.getSource() == main_department_button) {
            main_department_panel_1.toFront();

        //Department Button page 1 - Add Department Button
        } else if (event.getSource() == main_department_add_department_button) {
            main_department_add_department_panel.toFront();

        //Department Button page 1 - Add Department - Cancel Button
        } else if (event.getSource() == main_department_add_department_cancel_button) {
            main_department_panel_1.toFront();

        //Department Button page 1 - Edit Department Button
        } else if (event.getSource() == main_department_edit_department_button) {
            main_department_edit_department_panel.toFront();

        //Department Button page 1 - Edit Department - Cancel Button
        } else if (event.getSource() == main_department_edit_department_cancel_button) {
            main_department_panel_1.toFront();

        //Department Button page 2 - Shift page
        } else if (event.getSource() == main_department_shift_button) {
            main_department_shift_panel_1.toFront();

        //Department Button back to Page 1 from Page 2
        } else if (event.getSource() == main_department_shift_back_button) {
            main_department_panel_1.toFront();

        //Department Button page 2 - Shift page - Add Shift
        } else if (event.getSource() == main_department_add_shift_button) {
            main_department_add_shift_panel.toFront();

        //Department Button page 2 - Shift page - Add Shift - Cancel button
        } else if (event.getSource() == main_department_add_shift_cancel_button) {
            main_department_shift_panel_1.toFront();

        //Holiday Button
        } else if (event.getSource() == main_holiday_button) {
            main_holiday_panel_1.toFront();

        //Holiday Button - Add Holiday
        } else if (event.getSource() == main_holiday_add_holiday_button) {
            main_holiday_add_holiday_panel_1.toFront();

        //Holiday Button
        } else if (event.getSource() == main_holiday_add_holiday_cancel_button) {
            main_holiday_panel_1.toFront();

        //Bonus Button
        } else if (event.getSource() == main_bonus_button) {
            main_bonus_panel_1.toFront();

        //Bonus Button - Add Bonus
        } else if (event.getSource() == main_bonus_add_bonus_button) {
            main_bonus_add_bonus_panel_1.toFront();

        //Bonus Button - Add Bonus - Cancel button
        } else if (event.getSource() == main_bonus_add_bonus_cancel_button) {
            main_bonus_panel_1.toFront();

        //Credentials Button
        } else if (event.getSource() == main_credentials_button) {
            main_credentials_panel_1.toFront();

        //Logout Button
        } else if (event.getSource() == main_logout_button) {
            System.exit(0);
        }
    }
}