package skin.appearance;

import skin.part.CatalogueLayout;
import skin.part.RecordPanel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Eligi.Ran on 2017/1/5.
 */
public class PadLayout extends JFrame {
    private CardLayout cardLayout = new CardLayout(20, 10);
    private JPanel cardPanel = new JPanel(cardLayout);
    private JComboBox jCombo;

    public PadLayout() {

        //record manager box
        JComponent rdMngBox = CatalogueLayout.newInstance();

        //record box
        JComponent rdPanel = RecordPanel.newRecordView();

        JSplitPane jSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        jSplitPane.add(rdMngBox);
        jSplitPane.add(rdPanel);

        this.add(jSplitPane);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("FlashPad");
        this.setPreferredSize(new Dimension(1000, 1000));
        this.pack();
        this.setVisible(true);
    }

    public static void main(String[] args) {
        JFrame jFrame = new PadLayout();
    }

}
