package Classes;

import Models.Admin;
import Models.BonusSummary;
import Models.Summary;
import Models.SummarySchema;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.color.DeviceRgb;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.property.TextAlignment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.WritableImage;
import javafx.embed.swing.SwingFXUtils;


import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Comparator;

import static Classes.CustomAlert.callAlert;
import static Classes.DateTimeCalculator.*;

public class PDFWriter {
    public static void writePDF(String name) {
        try {
            String imgPath = "output/temp/temp.png";
            ImageData data = ImageDataFactory.create(imgPath);
            String path = "output/Payslips/" + name + ".pdf";
            Image image = new Image(data);
            PdfWriter pdfWriter = new PdfWriter(path);

            PdfDocument pdfDocument = new PdfDocument(pdfWriter);
            pdfDocument.addNewPage();

            Document document = new Document(pdfDocument);

            document.add(image);

            document.close();

            callAlert("PDF created Successfully", 2);
//            Runtime.getRuntime().exec("explorer.exe /select," + "C:\\Users\\Arvy Enriquez\\Desktop\\Text.txt");
            File file = new File (path);
            Desktop desktop = Desktop.getDesktop();
            desktop.open(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void generatePayslipBulk(String name, int count) {
        try {
            //Create PDF Path
            String path = "output/Payslips/" + name + ".pdf";
            //Create PDF Writer
            PdfWriter pdfWriter = new PdfWriter(path);
            //Create Document
            PdfDocument pdfDocument = new PdfDocument(pdfWriter);
//            pdfDocument.addNewPage();
            Document document = new Document(pdfDocument, PageSize.LEGAL);
            document.setMargins(24, 36, 12,  36);

            //Add all images to PDF
            for (int i = 0; i < count; i++) {
                String imgPath = "output/temp/" + i + ".png";
                ImageData data = ImageDataFactory.create(imgPath);
                Image image = new Image(data);

                document.add(image);
                document.add( new Paragraph("\n"));
            }


            document.close();

            callAlert("PDF created Successfully", 2);
//            Runtime.getRuntime().exec("explorer.exe /select," + "C:\\Users\\Arvy Enriquez\\Desktop\\Text.txt");
            File file = new File (path);
            Desktop desktop = Desktop.getDesktop();
            desktop.open(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void getImage(Node node) {
        try {
            WritableImage image = node.snapshot(null, null);
            String path = "output/temp/temp.png";
            File file = new File(path);
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "PNG", file);
            System.out.println("Image Saved");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void getImageWithName(Node node, String name) {
        try {
            WritableImage image = node.snapshot(null, null);
            String path = "output/temp/" + name + ".png";
            File file = new File(path);
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "PNG", file);
            System.out.println("Image Saved");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createPayrollSummaryPDF(ObservableList<Summary> summaryList, Date from, Date to, Admin admin) {

        ObservableList<String> deptlist = FXCollections.observableArrayList();

        for(int i = 0; i < summaryList.size(); i++){
            //we will skip the index until there are duplicates ahead
            while (i < summaryList.size() - 1 && summaryList.get(i).getDepartment().equals(summaryList.get(i + 1).getDepartment()))
                i++;

            //store element in the distinctArray
            deptlist.add(summaryList.get(i).getDepartment());
        }




        try {
            String path = "output/Payroll Summary/table.pdf";
            PdfWriter pdfWriter = new PdfWriter(path);
            PdfDocument pdfDocument = new PdfDocument(pdfWriter);
            Document document = new Document(pdfDocument, PageSize.LEGAL.rotate());

            float[] column_width = {30f, 200f, 100f, 50f, 50f, 200f, 50f, 100f, 100f, 50f, 150f, 50f, 100f, 50f};
            float[] column_width2 = {200f, 150f, 50f, 50f, 70f, 200f, 100f, 200f};
            Table table = new Table(column_width);
            Table table2 = new Table(column_width2);

            float fontsize = 5f;

            table.addCell(new Cell(0, 14).add("PAYROLL-PERSONAL SERVICES").setFontSize(fontsize).setTextAlignment(TextAlignment.CENTER));


            table.addCell(new Cell(0, 14).add(getMonthName(from) + " " + getYear(from)).setFontSize(fontsize).setTextAlignment(TextAlignment.CENTER));

            table.addCell(new Cell(1, 3).add("Barangay: CANUMAY WEST \n Barangay Treasurer: ZAMORA A. NAVARRO").setFontSize(fontsize));
            table.addCell(new Cell(1, 6).add("City/Municipality: VALENZUELA \n Province: METROPOLITAN MANILA").setFontSize(fontsize));
            table.addCell(new Cell(1, 5).add("Payroll No. " + getYear(from) + "-" + getMonthNumber(from) + "-032 \n Page No. " + getYear(from) + "-01-PS").setFontSize(fontsize));

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

            for (int i = 0; i < deptlist.size(); i++) {
                table.addCell(new Cell(0, 14).add("DEPARTMENT OF " + deptlist.get(i)).setFontSize(fontsize).setTextAlignment(TextAlignment.CENTER).setBackgroundColor(new DeviceRgb(153, 217, 234)));
                for (int y = 0; y < summaryList.size(); y++) {
                    if (summaryList.get(y).getDepartment().equals(deptlist.get(i))) {
//                    for (int x = 0; x < 14; x++) {
//                        table.addCell(new Cell(0, 0).add(y + " " + x).setFontSize(fontsize).setTextAlignment(TextAlignment.CENTER));
//                    }
                        table.addCell(new Cell(0, 0).add(String.valueOf(summaryList.get(y).getNumber())).setFontSize(fontsize).setTextAlignment(TextAlignment.CENTER));
                        table.addCell(new Cell(0, 0).add(String.valueOf(summaryList.get(y).getName())).setFontSize(fontsize).setTextAlignment(TextAlignment.CENTER));
                        table.addCell(new Cell(0, 0).add(String.valueOf(summaryList.get(y).getPosition())).setFontSize(fontsize).setTextAlignment(TextAlignment.CENTER));
                        table.addCell(new Cell(0, 0).add(String.valueOf(summaryList.get(y).getWage())).setFontSize(fontsize).setTextAlignment(TextAlignment.CENTER));
                        table.addCell(new Cell(0, 0).add("-").setFontSize(fontsize).setTextAlignment(TextAlignment.CENTER));
                        table.addCell(new Cell(0, 0).add(String.valueOf(summaryList.get(y).getPresentDays())).setFontSize(fontsize).setTextAlignment(TextAlignment.CENTER));
                        table.addCell(new Cell(0, 0).add(String.valueOf(summaryList.get(y).getAbsentDays())).setFontSize(fontsize).setTextAlignment(TextAlignment.CENTER));
                        table.addCell(new Cell(0, 0).add(String.valueOf(summaryList.get(y).getTotalCompensation())).setFontSize(fontsize).setTextAlignment(TextAlignment.CENTER));
                        table.addCell(new Cell(0, 0).add("EXEMPTED").setFontSize(fontsize).setTextAlignment(TextAlignment.CENTER).setBold());
                        table.addCell(new Cell(0, 0).add(String.valueOf(summaryList.get(y).getLess())).setFontSize(fontsize).setTextAlignment(TextAlignment.CENTER));
                        table.addCell(new Cell(0, 0).add("-").setFontSize(fontsize).setTextAlignment(TextAlignment.CENTER));
                        table.addCell(new Cell(0, 0).add(String.valueOf(summaryList.get(y).getTotalDeduction())).setFontSize(fontsize).setTextAlignment(TextAlignment.CENTER));
                        table.addCell(new Cell(0, 0).add(String.valueOf(summaryList.get(y).getNetAmount())).setFontSize(fontsize).setTextAlignment(TextAlignment.CENTER));
                        table.addCell(new Cell(0, 0).add("").setFontSize(fontsize).setTextAlignment(TextAlignment.CENTER));
                    }
                }
            }

            table2.addCell(new Cell(0, 0).add("\nA. Certified as to availability of appropriation for obligation in the amount\n\nSignature:\n\nPrinted Name:\nPosition:Chairman,Committee or Appropriation\n\n").setFontSize(fontsize));
            table2.addCell(new Cell(0, 4).add("\nB. Certified as to availability of funds and completeness and propriety of supporting documents\n\nSignature:\n\nPrinted Name:\nPosition: BARANGAY TREASURER\nDate: " + LocalDate.now()).setFontSize(fontsize));
            table2.addCell(new Cell(0, 0).add("\nC. Certified as to validity, propriety and legality of claim and approved for payment\n\nSignature:\n\nPrinted Name:\nPosition: PUNONG BARANGAY").setFontSize(fontsize));
            table2.addCell(new Cell(0, 2).add("\nD. Certified that each official/employee whose name appears on the above roll has been paid the amount stated opposite to his/her name\n\nSignature:\n\nPrinted Name:\nPosition: BARANGAY TREASURER\nDATE: " + LocalDate.now()).setFontSize(fontsize));

            table2.addCell(new Cell(0, 8).add("E. Accounting Entries").setFontSize(fontsize));

            table2.addCell(new Cell(0, 0).add("Account Title").setFontSize(fontsize).setTextAlignment(TextAlignment.CENTER));
            table2.addCell(new Cell(0, 0).add("Account Code").setFontSize(fontsize).setTextAlignment(TextAlignment.CENTER));
            table2.addCell(new Cell(0, 0).add(" ").setFontSize(fontsize).setTextAlignment(TextAlignment.CENTER));
            table2.addCell(new Cell(0, 0).add("Debit").setFontSize(fontsize).setTextAlignment(TextAlignment.CENTER));
            table2.addCell(new Cell(0, 0).add("Credit").setFontSize(fontsize).setTextAlignment(TextAlignment.CENTER));
            table2.addCell(new Cell(7, 0).add("Prepared By:\n\nBarangay BookKeeper:\nDate:\nApproved By:").setFontSize(fontsize));
            table2.addCell(new Cell(0, 0).add(" ").setFontSize(fontsize).setTextAlignment(TextAlignment.CENTER));
            table2.addCell(new Cell(0, 0).add(" ").setFontSize(fontsize).setTextAlignment(TextAlignment.CENTER));

            for (int i = 0; i < 42; i++) {
                table2.addCell(new Cell(0, 0).add(" ").setFontSize(fontsize).setTextAlignment(TextAlignment.CENTER));
            }





            document.add(table);
            document.add(new AreaBreak());
            document.add(table2);
//            document.add(table2);

//            Table table2 = new Table();

            document.close();
            callAlert("PDF Created Successfully", 2);
//            Runtime.getRuntime().exec("explorer.exe /select," + "C:\\Users\\Arvy Enriquez\\Desktop\\Text.txt");
            File file = new File (path);
            Desktop desktop = Desktop.getDesktop();
            desktop.open(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createBonusSummaryPDF(ObservableList<BonusSummary> bonusSummaries, Date date, String name) {
        try {
            String path = "output/Bonus Summary/bonus summary.pdf";
            PdfWriter pdfWriter = new PdfWriter(path);
            PdfDocument pdfDocument = new PdfDocument(pdfWriter);
            Document document = new Document(pdfDocument, PageSize.LETTER);

            float[] column_width = {30f, 200f, 100f, 100f, 100f};
            Table table = new Table(column_width);


            float fontsize = 5f;

            table.addCell(new Cell(0, 14).add("PAYROLL-BONUS SUMMARY").setFontSize(fontsize).setTextAlignment(TextAlignment.CENTER));


            table.addCell(new Cell(0, 14).add(getDateName(date) + " - " + name).setFontSize(fontsize).setTextAlignment(TextAlignment.CENTER));

            table.addCell(new Cell(1, 3).add("Barangay: CANUMAY WEST \n Barangay Treasurer: ZAMORA A. NAVARRO").setFontSize(fontsize));
            table.addCell(new Cell(1, 6).add("City/Municipality: VALENZUELA \n Province: METROPOLITAN MANILA").setFontSize(fontsize));


            table.addCell(new Cell(1, 0).add("NO.").setFontSize(fontsize).setTextAlignment(TextAlignment.CENTER));
            table.addCell(new Cell(1, 0).add("NAME").setFontSize(fontsize).setTextAlignment(TextAlignment.CENTER));
            table.addCell(new Cell(1, 0).add("EMPLOYEE ID").setFontSize(fontsize).setTextAlignment(TextAlignment.CENTER));
            table.addCell(new Cell(1, 0).add("DEPARTMENT").setFontSize(fontsize).setTextAlignment(TextAlignment.CENTER));
            table.addCell(new Cell(1, 0).add("BONUS AMOUNT").setFontSize(fontsize).setTextAlignment(TextAlignment.CENTER));

            for (int i = 0; i < 1; i++) {
//                table.addCell(new Cell(0, 14).add("DEPARTMENT OF " + i).setFontSize(fontsize).setTextAlignment(TextAlignment.CENTER).setBackgroundColor(new DeviceRgb(153, 217, 234)));
                for (int y = 0; y < bonusSummaries.size(); y++) {
//                    for (int x = 0; x < 14; x++) {
//                        table.addCell(new Cell(0, 0).add(y + " " + x).setFontSize(fontsize).setTextAlignment(TextAlignment.CENTER));
//                    }
                    table.addCell(new Cell(0, 0).add(String.valueOf(bonusSummaries.get(y).getBonus_ID())).setFontSize(fontsize).setTextAlignment(TextAlignment.CENTER));
                    table.addCell(new Cell(0, 0).add(String.valueOf(bonusSummaries.get(y).getFullname())).setFontSize(fontsize).setTextAlignment(TextAlignment.CENTER));
                    table.addCell(new Cell(0, 0).add(String.valueOf(bonusSummaries.get(y).getEmp_ID())).setFontSize(fontsize).setTextAlignment(TextAlignment.CENTER));
                    table.addCell(new Cell(0, 0).add(String.valueOf(bonusSummaries.get(y).getDepartment())).setFontSize(fontsize).setTextAlignment(TextAlignment.CENTER));
                    table.addCell(new Cell(0, 0).add(String.valueOf(bonusSummaries.get(y).getAmount())).setFontSize(fontsize).setTextAlignment(TextAlignment.CENTER));
                }
            }

            document.add(table);

            document.close();
            callAlert("PDF Created Successfully", 2);
//            Runtime.getRuntime().exec("explorer.exe /select," + "C:\\Users\\Arvy Enriquez\\Desktop\\Text.txt");
            File file = new File (path);
            Desktop desktop = Desktop.getDesktop();
            desktop.open(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
