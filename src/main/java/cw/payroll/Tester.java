package cw.payroll;

import Classes.SQLExecution;
import Models.Employee;

import java.sql.Date;
import java.time.LocalDate;

public class Tester {

    public static void main(String[] args) {
//        String raw = Main.class.getClassLoader().getResource("").getPath();
//        String filepath = raw.replace("target/classes/", "src/main/java/Tools/");
//        filepath = filepath.substring(1).replace("%20", " ");
//        filepath = filepath.replace("/", "\\");
//        System.out.println(filepath);


//        new Tester().createTables(new SQLExecution());
    }

    public void createTables(SQLExecution sql) {
        sql.createTables();
    }

    public void dropTables(SQLExecution sql) {
        sql.dropTables();
    }

    public void populateEmployeeTable(SQLExecution sql) {
        sql.createTables();

        String letters = "abcdefghijklmnopqrstuvwxyz";
        for (int i = 0; i < 100; i++) {
            Employee rand = new Employee(
                    i,
                    "Filipino",
                    "Single",
                    i % 5,
                    "i % 3",
                    "Permanent",

                    letters.charAt(i % 26) + "",
                    letters.charAt((i + 20) % 26) + "",
                    letters.charAt((i + 10) % 26) + "",
                    letters.charAt((i + 5) % 26) + "",
                    letters.charAt(i % 26) + " " + letters.charAt((i + 20) % 26),

                    true,

                    "asd",

                    Date.valueOf(LocalDate.now()),
                    true,

                    "private String Contact_Name;",
                    "private String Contact_Relationship;",
                    "private String Contact_Address;",

                    "private String Contact_Number;",
                    "Mema"
            );
            sql.addEmployee(rand);
        }
    }
}
