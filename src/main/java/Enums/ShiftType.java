package Enums;

public enum ShiftType {
    DEPARTMENT(1),
    EMPLOYEE(2);

    private final int shiftType;

    ShiftType(int type) {
        this.shiftType = type;
    }

    public int getValue() {
        return shiftType;
    }
}
