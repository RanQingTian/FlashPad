package underskin.cell;

import java.util.Date;

/**
 * Created by Eligi.Ran on 2017/1/5.
 */
public class RecordUnit {
    private long id;
    private String title;
    private String belonging;
    private String label;
    private Date date;
    private String content;

    public RecordUnit() {
    }

    public RecordUnit(String label, String title, String belonging, Date date, String content) {
        this.label = label;
        this.title = title;
        this.belonging = belonging;
        this.date = date;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBelonging() {
        return belonging;
    }

    public void setBelonging(String belonging) {
        this.belonging = belonging;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
