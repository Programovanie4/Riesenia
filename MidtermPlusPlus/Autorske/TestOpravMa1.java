import LISTTestScoring.LISTTestScoring;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.*;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class TestOpravMa1 {
    private static LISTTestScoring scoring = null;
    @BeforeClass
    public static void initScoring() {
        scoring = new LISTTestScoring();
        scoring.setScore("lang:common_list_test_scoring_name", 0, 100);
    }

    Random rnd = new Random();

    @Test
    public void testUloha1() {
        for(int pokus = 0; pokus < 1000; pokus++) {
            int[] s = randomIntArray();
            int[] r = Arrays.copyOf(s, s.length);
            OpravMa.Uloha1(s);
            int min = Integer.MIN_VALUE;
            for (int i = 0; i < s.length; i++)
                if (min <= s[i])
                    min = s[i];
                else
                    fail("U1-1 pole nie je utriedene");
            Arrays.sort(s);
            Arrays.sort(r);
            assertArrayEquals("U1-2 vysledne pole ma ine hodnoty ako povodne", r, s);
        }
        scoring.updateScore("lang:common_list_test_scoring_name",   20.0D);
    }
    private int[] randomIntArray() {
        int len = 1+rnd.nextInt(100);
        int[]s = new int[len];
        for(int i = 0; i<len; i++) s[i] = rnd.nextInt(255);
        return s;
    }
}