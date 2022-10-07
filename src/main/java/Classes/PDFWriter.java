package Classes;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Table;
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

    public static void createTablePDF() {
        try {
            String path = "src/main/resources/cw/payroll/output/table.pdf";
            PdfWriter pdfWriter = new PdfWriter(path);
            PdfDocument pdfDocument = new PdfDocument(pdfWriter);
            Document document = new Document(pdfDocument, PageSize.LEGAL.rotate());

            float[] column_width = {200f, 50f, 100f, 50f, 50f, 200f, 50f, 100f, 50f, 50f, 200f, 50f, 100f, 50f};
            Table table = new Table(column_width);

            Cell cell = new Cell(0, 14).add("PAYROLL-PERSONAL SERVICES");

            Cell cell2 = new Cell(0, 14).add("NOVEMBER 2021");

            Cell cell3_1 = new Cell(2, 3).add("Barangay: CANUMAY WEST \n Barangay Treasurer: ZAMORA A. NAVARRO");
            Cell cell3_2 = new Cell(2, 6).add("City/Municipality: VALENZUELA \n Province: METROPOLITAN MANILA");
            Cell cell3_3 = new Cell(2, 5).add("Payroll No. 2021-11-032 \n Page No. 2021-01-PS");

            Cell cell4_1 = new Cell().add("");
            Cell cell4_2 = new Cell().add("");
            Cell cell4_3 = new Cell().add("");
            Cell cell4_4 = new Cell(0, 6).add("COMPENSATION");
            Cell cell4_5 = new Cell(0, 5).add("DEDUCTIONS");

            Cell cell5_1 = new Cell(2, 0).add("NO.");
            Cell cell5_2 = new Cell(2, 0).add("NAME");
            Cell cell5_3 = new Cell(2, 0).add("Position");
            Cell cell5_4 = new Cell(2, 0).add("Salaries and Wages");
            Cell cell5_5 = new Cell(2, 0).add("Other Benefits");
            Cell cell5_6 = new Cell(2, 0).add("No. of days");
            Cell cell5_7 = new Cell(2, 0).add("No. of Days Absent");
            Cell cell5_8 = new Cell(2, 0).add("Total");
            Cell cell5_9 = new Cell(2, 0).add("BIR w/ Holding Tax");
            Cell cell5_10 = new Cell(2, 0).add("Less");
            Cell cell5_11 = new Cell(2, 0).add("Pag-IBIG");
            Cell cell5_12 = new Cell(2, 0).add("Total");
            Cell cell5_13 = new Cell(2, 0).add("Net Amount");
            Cell cell5_14 = new Cell(2, 0).add("Signature of Recipient");

            Cell dept_title = new Cell(0, 14).add("DEPARTMENT OF HEALTH");



            table.addCell(cell);
            table.addCell(cell2);
            table.addCell(cell3_1);
            table.addCell(cell3_2);
            table.addCell(cell3_3);
            table.addCell(cell4_1);
            table.addCell(cell4_2);
            table.addCell(cell4_3);
            table.addCell(cell4_4);
            table.addCell(cell4_5);
            table.addCell(cell5_1);
            table.addCell(cell5_2);
            table.addCell(cell5_3);
            table.addCell(cell5_4);
            table.addCell(cell5_5);
            table.addCell(cell5_6);
            table.addCell(cell5_7);
            table.addCell(cell5_8);
            table.addCell(cell5_9);
            table.addCell(cell5_10);
            table.addCell(cell5_11);
            table.addCell(cell5_12);
            table.addCell(cell5_13);
            table.addCell(cell5_14);
            table.addCell(dept_title);


            document.add(table);

            document.close();
            System.out.println("Table Created");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
