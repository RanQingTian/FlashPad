package skin.part;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;

/**
 * Created by Eligi.Ran on 2017/1/6.
 */
public class CatalogueLayout extends JPanel {



    private CatalogueLayout() {
        GridLayout gridLayout = new GridLayout(1,1);
        this.setLayout(gridLayout);

        //create the root node
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root1");
        //create the child nodes
        DefaultMutableTreeNode vegetableNode = new DefaultMutableTreeNode("Vegetables");
        DefaultMutableTreeNode fruitNode = new DefaultMutableTreeNode("Fruits");
        //add the child nodes to the root node
        root.add(vegetableNode);
        root.add(fruitNode);

        //create the root node
        DefaultMutableTreeNode root2 = new DefaultMutableTreeNode("Root2");
        //create the child nodes
        DefaultMutableTreeNode vegetableNode2 = new DefaultMutableTreeNode("Vegetables");
        DefaultMutableTreeNode fruitNode2 = new DefaultMutableTreeNode("Fruits");
        //add the child nodes to the root node
        root2.add(vegetableNode2);
        root2.add(fruitNode2);

        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("Deafult");
        rootNode.add(root);
        rootNode.add(root2);
        JTree mngTree = new JTree(rootNode);
        mngTree.setAutoscrolls(true);
        mngTree.setRootVisible(false);
        mngTree.setPreferredSize(new Dimension(150,200));
        this.add(mngTree);


    }

    public static JComponent newInstance() {
        return new CatalogueLayout();
    }

}
