import java.util.Scanner;

public class Matrix {
    private int vertices;
    private int edges;
    private int data;
    private int[][] matrix;

    public void readDataToReclame() {
        Scanner scanner = new Scanner(System.in);
        int x, y;
        this.vertices = scanner.nextInt();
        this.edges = scanner.nextInt();
        this.matrix = new int[vertices][vertices];

        for(int i = 0; i < edges; ++i) {
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;
            matrix[x][y] = 1;
        }
    }

    public void readDataRetele() {
        Scanner scanner = new Scanner(System.in);
        int x, y;
        this.vertices = scanner.nextInt();
        this.edges = scanner.nextInt();
        this.data = scanner.nextInt();

        this.matrix = new int[vertices][vertices];
        for (int i = 0; i < edges; ++i) {
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;
            matrix[x][y] = 1;
        }
    }

    public void setVertices(int vertices) {
        this.vertices = vertices;
    }

    public void setEdges(int edges) {
        this.edges = edges;
    }

    public void setData(int data) {
        this.data = data;
    }

    public void setMatrix(int[][] matrix) {
        this.matrix = matrix;
    }

    public int getVertices() {
        return vertices;
    }

    public int getEdges() {
        return this.edges;
    }

    public int getData() {
        return data;
    }

    public int[][] getMatrix() {
        return matrix;
    }
}
