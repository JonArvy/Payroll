package Models;

import java.sql.Date;

public class Employee {
    // Self ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    private int Employee_ID;
    private String Nationality;
    private String Marital_Status;
    private int Department;
    private String Position;
    private String Employment_Status;

    private String First_Name;
    private String Last_Name;
    private String Middle_Name;
    private String Extension;
    private String Address;

    private boolean Gender;

    private String Number;

    private Date Birthdate;
    private Boolean Active;

    private String Employee_Biometrics;
    // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    // Emergency Contact Information Starts here --------------------------
    private String Contact_Name;
    private String Contact_Relationship;
    private String Contact_Address;

    private String Contact_Number;


    // --------------------------------------------------------------------
    public Employee() {

    }


    public Employee(int Employee_ID, String Nationality, String Marital_Status, int Department, String Position, String Employment_Status, String First_Name, String Last_Name, String Middle_Name, String Extension, String Address, boolean Gender, String Number, Date Birthdate, Boolean Active, String Contact_Name, String Contact_Relationship, String Contact_Address, String Contact_Number, String Employee_Biometrics) {
        this.Employee_ID = Employee_ID;
        this.Nationality = Nationality;
        this.Marital_Status = Marital_Status;
        this.Department = Department;
        this.Position = Position;
        this.Employment_Status = Employment_Status;

        this.First_Name = First_Name;
        this.Last_Name = Last_Name;
        this.Middle_Name = Middle_Name;
        this.Extension = Extension;
        this.Address = Address;

        this.Gender = Gender;

        this.Number = Number;

        this.Birthdate = Birthdate;
        this.Active = Active;

        this.Contact_Name = Contact_Name;
        this.Contact_Relationship = Contact_Relationship;
        this.Contact_Address = Contact_Address;

        this.Contact_Number = Contact_Number;

        this.Employee_Biometrics = Employee_Biometrics;
    }
    //For Manage Employee
    public Employee(int Employee_ID, int Department, String Employment_Status, String First_Name, String Last_Name) {
        this.Employee_ID = Employee_ID;
        this.Department = Department;
        this.Employment_Status = Employment_Status;

        this.First_Name = First_Name;
        this.Last_Name = Last_Name;
    }
    //For Getting Attendance Info
    public Employee(int Employee_ID, int Department, String First_Name, String Last_Name) {
        this.Employee_ID = Employee_ID;
        this.Department = Department;

        this.First_Name = First_Name;
        this.Last_Name = Last_Name;
    }

    public Employee(int Employee_ID, String Last_Name, String First_Name, String Employment_Status, int Department, boolean Active) {
        this.Employee_ID = Employee_ID;
        this.Last_Name = Last_Name;
        this.First_Name = First_Name;
        this.Employment_Status = Employment_Status;
        this.Department = Department;
        this.Active = Active;
    }

    // Getters Here ///////////////////////////////////////////////////////

    public int getEmployee_ID() {
        return Employee_ID;
    }

    public String getPosition() {
        return Position;
    }

    public String getNationality() {
        return Nationality;
    }

    public String getMarital_Status() {
        return Marital_Status;
    }

    public int getDepartment() {
        return Department;
    }

    public String getEmployment_Status() {
        return Employment_Status;
    }

    public String getFirst_Name() {
        return First_Name;
    }

    public String getLast_Name() {
        return Last_Name;
    }

    public String getMiddle_Name() {
        return Middle_Name;
    }

    public String getExtension() {
        return Extension;
    }

    public String getAddress() {
        return Address;
    }

    public boolean isGender() {
        return Gender;
    }

    public String getNumber() {
        return Number;
    }

    public Date getBirthdate() {
        return Birthdate;
    }

    public boolean isActive() {
        return Active;
    }

    public String getEmployee_Biometrics() {
        return Employee_Biometrics;
    }

    public String getContact_Name() {
        return Contact_Name;
    }

    public String getContact_Relationship() {
        return Contact_Relationship;
    }

    public String getContact_Address() {
        return Contact_Address;
    }

    public String getContact_Number() {
        return Contact_Number;
    }
}
