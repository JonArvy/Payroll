package Models;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SummarySchema {
    private int summary_id;
    private String summary_name;
    private Date summary_date_from;
    private Date summary_date_to;

    public SummarySchema(int summary_id, Date summary_date_from, Date summary_date_to) {
        this.summary_id = summary_id;
        this.summary_date_from = summary_date_from;
        this.summary_date_to = summary_date_to;

        setSummary_name();
    }

    private void setSummary_name() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("MMMM dd yyyy");
        String s = formatter.format(summary_date_from) + " - " + formatter.format(summary_date_to);
        this.summary_name = s;
    }

    public int getSummary_id() {
        return summary_id;
    }

    public void setSummary_id(int summary_id) {
        this.summary_id = summary_id;
    }

    public String getSummary_name() {
        setSummary_name();
        return summary_name;
    }

    public Date getSummary_date_from() {
        return summary_date_from;
    }

    public void setSummary_date_from(Date summary_date_from) {
        this.summary_date_from = summary_date_from;
    }

    public Date getSummary_date_to() {
        return summary_date_to;
    }

    public void setSummary_date_to(Date summary_date_to) {
        this.summary_date_to = summary_date_to;
    }
}
