package Database;

public class SQLComments {
    //        String command = "SELECT " +
//                "te.emp_id as employee_id," +
//
//                "te.emp_fname || ' ' || te.emp_mname || ' ' || te.emp_lname as full_name," +
////                "td.department_name as department," +
//                "te.emp_position as employee_position," +

//                "(" +
//                "   SELECT " +
//                "   department_monthlyrate " +
//                "   FROM tbl_department" +
//                "   WHERE td.department_id = ta.emp_id" +
//                ") as montlywage," +

//                "(" +
//                "   SELECT " +
//                "   COUNT(*) " +
//                "   FROM tbl_attendance ta " +
//                "   WHERE ta.emp_id = te.emp_id " +
//                ") as present_days," +
//
//                "(" +
//                "   SELECT " +
//                "   tq.department_dayspermonth - " +
//                "   (" +
//                "       SELECT " +
//                "       COUNT(*) " +
//                "       FROM tbl_attendance ta " +
//                "       WHERE ta.emp_id = te.emp_id " +
//                "   )" +
//                "   FROM tbl_department tq " +
//                "   WHERE tq.department_id = td.department_id " +
//                ") as absent_days, " +

//                "(" +
//                "   SELECT " +
//                "   td.department_monthlyrate / td.department_dayspermonth" +
//                "   FROM td.tbl_department" +
//                "   WHERE td.department_id = ta.emp_id" +
//                ") as dailyrate," +
//
//                "(" +
//                "   SELECT " +
//                "   present_days * dailyrate" +
//                ") as total_from_presentdays," +
//
//                "( " +
//                "   SELECT " +
//                "   tq.department_dayspermonth - " +
//                "   ( " +
//                "       SELECT " +
//                "       COUNT(*) " +
//                "       FROM tbl_attendance ta " +
//                "       WHERE ta.emp_id = te.emp_id " +
//                "   ) " +
//                "   FROM tbl_department tq " +
//                "   WHERE tq.department_id = td.department_id " +
//                ") as late_hours," +
//
//                "( " +
//                "   SELECT " +
//                "   tm.department_dayspermonth - " +
//                "   ( " +
//                "       SELECT " +
//                "       COUNT(*) " +
//                "       FROM tbl_attendance ta " +
//                "       WHERE ta.emp_id = te.emp_id " +
//                "   ) " +
//                "   FROM tbl_department tm " +
//                "   WHERE tm.department_id = td.department_id " +
//                ") as holiday " +
//                "FROM tbl_employees te " +
//                "JOIN tbl_department td " +
//                "ON te.emp_department = td.department_id";


    //                "(" +
//                "   SELECT present_days * " +
//                ") as total_from_presentdays," +

//                "( " +
//                "   SELECT " +
//                "   " +


    //                        new Summary(
//                                id,
//                                resultSet.getString("full_name"),
//                                resultSet.getString("employee_position"),
//                                resultSet.getDouble("monthlywage"),
//
////                                resultSet.getDouble("monthlywage"),
//                                0.0,
//
//                                resultSet.getInt("dayspermonth"),
//                                resultSet.getInt("absent_days"),
////                                resultSet.getInt("total_from_presentdays"),
//
//                                resultSet.getInt("dailyrate") * resultSet.getInt("present_days"),
//
//                                calculateReduction(resultSet.getInt("employee_id"), from, to, resultSet.getTime("timein"), resultSet.getTime("timeout"), resultSet.getDouble("hourlyrate")),
////                                resultSet.getDouble("monthlywage"),
//                                resultSet.getDouble("monthlywage"),//Deduction
////                                resultSet.getDouble("monthlywage"),//Total after deduction
//                                resultSet.getDouble("monthlywage")
////                                resultSet.getInt("late_hours"),
////                                resultSet.getInt("holiday")
//                        )
}
