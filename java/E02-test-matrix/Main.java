public class Main {
    
    public static void main(String[] args) {
        int[][] matrix = {
            {1,1,1,1,1},
            {0,1,0,1,1},
            {1,1,0,1,0},
            {1,0,1,1,1},
            {1,1,1,0,1}
        };

        int[][] visited = new int[matrix.length][matrix[0].length];

        Point startPoint = new Point(0, 0);
        Point endPoint = new Point(4, 4);
        traverseMatrix(matrix, visited, startPoint, endPoint);
        

        
    }

    private static void traverseMatrix(int[][] matrix, int[][] visited, Point startPoint, Point endPoint) {
        
        // base case
        if (startPoint.getX() < 0 || startPoint.getY() < 0 || 
            startPoint.getX() >= matrix.length || startPoint.getY() >= matrix[0].length) {
            return;
        }

        if (matrix[startPoint.getY()][startPoint.getX()] == 0 || 
            visited[startPoint.getY()][startPoint.getX()] == 1) {
            return;
        }
        if (startPoint.getX() == endPoint.getX() && startPoint.getY() == endPoint.getY()) {
            System.out.println("Reached the end point: (" + endPoint.getX() + ", " + endPoint.getY() + ")");
            return;
        }

        // mark as visited
        visited[startPoint.getY()][startPoint.getX()] = 1;

        // move in all four directions
        Point upPoint = new Point(startPoint.getX(), startPoint.getY());
        upPoint.up();
        traverseMatrix(matrix, visited, upPoint, endPoint);

        Point downPoint = new Point(startPoint.getX(), startPoint.getY());
        downPoint.down();
        traverseMatrix(matrix, visited, downPoint, endPoint);

        Point leftPoint = new Point(startPoint.getX(), startPoint.getY());
        leftPoint.left();
        traverseMatrix(matrix, visited, leftPoint, endPoint);

        Point rightPoint = new Point(startPoint.getX(), startPoint.getY());
        rightPoint.right();
        traverseMatrix(matrix, visited, rightPoint, endPoint);

        // backtrack
        visited[startPoint.getY()][startPoint.getX()] = 0;
        

    }

    public static class Point {
    public int x;
    public int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // setters and getters
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    // to up y=y-1
    public void up() {
        this.y = this.y - 1;
    }
    // to down y=y+1
    public void down() {
        this.y = this.y + 1;
    }
    // to left x=x-1
    public void left() {
        this.x = this.x - 1;
    }
    // to right x=x+1
    public void right() {
        this.x = this.x + 1;
    }
}

   
}