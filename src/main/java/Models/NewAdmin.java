package Models;

public class NewAdmin {
    private int ID;
    private String username;
    private String name;
    private String password;
    private String grantor;
    private String disabler;
    private boolean isUsingTheSystem;

    private boolean isSuperAdmin;

    private String activeMessage;
    private String adminTypeMessage;




    public NewAdmin() {

    }


    public NewAdmin(String username) {
        this.username = username;
    }

    public NewAdmin(int ID, String username, String name, String password, String grantor, String disabler, boolean isUsingTheSystem, boolean isSuperAdmin) {
        this.ID = ID;
        this.username = username;
        this.name = name;
        this.password = password;
        this.grantor = grantor;
        this.disabler = disabler;
        this.isUsingTheSystem = isUsingTheSystem;
        this.isSuperAdmin = isSuperAdmin;
    }


    public NewAdmin(String username, String name, String password, String grantor, String disabler, boolean isUsingTheSystem, boolean isSuperAdmin) {
        this.username = username;
        this.name = name;
        this.password = password;
        this.grantor = grantor;
        this.disabler = disabler;
        this.isUsingTheSystem = isUsingTheSystem;
        this.isSuperAdmin = isSuperAdmin;
    }

    public void setItems() {
        activeMessage = "Inactive";
        if (disabler == null) {
            activeMessage = "Active";
        }

        adminTypeMessage = "Admin";
        if (isSuperAdmin) {
            adminTypeMessage = "Super Admin";
        }
    }


    public boolean isSuperAdmin() {
        return isSuperAdmin;
    }

    public void setSuperAdmin(boolean superAdmin) {
        isSuperAdmin = superAdmin;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGrantor() {
        return grantor;
    }

    public void setGrantor(String grantor) {
        this.grantor = grantor;
    }

    public String getDisabler() {
        return disabler;
    }

    public void setDisabler(String disabler) {
        this.disabler = disabler;
    }

    public boolean isUsingTheSystem() {
        return isUsingTheSystem;
    }

    public void setUsingTheSystem(boolean usingTheSystem) {
        isUsingTheSystem = usingTheSystem;
    }

    public String getActiveMessage() {
        return activeMessage;
    }

    public void setActiveMessage(String activeMessage) {
        this.activeMessage = activeMessage;
    }

    public String getAdminTypeMessage() {
        return adminTypeMessage;
    }

    public void setAdminTypeMessage(String adminTypeMessage) {
        this.adminTypeMessage = adminTypeMessage;
    }
}
