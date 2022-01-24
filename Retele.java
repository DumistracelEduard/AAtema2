import java.io.*;
import java.util.*;

class Retele extends Task {
    private StringBuilder answer = new StringBuilder();

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
    public void readProblemData() {
        readDataRetele();
    }

    @Override
    public void formulateOracleQuestion() throws IOException {
        int vertices = getVertices();
        int cliques = getData();
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
                if (getMatrix()[i][j] == 0) {
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
        BufferedReader bufferedReader = new BufferedReader(new FileReader("sat.sol"));
        int number;
        StringTokenizer st;
        String buffer;
        Boolean solvable = Boolean.valueOf(bufferedReader.readLine());
        if (solvable) {
            this.answer.append("True\n");
            buffer = bufferedReader.readLine();
            st = new StringTokenizer(buffer);
            int size = Integer.parseInt(st.nextToken());
            int remainder;
            buffer = bufferedReader.readLine();
            st = new StringTokenizer(buffer);
            for (int i = 0; i < size; ++i) {
                number = Integer.parseInt(st.nextToken());
                Boolean assigned = number > 0;
                if (assigned) {
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
    public void writeAnswer() {
        System.out.println(answer.toString());
    }
}