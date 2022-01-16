import java.util.Scanner;

public class Matrix {
    private int vertices;
    private int edges;
    private int extraData;
    private int[][] adjMatrix;

    public void readDataTask2() {
        Scanner scanner = new Scanner(System.in);
        this.vertices = scanner.nextInt();
        this.edges = scanner.nextInt();
        this.adjMatrix = new int[vertices][vertices];

        for(int i = 0; i < edges; ++i) {
            adjMatrix[scanner.nextInt() - 1][scanner.nextInt() - 1] = 1;
        }
    }

    public void readDataTask1() {
        Scanner scanner = new Scanner(System.in);
        int x, y;
        this.vertices = scanner.nextInt();
        this.edges = scanner.nextInt();
        this.extraData = scanner.nextInt();

        this.adjMatrix = new int[vertices][vertices];
        for (int i = 0; i < edges; ++i) {
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;
            adjMatrix[x][y] = 1;
        }
    }

    public void setVertices(int vertices) {
        this.vertices = vertices;
    }

    public void setEdges(int edges) {
        this.edges = edges;
    }

    public void setExtraData(int extraData) {
        this.extraData = extraData;
    }

    public void setAdjMatrix(int[][] adjMatrix) {
        this.adjMatrix = adjMatrix;
    }

    public int getVertices() {
        return vertices;
    }

    public int getEdges() {
        return this.edges;
    }

    public int getExtraData() {
        return extraData;
    }

    public int[][] getAdjMatrix() {
        return adjMatrix;
    }
}
