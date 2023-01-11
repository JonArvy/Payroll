package Classes;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import Controller.Logs.MenuController;
import Controller.QRCodeScanner.QRCodeController;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import cw.payroll.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Map;

public class QRCodeGenerator {
    public static void createQR(String data, String filename) {
        try {
            String path = "output/qr/" + filename + ".png";
            String charset = "UTF-8";
            int width = 200;
            int height = 200;

            Map<EncodeHintType, ErrorCorrectionLevel> hashMap
                    = new HashMap<EncodeHintType,
                    ErrorCorrectionLevel>();

            hashMap.put(EncodeHintType.ERROR_CORRECTION,
                    ErrorCorrectionLevel.L);

            BitMatrix matrix = new MultiFormatWriter().encode(
                    new String(data.getBytes(charset), charset),
                    BarcodeFormat.QR_CODE, width, height);

            MatrixToImageWriter.writeToFile(
                    matrix,
                    path.substring(path.lastIndexOf('.') + 1),
                    new File(path));


//            System.out.println(data + " " + data.substring(4,14));
            showQRWindow(path, data.substring(4,14));
        } catch (WriterException we) {
            we.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }


    public static void deleteAllQRImages() {
        File folder = new File("output/qr/");
        deleteFiles(folder);
    }

    public static void deleteFiles(File folder) {
        File[] files = folder.listFiles();
        if(files!=null) {
            for(File f: files) {
                if(f.isDirectory()) {
                    deleteFiles(f);
                } else {
                    f.delete();
                }
            }
        }
    }

    private static void showQRWindow(String path, String password) {
        Parent root;
        QRCodeController controller;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UI/QRCodeScanner/QRCode.fxml"));
            root = fxmlLoader.load();

            controller = fxmlLoader.getController();

            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.getIcons().add(new Image(Main.class.getResourceAsStream("/cw/payroll/Assets/CWBH Logo.png")));
            stage.setResizable(false);
            stage.show();

            System.out.println(path);
            controller.setImage(path, password);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
