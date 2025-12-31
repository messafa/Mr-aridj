import java.util.ArrayList;
import java.util.List;

class Node {
    List<Node> children = new ArrayList<>();
    int value;        
    boolean isLeaf;


    Node(int value) {
        this.value = value;
        this.isLeaf = true;
    }


    Node() {
        this.isLeaf = false;
    }

    void addChild(Node child) {
        children.add(child);
    }
}