import java.io.*;
import java.util.*;

class Registre extends Task {
    private StringBuilder stringBuilder = new StringBuilder();

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
        readDataRetele();
    }

    @Override
    public void formulateOracleQuestion() throws IOException {
        int[][] matrix = new int[getVertices()][getData()];
        List<Integer> line;
        Set<List<Integer>> answer = new HashSet<>();
        int i, j;
        int number = 0;
        int count = 1;
        for (i = 0; i < getVertices(); ++i) {
            line = new ArrayList<>();
            for (j = 0; j < getData(); ++j) {
                matrix[i][j] = count;
                ++count;
                line.add(matrix[i][j]);
            }
            line.add(0);
            answer.add(line);
        }
        number += count;

        for (i = 0; i < getVertices(); ++i) {
            for (j = 0; j < getData(); ++j) {
                for (int k = j + 1; k < getData(); k++) {
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
                if (getMatrix()[i][j] == 1) {
                    for (int k = 0; k < getData(); ++k) {
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
        StringBuilder data = new StringBuilder("p cnf " + (getData() * getVertices()) + " " + number + "\n");
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
        BufferedReader bufferedReader = new BufferedReader(new FileReader("sat.sol"));
        int number;
        StringTokenizer st;
        String buffer;
        Boolean solvable = Boolean.valueOf(bufferedReader.readLine());
        if (solvable) {
            this.stringBuilder.append("True\n");
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
                    remainder = number % getData();
                    if (remainder == 0) {
                        this.stringBuilder.append(getData()).append(" ");
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