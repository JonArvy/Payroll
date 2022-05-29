package Models;

import java.sql.Date;

public class Bonus {
    private int Bonus_ID;
    private String Bonus_Name;
    private double Bonus_Amount;
    private int Bonus_Recipient;
    private Date Bonus_Date;

    private String Recipient_Name;

    public Bonus(int Bonus_ID, String Bonus_Name, double Bonus_Amount, int Bonus_Recipient, Date Bonus_Date) {
        this.Bonus_ID = Bonus_ID;
        this.Bonus_Name = Bonus_Name;
        this.Bonus_Amount = Bonus_Amount;
        this.Bonus_Recipient = Bonus_Recipient;
        this.Bonus_Date = Bonus_Date;
    }

    public Bonus(int Bonus_ID, String Bonus_Name, double Bonus_Amount, String Recipient_Name, Date Bonus_Date) {
        this.Bonus_ID = Bonus_ID;
        this.Bonus_Name = Bonus_Name;
        this.Bonus_Amount = Bonus_Amount;
        this.Recipient_Name = Recipient_Name;
        this.Bonus_Date = Bonus_Date;
    }

    public Bonus(String Bonus_Name, double Bonus_Amount, String Recipient_Name, Date Bonus_Date) {
        this.Bonus_ID = Bonus_ID;
        this.Bonus_Name = Bonus_Name;
        this.Bonus_Amount = Bonus_Amount;
        this.Recipient_Name = Recipient_Name;
        this.Bonus_Date = Bonus_Date;
    }

    public Bonus(String Bonus_Name, double Bonus_Amount, int Bonus_Recipient, Date Bonus_Date) {
        this.Bonus_Name = Bonus_Name;
        this.Bonus_Amount = Bonus_Amount;
        this.Bonus_Recipient = Bonus_Recipient;
        this.Bonus_Date = Bonus_Date;
    }

    public Bonus(int Bonus_ID) {
        this.Bonus_ID = Bonus_ID;
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

    public String getRecipient_Name() {
        return Recipient_Name;
    }

    public void setBonus_ID(int bonus_ID) {
        Bonus_ID = bonus_ID;
    }

    public void setBonus_Name(String bonus_Name) {
        Bonus_Name = bonus_Name;
    }

    public void setBonus_Amount(double bonus_Amount) {
        Bonus_Amount = bonus_Amount;
    }

    public void setBonus_Recipient(int bonus_Recipient) {
        Bonus_Recipient = bonus_Recipient;
    }

    public void setBonus_Date(Date bonus_Date) {
        Bonus_Date = bonus_Date;
    }

    public void setRecipient_Name(String recipient_Name) {
        Recipient_Name = recipient_Name;
    }
}
