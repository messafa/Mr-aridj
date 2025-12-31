import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Node root = new Node();

        Node min1 = new Node();
        min1.addChild(new Node(3));
        min1.addChild(new Node(5));

        Node min2 = new Node();
        min2.addChild(new Node(2));
        min2.addChild(new Node(4));

        root.addChild(min1);
        root.addChild(min2);

        int result = AlphaBeta.alphaBeta(
                root,
                3,
                Integer.MIN_VALUE,
                Integer.MAX_VALUE,
                true
        );

        System.out.println("best value of MAX = " + result);
    }
}

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

class AlphaBeta {

    static int alphaBeta(Node node, int depth, int alpha, int beta, boolean maximizingPlayer) {

        // شرط التوقف
        if (depth == 0 || node.isLeaf) {
            return node.value;
        }

        if (maximizingPlayer) {
            int maxEval = Integer.MIN_VALUE;

            for (Node child : node.children) {
                int eval = alphaBeta(child, depth - 1, alpha, beta, false);
                maxEval = Math.max(maxEval, eval);
                alpha = Math.max(alpha, eval);

                // ✂ Pruning
                if (beta <= alpha) {
                    break;
                }
            }
            return maxEval;

        } else {
            int minEval = Integer.MAX_VALUE;

            for (Node child : node.children) {
                int eval = alphaBeta(child, depth - 1, alpha, beta, true);
                minEval = Math.min(minEval, eval);
                beta = Math.min(beta, eval);

                // ✂ Pruning
                if (beta <= alpha) {
                    break;
                }
            }
            return minEval;
        }
    }
}