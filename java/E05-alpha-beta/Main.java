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