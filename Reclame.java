import java.io.File;
import java.io.IOException;
import java.util.Scanner;

class Reclame extends Task {
    int[] v;
    StringBuilder stringBuilder = new StringBuilder();
    Retele reduceData;

    public static void main(String[] args) throws IOException, InterruptedException {
        Reclame reclame = new Reclame();
        reclame.solve();
    }

    @Override
    public void solve() throws IOException, InterruptedException {
        readProblemData();
        reduceData = new Retele();
        v = new int[getVertices() + 1];
        reduceData(reduceData);

        for (int clique = getVertices() - 2; clique > 1; --clique) {
            reduceData.setExtraData(clique);
            formulateOracleQuestion();
            reduceData.askOracle();
            if(answer()) {
                for (int j = 1; j < v.length; j++) {
                    if (v[j] == 0) {
                        stringBuilder.append(j).append(" ");
                    }
                }
                writeAnswer();
                break;
            }
        }
    }

    boolean answer() throws IOException {
        Scanner scanner = new Scanner(new File("sat.sol"));

        if (scanner.nextBoolean()) {
           int size = scanner.nextInt();
           int number;
           for (int i = 0; i < size; ++i) {
               number = scanner.nextInt();
               if (number > 0) {
                   if (number % getVertices() == 0) {
                       v[getVertices()] = 1;
                   } else {
                       v[(number % getVertices())] = 1;
                   }
               }
           }
           return true;
        } else {
            return false;
        }
    }

    public void reduceData(Retele reduce) {
        for (int i = 0; i < getVertices() - 1; ++i) {
            for (int j = i + 1; j < getVertices(); ++j) {
                if (getAdjMatrix()[i][j] == 1) {
                    getAdjMatrix()[i][j] = 0;
                } else {
                    getAdjMatrix()[i][j]  = 1;
                }
            }
        }
        reduce.setVertices(getVertices());
        reduce.setEdges(getEdges());
        reduce.setAdjMatrix(getAdjMatrix());
    }

    @Override
    public void readProblemData() throws IOException {
        readDataTask2();
    }

    @Override
    public void formulateOracleQuestion() throws IOException {
        reduceData.formulateOracleQuestion();
    }

    @Override
    public void decipherOracleAnswer() throws IOException {

    }

    @Override
    public void writeAnswer() throws IOException {
        System.out.println(stringBuilder.toString());
    }
}