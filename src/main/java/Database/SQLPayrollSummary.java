package Database;

import Models.Attendance;
import Models.AttendanceReport;
import Models.Summary;
import Models.SummarySchema;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.Duration;
import java.time.LocalDate;

import static Database.SQLConnection.connect;

public class SQLPayrollSummary {
    public ObservableList<Summary> loadPayrollSummary(Date from, Date to) {
        ObservableList<Summary> summaryObservableList = FXCollections.observableArrayList();
        String command_emp = "SELECT * FROM tbl_employee";
        String command_dept = "SELECT * FROM tbl_department";
        String command_shift = "SELECT * FROM tbl_shift";
        String command_bonus = "SELECT * FROM tbl_bonus";
        String command_attendance = "SELECT * FROM tbl_attendance";

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


        int id = 1;
        String command = "SELECT " +
                "te.emp_id as employee_id," +

                "te.emp_fname || ' ' || te.emp_mname || ' ' || te.emp_lname as full_name," +

                "te.emp_position as employee_position," +

                "(" +
                "   SELECT " +
                "   department_monthlyrate " +
                "   FROM tbl_department td " +
                "   WHERE td.department_id = te.emp_department" +
                ") as monthlywage," +

                "(" +
                "   SELECT " +
                "   department_name " +
                "   FROM tbl_department td " +
                "   WHERE td.department_id = te.emp_department" +
                ") as dept_name," +

                "(" +
                "   SELECT " +
                "   department_dayspermonth " +
                "   FROM tbl_department td " +
                "   WHERE td.department_id = te.emp_department" +
                ") as dayspermonth," +

                "(" +
                "   SELECT " +
                "   COUNT(*) " +
                "   FROM tbl_attendance ta " +
                "   WHERE ta.emp_id = te.emp_id " +
                "   AND emp_attendance_date BETWEEN ? AND ? " +
                ") as present_days," +



                "(" +
                "   SELECT " +
                "   tq.department_dayspermonth - " +
                "   (" +
                "       SELECT " +
                "       COUNT(*) " +
                "       FROM tbl_attendance ta " +
                "       WHERE ta.emp_id = te.emp_id " +
                "       AND emp_attendance_date BETWEEN ? AND ? " +
                "   )" +
                "   FROM tbl_department tq " +
                "   WHERE tq.department_id = td.department_id " +
                ") as absent_days, " +

                //Sino si TE?
                "(" +
                "   SELECT " +
                "   td.department_monthlyrate / td.department_dayspermonth" +
                "   FROM tbl_department td" +
                "   WHERE te.emp_department = td.department_id" +
                ") as dailyrate," +

                "(" +
                "   SELECT " +
                "   td.department_monthlyrate / td.department_dayspermonth / td.department_hoursperday" +
                "   FROM tbl_department td" +
                "   WHERE te.emp_department = td.department_id" +
                ") as hourlyrate," +

                "(" +
                "   SELECT " +
                "   (" +
                "       SELECT " +
                "       COUNT(*) " +
                "       FROM tbl_attendance ta " +
                "       WHERE ta.emp_id = te.emp_id " +
                "       AND emp_attendance_date BETWEEN ? AND ?" +
                "   ) * " +
                "   (" +
                "       SELECT td.department_monthlyrate / td.department_dayspermonth " +
                "       FROM tbl_department td " +
                "       WHERE te.emp_department = td.department_id" +
                "   )" +
                ") as total_from_presentdays," +

//                "(" +
//                "   SELECT present_days * " +
//                ") as total_from_presentdays," +

//                "( " +
//                "   SELECT " +
//                "   " +

                "(" +
                "   SELECT " +
                "   ts.shift_in " +
                "   FROM tbl_shift ts " +
                "   WHERE te.emp_department = ts.shift_recipient " +
                ") as timein," +

                "(" +
                "   SELECT " +
                "   ts.shift_out" +
                "   FROM tbl_shift ts" +
                "   WHERE te.emp_department = ts.shift_recipient" +
                ") as timeout," +

                "( " +
                "   SELECT " +
                "   tq.department_dayspermonth - " +
                "   ( " +
                "       SELECT " +
                "       COUNT(*) " +
                "       FROM tbl_attendance ta " +
                "       WHERE ta.emp_id = te.emp_id " +
                "   ) " +
                "   FROM tbl_department tq " +
                "   WHERE tq.department_id = td.department_id " +
                ") as late_hours," +

                "( " +
                "   SELECT " +
                "   tm.department_dayspermonth - " +
                "   ( " +
                "       SELECT " +
                "       COUNT(*) " +
                "       FROM tbl_attendance ta " +
                "       WHERE ta.emp_id = te.emp_id " +
                "   ) " +
                "   FROM tbl_department tm " +
                "   WHERE tm.department_id = td.department_id " +
                ") as holiday " +

                "FROM tbl_employees te " +
                "JOIN tbl_department td " +
                "ON te.emp_department = td.department_id " +
                "WHERE te.emp_status = true";


        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(command)) {
            preparedStatement.setDate(1, from);
            preparedStatement.setDate(2, to);
            preparedStatement.setDate(3, from);
            preparedStatement.setDate(4, to);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Summary sm = new Summary();
                sm.setNumber(id);
                sm.setEmployeeNumber(resultSet.getInt("employee_id"));
                sm.setName(resultSet.getString("full_name"));
                sm.setPosition(resultSet.getString("employee_position"));
                sm.setDepartment(resultSet.getString("dept_name"));

                sm.setWage(resultSet.getDouble("monthlywage"));
                sm.setNetAmount(resultSet.getDouble("monthlywage"));
                sm.setPresentDays(resultSet.getInt("dayspermonth"));
                sm.setAbsentDays(resultSet.getInt("absent_days"));
                sm.setTotalCompensation(resultSet.getInt("dailyrate") * resultSet.getInt("present_days"));

                int utandlate = calculateReduction(resultSet.getInt("employee_id"), from, to, resultSet.getTime("timein"), resultSet.getTime("timeout"));
                sm.setLess(resultSet.getDouble("hourlyrate") * utandlate);
                sm.setTotalDeduction(sm.getLess());
                sm.setNetAmount(sm.getTotalCompensation() - sm.getTotalDeduction());

                sm.setLateUT(utandlate);
                sm.setDateCreated(Date.valueOf(LocalDate.now()));

                summaryObservableList.add(
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
                        sm
                );
                id++;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return summaryObservableList;
    }

