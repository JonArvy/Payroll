package Enums;

public enum ShiftType {
    DEPARTMENT(1),
    EMPLOYEE(2);

    private final int shiftType;
    // hawo
    ShiftType(int type) {
        this.shiftType = type;
    }

    public int getValue() {
        return shiftType;
    }
}

