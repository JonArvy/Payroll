package Controller.Importer;

import Database.SQLEmployee;
import Models.Employee;
import Models.ExcelEmployee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.apache.poi.ss.formula.atp.Switch;
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
import java.time.LocalDate;

public class ImportEmployeeTableController {

    @FXML
    private TableColumn<ExcelEmployee, String> address;

    @FXML
    private TableColumn<ExcelEmployee, String> birthdate;

    @FXML
    private TableColumn<ExcelEmployee, String> contact_number;

    @FXML
    private TableColumn<ExcelEmployee, String> department;

    @FXML
    private TableColumn<ExcelEmployee, String> emergency_contact_address;

    @FXML
    private TableColumn<ExcelEmployee, String> emergency_contact_name;

    @FXML
    private TableColumn<ExcelEmployee, String> emergency_contact_number;

    @FXML
    private TableColumn<ExcelEmployee, String> emergency_contact_relationship;

    @FXML
    private TableColumn<ExcelEmployee, String> employment_status;

    @FXML
    private TableColumn<ExcelEmployee, String> extension;

    @FXML
    private Text filename;

    @FXML
    private TableColumn<ExcelEmployee, String> first_name;

    @FXML
    private TableColumn<ExcelEmployee, String> gender;

    @FXML
    private TableColumn<ExcelEmployee, String> last_name;

    @FXML
    private TableColumn<ExcelEmployee, String> marital_status;

    @FXML
    private TableColumn<ExcelEmployee, String> middle_name;

    @FXML
    private TableColumn<ExcelEmployee, String> nationality;

    @FXML
    private TableColumn<ExcelEmployee, String> position;

    @FXML
    private TableColumn<ExcelEmployee, String> status;

    @FXML
    private TableView<ExcelEmployee> table;

