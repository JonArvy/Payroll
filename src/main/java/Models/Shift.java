package Models;

import java.sql.Time;

public class Shift {
    private int Shift_ID;
    private int Shift_Type;
    private String Shift_Type_Name;

    private int Shift_Recipient;
    private String Recipient_Name;

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

    private String Shift_Schema;


    public Shift(int Shift_ID, int Shift_Type, int Shift_Recipient, boolean Shift_Sunday, boolean Shift_Monday, boolean Shift_Tuesday, boolean Shift_Wednesday, boolean Shift_Thursday, boolean Shift_Friday, boolean Shift_Saturday) {
        this.Shift_ID = Shift_ID;
        this.Shift_Type = Shift_Type;
        this.Shift_Recipient = Shift_Recipient;
        this.Shift_Sunday = Shift_Sunday;
        this.Shift_Monday = Shift_Monday;
        this.Shift_Tuesday = Shift_Tuesday;
        this.Shift_Wednesday = Shift_Wednesday;
        this.Shift_Thursday = Shift_Thursday;
        this.Shift_Friday = Shift_Friday;
        this.Shift_Saturday = Shift_Saturday;
        setShiftNameAndSchema();
    }

    public Shift(int Shift_ID, int Shift_Type, int Shift_Recipient, String Recipient_Name, boolean Shift_Sunday, boolean Shift_Monday, boolean Shift_Tuesday, boolean Shift_Wednesday, boolean Shift_Thursday, boolean Shift_Friday, boolean Shift_Saturday) {
        this.Shift_ID = Shift_ID;
        this.Shift_Type = Shift_Type;
        this.Shift_Recipient = Shift_Recipient;
        this.Recipient_Name = Recipient_Name;
        this.Shift_Sunday = Shift_Sunday;
        this.Shift_Monday = Shift_Monday;
        this.Shift_Tuesday = Shift_Tuesday;
        this.Shift_Wednesday = Shift_Wednesday;
        this.Shift_Thursday = Shift_Thursday;
        this.Shift_Friday = Shift_Friday;
        this.Shift_Saturday = Shift_Saturday;
        setShiftNameAndSchema();
    }

    public Shift(int Shift_Type, int Shift_Recipient, boolean Shift_Sunday, boolean Shift_Monday, boolean Shift_Tuesday, boolean Shift_Wednesday, boolean Shift_Thursday, boolean Shift_Friday, boolean Shift_Saturday) {
        this.Shift_Type = Shift_Type;
        this.Shift_Recipient = Shift_Recipient;
        this.Shift_Sunday = Shift_Sunday;
        this.Shift_Monday = Shift_Monday;
        this.Shift_Tuesday = Shift_Tuesday;
        this.Shift_Wednesday = Shift_Wednesday;
        this.Shift_Thursday = Shift_Thursday;
        this.Shift_Friday = Shift_Friday;
        this.Shift_Saturday = Shift_Saturday;
        setShiftNameAndSchema();
    }

    public Shift(int Shift_Type, int Shift_Recipient, Time Time_In, Time Time_Out, Time Break_Start, Time Break_End, boolean Shift_Sunday, boolean Shift_Monday, boolean Shift_Tuesday, boolean Shift_Wednesday, boolean Shift_Thursday, boolean Shift_Friday, boolean Shift_Saturday) {
        this.Shift_Type = Shift_Type;
        this.Shift_Recipient = Shift_Recipient;
        this.Time_In = Time_In;
        this.Time_Out = Time_Out;
        this.Break_Start = Break_Start;
        this.Break_End = Break_End;
        this.Shift_Sunday = Shift_Sunday;
        this.Shift_Monday = Shift_Monday;
        this.Shift_Tuesday = Shift_Tuesday;
        this.Shift_Wednesday = Shift_Wednesday;
        this.Shift_Thursday = Shift_Thursday;
        this.Shift_Friday = Shift_Friday;
        this.Shift_Saturday = Shift_Saturday;
        setShiftNameAndSchema();
    }

    private void setShiftNameAndSchema() {
        Shift_Schema = "";
        if (Shift_Type == 1) {
            Shift_Type_Name = "By Department";
        } else if (Shift_Type == 2) {
            Shift_Type_Name = "By Employee";
        }

        if (Shift_Sunday == true) {
            Shift_Schema += "SUN ";
        }
        if (Shift_Monday == true) {
            Shift_Schema += "MON ";
        }
        if (Shift_Tuesday == true) {
            Shift_Schema += "TUE ";
        }
        if (Shift_Wednesday == true) {
            Shift_Schema += "WED ";
        }
        if (Shift_Thursday == true) {
            Shift_Schema += "THU ";
        }
        if (Shift_Friday == true) {
            Shift_Schema += "FRI ";
        }
        if (Shift_Saturday == true) {
            Shift_Schema += "SAT ";
        }
    }

    public int getShift_ID() {
        return Shift_ID;
    }

    public int getShift_Type() {
        return Shift_Type;
    }

    public int getShift_Recipient() {
        return Shift_Recipient;
    }

    public String getRecipient_Name() {
        return Recipient_Name;
    }

    public boolean isShift_Sunday() {
        return Shift_Sunday;
    }

    public boolean isShift_Monday() {
        return Shift_Monday;
    }

    public boolean isShift_Tuesday() {
        return Shift_Tuesday;
    }

    public boolean isShift_Wednesday() {
        return Shift_Wednesday;
    }

    public boolean isShift_Thursday() {
        return Shift_Thursday;
    }

    public boolean isShift_Friday() {
        return Shift_Friday;
    }

    public boolean isShift_Saturday() {
        return Shift_Saturday;
    }

    public String getShift_Type_Name() {
        return Shift_Type_Name;
    }

    public String getShift_Schema() {
        return Shift_Schema;
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
}
