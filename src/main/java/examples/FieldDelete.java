package examples;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 * Created by Eligi.Ran on 2017/3/20.
 */
public class FieldDelete {
    public static void main(String[] args) {
        JPanel panel = new JPanel(new BorderLayout());
        JTextField textField = new JTextField();
        textField.setText("a1,b2,c3,d4,e5,f6,g7");
//        textField.setEditable(false);
        panel.add(textField, BorderLayout.CENTER);
        textField.getActionMap().put("deleteAction", new AbstractAction() {
            final String separator = ",";
            public void actionPerformed(ActionEvent e) {
                JTextField field = (JTextField) e.getSource();
                String originalText = field.getText();
                int originalLength = originalText.length();
                int caretPos = field.getCaretPosition();
                if(caretPos == originalLength) {
                    return;
                }
                int behindComma = originalText.indexOf(separator, caretPos);
                int frontComma = originalText.substring(0, caretPos).lastIndexOf(separator);
                String finalText = originalText;
                if(frontComma == -1 && behindComma == -1) {
                    finalText = "";
                } else if(frontComma != -1 && behindComma == -1) {
                    finalText = originalText.substring(0,frontComma);
                } else if(frontComma == -1 && behindComma != -1) {
                    if(caretPos == behindComma) {
                        int doubleBehind = originalText.substring(behindComma + 1, originalLength).indexOf(separator);
                        if (doubleBehind == -1) {
                            finalText = originalText.substring(0, behindComma);
                        } else {
                            doubleBehind += behindComma + 1;
                            finalText = originalText.substring(0, behindComma) + originalText.substring(doubleBehind, originalLength);
                        }
                    } else {
                        finalText = originalText.substring(behindComma + 1,originalLength);
                    }
                } else {
                    int doubleBehind = originalText.substring(behindComma + 1, originalLength).indexOf(separator);
                    if (originalText.charAt(caretPos) == ',') {
                        if (doubleBehind == -1) {
                            finalText = originalText.substring(0, behindComma);
                        } else {
                            doubleBehind += behindComma + 1;
                            finalText = originalText.substring(0, behindComma) + originalText.substring(doubleBehind, originalLength);
                        }
                    } else{
                        finalText = originalText.substring(0, frontComma) + originalText.substring(behindComma, originalLength);
                    }
                }
                field.setText(finalText);
            }
        });
        textField.getActionMap().put("backspaceAction", new AbstractAction() {
            final String separator = ",";
            public void actionPerformed(ActionEvent e) {
                JTextField field = (JTextField) e.getSource();
                String originalText = field.getText();
                int originalLength = originalText.length();
                int caretPos = field.getCaretPosition();
                if(caretPos == 0) {
                    return;
                }
                int behindComma = originalText.indexOf(separator, caretPos);
                int frontComma = originalText.substring(0, caretPos).lastIndexOf(separator);
                String finalText = originalText;
                if(frontComma == -1 && behindComma == -1) {
                    finalText = "";
                } else if(frontComma != -1 && behindComma == -1) {
                    if((caretPos - 1) == originalText.lastIndexOf(separator)) {
                        int doubleFront = originalText.substring(0, frontComma).lastIndexOf(separator);
                        if (doubleFront == -1) {
                            finalText = originalText.substring(frontComma + 1, originalLength);
                        } else {
                            finalText = originalText.substring(0, doubleFront) + originalText.substring(frontComma, originalLength);
                        }
                    }else {
                            finalText = originalText.substring(0, frontComma);
                    }
                } else if(frontComma == -1 && behindComma != -1) {
                    finalText = originalText.substring(behindComma + 1,originalLength);
                } else {
                    if(originalText.charAt(caretPos - 1) == ','){
                        int doubleFront = originalText.substring(0, frontComma).lastIndexOf(separator);
                        if (doubleFront == -1) {
                            finalText = originalText.substring(frontComma + 1, originalLength);
                        } else {
                            finalText = originalText.substring(0, doubleFront) + originalText.substring(frontComma, originalLength);
                        }
                    } else {
                        finalText = originalText.substring(0, frontComma) + originalText.substring(behindComma, originalLength);
                    }
                }
                field.setText(finalText);
            }
        });
        InputMap textInputMap = textField.getInputMap();
        textInputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SPACE, 0), "backspaceAction");
        textInputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0), "deleteAction");
        /* add a new action named "foo" to the panel's action map */
        panel.getActionMap().put("foo", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("hello, world");
            }
        });
        /* connect two keystrokes with the newly created "foo" action:
           - a
           - CTRL-a
        */
        InputMap inputMap = panel.getInputMap();
        inputMap.put(KeyStroke.getKeyStroke(Character.valueOf('a'), 0), "foo");
        inputMap.put(KeyStroke.getKeyStroke("control a"), "foo");

        /* display the panel in a frame */
        JFrame frame = new JFrame();
        frame.getContentPane().add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setVisible(true);


    }

}
