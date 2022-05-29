package Models;

public class Admin {
    private int Admin_ID;
    private int Employee_ID;
    private String Admin_Password;
    private int Admin_Grantor;
    private int Admin_Disabler;


    public Admin(int admin_ID, int employee_ID) {
        Admin_ID = admin_ID;
        Employee_ID = employee_ID;
    }

    public Admin(int Admin_ID, int Employee_ID, String Admin_Password, int Admin_Grantor, int Admin_Disabler) {
        this.Admin_ID = Admin_ID;
        this.Employee_ID = Employee_ID;
        this.Admin_Password = Admin_Password;
        this.Admin_Grantor = Admin_Grantor;
        this.Admin_Disabler = Admin_Disabler;
    }

    public Admin(int Employee_ID, String Admin_Password, int Admin_Grantor, int Admin_Disabler) {
        this.Admin_ID = Admin_ID;
        this.Employee_ID = Employee_ID;
        this.Admin_Password = Admin_Password;
        this.Admin_Grantor = Admin_Grantor;
        this.Admin_Disabler = Admin_Disabler;
    }

    public Admin(int employee_ID, String Admin_Password) {
        Employee_ID = employee_ID;
        this.Admin_Password = Admin_Password;
    }

    public int getAdmin_ID() {
        return Admin_ID;
    }

    public String getAdmin_Password() {
        return Admin_Password;
    }

    public int getAdmin_Grantor() {
        return Admin_Grantor;
    }

    public int getAdmin_Disabler() {
        return Admin_Disabler;
    }

    public int getEmployee_ID() {
        return Employee_ID;
    }

    public void setAdmin_ID(int admin_ID) {
        Admin_ID = admin_ID;
    }

    public void setEmployee_ID(int employee_ID) {
        Employee_ID = employee_ID;
    }

    public void setAdmin_Password(String admin_Password) {
        Admin_Password = admin_Password;
    }

    public void setAdmin_Grantor(int admin_Grantor) {
        Admin_Grantor = admin_Grantor;
    }

    public void setAdmin_Disabler(int admin_Disabler) {
        Admin_Disabler = admin_Disabler;
    }
}
