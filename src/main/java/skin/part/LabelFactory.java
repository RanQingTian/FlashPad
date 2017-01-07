package skin.part;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * Created by Eligi.Ran on 2017/1/6.
 */
public class LabelFactory {
    public static Border border = BorderFactory.createLineBorder(Color.BLACK);


    public static JLabel crtIndexLabel(String name){
        JLabel jLabel = new JLabel(name);
        jLabel.setBorder(border);
        return jLabel;
    }
}
