package Classes;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.ds.ipcam.IpCamDeviceRegistry;
import com.github.sarxos.webcam.ds.ipcam.IpCamDriver;
import com.github.sarxos.webcam.ds.ipcam.IpCamMode;

import java.net.MalformedURLException;

public class IPCamera {
    static {
        Webcam.setDriver(new IpCamDriver());
    }

    public static Webcam getIPWebcam() {
        try {
            IpCamDeviceRegistry.register("", "http://192.168.1.3:8080/videofeed", IpCamMode.PUSH);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        return Webcam.getWebcams().get(0);
    }

    public static WebcamPanel getIPCamPanel() {
        Webcam webcam = getIPWebcam();

        WebcamPanel panel = new WebcamPanel(webcam);
        panel.setFPSLimit(60);

        return panel;
    }
}
