package examples;

import javax.swing.*;
import javax.swing.plaf.ComboBoxUI;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.ComboPopup;
import java.util.ArrayList;

public class CustomArrowComboBoxUI extends BasicComboBoxUI {

    private CustomArrowComboBoxUI() {}

    public static ComboBoxUI createUI() {
        return new CustomArrowComboBoxUI();
    }

    @Override
    protected JButton createArrowButton() {
        JButton btn = new JButton();
        btn.setIcon(new ImageIcon(getClass().getResource("/images/icon_list_more.png")));
        return btn;
    }

    @Override
    protected ComboPopup createPopup() {
        return super.createPopup();//BasicComboPopup
    }
}