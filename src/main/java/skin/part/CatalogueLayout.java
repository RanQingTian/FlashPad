package skin.part;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;

/**
 * Created by Eligi.Ran on 2017/1/6.
 */
public class CatalogueLayout extends JPanel {
    //record box
    private Box rdMngBox;



    private CatalogueLayout() {
        rdMngBox = Box.createVerticalBox();
        JPanel rdCatPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        //create the root node
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");
        //create the child nodes
        DefaultMutableTreeNode vegetableNode = new DefaultMutableTreeNode("Vegetables");
        DefaultMutableTreeNode fruitNode = new DefaultMutableTreeNode("Fruits");
        //add the child nodes to the root node
        root.add(vegetableNode);
        root.add(fruitNode);
        JTree mngTree = new JTree(root);
        mngTree.setPreferredSize(new Dimension(200,500));
        rdCatPanel.add(mngTree);
        rdMngBox.add(rdCatPanel);

    }

    public static JComponent newInstance() {
        return new CatalogueLayout().getRdMngBox();
    }

    public Box getRdMngBox() {
        return rdMngBox;
    }
}
