/*
 * Copyright (c) 2017. For intelligent group.
 */

package examples.combobox;


import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxEditor;
import java.awt.*;

/**
 * Created by Eligi.Ran on 2017/6/7.
 */
public class TemplateComboBoxEditor extends BasicComboBoxEditor {
    private String selectedValue;

    public TemplateComboBoxEditor() {
        CellConstraints cc = new CellConstraints();
        panel = new JPanel(new FormLayout("left:pref:grow,right:20px,right:10px,right:20px","fill:22px"));
        labelItem = new JLabel();
        btnDelete = new JButton(new ImageIcon(getClass().getResource("/images/icon_list_more.png")));
        btnEdit = new JButton(new ImageIcon(getClass().getResource("/images/icon_list_more.png")));

        labelItem.setOpaque(false);
        labelItem.setHorizontalAlignment(JLabel.LEFT);
        labelItem.setForeground(Color.WHITE);

        panel.add(labelItem, cc.xy(1,1));
        panel.add(btnEdit, cc.xy(2,1));
        panel.add(btnDelete, cc.xy(4,1));
        panel.setBackground(Color.GRAY);
    }

    public Component getEditorComponent() {
        return this.panel;
    }

    public Object getItem() {
        return this.selectedValue;
    }

    public void setItem(Object item) {
        if (item == null) {
            return;
        }
        selectedValue = item.toString();
        labelItem.setText(selectedValue);
    }

    private JPanel panel;
    private JLabel labelItem;
    private JButton btnDelete,btnEdit;
}
