import java.io.*;
import java.util.Arrays;

public class State implements Serializable {
        private static final long serialVersionUID = 1L;
        public int[][] playground;

        public State(int size) {
            State s = load(); // nacita poslednu konfiguraciu(ak ma rovnaku size) ak neexistuje tak vyrobi prazdnu plochu

            if (s == null || s.playground.length != size) {
                playground = new int[size][size];
            } else {
                playground = s.playground;
            }

        }

        public void save() {
            try {
                ObjectOutputStream fs = new ObjectOutputStream(new FileOutputStream("config.txt"));
                fs.writeObject(this);
                fs.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public State load() {
            try {
                ObjectInputStream is = new ObjectInputStream(new FileInputStream("config.txt"));
                State s = (State)is.readObject();
                is.close();
                return s;
            } catch (IOException | ClassNotFoundException ignored) { }

            return null;
        }

        public void update() {
            // Any live cell with fewer than two live neighbours dies, as if by underpopulation.
            // Any live cell with two or three live neighbours lives on to the next generation.
            // Any live cell with more than three live neighbours dies, as if by overpopulation.
            // ny dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.

            // stare pole nechcem modifikovat pocas behu takze si vysledky ukladam do noveho

            int[][] newPlayground = new int[playground.length][playground.length];

            for (int i = 0; i < playground.length; i++) {
                for (int j = 0; j < playground[i].length; j++) {
                    int neighbours = numOfNeighbours(i,j);

                    if (neighbours == 3 || (neighbours == 2 && playground[i][j] == 1)) { // ak su 3 tak nemusi moze byt dead a narodi sa ak su 2 musi byt live
                        newPlayground[i][j] = 1;
                    }
                }
            }

            playground = newPlayground;

        }

        private int numOfNeighbours(int row, int col) {
            int count = 0;
            for (int r = row-1; r <= row+1; r++) {
                for (int c = col-1; c <= col+1; c++) {
                    if (r >= 0 && r < playground.length && c >= 0 && c < playground[r].length) {
                        count += playground[r][c];
                    }
                }
            }

            return count - playground[row][col];
        }
    }
