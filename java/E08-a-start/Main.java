import java.util.*;

class AStar {

    static int heuristic(Node a, Node b) {
        return Math.abs(a.x - b.x) + Math.abs(a.y - b.y); // Manhattan
    }

    static List<Node> aStar(int[][] grid, Node start, Node goal) {

        PriorityQueue<Node> open = new PriorityQueue<>(Comparator.comparingInt(n -> n.f));
        boolean[][] closed = new boolean[grid.length][grid[0].length];

        start.g = 0;
        start.h = heuristic(start, goal);
        start.f = start.g + start.h;

        open.add(start);

        while (!open.isEmpty()) {
            Node current = open.poll();

            if (current.x == goal.x && current.y == goal.y) {
                return reconstructPath(current);
            }

            closed[current.x][current.y] = true;

            int[][] directions = {{1,0},{-1,0},{0,1},{0,-1}};

            for (int[] d : directions) {
                int nx = current.x + d[0];
                int ny = current.y + d[1];

                if (nx < 0 || ny < 0 || nx >= grid.length || ny >= grid[0].length)
                    continue;
                if (grid[nx][ny] == 1 || closed[nx][ny])
                    continue;

                Node neighbor = new Node(nx, ny);
                int tentativeG = current.g + 1;

                neighbor.g = tentativeG;
                neighbor.h = heuristic(neighbor, goal);
                neighbor.f = neighbor.g + neighbor.h;
                neighbor.parent = current;

                open.add(neighbor);
            }
        }
        return null;
    }

    static List<Node> reconstructPath(Node node) {
        List<Node> path = new ArrayList<>();
        while (node != null) {
            path.add(node);
            node = node.parent;
        }
        Collections.reverse(path);
        return path;
    }
}


class Node {
    int x, y;
    int g, h, f;
    Node parent;

    Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}



public class Main {
    public static void main(String[] args) {

        int[][] grid = {
            {0, 0, 0, 1},
            {0, 1, 0, 0},
            {0, 0, 0, 0}
        };

        Node start = new Node(0, 0);
        Node goal  = new Node(2, 3);

        List<Node> path = AStar.aStar(grid, start, goal);

        for (Node n : path) {
            System.out.println("(" + n.x + "," + n.y + ")");
        }
    }
}
