package Models;

public class Summary {
    private int Number;
    private String Name;
    private String Position;

    private double Wage;
    private double Benefits;
    private int PresentDays;
    private int AbsentDays;
    private double TotalCompensation;

    private double Less;
    private double TotalDeduction;
    private double NetAmount;


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
}
