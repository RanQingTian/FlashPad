package skin.appearance;

import skin.part.CataloguePanel;
import skin.part.RecordPanel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Eligi.Ran on 2017/1/5.
 */
public class PadLayout extends JFrame {

    public PadLayout() {

        //record manager box
        JComponent rdMngPanel = CataloguePanel.newInstance();

        //record box
        JTabbedPane rdPanel = RecordPanel.newRecordView();

        JSplitPane jSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        jSplitPane.add(rdMngPanel);
        jSplitPane.add(rdPanel);

        this.add(jSplitPane);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("FlashPad");
        this.setPreferredSize(new Dimension(1000, 1000));
        this.pack();
        this.setVisible(true);
//        System.out.println(rdPanel.getTabComponentAt(0).getName());

    }

    public static void main(String[] args) {
        JFrame jFrame = new PadLayout();
    }

}
