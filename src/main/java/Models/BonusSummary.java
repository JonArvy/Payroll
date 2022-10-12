package Models;

public class BonusSummary {
    private int Bonus_ID;
    private String Fullname;
    private int Emp_ID;
    private String Department;
    private double Amount;

    public BonusSummary(int bonus_ID, String fullname, int emp_ID, String department, double amount) {
        Bonus_ID = bonus_ID;
        Fullname = fullname;
        Emp_ID = emp_ID;
        Department = department;
        Amount = amount;
    }

    public int getBonus_ID() {
        return Bonus_ID;
    }

    public void setBonus_ID(int bonus_ID) {
        Bonus_ID = bonus_ID;
    }

    public String getFullname() {
        return Fullname;
    }

    public void setFullname(String fullname) {
        Fullname = fullname;
    }

    public int getEmp_ID() {
        return Emp_ID;
    }

    public void setEmp_ID(int emp_ID) {
        Emp_ID = emp_ID;
    }

    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String department) {
        Department = department;
    }

    public double getAmount() {
        return Amount;
    }

    public void setAmount(double amount) {
        Amount = amount;
    }
}
