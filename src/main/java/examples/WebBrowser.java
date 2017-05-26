package examples;

import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Eligi.Ran on 2017/5/26.
 */
public class WebBrowser extends JApplet {
    private JEditorPane jep = new JEditorPane();

    private JLabel jlblURL = new JLabel("URL");

    private JTextField jtfURL = new JTextField();

    public void init() {
        JPanel jpURL = new JPanel();
        jpURL.setLayout(new BorderLayout());
        jpURL.add(jlblURL, BorderLayout.WEST);
        jpURL.add(jtfURL, BorderLayout.CENTER);

        add(new JScrollPane(jep), BorderLayout.CENTER);
        add(jpURL, BorderLayout.NORTH);

        jep.setEditable(false);

        jep.addHyperlinkListener(new HyperlinkListener() {
            @Override
            public void hyperlinkUpdate(HyperlinkEvent e) {
                try {
                    jep.setPage(e.getURL());
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        jtfURL.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    URL url = new URL(jtfURL.getText().trim());
                    jep.setPage(url);
                } catch (MalformedURLException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }
}
