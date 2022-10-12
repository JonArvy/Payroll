package Classes;

import Models.BooleanValue;
import Models.Department;
import Models.Employee;
import Models.SummarySchema;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.StringConverter;

import java.util.stream.Collectors;

public class Converters {
    private ObservableList<Department> departmentList = FXCollections.observableArrayList();

    public StringConverter<Department> departmentConverter() {
        StringConverter<Department> converter = new StringConverter<Department>() {
            @Override
            public String toString(Department department) {
                String s = "";
                try {
                    s = department.getDepartment_Name();
                } catch (NullPointerException e) {
                    s = "";
                }
                return s;
            }

            @Override
            public Department fromString(String s) {
                return departmentList.stream()
                        .filter(item -> item.getDepartment_Name().equals(s))
                        .collect(Collectors.toList()).get(0);
            }
        };
        return converter;
    }

    public StringConverter<BooleanValue> booleanValueConverter(ObservableList<BooleanValue> list) {
        StringConverter<BooleanValue> converter = new StringConverter<BooleanValue>() {
            @Override
            public String toString(BooleanValue booleanValue) {
                String s = "";
                try {
                    s = booleanValue.getName();
                } catch (NullPointerException e) {
                    s = "";
                }
                return s;
            }

            @Override
            public BooleanValue fromString(String s) {
                return list.stream()
                        .filter(item -> item.getName().equals(s))
                        .collect(Collectors.toList()).get(0);
            }
        };
        return converter;
    }


    public StringConverter<SummarySchema> summarySchemaStringConverter(ObservableList<SummarySchema> list) {
        StringConverter<SummarySchema> converter = new StringConverter<SummarySchema>() {
            @Override
            public String toString(SummarySchema summarySchema) {
                String s = "";
                try {
                    s = summarySchema.getSummary_name();
                } catch (NullPointerException e) {
                    s = "";
                }
                return s;
            }

            @Override
            public SummarySchema fromString(String s) {
                return list.stream()
                        .filter(item -> item.getSummary_name().equals(s))
                        .collect(Collectors.toList()).get(0);
            }
        };
        return converter;
    }


    public StringConverter<Employee> employeeStringConverter(ObservableList<Employee> list) {
        StringConverter<Employee> converter = new StringConverter<Employee>() {
            @Override
            public String toString(Employee employee) {
                String s = "";
                try {
                    s = employee.getFull_Name();
                } catch (NullPointerException e) {
                    s = "";
                }
                return s;
            }

            @Override
            public Employee fromString(String s) {
                return list.stream()
                        .filter(item -> item.getFull_Name().equals(s))
                        .collect(Collectors.toList()).get(0);
            }
        };
        return converter;
    }
}
