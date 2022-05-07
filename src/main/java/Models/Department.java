package Models;

import java.text.DecimalFormat;
import java.text.ParseException;

public class Department {
    private int Department_ID;
    private String Department_Name;
    double Department_MonthlyRate;
    int Department_DaysPerMonth;
    int Department_HoursPerDay;

    double Daily_Rate;
    double Hourly_Rate;
    //Shift later here

    public Department(int Department_ID, String Department_Name, double Department_MonthlyRate, int Department_DaysPerMonth, int Department_HoursPerDay) throws ParseException {
        DecimalFormat df = new DecimalFormat("#.00");
        String dailyRateString = df.format(Department_MonthlyRate / Department_DaysPerMonth);
        String hourlyRateString = df.format(Department_MonthlyRate / Department_DaysPerMonth / Department_HoursPerDay);
        this.Department_ID = Department_ID;
        this.Department_Name = Department_Name;
        this.Department_MonthlyRate = Department_MonthlyRate;
        this.Department_DaysPerMonth = Department_DaysPerMonth;
        this.Department_HoursPerDay = Department_HoursPerDay;
        this.Daily_Rate = Double.parseDouble(dailyRateString);
        this.Hourly_Rate = Double.parseDouble(hourlyRateString);
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

    public double getDaily_Rate() {
        return Daily_Rate;
    }

    public double getHourly_Rate() {
        return Hourly_Rate;
    }
}
