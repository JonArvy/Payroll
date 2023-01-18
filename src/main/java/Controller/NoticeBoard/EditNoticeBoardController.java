package Controller.NoticeBoard;

import Database.SQLAttendance;
import Database.SQLNoticeboard;
import Models.Admin;
import Models.NewAdmin;
import cw.payroll.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class EditNoticeBoardController {

    @FXML
    private Button cancel;

    @FXML
    private Button update;

    @FXML
    private TextArea editnoticeboard_message;

    @FXML
    private void editNoticeBoardButtonAction(ActionEvent event) {
        if (event.getSource() == cancel) {
            loadNoticeBoard();
        } else if (event.getSource() == update) {
            sqlNoticeboard.updateNoticeBoard(editnoticeboard_message.getText());
            loadNoticeBoard();
        }
    }

    @FXML
    private void initialize() {
        editnoticeboard_message.setText(sqlNoticeboard.loadLastMessage());
    }

    /****************************** FXML ENDS HERE ******************************/

    private NewAdmin admin;
    private AnchorPane container;

    private SQLNoticeboard sqlNoticeboard = new SQLNoticeboard();

    public void setRetrievedData(NewAdmin admin, AnchorPane anchorPane) {
        this.admin = admin;
        this.container = anchorPane;
    }

    private void loadNoticeBoard() {
        NoticeBoardController controller;

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UI/Noticeboard/NoticeBoard.fxml"));
            fxmlLoader.load();

            controller = fxmlLoader.getController();
            controller.setRetrievedData(admin, container);

            AnchorPane anchorPane = fxmlLoader.getRoot();
            container.getChildren().clear();
            container.getChildren().add(anchorPane);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void loadNoticeBoardMessage() {
        String message = sqlNoticeboard.loadLastMessage();
    }
}
