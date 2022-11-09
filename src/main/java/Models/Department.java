package Models;

import java.sql.Time;
import java.text.DecimalFormat;
import java.text.ParseException;

public class Department {
    private int Department_ID;
    private String Department_Name;
    private double Department_MonthlyRate;
    private int Department_DaysPerMonth;
    private int Department_HoursPerDay;

    private double Daily_Rate;
    private double Hourly_Rate;
    //Shift later here

    private Time Time_In;
    private Time Time_Out;

    private Time Break_Start;
    private Time Break_End;

    private boolean Shift_Sunday;
    private boolean Shift_Monday;
    private boolean Shift_Tuesday;
    private boolean Shift_Wednesday;
    private boolean Shift_Thursday;
    private boolean Shift_Friday;
    private boolean Shift_Saturday;

    public Department(int department_ID, String department_Name, double department_MonthlyRate, int department_DaysPerMonth, int department_HoursPerDay, double daily_Rate, double hourly_Rate, Time time_In, Time time_Out, Time break_Start, Time break_End, boolean shift_Sunday, boolean shift_Monday, boolean shift_Tuesday, boolean shift_Wednesday, boolean shift_Thursday, boolean shift_Friday, boolean shift_Saturday) {
        this.Department_ID = department_ID;
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

        Time_In = time_In;
        Time_Out = time_Out;
        Break_Start = break_Start;
        Break_End = break_End;
        Shift_Sunday = shift_Sunday;
        Shift_Monday = shift_Monday;
        Shift_Tuesday = shift_Tuesday;
        Shift_Wednesday = shift_Wednesday;
        Shift_Thursday = shift_Thursday;
        Shift_Friday = shift_Friday;
        Shift_Saturday = shift_Saturday;
    }

    public Department(String department_Name, double department_MonthlyRate, int department_DaysPerMonth, int department_HoursPerDay, Time time_In, Time time_Out, Time break_Start, Time break_End, boolean shift_Sunday, boolean shift_Monday, boolean shift_Tuesday, boolean shift_Wednesday, boolean shift_Thursday, boolean shift_Friday, boolean shift_Saturday) {
        DecimalFormat df = new DecimalFormat("#.00");
        String dailyRateString = df.format(department_MonthlyRate / department_DaysPerMonth);
        String hourlyRateString = df.format(department_MonthlyRate / department_DaysPerMonth / department_HoursPerDay);
        this.Department_Name = department_Name;
        this.Department_MonthlyRate = department_MonthlyRate;
        this.Department_DaysPerMonth = department_DaysPerMonth;
        this.Department_HoursPerDay = department_HoursPerDay;
        this.Daily_Rate = Double.parseDouble(dailyRateString);
        this.Hourly_Rate = Double.parseDouble(hourlyRateString);

        Time_In = time_In;
        Time_Out = time_Out;
        Break_Start = break_Start;
        Break_End = break_End;
        Shift_Sunday = shift_Sunday;
        Shift_Monday = shift_Monday;
        Shift_Tuesday = shift_Tuesday;
        Shift_Wednesday = shift_Wednesday;
        Shift_Thursday = shift_Thursday;
        Shift_Friday = shift_Friday;
        Shift_Saturday = shift_Saturday;
    }

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

    public Department(String Department_Name, double Department_MonthlyRate, int Department_DaysPerMonth, int Department_HoursPerDay) throws ParseException {
        DecimalFormat df = new DecimalFormat("#.00");
        String dailyRateString = df.format(Department_MonthlyRate / Department_DaysPerMonth);
        String hourlyRateString = df.format(Department_MonthlyRate / Department_DaysPerMonth / Department_HoursPerDay);
        this.Department_Name = Department_Name;
        this.Department_MonthlyRate = Department_MonthlyRate;
        this.Department_DaysPerMonth = Department_DaysPerMonth;
        this.Department_HoursPerDay = Department_HoursPerDay;
        this.Daily_Rate = Double.parseDouble(dailyRateString);
        this.Hourly_Rate = Double.parseDouble(hourlyRateString);
    }

    public Department(int department_ID, String department_Name) {
        Department_ID = department_ID;
        Department_Name = department_Name;
    }

    public Department(int department_ID) {
        Department_ID = department_ID;
    }

    public Department() {
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

    public void setDepartment_ID(int department_ID) {
        Department_ID = department_ID;
    }

    public void setDepartment_Name(String department_Name) {
        Department_Name = department_Name;
    }

    public void setDepartment_MonthlyRate(double department_MonthlyRate) {
        Department_MonthlyRate = department_MonthlyRate;
    }

    public void setDepartment_DaysPerMonth(int department_DaysPerMonth) {
        Department_DaysPerMonth = department_DaysPerMonth;
    }

    public void setDepartment_HoursPerDay(int department_HoursPerDay) {
        Department_HoursPerDay = department_HoursPerDay;
    }

    public void setDaily_Rate(double daily_Rate) {
        Daily_Rate = daily_Rate;
    }

    public void setHourly_Rate(double hourly_Rate) {
        Hourly_Rate = hourly_Rate;
    }

    public Time getTime_In() {
        return Time_In;
    }

    public void setTime_In(Time time_In) {
        Time_In = time_In;
    }

    public Time getTime_Out() {
        return Time_Out;
    }

    public void setTime_Out(Time time_Out) {
        Time_Out = time_Out;
    }

    public Time getBreak_Start() {
        return Break_Start;
    }

    public void setBreak_Start(Time break_Start) {
        Break_Start = break_Start;
    }

    public Time getBreak_End() {
        return Break_End;
    }

    public void setBreak_End(Time break_End) {
        Break_End = break_End;
    }

    public boolean isShift_Sunday() {
        return Shift_Sunday;
    }

    public void setShift_Sunday(boolean shift_Sunday) {
        Shift_Sunday = shift_Sunday;
    }

    public boolean isShift_Monday() {
        return Shift_Monday;
    }

    public void setShift_Monday(boolean shift_Monday) {
        Shift_Monday = shift_Monday;
    }

    public boolean isShift_Tuesday() {
        return Shift_Tuesday;
    }

    public void setShift_Tuesday(boolean shift_Tuesday) {
        Shift_Tuesday = shift_Tuesday;
    }

    public boolean isShift_Wednesday() {
        return Shift_Wednesday;
    }

    public void setShift_Wednesday(boolean shift_Wednesday) {
        Shift_Wednesday = shift_Wednesday;
    }

    public boolean isShift_Thursday() {
        return Shift_Thursday;
    }

    public void setShift_Thursday(boolean shift_Thursday) {
        Shift_Thursday = shift_Thursday;
    }

    public boolean isShift_Friday() {
        return Shift_Friday;
    }

    public void setShift_Friday(boolean shift_Friday) {
        Shift_Friday = shift_Friday;
    }

    public boolean isShift_Saturday() {
        return Shift_Saturday;
    }

    public void setShift_Saturday(boolean shift_Saturday) {
        Shift_Saturday = shift_Saturday;
    }
}
