package Models;

public class Admin {
    private int Admin_ID;
    private int Employee_ID;
    private String Admin_Password;
    private int Admin_Grantor;
    private int Admin_Disabler;
    private String Employee_FullName;
    private String Employee_Active;


    public Admin() {
        Employee_Active = "Active";
    }
    public Admin(int admin_ID) {
        Admin_ID = admin_ID;
    }
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
        if (Admin_Disabler == 0) {
            Employee_Active = "Active";
        } else {
            Employee_Active = "Inactive";
        }
    }

    public String getEmployee_FullName() {
        return Employee_FullName;
    }

    public void setEmployee_FullName(String employee_FullName) {
        Employee_FullName = employee_FullName;
    }

    public String getEmployee_Active() {
        return Employee_Active;
    }

    public void setEmployee_Active(String employee_Active) {
        Employee_Active = employee_Active;
    }
}
