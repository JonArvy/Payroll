package Classes;

import java.sql.Date;
import java.sql.Time;
import java.time.Duration;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class DateTimeCalculator {
    private static int gracePeriod = 15;

    public static void main(String[] args) {
        Time sti = Time.valueOf("08:00:00");
        Time sto = Time.valueOf("17:00:00");

        Time sbs = Time.valueOf("12:00:00");
        Time sbe = Time.valueOf("13:00:00");

        Time eti = Time.valueOf("08:00:00");
        Time eto = Time.valueOf("17:00:00");

        getTotalHours(sti, sto, sbs, sbe, eti, eto);
    }

    public static long getTotalHours(Time System_Time_In, Time System_Time_Out,
                                     Time System_Break_Start, Time System_Break_End,
                                     Time Employee_Time_In, Time Employee_Time_Out) {

        long time_in = Duration.between(System_Time_In.toLocalTime(), Employee_Time_In.toLocalTime()).toMinutes();
        long time_out = Duration.between(System_Time_Out.toLocalTime(), Employee_Time_Out.toLocalTime()).toMinutes();
        long registered_time_difference = Duration.between(Employee_Time_In.toLocalTime(), Employee_Time_Out.toLocalTime()).toMinutes();

        Time temp_employee_time_in = Time.valueOf("00:00:00");
        Time temp_employee_time_out = Time.valueOf("00:00:00");

//        System.out.println(time_in);
//        System.out.println(time_out);

//        System.out.println("----------------------------------");
        //Time In
        if (time_in <= 0) {
//            System.out.println("You are early or on time");
            temp_employee_time_in = System_Time_In;
        } else if (time_in > 0 && time_in <= gracePeriod) {
//            System.out.println("You are late but within the grace period");
            temp_employee_time_in = System_Time_In;
        } else {
//            System.out.println("You are late");
            temp_employee_time_in = roundUpTime(Employee_Time_In);
            //Calculation for counting how many late hours

        }

//        System.out.println("----------------------------------");
        //Time Out
        if (time_out >= 0) {
//            System.out.println("You left on time or greater");
            temp_employee_time_out = System_Time_Out;
        } else {
//            System.out.println("You left early");
            temp_employee_time_out = roundDownTime(Employee_Time_Out);
            //Calculation for counting how many early hours
        }

//        System.out.println("----------------------------------");
        if (Employee_Time_In.before(System_Break_Start) || Employee_Time_In.after(System_Break_End)) {
//            System.out.println("You are on duty during your break");
        } else {
//            System.out.println("You are not on duty during your break");
        }

//        System.out.println("----------------------------------");
        long total_break_hours = Duration.between(System_Break_Start.toLocalTime(), System_Break_End.toLocalTime()).toMinutes();
        long total_hours = Duration.between(temp_employee_time_in.toLocalTime(), temp_employee_time_out.toLocalTime()).toHours();

//        System.out.println("Total Break Hours: " + total_break_hours);
//        System.out.println("Total Hours: " + total_hours);
//
//        System.out.println("Subtracted Hours: " + (total_hours - (total_break_hours / 60)));
//
//
//        System.out.println("----------------------------------");

//        System.out.println(roundUpTime(Employee_Time_In));
//        System.out.println(roundDownTime(Employee_Time_Out));
        long subtracted_hours = total_hours - (total_break_hours / 60);

        System.out.println(registered_time_difference);
        if (registered_time_difference < 60) {
            subtracted_hours = 0;
        }

        return subtracted_hours;
    }


    public static Time roundUpTime(Time time) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(time);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        if (minute >= 1) {
            hour++;
        }

        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, 0);

        return new Time(calendar.getTimeInMillis());
    }

    public static Time roundDownTime(Time time) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(time);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);

        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, 0);

        return new Time(calendar.getTimeInMillis());
    }




    //For getting Date names and value
    //No calculation within


    public static String getDateName(Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        String name = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault()) + " " + calendar.get(Calendar.DAY_OF_MONTH) + ", " + calendar.get(Calendar.YEAR);

        return name;
    }

    public static String getMonthName(Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        String month = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());

        return month;
    }

    public static int getMonthNumber(Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        int month = calendar.get(Calendar.MONTH);

        return month + 1;
    }

    public static int getYear(Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);

        return year;
    }

//    public static Time subtractTime(Time time1, long time2) {
//        Calendar calendar = new GregorianCalendar();
//        calendar.setTime(time1);
//        int hour = calendar.get(Calendar.HOUR_OF_DAY);
//        int minute = calendar.get(Calendar.MINUTE);
//
//        int t2_minute = (int) time2 % 60;
//        int t2_hour = (int) time2 / 60;
//
//        calendar.set(Calendar.HOUR_OF_DAY, hour + t2_hour);
//        calendar.set(Calendar.MINUTE, minute + t2_minute);
//
//        return new Time(calendar.getTimeInMillis());
//    }

    public static void getMinuteDifference() {

    }
}
