package Controller.NoticeBoard;

import Database.SQLNoticeboard;
import Models.Admin;
import cw.payroll.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class NoticeBoardController {
    @FXML
    private TextArea noticeboard_message;

    @FXML
    private void editNoticeBoard(ActionEvent event) {
        loadEditNoticeBoard();
    }

    @FXML
    private void initialize() {
        noticeboard_message.setText(sqlNoticeboard.loadLastMessage());
    }

    /****************************** FXML ENDS HERE ******************************/

    private Admin admin;
    private AnchorPane container;

    private SQLNoticeboard sqlNoticeboard = new SQLNoticeboard();

    public void setRetrievedData(Admin admin, AnchorPane anchorPane) {
        this.admin = admin;
        this.container = anchorPane;
    }
    private void loadEditNoticeBoard() {
        EditNoticeBoardController controller;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UI/NoticeBoard/EditNoticeBoard.fxml"));
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

}
