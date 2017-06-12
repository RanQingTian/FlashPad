package examples;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import javax.swing.*;
import java.util.*;

/**
 * Created by Eligi.Ran on 2017/3/20.
 */

/**
 * JtextPane use html type
 */
public class Test {
    public boolean turn = true;
    volatile boolean re = false;
    boolean syn = false;
    public final int i;
    {
        i = 1;
    }

    public synchronized static void show(int i, String... words) {
        System.out.println(words.length);
    }

    public boolean revert() {
        return  turn = !turn;
    }

    public static void main(String[] args) {
        JDialog dialog = new JDialog();
        DefaultListModel model = new DefaultListModel();
        JList<String> list = new JList<>(model);
        JScrollPane scrollPane = new JScrollPane(list);
        dialog.add(scrollPane);

        model.addElement(createSpcItemPanel("1333"));
        model.addElement(createSpcItemPanel("asfd"));
        model.addElement(createSpcItemPanel("sdfsdf"));
        dialog.setVisible(true);

    }

    public static JPanel createSpcItemPanel(String name) {
        CellConstraints cc = new CellConstraints();
        JPanel panel = new JPanel(new FormLayout("fill:10px,fill:20px,fill:10px,left:pref:grow", "fill:22px"));
        JRadioButton radioButton = new JRadioButton();
        radioButton.setName(name);
        JLabel label = new JLabel(name);

        panel.add(radioButton, cc.xy(2,1));
        panel.add(label, cc.xy(4,1));

        return panel;
    }
}
