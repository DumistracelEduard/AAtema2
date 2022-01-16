import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

class Registre extends Task {
    StringBuilder stringBuilder;
    public static void main(String[] args) throws IOException, InterruptedException {
        Registre registre = new Registre();
        registre.solve();
    }

    @Override
    public void solve() throws IOException, InterruptedException {
        readProblemData();
        formulateOracleQuestion();
        askOracle();
        decipherOracleAnswer();
        writeAnswer();
    }

    @Override
    public void readProblemData() throws IOException {
        readDataTask1();
    }

    @Override
    public void formulateOracleQuestion() throws IOException {
        int[][] matrix = new int[getVertices()][getExtraData()];
        List<Integer> line;
        Set<List<Integer>> answer = new HashSet<>();
        int i, j;
        int number = 0;
        int count = 1;
        for (i = 0; i < getVertices(); ++i) {
            line = new ArrayList<>();
            for (j = 0; j < getExtraData(); ++j) {
                matrix[i][j] = count;
                ++count;
                line.add(matrix[i][j]);
            }
            line.add(0);
            answer.add(line);
        }
        number += count;

        for (i = 0; i < getVertices(); ++i) {
            for (j = 0; j < getExtraData(); ++j) {
                for (int k = j + 1; k < getExtraData(); k++) {
                    line = new ArrayList<>();
                    line.add(-matrix[i][j]);
                    line.add(-matrix[i][k]);
                    line.add(0);
                    number++;
                    answer.add(line);
                }
            }
        }

        for (i = 0; i < getVertices() - 1; ++i) {
            for (j = i + 1; j < getVertices(); ++j) {
                if (getAdjMatrix()[i][j] == 1) {
                    for (int k = 0; k < getExtraData(); ++k) {
                        line = new ArrayList<>();
                        line.add(-matrix[i][k]);
                        line.add(-matrix[j][k]);
                        line.add(0);
                        number++;
                        answer.add(line);
                    }
                }
            }
        }
        StringBuilder data = new StringBuilder("p cnf " + (getExtraData() * getVertices()) + " " + number + "\n");
        for (List<Integer> list : answer) {
            for (Integer number2 : list) {
                if (number2 == 0) {
                    data.append(number2).append("\n");
                } else {
                    data.append(number2).append(" ");
                }
            }
        }

        FileWriter fileWriter = new FileWriter("sat.cnf");
        fileWriter.write(data.toString());
        fileWriter.close();
    }


    @Override
    public void decipherOracleAnswer() throws IOException {
        Scanner scanner = new Scanner(new File("sat.sol"));
        int number;
        stringBuilder = new StringBuilder();
        if (scanner.nextBoolean()) {
            this.stringBuilder.append("True\n");
            int remainder;
            int size = scanner.nextInt();
            for (int i = 0; i < size; ++i) {
                number = scanner.nextInt();
                if (number > 0) {
                    remainder = number % getExtraData();
                    if (remainder == 0) {
                        this.stringBuilder.append(getExtraData()).append(" ");
                    } else {
                        this.stringBuilder.append(remainder).append(" ");
                    }
                }
            }
        } else {
            stringBuilder.append("False");
        }
    }

    @Override
    public void writeAnswer() throws IOException {
        System.out.println(stringBuilder.toString());
    }
}