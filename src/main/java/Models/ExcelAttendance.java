package Models;

public class ExcelAttendance {
    private String employee_id;
    private String date;
    private String time_in;
    private String time_out;


    public ExcelAttendance() {

    }

    public ExcelAttendance(String employee_id, String date, String time_in, String time_out) {
        this.employee_id = employee_id;
        this.date = date;
        this.time_in = time_in;
        this.time_out = time_out;
    }

    public String getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(String employee_id) {
        this.employee_id = employee_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime_in() {
        return time_in;
    }

    public void setTime_in(String time_in) {
        this.time_in = time_in;
    }

    public String getTime_out() {
        return time_out;
    }

    public void setTime_out(String time_out) {
        this.time_out = time_out;
    }
}
