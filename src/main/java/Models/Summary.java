package Models;

import java.sql.Date;

public class Summary {

    private int DatabaseID;

    public int getDatabaseID() {
        return DatabaseID;
    }

    public void setDatabaseID(int databaseID) {
        DatabaseID = databaseID;
    }

    private int Number;

    private int EmployeeNumber;
    private String Name;

    private Date DateCreated;

    private String Department;

    private double MonthlyRate;

    private int LateUT;

    private String Position;

    private double Wage;
    private double Benefits;
    private int PresentDays;
    private int AbsentDays;
    private double TotalCompensation;

    private double Less;
    private double TotalDeduction;
    private double NetAmount;


    public Summary(int number, String name, String position) {
        Number = number;
        Name = name;
        Position = position;
    }


    public Summary(int number, String name, String position, double wage, double benefits, int presentDays, int absentDays, double totalCompensation, double less, double totalDeduction, double netAmount) {
        Number = number;
        Name = name;
        Position = position;
        Wage = wage;
        Benefits = benefits;
        PresentDays = presentDays;
        AbsentDays = absentDays;
        TotalCompensation = totalCompensation;
        Less = less;
        TotalDeduction = totalDeduction;
        NetAmount = netAmount;
    }

    public Summary() {

    }

    public int getNumber() {
        return Number;
    }

    public void setNumber(int number) {
        Number = number;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPosition() {
        return Position;
    }

    public void setPosition(String position) {
        Position = position;
    }

    public double getWage() {
        return Wage;
    }

    public void setWage(double wage) {
        Wage = wage;
    }

    public double getBenefits() {
        return Benefits;
    }

    public void setBenefits(double benefits) {
        Benefits = benefits;
    }

    public int getPresentDays() {
        return PresentDays;
    }

    public void setPresentDays(int presentDays) {
        PresentDays = presentDays;
    }

    public int getAbsentDays() {
        return AbsentDays;
    }

    public void setAbsentDays(int absentDays) {
        AbsentDays = absentDays;
    }

    public double getTotalCompensation() {
        return TotalCompensation;
    }

    public void setTotalCompensation(double totalCompensation) {
        TotalCompensation = totalCompensation;
    }

    public double getLess() {
        return Less;
    }

    public void setLess(double less) {
        Less = less;
    }

    public double getTotalDeduction() {
        return TotalDeduction;
    }

    public void setTotalDeduction(double totalDeduction) {
        TotalDeduction = totalDeduction;
    }

    public double getNetAmount() {
        return NetAmount;
    }

    public void setNetAmount(double netAmount) {
        NetAmount = netAmount;
    }

    public int getEmployeeNumber() {
        return EmployeeNumber;
    }

    public void setEmployeeNumber(int employeeNumber) {
        EmployeeNumber = employeeNumber;
    }

    public Date getDateCreated() {
        return DateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        DateCreated = dateCreated;
    }

    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String department) {
        Department = department;
    }

    public int getLateUT() {
        return LateUT;
    }

    public void setLateUT(int lateUT) {
        LateUT = lateUT;
    }

    public double getMonthlyRate() {
        return MonthlyRate;
    }

    public void setMonthlyRate(double monthlyRate) {
        MonthlyRate = monthlyRate;
    }
}
