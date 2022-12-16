package Controller.Importer;

import Database.SQLAttendance;
import Database.SQLEmployee;
import Models.Attendance;
import Models.Employee;
import Models.ExcelAttendance;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ImportAttendanceTableController {

    @FXML
    private TableColumn<ExcelAttendance, String> date;

    @FXML
    private TableColumn<ExcelAttendance, String> employee_id;

    @FXML
    private Text filename;

    @FXML
    private TableView<ExcelAttendance> table;

    @FXML
    private TableColumn<ExcelAttendance, String> time_in;

    @FXML
    private TableColumn<ExcelAttendance, String> time_out;

    @FXML
    private void cancel(ActionEvent event) {
        Stage stage = (Stage) table.getScene().getWindow();
        stage.close();
    }
    @FXML
    private void importFile(ActionEvent event) {
        attendanceData.removeIf(attd -> attd.getEmployee_id().trim().equals("") ||
                attd.getDate().trim().equals("") ||
                attd.getTime_in().trim().equals("") ||
                attd.getTime_out().trim().equals("") ||
                !parser(1, attd.getEmployee_id()) ||
                !parser(2, attd.getDate()) ||
                !parser(3, attd.getTime_in()) ||
                !parser(3, attd.getTime_out()));


        ObservableList<Attendance> attendanceList = FXCollections.observableArrayList();
        for (int i = 0; i < attendanceData.size(); i++) {
            Attendance attendance = new Attendance();
            attendance.setEmployee_ID(EmployeeHandlerForEmployeeID(attendanceData.get(i).getEmployee_id()));
            attendance.setEmployee_Attendance_Date(EmployeeHandlerForDate(attendanceData.get(i).getDate()));
            attendance.setEmployee_TimeIn(EmployeeHandlerForTime(attendanceData.get(i).getTime_in()));
            attendance.setEmployee_TimeOut(EmployeeHandlerForTime(attendanceData.get(i).getTime_out()));

//            if () {
//                continue;
//            }
            attendanceList.add(attendance);
        }
        SQLAttendance sqlAttendance = new SQLAttendance();
        sqlAttendance.registerMultipleAttendance(attendanceList);


        Stage stage = (Stage) table.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void initialize() {
    }

    /****************************** FXML ENDS HERE ******************************/
    private File file;
    public void setFile(File file) {
        this.file = file;
        filename.setText(file.getName());
        loadFileUsingIncrements();
    }

    ObservableList<ExcelAttendance> attendanceData = FXCollections.observableArrayList();

    private void loadFileUsingIncrements() {
        ObservableList<ExcelAttendance> data = FXCollections.observableArrayList();
        try {
            //obtaining input bytes from a file
            FileInputStream fis = new FileInputStream(this.file);
            //creating workbook instance that refers to .xls file
            XSSFWorkbook wb = new XSSFWorkbook(fis);
            wb.setMissingCellPolicy(Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
            XSSFSheet sheet = wb.getSheetAt(0);
            DataFormatter fmt = new DataFormatter();

            for (int rn=sheet.getFirstRowNum(); rn<=sheet.getLastRowNum(); rn++) {
                Row row = sheet.getRow(rn);
                if (row == null) {
                    // There is no data in this row, handle as needed
                    continue;
                } else {
                    // Row "rn" has data
                    if (fmt.formatCellValue(row.getCell(0)).equals("employee_id") || fmt.formatCellValue(row.getCell(1)).equals("date")) {
                        continue;
                    }
                    String[] arr = new String[4];
                    for (int cn=0; cn < 4; cn++) {
                        Cell cell = row.getCell(cn);
                        String cellStr = convertCellValue(cell);
//                        System.out.print(cellStr + "\t\t");
                        arr[cn] = cellStr;
                    }
                    ExcelAttendance attd = new ExcelAttendance(arr[0], arr[1], arr[2], arr[3]);
                    data.add(attd);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        attendanceData = data;
        loadTable(data);

    }

    private String convertCellValue(Cell cell) {
        DataFormatter fmt = new DataFormatter();
        String cellStringValue = "";
        if (cell != null) {
            cellStringValue = fmt.formatCellValue(cell);
        }
        return cellStringValue;
    }

    private void loadTable(ObservableList<ExcelAttendance> data) {
        employee_id.setCellValueFactory(new PropertyValueFactory<ExcelAttendance, String>("employee_id"));
        date.setCellValueFactory(new PropertyValueFactory<ExcelAttendance, String>("date"));
        time_in.setCellValueFactory(new PropertyValueFactory<ExcelAttendance, String>("time_in"));
        time_out.setCellValueFactory(new PropertyValueFactory<ExcelAttendance, String>("time_out"));

        table.setItems(data);
        styleRows();
    }


    private void styleRows() {
        Callback<TableColumn<ExcelAttendance, String>, TableCell<ExcelAttendance, String>> cellFactory
                = //
                new Callback<TableColumn<ExcelAttendance, String>, TableCell<ExcelAttendance, String>>() {
                    @Override
                    public TableCell<ExcelAttendance, String> call(final TableColumn<ExcelAttendance, String> param) {
                        final TableCell<ExcelAttendance, String> cell = new TableCell<ExcelAttendance, String>() {

                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    setText(item);
                                    TableRow<ExcelAttendance> row = getTableRow();
//                                    if (row.getIndex() % 2 == 0) {
//                                        row.setStyle("-fx-background-color:lightcoral");
//                                    } else {
//                                        row.setStyle("-fx-background-color:lightgreen");
//                                    }
                                    try {
                                        if (row.getItem() == null) {
                                            return;
                                        }
                                        if (row.getItem().getEmployee_id().trim().equals("") ||
                                                row.getItem().getDate().trim().equals("") ||
                                                row.getItem().getTime_in().trim().equals("") ||
                                                row.getItem().getTime_out().trim().equals("") ||
                                                !parser(1, row.getItem().getEmployee_id()) ||
                                                !parser(2, row.getItem().getDate())
//                                                !parser(3, row.getItem().getTime_in()) ||
//                                                !parser(3, row.getItem().getTime_out())
                                        ) {
                                            row.setStyle("-fx-background-color:lightcoral");
                                        } else {
                                            row.setStyle("-fx-background-color:lightgreen");
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    // True Conditions


                                }
                            }
                        };
                        return cell;
                    }
                };
        employee_id.setCellFactory(cellFactory);
    }

    private Boolean parser(int mode, String str) {
        boolean isSuccessful = false;

        try {
            switch (mode) {
                case 1:
                    int a = Integer.parseInt(str);
                    isSuccessful = true;
                    break;
                case 2:
                    System.out.println(str);
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                    Date d = Date.valueOf(LocalDate.parse(str, formatter));
                    System.out.println(d);
                    System.out.println("Kek");
                    isSuccessful = true;
                    System.out.println(isSuccessful);
                    break;
                case 3:
                    Time t = Time.valueOf(str);
                    isSuccessful = true;
                    break;

            }
        } catch (Exception e) {
//            e.printStackTrace();
        }

        return isSuccessful;
    }


    private Date EmployeeHandlerForDate(String value) {
        Date birthday = Date.valueOf(LocalDate.of(2000, 1, 1));
        try {
            birthday = Date.valueOf(value);
        } catch (Exception e) {
//            e.printStackTrace();
            System.out.println("Invalid Date Format");
        }
        return birthday;
    }

    private Time EmployeeHandlerForTime(String value) {
        Time time = Time.valueOf(LocalTime.of(0, 0, 0));
        try {
            time = Time.valueOf(value);
        } catch (Exception e) {

        }
        return time;
    }

    private int EmployeeHandlerForEmployeeID(String value) {
        int id = 1;
        try {
            id = Integer.parseInt(value);
        } catch (Exception e) {

        }
        return id;
    }
}
