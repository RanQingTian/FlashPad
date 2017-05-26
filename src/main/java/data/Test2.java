package data;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.EventListenerList;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.*;

/**
 * Created by Eligi.Ran on 2017/3/15.
 */
public class Test2 extends JFrame{
    JComboBox comboBox = null;
    String[] items = new String[] {"Red", "Green", "Blue"};
    java.util.List<String> listForComboBox= Arrays.asList(items);
    public Test2(){
        Box panel = Box.createVerticalBox();
        final DefaultComboBoxModel model = new DefaultComboBoxModel(items);
        comboBox = new JComboBox(model){
            @Override
            public void setPopupVisible(boolean v) {
//                if (!v) {
//                    System.out.println("set not visible");
//                   if (!listForComboBox.contains(((JTextField)this.getEditor().getEditorComponent()).getText())){
//                       this.setSelectedItem(null);
//                   }
//                }
                super.setPopupVisible(v);
            }
        };
        comboBox.setEditor(new Editor());
        comboBox.setEditable(true);
        comboBox.setMaximumSize(new Dimension(100,100));
        comboBox.setPreferredSize(new Dimension(100,100));
        comboBox.setMinimumSize(new Dimension(100,100));
        comboBox.addActionListener(new ActionListener() {
            private int selectedIndex = -1;

            @Override
            public void actionPerformed(ActionEvent e) {
                int index = comboBox.getSelectedIndex();
                if(index >= 0) {
                    selectedIndex = index;
                } else if("comboBoxEdited".equals(e.getActionCommand())) {
                    Object newValue = model.getSelectedItem();
                    model.addElement(newValue);
                    comboBox.setSelectedItem(newValue);
                    selectedIndex = model.getIndexOf(newValue);
                }
            }
        });
        final JTextField textField = (JTextField) comboBox.getEditor().getEditorComponent();
        textField.addCaretListener(new CaretListener() {

            @Override
            public void caretUpdate(CaretEvent e) {
                System.out.println("text field have changed");

            }
        });
        textField.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent ke) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
//                        if(!textfield.getText().isEmpty()){
                            comboBoxFilter(textField.getText());
//                        }
                    }
                });

            }
        });
        panel.add(comboBox);
        panel.add(new JButton("Hello Label"));
        panel.setPreferredSize(new Dimension(400,400));
        this.setPreferredSize(new Dimension(400,400));
        this.add(panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
    }

    public void comboBoxFilter(String enteredText) {
        System.out.println(comboBox.getItemCount());

        if (!comboBox.isPopupVisible()) {
            comboBox.showPopup();
        }

        java.util.List<String> filterArray= new ArrayList<String>();
        for (int i = 0; i < listForComboBox.size(); i++) {
            if (listForComboBox.get(i).toLowerCase().contains(enteredText.toLowerCase())) {
                filterArray.add(listForComboBox.get(i));
            }
        }
        System.out.println(filterArray.toString());
        DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) comboBox.getModel();
        model.removeAllElements();
        model.addElement("");
        if (filterArray.size() > 0) {
            for (String s: filterArray)
                model.addElement(s);
        }
        JTextField textfield = (JTextField) comboBox.getEditor().getEditorComponent();
        textfield.setText(enteredText);
    }

    public static void main(String[] args){
        new Test2().setVisible(true);
    }
}
class Editor implements ComboBoxEditor {

    private final JTextField editor;
    private EventListenerList listenerList = new EventListenerList();

    Editor() {
        editor = new JTextField();
    }

    @Override
    public void addActionListener(ActionListener l) {
        listenerList.add(ActionListener.class, l);
    }

    @Override
    public Component getEditorComponent() {
        return editor;
    }

    @Override
    public Object getItem() {
        return editor.getText();
    }

    @Override
    public void removeActionListener(ActionListener l) {
        listenerList.remove(ActionListener.class, l);
    }

    @Override
    public void selectAll() {
    }

    @Override
    public void setItem(Object newValue) {
        if (newValue instanceof String) {
            String sz = (String) newValue;
            editor.setText(sz);
        } else {
            editor.setText("");
        }
    }
}