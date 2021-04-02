import java.util.HashSet;
import java.util.Set;

class NedaSa extends RuntimeException {  }  // toto nechajte tak ako je

public class Labyrint {
    Integer[][] lab;                                    // labyrint samotny
    public Labyrint(Integer[][] lab) {                  // konstruktor zadarmo
        this.lab = lab;
    }
    @Override
    public String toString() {                              // aspon nejaky toString
        StringBuilder sb = new StringBuilder();
        String newLine = "";
        if (lab == null) sb.append(" null ");
        else
            for (Integer[] row : lab) {
                sb.append(newLine); newLine = "\n";
                if (row == null) sb.append(" null ");
                else
                    for (Integer elem : row)
                        sb.append((elem == null) ? " null" : " %3d ".formatted(elem));
            }
        return sb.toString();
    }
    //------------------------- vas kod piste odtialto dole ---------------------------------
    public boolean korektny() {
        if (lab == null) return false;
        int M = lab.length;
        if (M == 0) return false;
        if (lab[0] == null) return false;
        int N = lab[0].length;
        for (Integer[] row : lab) if (row == null || row.length != N) return false;
        Set<Integer> s = new HashSet<Integer>();
        for (Integer[] row : lab)
            for (Integer elem : row)
                if (elem == null) return false;
                else if (elem < 1 || elem > M * N) return false;
                else s.add(elem);
        if (lab[0][0] != 1 || lab[M-1][N-1] != M*N) return false;
        return s.size() == M * N;
    }
    public boolean priechodzi() {
        //return false;  // toto doprogramujte
        return korektny() && /*lab[0][0] == 1 && lab[M-1][N-1] == M * N &&*/ foolsDay(0,0, 1);
    }
    private boolean foolsDay(int i, int j, Integer next) {
        return  // kod vzdy piste s vedomim, ze ten, kto ho bude citat, moze byt psychopat, ktory pozna vasu adresu :)
                (lab[i][j].equals(next))&&
                ((i == lab.length-1 && j == lab[i].length-1 && lab[i][j].equals(next))||
                foolsDay((i+1)%lab.length,j, next+1) ||
                foolsDay(Math.floorMod(i-1,lab.length),j,next+1) ||
                foolsDay(i,(j+1)%lab[i].length,next+1) ||
                foolsDay(i,Math.floorMod(j-1,lab[i].length),next+1));
    }

    public Labyrint(int M, int N)  throws NedaSa {
        // toto doprogramujte
        //System.out.println("konstrukor Labyrint(" + M + "," + N + "):");
        if (0 < (((M + 1) * (N + 1)) & 1)) throw new NedaSa();
        lab = new Integer[M][N];
        int next = 1;
        if ((N & 1) > 0)
            for (int j = 0; j < N; j++)
                 for (int i = 0; i < M; i++)
                     lab[((j&1)==0)?i:M-i-1][j] = next++;
        else
            for (int i = 0; i < M; i++)
                 for (int j = 0; j < N; j++)
                     lab[i][((i&1)==0)?j:N-j-1] = next++;
    }

    public boolean priechodziNull() {
        //return false;  // toto doprogramujte
        return zolik(0,0, 1);
    }
    private boolean zolik(int i, int j, Integer next) {
        if (lab[i][j] != null && !lab[i][j].equals(next)) return false;
        else if (i == lab.length-1 && j == lab[i].length-1 && lab[i][j].equals(next)) return true;
        else {
            if (lab[i][j] == null) lab[i][j] = -next;
            boolean b = zolik((i+1)%lab.length,j, next+1)||zolik(Math.floorMod(i-1,lab.length),j,next+1)
                    ||zolik(i,(j+1)%lab[i].length,next+1)||zolik(i,Math.floorMod(j-1,lab[i].length),next+1);
            if (lab[i][j] < 0) lab[i][j] = null;
            return b;
        }
    }

