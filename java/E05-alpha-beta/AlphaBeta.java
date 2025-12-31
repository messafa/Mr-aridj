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