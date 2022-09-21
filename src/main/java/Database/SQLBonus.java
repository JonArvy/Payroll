package Database;

import Models.Bonus;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static Classes.CustomAlert.callAlert;
import static Database.SQLConnection.connect;

public class SQLBonus {
    public ObservableList<Bonus> getBonus() {
        ObservableList<Bonus> bonusList = FXCollections.observableArrayList();
        String command = "SELECT bonus_name," +
                "bonus_amount," +
                "department_name," +
                "bonus_date " +
                "FROM tbl_bonus JOIN tbl_department " +
                "ON bonus_recipient = department_id";

        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(command)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                bonusList.add(new Bonus(
//                                resultSet.getInt("bonus_id"),
                                resultSet.getString("bonus_name"),
                                resultSet.getDouble("bonus_amount"),
                                resultSet.getString("department_name"),
                                resultSet.getDate("bonus_date")
                        )
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bonusList;

    }

    public void addBonus(Bonus bonus) {
        String command = "INSERT INTO tbl_bonus (bonus_name, bonus_amount, bonus_recipient, bonus_date)" +
                "VALUES (?, ?, ?, ?);";
        try (Connection conn = connect();
             PreparedStatement prep = conn.prepareStatement(command)) {
            prep.setString(1, bonus.getBonus_Name());
            prep.setDouble(2, bonus.getBonus_Amount());
            prep.setInt(3, bonus.getBonus_Recipient());
            prep.setDate(4, bonus.getBonus_Date());

            prep.executeUpdate();
            callAlert("Success!", "Bonus: " + bonus.getBonus_Name() + " has been added!");
        } catch (SQLException e) {
            System.out.println("Error connecting to SQLite Database");
        }
    }

    public Bonus getBonus(Bonus bonus) {
        String command = "SELECT * FROM tbl_bonus " +
                "WHERE bonus_id = ?";
        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(command)) {

            preparedStatement.setInt(1, bonus.getBonus_ID());

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                bonus.setBonus_ID(resultSet.getInt("bonus_id"));
                bonus.setBonus_Name(resultSet.getString("bonus_name"));
                bonus.setBonus_Recipient(resultSet.getInt("bonus_recipient"));
                bonus.setBonus_Amount(resultSet.getDouble("bonus_amount"));
                bonus.setBonus_Date(resultSet.getDate("bonus_date"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bonus;
    }

    public void editBonus(Bonus bonus) {
        String command = "UPDATE tbl_bonus " +
                "SET bonus_name = ?, " +
                "    bonus_amount = ?, " +
                "    bonus_recipient = ?, " +
                "    bonus_date = ? " +
                "WHERE bonus_id = ?";

        try (Connection conn = connect();
             PreparedStatement prep = conn.prepareStatement(command)) {
            prep.setString(1, bonus.getBonus_Name());
            prep.setDouble(2, bonus.getBonus_Amount());
            prep.setInt(3, bonus.getBonus_Recipient());
            prep.setDate(4, bonus.getBonus_Date());
            prep.setInt(5, bonus.getBonus_ID());

            prep.executeUpdate();

            callAlert("Success!", "Bonus: " + bonus.getBonus_Name() + " has been updated!");
        } catch (SQLException e) {
            System.out.println("Error connecting to SQLite Database");
        }
    }
}
