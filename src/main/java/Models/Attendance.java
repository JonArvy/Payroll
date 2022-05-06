package Models;

import java.sql.Date;
import java.sql.Time;

public class Attendance {
    private int Attendance_ID;

    private int Employee_ID;
    private Date Employee_Attendance_Date;
    private Time Employee_TimeIn;
    private Time Employee_TimeOut;

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

    public int getAttendance_ID() {
        return Attendance_ID;
    }

    public int getEmployee_ID() {
        return Employee_ID;
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

}
