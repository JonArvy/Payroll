package Models;

public class NewAdmin {
    private int ID;
    private String username;
    private String name;
    private String password;
    private String grantor;
    private String disabler;
    private boolean isUsingTheSystem;

    public NewAdmin() {

    }

    public NewAdmin(int ID, String username, String name, String password, String grantor, String disabler, boolean isUsingTheSystem) {
        this.ID = ID;
        this.username = username;
        this.name = name;
        this.password = password;
        this.grantor = grantor;
        this.disabler = disabler;
        this.isUsingTheSystem = isUsingTheSystem;
    }


    public NewAdmin(String username, String name, String password, String grantor, String disabler, boolean isUsingTheSystem) {
        this.username = username;
        this.name = name;
        this.password = password;
        this.grantor = grantor;
        this.disabler = disabler;
        this.isUsingTheSystem = isUsingTheSystem;
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
}
