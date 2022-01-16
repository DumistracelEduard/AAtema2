import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

class Retele extends Task {
    StringBuilder answer;

    public static void main(String[] args) throws IOException, InterruptedException {
        Retele task = new Retele();
        task.solve();
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
        int vertices = getVertices();
        int cliques = getExtraData();
        Set<List<Integer>> finalAnswer = new HashSet<>();
        List<Integer> line;
        int[][] matrix = new int[cliques][vertices];
        int i,j;
        int count = 1;
        int number = 0;

        for (i = 0; i < cliques; ++i) {
            line = new ArrayList<>();
            for (j = 0; j < vertices; ++j) {
                matrix[i][j] = count;
                count++;
                line.add(matrix[i][j]);
            }
            line.add(0);
            finalAnswer.add(line);
        }
        number += count;

        for (i = 0; i < cliques; ++i) {
            for (j = 0; j < vertices; ++j) {
                for (int k = i + 1; k < cliques; k++) {
                    line = new ArrayList<>();
                    line.add(-matrix[i][j]);
                    line.add(-matrix[k][j]);
                    line.add(0);
                    finalAnswer.add(line);
                    number++;
                }
            }
        }

        for (i = 0; i < vertices - 1; ++i) {
            for (j = i + 1; j < vertices; ++j) {
                if (getAdjMatrix()[i][j] == 0) {
                    for (int k = 0; k < cliques - 1; ++k) {
                        for (int l = k + 1; l < cliques; ++l) {
                            line = new ArrayList<>();
                            line.add(-matrix[k][i]);
                            line.add(-matrix[l][j]);
                            line.add(0);
                            line.add(-matrix[k][j]);
                            line.add(-matrix[l][i]);
                            line.add(0);
                            finalAnswer.add(line);
                            number += 2;
                        }
                    }
                }
            }
        }

        StringBuilder stringBuilder = new StringBuilder("p cnf " + (cliques * vertices) + " " + number + "\n");
        for (List<Integer> list : finalAnswer) {
            for (Integer number2 : list) {
                if (number2 == 0) {
                    stringBuilder.append(number2).append("\n");
                } else {
                    stringBuilder.append(number2).append(" ");
                }
            }
        }

        FileWriter fileWriter = new FileWriter("sat.cnf");
        fileWriter.write(stringBuilder.toString());
        fileWriter.close();
    }

    @Override
    public void decipherOracleAnswer() throws IOException {
        Scanner scanner = new Scanner(new File("sat.sol"));
        int number;
        answer = new StringBuilder();
        if (scanner.nextBoolean()) {
            this.answer.append("True\n");
            int remainder;
            int size = scanner.nextInt();
            for (int i = 0; i < size; ++i) {
                number = scanner.nextInt();
                if (number > 0) {
                    remainder = number % getVertices();
                    if (remainder == 0) {
                        this.answer.append(getVertices()).append(" ");
                    } else {
                        this.answer.append(remainder).append(" ");
                    }
                }
            }
        } else {
            answer.append("False");
        }
    }

    @Override
    public void writeAnswer() throws IOException {
        System.out.println(answer.toString());
    }
}