package Database;

import java.sql.*;
import java.time.LocalDate;

import static Classes.CustomAlert.callAlert;
import static Database.SQLConnection.connect;

public class SQLNoticeboard {
    public String loadLastMessage() {
        String message = "";
        String command = "SELECT * " +
                "FROM tbl_noticeboard " +
                "WHERE noticeboard_active = true";

        try (Connection conn = connect();
             PreparedStatement preparedStatement = conn.prepareStatement(command)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                message = resultSet.getString("noticeboard_message");
            }

        } catch (SQLException e) {
            System.out.println("Error connecting to SQLite database");
            e.printStackTrace();
        }

        return message;
    }

    public void updateNoticeBoard(String message) {
        deactivatePreviousMessage();
        String command = "INSERT INTO tbl_noticeboard (noticeboard_message, noticeboard_date, noticeboard_active) " +
                "VALUES (?, ?, ?)";
        try (Connection conn = connect();
             PreparedStatement preparedStatement = conn.prepareStatement(command)) {

            preparedStatement.setString(1, message);
            preparedStatement.setDate(2, Date.valueOf(LocalDate.now()));
            preparedStatement.setBoolean(3, true);

            preparedStatement.executeUpdate();

            callAlert("Noticeboard has been updated!", 2);
        } catch (SQLException e) {
            System.out.println("Error connecting to SQLite database");
            e.printStackTrace();
        }
    }

    private void deactivatePreviousMessage() {
        String command = "UPDATE tbl_noticeboard " +
            "SET noticeboard_active = ? " +
            "WHERE noticeboard_active = ?";

        try (Connection conn = connect();
             PreparedStatement preparedStatement = conn.prepareStatement(command)) {

            preparedStatement.setBoolean(1, false);
            preparedStatement.setBoolean(2, true);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error connecting to SQLite database");
            e.printStackTrace();
        }
    }
}
