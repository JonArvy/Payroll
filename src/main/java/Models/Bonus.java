package Models;

import java.sql.Date;

public class Bonus {
    private int Bonus_ID;
    private String Bonus_Name;
    private double Bonus_Amount;
    private int Bonus_Recipient;
    private Date Bonus_Date;

    public Bonus(int Bonus_ID, String Bonus_Name, double Bonus_Amount, int Bonus_Recipient, Date Bonus_Date) {
        this.Bonus_ID = Bonus_ID;
        this.Bonus_Name = Bonus_Name;
        this.Bonus_Amount = Bonus_Amount;
        this.Bonus_Recipient = Bonus_Recipient;
        this.Bonus_Date = Bonus_Date;
    }

    public int getBonus_ID() {
        return Bonus_ID;
    }

    public String getBonus_Name() {
        return Bonus_Name;
    }

    public double getBonus_Amount() {
        return Bonus_Amount;
    }

    public int getBonus_Recipient() {
        return Bonus_Recipient;
    }

    public Date getBonus_Date() {
        return Bonus_Date;
    }
}
