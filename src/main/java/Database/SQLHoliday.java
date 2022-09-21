package Database;

import Models.Holiday;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

            callAlert("Success!", "Holiday: " + holiday.getHoliday_Name() + " has been added!");
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

            callAlert("Success!", "Holiday: " + holiday.getHoliday_Name() + " has been updated!");
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
}
