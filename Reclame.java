import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

class Reclame extends Task {
    private int[] finalAnswer;
    private StringBuilder stringBuilder = new StringBuilder();
    private Retele retele;
    private Boolean type;

    public static void main(String[] args) throws IOException, InterruptedException {
        Reclame reclame = new Reclame();
        reclame.solve();
    }

    @Override
    public void solve() throws IOException, InterruptedException {
        readProblemData();
        retele = new Retele();
        finalAnswer = new int[getVertices() + 1];
        reduceData(retele);

        for (int clique = getVertices(); clique > 1; --clique) {
            retele.setData(clique);
            formulateOracleQuestion();
            retele.askOracle();
            decipherOracleAnswer();
            if(type) {
                for (int j = 1; j < finalAnswer.length; j++) {
                    if (finalAnswer[j] == 0) {
                        stringBuilder.append(j).append(" ");
                    }
                }
                writeAnswer();
                break;
            }
        }
    }

    public void reduceData(Retele reduce) {
        for (int i = 0; i < getVertices() - 1; ++i) {
            for (int j = i + 1; j < getVertices(); ++j) {
                if (getMatrix()[i][j] == 1) {
                    getMatrix()[i][j] = 0;
                } else {
                    getMatrix()[i][j]  = 1;
                }
            }
        }
        reduce.setVertices(getVertices());
        reduce.setEdges(getEdges());
        reduce.setMatrix(getMatrix());
    }

    @Override
    public void readProblemData() throws IOException {
        readDataToReclame();
    }

    @Override
    public void formulateOracleQuestion() throws IOException {
        retele.formulateOracleQuestion();
    }

    @Override
    public void decipherOracleAnswer() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("sat.sol"));
        StringTokenizer stringBuf;
        String buff;
        Boolean solvable = Boolean.valueOf(bufferedReader.readLine());
        if (solvable) {
            buff = bufferedReader.readLine();
            stringBuf = new StringTokenizer(buff);
            int size = Integer.parseInt(stringBuf.nextToken());
            int number;
            buff = bufferedReader.readLine();
            stringBuf = new StringTokenizer(buff);
            for (int i = 0; i < size; ++i) {
                number = Integer.parseInt(stringBuf.nextToken());
                Boolean assigned = number > 0;
                if (assigned) {
                    if (number % getVertices() == 0) {
                        finalAnswer[getVertices()] = 1;
                    } else {
                        finalAnswer[(number % getVertices())] = 1;
                    }
                }
            }
            this.type = true;
        } else {
            this.type = false;
        }
    }

    @Override
    public void writeAnswer() throws IOException {
        System.out.println(stringBuilder.toString());
    }
}