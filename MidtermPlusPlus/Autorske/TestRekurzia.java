import LISTTestScoring.LISTTestScoring;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class TestRekurzia {
    private static LISTTestScoring scoring = null;
    @BeforeClass
    public static void initScoring() {
        scoring = new LISTTestScoring();
        scoring.setScore("lang:common_list_test_scoring_name", 0, 100);
    }

    @Test
    public void foo() {
        assertTrue("foo(0,0)" , 1L == Rekurzia.foo(0,0) );
        assertTrue("foo(1,1)" , 3L == Rekurzia.foo(1,1) );
        assertTrue("foo(2,2)" , 26L == Rekurzia.foo(2,2) );
        assertTrue("foo(3,3)" , 252L == Rekurzia.foo(3,3) );
        assertTrue("foo(4,4)" , 2568L == Rekurzia.foo(4,4) );
        assertTrue("foo(5,5)" , 26928L == Rekurzia.foo(5,5) );
        assertTrue("foo(6,6)" , 287648L == Rekurzia.foo(6,6) );
        assertTrue("foo(7,7)" , 3112896L == Rekurzia.foo(7,7) );
        assertTrue("foo(8,8)" , 34013312L == Rekurzia.foo(8,8) );
        assertTrue("foo(9,9)" , 374416128L == Rekurzia.foo(9,9) );
        assertTrue("foo(10,10)" , 4145895936L == Rekurzia.foo(10,10) );
        assertTrue("foo(11,11)" , 46127840256L == Rekurzia.foo(11,11) );
        assertTrue("foo(12,12)" , 515268544512L == Rekurzia.foo(12,12) );
        assertTrue("foo(13,13)" , 5775088103424L == Rekurzia.foo(13,13) );
        assertTrue("foo(14,14)" , 64912164888576L == Rekurzia.foo(14,14) );
        assertTrue("foo(15,15)" , 731420783788032L == Rekurzia.foo(15,15) );

        scoring.updateScore("lang:common_list_test_scoring_name",   25.0D);
    }
    @Test
    public void kontrapriklad1() {
        assertTrue("kontrapriklad1()", 2 == Rekurzia.kontrapriklad1());
        scoring.updateScore("lang:common_list_test_scoring_name",   25.0D);
    }

    @Test
    public void kontrapriklad2() {
        assertTrue("kontrapriklad2()", -1 == Rekurzia.kontrapriklad2());
        scoring.updateScore("lang:common_list_test_scoring_name",   25.0D);
    }

    @Test
    public void maxHodnota() {
        long m =  Rekurzia.maxHodnota();
        if (m == 9102433660539240448L) {
            System.out.println("maxHodnota() - gratulujem, maximum pre (8,35)");
            scoring.updateScore("lang:common_list_test_scoring_name", 25.0D);
        } else if (List.of(
                8685503036038053888L,
                8431430126387855360L,
                6208276560896589816L,
                7451345823314477024L,
                5804592721211424734L,
                6731088911163981824L,
                7858726569219457024L,
                8054780324191666176L,
                5993637112178540512L,
                6775628889138921280L,
                6420558162641813504L,
                6731995342327234914L,
                7173214962813231792L,
                8914842299326791680L,
                5312488023059857408L,
                7593636610193752064L,
                7062015575769219072L,
                5500724732367994880L,
                5061817282745860096L,
                7339741492707065856L,
                7178737806028570624L,
                8646911284551352320L)
                .contains(m)) {
            System.out.println("maxHodnota() - slusny odhad, ale maximum je vyssie, ciastocne bodujes");
            scoring.updateScore("lang:common_list_test_scoring_name",   15.0D);
        } else {
            fail("maxHodnota() - sorry, maximum je vyssie");
        }
    }
}