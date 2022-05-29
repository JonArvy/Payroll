package Models;

import java.sql.Date;

public class Employee {
    // Self ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    private int Employee_ID;
    private String Nationality;
    private String Marital_Status;
    private int Department;

    private String Department_Name; //Value

    private String Position;
    private String Employment_Status;

    private String First_Name;
    private String Last_Name;
    private String Middle_Name;
    private String Extension;

    private String Full_Name;

    private String Address;

    private boolean Gender;

    private String Number;

    private Date Birthdate;
    private Boolean Active;
    private String Active_Message;

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

    public Employee(int Employee_ID) {
        this.Employee_ID = Employee_ID;
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
        this.Active_Message = (Active ? "Active" : "Inactive");

        this.Contact_Name = Contact_Name;
        this.Contact_Relationship = Contact_Relationship;
        this.Contact_Address = Contact_Address;

        this.Contact_Number = Contact_Number;

        this.Employee_Biometrics = Employee_Biometrics;
    }

    public Employee(int Employee_ID, String Nationality, String Marital_Status, int Department, String Position, String Employment_Status, String First_Name, String Last_Name, String Middle_Name, String Extension, String Address, boolean Gender, String Number, Date Birthdate, Boolean Active, String Contact_Name, String Contact_Relationship, String Contact_Address, String Contact_Number) {
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
        this.Active_Message = (Active ? "Active" : "Inactive");

        this.Contact_Name = Contact_Name;
        this.Contact_Relationship = Contact_Relationship;
        this.Contact_Address = Contact_Address;

        this.Contact_Number = Contact_Number;
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

    public Employee(int Employee_ID, String Last_Name, String First_Name, String Employment_Status, String Department_Name, boolean Active) {
        this.Employee_ID = Employee_ID;
        this.Last_Name = Last_Name;
        this.First_Name = First_Name;
        this.Employment_Status = Employment_Status;
        this.Department_Name = Department_Name;
        this.Active = Active;
        this.Active_Message = (Active ? "Active" : "Inactive");
    }

    public Employee(int Employee_ID, String Full_Name, String Department_Name, String Position) {
        this.Employee_ID = Employee_ID;
        this.Full_Name = Full_Name;
        this.Department_Name = Department_Name;
        this.Position = Position;
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

    public String getDepartment_Name() {
        return Department_Name;
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

    public String getActive_Message() {
        return Active_Message;
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

    public String getFull_Name() {
        return Full_Name;
    }

    public void setEmployee_ID(int employee_ID) {
        Employee_ID = employee_ID;
    }

    public void setNationality(String nationality) {
        Nationality = nationality;
    }

    public void setMarital_Status(String marital_Status) {
        Marital_Status = marital_Status;
    }

    public void setDepartment(int department) {
        Department = department;
    }

    public void setDepartment_Name(String department_Name) {
        Department_Name = department_Name;
    }

    public void setPosition(String position) {
        Position = position;
    }

    public void setEmployment_Status(String employment_Status) {
        Employment_Status = employment_Status;
    }

    public void setFirst_Name(String first_Name) {
        First_Name = first_Name;
    }

    public void setLast_Name(String last_Name) {
        Last_Name = last_Name;
    }

    public void setMiddle_Name(String middle_Name) {
        Middle_Name = middle_Name;
    }

    public void setExtension(String extension) {
        Extension = extension;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public void setGender(boolean gender) {
        Gender = gender;
    }

    public void setNumber(String number) {
        Number = number;
    }

    public void setBirthdate(Date birthdate) {
        Birthdate = birthdate;
    }

    public void setActive(Boolean active) {
        Active = active;
    }

    public void setEmployee_Biometrics(String employee_Biometrics) {
        Employee_Biometrics = employee_Biometrics;
    }

    public void setContact_Name(String contact_Name) {
        Contact_Name = contact_Name;
    }

    public void setContact_Relationship(String contact_Relationship) {
        Contact_Relationship = contact_Relationship;
    }

    public void setContact_Address(String contact_Address) {
        Contact_Address = contact_Address;
    }

    public void setContact_Number(String contact_Number) {
        Contact_Number = contact_Number;
    }
}
