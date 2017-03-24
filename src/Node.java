import java.util.List;

/**
 * Created by s1309096 on 23/03/17.
 */
public class Node {
    private List<formula> key;
    private Node left;
    private Node right;

    Node (List<formula> key) {
        this.key = key;
        right = null;
        left = null;
    }

    public void setKey(List<formula> key) {
        this.key = key;
    }

    public List<formula> getKey() {
        return key;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getLeft() {
        return left;
    }

    public void setRight(Node right ) {
        this.right = right;
    }

    public Node getRight() {
        return right;
    }
}