    public void savePayrollSummary(ObservableList<Summary> summary, Date from, Date to) {
//        System.out.println("Count : " + getSummaryID());
        String command = "INSERT INTO payroll_summary (summary_id, summary_number, summary_employee_number, summary_name, summary_date_created, summary_department, summary_position, summary_late_ut, summary_wage, summary_benefits, summary_present_days, summary_absent_days, summary_total_compensation, summary_less, summary_total_deduction, summary_net_amount)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        String command2 = "INSERT INTO payroll_summary_schema (summary_date_from, summary_date_to) " +
                "VALUES (?, ?)";
        try (Connection connection = connect();

             PreparedStatement preparedStatement = connection.prepareStatement(command)) {

            for (Summary sum : summary) {
                preparedStatement.setInt(1, getSummaryID() + 1);
                preparedStatement.setInt(2, sum.getNumber());
                preparedStatement.setInt(3, sum.getEmployeeNumber());
                preparedStatement.setString(4, sum.getName());
                preparedStatement.setDate(5, Date.valueOf(LocalDate.now()));
                preparedStatement.setString(6, sum.getDepartment());
                preparedStatement.setString(7, sum.getPosition());
                preparedStatement.setInt(8, sum.getLateUT());
                preparedStatement.setDouble(9, sum.getNetAmount());
                preparedStatement.setDouble(10, sum.getBenefits());
                preparedStatement.setInt(11, sum.getPresentDays());
                preparedStatement.setInt(12, sum.getAbsentDays());
                preparedStatement.setDouble(13, sum.getTotalCompensation());
                preparedStatement.setDouble(14, sum.getLess());
                preparedStatement.setDouble(15, sum.getTotalDeduction());
                preparedStatement.setDouble(16, sum.getNetAmount());

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(command2)) {

            preparedStatement.setDate(1, from);
            preparedStatement.setDate(2, to);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private int getSummaryID() {
        String command = "SELECT summary_id FROM payroll_summary_schema";

        int item = 0;
        try (Connection connection = connect();
        PreparedStatement preparedStatement = connection.prepareStatement(command)) {

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                item = resultSet.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return item;
    }

    public int calculateReduction(int emp_id, Date from, Date to, Time timein, Time timeout) {
        String command = "SELECT * FROM tbl_attendance " +
                "WHERE emp_id = ? " +
                "AND emp_attendance_date BETWEEN ? AND ?";

        int late = 0;
        int undertime = 0;

        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(command)) {
            preparedStatement.setInt(1, emp_id);
            preparedStatement.setDate(2, from);
            preparedStatement.setDate(3, to);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                resultSet.getTime("emp_timein");
//                attendanceList.add(new Attendance(
//                        resultSet.getInt("emp_id"),
//
//                        resultSet.getDate("emp_attendance_date"),
//                        resultSet.getTime("emp_timein"),
//                        resultSet.getTime("emp_timeout")
//
//                ));
//                System.out.println("Employee: " + resultSet.getInt("emp_id"));
//                System.out.println("Date: " + resultSet.getDate("emp_attendance_date") + " " + resultSet.getTime("emp_timein") + " - " + resultSet.getTime("emp_timeout"));
//                System.out.println("Difference: " + resultSet.getTime("emp_timeout").toLocalTime() + " " + timeout.toLocalTime());
                long i = Duration.between(timein.toLocalTime(), resultSet.getTime("emp_timein").toLocalTime()).toMinutes();
                long o = Duration.between(timeout.toLocalTime(), resultSet.getTime("emp_timeout").toLocalTime()).toMinutes();
//                System.out.println("Diff 2 " + i);

                if (i > 15) {
//                    System.out.println("Late");
                    int latetobeadded = 0;
                    if (i % 60 > 0) {
                        latetobeadded = (int) Math.ceil(i / 60) + 1;
                    } else {
                        latetobeadded = (int) Math.ceil(i / 60);
                    }
                    late += latetobeadded;
                } else {
//                    System.out.println("Not late");
                }

                if (o < 0) {
                    o = Math.abs(o);
//                    System.out.println("Undertime");
                    int undertimetobeadded = 0;
//                    System.out.println(o % 60);
                    if (o % 60 > 0) {
                        undertimetobeadded = (int) Math.ceil(o / 60) + 1;
                    } else {
                        undertimetobeadded = (int) Math.ceil(o / 60);
                    }
                    undertime += undertimetobeadded;
                } else {
//                    System.out.println("Not undertime");

                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return late + undertime;
    }

    public int lateChecker() {
        return 1;
    }
//        public ObservableList<Summary> generatePayrollSummary(Date from, Date to, ObservableList<Summary> summaryObservableList) {
////
//
//            String command = "SELECT * " +
//                    "   FROM tbl_attendance " +
//                    "   WHERE emp_id = ? " +
//                    "   AND emp_attendance_date BETWEEN ? AND ?";
//
//
//            try (Connection connection = connect();
//                 PreparedStatement preparedStatement = connection.prepareStatement(command)) {
//                preparedStatement.setInt(1, summaryObservableList.get(0).getNumber());
//                preparedStatement.setDate(2, from);
//                preparedStatement.setDate(3, to);
//
//                ResultSet resultSet = preparedStatement.executeQuery();
//                while (resultSet.next()) {
//                    summaryObservableList.add(new Summary(
//                                    id,
//                                    resultSet.getString("full_name"),
//                                    resultSet.getString("employee_position"),
//                                    resultSet.getDouble("monthlywage"),
//
//                                    resultSet.getDouble("monthlywage"),
//
//                                    resultSet.getInt("present_days"),
//                                    resultSet.getInt("absent_days"),
////                                resultSet.getInt("total_from_presentdays"),
//
//                                    resultSet.getInt("dailyrate") * resultSet.getInt("present_days"),
//
//                                    resultSet.getDouble("monthlywage"),
//                                    resultSet.getDouble("monthlywage"),
//                                    resultSet.getDouble("monthlywage")
////                                resultSet.getInt("late_hours"),
////                                resultSet.getInt("holiday")
//                            )
//                    );
//                    id++;
//                }
//
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//
//            return summaryObservableList;
//        }

//        try (Connection connection = connect();
//             PreparedStatement preparedStatement = connection.prepareStatement(command_emp);
//             PreparedStatement preparedStatement1 = connection.prepareStatement(command_dept);
//             PreparedStatement preparedStatement2 = connection.prepareStatement(command_shift);
//             PreparedStatement preparedStatement3 = connection.prepareStatement(command_bonus);
//             PreparedStatement preparedStatement4 = connection.prepareStatement(command_attendance)) {
//            ResultSet resultSet = preparedStatement.executeQuery();
//            ResultSet resultSet1 = preparedStatement1.executeQuery();
//            ResultSet resultSet2 = preparedStatement2.executeQuery();
//            ResultSet resultSet3 = preparedStatement3.executeQuery();
//            ResultSet resultSet4 = preparedStatement4.executeQuery();
//            //            while (resultSet.next()) {
//            //                list.add(new Summary(
//            //                                resultSet.getInt("employee_id"),
//            //                                resultSet.getString("employee_name"),
//            //                                resultSet.getString("employee_position"),
//            //                                resultSet.getString("employee_address"),
//            //                                resultSet.getString("employee_contact"),
//            //                                resultSet.getString("employee_email"),
//            //                                resultSet.getString
//
//        }
//    } catch (SQLException e) {
//        System.out.println(e.getMessage());
//    }
//        return list;

    public ObservableList<SummarySchema> getSchemaSummaryList() {
        String command = "SELECT * FROM payroll_summary_schema";
        ObservableList<SummarySchema> list = FXCollections.observableArrayList();

        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(command)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                list.add(new SummarySchema(
                        resultSet.getInt("summary_id"),
                        resultSet.getDate("summary_date_from"),
                        resultSet.getDate("summary_date_to")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public ObservableList<Summary> getSummaryList(int id) {
        ObservableList<Summary> summaryList = FXCollections.observableArrayList();
        String command = "SELECT * FROM payroll_summary WHERE summary_id = ?";

        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(command)) {

            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                Summary sm = new Summary();
//                sm.setDateCreated(resultSet.getDate("summary_date_created"));
                sm.setName(resultSet.getString("summary_name"));
                sm.setEmployeeNumber(resultSet.getInt("summary_employee_number"));
                sm.setDepartment(resultSet.getString("summary_department"));
                sm.setPosition(resultSet.getString("summary_position"));
                sm.setDatabaseID(resultSet.getInt("summary_individual_id"));

                sm.setWage(resultSet.getDouble("summary_wage"));
                sm.setLateUT(resultSet.getInt("summary_late_ut"));
                sm.setAbsentDays(resultSet.getInt("summary_absent_days"));
                sm.setTotalCompensation(resultSet.getDouble("summary_total_compensation"));
                sm.setTotalDeduction(resultSet.getDouble("summary_total_deduction"));

                sm.setNetAmount(resultSet.getDouble("summary_net_amount"));
                summaryList.add(
                        sm
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return summaryList;
    }
}
