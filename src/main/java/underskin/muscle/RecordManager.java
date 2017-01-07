package underskin.muscle;

import equipment.StringUtil;
import underskin.blood.RecordBelongContainer;
import underskin.skeleton.RecordManagerOpt;

/**
 * Created by Eligi.Ran on 2017/1/5.
 */
public class RecordManager implements RecordManagerOpt {
    @Override
    public String createBelonging(String name) {
        if(!StringUtil.emptyCheck(name)){
            return null;
        }
        RecordBelongContainer rbContainer = new RecordBelongContainer();
        rbContainer.setName(name);
        return name;
    }

    @Override
    public String deleteBelonging(String name) {
        if(!StringUtil.emptyCheck(name)){
            return null;
        }
        return name;
    }
}
