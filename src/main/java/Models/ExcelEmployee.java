package Models;

public class ExcelEmployee {
    private String first_name;
    private String last_name;
    private String middle_name;
    private String extension;
    private String address;
    private String gender;
    private String contact_number;
    private String birthdate;
    private String status;
    private String nationality;
    private String marital_status;
    private String department;
    private String position;
    private String employment_status;
    private String emergency_contact_name;
    private String emergency_contact_number;
    private String emergency_contact_address;
    private String emergency_contact_relationship;

    public ExcelEmployee() {

    }

    public ExcelEmployee(String first_name, String last_name, String contact_number, String status, String department, String position, String employment_status) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.contact_number = contact_number;
        this.status = status;
        this.department = department;
        this.position = position;
        this.employment_status = employment_status;
    }

    public ExcelEmployee(String first_name, String last_name, String middle_name, String extension, String address, String gender, String contact_number, String birthdate, String status, String nationality, String marital_status, String department, String position, String employment_status, String emergency_contact_name, String emergency_contact_relationship, String emergency_contact_address, String emergency_contact_number) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.middle_name = middle_name;
        this.extension = extension;
        this.address = address;
        this.gender = gender;
        this.contact_number = contact_number;
        this.birthdate = birthdate;
        this.status = status;
        this.nationality = nationality;
        this.marital_status = marital_status;
        this.department = department;
        this.position = position;
        this.employment_status = employment_status;
        this.emergency_contact_name = emergency_contact_name;
        this.emergency_contact_number = emergency_contact_number;
        this.emergency_contact_address = emergency_contact_address;
        this.emergency_contact_relationship = emergency_contact_relationship;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getMiddle_name() {
        return middle_name;
    }

    public void setMiddle_name(String middle_name) {
        this.middle_name = middle_name;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getContact_number() {
        return contact_number;
    }

    public void setContact_number(String contact_number) {
        this.contact_number = contact_number;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getMarital_status() {
        return marital_status;
    }

    public void setMarital_status(String marital_status) {
        this.marital_status = marital_status;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getEmployment_status() {
        return employment_status;
    }

    public void setEmployment_status(String employment_status) {
        this.employment_status = employment_status;
    }

    public String getEmergency_contact_name() {
        return emergency_contact_name;
    }

    public void setEmergency_contact_name(String emergency_contact_name) {
        this.emergency_contact_name = emergency_contact_name;
    }

    public String getEmergency_contact_number() {
        return emergency_contact_number;
    }

    public void setEmergency_contact_number(String emergency_contact_number) {
        this.emergency_contact_number = emergency_contact_number;
    }

    public String getEmergency_contact_address() {
        return emergency_contact_address;
    }

    public void setEmergency_contact_address(String emergency_contact_address) {
        this.emergency_contact_address = emergency_contact_address;
    }

    public String getEmergency_contact_relationship() {
        return emergency_contact_relationship;
    }

    public void setEmergency_contact_relationship(String emergency_contact_relationship) {
        this.emergency_contact_relationship = emergency_contact_relationship;
    }
}
