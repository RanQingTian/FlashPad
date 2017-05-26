package data;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Eligi.Ran on 2017/1/20.
 */
public class Test {

    private JFrame frame;
    private final JComboBox comboBox = new JComboBox();
    private static List<String>listForComboBox= new ArrayList<String>();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        SwingWorker swingWorker = new SwingWorker() {
            @Override
            protected Object doInBackground() throws Exception {
                return null;
            }
        };
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Test window = new Test();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        listForComboBox.add("Lion");
        listForComboBox.add("LionKing");
        listForComboBox.add("Mufasa");
        listForComboBox.add("Nala");
        listForComboBox.add("KingNala");
        listForComboBox.add("Animals");
        listForComboBox.add("Anims");
        listForComboBox.add("Fish");
        listForComboBox.add("Jelly Fish");
        listForComboBox.add("I am the boss");


    }

    public Test() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 412, 165);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        comboBox.setEditable(true);

        comboBox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent arg0) {

            }
        });

        for(String detail : listForComboBox) {
            comboBox.addItem(detail);
        }
        final JTextField textfield = (JTextField) comboBox.getEditor().getEditorComponent();
        textfield.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent ke) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        if(!textfield.getText().isEmpty()){
                            comboBoxFilter(textfield.getText());
                        }
                    }
                });

            }
        });
        comboBox.setBounds(10, 39, 364, 29);
        frame.getContentPane().add(comboBox);
    }
    public void comboBoxFilter(String enteredText) {
        System.out.println(comboBox.getItemCount());

        if (!comboBox.isPopupVisible()) {
            comboBox.showPopup();
        }

        List<String> filterArray= new ArrayList<String>();
        for (int i = 0; i < listForComboBox.size(); i++) {
            if (listForComboBox.get(i).toLowerCase().contains(enteredText.toLowerCase())) {
                filterArray.add(listForComboBox.get(i));
            }
        }
        if (filterArray.size() > 0) {
            DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) comboBox.getModel();
            model.removeAllElements();
            model.addElement("");
            for (String s: filterArray)
                model.addElement(s);

            JTextField textfield = (JTextField) comboBox.getEditor().getEditorComponent();
            textfield.setText(enteredText);
        }
    }

}

