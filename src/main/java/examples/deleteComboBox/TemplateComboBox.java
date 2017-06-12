package examples.deleteComboBox;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by eligi on 2017/6/7.
 */
public class TemplateComboBox extends JPanel {
    private CellConstraints cc;
    private java.util.List<String> itemNames;
    private MouseAdapter editListener, deleteListener,selectListener;


    public TemplateComboBox() {
        cc = new CellConstraints();
        itemNames = new ArrayList<>();

        setLayout(new FormLayout("fill:80px,fill:20px", "fill:22px"));
        txtEditField = new JTextField();
        txtEditField.setEditable(false);
        btnDropDown = new JButton("D");
        pmuDropDown = new JPopupMenu();
        pmuDropDown.setPreferredSize(new Dimension(100, 100));
        Box panel = Box.createVerticalBox();
        JScrollPane test1 = new JScrollPane();
        test1.setPreferredSize(new Dimension(100, 100));
        test1.add(new JLabel("123"));
        scrollMenu = new JScrollPane(panel);
//        pmuDropDown.add(test1);
        panel.add(new JLabel("Not"));
        panel.add(createItem("test1"));
        panel.add(createItem("test2"));
        panel.add(createItem("test3"));
        panel.add(createItem("test4"));
        panel.add(createItem("test5"));
        panel.add(createItem("test6"));
        panel.add(createItem("test7"));
        JMenuItem menuItem = new JMenuItem();
        menuItem.add(scrollMenu);
        pmuDropDown.add(menuItem);

        initComponents();
        this.add(txtEditField, cc.xy(1, 1));
        this.add(btnDropDown, cc.xy(2, 1));
    }

    public void addItem(String name) {
        itemNames.add(name);
//        Collections.sort(itemNames);
//        pmuDropDown.add(createItem(name),itemNames.indexOf(name));
        pmuDropDown.add(createItem(name));
    }

    public void removeItem(JMenuItem item) {
        itemNames.remove(item.getName());
        pmuDropDown.remove(item);
    }

    private void initComponents() {
        loadListener();



    }

    private void loadListener() {
        btnDropDown.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                showPopupMenu();
            }
        });

        pmuDropDown.addMouseListener(getSelectListener());
    }

    private void showPopupMenu(){
        if(pmuDropDown.isShowing()){
            pmuDropDown.setVisible(false);
        }
        btnDropDown.requestFocusInWindow();
        pmuDropDown.show(txtEditField, 0, txtEditField.getSize().height);
    }

    private MouseAdapter getEditListener(){
        if(editListener == null){
            editListener = new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                }
            };
        }
        return editListener;
    }

    private MouseAdapter getSelectListener(){
        if(selectListener == null){
            selectListener = new MouseAdapter() {

                @Override
                public void mouseReleased(MouseEvent e) {
                    System.out.println("Released");
                    JMenuItem item = (JMenuItem) e.getSource();
                    String lastSelected = txtEditField.getText();
                    if(!lastSelected.trim().isEmpty()){
                        addItem(lastSelected);
                    }
                    item.getParent().remove(item);
                    txtEditField.setText(item.getName());

                }

                @Override
                public void mouseClicked(MouseEvent e) {
                    System.out.println("Selected");
                    JMenuItem item = (JMenuItem) e.getSource();
                    String lastSelected = txtEditField.getText();
                    if(!lastSelected.trim().isEmpty()){
                        addItem(lastSelected);
                    }
                    item.getParent().remove(item);
                    txtEditField.setText(item.getName());
                }
            };
        }
        return selectListener;
    }

    private MouseAdapter getDeleteListener(){
        if(deleteListener == null){
            deleteListener = new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    JButton button = (JButton) e.getSource();
                    Container father = button.getParent();
                    Container grandfather = father.getParent();
                    grandfather.remove(father);
                    itemNames.remove(father.getName());
                    showPopupMenu();
                }
            };
        }
        return deleteListener;
    }

    private JMenuItem createItem(String name) {
        JMenuItem item = new JMenuItem();
        JButton btnEdit = new JButton(new ImageIcon(getClass().getResource("/images/icon_list_more.png")));
        JButton btnDelete = new JButton(new ImageIcon(getClass().getResource("/images/icon_list_more.png")));
        item.addMouseListener(getSelectListener());

        btnDelete.addMouseListener(getDeleteListener());

        item.setName(name);
        item.setLayout(new FormLayout("fill:10px,left:40px:grow,fill:20px,fill:20px", "fill:22px"));
        item.add(new JLabel(name), cc.xy(2, 1));
        item.add(btnEdit, cc.xy(3, 1));
        item.add(btnDelete, cc.xy(4, 1));
        return item;
    }


    private JTextField txtEditField;
    private JButton btnDropDown;
    private JPopupMenu pmuDropDown;
    private JScrollPane scrollMenu;

}
