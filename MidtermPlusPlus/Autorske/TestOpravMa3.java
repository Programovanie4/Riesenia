import LISTTestScoring.LISTTestScoring;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class TestOpravMa3 {
    private static LISTTestScoring scoring = null;
    @BeforeClass
    public static void initScoring() {
        scoring = new LISTTestScoring();
        scoring.setScore("lang:common_list_test_scoring_name", 0, 100);
    }

    Random rnd = new Random();

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
}