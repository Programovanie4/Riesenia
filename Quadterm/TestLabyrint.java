import LISTTestScoring.LISTTestScoring;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestLabyrint {
    private static LISTTestScoring scoring = null;
    @BeforeClass
    public static void initScoring() {
        scoring = new LISTTestScoring();
        scoring.setScore("lang:common_list_test_scoring_name", 0, 100);
    }

    @Test
    public void testKorektny() {
        {
            Labyrint p = new Labyrint(new Integer[][]
                           {{1, 2, 3, 4},
                            {8, 7, 6, 5},
                            {9, 10, 11, 12}});
            assertTrue(p.toString() + " .korektny() = ", p.korektny());  // true
        }
        {
            Labyrint p = new Labyrint(new Integer[][]
                           {{1, null, 3, 4},
                            {8, 7, 6, 5},
                            {9, 10, 11, 12}});
            assertFalse(p.toString() + " .korektny() = ", p.korektny());  // false
        }
        {
            Labyrint p = new Labyrint(new Integer[][]
                           {{1, null, 3, 4},
                            null,
                            {9, 10, 11, 12}});
            assertFalse(p.toString() + " .korektny() = ", p.korektny());  // false
        }
        {
            Labyrint p = new Labyrint(new Integer[][]
                            {null, null, null});
            assertFalse(p.toString() + " .korektny() = ", p.korektny());  // false
        }
        {
            Labyrint p = new Labyrint(new Integer[][]
                           {{1, 2, 3, 4},
                            {8, 7, 6},
                            {9, 10, 11, 12}});
            assertFalse(p.toString() + " .korektny() = ", p.korektny());  // false
        }
        {
            Labyrint p = new Labyrint(new Integer[][]
                           {{1, 2, 3, 4},
                            {8, 7, 6},
                            {9, 2, 11, 12}});
            assertFalse(p.toString() + " .korektny() = ", p.korektny());  // false
        }
        {
            Labyrint p = new Labyrint(new Integer[][]
                           {{0, 1},
                            {2, 3}});
            assertFalse(p.toString() + " .korektny() = ", p.korektny());  // false
        }
        {
            Labyrint p = new Labyrint(new Integer[][]
                           {{1, 1},
                            {1, 3}});
            assertFalse(p.toString() + " .korektny() = ", p.korektny());  // false
        }
        {
            Labyrint p = new Labyrint(new Integer[][]
                           {{1, null},
                            {2, 4}});
            assertFalse(p.toString() + " .korektny() = ", p.korektny());  // false
        }
        {
            Labyrint p = new Labyrint(new Integer[][]
                           {{1, 3},
                            {2, 5}});
            assertFalse(p.toString() + " .korektny() = ", p.korektny());  // false
        }
        {
            Labyrint p = new Labyrint(new Integer[][]
                    {{1}});
            assertTrue(p.toString() + " .korektny() = ", p.korektny());  // true
        }
        scoring.updateScore("lang:common_list_test_scoring_name", 35);
    }

    @Test
    public void testpriechodzi() {
        {
            Labyrint p = new Labyrint(new Integer[][]
                           {{1, 2, 3, 4},
                            {8, 7, 6, 5},
                            {9, 10, 11, 12}});
            assertTrue(p.toString() + " .priechodzi() = ", p.priechodzi());  // true
        }
        {
            Labyrint p = new Labyrint(new Integer[][]
                           {{1, 2, 9, 10},
                            {4, 3, 8, 11},
                            {5, 6, 7, 12}});
            assertTrue(p.toString() + " .priechodzi() = " , p.priechodzi());  // true
        }
        {
            Labyrint p = new Labyrint(new Integer[][]
                           {{1, 2, 9, 8},
                            {6, 3, 10, 7},
                            {5, 4, 11, 12}});
            assertTrue(p.toString() + " .priechodzi() = " , p.priechodzi());  // true
        }
        {
            Labyrint p = new Labyrint(new Integer[][]
                           {{1, 2, 11, 10},
                            {4, 3, 8, 9},
                            {5, 6, 7, 12}});
            assertFalse(p.toString() + " .priechodzi() = " , p.priechodzi());  // false
        }
        {
            Labyrint p = new Labyrint(new Integer[][]
                           {{1, 2},
                            {3, 4}});
            assertFalse(p.toString() + " .priechodzi() = ", p.priechodzi());  // false
        }
        {
            Labyrint p = new Labyrint(new Integer[][]
                           {{  1,  2,  8},
                            {  6,  3,  7},
                            {  5,  4,  9}});
            assertTrue(p.toString() + " .priechodzi() = ", p.priechodzi());  // true
        }
        {
            Labyrint p = new Labyrint(new Integer[][]
                           {{  1,  2,  3},
                            {  5,  6,  4},
                            {  8,  7,  9}});
            assertTrue(p.toString() + " .priechodzi() = ", p.priechodzi());  // true
        }
        scoring.updateScore("lang:common_list_test_scoring_name", 30);
    }

    @Test
    public void testLabyrint() {
        new Labyrint(1,1);
        new Labyrint(1,2);
        new Labyrint(2,1);
        try {
            new Labyrint(2, 2);
        } catch (NedaSa e) {  }
        new Labyrint(3,3);
        new Labyrint(3,4);
        new Labyrint(4,3);
        try {
            new Labyrint(4,4);
        } catch (NedaSa e) {  }

        for(int M = 1; M < 11; M++) {
            for(int N = 1; N < 11; N++) {
                try {
                    var lab = new Labyrint(M, N);
                    if (lab == null || lab.lab == null)
                        fail("Labyrint(" + M + "," + N + ") vygeneroval null object, alebo lab=null");
                    if (!(lab.lab[0][0] == 1 && lab.lab[M-1][N-1] == M * N && foolsDay(lab.lab, 0,0, 1)))
                        fail("Labyrint(" + M + "," + N + ") vygeneroval nepriechodzi labyrint");
                } catch (NedaSa e) {
                    if (((M-1)*(N+1)&1)==0)
                        fail("Labyrint(" + M + "," + N + ") nevygeneroval priechodzi labyrint, a pritom existuje");
                }
            }
        }
        scoring.updateScore("lang:common_list_test_scoring_name", 35);
    }
                                                                                                                                                                  private boolean foolsDay(Integer[][]lab, int i, int j, Integer next) { return (lab[i][j].equals(next))&& ((i == lab.length-1 && j == lab[i].length-1 && lab[i][j].equals(next))|| foolsDay(lab,(i+1)%lab.length,j, next+1) ||  foolsDay(lab,Math.floorMod(i-1,lab.length),j,next+1) ||  foolsDay(lab,i,(j+1)%lab[i].length,next+1) || foolsDay(lab,i,Math.floorMod(j-1,lab[i].length),next+1)); /* kod vzdy piste s vedomim, ze ten, kto ho bude citat, moze byt psychopat, ktory pozna vasu adresu :)  */ }

}