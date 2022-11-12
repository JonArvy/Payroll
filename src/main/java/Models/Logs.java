package Models;

public class Logs {
    private int log_id;
    private String log_message;
    private String log_date;
    private String log_time;

    public Logs() {

    }

    public Logs(int log_id, String log_message, String log_date, String log_time) {
        this.log_id = log_id;
        this.log_message = log_message;
        this.log_date = log_date;
        this.log_time = log_time;
    }

    public int getLog_id() {
        return log_id;
    }

    public void setLog_id(int log_id) {
        this.log_id = log_id;
    }

    public String getLog_message() {
        return log_message;
    }

    public void setLog_message(String log_message) {
        this.log_message = log_message;
    }

    public String getLog_date() {
        return log_date;
    }

    public void setLog_date(String log_date) {
        this.log_date = log_date;
    }

    public String getLog_time() {
        return log_time;
    }

    public void setLog_time(String log_time) {
        this.log_time = log_time;
    }
}
