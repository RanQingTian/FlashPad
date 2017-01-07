package underskin.blood;

import java.util.Date;
import java.util.Set;

/**
 * Created by Eligi.Ran on 2017/1/5.
 */
public class RecordContainer {
    private String title;
    private String belonging;
    private Set<String> label;
    private Date date;
    private String content;

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

    public Set<String> getLabel() {
        return label;
    }

    public void setLabel(Set<String> label) {
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

    public void appendLabel(String label){
        this.label.add(label);
    }

    public void removeLabel(String label){
        this.label.remove(label);
    }
}
