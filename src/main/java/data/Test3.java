package data;

import examples.CustomArrowComboBoxUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by Eligi.Ran on 2017/3/20.
 */

/**
 * JtextPane use html type
 * */
public class Test3 {
    public static void main(String[] args) {
        Box panel = Box.createVerticalBox();
        Test3 test = new Test3();
        JButton button = test.crtButton();
        button.setMnemonic(KeyEvent.VK_B);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("pressed");
            }
        });

        panel.add(button);

        /* display the panel in a frame */
        JFrame frame = new JFrame();
        frame.getContentPane().add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setVisible(true);


    }

    public JButton crtButton(){
        return new JButton("sd",new ImageIcon(getClass().getResource("/images/icon_list_more.png")));
    }

}
