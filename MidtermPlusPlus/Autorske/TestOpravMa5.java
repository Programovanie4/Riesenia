import LISTTestScoring.LISTTestScoring;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.*;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class TestOpravMa5 {
    private static LISTTestScoring scoring = null;
    @BeforeClass
    public static void initScoring() {
        scoring = new LISTTestScoring();
        scoring.setScore("lang:common_list_test_scoring_name", 0, 100);
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

        try {
            OpravMa.Uloha5(new String[][]{ {randomString(), randomString()}, {randomString(), randomString()} });
            // looks ok
        } catch (Exception e) {
            fail("U5-6 nemala byt throw new Exception()");
        }
        try {
            OpravMa.Uloha5(new String[][]{ {randomString()}, {randomString()} });
            // looks ok
        } catch (Exception e) {
            fail("U5-7 nemala byt throw new Exception()");
        }
        try {
            OpravMa.Uloha5(new String[][]{ {randomString()} });
            // looks ok
        } catch (Exception e) {
            fail("U5-8 nemala byt throw new Exception()");
        }
        try {
            OpravMa.Uloha5(new String[][]{{}});
            // looks ok
        } catch (Exception e) {
            fail("U5-9 nemala byt throw new Exception()");
        }

        scoring.updateScore("lang:common_list_test_scoring_name",   20.0D);
    }

    private static String randomString() {
        Random rnd = new Random();
        return Integer.toHexString(rnd.nextInt());
    }
}