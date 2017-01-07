package underskin.skeleton;

import underskin.blood.RecordContainer;

/**
 * Created by Eligi.Ran on 2017/1/5.
 */
public interface RecordOpt {

    <T extends RecordContainer>void loadContainer(T record);

    void appendLabel(String label);

    void removeLabel(String label);

    String resetBelong(String belonging);

    void updateTitle(String title);

    void updateContent(String content);
}
