package examples;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by Eligi.Ran on 2017/3/20.
 */
/**
 * JtextPane use html type
 * */
public class JTextPaneHTMLDemo {
    public static void main(String[] args) {
        Box panel = Box.createVerticalBox();

        Integer[][] data = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        String[] cols = {"A", "B", "C"};
        JTable table = new JTable(data, cols);


        JComboBox comboBox = new JComboBox(cols);
        comboBox.setPreferredSize(new Dimension(100,100));
        comboBox.setForeground(Color.blue);
        comboBox.setUI(CustomArrowComboBoxUI.createUI());

        JTextPane textField = new JTextPane();
        textField.setPreferredSize(new Dimension(100,100));
        KeyListener keyListener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == 'r') {
                    Toolkit.getDefaultToolkit().beep();
                    System.out.println("pressed r");

                    e.consume();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
//                if (e.getKeyChar() == 'r') {
//                    System.out.println("pressed r");
//                    e.consume();
//                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
//                if (e.getKeyChar() == 'r') {
//                    System.out.println("pressed r");
//                    e.consume();
//                }
            }
        };
        KeyAdapter keyAdapter = new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyChar() == 'r') {
                    System.out.println("pressed r");
                    e.consume();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
            }
        };
        textField.addKeyListener(keyListener);
        textField.setContentType("text/html");
        textField.setText("<html>" +
                "<b><font color=red>"
                + "Please enter some text here" + "</font></b>"
                +"<b><font color=blue>"
                + "Please enter some text here" + "</font></b>"
                + "</html>");
        textField.setText("<html><b><u>T</u>wo</b><br>lines</html>");
        textField.setToolTipText("<html><b><font color=red>"
                + "Please enter some text here" + "</font></b></html>");

        panel.add(new JScrollPane(table), BorderLayout.NORTH);
        panel.add(comboBox, BorderLayout.SOUTH);
        panel.add(textField, BorderLayout.CENTER);

        /* display the panel in a frame */
        JFrame frame = new JFrame();
        frame.getContentPane().add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setVisible(true);


    }

}
