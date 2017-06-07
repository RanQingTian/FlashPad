package examples.combobox;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Eligi.Ran on 2017/6/7.
 */
public class TemplateComboBoxRenderer extends JPanel implements ListCellRenderer {
    private JLabel labelItem = new JLabel();
    private JButton btnDelete,btnEdit;
    private CellConstraints cc = new CellConstraints();

    public TemplateComboBoxRenderer() {
        setLayout(new FormLayout("left:pref:grow,right:20px,right:10px,right:20px","fill:22px"));

        labelItem.setOpaque(true);
        labelItem.setHorizontalAlignment(JLabel.LEFT);

        add(labelItem, cc.xy(1,1));

        setBackground(Color.LIGHT_GRAY);
        System.out.println(1);
    }

    @Override
    public Component getListCellRendererComponent(JList list, Object value,
                                                  int index, boolean isSelected, boolean cellHasFocus) {
        // set country name
        labelItem.setText(value.toString());

        if (isSelected) {
            labelItem.setBackground(Color.BLUE);
            labelItem.setForeground(Color.YELLOW);
        } else {
            labelItem.setForeground(Color.BLACK);
            labelItem.setBackground(Color.LIGHT_GRAY);
        }
        JPanel panel = new JPanel(new FormLayout("left:pref:grow,right:20px,right:10px,right:20px","fill:22px"));
        btnDelete = new JButton(new ImageIcon(getClass().getResource("/images/icon_list_more.png")));
        btnEdit = new JButton(new ImageIcon(getClass().getResource("/images/icon_list_more.png")));
        add(labelItem, cc.xy(1,1));
        add(btnEdit, cc.xy(2,1));
        add(btnDelete, cc.xy(4,1));add(btnDelete, cc.xy(8,1));

        return this;
    }

}
