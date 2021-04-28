import java.io.Serializable;
import java.util.Arrays;
import java.util.Random;

public class State implements Serializable {
        private static final long serialVersionUID = 1L;
        public int[][] playground;

        public State(int size) {
            playground = get2Darr(size); // RANDOM konfiguracia (nahodne rozmiestnenie 0 a 1)

            // ina konfiguracia:
            /*
            playground = new int[size][size];
            for(int i = 0; i < size; i++) Arrays.fill(playground[i], 0);
            for(int i = 0; i < size; i++) {
                for(int j = 0; j < size; j += 3) playground[i][j] = 1;
                for(int j = 0; j < size; j++) if(i - j < 4 || j - 1 < 4) playground[i][j] = 1;
            }
            */
        }

        private int[][] get2Darr(int size){
            Random rnd = new Random();
            int[][] arr = new int[size][size];
            // nahodne generujem 0/1 v poli
            for(int i = 0; i < size; i++) for(int j = 0; j < size; j++) arr[i][j] = rnd.nextBoolean() ? 1 : 0;
            for(int i = 0; i < size; i++) {
                // prechadzam od 1 po size-1
                // inak by po stranach vzdy existovali zive bunky keby ich tam nahodne nagenerujem a potom neprechadzam v update()
                for(int j = 0; j < size; j++) if(i == size-1 || j == size-1 || i == 0 || j == 0) arr[i][j] = 0;
            }
            return arr;
        }

        // tato simulacia mi prisla strasne cool
        // obsahuje nejake blinkre, bee-hives, loafs, tubs,...

        public void update() {
            int[][] next = get2Darr(playground.length);

            for(int i = 1; i < playground.length-1; i++) {
                for(int j = 1; j < playground[i].length - 1; j++) {

                    // pocet susedov
                    int sum = 0;
                    sum += playground[i - 1][j - 1] + playground[i][j - 1] + playground[i - 1][j] +
                            playground[i - 1][j + 1] + playground[i + 1][j - 1] + playground[i + 1][j + 1] +
                            playground[i][j + 1] + playground[i + 1][j];

                    // tak nejak podla pravidiel
                    var curr = playground[i][j];
                    if(curr == 0 && sum == 3) next[i][j] = 1;
                    else {
                        if(curr == 1 && (sum < 2 || sum > 3)) next[i][j] = 0;
                        else next[i][j] = curr;
                    }
                }
            }
            playground = next;
        }
    }
