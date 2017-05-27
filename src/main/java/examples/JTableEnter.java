package examples;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.*;

/**
 * Created by Eligi.Ran on 2017/4/11.
 */
public class JTableEnter extends JFrame {
    private JTable table;
    private TableModel tableModel;

    public JTableEnter() {
        tableModel = new DefaultTableModel(5, 5);

        table = new JTable(tableModel){
            public String getToolTipText(MouseEvent e) {
                String tip = null;
                java.awt.Point p = e.getPoint();
                int rowIndex = rowAtPoint(p);
                int colIndex = columnAtPoint(p);

                try {
                    tip = getValueAt(rowIndex, colIndex).toString();
                } catch (RuntimeException e1) {
                    //catch null pointer exception if mouse is over an empty line
                }

                return tip;
            }
        };
        table.setColumnSelectionAllowed(true);

        getContentPane().add(table);

        Action handleEnter = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                table.getCellEditor().stopCellEditing(); // store user input
                int row = table.getSelectedRow();
                int col = table.getSelectedColumn();
                String val = String.valueOf(table.getValueAt(row, col)).toLowerCase();
                if (val.equals("u"))
                    --row;
                else if (val.equals("d"))
                    ++row;
                else if (val.equals("l"))
                    --col;
                else if (val.equals("r"))
                    ++col;
                if (row >= 0 && row < tableModel.getRowCount()
                        && col >= 0 && col < tableModel.getColumnCount()) {
                    table.changeSelection(row, col, false, false);
                    table.editCellAt(row, col);
                }
            }
        };
        // replace action for ENTER, since next row would be selected automatically
        table.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0), "handleEnter");
        table.getActionMap().put("handleEnter", handleEnter);
//        table.addFocusListener(new FocusAdapter() {
//            public void focusGained(FocusEvent focusEvent) {
//                Object source = focusEvent.getSource();
//                if (source instanceof JTable)
//                {
//                    JTable table = (JTable) source;
//                    int row = table.getSelectionModel().getAnchorSelectionIndex();
//
//                    table.setRowSelectionInterval(row, row+1);
//                }
//            }
//        });
    }

    public static void main(String[] args) {
        JTableEnter test = new JTableEnter();
        test.setSize(800, 600);
        test.setVisible(true);
    }
}
