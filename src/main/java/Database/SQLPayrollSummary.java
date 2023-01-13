package Database;

import Models.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.Duration;
import java.time.LocalDate;

import static Classes.CustomAlert.callAlert;
import static Classes.DateGetter.getActiveDates;
import static Classes.DateTimeCalculator.getTotalHours;
import static Database.SQLConnection.connect;

public class SQLPayrollSummary {
    public ObservableList<Summary> loadPayrollSummarya(Date from, Date to) {
        ObservableList<Summary> summaryObservableList = FXCollections.observableArrayList();
        String command_emp = "SELECT * FROM tbl_employee";
        String command_dept = "SELECT * FROM tbl_department";
        String command_bonus = "SELECT * FROM tbl_bonus";
        String command_attendance = "SELECT * FROM tbl_attendance";

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



                "(" +
                "   SELECT " +
                "   ts.shift_in " +
                "   FROM tbl_department ts " +
                "   WHERE te.emp_department = ts.shift_recipient " +
                ") as timein," +

                "(" +
                "   SELECT " +
                "   ts.shift_out" +
                "   FROM tbl_department ts" +
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

            SQLHoliday sqlHoliday = new SQLHoliday();
            int holiday_count = sqlHoliday.getHolidayCount(from, to);

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
                sm.setPresentDays(resultSet.getInt("dayspermonth") - holiday_count);
                sm.setAbsentDays(resultSet.getInt("absent_days") - holiday_count);
                sm.setTotalCompensation(resultSet.getInt("dailyrate") * resultSet.getInt("present_days"));

                int utandlate = calculateReduction(resultSet.getInt("employee_id"), from, to, resultSet.getTime("timein"), resultSet.getTime("timeout"));
                sm.setLess(resultSet.getDouble("hourlyrate") * utandlate);
                sm.setTotalDeduction(sm.getLess());
                sm.setNetAmount(sm.getTotalCompensation() - sm.getTotalDeduction());

                sm.setLateUT(utandlate);
                sm.setDateCreated(Date.valueOf(LocalDate.now()));

                summaryObservableList.add(

                        sm
                );
                id++;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return summaryObservableList;
    }

//    public ObservableList<Summary> loadPayrollSummary() {
//        ObservableList<Summary> summaryList = FXCollections.observableArrayList();
//        int id = 1;
//        String command = "SELECT " +
//                "te.emp_id as employee_id, " +
//
//                "te.emp_fname || ' ' || te.emp_mname || ' ' || te.emp_lname as full_name," +
//
//                "te.emp_position as employee_position," +
//
//                "(" +
//                "   SELECT " +
//                "   department_name " +
//                "   FROM tbl_department td " +
//                "   WHERE td.department_id = te.emp_department" +
//                ") as dept_name," +
//
//                "(" +
//                "   SELECT " +
//                "   department_monthlyrate " +
//                "   FROM tbl_department td " +
//                "   WHERE td.department_id = te.emp_department" +
//                ") as monthlywage," +
//
//                "(" +
//                "   SELECT " +
//                "   department_dayspermonth " +
//                "   FROM tbl_department td " +
//                "   WHERE td.department_id = te.emp_department" +
//                ") as dayspermonth," +
//
//                "(" +
//                "   SELECT " +
//                "   tq.department_dayspermonth - " +
//                "   (" +
//                "       SELECT " +
//                "       COUNT(*) " +
//                "       FROM tbl_attendance ta " +
//                "       WHERE ta.emp_id = te.emp_id " +
//                "       AND emp_attendance_date BETWEEN ? AND ? " +
//                "   )" +
//                "   FROM tbl_department tq " +
//                "   WHERE tq.department_id = td.department_id " +
//                ") as absent_days, " +
//
//                "(" +
//                "   SELECT " +
//                "   td.department_monthlyrate / td.department_dayspermonth" +
//                "   FROM tbl_department td" +
//                "   WHERE te.emp_department = td.department_id" +
//                ") as dailyrate," +
//
//                "(" +
//                "   SELECT " +
//                "   COUNT(*) " +
//                "   FROM tbl_attendance ta " +
//                "   WHERE ta.emp_id = te.emp_id " +
//                "   AND emp_attendance_date BETWEEN ? AND ? " +
//                ") as present_days," +
//
//
//
//                ;
//            int holiday_count = sqlHoliday.getHolidayCount(from, to);
//            Summary sm = new Summary();
////            sm.setNumber(id);
////            sm.setEmployeeNumber(resultSet.getInt("employee_id"));
////            sm.setName(resultSet.getString("full_name"));
////            sm.setPosition(resultSet.getString("employee_position"));
////            sm.setDepartment(resultSet.getString("dept_name"));
//
////            sm.setWage(resultSet.getDouble("monthlywage"));
////            sm.setNetAmount(resultSet.getDouble("monthlywage"));
////            sm.setPresentDays(resultSet.getInt("dayspermonth") - holiday_count);
////            sm.setAbsentDays(resultSet.getInt("absent_days") - holiday_count);
//            sm.setTotalCompensation(resultSet.getInt("dailyrate") * resultSet.getInt("present_days"));
//////////////
//
//        int utandlate = calculateReduction(resultSet.getInt("employee_id"), from, to, resultSet.getTime("timein"), resultSet.getTime("timeout"));
//        sm.setLess(resultSet.getDouble("hourlyrate") * utandlate);
//        sm.setTotalDeduction(sm.getLess());
//        sm.setNetAmount(sm.getTotalCompensation() - sm.getTotalDeduction());
//
//        sm.setLateUT(utandlate);
//        sm.setDateCreated(Date.valueOf(LocalDate.now()));
//
//            summaryObservableList.add(sm);
//            id++;
//        }
//
//        return summaryList;
//    }

