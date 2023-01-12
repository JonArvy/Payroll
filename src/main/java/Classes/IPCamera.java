package Classes;

import Database.SQLAdminAttendance;
import Models.Attendance;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.ds.ipcam.IpCamDeviceRegistry;
import com.github.sarxos.webcam.ds.ipcam.IpCamDriver;
import com.github.sarxos.webcam.ds.ipcam.IpCamMode;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Socket;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

import static Classes.CustomAlert.callAlert;

public class IPCamera {
    private static final String IP = "192.168.1.3";
    private static final int PORT = 8080;

    static {
        Webcam.setDriver(new IpCamDriver());
    }

    public static Webcam getIPWebcam() {
        try {
            IpCamDeviceRegistry.register("" + (Math.random() * 300), "http://" + IP + ":" + PORT + "/videofeed", IpCamMode.PUSH);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        return Webcam.getWebcams().get(0);
    }

    public static Webcam getIPWebcam(String name) {
        try {
            IpCamDeviceRegistry.register(name, "http://" + IP + ":" + PORT + "/videofeed", IpCamMode.PUSH);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        return Webcam.getWebcams().get(0);
    }

    public static WebcamPanel getIPCamPanel() {
        Webcam webcam = getIPWebcam();

        WebcamPanel panel = new WebcamPanel(webcam);
        panel.setFPSLimit(60);

//        panel.setFPSDisplayed(true);
//        panel.setDisplayDebugInfo(true);
//        panel.setImageSizeDisplayed(true);
//        panel.setMirrored(true);

        return panel;
    }

    public static WebcamPanel getIPCamPanel(Webcam webcam) {
        WebcamPanel panel = new WebcamPanel(webcam);
        panel.setFPSLimit(60);

//        panel.setFPSDisplayed(true);
//        panel.setDisplayDebugInfo(true);
//        panel.setImageSizeDisplayed(true);
//        panel.setMirrored(true);

        return panel;
    }

    public static void captureFromIPCamera() {
        Webcam webcam = getIPWebcam();
        webcam.open();
        try {
            ImageIO.write(webcam.getImage(), "JPG", new File("capture.jpg"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void unregisterIPCamera(String name) {
        IpCamDeviceRegistry.unregister(name);
    }

    public static boolean checkIfIPCameraIsOnline() {
        boolean isOnline = false;
        try {
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress(IP, PORT), 1000);
//            System.out.println("Connection to " + IP + ":" + PORT + " is online.");
            isOnline = true;
            socket.close();
        } catch (Exception e) {
            callAlert("Connection to camera is offline.", 1);
        }
        return isOnline;
    }
}
