package Models;

import java.sql.Date;
import java.sql.Time;

public class Attendance {
    private int Attendance_ID;

    private int Employee_ID;
    private String Employee_FullName;
    private String Department_Name;
    private String Employee_Position;

    private Date Employee_Attendance_Date;
    private Time Employee_TimeIn;
    private Time Employee_TimeOut;

    private int Employee_TotalHours;

    public Attendance() {

    }

    public Attendance(int Attendance_ID, int Employee_ID, Date Employee_Attendance_Date, Time Employee_TimeIn, Time Employee_TimeOut) {
        this.Attendance_ID = Attendance_ID;
        this.Employee_ID = Employee_ID;
        this.Employee_Attendance_Date = Employee_Attendance_Date;
        this.Employee_TimeIn = Employee_TimeIn;
        this.Employee_TimeOut = Employee_TimeOut;
    }

    public Attendance(int Employee_ID, Date Employee_Attendance_Date, Time Employee_TimeIn, Time Employee_TimeOut) {
        this.Employee_ID = Employee_ID;
        this.Employee_Attendance_Date = Employee_Attendance_Date;
        this.Employee_TimeIn = Employee_TimeIn;
        this.Employee_TimeOut = Employee_TimeOut;
    }

    public Attendance(int attendance_ID, int employee_ID, String employee_FullName, String department_Name, String employee_Position, Date employee_Attendance_Date, Time employee_TimeIn, Time employee_TimeOut) {
        Attendance_ID = attendance_ID;
        Employee_ID = employee_ID;
        Employee_FullName = employee_FullName;
        Department_Name = department_Name;
        Employee_Position = employee_Position;
        Employee_Attendance_Date = employee_Attendance_Date;
        Employee_TimeIn = employee_TimeIn;
        Employee_TimeOut = employee_TimeOut;
    }

    public Attendance(int employee_ID, Date employee_Attendance_Date) {
        Employee_ID = employee_ID;
        Employee_Attendance_Date = employee_Attendance_Date;
    }

    public int getAttendance_ID() {
        return Attendance_ID;
    }

    public int getEmployee_ID() {
        return Employee_ID;
    }

    public String getEmployee_FullName() {
        return Employee_FullName;
    }

    public String getDepartment_Name() {
        return Department_Name;
    }

    public String getEmployee_Position() {
        return Employee_Position;
    }

    public Date getEmployee_Attendance_Date() {
        return Employee_Attendance_Date;
    }

    public Time getEmployee_TimeIn() {
        return Employee_TimeIn;
    }

    public Time getEmployee_TimeOut() {
        return Employee_TimeOut;
    }

    public void setAttendance_ID(int attendance_ID) {
        Attendance_ID = attendance_ID;
    }

    public void setEmployee_ID(int employee_ID) {
        Employee_ID = employee_ID;
    }

    public void setEmployee_FullName(String employee_FullName) {
        Employee_FullName = employee_FullName;
    }

    public void setDepartment_Name(String department_Name) {
        Department_Name = department_Name;
    }

    public void setEmployee_Position(String employee_Position) {
        Employee_Position = employee_Position;
    }

    public void setEmployee_Attendance_Date(Date employee_Attendance_Date) {
        Employee_Attendance_Date = employee_Attendance_Date;
    }

    public void setEmployee_TimeIn(Time employee_TimeIn) {
        Employee_TimeIn = employee_TimeIn;
    }

    public void setEmployee_TimeOut(Time employee_TimeOut) {
        Employee_TimeOut = employee_TimeOut;
    }


    public int getEmployee_TotalHours() {
        return Employee_TotalHours;
    }

    public void setEmployee_TotalHours(int employee_TotalHours) {
        Employee_TotalHours = employee_TotalHours;
    }
}
