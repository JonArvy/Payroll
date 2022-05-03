package Models;

public class BooleanValue {
    private String name;
    private boolean bool;

    public BooleanValue(String name, boolean bool) {
        this.name = name;
        this.bool = bool;
    }

    public String getName() {
        return name;
    }

    public boolean isBool() {
        return bool;
    }
}
