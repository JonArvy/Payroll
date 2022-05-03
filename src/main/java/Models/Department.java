package Models;

public class Department {
    private int Department_ID;
    private String Department_Name;

    public Department(int Department_ID, String Department_Name) {
        this.Department_ID = Department_ID;
        this.Department_Name = Department_Name;
    }

    public int getDepartment_ID() {
        return Department_ID;
    }

    public String getDepartment_Name() {
        return Department_Name;
    }
}
