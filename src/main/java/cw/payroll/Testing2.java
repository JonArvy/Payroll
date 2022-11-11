package cw.payroll;

import Database.SQLDepartment;
import Models.Department;
import javafx.collections.ObservableList;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

public class Testing2 {
    public static void main(String[] args) {
        LocalDate date = LocalDate.now();
        System.out.println(getDepartmentsInDate(date));

        for (Department dept : getDepartmentsInDate(date)) {
            System.out.println(dept.getDepartment_Name());
        }

    }

    /**
     *
     * @param date The date to check
     * @return An observable list of all the departments which has a schedule on the given date
     */
    private static List<Department> getDepartmentsInDate(LocalDate date) {
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
