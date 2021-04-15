import LISTTestScoring.LISTTestScoring;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.*;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class TestOpravMa4 {
    private static LISTTestScoring scoring = null;
    @BeforeClass
    public static void initScoring() {
        scoring = new LISTTestScoring();
        scoring.setScore("lang:common_list_test_scoring_name", 0, 100);
    }

    Random rnd = new Random();

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
}