package skin.part;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * Created by Eligi.Ran on 2017/1/5.
 */
public class RecordPanel extends JPanel {
    //record box
    private JPanel recordChooser;
    private JPanel recordPanel;
    private Border border = BorderFactory.createLineBorder(Color.BLACK);

    private RecordPanel() {
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc;
        recordChooser = crtRecordChooser();
        recordPanel = crtRecordPanel();

        gbc = crtGBC(0, 0, 1);
//        gbc.insets = new Insets(0, 0, 0, 0);
        this.add(recordChooser, gbc);
//        gbc.insets = new Insets(100, 0, 0, 0);
        gbc.gridy++;
        gbc.gridheight=5;
        gbc.weighty=1;
        this.add(recordPanel, gbc);
        System.out.println(recordChooser.getPreferredSize());
    }

    public static JComponent newRecordView() {
        return new RecordPanel();
    }

    public static void main(String[] args) {

        JFrame jFrame = new JFrame();
        jFrame.add(new RecordPanel());
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setTitle("FlashPad");
        jFrame.setPreferredSize(new Dimension(1000, 1000));
        jFrame.pack();
        jFrame.setVisible(true);
    }

    private JPanel crtRecordChooser() {
        JPanel rdChooser = new JPanel(new FlowLayout(FlowLayout.LEFT));
        rdChooser.setBorder(border);
        rdChooser.setLayout(new FlowLayout(FlowLayout.LEFT));

        rdChooser.add(LabelFactory.crtIndexLabel("hello1"));
        rdChooser.add(LabelFactory.crtIndexLabel("hello2"));
        rdChooser.add(LabelFactory.crtIndexLabel("hello3"));

        return rdChooser;
    }

    private JPanel crtRecordPanel() {
        JPanel rdPanel = new JPanel(new GridBagLayout());
        rdPanel.setBorder(new TitledBorder("Hello Border"));
        GridBagConstraints gbc;

        JComponent recordLabel = crtRecordLabel();
        JComponent recordContent = crtRecordContent();
        gbc = crtGBC(0, 0, 1);
//        gbc.insets = new Insets(50, 50, 50, 50);
        rdPanel.add(recordLabel, gbc);
        gbc = crtGBC(0, 1, 10);
        gbc.weighty=1;
//        gbc.insets = new Insets(700, 0, 0, 0);
        rdPanel.add(recordContent, gbc);


        return rdPanel;
    }

    private JComponent crtRecordTitle() {
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        titlePanel.setBorder(border);

        JLabel rdTitleLabel = new JLabel();
        rdTitleLabel.setText("Label : ");
        JTextField rdTitleText = new JTextField("ccc");
        titlePanel.add(rdTitleLabel);
        titlePanel.add(rdTitleText);
        return titlePanel;
    }

    private JComponent crtRecordLabel() {
        JPanel labelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        labelPanel.add(new Label("first"));
        labelPanel.add(new Label("second"));
        labelPanel.add(new Label("third"));
        return labelPanel;
    }

    private JComponent crtRecordContent() {
        JPanel contentPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        contentPanel.setBorder(border);

        JTextArea rdContentArea = new JTextArea(50,50);
        JScrollPane rdContentPane = new JScrollPane(rdContentArea);
        rdContentPane.setBorder(border);
        contentPanel.add(rdContentPane);


        return rdContentPane;
    }

    private GridBagConstraints crtGBC(int x, int y, int height) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;

        gbc.weightx = 1;
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = 1;
        gbc.gridheight = height;

        return gbc;
    }

}
