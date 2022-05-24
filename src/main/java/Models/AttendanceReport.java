package Models;

import java.sql.Time;

public class AttendanceReport {
    private int Number;
    private String Full_Name;
    private int Employee_ID;
    private Department department;
    private String Position;
    private int Present;
    private int Absent;
    private Time Late;
    private int Holiday;

    public AttendanceReport(int number, String full_Name, int employee_ID, Department department, String position, int present, int absent, Time late, int holiday) {
        Number = number;
        Full_Name = full_Name;
        Employee_ID = employee_ID;
        this.department = department;
        Position = position;
        Present = present;
        Absent = absent;
        Late = late;
        Holiday = holiday;
    }

    public int getNumber() {
        return Number;
    }

    public String getFull_Name() {
        return Full_Name;
    }

    public int getEmployee_ID() {
        return Employee_ID;
    }

    public Department getDepartment() {
        return department;
    }

    public String getPosition() {
        return Position;
    }

    public int getPresent() {
        return Present;
    }

    public int getAbsent() {
        return Absent;
    }

    public Time getLate() {
        return Late;
    }

    public int getHoliday() {
        return Holiday;
    }
}