    public static void main(String[] args) {
        //----------------------------------- prikady na korektny
        {
            Labyrint p = new Labyrint(new Integer[][]
                    {{1, 2, 3, 4},
                            {8, 7, 6, 5},
                            {9, 10, 11, 12}});
            System.out.println(p.toString() + " .korektny() = " + p.korektny()+ "\n");  // true
        }
        {
            Labyrint p = new Labyrint(new Integer[][]
                           {{1, null, 3, 4},
                            {8, 7, 6, 5},
                            {9, 10, 11, 12}});
            System.out.println(p.toString() + " .korektny() = " + p.korektny()+ "\n");  // false
        }
        {
            Labyrint p = new Labyrint(new Integer[][]
                           {{1, null, 3, 4},
                            null,
                            {9, 10, 11, 12}});
            System.out.println(p.toString() + " .korektny() = " + p.korektny()+ "\n");  // false
        }
        {
            Labyrint p = new Labyrint(new Integer[][]
                            {null, null, null});
            System.out.println(p.toString() + " .korektny() = " + p.korektny()+ "\n");  // false
        }

        {
            Labyrint p = new Labyrint(new Integer[][]
                           {{1, 2, 3, 4},
                            {8, 7, 6},
                            {9, 10, 11, 12}});
            System.out.println(p.toString() + " .korektny() = " + p.korektny()+ "\n");  // false
        }
        {
            Labyrint p = new Labyrint(new Integer[][]
                           {{1, 2, 3, 4},
                            {8, 7, 6},
                            {9, 2, 11, 12}});
            System.out.println(p.toString() + " .korektny() = " + p.korektny()+ "\n");  // false
        }
        {
            Labyrint p = new Labyrint(new Integer[][]
                           {{0, 1},
                            {2, 3}});
            System.out.println(p.toString() + " .korektny() = " + p.korektny()+ "\n");  // false
        }
        {
            Labyrint p = new Labyrint(new Integer[][]
                           {{1, 1},
                            {1, 3}});
            System.out.println(p.toString() + " .korektny() = " + p.korektny()+ "\n");  // false
        }
        {
            Labyrint p = new Labyrint(new Integer[][]
                           {{1, null},
                            {2, 4}});
            System.out.println(p.toString() + " .korektny() = " + p.korektny()+ "\n");  // false
        }
        {
            Labyrint p = new Labyrint(new Integer[][]
                           {{1, 3},
                            {2, 5}});
            System.out.println(p.toString() + " .korektny() = " + p.korektny()+ "\n");  // false
        }
        {
            Labyrint p = new Labyrint(new Integer[][]
                           {{1}});
            System.out.println(p.toString() + " .korektny() = " + p.korektny()+ "\n");  // true
        }
        //----------------------------------- prikady na priechodzi
        {
            Labyrint p = new Labyrint(new Integer[][]
                    {{1, 2, 3, 4},
                            {8, 7, 6, 5},
                            {9, 10, 11, 12}});
            System.out.println(p.toString() + " .priechodzi() = " + p.priechodzi() + "\n");  // true
        }
        {
            Labyrint p = new Labyrint(new Integer[][]
                    {{1, 2, 9, 10},
                            {4, 3, 8, 11},
                            {5, 6, 7, 12}});
            System.out.println(p.toString() + " .priechodzi() = " + p.priechodzi() + "\n");  // true
        }
        {
            Labyrint p = new Labyrint(new Integer[][]
                    {{1, 2, 9, 8},
                            {6, 3, 10, 7},
                            {5, 4, 11, 12}});
            System.out.println(p.toString() + " .priechodzi() = " + p.priechodzi() + "\n");  // true
        }
        {
            Labyrint p = new Labyrint(new Integer[][]
                    {{1, 2, 11, 10},
                            {4, 3, 8, 9},
                            {5, 6, 7, 12}});
            System.out.println(p.toString() + " .priechodzi() = " + p.priechodzi() + "\n");  // false
        }
        {
            Labyrint p = new Labyrint(new Integer[][]
                           {{1, 2},
                            {3, 4}});
            System.out.println(p.toString() + " .priechodzi() = " + p.priechodzi() + "\n");  // false
        }
        {
            Labyrint p = new Labyrint(new Integer[][]
                        {{  1,  2,  8},
                         {  6,  3,  7},
                         {  5,  4,  9}});
            System.out.println(p.toString() + " .priechodzi() = " + p.priechodzi() + "\n");  // true
        }
        {
            Labyrint p = new Labyrint(new Integer[][]
                         {{  1,  2,  3},
                          {  5,  6,  4},
                          {  8,  7,  9}});
            System.out.println(p.toString() + " .priechodzi() = " + p.priechodzi() + "\n");  // true
        }
        //----------------------------------- prikady na konstruktor(int, int)
        try {
            System.out.println(new Labyrint(2, 2));
        } catch (NedaSa e) {
            System.out.println("a fakt sa neda...");
        }
        System.out.println(new Labyrint(2,3));
        System.out.println(new Labyrint(3,3));
        System.out.println(new Labyrint(4,3));
        System.out.println(new Labyrint(3,4));
        try {
            System.out.println(new Labyrint(4, 4));
        } catch (NedaSa e) {
            System.out.println("a fakt sa neda...");
        }
    }
}
