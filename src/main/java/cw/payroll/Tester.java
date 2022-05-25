package cw.payroll;

import Classes.SQLExecution;
import Database.*;
import Models.Attendance;
import Models.Employee;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;

public class Tester {
    private SQLExecution sqlExecution = new SQLExecution();
    private SQLAdmin sqlAdmin = new SQLAdmin();
    private SQLAttendance sqlAttendance = new SQLAttendance();
    private SQLBonus sqlBonus = new SQLBonus();
    private SQLDepartment sqlDepartment = new SQLDepartment();
    private SQLEmployee sqlEmployee = new SQLEmployee();
    private SQLHoliday sqlHoliday = new SQLHoliday();
    private SQLNoticeboard sqlNoticeboard = new SQLNoticeboard();
    private SQLShift sqlShift = new SQLShift();
    public static void main(String[] args) {
        new Tester().insertDateAndTime();



    }

    public void createTables() {
        sqlExecution.createTables();
    }

    public void dropTables() {
        sqlExecution.dropTables();
    }

    public void insertDateAndTime() {
        int empid = 1;
        Date attendance_date = Date.valueOf("2022-05-11");
        Time attendance_timein = Time.valueOf("08:30:00");
        Time attendance_timeout = Time.valueOf("16:20:00");
        sqlAttendance.registerAttendance(new Attendance(empid, attendance_date, attendance_timein, attendance_timeout));
    }
    public void populateEmployeeTable() {
        sqlExecution.createTables();

        String letters = "abcdefghijklmnopqrstuvwxyz";
        for (int i = 0; i < 100; i++) {
            Employee rand = new Employee(
                    i,
                    "Filipino",
                    "Single",
                    i % 5,
                    "i % 3",
                    "Permanent",

                    letters.charAt(i % 26) + "",
                    letters.charAt((i + 20) % 26) + "",
                    letters.charAt((i + 10) % 26) + "",
                    letters.charAt((i + 5) % 26) + "",
                    letters.charAt(i % 26) + " " + letters.charAt((i + 20) % 26),

                    true,

                    "asd",

                    Date.valueOf(LocalDate.now()),
                    true,

                    "private String Contact_Name;",
                    "private String Contact_Relationship;",
                    "private String Contact_Address;",

                    "private String Contact_Number;",
                    "Mema"
            );
            sqlEmployee.addEmployee(rand);
        }
    }
}