    public ObservableList<Summary> loadPayrollSummary(Date from, Date to) {
        ObservableList<Summary> summaryObservableList = FXCollections.observableArrayList();

        SQLEmployee sqlEmployee = new SQLEmployee();
        SQLDepartment sqlDepartment = new SQLDepartment();
        ObservableList<Employee> activeEmployeeList = sqlEmployee.getAllEmployees(true);
        int number = 1;
        for (Employee employee : activeEmployeeList) {
//            System.out.println(employee.getEmployee_ID() + " " + employee.getDepartment());
            Department employeeDepartment = sqlDepartment.getDepartment(new Department(employee.getDepartment()));
            ObservableList<Date> activeDates = getActiveDates(from , to, employeeDepartment.isShift_Sunday(), employeeDepartment.isShift_Monday(), employeeDepartment.isShift_Tuesday(), employeeDepartment.isShift_Wednesday(), employeeDepartment.isShift_Thursday(), employeeDepartment.isShift_Friday(), employeeDepartment.isShift_Saturday());
            Date[] activeDatesArray = activeDates.toArray(new Date[activeDates.size()]);

            ObservableList<Attendance> attendanceList = getAttendanceFromArray(activeDatesArray, employee);

            int totalHoursRendered = 0;
            int totalDaysRendered = 0;
            for (Attendance attds : attendanceList) {
                long hrs = getTotalHours(employeeDepartment.getTime_In(), employeeDepartment.getTime_Out(),
                        employeeDepartment.getBreak_Start(), employeeDepartment.getBreak_End(),
                        attds.getEmployee_TimeIn(), attds.getEmployee_TimeOut());
                totalHoursRendered += hrs;
                totalDaysRendered++;
//                System.out.println("Active Dates: " + attds.getEmployee_Attendance_Date() + " Hours: " + hrs);
            }

//            System.out.println("Total Hours Rendered: " + totalHoursRendered);
//            System.out.println("Total Days Rendered: " + totalDaysRendered);

            Summary summary = new Summary();
            summary.setNumber(number);
            summary.setEmployeeNumber(employee.getEmployee_ID());
            summary.setName(employee.getFull_Name());
            summary.setPosition(employee.getPosition());
            summary.setWage(employeeDepartment.getDepartment_MonthlyRate());
            //Other Benefits
            summary.setPresentDays(employeeDepartment.getDepartment_DaysPerMonth());
//            System.out.println(summary.getPresentDays());
            summary.setAbsentDays(absentCalculator(employeeDepartment.getDepartment_DaysPerMonth(), totalDaysRendered));
//            System.out.println(employeeDepartment.getHourly_Rate());
            summary.setTotalCompensation(calculateSalary(employeeDepartment.getHourly_Rate(), totalHoursRendered));
            summary.setMonthlyRate(employeeDepartment.getDepartment_MonthlyRate());
            //BIR
            //LESS
            //PAG-IBIG
            summary.setTotalDeduction(0);
            //Net Amount
            summary.setNetAmount(summary.getTotalCompensation());

            summary.setTotalHourlyRate(employeeDepartment.getHourly_Rate());
            summary.setTotalHour(totalHoursRendered);

            //Other Hidden Information
            summary.setDepartment(employeeDepartment.getDepartment_Name());

            summaryObservableList.add(summary);
            number++;
        }
        for (Summary s : summaryObservableList) {
//            System.out.println(s.getNumber() + " " + s.getEmployeeNumber() + " " + s.getName() + " " + s.getPosition() + " " + s.getWage() + " " + s.getPresentDays() + " " + s.getAbsentDays() + " " + s.getTotalCompensation() + " " + s.getTotalDeduction());
        }
        return summaryObservableList;
    }

