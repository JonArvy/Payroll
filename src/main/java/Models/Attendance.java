package Models;

import java.sql.Time;

public class Attendance {
    private int Employee_ID;
    private Time Employee_TimeIn;
    private Time Employee_TimeOut;
    private String Employee_Department;

    public Attendance() {

    }

    public Attendance(int Employee_ID, Time Employee_TimeIn, Time Employee_TimeOut, String Employee_Department) {
        this.Employee_ID = Employee_ID;
        this.Employee_TimeIn = Employee_TimeIn;
        this.Employee_TimeOut = Employee_TimeOut;
        this.Employee_Department = Employee_Department;
    }

    public int getEmployee_ID() {
        return Employee_ID;
    }

    public Time getEmployee_TimeIn() {
        return Employee_TimeIn;
    }

    public Time getEmployee_TimeOut() {
        return Employee_TimeOut;
    }

    public String getEmployee_Department() {
        return Employee_Department;
    }
}
