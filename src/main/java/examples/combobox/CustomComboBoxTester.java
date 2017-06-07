package examples.combobox;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * Created by Eligi.Ran on 2017/6/7.
 */
public class CustomComboBoxTester extends JFrame {

    public CustomComboBoxTester() {
        super("Demo program for custom combobox");
        setLayout(new FlowLayout());
        String[] strings = new String[]{"hello","how","are","you"};
        JComboBox customCombobox = new JComboBox(strings);
//        customCombobox.setEditor(new TemplateComboBoxEditor());
        customCombobox.setRenderer(new TemplateComboBoxRenderer());
        customCombobox.setPreferredSize(new Dimension(120, 30));
        customCombobox.setEditable(true);
        JButton button = new JButton("qwe");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                customCombobox.getComponentPopupMenu().setVisible(true);
            }
        });
        add(customCombobox);
        add(button);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 100);
        setLocationRelativeTo(null);    // center on screen
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CustomComboBoxTester().setVisible(true);
            }
        });
    }

}
