package Classes;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.WritableImage;
import javafx.embed.swing.SwingFXUtils;


import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;

public class PDFWriter {
    public static void writePDF(String name) {
        try {
            String imgPath = "src/main/resources/cw/payroll/output/temp.png";
            ImageData data = ImageDataFactory.create(imgPath);
            String path = "src/main/resources/cw/payroll/output/" + name + ".pdf";
            Image image = new Image(data);
            PdfWriter pdfWriter = new PdfWriter(path);

            PdfDocument pdfDocument = new PdfDocument(pdfWriter);
            pdfDocument.addNewPage();

            Document document = new Document(pdfDocument);

            document.add(image);

            document.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void getImage(Node node) {
        try {
            WritableImage image = node.snapshot(null, null);
            File file = new File("src/main/resources/cw/payroll/output/temp.png");
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "PNG", file);
            System.out.println("Image Saved");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
