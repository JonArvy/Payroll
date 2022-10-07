package Classes;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.color.DeviceRgb;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
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

            float[] column_width = {30f, 200f, 100f, 50f, 50f, 200f, 50f, 100f, 50f, 50f, 200f, 50f, 100f, 50f};
            Table table = new Table(column_width);

            float fontsize = 5f;

            table.addCell(new Cell(0, 14).add("PAYROLL-PERSONAL SERVICES").setFontSize(fontsize).setTextAlignment(TextAlignment.CENTER));


            table.addCell(new Cell(0, 14).add("NOVEMBER 2021").setFontSize(fontsize).setTextAlignment(TextAlignment.CENTER));

            table.addCell(new Cell(1, 3).add("Barangay: CANUMAY WEST \n Barangay Treasurer: ZAMORA A. NAVARRO").setFontSize(fontsize));
            table.addCell(new Cell(1, 6).add("City/Municipality: VALENZUELA \n Province: METROPOLITAN MANILA").setFontSize(fontsize));
            table.addCell(new Cell(1, 5).add("Payroll No. 2021-11-032 \n Page No. 2021-01-PS").setFontSize(fontsize));

            table.addCell(new Cell(0, 0).add("").setFontSize(fontsize));
            table.addCell(new Cell(0, 0).add("").setFontSize(fontsize));
            table.addCell(new Cell(0, 0).add("").setFontSize(fontsize));
            table.addCell(new Cell(0, 6).add("COMPENSATION").setFontSize(fontsize).setTextAlignment(TextAlignment.CENTER));
            table.addCell(new Cell(0, 5).add("DEDUCTIONS").setFontSize(fontsize).setTextAlignment(TextAlignment.CENTER));

            table.addCell(new Cell(1, 0).add("NO.").setFontSize(fontsize).setTextAlignment(TextAlignment.CENTER));
            table.addCell(new Cell(1, 0).add("NAME").setFontSize(fontsize).setTextAlignment(TextAlignment.CENTER));
            table.addCell(new Cell(1, 0).add("Position").setFontSize(fontsize).setTextAlignment(TextAlignment.CENTER));
            table.addCell(new Cell(1, 0).add("Salaries and Wages").setFontSize(fontsize).setTextAlignment(TextAlignment.CENTER));
            table.addCell(new Cell(1, 0).add("Other Benefits").setFontSize(fontsize).setTextAlignment(TextAlignment.CENTER));
            table.addCell(new Cell(1, 0).add("No. of days").setFontSize(fontsize).setTextAlignment(TextAlignment.CENTER));
            table.addCell(new Cell(1, 0).add("No. of Days Absent").setFontSize(fontsize).setTextAlignment(TextAlignment.CENTER));
            table.addCell(new Cell(1, 0).add("Total").setFontSize(fontsize).setTextAlignment(TextAlignment.CENTER));
            table.addCell(new Cell(1, 0).add("BIR w/ Holding Tax").setFontSize(fontsize).setTextAlignment(TextAlignment.CENTER));
            table.addCell(new Cell(1, 0).add("Less").setFontSize(fontsize).setTextAlignment(TextAlignment.CENTER));
            table.addCell(new Cell(1, 0).add("Pag-IBIG").setFontSize(fontsize).setTextAlignment(TextAlignment.CENTER));
            table.addCell(new Cell(1, 0).add("Total").setFontSize(fontsize).setTextAlignment(TextAlignment.CENTER));
            table.addCell(new Cell(1, 0).add("Net Amount").setFontSize(fontsize).setTextAlignment(TextAlignment.CENTER));
            table.addCell(new Cell(1, 0).add("Signature of Recipient").setFontSize(fontsize).setTextAlignment(TextAlignment.CENTER));

            String[][] arr = {{},};

            for (int i = 0; i < 10; i++) {
                table.addCell(new Cell(0, 14).add("DEPARTMENT OF " + i).setFontSize(fontsize).setTextAlignment(TextAlignment.CENTER).setBackgroundColor(new DeviceRgb(153, 217, 234)));
                for (int y = 0; y < 20; y++) {
                    for (int x = 0; x < 14; x++) {
                        table.addCell(new Cell(0, 0).add(y + " " + x).setFontSize(fontsize).setTextAlignment(TextAlignment.CENTER));
                    }
                }
            }


            document.add(table);

//            Table table2 = new Table();

            document.close();
            System.out.println("Table Created");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
