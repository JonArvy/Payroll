package Controller.QRCodeScanner;

import Controller.LoginController;
import Database.SQLEmployee;
import Models.Employee;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import javafx.application.Platform;
import javafx.embed.swing.SwingNode;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import javax.swing.*;

import java.awt.image.BufferedImage;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import static Classes.CustomAlert.callAlert;
import static Classes.IPCamera.*;


public class QRScannerController implements Runnable, ThreadFactory{

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
                try {
                    boolean exist = sqlEmployee.checkIfEmployeeIDExists(Integer.parseInt(result.getText()));
                    if (!exist) {
                        this.result = result;
                        gotcalled = true;
                        terminateThreadAndIPCamera();
                    } else {
                        System.out.println("Employee not found");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid Code");
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
                    Employee employee = new Employee(Integer.parseInt(result.getText()));
                    loginController.loginAsEmployee(employee);
                }
            });


        }
        t.setDaemon(true);
        return t;
    }

    private boolean run = false;
    private boolean gotcalled = false;

    private Result result;
    public void terminateThreadAndIPCamera() {
        webcam.close();
        webcamPanel.stop();
        t.stop();
    }
}