    private int absentCalculator(int daysToRender, int daysRendered) {
        int absent = 0;

        absent = daysToRender - daysRendered;
        if (absent < 0) {
            absent = 0;
        }

        return absent;
    }

    private double calculateSalary(double hourlyRate, int hoursRendered) {
        double salary = 0;

        salary = hourlyRate * hoursRendered;

        return salary;
    }

    private ObservableList<Attendance> getAttendanceFromArray(Date[] dates, Employee employee) {
        ObservableList<Attendance> attendanceList = FXCollections.observableArrayList();

        dropTemporaryTable();
        createTemporaryTable();
        populateTemporaryTable(dates);

//        String command = "SELECT * FROM tbl_attendance WHERE emp_id = ? " +
//                "AND emp_attendance_date IN (SELECT date FROM temp_dates) ";

        String command = "SELECT taa.attendance_id as atd_id, taa.emp_id as atd_emp_id, taa.emp_attendance_date as atd_date, taa.emp_timein as atd_in, taa.emp_timeout as atd_out " +
                "FROM tbl_attendance_admin as taa " +
                "INNER JOIN tbl_attendance as ta " +
                "ON taa.emp_attendance_date = ta.emp_attendance_date " +
                "AND taa.emp_id = ta.emp_id " +
                "WHERE taa.emp_id = ? " +
                "AND taa.emp_attendance_date IN (SELECT date FROM temp_dates) ";

        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(command)) {

            preparedStatement.setInt(1, employee.getEmployee_ID());


            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                System.out.println(resultSet.getInt("atd_id") + " " + resultSet.getInt("atd_emp_id") + " " + resultSet.getDate("atd_date") + " " + resultSet.getTime("atd_in") + " " + resultSet.getTime("atd_out"));
                Attendance attendance = new Attendance();
                attendance.setAttendance_ID(resultSet.getInt("atd_id"));
                attendance.setEmployee_ID(resultSet.getInt("atd_emp_id"));
                attendance.setEmployee_Attendance_Date(resultSet.getDate("atd_date"));
                attendance.setEmployee_TimeIn(resultSet.getTime("atd_in"));
                attendance.setEmployee_TimeOut(resultSet.getTime("atd_out"));

                attendanceList.add(attendance);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return attendanceList;
    }

    private void createTemporaryTable() {
        String command = "CREATE TABLE temp_dates (date_id INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE," +
                "date DATE)";
        try (Connection connection = connect();
             Statement statement = connection.createStatement()) {

            statement.executeUpdate(command);


        } catch (SQLException e) {
            System.out.println("Error connecting to SQLite database");
            e.printStackTrace();
        }
    }

