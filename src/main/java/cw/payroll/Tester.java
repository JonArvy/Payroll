package cw.payroll;

import Database.SQLExecution;
import Database.*;
import Models.Admin;
import Models.Attendance;
import Models.Employee;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;

public class Tester {
    private SQLExecution sqlExecution = new SQLExecution();
    private SQLExecutionTesting sqlExecutionTesting = new SQLExecutionTesting();
    private SQLAdmin sqlAdmin = new SQLAdmin();
    private SQLAttendance sqlAttendance = new SQLAttendance();
    private SQLBonus sqlBonus = new SQLBonus();
    private SQLDepartment sqlDepartment = new SQLDepartment();
    private SQLEmployee sqlEmployee = new SQLEmployee();
    private SQLHoliday sqlHoliday = new SQLHoliday();
    private SQLNoticeboard sqlNoticeboard = new SQLNoticeboard();
    private SQLShift sqlShift = new SQLShift();

    public static void main(String[] args) {
//        new Tester().insertDateAndTime();
        Tester tester = new Tester();
//        tester.dropTables();
//        tester.createTables();
//        tester.insertDateAndTime();
//        tester.deleteDate();

    }

    public Tester() {
//        sqlAdmin.addAdmin(new Admin(0, "admin", 0, 0));
    }

    public void createTables() {
        sqlExecution.createTables();
//        sqlExecutionTesting.createTables();
    }

    public void dropTables() {
        sqlExecution.dropTables();

//        sqlExecutionTesting.dropTables();
    }
}