    @FXML
    private void cancel(ActionEvent event) {
        Stage stage = (Stage) table.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void importFile(ActionEvent event) {
        employeeData.removeIf(emp -> emp.getFirst_name().trim().equals("") ||
                emp.getLast_name().trim().equals("") ||
                emp.getContact_number().trim().equals("") ||
                emp.getStatus().trim().equals("") ||
                emp.getDepartment().trim().equals("") ||
                emp.getPosition().trim().equals("") ||
                emp.getEmployment_status().trim().equals("") ||
                !parser(2, emp.getStatus()) ||
                !parser(1, emp.getDepartment()));


        ObservableList<Employee> employeeList = FXCollections.observableArrayList();
        for (int i = 0; i < employeeData.size(); i++) {
            Employee employee = new Employee();
            employee.setFirst_Name(employeeData.get(i).getFirst_name());
            employee.setLast_Name(employeeData.get(i).getLast_name());
            employee.setMiddle_Name(employeeData.get(i).getMiddle_name());
            employee.setExtension(employeeData.get(i).getExtension());
            employee.setAddress(employeeData.get(i).getAddress());
            employee.setGender(EmployeeHandlerForGender(employeeData.get(i).getGender()));
            employee.setNumber(employeeData.get(i).getContact_number());
            employee.setBirthdate(EmployeeHandlerForBirthDate(employeeData.get(i).getBirthdate()));
            employee.setActive(EmployeeHandlerForStatus(employeeData.get(i).getStatus()));
            employee.setNationality(employeeData.get(i).getNationality());
            employee.setMarital_Status(employeeData.get(i).getMarital_status());
            employee.setDepartment(EmployeeHandlerForDepartment(employeeData.get(i).getDepartment()));
            employee.setPosition(employeeData.get(i).getPosition());
            employee.setEmployment_Status(employeeData.get(i).getEmployment_status());
            employee.setContact_Name(employeeData.get(i).getEmergency_contact_name());
            employee.setContact_Relationship(employeeData.get(i).getEmergency_contact_relationship());
            employee.setContact_Address(employeeData.get(i).getEmergency_contact_address());
            employee.setContact_Number(employeeData.get(i).getEmergency_contact_number());
//            ExcelEmployee emp = employeeData.get(i);
            employeeList.add(employee);
        }
        SQLEmployee sqlEmployee = new SQLEmployee();
        sqlEmployee.addMultipleEmployee(employeeList);


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

    ObservableList<ExcelEmployee> employeeData = FXCollections.observableArrayList();

    private void loadFile() {
        ObservableList<ExcelEmployee> data = FXCollections.observableArrayList();
        try {
            //obtaining input bytes from a file
            FileInputStream fis = new FileInputStream(this.file);
            //creating workbook instance that refers to .xls file
            XSSFWorkbook wb = new XSSFWorkbook(fis);
            wb.setMissingCellPolicy(Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
            //creating a Sheet object to retrieve the object
            XSSFSheet sheet = wb.getSheetAt(0);
            //evaluating cell type
            FormulaEvaluator formulaEvaluator = wb.getCreationHelper().createFormulaEvaluator();
            for (Row row : sheet) {   //iteration over row using for each loop
                int i = 0;
                for (Cell cell : row) {   //iteration over cell using for each loop
                    switch (formulaEvaluator.evaluateInCell(cell).getCellType()) {
                        case NUMERIC:   //field that represents numeric cell type
                            //getting the value of the cell as a number
                            System.out.print(cell.getNumericCellValue() + "\t\t");
                            break;
                        case STRING:    //field that represents string cell type
                            //getting the value of the cell as a string
                            System.out.print(cell.getStringCellValue() + "\t\t");
                            break;
                        case BLANK:
                            System.out.print("BLANK\t\t");
                            break;
                    }
                    i++;
                }
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadFileUsingIncrements() {
        ObservableList<ExcelEmployee> data = FXCollections.observableArrayList();
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
                    if (fmt.formatCellValue(row.getCell(0)).equals("first_name") || fmt.formatCellValue(row.getCell(1)).equals("last_name")) {
                        continue;
                    }
                    String[] arr = new String[18];
                    for (int cn=0; cn < 18; cn++) {
                        Cell cell = row.getCell(cn);
                        String cellStr = convertCellValue(cell);
//                        System.out.print(cellStr + "\t\t");
                        arr[cn] = cellStr;
                    }
                    ExcelEmployee emp = new ExcelEmployee(arr[0], arr[1], arr[2], arr[3], arr[4], arr[5], arr[6], arr[7], arr[8], arr[9], arr[10], arr[11], arr[12], arr[13], arr[14], arr[15], arr[16], arr[17]);
                    data.add(emp);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        employeeData = data;
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

    private void loadTable(ObservableList<ExcelEmployee> data) {
        first_name.setCellValueFactory(new PropertyValueFactory<ExcelEmployee, String>("first_name"));
        last_name.setCellValueFactory(new PropertyValueFactory<ExcelEmployee, String>("last_name"));
        middle_name.setCellValueFactory(new PropertyValueFactory<ExcelEmployee, String>("middle_name"));
        extension.setCellValueFactory(new PropertyValueFactory<ExcelEmployee, String>("extension"));
        address.setCellValueFactory(new PropertyValueFactory<ExcelEmployee, String>("address"));
        gender.setCellValueFactory(new PropertyValueFactory<ExcelEmployee, String>("gender"));
        contact_number.setCellValueFactory(new PropertyValueFactory<ExcelEmployee, String>("contact_number"));
        birthdate.setCellValueFactory(new PropertyValueFactory<ExcelEmployee, String>("birthdate"));
        status.setCellValueFactory(new PropertyValueFactory<ExcelEmployee, String>("status"));
        nationality.setCellValueFactory(new PropertyValueFactory<ExcelEmployee, String>("nationality"));
        marital_status.setCellValueFactory(new PropertyValueFactory<ExcelEmployee, String>("marital_status"));
        department.setCellValueFactory(new PropertyValueFactory<ExcelEmployee, String>("department"));
        position.setCellValueFactory(new PropertyValueFactory<ExcelEmployee, String>("position"));
        employment_status.setCellValueFactory(new PropertyValueFactory<ExcelEmployee, String>("employment_status"));
        emergency_contact_name.setCellValueFactory(new PropertyValueFactory<ExcelEmployee, String>("emergency_contact_name"));
        emergency_contact_number.setCellValueFactory(new PropertyValueFactory<ExcelEmployee, String>("emergency_contact_number"));
        emergency_contact_address.setCellValueFactory(new PropertyValueFactory<ExcelEmployee, String>("emergency_contact_address"));
        emergency_contact_relationship.setCellValueFactory(new PropertyValueFactory<ExcelEmployee, String>("emergency_contact_relationship"));

        table.setItems(data);
        styleRows();

//        currentRow.setStyle("-fx-background-color:lightcoral");
//        currentRow.setStyle("-fx-background-color:lightgreen");
//        table.getItems().ge
    }


    private void styleRows() {
        Callback<TableColumn<ExcelEmployee, String>, TableCell<ExcelEmployee, String>> cellFactory
                = //
                new Callback<TableColumn<ExcelEmployee, String>, TableCell<ExcelEmployee, String>>() {
                    @Override
                    public TableCell<ExcelEmployee, String> call(final TableColumn<ExcelEmployee, String> param) {
                        final TableCell<ExcelEmployee, String> cell = new TableCell<ExcelEmployee, String>() {

                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    setText(item);
                                    TableRow<ExcelEmployee> row = getTableRow();
//                                    if (row.getIndex() % 2 == 0) {
//                                        row.setStyle("-fx-background-color:lightcoral");
//                                    } else {
//                                        row.setStyle("-fx-background-color:lightgreen");
//                                    }
                                    try {
                                        if (row.getItem() == null) {
                                            return;
                                        }
                                        if (row.getItem().getFirst_name().trim().equals("") ||
                                                row.getItem().getLast_name().trim().equals("") ||
                                                row.getItem().getContact_number().trim().equals("") ||
                                                row.getItem().getStatus().trim().equals("") ||
                                                row.getItem().getDepartment().trim().equals("") ||
                                                row.getItem().getPosition().trim().equals("") ||
                                                row.getItem().getEmployment_status().trim().equals("") ||
                                                !parser(2, row.getItem().getStatus()) ||
                                                !parser(1, row.getItem().getDepartment())) {
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
        first_name.setCellFactory(cellFactory);
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
                    if (str.equalsIgnoreCase("true") ||
                        str.equalsIgnoreCase("false") ||
                        str.equalsIgnoreCase("1") ||
                        str.equalsIgnoreCase("0")) {
                        isSuccessful = true;
                    }
                    break;
                case 3:
                    Double c = Double.parseDouble(str);
                    isSuccessful = true;
                    break;
                case 4:
                    Date d = Date.valueOf(str);
                    isSuccessful = true;
                    break;

            }
        } catch (Exception e) {
//            e.printStackTrace();
        }

        return isSuccessful;
    }

    private Boolean EmployeeHandlerForStatus(String value) {
        boolean status = false;
        if (value.equalsIgnoreCase("true") ||
                value.equalsIgnoreCase("false") ||
                value.equalsIgnoreCase("1") ||
                value.equalsIgnoreCase("0")) {
            status = value.equalsIgnoreCase("true") ||
                    value.equalsIgnoreCase("1");
        }
        return status;
    }

    private Date EmployeeHandlerForBirthDate(String value) {
        Date birthday = Date.valueOf(LocalDate.of(2000, 1, 1));
        try {
            birthday = Date.valueOf(value);
        } catch (Exception e) {
//            e.printStackTrace();
            System.out.println("Invalid Date Format");
        }
        return birthday;
    }

    private Boolean EmployeeHandlerForGender(String value) {
        boolean gender = false;
        if (value.equalsIgnoreCase("male") ||
                value.equalsIgnoreCase("female") ||
                value.equalsIgnoreCase("m") ||
                value.equalsIgnoreCase("f")) {
            gender = value.equalsIgnoreCase("male") ||
                    value.equalsIgnoreCase("m");
        }
        return gender;
    }

    private int EmployeeHandlerForDepartment(String value) {
        int dept = 1;
        try {
            dept = Integer.parseInt(value);
        } catch (Exception e) {

        }
        return dept;
    }
}
