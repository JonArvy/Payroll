package Database;

import Models.Holiday;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

import static Classes.CustomAlert.callAlert;
import static Database.SQLConnection.connect;

public class SQLHoliday {
    public ObservableList<Holiday> getHolidays() {
        ObservableList<Holiday> holidayList = FXCollections.observableArrayList();
        String command = "SELECT * FROM tbl_holiday";
        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(command)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                holidayList.add(new Holiday(
                                resultSet.getInt("holiday_id"),
                                resultSet.getString("holiday_name"),
                                resultSet.getDate("holiday_date"),
                                resultSet.getString("holiday_type"),
                                resultSet.getString("holiday_repeat")
                        )
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return holidayList;
    }

    public void addHoliday(Holiday holiday) {
        String command = "INSERT INTO tbl_holiday (holiday_name, holiday_date, holiday_type) " +
                "VALUES (?, ?, ?)";
        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(command)) {

            preparedStatement.setString(1, holiday.getHoliday_Name());
            preparedStatement.setDate(2, holiday.getHoliday_Date()); //
            preparedStatement.setString(3, holiday.getHoliday_Type());

            preparedStatement.executeUpdate();

            callAlert("Holiday: " + holiday.getHoliday_Name() + " has been added!", 2);
        } catch (SQLException e) {
            System.out.println("Error connecting to SQLite database");
            e.printStackTrace();
        }
    }

    public void editHoliday(Holiday holiday) {
        String command = "INSERT INTO tbl_holiday (holiday_name, holiday_date, holiday_type) " +
                "VALUES (?, ?, ?)";
        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(command)) {

            preparedStatement.setString(1, holiday.getHoliday_Name());
            preparedStatement.setDate(2, holiday.getHoliday_Date()); //
            preparedStatement.setString(3, holiday.getHoliday_Type());

            preparedStatement.executeUpdate();

            callAlert("Holiday: " + holiday.getHoliday_Name() + " has been updated!", 2);
        } catch (SQLException e) {
            System.out.println("Error connecting to SQLite database");
            e.printStackTrace();
        }
    }

    public Holiday getHoliday(Holiday holiday) {
        String command = "SELECT * FROM tbl_holiday " +
                "WHERE holiday_id = ?";
        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(command)) {

            preparedStatement.setInt(1, holiday.getHoliday_ID());

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                holiday.setHoliday_ID(resultSet.getInt("holiday_id"));
                holiday.setHoliday_Name(resultSet.getString("holiday_name"));
                holiday.setHoliday_Date(resultSet.getDate("holiday_date"));
                holiday.setHoliday_Type(resultSet.getString("holiday_type"));
                holiday.setHoliday_Repeat(resultSet.getString("holiday_repeat"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return holiday;
    }

    public Boolean checkIfHoliday(Date dt1, Date dt2) {
        boolean isHoliday = false;
        String command = "SELECT * " +
                "FROM tbl_holiday hol " +
                "WHERE holiday_date >= ? " +
                "AND holiday_date < ? ";

        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(command)) {
            preparedStatement.setDate(1, dt1);
            preparedStatement.setDate(2, dt2);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                isHoliday = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return isHoliday;
    }

    public int getHolidayCount(Date dt1, Date dt2) {
        int count = 0;
        String command = "SELECT COUNT(*) AS count " +
                "FROM tbl_holiday hol " +
                "WHERE holiday_date >= ? " +
                "AND holiday_date <= ? ";

        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(command)) {
            preparedStatement.setDate(1, dt1);
            preparedStatement.setDate(2, dt2);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                count = resultSet.getInt("count");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }
}
