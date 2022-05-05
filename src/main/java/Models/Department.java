package Models;

public class Department {
    private int Department_ID;
    private String Department_Name;
    double Department_MonthlyRate;
    int Department_DaysPerMonth;
    int Department_HoursPerDay;

    public Department(int Department_ID, String Department_Name, double Department_MonthlyRate, int Department_DaysPerMonth, int Department_HoursPerDay) {
        this.Department_ID = Department_ID;
        this.Department_Name = Department_Name;
        this.Department_MonthlyRate = Department_MonthlyRate;
        this.Department_DaysPerMonth = Department_DaysPerMonth;
        this.Department_HoursPerDay = Department_HoursPerDay;

    }

    public int getDepartment_ID() {
        return Department_ID;
    }

    public String getDepartment_Name() {
        return Department_Name;
    }

    public double getDepartment_MonthlyRate() {
        return Department_MonthlyRate;
    }

    public int getDepartment_DaysPerMonth() {
        return Department_DaysPerMonth;
    }

    public int getDepartment_HoursPerDay() {
        return Department_HoursPerDay;
    }
}
