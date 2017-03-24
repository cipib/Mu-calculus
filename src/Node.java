import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by s1309096 on 23/03/17.
 */
public class Node {
    private List<formula> key;
    private Node left;
    private Node right;
    private List<String> parents = new ArrayList<>();

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

    public List<String> getParents(){
        return parents;
    }

    public static String getNode(Node node) {
        String s = "";
        List<String> list = new ArrayList<>();
        for (formula f : node.getKey())
            list.add(f.toString() + f.names);
        Collections.sort(list);
        for(String str:list)
            s = s + str;
        return s;
    }

    public void setParents(Node node) {
        if(node.getParents() != null)
            parents.addAll(node.getParents());

        parents.add(getNode(node));
    }
}
