import LISTTestScoring.LISTTestScoring;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class TestOpravMa2 {
    private static LISTTestScoring scoring = null;
    @BeforeClass
    public static void initScoring() {
        scoring = new LISTTestScoring();
        scoring.setScore("lang:common_list_test_scoring_name", 0, 100);
    }

    Random rnd = new Random();

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
}