package examples.deleteComboBox;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by eligi on 2017/6/7.
 */
public class TemplateComboBox extends JPanel {

    public TemplateComboBox(){
        CellConstraints cc = new CellConstraints();
        setLayout(new FormLayout("fill:80px,fill:20px","fill:22px"));
        txtEditField = new JTextField();
        txtEditField.setEditable(false);
        btnDropDown = new JButton("D");
        pmuDropDown = new JPopupMenu();
        pmuDropDown.setPreferredSize(new Dimension(100,200));
        JMenuItem item = new JMenuItem();
        item.setLayout(new FormLayout("left:40px:grow,fill:20px,fill:20px","fill:22px"));
        item.add(new JLabel("real"),cc.xy(1,1));
        item.add(new JButton("1"),cc.xy(2,1));
        item.add(new JButton("2"),cc.xy(3,1));
        pmuDropDown.add(new JMenu("A1"));
        pmuDropDown.add(new JMenu("B2"));
        pmuDropDown.add(item);


        loadListener();
        this.add(txtEditField,cc.xy(1,1));
        this.add(btnDropDown,cc.xy(2,1));
    }

    private void loadListener(){
        btnDropDown.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JButton button = (JButton)e.getSource();
//                pmuDropDown.setLocale(button.getLocale());
//                pmuDropDown.show(button,0,0);
                button.requestFocusInWindow();
//                pmuDropDown.setVisible(true);
                pmuDropDown.show(txtEditField,0,txtEditField.getSize().height);
                System.out.println("Clicked");}

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }

    private JTextField txtEditField;
    private JButton btnDropDown;
    private JPopupMenu pmuDropDown;

}
