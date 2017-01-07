package underskin.muscle;

import equipment.StringUtil;
import underskin.blood.RecordContainer;
import underskin.skeleton.RecordOpt;

/**
 * Created by Eligi.Ran on 2017/1/5.
 */
public class Record implements RecordOpt {

    private RecordContainer rContainer;

    @Override
    public <T extends RecordContainer> void loadContainer(T rContainer) {
        this.rContainer = null;
        if(rContainer == null){
            return;
        }
        this.rContainer = rContainer;
        return;
    }

    @Override
    public void appendLabel(String label) {
        if(!StringUtil.emptyCheck(label)){
            return;
        }
        rContainer.appendLabel(label);
        return;

    }

    @Override
    public void removeLabel(String label) {
        if(!StringUtil.emptyCheck(label)){
            return;
        }
        rContainer.removeLabel(label);
        return;

    }

    @Override
    public String resetBelong(String belonging) {
        if(!StringUtil.emptyCheck(belonging)){
            return null;
        }
        rContainer.setBelonging(belonging);
        return belonging;
    }

    @Override
    public void updateTitle(String title) {
        if(!StringUtil.emptyCheck(title)){
            return;
        }
        rContainer.setTitle(title);
        return;
    }

    @Override
    public void updateContent(String content) {
        if(!StringUtil.emptyCheck(content)){
            return;
        }
        rContainer.setContent(content);
        return;
    }
}
