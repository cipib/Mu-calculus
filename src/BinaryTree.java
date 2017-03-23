/**
 * Created by s1309096 on 23/03/17.
 */
public class BinaryTree {
    private static Node root;

    public BinaryTree(formula data) {
        root = new Node(data);
    }

    public void add(Node parent, Node child, String orientation) {
        if (orientation.equals("left")) {
            parent.setLeft(child);
        } else {
            parent.setRight(child);
        }
    }

}