    private void populateTemporaryTable(Date[] dates) {
        String command = "INSERT INTO temp_dates (date) VALUES (?)";
        try (Connection connection = connect();
            Statement statement = connection.createStatement()) {
            for (Date date : dates) {
                PreparedStatement preparedStatement = connection.prepareStatement(command);
                preparedStatement.setDate(1, date);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Error connecting to SQLite database");
            e.printStackTrace();
        }
    }


    private void dropTemporaryTable() {
        String command = "DROP TABLE temp_dates";
        try (Connection connection = connect();
             Statement statement = connection.createStatement()) {

            statement.executeUpdate(command);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void savePayrollSummary(ObservableList<Summary> summary, Date from, Date to) {
//        System.out.println("Count : " + getSummaryID());
        String command = "INSERT INTO payroll_summary (summary_id, summary_number, summary_employee_number, summary_name, summary_date_created, summary_department, summary_department_monthly_rate, summary_position, summary_late_ut, summary_wage, summary_benefits, summary_present_days, summary_absent_days, summary_total_compensation, summary_less, summary_total_deduction, summary_net_amount, summary_total_hours, summary_department_hourly_rate)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
                preparedStatement.setDouble(7, sum.getMonthlyRate());
                preparedStatement.setString(8, sum.getPosition());
                preparedStatement.setInt(9, sum.getLateUT());
                preparedStatement.setDouble(10, sum.getNetAmount());
                preparedStatement.setDouble(11, sum.getBenefits());
                preparedStatement.setInt(12, sum.getPresentDays());
                preparedStatement.setInt(13, sum.getAbsentDays());
                preparedStatement.setDouble(14, sum.getTotalCompensation());
                preparedStatement.setDouble(15, sum.getLess());
                preparedStatement.setDouble(16, sum.getTotalDeduction());
                preparedStatement.setDouble(17, sum.getNetAmount());
                preparedStatement.setInt(18, sum.getTotalHour());
                preparedStatement.setDouble(19, sum.getTotalHourlyRate());

                preparedStatement.executeUpdate();
            }

            callAlert("Payroll Summary is generated! You can now check payslip list", 2);
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

    public ObservableList<SummarySchema> getSchemaSummaryListForSingleEmployee(Employee employee) {
        String command = "SELECT * FROM payroll_summary_schema " +
                "JOIN payroll_summary " +
                "ON payroll_summary_schema.summary_id = payroll_summary.summary_id " +
                "WHERE payroll_summary.summary_employee_number = ?";
        ObservableList<SummarySchema> list = FXCollections.observableArrayList();

        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(command)) {

            preparedStatement.setInt(1, employee.getEmployee_ID());

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

    public String getDateForSummary(int id) {
        String command = "SELECT * FROM payroll_summary_schema " +
                "WHERE summary_id = ?";

        String date = "";

        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(command)) {

            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                date = resultSet.getDate("summary_date_from") + " + " + resultSet.getDate("summary_date_to");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return date.replace("-", "/").replace("+", "-");
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
                sm.setDatabaseID(resultSet.getInt("summary_individual_id"));
                sm.setSummaryID(resultSet.getInt("summary_id"));

                sm.setNumber(resultSet.getInt("summary_number"));
//                sm.setDateCreated(resultSet.getDate("summary_date_created"));
                sm.setName(resultSet.getString("summary_name"));
                sm.setEmployeeNumber(resultSet.getInt("summary_employee_number"));
                sm.setDepartment(resultSet.getString("summary_department"));
                sm.setPosition(resultSet.getString("summary_position"));
                sm.setDatabaseID(resultSet.getInt("summary_individual_id"));

                sm.setWage(resultSet.getDouble("summary_wage"));
                sm.setLateUT(resultSet.getInt("summary_late_ut"));
                sm.setPresentDays(resultSet.getInt("summary_present_days") - resultSet.getInt("summary_absent_days"));
                sm.setAbsentDays(resultSet.getInt("summary_absent_days"));
                sm.setMonthlyRate(resultSet.getDouble("summary_department_monthly_rate"));
                sm.setTotalCompensation(resultSet.getDouble("summary_total_compensation"));
                sm.setTotalDeduction(resultSet.getDouble("summary_total_deduction"));

                sm.setNetAmount(resultSet.getDouble("summary_net_amount"));

                sm.setTotalHour(resultSet.getInt("summary_total_hours"));
                sm.setTotalHourlyRate(resultSet.getDouble("summary_department_hourly_rate"));

                sm.setClaimed(resultSet.getBoolean("recieved"));
//                sm.setSummaryID(resultSet.getInt("summary_id"));
                summaryList.add(
                        sm
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return summaryList;
    }

    public Summary getSummary(int id, Employee employee) {
        Summary sm = new Summary();
        String command = "SELECT * FROM payroll_summary WHERE summary_id = ? AND summary_employee_number = ?";

        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(command)) {

            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, employee.getEmployee_ID());

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                sm.setDatabaseID(resultSet.getInt("summary_individual_id"));
                sm.setSummaryID(resultSet.getInt("summary_id"));

                sm.setNumber(resultSet.getInt("summary_number"));

                sm.setDateCreated(resultSet.getDate("summary_date_created"));
                sm.setName(resultSet.getString("summary_name"));
                sm.setEmployeeNumber(resultSet.getInt("summary_employee_number"));
                sm.setDepartment(resultSet.getString("summary_department"));
                sm.setPosition(resultSet.getString("summary_position"));
                sm.setDatabaseID(resultSet.getInt("summary_individual_id"));

                sm.setWage(resultSet.getDouble("summary_wage"));
                sm.setLateUT(resultSet.getInt("summary_late_ut"));
                sm.setAbsentDays(resultSet.getInt("summary_absent_days"));
                sm.setMonthlyRate(resultSet.getDouble("summary_department_monthly_rate"));
                sm.setTotalCompensation(resultSet.getDouble("summary_total_compensation"));
                sm.setTotalDeduction(resultSet.getDouble("summary_total_deduction"));

                sm.setNetAmount(resultSet.getDouble("summary_net_amount"));

                sm.setTotalHour(resultSet.getInt("summary_total_hours"));
                sm.setTotalHourlyRate(resultSet.getDouble("summary_department_hourly_rate"));

                sm.setClaimed(resultSet.getBoolean("recieved"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sm;
    }

    public void markPayAsClaimed(int id) {
        String command = "UPDATE payroll_summary " +
                "SET recieved = ? " +
                "WHERE summary_individual_id = ?";
        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(command)) {
            preparedStatement.setBoolean(1, true);
            preparedStatement.setInt(2, id);

            preparedStatement.executeUpdate();
            callAlert("Marked the Payslip as Claimed" , 2);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
