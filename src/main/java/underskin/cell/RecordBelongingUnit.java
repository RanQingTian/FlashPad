package underskin.cell;

import java.util.List;

/**
 * Created by Eligi.Ran on 2017/1/5.
 */
public class RecordBelongingUnit {
    private long id;
    private String name;
    private List<String> owners;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getOwners() {
        return owners;
    }

    public void setOwners(List<String> owners) {
        this.owners = owners;
    }
}
