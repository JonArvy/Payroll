package Controller.QRCodeScanner;

import Controller.LoginController;
import Database.SQLAdminAttendance;
import Database.SQLEmployee;
import Models.Attendance;
import Models.Employee;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import javafx.application.Platform;
import javafx.embed.swing.SwingNode;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import static Classes.IPCamera.getIPCamPanel;
import static Classes.IPCamera.getIPWebcam;


public class QRScannerForAttendanceController implements Runnable, ThreadFactory{

    @FXML
    private AnchorPane content_container;


    private Webcam webcam = null;
    private WebcamPanel webcamPanel = null;
    private Executor executor = Executors.newSingleThreadExecutor(this);

    private LoginController loginController;

    Thread t;


    @FXML
    private void initialize() {

    }

    public void getCalled() {
        webcam = getIPWebcam("" + Math.random() * 10000);
        webcamPanel = getIPCamPanel(webcam);
        final SwingNode swingNode = new SwingNode();

        createSwingContent(swingNode);

        StackPane pane = new StackPane();
        pane.getChildren().add(swingNode);
        pane.setPrefWidth(content_container.getPrefWidth());
        pane.setPrefHeight(content_container.getPrefHeight());

        content_container.getChildren().add(pane);

        executor.execute(this);
        System.out.println("2");
    }

    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }

//    private boolean verifyEmployee() {
//
//    }


    private void createSwingContent(final SwingNode swingNode) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                swingNode.setContent(webcamPanel);
                webcamPanel.start();
            }
        });
    }

    @Override
    public void run() {
        do {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Result result = null;
            BufferedImage image = null;

            if (webcam.isOpen()) {

                if ((image = webcam.getImage()) == null) {
                    continue;
                }

                LuminanceSource source = new BufferedImageLuminanceSource(image);
                BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

                try {
                    result = new MultiFormatReader().decode(bitmap);
                } catch (NotFoundException e) {
                    // fall thru, it means there is no QR code in image
                }
            }

            if (result != null) {
                SQLEmployee sqlEmployee = new SQLEmployee();
                System.out.println(result.getText());
                boolean exist = sqlEmployee.checkIfBiometricsExist(result.getText());
                if (exist) {
                    this.result = result;
                    gotcalled = true;
                    employee = sqlEmployee.getEmployeeWithBiometrics(result.getText());

                    terminateThreadAndIPCamera();
                } else {
                    System.out.println("Employee not found");
                }

            }

        } while (true);
    }


    @Override
    public Thread newThread(Runnable r) {
        t = new Thread(r, "camera-thread");
        if (!run) {
            run = true;
        } else {

            Platform.runLater(() -> {
                Stage stage = (Stage) content_container.getScene().getWindow();
                stage.close();
                if (gotcalled) {
                    System.out.println("Got called");
//                    gotcalled = false;
                    //Attendance
                    attendance = new Attendance();
                    attendance.setEmployee_ID(employee.getEmployee_ID());
                    attendance.setEmployee_Attendance_Date(Date.valueOf(LocalDate.now()));
                    attendance.setEmployee_TimeIn(Time.valueOf(LocalTime.now()));
                    attendance.setEmployee_TimeOut(Time.valueOf(LocalTime.now()));
                    SQLAdminAttendance sqlAdminAttendance = new SQLAdminAttendance();

                    loginController.loadTimeInfo(employee, attendance, !sqlAdminAttendance.checkIfAttendanceExist(attendance));

                    sqlAdminAttendance.registerAttendanceUsingQR(attendance);
                }
            });


        }
        t.setDaemon(true);
        return t;
    }

    private boolean run = false;
    private boolean gotcalled = false;

    private boolean isTimeIn = false;

    private Employee employee;
    private Attendance attendance;
    private Result result;
    public void terminateThreadAndIPCamera() {
        webcam.close();
        webcamPanel.stop();
        t.stop();
    }
}
