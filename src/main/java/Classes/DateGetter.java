package Classes;


import Database.SQLDepartment;
import Models.Department;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.sql.Date;
import java.util.stream.Collectors;

public class DateGetter {


//    public static void main(String[] args) {
//        String dateStart = "01/11/2022";
//        String dateStop = "01/12/2022";
//
//        System.out.println(Date.valueOf(LocalDate.now()).toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate());
//
//        System.out.println(java.time.ZoneId.systemDefault());
//
//
////        HashMap<DayOfWeek, Boolean> shiftDays = generateDays();
//
//        int counter = 0;
//
//        List<LocalDate> dates = getDatesBetween(LocalDate.parse(dateStart, DateTimeFormatter.ofPattern("dd/MM/yyyy")), LocalDate.parse(dateStop, DateTimeFormatter.ofPattern("dd/MM/yyyy")));
//
////        for (LocalDate date : dates) {
////            if (shiftDays.get(date.getDayOfWeek())) {
////                counter++;
////                System.out.println(date);
////            }
////        }
//
//        System.out.println(counter);
//    }

    public static void main(String[] args) {
        ObservableList<Date> dateList = getActiveDates(Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now().plusMonths(1)), false, true, true, true, true, true, false);
        for (Date date : dateList) {
            System.out.println(date);
        }
    }



    public static ObservableList<Date> getActiveDates(Date dt1, Date dt2,
                                                      boolean sunday, boolean monday, boolean tuesday, boolean wednesday, boolean thursday, boolean friday, boolean saturday) {
        ObservableList<Date> dateList = FXCollections.observableArrayList();
        HashMap<DayOfWeek, Boolean> shiftDays = new HashMap<>();

        shiftDays.put(DayOfWeek.SUNDAY, sunday);
        shiftDays.put(DayOfWeek.MONDAY, monday);
        shiftDays.put(DayOfWeek.TUESDAY, tuesday);
        shiftDays.put(DayOfWeek.WEDNESDAY, wednesday);
        shiftDays.put(DayOfWeek.THURSDAY, thursday);
        shiftDays.put(DayOfWeek.FRIDAY, friday);
        shiftDays.put(DayOfWeek.SATURDAY, saturday);

        int counter = 0;
        List<LocalDate> dates = getDatesBetween(dt1.toLocalDate(), dt2.toLocalDate());
        for (LocalDate date : dates) {
            if (shiftDays.get(date.getDayOfWeek())) {
                counter++;
                dateList.add(Date.valueOf(date));
//                System.out.println(date);
            }
        }
        return dateList;
    }

    public static int getActiveDateCount(Date dt1, Date dt2,
                                         boolean sunday, boolean monday, boolean tuesday, boolean wednesday, boolean thursday, boolean friday, boolean saturday) {
        ObservableList<Date> dateList = FXCollections.observableArrayList();
        HashMap<DayOfWeek, Boolean> shiftDays = new HashMap<>();

        shiftDays.put(DayOfWeek.SUNDAY, sunday);
        shiftDays.put(DayOfWeek.MONDAY, monday);
        shiftDays.put(DayOfWeek.TUESDAY, tuesday);
        shiftDays.put(DayOfWeek.WEDNESDAY, wednesday);
        shiftDays.put(DayOfWeek.THURSDAY, thursday);
        shiftDays.put(DayOfWeek.FRIDAY, friday);
        shiftDays.put(DayOfWeek.SATURDAY, saturday);

        int counter = 0;
        List<LocalDate> dates = getDatesBetween(dt1.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate(), dt2.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate());
        for (LocalDate date : dates) {
            if (shiftDays.get(date.getDayOfWeek())) {
                counter++;
                dateList.add(Date.valueOf(date));
                System.out.println(date);
            }
        }
        return counter;
    }


    public static List<LocalDate> getDatesBetween(LocalDate startDate, LocalDate endDate) {
        return startDate.datesUntil(endDate)
                .collect(Collectors.toList());
    }

    public static ObservableList<Department> getDepartmentsInDate(LocalDate date) {
        // get the day of the week
        SQLDepartment sqlDepartment = new SQLDepartment();
        DayOfWeek dayOfWeek = date.getDayOfWeek();

        // get the departments that have a schedule on that day

        return switch (dayOfWeek) {
            case MONDAY -> sqlDepartment.getDepartmentsWithDaySchedule("monday");
            case TUESDAY -> sqlDepartment.getDepartmentsWithDaySchedule("tuesday");
            case WEDNESDAY -> sqlDepartment.getDepartmentsWithDaySchedule("wednesday");
            case THURSDAY -> sqlDepartment.getDepartmentsWithDaySchedule("thursday");
            case FRIDAY -> sqlDepartment.getDepartmentsWithDaySchedule("friday");
            case SATURDAY -> sqlDepartment.getDepartmentsWithDaySchedule("saturday");
            case SUNDAY -> sqlDepartment.getDepartmentsWithDaySchedule("sunday");
        };
    }
}
