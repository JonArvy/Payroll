package Models;

public class Shift {
    private int Shift_ID;
    private int Shift_Type;
    private int Shift_Recipient;
    private boolean Shift_Sunday;
    private boolean Shift_Monday;
    private boolean Shift_Tuesday;
    private boolean Shift_Wednesday;
    private boolean Shift_Thursday;
    private boolean Shift_Friday;
    private boolean Shift_Saturday;


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
}
