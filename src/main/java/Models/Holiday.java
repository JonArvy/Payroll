package Models;

import java.sql.Date;

public class Holiday {
    private int Holiday_ID;
    private String Holiday_Name;
    private Date Holiday_Date;
    private String Holiday_Type;
    private String Holiday_Repeat;

    public Holiday(int holiday_ID, String holiday_Name, Date holiday_Date, String holiday_Type, String holiday_Repeat) {
        Holiday_ID = holiday_ID;
        Holiday_Date = holiday_Date;
        Holiday_Name = holiday_Name;
        Holiday_Type = holiday_Type;
        Holiday_Repeat = holiday_Repeat;
    }

    public Holiday(String holiday_Name, Date holiday_Date, String holiday_Type, String holiday_Repeat) {
        Holiday_Name = holiday_Name;
        Holiday_Date = holiday_Date;
        Holiday_Type = holiday_Type;
        Holiday_Repeat = holiday_Repeat;
    }

    public Holiday(String holiday_Name, Date holiday_Date, String holiday_Type) {
        Holiday_Name = holiday_Name;
        Holiday_Date = holiday_Date;
        Holiday_Type = holiday_Type;
    }

    public Holiday(int holiday_ID) {
        Holiday_ID = holiday_ID;
    }



    public int getHoliday_ID() {
        return Holiday_ID;
    }

    public Date getHoliday_Date() {
        return Holiday_Date;
    }

    public String getHoliday_Name() {
        return Holiday_Name;
    }

    public String getHoliday_Type() {
        return Holiday_Type;
    }

    public String getHoliday_Repeat() {
        return Holiday_Repeat;
    }

    public void setHoliday_ID(int holiday_ID) {
        Holiday_ID = holiday_ID;
    }

    public void setHoliday_Name(String holiday_Name) {
        Holiday_Name = holiday_Name;
    }

    public void setHoliday_Date(Date holiday_Date) {
        Holiday_Date = holiday_Date;
    }

    public void setHoliday_Type(String holiday_Type) {
        Holiday_Type = holiday_Type;
    }

    public void setHoliday_Repeat(String holiday_Repeat) {
        Holiday_Repeat = holiday_Repeat;
    }
}
