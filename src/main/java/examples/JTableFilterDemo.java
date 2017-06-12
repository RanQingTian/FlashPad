package examples;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
/**
 * Created by Eligi.Ran on 2017/6/12.
 */

public class JTableFilterDemo {
    public static void main(String[] args) {
        Object[][] data = { { true, "A", 5 }, {true, "B", 2 }, {false, "C", 4 }, {false, "D", 8 } };
        String columnNames[] = { "selected", "Item", "Value" };
        TableModel model = new DefaultTableModel(data, columnNames) {
            public Class<?> getColumnClass(int column) {
                return getValueAt(0, column).getClass();
            }
        };
        JTable table = new JTable(model);

        RowFilter<Object, Object> filter = new RowFilter<Object, Object>() {
            public boolean include(Entry entry) {
                Integer population = (Integer) entry.getValue(2);
                return population.intValue() > 3;
            }
        };

        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
        sorter.setRowFilter(filter);
        table.setRowSorter(sorter);
        JScrollPane scrollPane = new JScrollPane(table);
        JFrame frame = new JFrame("Filtering Table");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(scrollPane);
        frame.setSize(300, 200);
        frame.setVisible(true);
    }
}
