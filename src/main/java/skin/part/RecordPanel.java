package skin.part;

import underskin.blood.RecordContainer;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Eligi.Ran on 2017/1/5.
 */
public class RecordPanel extends JTabbedPane implements ChangeListener {



    public void addTab(RecordContainer recordContainer){
        this.addTab(recordContainer.getTitle(),crtRecordPanel());

    }

    public static JTabbedPane newRecordView() {
        return new RecordPanel();
    }

    public void stateChanged(ChangeEvent e) {
        JTabbedPane sourceTabbedPane = (JTabbedPane) changeEvent.getSource();
        int index = sourceTabbedPane.getSelectedIndex();
        System.out.println("Tab changed to: " + sourceTabbedPane.getTitleAt(index));

//        sourceTabbedPane.getComponentAt(index).setVisible(false);
    }

    public JPanel crtRecordPanel(RecordContainer recordContainer){
        JPanel rdPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc;

        gbc = crtGBC(0, 0, 1);
        gbc.insets = new Insets(5, 0, 0, 0);
        rdPanel.add(crtRecordTitle(recordContainer.getTitle()), gbc);

        JComponent recordContent = crtRecordContent(recordContainer.getContent());
        gbc.gridy++;
        gbc.gridwidth = 10;
        gbc.weighty=1;
        gbc.ipady = 50;
        gbc.ipadx = 50;
        gbc.insets = new Insets(20, 0, 0, 0);
        rdPanel.add(recordContent, gbc);

        return rdPanel;
    }

    public JPanel crtRecordTitle(String title) {
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JLabel rdTitleLabel = new JLabel();
        rdTitleLabel.setText("Label : ");
        JTextField rdTitleText = new JTextField(title);
//        rdTitleText.setPreferredSize(new Dimension(100, 20));
        titlePanel.add(rdTitleLabel);
        titlePanel.add(rdTitleText);
        return titlePanel;
    }

    public JComponent crtRecordContent(String content) {

        JTextArea rdContentArea = new JTextArea(50,50);
        rdContentArea.setAutoscrolls(true);
        rdContentArea.setMargin(new Insets(10, 10, 10, 10));
        rdContentArea.append(content);

        return rdContentArea;
    }

    private JPanel crtRecordPanel() {
        JPanel rdPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc;


        gbc = crtGBC(0, 0, 1);
        gbc.insets = new Insets(5, 0, 0, 0);
        rdPanel.add(crtRecordTitle(), gbc);

        JComponent recordContent = crtRecordContent();
        gbc.gridy++;
        gbc.gridwidth = 10;
        gbc.weighty=1;
        gbc.ipady = 50;
        gbc.ipadx = 50;
        gbc.insets = new Insets(20, 0, 0, 0);
        rdPanel.add(recordContent, gbc);

        return rdPanel;
    }



    private JComponent crtRecordTitle() {
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JLabel rdTitleLabel = new JLabel();
        rdTitleLabel.setText("Label : ");
        JTextField rdTitleText = new JTextField("ccc");
        rdTitleText.setPreferredSize(new Dimension(100, 20));
        titlePanel.add(rdTitleLabel);
        titlePanel.add(rdTitleText);
        return titlePanel;
    }

    private JComponent crtRecordContent() {

        JTextArea rdContentArea = new JTextArea(50,50);
        rdContentArea.setAutoscrolls(true);
        rdContentArea.setMargin(new Insets(10, 10, 10, 10));


        return rdContentArea;
    }

    private GridBagConstraints crtGBC(int x, int y, int height) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.ipadx = 1;
        gbc.ipady = 1;
        gbc.weightx = 1;
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = 1;
        gbc.gridheight = height;

        return gbc;
    }

    private RecordPanel() {
        String title1 = "first";
        String title2 = "second";
        String title3 = "third";


        this.addTab(title1, crtRecordPanel());
        this.addTab(title2, crtRecordPanel());
        this.addTab(title3, crtRecordPanel());

        this.addChangeListener(this);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JTabbedPane sourceTabbedPane = (JTabbedPane) changeEvent.getSource();
                int index = sourceTabbedPane.getSelectedIndex();
                if(e.getClickCount() == 2){
                    System.out.println("Hello: " + sourceTabbedPane.getTitleAt(index));
                    sourceTabbedPane.remove(sourceTabbedPane.getComponentAt(index));
                }
            }
        });

    }



}

