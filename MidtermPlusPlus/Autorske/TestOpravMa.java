import LISTTestScoring.LISTTestScoring;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class TestOpravMa {
    private static LISTTestScoring scoring = null;
    @BeforeClass
    public static void initScoring() {
        scoring = new LISTTestScoring();
        scoring.setScore("lang:common_list_test_scoring_name", 0, 100);
    }


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
    @Test
    public void testUloha2() {
        List<List<Integer>> list = OpravMa.Uloha2(10);
        for(int i = 0; i<list.size(); i++) {
            assertEquals("U2-1 i+1 == list.get(i).size()", i+1, list.get(i).size());
            assertEquals("U2-2 list.get(i) == {0..i}",
                    IntStream.range(0,i+1).boxed().collect(Collectors.toList()),
                    list.get(i));
        }
        scoring.updateScore("lang:common_list_test_scoring_name",   20.0D);
    }

    @Test
    public void testUloha3() {
    // make this test great again
        var matfyz = List.of(new Bakalar(), new Magister());
        assertTrue("U3-1", matfyz.stream().map(Matfyzak::goo).collect(Collectors.toSet()).size() > 1);  // true
        assertTrue("U3-2",Matfyzak.foo() != Bakalar.foo());                    // not                  // true
        assertTrue("U3-3",Matfyzak.foo().equals(Bakalar.foo()));                                       // true
        assertTrue("U3-4",new Magister().goo() != new Bakalar().goo());        // not                  // true
        assertTrue("U3-5",!new Magister().foo().equals(new Bakalar().goo()));           // not                  // true
        assertTrue("U3-6",Profak.zoo() != Profak.zoo());                       // not                  // true
        assertTrue("U3-7",!Profak.zoo().equals(Profak.zoo()));                          // not                  // true

        scoring.updateScore("lang:common_list_test_scoring_name",   20.0D);
    }

    @Test
    public void testUloha4() {
    // make this test great again
        var b1 = new Bakalar("Palko");
        var b2 = new Bakalar("Ferko");
        var m1 = new Magister("Lukas");
        var m2 = new Magister("Jozko");
        assertEquals("U4-1", 2, new TreeSet<>(List.of(m1, m2)).size());
        assertEquals("U4-2", m2, new TreeSet<>(List.of(m1, m2)).first());
        assertEquals("U4-3", 1, new TreeSet<>(List.of(b1, b2)).size());

        assertEquals("U4-4", 3, new HashSet<>(List.of(b1, b2, m1, m2)).size());
        scoring.updateScore("lang:common_list_test_scoring_name",   20.0D);
    }

    @Test
    public void testUloha5() {
        try {
            OpravMa.Uloha5(null);
            fail("U5-1 mala byt throw new Exception()");
        } catch (Exception e) {
            // looks ok
        }
        try {
            OpravMa.Uloha5(new String[][]{ null, null });
            fail("U5-2 mala byt throw new Exception()");
        } catch (Exception e) {
            // looks ok
        }
        try {
            OpravMa.Uloha5(new String[][]{ {null}, {null} });
            fail("U5-3 mala byt throw new Exception()");
        } catch (Exception e) {
            // looks ok
        }
        try {
            OpravMa.Uloha5(new String[][]{ {randomString()}, {null} });
            fail("U5-4 mala byt throw new Exception()");
        } catch (Exception e) {
            // looks ok
        }
        try {
            OpravMa.Uloha5(new String[][]{ {randomString()}, {randomString(), randomString()} });
            fail("U5-5 mala byt throw new Exception()");
        } catch (Exception e) {
            // looks ok
        }
        scoring.updateScore("lang:common_list_test_scoring_name",   20.0D);
    }

    private static String randomString() {
        Random rnd = new Random();
        return Integer.toHexString(rnd.nextInt());
    }
    private int[] randomIntArray() {
        Random rnd = new Random();
        int len = 1+rnd.nextInt(100);
        int[]s = new int[len];
        for(int i = 0; i<len; i++) s[i] = rnd.nextInt(255);
        return s;
    }
}