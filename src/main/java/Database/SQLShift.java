package Database;

import Models.Shift;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static Classes.CustomAlert.callAlert;
import static Database.SQLConnection.connect;

public class SQLShift {
    public ObservableList<Shift> getShift() {
        ObservableList<Shift> shiftList = FXCollections.observableArrayList();

        String command = "SELECT shift_id," +
                "shift_type," +

                "shift_recipient," +
                "department_name," +

                "shift_sunday," +
                "shift_monday," +
                "shift_tuesday," +
                "shift_wednesday," +
                "shift_thursday," +
                "shift_friday," +
                "shift_saturday " +

                "FROM tbl_shift JOIN tbl_department " +
                "ON shift_recipient = department_id " +
                "WHERE shift_type = 1";

        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(command)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                shiftList.add(new Shift(
                                resultSet.getInt("shift_id"),
                                resultSet.getInt("shift_type"),
                                resultSet.getInt("shift_recipient"),
                                resultSet.getString("department_name"),
                                resultSet.getBoolean("shift_sunday"),
                                resultSet.getBoolean("shift_monday"),
                                resultSet.getBoolean("shift_tuesday"),
                                resultSet.getBoolean("shift_wednesday"),
                                resultSet.getBoolean("shift_thursday"),
                                resultSet.getBoolean("shift_friday"),
                                resultSet.getBoolean("shift_saturday")
                        )
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        command = "SELECT shift_id," +
                "shift_type," +

                "shift_recipient," +
                "emp_lname  || ' ' || emp_fname || ' ' || emp_mname as fullname," +
                "shift_sunday," +
                "shift_monday," +
                "shift_tuesday," +
                "shift_wednesday," +
                "shift_thursday," +
                "shift_friday," +
                "shift_saturday " +

                "FROM tbl_shift JOIN tbl_employees " +
                "ON shift_recipient = emp_id " +
                "WHERE shift_type = 2";

        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(command)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                shiftList.add(new Shift(
                                resultSet.getInt("shift_id"),
                                resultSet.getInt("shift_type"),
                                resultSet.getInt("shift_recipient"),
                                resultSet.getString("fullname"),
                                resultSet.getBoolean("shift_sunday"),
                                resultSet.getBoolean("shift_monday"),
                                resultSet.getBoolean("shift_tuesday"),
                                resultSet.getBoolean("shift_wednesday"),
                                resultSet.getBoolean("shift_thursday"),
                                resultSet.getBoolean("shift_friday"),
                                resultSet.getBoolean("shift_saturday")
                        )
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return shiftList;

    }

    public void addShift(Shift shift) {

        String command = "INSERT INTO tbl_shift (shift_type, shift_recipient, shift_sunday, shift_monday, shift_tuesday, shift_wednesday, shift_thursday, shift_friday, shift_saturday)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = connect();
             PreparedStatement prep = conn.prepareStatement(command)) {
            prep.setInt(1, shift.getShift_Type());
            prep.setInt(2, shift.getShift_Recipient());
            prep.setBoolean(3, shift.isShift_Sunday());
            prep.setBoolean(4, shift.isShift_Monday());
            prep.setBoolean(5, shift.isShift_Tuesday());
            prep.setBoolean(6, shift.isShift_Wednesday());
            prep.setBoolean(7, shift.isShift_Thursday());
            prep.setBoolean(8, shift.isShift_Friday());
            prep.setBoolean(9, shift.isShift_Saturday());

            prep.executeUpdate();

            callAlert("Success!", "Shift Added with days of " + shift.getShift_Schema());
        } catch (SQLException e) {
            System.out.println("Error connecting to SQLite Database");
            callAlert("Error!", "Error connecting to SQLite Database");
        }
    }

    public void editShift(Shift shift) {
        String command = "UPDATE tbl_bonus" +
                "SET shift_type = ?," +
                "    shift_recipient = ?," +
                "    shift_sunday = ?, " +
                "    shift_monday = ?," +
                "    shift_tuesday = ?," +
                "    shift_wednesday = ?," +
                "    shift_thursday = ?," +
                "    shift_friday = ?," +
                "    shift_saturday = ?" +
                "WHERE shift_id = ?";

        try (Connection conn = connect();
             PreparedStatement prep = conn.prepareStatement(command)) {
            prep.setInt(1, shift.getShift_Type());
            prep.setInt(2, shift.getShift_Recipient());
            prep.setBoolean(3, shift.isShift_Sunday());
            prep.setBoolean(4, shift.isShift_Monday());
            prep.setBoolean(5, shift.isShift_Tuesday());
            prep.setBoolean(6, shift.isShift_Wednesday());
            prep.setBoolean(7, shift.isShift_Thursday());
            prep.setBoolean(8, shift.isShift_Friday());
            prep.setBoolean(9, shift.isShift_Saturday());
            prep.setInt(10, shift.getShift_ID());

            prep.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error connecting to SQLite Database");
        }
    }
}
