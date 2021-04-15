import LISTTestScoring.LISTTestScoring;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;

public class TestGraph {

    private static LISTTestScoring scoring = null;
    @BeforeClass
    public static void initScoring() {
        scoring = new LISTTestScoring();
        scoring.setScore("lang:common_list_test_scoring_name", 0, 100);
    }
    // automaticky generovane testy ----------------
    @Test
    public void test_pramene() {
        {//g={A=[E], B=[C, F, I], C=[], D=[A], E=[A, B, D], F=[], G=[C, D, I], H=[B, C, F, J], I=[E, F, G], J=[C]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'E'),'B',Set.of( 'C','F','I'),'C',Set.of(),'D',Set.of( 'A'),'E',Set.of( 'A','B','D'),'F',Set.of(),'G',Set.of( 'C','D','I'),'H',Set.of( 'B','C','F','J'),'I',Set.of( 'E','F','G'),'J',Set.of( 'C')));
            assertEquals("pramene/Test_pramene", Set.of( 'H'), g.pramene());
        }
        {//g={A=[B, J], B=[I], C=[J], D=[], E=[A, G], F=[E, H, I], G=[], H=[B, D], I=[], J=[A]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'B','J'),'B',Set.of( 'I'),'C',Set.of( 'J'),'D',Set.of(),'E',Set.of( 'A','G'),'F',Set.of( 'E','H','I'),'G',Set.of(),'H',Set.of( 'B','D'),'I',Set.of(),'J',Set.of( 'A')));
            assertEquals("pramene/Test_pramene", Set.of( 'C','F'), g.pramene());
        }
        {//g={A=[F], B=[I], C=[A, B], D=[B], E=[B, C, F], F=[A, H], G=[], H=[], I=[D, J], J=[]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'F'),'B',Set.of( 'I'),'C',Set.of( 'A','B'),'D',Set.of( 'B'),'E',Set.of( 'B','C','F'),'F',Set.of( 'A','H'),'G',Set.of(),'H',Set.of(),'I',Set.of( 'D','J'),'J',Set.of()));
            assertEquals("pramene/Test_pramene", Set.of( 'E','G'), g.pramene());
        }
        {//g={A=[E, H, J], B=[], C=[], D=[], E=[B], F=[C, E, H, I], G=[C, D, E, J], H=[A, B, G], I=[A, B, G, J], J=[B, C, D]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'E','H','J'),'B',Set.of(),'C',Set.of(),'D',Set.of(),'E',Set.of( 'B'),'F',Set.of( 'C','E','H','I'),'G',Set.of( 'C','D','E','J'),'H',Set.of( 'A','B','G'),'I',Set.of( 'A','B','G','J'),'J',Set.of( 'B','C','D')));
            assertEquals("pramene/Test_pramene", Set.of( 'F'), g.pramene());
        }
        {//g={A=[H, I, J], B=[E], C=[H, J], D=[C, F, G, I], E=[F, J], F=[B, C, D], G=[H, J], H=[F], I=[C], J=[F]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'H','I','J'),'B',Set.of( 'E'),'C',Set.of( 'H','J'),'D',Set.of( 'C','F','G','I'),'E',Set.of( 'F','J'),'F',Set.of( 'B','C','D'),'G',Set.of( 'H','J'),'H',Set.of( 'F'),'I',Set.of( 'C'),'J',Set.of( 'F')));
            assertEquals("pramene/Test_pramene", Set.of( 'A'), g.pramene());
        }
        {//g={A=[C, D, F], B=[F], C=[A, D, I], D=[E, F], E=[A, B, C, H], F=[], G=[C], H=[A], I=[H], J=[E, F, G]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'C','D','F'),'B',Set.of( 'F'),'C',Set.of( 'A','D','I'),'D',Set.of( 'E','F'),'E',Set.of( 'A','B','C','H'),'F',Set.of(),'G',Set.of( 'C'),'H',Set.of( 'A'),'I',Set.of( 'H'),'J',Set.of( 'E','F','G')));
            assertEquals("pramene/Test_pramene", Set.of( 'J'), g.pramene());
        }
        {//g={A=[], B=[H], C=[G, J], D=[B, J], E=[H], F=[C, D, E], G=[I], H=[], I=[B, C, J], J=[B]}
            var g = new Graph<Character>(Map.of( 'A',Set.of(),'B',Set.of( 'H'),'C',Set.of( 'G','J'),'D',Set.of( 'B','J'),'E',Set.of( 'H'),'F',Set.of( 'C','D','E'),'G',Set.of( 'I'),'H',Set.of(),'I',Set.of( 'B','C','J'),'J',Set.of( 'B')));
            assertEquals("pramene/Test_pramene", Set.of( 'A','F'), g.pramene());
        }
        {//g={A=[D, E], B=[H], C=[], D=[F, G], E=[], F=[A, E], G=[I], H=[A, B, F], I=[C, J], J=[A, F, I]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'D','E'),'B',Set.of( 'H'),'C',Set.of(),'D',Set.of( 'F','G'),'E',Set.of(),'F',Set.of( 'A','E'),'G',Set.of( 'I'),'H',Set.of( 'A','B','F'),'I',Set.of( 'C','J'),'J',Set.of( 'A','F','I')));
            assertEquals("pramene/Test_pramene", Set.of(), g.pramene());
        }
        {//g={A=[B, C, F, I], B=[F, I, J], C=[D, G], D=[C, H, I], E=[C, F, I], F=[B], G=[], H=[A, B], I=[A, F, J], J=[B, D]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'B','C','F','I'),'B',Set.of( 'F','I','J'),'C',Set.of( 'D','G'),'D',Set.of( 'C','H','I'),'E',Set.of( 'C','F','I'),'F',Set.of( 'B'),'G',Set.of(),'H',Set.of( 'A','B'),'I',Set.of( 'A','F','J'),'J',Set.of( 'B','D')));
            assertEquals("pramene/Test_pramene", Set.of( 'E'), g.pramene());
        }
        {//g={A=[], B=[F, G, H, J], C=[G, H], D=[A, J], E=[A, H], F=[B], G=[B, C, D], H=[], I=[D, F], J=[]}
            var g = new Graph<Character>(Map.of( 'A',Set.of(),'B',Set.of( 'F','G','H','J'),'C',Set.of( 'G','H'),'D',Set.of( 'A','J'),'E',Set.of( 'A','H'),'F',Set.of( 'B'),'G',Set.of( 'B','C','D'),'H',Set.of(),'I',Set.of( 'D','F'),'J',Set.of()));
            assertEquals("pramene/Test_pramene", Set.of( 'E','I'), g.pramene());
        }
        {//g={A=[G], B=[A, E], C=[D], D=[E], E=[G, J], F=[B, G, I, J], G=[J], H=[], I=[B, F, J], J=[A, B, C, H]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'G'),'B',Set.of( 'A','E'),'C',Set.of( 'D'),'D',Set.of( 'E'),'E',Set.of( 'G','J'),'F',Set.of( 'B','G','I','J'),'G',Set.of( 'J'),'H',Set.of(),'I',Set.of( 'B','F','J'),'J',Set.of( 'A','B','C','H')));
            assertEquals("pramene/Test_pramene", Set.of(), g.pramene());
        }
        {//g={A=[C, F], B=[], C=[B, D, F, H], D=[], E=[I, J], F=[], G=[], H=[], I=[J], J=[A, C, H]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'C','F'),'B',Set.of(),'C',Set.of( 'B','D','F','H'),'D',Set.of(),'E',Set.of( 'I','J'),'F',Set.of(),'G',Set.of(),'H',Set.of(),'I',Set.of( 'J'),'J',Set.of( 'A','C','H')));
            assertEquals("pramene/Test_pramene", Set.of( 'E','G'), g.pramene());
        }
        {//g={A=[C, I, J], B=[E, F, H, J], C=[I], D=[A, E, J], E=[H], F=[A], G=[], H=[J], I=[C, E, F], J=[G]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'C','I','J'),'B',Set.of( 'E','F','H','J'),'C',Set.of( 'I'),'D',Set.of( 'A','E','J'),'E',Set.of( 'H'),'F',Set.of( 'A'),'G',Set.of(),'H',Set.of( 'J'),'I',Set.of( 'C','E','F'),'J',Set.of( 'G')));
            assertEquals("pramene/Test_pramene", Set.of( 'B','D'), g.pramene());
        }
        {//g={A=[], B=[], C=[B], D=[E, J], E=[], F=[], G=[D, E, F, I], H=[I], I=[B, C], J=[C, H]}
            var g = new Graph<Character>(Map.of( 'A',Set.of(),'B',Set.of(),'C',Set.of( 'B'),'D',Set.of( 'E','J'),'E',Set.of(),'F',Set.of(),'G',Set.of( 'D','E','F','I'),'H',Set.of( 'I'),'I',Set.of( 'B','C'),'J',Set.of( 'C','H')));
            assertEquals("pramene/Test_pramene", Set.of( 'A','G'), g.pramene());
        }
        {//g={A=[D, E, G, I], B=[A], C=[], D=[G], E=[H, I], F=[], G=[C], H=[D, E], I=[A, E, J], J=[B, H]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'D','E','G','I'),'B',Set.of( 'A'),'C',Set.of(),'D',Set.of( 'G'),'E',Set.of( 'H','I'),'F',Set.of(),'G',Set.of( 'C'),'H',Set.of( 'D','E'),'I',Set.of( 'A','E','J'),'J',Set.of( 'B','H')));
            assertEquals("pramene/Test_pramene", Set.of( 'F'), g.pramene());
        }
        {//g={A=[C, G], B=[], C=[B, D], D=[C, F], E=[], F=[G], G=[D], H=[], I=[A, D, J], J=[C, D]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'C','G'),'B',Set.of(),'C',Set.of( 'B','D'),'D',Set.of( 'C','F'),'E',Set.of(),'F',Set.of( 'G'),'G',Set.of( 'D'),'H',Set.of(),'I',Set.of( 'A','D','J'),'J',Set.of( 'C','D')));
            assertEquals("pramene/Test_pramene", Set.of( 'E','H','I'), g.pramene());
        }
        {//g={A=[], B=[E, F, H], C=[A, D, E, G], D=[G], E=[B, G, J], F=[H], G=[C, H, J], H=[B, J], I=[A, C, G], J=[A]}
            var g = new Graph<Character>(Map.of( 'A',Set.of(),'B',Set.of( 'E','F','H'),'C',Set.of( 'A','D','E','G'),'D',Set.of( 'G'),'E',Set.of( 'B','G','J'),'F',Set.of( 'H'),'G',Set.of( 'C','H','J'),'H',Set.of( 'B','J'),'I',Set.of( 'A','C','G'),'J',Set.of( 'A')));
            assertEquals("pramene/Test_pramene", Set.of( 'I'), g.pramene());
        }
        {//g={A=[F, H, I], B=[I], C=[G, H, I, J], D=[A, J], E=[B, D, G], F=[], G=[], H=[D, E], I=[C, F, J], J=[]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'F','H','I'),'B',Set.of( 'I'),'C',Set.of( 'G','H','I','J'),'D',Set.of( 'A','J'),'E',Set.of( 'B','D','G'),'F',Set.of(),'G',Set.of(),'H',Set.of( 'D','E'),'I',Set.of( 'C','F','J'),'J',Set.of()));
            assertEquals("pramene/Test_pramene", Set.of(), g.pramene());
        }
        {//g={A=[H], B=[H], C=[A, B, I, J], D=[A], E=[D], F=[], G=[D, I], H=[G], I=[], J=[G, H, I]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'H'),'B',Set.of( 'H'),'C',Set.of( 'A','B','I','J'),'D',Set.of( 'A'),'E',Set.of( 'D'),'F',Set.of(),'G',Set.of( 'D','I'),'H',Set.of( 'G'),'I',Set.of(),'J',Set.of( 'G','H','I')));
            assertEquals("pramene/Test_pramene", Set.of( 'C','E','F'), g.pramene());
        }
        {//g={A=[F, I], B=[A, G], C=[A, H], D=[C], E=[B, G, H], F=[G, I], G=[F], H=[B, D, I], I=[], J=[A, B]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'F','I'),'B',Set.of( 'A','G'),'C',Set.of( 'A','H'),'D',Set.of( 'C'),'E',Set.of( 'B','G','H'),'F',Set.of( 'G','I'),'G',Set.of( 'F'),'H',Set.of( 'B','D','I'),'I',Set.of(),'J',Set.of( 'A','B')));
            assertEquals("pramene/Test_pramene", Set.of( 'E','J'), g.pramene());
        }
        {//g={A=[D], B=[E, G], C=[A, D, F], D=[A, C], E=[J], F=[E], G=[C, I, J], H=[B, E, F], I=[A, B, D, E], J=[H, I]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'D'),'B',Set.of( 'E','G'),'C',Set.of( 'A','D','F'),'D',Set.of( 'A','C'),'E',Set.of( 'J'),'F',Set.of( 'E'),'G',Set.of( 'C','I','J'),'H',Set.of( 'B','E','F'),'I',Set.of( 'A','B','D','E'),'J',Set.of( 'H','I')));
            assertEquals("pramene/Test_pramene", Set.of(), g.pramene());
        }
        {//g={A=[C, D, E, F], B=[A, F], C=[G], D=[G, H], E=[J], F=[E, H, I], G=[J], H=[A, D, G], I=[A, B, F, G], J=[A, B]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'C','D','E','F'),'B',Set.of( 'A','F'),'C',Set.of( 'G'),'D',Set.of( 'G','H'),'E',Set.of( 'J'),'F',Set.of( 'E','H','I'),'G',Set.of( 'J'),'H',Set.of( 'A','D','G'),'I',Set.of( 'A','B','F','G'),'J',Set.of( 'A','B')));
            assertEquals("pramene/Test_pramene", Set.of(), g.pramene());
        }
        {//g={A=[C], B=[D, H], C=[], D=[E], E=[B, F, G, I], F=[A], G=[A, B, D], H=[B, J], I=[D], J=[]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'C'),'B',Set.of( 'D','H'),'C',Set.of(),'D',Set.of( 'E'),'E',Set.of( 'B','F','G','I'),'F',Set.of( 'A'),'G',Set.of( 'A','B','D'),'H',Set.of( 'B','J'),'I',Set.of( 'D'),'J',Set.of()));
            assertEquals("pramene/Test_pramene", Set.of(), g.pramene());
        }
        {//g={A=[], B=[A, G], C=[E, J], D=[A, B, G, I], E=[], F=[A, G, H], G=[J], H=[B, D, E], I=[F, G, J], J=[A, B, H]}
            var g = new Graph<Character>(Map.of( 'A',Set.of(),'B',Set.of( 'A','G'),'C',Set.of( 'E','J'),'D',Set.of( 'A','B','G','I'),'E',Set.of(),'F',Set.of( 'A','G','H'),'G',Set.of( 'J'),'H',Set.of( 'B','D','E'),'I',Set.of( 'F','G','J'),'J',Set.of( 'A','B','H')));
            assertEquals("pramene/Test_pramene", Set.of( 'C'), g.pramene());
        }
        {//g={A=[], B=[G, J], C=[A, H, J], D=[B, H], E=[D, F], F=[H, I], G=[E], H=[], I=[A, C, D], J=[E]}
            var g = new Graph<Character>(Map.of( 'A',Set.of(),'B',Set.of( 'G','J'),'C',Set.of( 'A','H','J'),'D',Set.of( 'B','H'),'E',Set.of( 'D','F'),'F',Set.of( 'H','I'),'G',Set.of( 'E'),'H',Set.of(),'I',Set.of( 'A','C','D'),'J',Set.of( 'E')));
            assertEquals("pramene/Test_pramene", Set.of(), g.pramene());
        }
        {//g={A=[B, F, I], B=[D, G, H], C=[F], D=[A, B, E], E=[], F=[G, I], G=[A, E], H=[G, J], I=[C], J=[]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'B','F','I'),'B',Set.of( 'D','G','H'),'C',Set.of( 'F'),'D',Set.of( 'A','B','E'),'E',Set.of(),'F',Set.of( 'G','I'),'G',Set.of( 'A','E'),'H',Set.of( 'G','J'),'I',Set.of( 'C'),'J',Set.of()));
            assertEquals("pramene/Test_pramene", Set.of(), g.pramene());
        }
        {//g={A=[], B=[A, J], C=[], D=[H, J], E=[F, I], F=[C, G], G=[B, C, I], H=[C, J], I=[], J=[A, C, G]}
            var g = new Graph<Character>(Map.of( 'A',Set.of(),'B',Set.of( 'A','J'),'C',Set.of(),'D',Set.of( 'H','J'),'E',Set.of( 'F','I'),'F',Set.of( 'C','G'),'G',Set.of( 'B','C','I'),'H',Set.of( 'C','J'),'I',Set.of(),'J',Set.of( 'A','C','G')));
            assertEquals("pramene/Test_pramene", Set.of( 'D','E'), g.pramene());
        }
        {//g={A=[E], B=[A, I], C=[E], D=[C], E=[], F=[], G=[A], H=[D], I=[], J=[]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'E'),'B',Set.of( 'A','I'),'C',Set.of( 'E'),'D',Set.of( 'C'),'E',Set.of(),'F',Set.of(),'G',Set.of( 'A'),'H',Set.of( 'D'),'I',Set.of(),'J',Set.of()));
            assertEquals("pramene/Test_pramene", Set.of( 'B','F','G','H','J'), g.pramene());
        }
        {//g={A=[J], B=[F, H, J], C=[B, G], D=[E, J], E=[], F=[], G=[], H=[B, I], I=[E, F], J=[B]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'J'),'B',Set.of( 'F','H','J'),'C',Set.of( 'B','G'),'D',Set.of( 'E','J'),'E',Set.of(),'F',Set.of(),'G',Set.of(),'H',Set.of( 'B','I'),'I',Set.of( 'E','F'),'J',Set.of( 'B')));
            assertEquals("pramene/Test_pramene", Set.of( 'A','C','D'), g.pramene());
        }
        {//g={A=[C, I, J], B=[G, I, J], C=[E], D=[A, C], E=[G, J], F=[B, I], G=[J], H=[F, G, I], I=[G], J=[C, F]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'C','I','J'),'B',Set.of( 'G','I','J'),'C',Set.of( 'E'),'D',Set.of( 'A','C'),'E',Set.of( 'G','J'),'F',Set.of( 'B','I'),'G',Set.of( 'J'),'H',Set.of( 'F','G','I'),'I',Set.of( 'G'),'J',Set.of( 'C','F')));
            assertEquals("pramene/Test_pramene", Set.of( 'D','H'), g.pramene());
        }
        {//g={A=[], B=[A, C, J], C=[H, I], D=[H, I, J], E=[], F=[G], G=[F], H=[E, F, J], I=[D, H], J=[H]}
            var g = new Graph<Character>(Map.of( 'A',Set.of(),'B',Set.of( 'A','C','J'),'C',Set.of( 'H','I'),'D',Set.of( 'H','I','J'),'E',Set.of(),'F',Set.of( 'G'),'G',Set.of( 'F'),'H',Set.of( 'E','F','J'),'I',Set.of( 'D','H'),'J',Set.of( 'H')));
            assertEquals("pramene/Test_pramene", Set.of( 'B'), g.pramene());
        }
        {//g={A=[], B=[H], C=[A, D, F, H], D=[A, B, H], E=[A], F=[C], G=[I], H=[C], I=[C, J], J=[B, E, F]}
            var g = new Graph<Character>(Map.of( 'A',Set.of(),'B',Set.of( 'H'),'C',Set.of( 'A','D','F','H'),'D',Set.of( 'A','B','H'),'E',Set.of( 'A'),'F',Set.of( 'C'),'G',Set.of( 'I'),'H',Set.of( 'C'),'I',Set.of( 'C','J'),'J',Set.of( 'B','E','F')));
            assertEquals("pramene/Test_pramene", Set.of( 'G'), g.pramene());
        }
        {//g={A=[C, D, J], B=[D], C=[A, J], D=[B, E, F, I], E=[], F=[I], G=[], H=[A, D, J], I=[J], J=[D]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'C','D','J'),'B',Set.of( 'D'),'C',Set.of( 'A','J'),'D',Set.of( 'B','E','F','I'),'E',Set.of(),'F',Set.of( 'I'),'G',Set.of(),'H',Set.of( 'A','D','J'),'I',Set.of( 'J'),'J',Set.of( 'D')));
            assertEquals("pramene/Test_pramene", Set.of( 'G','H'), g.pramene());
        }
        {//g={A=[], B=[A], C=[H], D=[A, C, J], E=[D, H, J], F=[A, B, D], G=[J], H=[D, E, F], I=[E], J=[A]}
            var g = new Graph<Character>(Map.of( 'A',Set.of(),'B',Set.of( 'A'),'C',Set.of( 'H'),'D',Set.of( 'A','C','J'),'E',Set.of( 'D','H','J'),'F',Set.of( 'A','B','D'),'G',Set.of( 'J'),'H',Set.of( 'D','E','F'),'I',Set.of( 'E'),'J',Set.of( 'A')));
            assertEquals("pramene/Test_pramene", Set.of( 'G','I'), g.pramene());
        }
        {//g={A=[C, G, J], B=[J], C=[], D=[B], E=[C, D, I], F=[A, B, G], G=[], H=[F], I=[E, F, H], J=[A, E]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'C','G','J'),'B',Set.of( 'J'),'C',Set.of(),'D',Set.of( 'B'),'E',Set.of( 'C','D','I'),'F',Set.of( 'A','B','G'),'G',Set.of(),'H',Set.of( 'F'),'I',Set.of( 'E','F','H'),'J',Set.of( 'A','E')));
            assertEquals("pramene/Test_pramene", Set.of(), g.pramene());
        }
        {//g={A=[B, E], B=[A, D, J], C=[D, F], D=[C, G], E=[B, D], F=[], G=[B], H=[E, I], I=[], J=[]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'B','E'),'B',Set.of( 'A','D','J'),'C',Set.of( 'D','F'),'D',Set.of( 'C','G'),'E',Set.of( 'B','D'),'F',Set.of(),'G',Set.of( 'B'),'H',Set.of( 'E','I'),'I',Set.of(),'J',Set.of()));
            assertEquals("pramene/Test_pramene", Set.of( 'H'), g.pramene());
        }
        {//g={A=[B, F, G], B=[A], C=[A, D], D=[E], E=[], F=[C, J], G=[B, C, I], H=[B, E, I], I=[D], J=[]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'B','F','G'),'B',Set.of( 'A'),'C',Set.of( 'A','D'),'D',Set.of( 'E'),'E',Set.of(),'F',Set.of( 'C','J'),'G',Set.of( 'B','C','I'),'H',Set.of( 'B','E','I'),'I',Set.of( 'D'),'J',Set.of()));
            assertEquals("pramene/Test_pramene", Set.of( 'H'), g.pramene());
        }
        {//g={A=[], B=[F], C=[A, G], D=[J], E=[A, D], F=[B, D, H, J], G=[E, J], H=[G], I=[], J=[C, D, F, I]}
            var g = new Graph<Character>(Map.of( 'A',Set.of(),'B',Set.of( 'F'),'C',Set.of( 'A','G'),'D',Set.of( 'J'),'E',Set.of( 'A','D'),'F',Set.of( 'B','D','H','J'),'G',Set.of( 'E','J'),'H',Set.of( 'G'),'I',Set.of(),'J',Set.of( 'C','D','F','I')));
            assertEquals("pramene/Test_pramene", Set.of(), g.pramene());
        }
        {//g={A=[B], B=[A], C=[D], D=[], E=[F], F=[B], G=[H], H=[D, I, J], I=[A, G, J], J=[C, I]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'B'),'B',Set.of( 'A'),'C',Set.of( 'D'),'D',Set.of(),'E',Set.of( 'F'),'F',Set.of( 'B'),'G',Set.of( 'H'),'H',Set.of( 'D','I','J'),'I',Set.of( 'A','G','J'),'J',Set.of( 'C','I')));
            assertEquals("pramene/Test_pramene", Set.of( 'E'), g.pramene());
        }
        {//g={A=[B, C], B=[E, H, I], C=[D, I], D=[B, C, H], E=[C, F, G], F=[C], G=[], H=[B], I=[], J=[C, H]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'B','C'),'B',Set.of( 'E','H','I'),'C',Set.of( 'D','I'),'D',Set.of( 'B','C','H'),'E',Set.of( 'C','F','G'),'F',Set.of( 'C'),'G',Set.of(),'H',Set.of( 'B'),'I',Set.of(),'J',Set.of( 'C','H')));
            assertEquals("pramene/Test_pramene", Set.of( 'A','J'), g.pramene());
        }
        {//g={A=[], B=[A, D], C=[E, G, H], D=[I], E=[A, C, G], F=[B, I, J], G=[C, I], H=[C, E, I], I=[], J=[F]}
            var g = new Graph<Character>(Map.of( 'A',Set.of(),'B',Set.of( 'A','D'),'C',Set.of( 'E','G','H'),'D',Set.of( 'I'),'E',Set.of( 'A','C','G'),'F',Set.of( 'B','I','J'),'G',Set.of( 'C','I'),'H',Set.of( 'C','E','I'),'I',Set.of(),'J',Set.of( 'F')));
            assertEquals("pramene/Test_pramene", Set.of(), g.pramene());
        }
        {//g={A=[C, D, I], B=[H], C=[], D=[A, B, C], E=[D], F=[A, E], G=[], H=[C, J], I=[A, B, H], J=[D, E]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'C','D','I'),'B',Set.of( 'H'),'C',Set.of(),'D',Set.of( 'A','B','C'),'E',Set.of( 'D'),'F',Set.of( 'A','E'),'G',Set.of(),'H',Set.of( 'C','J'),'I',Set.of( 'A','B','H'),'J',Set.of( 'D','E')));
            assertEquals("pramene/Test_pramene", Set.of( 'F','G'), g.pramene());
        }
        {//g={A=[I, J], B=[F, I], C=[], D=[E, I], E=[], F=[B, I], G=[D, E, I], H=[], I=[D, E], J=[]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'I','J'),'B',Set.of( 'F','I'),'C',Set.of(),'D',Set.of( 'E','I'),'E',Set.of(),'F',Set.of( 'B','I'),'G',Set.of( 'D','E','I'),'H',Set.of(),'I',Set.of( 'D','E'),'J',Set.of()));
            assertEquals("pramene/Test_pramene", Set.of( 'A','C','G','H'), g.pramene());
        }
        {//g={A=[E, F], B=[A, F], C=[], D=[B, G], E=[], F=[H], G=[A, E, H], H=[A, F, I], I=[A, F, G], J=[A, F, G]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'E','F'),'B',Set.of( 'A','F'),'C',Set.of(),'D',Set.of( 'B','G'),'E',Set.of(),'F',Set.of( 'H'),'G',Set.of( 'A','E','H'),'H',Set.of( 'A','F','I'),'I',Set.of( 'A','F','G'),'J',Set.of( 'A','F','G')));
            assertEquals("pramene/Test_pramene", Set.of( 'C','D','J'), g.pramene());
        }
        {//g={A=[B, C], B=[I], C=[], D=[J], E=[D], F=[], G=[B, F], H=[D, E, F], I=[], J=[]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'B','C'),'B',Set.of( 'I'),'C',Set.of(),'D',Set.of( 'J'),'E',Set.of( 'D'),'F',Set.of(),'G',Set.of( 'B','F'),'H',Set.of( 'D','E','F'),'I',Set.of(),'J',Set.of()));
            assertEquals("pramene/Test_pramene", Set.of( 'A','G','H'), g.pramene());
        }
        {//g={A=[B, C, D], B=[], C=[A, D, F, J], D=[A, E], E=[D, F, G], F=[J], G=[B, H, J], H=[G], I=[], J=[A, H]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'B','C','D'),'B',Set.of(),'C',Set.of( 'A','D','F','J'),'D',Set.of( 'A','E'),'E',Set.of( 'D','F','G'),'F',Set.of( 'J'),'G',Set.of( 'B','H','J'),'H',Set.of( 'G'),'I',Set.of(),'J',Set.of( 'A','H')));
            assertEquals("pramene/Test_pramene", Set.of( 'I'), g.pramene());
        }
        {//g={A=[D, E, H], B=[E], C=[B, I], D=[A, I], E=[B, H], F=[A, J], G=[B, D], H=[B], I=[C], J=[D, E, G]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'D','E','H'),'B',Set.of( 'E'),'C',Set.of( 'B','I'),'D',Set.of( 'A','I'),'E',Set.of( 'B','H'),'F',Set.of( 'A','J'),'G',Set.of( 'B','D'),'H',Set.of( 'B'),'I',Set.of( 'C'),'J',Set.of( 'D','E','G')));
            assertEquals("pramene/Test_pramene", Set.of( 'F'), g.pramene());
        }
        {//g={A=[], B=[C, E], C=[B, E, G], D=[A, E], E=[B, D, G, I], F=[A, B, D], G=[A, J], H=[E, F], I=[B, G], J=[B, F]}
            var g = new Graph<Character>(Map.of( 'A',Set.of(),'B',Set.of( 'C','E'),'C',Set.of( 'B','E','G'),'D',Set.of( 'A','E'),'E',Set.of( 'B','D','G','I'),'F',Set.of( 'A','B','D'),'G',Set.of( 'A','J'),'H',Set.of( 'E','F'),'I',Set.of( 'B','G'),'J',Set.of( 'B','F')));
            assertEquals("pramene/Test_pramene", Set.of( 'H'), g.pramene());
        }
        {//g={A=[G], B=[C], C=[A, I, J], D=[A, C], E=[B, H], F=[C, H], G=[C, E], H=[A, F, I, J], I=[E, J], J=[A]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'G'),'B',Set.of( 'C'),'C',Set.of( 'A','I','J'),'D',Set.of( 'A','C'),'E',Set.of( 'B','H'),'F',Set.of( 'C','H'),'G',Set.of( 'C','E'),'H',Set.of( 'A','F','I','J'),'I',Set.of( 'E','J'),'J',Set.of( 'A')));
            assertEquals("pramene/Test_pramene", Set.of( 'D'), g.pramene());
        }
        {//g={A=[], B=[C, G], C=[I], D=[B, F, I], E=[G, J], F=[E, I], G=[A, E, I], H=[E, I], I=[], J=[C, E, G, I]}
            var g = new Graph<Character>(Map.of( 'A',Set.of(),'B',Set.of( 'C','G'),'C',Set.of( 'I'),'D',Set.of( 'B','F','I'),'E',Set.of( 'G','J'),'F',Set.of( 'E','I'),'G',Set.of( 'A','E','I'),'H',Set.of( 'E','I'),'I',Set.of(),'J',Set.of( 'C','E','G','I')));
            assertEquals("pramene/Test_pramene", Set.of( 'D','H'), g.pramene());
        }
        {//g={A=[D], B=[D], C=[D, H, I, J], D=[I, J], E=[C, H], F=[B, G, J], G=[E], H=[D, E, G], I=[A, D, E, G], J=[B]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'D'),'B',Set.of( 'D'),'C',Set.of( 'D','H','I','J'),'D',Set.of( 'I','J'),'E',Set.of( 'C','H'),'F',Set.of( 'B','G','J'),'G',Set.of( 'E'),'H',Set.of( 'D','E','G'),'I',Set.of( 'A','D','E','G'),'J',Set.of( 'B')));
            assertEquals("pramene/Test_pramene", Set.of( 'F'), g.pramene());
        }
        {//g={A=[], B=[E], C=[B, D, I], D=[B], E=[I], F=[D, J], G=[D, E], H=[I, J], I=[A, E], J=[B, H]}
            var g = new Graph<Character>(Map.of( 'A',Set.of(),'B',Set.of( 'E'),'C',Set.of( 'B','D','I'),'D',Set.of( 'B'),'E',Set.of( 'I'),'F',Set.of( 'D','J'),'G',Set.of( 'D','E'),'H',Set.of( 'I','J'),'I',Set.of( 'A','E'),'J',Set.of( 'B','H')));
            assertEquals("pramene/Test_pramene", Set.of( 'C','F','G'), g.pramene());
        }
        {//g={A=[H], B=[E, H, I], C=[D, J], D=[A, B], E=[F, H, I, J], F=[B], G=[B], H=[A, G, I], I=[G, H, J], J=[A, G]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'H'),'B',Set.of( 'E','H','I'),'C',Set.of( 'D','J'),'D',Set.of( 'A','B'),'E',Set.of( 'F','H','I','J'),'F',Set.of( 'B'),'G',Set.of( 'B'),'H',Set.of( 'A','G','I'),'I',Set.of( 'G','H','J'),'J',Set.of( 'A','G')));
            assertEquals("pramene/Test_pramene", Set.of( 'C'), g.pramene());
        }
        {//g={A=[B, C], B=[], C=[], D=[C, I], E=[B, I, J], F=[E, J], G=[], H=[D, J], I=[], J=[B, G]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'B','C'),'B',Set.of(),'C',Set.of(),'D',Set.of( 'C','I'),'E',Set.of( 'B','I','J'),'F',Set.of( 'E','J'),'G',Set.of(),'H',Set.of( 'D','J'),'I',Set.of(),'J',Set.of( 'B','G')));
            assertEquals("pramene/Test_pramene", Set.of( 'A','F','H'), g.pramene());
        }
        {//g={A=[D, G], B=[A, H], C=[J], D=[], E=[A, C, F], F=[H], G=[D], H=[], I=[F, H], J=[A, I]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'D','G'),'B',Set.of( 'A','H'),'C',Set.of( 'J'),'D',Set.of(),'E',Set.of( 'A','C','F'),'F',Set.of( 'H'),'G',Set.of( 'D'),'H',Set.of(),'I',Set.of( 'F','H'),'J',Set.of( 'A','I')));
            assertEquals("pramene/Test_pramene", Set.of( 'B','E'), g.pramene());
        }
        {//g={A=[], B=[C, I], C=[], D=[C, H], E=[], F=[C, E], G=[D, I, J], H=[E], I=[E], J=[G]}
            var g = new Graph<Character>(Map.of( 'A',Set.of(),'B',Set.of( 'C','I'),'C',Set.of(),'D',Set.of( 'C','H'),'E',Set.of(),'F',Set.of( 'C','E'),'G',Set.of( 'D','I','J'),'H',Set.of( 'E'),'I',Set.of( 'E'),'J',Set.of( 'G')));
            assertEquals("pramene/Test_pramene", Set.of( 'A','B','F'), g.pramene());
        }
        {//g={A=[H], B=[], C=[F, J], D=[A], E=[B, C, G, I], F=[I], G=[I], H=[A, G, J], I=[A, E], J=[I]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'H'),'B',Set.of(),'C',Set.of( 'F','J'),'D',Set.of( 'A'),'E',Set.of( 'B','C','G','I'),'F',Set.of( 'I'),'G',Set.of( 'I'),'H',Set.of( 'A','G','J'),'I',Set.of( 'A','E'),'J',Set.of( 'I')));
            assertEquals("pramene/Test_pramene", Set.of( 'D'), g.pramene());
        }
        {//g={A=[], B=[E, H, I], C=[B, D, E], D=[B, H, J], E=[], F=[C], G=[A, D, E], H=[E], I=[G], J=[C, F, G]}
            var g = new Graph<Character>(Map.of( 'A',Set.of(),'B',Set.of( 'E','H','I'),'C',Set.of( 'B','D','E'),'D',Set.of( 'B','H','J'),'E',Set.of(),'F',Set.of( 'C'),'G',Set.of( 'A','D','E'),'H',Set.of( 'E'),'I',Set.of( 'G'),'J',Set.of( 'C','F','G')));
            assertEquals("pramene/Test_pramene", Set.of(), g.pramene());
        }
        {//g={A=[G, I], B=[G, I], C=[I], D=[A, E, F], E=[C], F=[I], G=[J], H=[D, F], I=[E, J], J=[A, G]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'G','I'),'B',Set.of( 'G','I'),'C',Set.of( 'I'),'D',Set.of( 'A','E','F'),'E',Set.of( 'C'),'F',Set.of( 'I'),'G',Set.of( 'J'),'H',Set.of( 'D','F'),'I',Set.of( 'E','J'),'J',Set.of( 'A','G')));
            assertEquals("pramene/Test_pramene", Set.of( 'B','H'), g.pramene());
        }
        {//g={A=[E, J], B=[D, G, I], C=[], D=[], E=[C, F, I], F=[J], G=[], H=[G, I], I=[], J=[B, D, G]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'E','J'),'B',Set.of( 'D','G','I'),'C',Set.of(),'D',Set.of(),'E',Set.of( 'C','F','I'),'F',Set.of( 'J'),'G',Set.of(),'H',Set.of( 'G','I'),'I',Set.of(),'J',Set.of( 'B','D','G')));
            assertEquals("pramene/Test_pramene", Set.of( 'A','H'), g.pramene());
        }
        {//g={A=[C, I, J], B=[A, F], C=[B, E, G], D=[C, E, F], E=[B, G], F=[H, I, J], G=[I], H=[C, E, G], I=[B, E, J], J=[B]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'C','I','J'),'B',Set.of( 'A','F'),'C',Set.of( 'B','E','G'),'D',Set.of( 'C','E','F'),'E',Set.of( 'B','G'),'F',Set.of( 'H','I','J'),'G',Set.of( 'I'),'H',Set.of( 'C','E','G'),'I',Set.of( 'B','E','J'),'J',Set.of( 'B')));
            assertEquals("pramene/Test_pramene", Set.of( 'D'), g.pramene());
        }
        {//g={A=[], B=[D, E, G], C=[], D=[B, F, G, J], E=[], F=[], G=[F, H, I], H=[], I=[E, J], J=[H]}
            var g = new Graph<Character>(Map.of( 'A',Set.of(),'B',Set.of( 'D','E','G'),'C',Set.of(),'D',Set.of( 'B','F','G','J'),'E',Set.of(),'F',Set.of(),'G',Set.of( 'F','H','I'),'H',Set.of(),'I',Set.of( 'E','J'),'J',Set.of( 'H')));
            assertEquals("pramene/Test_pramene", Set.of( 'A','C'), g.pramene());
        }
        {//g={A=[D], B=[I], C=[D, E], D=[], E=[A], F=[A, E, G], G=[I], H=[C, D, F, G], I=[G, H, J], J=[]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'D'),'B',Set.of( 'I'),'C',Set.of( 'D','E'),'D',Set.of(),'E',Set.of( 'A'),'F',Set.of( 'A','E','G'),'G',Set.of( 'I'),'H',Set.of( 'C','D','F','G'),'I',Set.of( 'G','H','J'),'J',Set.of()));
            assertEquals("pramene/Test_pramene", Set.of( 'B'), g.pramene());
        }
        {//g={A=[], B=[A, D], C=[G, H], D=[G], E=[J], F=[B, C], G=[J], H=[I], I=[], J=[B, E]}
            var g = new Graph<Character>(Map.of( 'A',Set.of(),'B',Set.of( 'A','D'),'C',Set.of( 'G','H'),'D',Set.of( 'G'),'E',Set.of( 'J'),'F',Set.of( 'B','C'),'G',Set.of( 'J'),'H',Set.of( 'I'),'I',Set.of(),'J',Set.of( 'B','E')));
            assertEquals("pramene/Test_pramene", Set.of( 'F'), g.pramene());
        }
        {//g={A=[], B=[A, E, G], C=[D, I], D=[], E=[], F=[A, D], G=[B], H=[E, J], I=[], J=[G, H]}
            var g = new Graph<Character>(Map.of( 'A',Set.of(),'B',Set.of( 'A','E','G'),'C',Set.of( 'D','I'),'D',Set.of(),'E',Set.of(),'F',Set.of( 'A','D'),'G',Set.of( 'B'),'H',Set.of( 'E','J'),'I',Set.of(),'J',Set.of( 'G','H')));
            assertEquals("pramene/Test_pramene", Set.of( 'C','F'), g.pramene());
        }
        {//g={A=[E, F], B=[], C=[], D=[A, C, F, J], E=[], F=[A, E, G], G=[], H=[C, D, I], I=[E, F], J=[G, I]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'E','F'),'B',Set.of(),'C',Set.of(),'D',Set.of( 'A','C','F','J'),'E',Set.of(),'F',Set.of( 'A','E','G'),'G',Set.of(),'H',Set.of( 'C','D','I'),'I',Set.of( 'E','F'),'J',Set.of( 'G','I')));
            assertEquals("pramene/Test_pramene", Set.of( 'B','H'), g.pramene());
        }
        {//g={A=[C], B=[], C=[], D=[A, I], E=[B, C], F=[B, E, I], G=[], H=[], I=[B, G, J], J=[A, D, F]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'C'),'B',Set.of(),'C',Set.of(),'D',Set.of( 'A','I'),'E',Set.of( 'B','C'),'F',Set.of( 'B','E','I'),'G',Set.of(),'H',Set.of(),'I',Set.of( 'B','G','J'),'J',Set.of( 'A','D','F')));
            assertEquals("pramene/Test_pramene", Set.of( 'H'), g.pramene());
        }
        {//g={A=[E], B=[D, H], C=[], D=[C, E], E=[A], F=[], G=[A, B, C, I], H=[I], I=[A], J=[C, E, H]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'E'),'B',Set.of( 'D','H'),'C',Set.of(),'D',Set.of( 'C','E'),'E',Set.of( 'A'),'F',Set.of(),'G',Set.of( 'A','B','C','I'),'H',Set.of( 'I'),'I',Set.of( 'A'),'J',Set.of( 'C','E','H')));
            assertEquals("pramene/Test_pramene", Set.of( 'F','G','J'), g.pramene());
        }
        {//g={A=[B, C, E, G], B=[], C=[E, F, G], D=[B, F, G], E=[A, B, F], F=[B, C, H, J], G=[I], H=[A, E, F, I], I=[G, H], J=[D, F, H]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'B','C','E','G'),'B',Set.of(),'C',Set.of( 'E','F','G'),'D',Set.of( 'B','F','G'),'E',Set.of( 'A','B','F'),'F',Set.of( 'B','C','H','J'),'G',Set.of( 'I'),'H',Set.of( 'A','E','F','I'),'I',Set.of( 'G','H'),'J',Set.of( 'D','F','H')));
            assertEquals("pramene/Test_pramene", Set.of(), g.pramene());
        }
        {//g={A=[], B=[E, G], C=[G], D=[A, F], E=[], F=[H], G=[], H=[], I=[B, C, D, G], J=[]}
            var g = new Graph<Character>(Map.of( 'A',Set.of(),'B',Set.of( 'E','G'),'C',Set.of( 'G'),'D',Set.of( 'A','F'),'E',Set.of(),'F',Set.of( 'H'),'G',Set.of(),'H',Set.of(),'I',Set.of( 'B','C','D','G'),'J',Set.of()));
            assertEquals("pramene/Test_pramene", Set.of( 'I','J'), g.pramene());
        }
        {//g={A=[], B=[D], C=[A, I, J], D=[A, H, J], E=[B, D], F=[], G=[A, C, H, J], H=[], I=[D, F], J=[]}
            var g = new Graph<Character>(Map.of( 'A',Set.of(),'B',Set.of( 'D'),'C',Set.of( 'A','I','J'),'D',Set.of( 'A','H','J'),'E',Set.of( 'B','D'),'F',Set.of(),'G',Set.of( 'A','C','H','J'),'H',Set.of(),'I',Set.of( 'D','F'),'J',Set.of()));
            assertEquals("pramene/Test_pramene", Set.of( 'E','G'), g.pramene());
        }
        {//g={A=[E, H], B=[F], C=[B], D=[E, G], E=[], F=[], G=[J], H=[D, G, J], I=[A], J=[D, G, I]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'E','H'),'B',Set.of( 'F'),'C',Set.of( 'B'),'D',Set.of( 'E','G'),'E',Set.of(),'F',Set.of(),'G',Set.of( 'J'),'H',Set.of( 'D','G','J'),'I',Set.of( 'A'),'J',Set.of( 'D','G','I')));
            assertEquals("pramene/Test_pramene", Set.of( 'C'), g.pramene());
        }
        {//g={A=[E, G, H], B=[D, H, J], C=[E], D=[E], E=[], F=[H, I, J], G=[], H=[B], I=[C, D, G], J=[C]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'E','G','H'),'B',Set.of( 'D','H','J'),'C',Set.of( 'E'),'D',Set.of( 'E'),'E',Set.of(),'F',Set.of( 'H','I','J'),'G',Set.of(),'H',Set.of( 'B'),'I',Set.of( 'C','D','G'),'J',Set.of( 'C')));
            assertEquals("pramene/Test_pramene", Set.of( 'A','F'), g.pramene());
        }
        {//g={A=[B, C, G, I], B=[], C=[A, B, D, I], D=[G, I], E=[], F=[I, J], G=[C, J], H=[], I=[A, F], J=[B, D, F]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'B','C','G','I'),'B',Set.of(),'C',Set.of( 'A','B','D','I'),'D',Set.of( 'G','I'),'E',Set.of(),'F',Set.of( 'I','J'),'G',Set.of( 'C','J'),'H',Set.of(),'I',Set.of( 'A','F'),'J',Set.of( 'B','D','F')));
            assertEquals("pramene/Test_pramene", Set.of( 'E','H'), g.pramene());
        }
        {//g={A=[D], B=[J], C=[A, F], D=[C], E=[B, C, I, J], F=[C, E, H, I], G=[B, C], H=[], I=[C], J=[A, B]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'D'),'B',Set.of( 'J'),'C',Set.of( 'A','F'),'D',Set.of( 'C'),'E',Set.of( 'B','C','I','J'),'F',Set.of( 'C','E','H','I'),'G',Set.of( 'B','C'),'H',Set.of(),'I',Set.of( 'C'),'J',Set.of( 'A','B')));
            assertEquals("pramene/Test_pramene", Set.of( 'G'), g.pramene());
        }
        {//g={A=[I, J], B=[], C=[F, G, I], D=[B, C, J], E=[I, J], F=[A], G=[I], H=[D, I, J], I=[A, H, J], J=[B]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'I','J'),'B',Set.of(),'C',Set.of( 'F','G','I'),'D',Set.of( 'B','C','J'),'E',Set.of( 'I','J'),'F',Set.of( 'A'),'G',Set.of( 'I'),'H',Set.of( 'D','I','J'),'I',Set.of( 'A','H','J'),'J',Set.of( 'B')));
            assertEquals("pramene/Test_pramene", Set.of( 'E'), g.pramene());
        }
        {//g={A=[], B=[D, J], C=[D, J], D=[], E=[A, D, J], F=[D], G=[], H=[D, F], I=[A, B], J=[B, E, I]}
            var g = new Graph<Character>(Map.of( 'A',Set.of(),'B',Set.of( 'D','J'),'C',Set.of( 'D','J'),'D',Set.of(),'E',Set.of( 'A','D','J'),'F',Set.of( 'D'),'G',Set.of(),'H',Set.of( 'D','F'),'I',Set.of( 'A','B'),'J',Set.of( 'B','E','I')));
            assertEquals("pramene/Test_pramene", Set.of( 'C','G','H'), g.pramene());
        }
        {//g={A=[B, C], B=[], C=[A, G, H, I], D=[B, H], E=[B], F=[B, E, G, J], G=[A, E], H=[C, D], I=[A, F, J], J=[C]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'B','C'),'B',Set.of(),'C',Set.of( 'A','G','H','I'),'D',Set.of( 'B','H'),'E',Set.of( 'B'),'F',Set.of( 'B','E','G','J'),'G',Set.of( 'A','E'),'H',Set.of( 'C','D'),'I',Set.of( 'A','F','J'),'J',Set.of( 'C')));
            assertEquals("pramene/Test_pramene", Set.of(), g.pramene());
        }
        {//g={A=[E], B=[G, H, J], C=[], D=[], E=[], F=[D, H, J], G=[], H=[B, C, E, F], I=[A, C, D, H], J=[]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'E'),'B',Set.of( 'G','H','J'),'C',Set.of(),'D',Set.of(),'E',Set.of(),'F',Set.of( 'D','H','J'),'G',Set.of(),'H',Set.of( 'B','C','E','F'),'I',Set.of( 'A','C','D','H'),'J',Set.of()));
            assertEquals("pramene/Test_pramene", Set.of( 'I'), g.pramene());
        }
        {//g={A=[B, E, G], B=[I], C=[B, G, J], D=[B, E, H], E=[G, H, I], F=[A, B, D], G=[D, H], H=[], I=[H], J=[A, F, G]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'B','E','G'),'B',Set.of( 'I'),'C',Set.of( 'B','G','J'),'D',Set.of( 'B','E','H'),'E',Set.of( 'G','H','I'),'F',Set.of( 'A','B','D'),'G',Set.of( 'D','H'),'H',Set.of(),'I',Set.of( 'H'),'J',Set.of( 'A','F','G')));
            assertEquals("pramene/Test_pramene", Set.of( 'C'), g.pramene());
        }
        {//g={A=[B, F, J], B=[], C=[F], D=[G, J], E=[], F=[], G=[A], H=[A, D, E], I=[], J=[A, H]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'B','F','J'),'B',Set.of(),'C',Set.of( 'F'),'D',Set.of( 'G','J'),'E',Set.of(),'F',Set.of(),'G',Set.of( 'A'),'H',Set.of( 'A','D','E'),'I',Set.of(),'J',Set.of( 'A','H')));
            assertEquals("pramene/Test_pramene", Set.of( 'C','I'), g.pramene());
        }
        {//g={A=[], B=[A, I], C=[B, H], D=[C, J], E=[C], F=[A, I], G=[F], H=[D], I=[B], J=[]}
            var g = new Graph<Character>(Map.of( 'A',Set.of(),'B',Set.of( 'A','I'),'C',Set.of( 'B','H'),'D',Set.of( 'C','J'),'E',Set.of( 'C'),'F',Set.of( 'A','I'),'G',Set.of( 'F'),'H',Set.of( 'D'),'I',Set.of( 'B'),'J',Set.of()));
            assertEquals("pramene/Test_pramene", Set.of( 'E','G'), g.pramene());
        }
        {//g={A=[I], B=[D, E, I, J], C=[J], D=[A], E=[], F=[G], G=[], H=[C, I], I=[F, J], J=[F, G]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'I'),'B',Set.of( 'D','E','I','J'),'C',Set.of( 'J'),'D',Set.of( 'A'),'E',Set.of(),'F',Set.of( 'G'),'G',Set.of(),'H',Set.of( 'C','I'),'I',Set.of( 'F','J'),'J',Set.of( 'F','G')));
            assertEquals("pramene/Test_pramene", Set.of( 'B','H'), g.pramene());
        }
        {//g={A=[], B=[H, J], C=[G, H], D=[C, J], E=[C, F, J], F=[A, B, D, I], G=[B], H=[G, I, J], I=[], J=[F]}
            var g = new Graph<Character>(Map.of( 'A',Set.of(),'B',Set.of( 'H','J'),'C',Set.of( 'G','H'),'D',Set.of( 'C','J'),'E',Set.of( 'C','F','J'),'F',Set.of( 'A','B','D','I'),'G',Set.of( 'B'),'H',Set.of( 'G','I','J'),'I',Set.of(),'J',Set.of( 'F')));
            assertEquals("pramene/Test_pramene", Set.of( 'E'), g.pramene());
        }
        {//g={A=[D, I], B=[A, D, F], C=[I], D=[F], E=[], F=[E, H], G=[E, F], H=[A, B, C], I=[], J=[H]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'D','I'),'B',Set.of( 'A','D','F'),'C',Set.of( 'I'),'D',Set.of( 'F'),'E',Set.of(),'F',Set.of( 'E','H'),'G',Set.of( 'E','F'),'H',Set.of( 'A','B','C'),'I',Set.of(),'J',Set.of( 'H')));
            assertEquals("pramene/Test_pramene", Set.of( 'G','J'), g.pramene());
        }
        {//g={A=[], B=[A, E], C=[A, B, D, G], D=[], E=[B], F=[E], G=[B], H=[E, I], I=[C], J=[A]}
            var g = new Graph<Character>(Map.of( 'A',Set.of(),'B',Set.of( 'A','E'),'C',Set.of( 'A','B','D','G'),'D',Set.of(),'E',Set.of( 'B'),'F',Set.of( 'E'),'G',Set.of( 'B'),'H',Set.of( 'E','I'),'I',Set.of( 'C'),'J',Set.of( 'A')));
            assertEquals("pramene/Test_pramene", Set.of( 'F','H','J'), g.pramene());
        }
        {//g={A=[I], B=[], C=[A, G, J], D=[B, C, J], E=[C, H, J], F=[D, H], G=[H], H=[], I=[C], J=[B]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'I'),'B',Set.of(),'C',Set.of( 'A','G','J'),'D',Set.of( 'B','C','J'),'E',Set.of( 'C','H','J'),'F',Set.of( 'D','H'),'G',Set.of( 'H'),'H',Set.of(),'I',Set.of( 'C'),'J',Set.of( 'B')));
            assertEquals("pramene/Test_pramene", Set.of( 'E','F'), g.pramene());
        }
        {//g={A=[J], B=[I], C=[B, J], D=[A, C], E=[], F=[], G=[H], H=[E], I=[], J=[D, G, I]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'J'),'B',Set.of( 'I'),'C',Set.of( 'B','J'),'D',Set.of( 'A','C'),'E',Set.of(),'F',Set.of(),'G',Set.of( 'H'),'H',Set.of( 'E'),'I',Set.of(),'J',Set.of( 'D','G','I')));
            assertEquals("pramene/Test_pramene", Set.of( 'F'), g.pramene());
        }
        {//g={A=[B, J], B=[G, H], C=[A, E], D=[E], E=[G, J], F=[G, I, J], G=[A, E], H=[], I=[], J=[]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'B','J'),'B',Set.of( 'G','H'),'C',Set.of( 'A','E'),'D',Set.of( 'E'),'E',Set.of( 'G','J'),'F',Set.of( 'G','I','J'),'G',Set.of( 'A','E'),'H',Set.of(),'I',Set.of(),'J',Set.of()));
            assertEquals("pramene/Test_pramene", Set.of( 'C','D','F'), g.pramene());
        }
        {//g={A=[C], B=[], C=[B, D, H], D=[E, H, I], E=[G], F=[B, C, D, I], G=[], H=[], I=[J], J=[B, C, H]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'C'),'B',Set.of(),'C',Set.of( 'B','D','H'),'D',Set.of( 'E','H','I'),'E',Set.of( 'G'),'F',Set.of( 'B','C','D','I'),'G',Set.of(),'H',Set.of(),'I',Set.of( 'J'),'J',Set.of( 'B','C','H')));
            assertEquals("pramene/Test_pramene", Set.of( 'A','F'), g.pramene());
        }
        {//g={A=[H, I], B=[F], C=[], D=[E, G], E=[C, D, H, I], F=[D, G], G=[C, I], H=[C, E], I=[], J=[F, G, H, I]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'H','I'),'B',Set.of( 'F'),'C',Set.of(),'D',Set.of( 'E','G'),'E',Set.of( 'C','D','H','I'),'F',Set.of( 'D','G'),'G',Set.of( 'C','I'),'H',Set.of( 'C','E'),'I',Set.of(),'J',Set.of( 'F','G','H','I')));
            assertEquals("pramene/Test_pramene", Set.of( 'A','B','J'), g.pramene());
        }
        {//g={A=[B, C, D], B=[E], C=[], D=[F], E=[A, B, C, J], F=[A, B, D], G=[D], H=[A, C, E, F], I=[D], J=[D, E]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'B','C','D'),'B',Set.of( 'E'),'C',Set.of(),'D',Set.of( 'F'),'E',Set.of( 'A','B','C','J'),'F',Set.of( 'A','B','D'),'G',Set.of( 'D'),'H',Set.of( 'A','C','E','F'),'I',Set.of( 'D'),'J',Set.of( 'D','E')));
            assertEquals("pramene/Test_pramene", Set.of( 'G','H','I'), g.pramene());
        }
        {//g={A=[E, G], B=[F], C=[E, J], D=[A], E=[F, H, I], F=[A, B, H], G=[D], H=[I], I=[A, B, C], J=[]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'E','G'),'B',Set.of( 'F'),'C',Set.of( 'E','J'),'D',Set.of( 'A'),'E',Set.of( 'F','H','I'),'F',Set.of( 'A','B','H'),'G',Set.of( 'D'),'H',Set.of( 'I'),'I',Set.of( 'A','B','C'),'J',Set.of()));
            assertEquals("pramene/Test_pramene", Set.of(), g.pramene());
        }
        {//g={A=[], B=[F, H, J], C=[D, I, J], D=[C, F, J], E=[A, B, D], F=[A, B, E], G=[B], H=[D], I=[D, H], J=[B, C, F]}
            var g = new Graph<Character>(Map.of( 'A',Set.of(),'B',Set.of( 'F','H','J'),'C',Set.of( 'D','I','J'),'D',Set.of( 'C','F','J'),'E',Set.of( 'A','B','D'),'F',Set.of( 'A','B','E'),'G',Set.of( 'B'),'H',Set.of( 'D'),'I',Set.of( 'D','H'),'J',Set.of( 'B','C','F')));
            assertEquals("pramene/Test_pramene", Set.of( 'G'), g.pramene());
        }
        {//g={A=[D, J], B=[H], C=[I], D=[J], E=[A, I], F=[A, B], G=[D, F], H=[A, D, G, J], I=[B, H, J], J=[B, D]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'D','J'),'B',Set.of( 'H'),'C',Set.of( 'I'),'D',Set.of( 'J'),'E',Set.of( 'A','I'),'F',Set.of( 'A','B'),'G',Set.of( 'D','F'),'H',Set.of( 'A','D','G','J'),'I',Set.of( 'B','H','J'),'J',Set.of( 'B','D')));
            assertEquals("pramene/Test_pramene", Set.of( 'C','E'), g.pramene());
        }
        {//g={A=[B, D, E], B=[D, G, H, J], C=[B], D=[B, C], E=[A, D], F=[C, D, J], G=[D, I], H=[A], I=[A, G], J=[B, C]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'B','D','E'),'B',Set.of( 'D','G','H','J'),'C',Set.of( 'B'),'D',Set.of( 'B','C'),'E',Set.of( 'A','D'),'F',Set.of( 'C','D','J'),'G',Set.of( 'D','I'),'H',Set.of( 'A'),'I',Set.of( 'A','G'),'J',Set.of( 'B','C')));
            assertEquals("pramene/Test_pramene", Set.of( 'F'), g.pramene());
        }
        {//g={A=[], B=[A, H, J], C=[], D=[B, C, G, H], E=[], F=[A, B, G], G=[B, C, D, F], H=[B], I=[C], J=[]}
            var g = new Graph<Character>(Map.of( 'A',Set.of(),'B',Set.of( 'A','H','J'),'C',Set.of(),'D',Set.of( 'B','C','G','H'),'E',Set.of(),'F',Set.of( 'A','B','G'),'G',Set.of( 'B','C','D','F'),'H',Set.of( 'B'),'I',Set.of( 'C'),'J',Set.of()));
            assertEquals("pramene/Test_pramene", Set.of( 'E','I'), g.pramene());
        }
        {//g={A=[B, G, H], B=[C], C=[], D=[B, F], E=[], F=[], G=[A, D], H=[D, F, I], I=[A, B, D, F], J=[B, I]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'B','G','H'),'B',Set.of( 'C'),'C',Set.of(),'D',Set.of( 'B','F'),'E',Set.of(),'F',Set.of(),'G',Set.of( 'A','D'),'H',Set.of( 'D','F','I'),'I',Set.of( 'A','B','D','F'),'J',Set.of( 'B','I')));
            assertEquals("pramene/Test_pramene", Set.of( 'E','J'), g.pramene());
        }
        {//g={A=[F, G, H, J], B=[C, E, I, J], C=[], D=[B, F, G, J], E=[A, F, J], F=[], G=[], H=[A, F], I=[A, B], J=[B, D, E, G]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'F','G','H','J'),'B',Set.of( 'C','E','I','J'),'C',Set.of(),'D',Set.of( 'B','F','G','J'),'E',Set.of( 'A','F','J'),'F',Set.of(),'G',Set.of(),'H',Set.of( 'A','F'),'I',Set.of( 'A','B'),'J',Set.of( 'B','D','E','G')));
            assertEquals("pramene/Test_pramene", Set.of(), g.pramene());
        }
        {//g={A=[C, E, G, J], B=[A], C=[], D=[A, C, E, G], E=[F, J], F=[], G=[], H=[A, B, J], I=[B], J=[A, D, E, I]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'C','E','G','J'),'B',Set.of( 'A'),'C',Set.of(),'D',Set.of( 'A','C','E','G'),'E',Set.of( 'F','J'),'F',Set.of(),'G',Set.of(),'H',Set.of( 'A','B','J'),'I',Set.of( 'B'),'J',Set.of( 'A','D','E','I')));
            assertEquals("pramene/Test_pramene", Set.of( 'H'), g.pramene());
        }
        scoring.updateScore("lang:common_list_test_scoring_name",   17.0D);
    }
    @Test
    public void test_ustia() {
        {//g={A=[], B=[], C=[E], D=[C, G], E=[A, D, H, J], F=[D], G=[F], H=[D, G, J], I=[A, B], J=[E]}
            var g = new Graph<Character>(Map.of( 'A',Set.of(),'B',Set.of(),'C',Set.of( 'E'),'D',Set.of( 'C','G'),'E',Set.of( 'A','D','H','J'),'F',Set.of( 'D'),'G',Set.of( 'F'),'H',Set.of( 'D','G','J'),'I',Set.of( 'A','B'),'J',Set.of( 'E')));
            assertEquals("ustia/Test_ustia", Set.of( 'A','B'), g.ustia());
        }
        {//g={A=[F, H], B=[H], C=[A, F], D=[A, I], E=[B, C, D], F=[B], G=[A], H=[A, G], I=[D, F, H], J=[]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'F','H'),'B',Set.of( 'H'),'C',Set.of( 'A','F'),'D',Set.of( 'A','I'),'E',Set.of( 'B','C','D'),'F',Set.of( 'B'),'G',Set.of( 'A'),'H',Set.of( 'A','G'),'I',Set.of( 'D','F','H'),'J',Set.of()));
            assertEquals("ustia/Test_ustia", Set.of( 'J'), g.ustia());
        }
        {//g={A=[H], B=[A, F, H], C=[G], D=[H], E=[], F=[], G=[], H=[], I=[C, F, H], J=[C]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'H'),'B',Set.of( 'A','F','H'),'C',Set.of( 'G'),'D',Set.of( 'H'),'E',Set.of(),'F',Set.of(),'G',Set.of(),'H',Set.of(),'I',Set.of( 'C','F','H'),'J',Set.of( 'C')));
            assertEquals("ustia/Test_ustia", Set.of( 'E','F','G','H'), g.ustia());
        }
        {//g={A=[], B=[F, J], C=[D, J], D=[A, E, H], E=[], F=[C, D, H], G=[A, C, E], H=[B, E, F, J], I=[A, B, H], J=[B, F]}
            var g = new Graph<Character>(Map.of( 'A',Set.of(),'B',Set.of( 'F','J'),'C',Set.of( 'D','J'),'D',Set.of( 'A','E','H'),'E',Set.of(),'F',Set.of( 'C','D','H'),'G',Set.of( 'A','C','E'),'H',Set.of( 'B','E','F','J'),'I',Set.of( 'A','B','H'),'J',Set.of( 'B','F')));
            assertEquals("ustia/Test_ustia", Set.of( 'A','E'), g.ustia());
        }
        {//g={A=[F], B=[F, I], C=[A, G, I], D=[], E=[G, H, I], F=[A, J], G=[H], H=[A, B, D], I=[C, G, H], J=[A, C, D, E]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'F'),'B',Set.of( 'F','I'),'C',Set.of( 'A','G','I'),'D',Set.of(),'E',Set.of( 'G','H','I'),'F',Set.of( 'A','J'),'G',Set.of( 'H'),'H',Set.of( 'A','B','D'),'I',Set.of( 'C','G','H'),'J',Set.of( 'A','C','D','E')));
            assertEquals("ustia/Test_ustia", Set.of( 'D'), g.ustia());
        }
        {//g={A=[B], B=[C, G, J], C=[], D=[E, G, H], E=[A, D, G], F=[], G=[C], H=[B, I], I=[F], J=[]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'B'),'B',Set.of( 'C','G','J'),'C',Set.of(),'D',Set.of( 'E','G','H'),'E',Set.of( 'A','D','G'),'F',Set.of(),'G',Set.of( 'C'),'H',Set.of( 'B','I'),'I',Set.of( 'F'),'J',Set.of()));
            assertEquals("ustia/Test_ustia", Set.of( 'C','F','J'), g.ustia());
        }
        {//g={A=[I], B=[], C=[F, G, H], D=[B, C, F], E=[], F=[H, I], G=[F], H=[B], I=[H], J=[D, E, H]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'I'),'B',Set.of(),'C',Set.of( 'F','G','H'),'D',Set.of( 'B','C','F'),'E',Set.of(),'F',Set.of( 'H','I'),'G',Set.of( 'F'),'H',Set.of( 'B'),'I',Set.of( 'H'),'J',Set.of( 'D','E','H')));
            assertEquals("ustia/Test_ustia", Set.of( 'B','E'), g.ustia());
        }
        {//g={A=[B], B=[C], C=[H], D=[G, H], E=[], F=[A, E, G, I], G=[], H=[B, F, J], I=[], J=[]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'B'),'B',Set.of( 'C'),'C',Set.of( 'H'),'D',Set.of( 'G','H'),'E',Set.of(),'F',Set.of( 'A','E','G','I'),'G',Set.of(),'H',Set.of( 'B','F','J'),'I',Set.of(),'J',Set.of()));
            assertEquals("ustia/Test_ustia", Set.of( 'E','G','I','J'), g.ustia());
        }
        {//g={A=[D, E, G], B=[], C=[A, F, H, I], D=[A, G], E=[H], F=[H, I], G=[A, C], H=[B, E, G], I=[C, E, F, G], J=[]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'D','E','G'),'B',Set.of(),'C',Set.of( 'A','F','H','I'),'D',Set.of( 'A','G'),'E',Set.of( 'H'),'F',Set.of( 'H','I'),'G',Set.of( 'A','C'),'H',Set.of( 'B','E','G'),'I',Set.of( 'C','E','F','G'),'J',Set.of()));
            assertEquals("ustia/Test_ustia", Set.of( 'B','J'), g.ustia());
        }
        {//g={A=[B, I, J], B=[H], C=[B, F, I], D=[G, H, I], E=[F, J], F=[C, E, J], G=[F, I], H=[C, E, F], I=[], J=[A, H, I]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'B','I','J'),'B',Set.of( 'H'),'C',Set.of( 'B','F','I'),'D',Set.of( 'G','H','I'),'E',Set.of( 'F','J'),'F',Set.of( 'C','E','J'),'G',Set.of( 'F','I'),'H',Set.of( 'C','E','F'),'I',Set.of(),'J',Set.of( 'A','H','I')));
            assertEquals("ustia/Test_ustia", Set.of( 'I'), g.ustia());
        }
        {//g={A=[I, J], B=[E], C=[A, B, F, G], D=[G, H], E=[], F=[H, J], G=[F, H], H=[A, C, F], I=[E, G], J=[A, B, G]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'I','J'),'B',Set.of( 'E'),'C',Set.of( 'A','B','F','G'),'D',Set.of( 'G','H'),'E',Set.of(),'F',Set.of( 'H','J'),'G',Set.of( 'F','H'),'H',Set.of( 'A','C','F'),'I',Set.of( 'E','G'),'J',Set.of( 'A','B','G')));
            assertEquals("ustia/Test_ustia", Set.of( 'E'), g.ustia());
        }
        {//g={A=[C], B=[E], C=[D, H], D=[A, C, G], E=[A, H], F=[D, E, H], G=[B, D, H, I], H=[], I=[H, J], J=[D, G]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'C'),'B',Set.of( 'E'),'C',Set.of( 'D','H'),'D',Set.of( 'A','C','G'),'E',Set.of( 'A','H'),'F',Set.of( 'D','E','H'),'G',Set.of( 'B','D','H','I'),'H',Set.of(),'I',Set.of( 'H','J'),'J',Set.of( 'D','G')));
            assertEquals("ustia/Test_ustia", Set.of( 'H'), g.ustia());
        }
        {//g={A=[C, E], B=[C, D, F], C=[A, I], D=[A, J], E=[], F=[A, I], G=[B, I], H=[], I=[B, F], J=[A, B]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'C','E'),'B',Set.of( 'C','D','F'),'C',Set.of( 'A','I'),'D',Set.of( 'A','J'),'E',Set.of(),'F',Set.of( 'A','I'),'G',Set.of( 'B','I'),'H',Set.of(),'I',Set.of( 'B','F'),'J',Set.of( 'A','B')));
            assertEquals("ustia/Test_ustia", Set.of( 'E','H'), g.ustia());
        }
        {//g={A=[H], B=[I], C=[], D=[], E=[I, J], F=[E, I], G=[A], H=[B, F], I=[A, E], J=[A, D, H]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'H'),'B',Set.of( 'I'),'C',Set.of(),'D',Set.of(),'E',Set.of( 'I','J'),'F',Set.of( 'E','I'),'G',Set.of( 'A'),'H',Set.of( 'B','F'),'I',Set.of( 'A','E'),'J',Set.of( 'A','D','H')));
            assertEquals("ustia/Test_ustia", Set.of( 'C','D'), g.ustia());
        }
        {//g={A=[B, H], B=[J], C=[D, F, H], D=[], E=[C, F, G, H], F=[A, J], G=[I], H=[F, J], I=[], J=[A, G]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'B','H'),'B',Set.of( 'J'),'C',Set.of( 'D','F','H'),'D',Set.of(),'E',Set.of( 'C','F','G','H'),'F',Set.of( 'A','J'),'G',Set.of( 'I'),'H',Set.of( 'F','J'),'I',Set.of(),'J',Set.of( 'A','G')));
            assertEquals("ustia/Test_ustia", Set.of( 'D','I'), g.ustia());
        }
        {//g={A=[], B=[A, C, E, I], C=[], D=[H, I], E=[C, H, I], F=[H, I, J], G=[], H=[], I=[A, E], J=[]}
            var g = new Graph<Character>(Map.of( 'A',Set.of(),'B',Set.of( 'A','C','E','I'),'C',Set.of(),'D',Set.of( 'H','I'),'E',Set.of( 'C','H','I'),'F',Set.of( 'H','I','J'),'G',Set.of(),'H',Set.of(),'I',Set.of( 'A','E'),'J',Set.of()));
            assertEquals("ustia/Test_ustia", Set.of( 'A','C','G','H','J'), g.ustia());
        }
        {//g={A=[B, C, E], B=[], C=[B, D, H], D=[C, E, I], E=[C, F], F=[C, D, G, J], G=[A, I], H=[B, J], I=[F], J=[D, I]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'B','C','E'),'B',Set.of(),'C',Set.of( 'B','D','H'),'D',Set.of( 'C','E','I'),'E',Set.of( 'C','F'),'F',Set.of( 'C','D','G','J'),'G',Set.of( 'A','I'),'H',Set.of( 'B','J'),'I',Set.of( 'F'),'J',Set.of( 'D','I')));
            assertEquals("ustia/Test_ustia", Set.of( 'B'), g.ustia());
        }
        {//g={A=[], B=[H, I, J], C=[E, I], D=[C, E, F], E=[B, F, G, I], F=[], G=[C], H=[], I=[B, C], J=[A, B]}
            var g = new Graph<Character>(Map.of( 'A',Set.of(),'B',Set.of( 'H','I','J'),'C',Set.of( 'E','I'),'D',Set.of( 'C','E','F'),'E',Set.of( 'B','F','G','I'),'F',Set.of(),'G',Set.of( 'C'),'H',Set.of(),'I',Set.of( 'B','C'),'J',Set.of( 'A','B')));
            assertEquals("ustia/Test_ustia", Set.of( 'A','F','H'), g.ustia());
        }
        {//g={A=[D, F, G], B=[D, F, I], C=[G, H, I], D=[E], E=[B, J], F=[], G=[A], H=[A, B, C], I=[J], J=[A, I]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'D','F','G'),'B',Set.of( 'D','F','I'),'C',Set.of( 'G','H','I'),'D',Set.of( 'E'),'E',Set.of( 'B','J'),'F',Set.of(),'G',Set.of( 'A'),'H',Set.of( 'A','B','C'),'I',Set.of( 'J'),'J',Set.of( 'A','I')));
            assertEquals("ustia/Test_ustia", Set.of( 'F'), g.ustia());
        }
        {//g={A=[D, G, I], B=[E], C=[], D=[B, J], E=[A, C, F, J], F=[I], G=[C, I, J], H=[C, J], I=[D], J=[]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'D','G','I'),'B',Set.of( 'E'),'C',Set.of(),'D',Set.of( 'B','J'),'E',Set.of( 'A','C','F','J'),'F',Set.of( 'I'),'G',Set.of( 'C','I','J'),'H',Set.of( 'C','J'),'I',Set.of( 'D'),'J',Set.of()));
            assertEquals("ustia/Test_ustia", Set.of( 'C','J'), g.ustia());
        }
        {//g={A=[], B=[], C=[F, G], D=[], E=[A, F], F=[], G=[], H=[G, J], I=[A, E, H, J], J=[B, E, F, I]}
            var g = new Graph<Character>(Map.of( 'A',Set.of(),'B',Set.of(),'C',Set.of( 'F','G'),'D',Set.of(),'E',Set.of( 'A','F'),'F',Set.of(),'G',Set.of(),'H',Set.of( 'G','J'),'I',Set.of( 'A','E','H','J'),'J',Set.of( 'B','E','F','I')));
            assertEquals("ustia/Test_ustia", Set.of( 'A','B','D','F','G'), g.ustia());
        }
        {//g={A=[F, H], B=[D], C=[E, H], D=[I], E=[J], F=[A], G=[A], H=[], I=[D], J=[C]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'F','H'),'B',Set.of( 'D'),'C',Set.of( 'E','H'),'D',Set.of( 'I'),'E',Set.of( 'J'),'F',Set.of( 'A'),'G',Set.of( 'A'),'H',Set.of(),'I',Set.of( 'D'),'J',Set.of( 'C')));
            assertEquals("ustia/Test_ustia", Set.of( 'H'), g.ustia());
        }
        {//g={A=[], B=[], C=[B, E], D=[F, I], E=[D, F], F=[A, C, J], G=[D, E, I], H=[E, I, J], I=[], J=[D, E, G]}
            var g = new Graph<Character>(Map.of( 'A',Set.of(),'B',Set.of(),'C',Set.of( 'B','E'),'D',Set.of( 'F','I'),'E',Set.of( 'D','F'),'F',Set.of( 'A','C','J'),'G',Set.of( 'D','E','I'),'H',Set.of( 'E','I','J'),'I',Set.of(),'J',Set.of( 'D','E','G')));
            assertEquals("ustia/Test_ustia", Set.of( 'A','B','I'), g.ustia());
        }
        {//g={A=[E, G], B=[], C=[D, H, J], D=[], E=[H], F=[B, D], G=[A, D, E], H=[E, I], I=[B, F, G], J=[F]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'E','G'),'B',Set.of(),'C',Set.of( 'D','H','J'),'D',Set.of(),'E',Set.of( 'H'),'F',Set.of( 'B','D'),'G',Set.of( 'A','D','E'),'H',Set.of( 'E','I'),'I',Set.of( 'B','F','G'),'J',Set.of( 'F')));
            assertEquals("ustia/Test_ustia", Set.of( 'B','D'), g.ustia());
        }
        {//g={A=[], B=[], C=[F, H], D=[], E=[], F=[D, E], G=[], H=[D], I=[F, G, J], J=[A, B]}
            var g = new Graph<Character>(Map.of( 'A',Set.of(),'B',Set.of(),'C',Set.of( 'F','H'),'D',Set.of(),'E',Set.of(),'F',Set.of( 'D','E'),'G',Set.of(),'H',Set.of( 'D'),'I',Set.of( 'F','G','J'),'J',Set.of( 'A','B')));
            assertEquals("ustia/Test_ustia", Set.of( 'A','B','D','E','G'), g.ustia());
        }
        {//g={A=[B, G, J], B=[], C=[], D=[], E=[C, F], F=[E, G, H], G=[B, C, D], H=[F], I=[F], J=[C]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'B','G','J'),'B',Set.of(),'C',Set.of(),'D',Set.of(),'E',Set.of( 'C','F'),'F',Set.of( 'E','G','H'),'G',Set.of( 'B','C','D'),'H',Set.of( 'F'),'I',Set.of( 'F'),'J',Set.of( 'C')));
            assertEquals("ustia/Test_ustia", Set.of( 'B','C','D'), g.ustia());
        }
        {//g={A=[], B=[C, E], C=[D, I], D=[G, I], E=[A, G, J], F=[A, E], G=[B, E, I], H=[E, I], I=[D, H], J=[]}
            var g = new Graph<Character>(Map.of( 'A',Set.of(),'B',Set.of( 'C','E'),'C',Set.of( 'D','I'),'D',Set.of( 'G','I'),'E',Set.of( 'A','G','J'),'F',Set.of( 'A','E'),'G',Set.of( 'B','E','I'),'H',Set.of( 'E','I'),'I',Set.of( 'D','H'),'J',Set.of()));
            assertEquals("ustia/Test_ustia", Set.of( 'A','J'), g.ustia());
        }
        {//g={A=[], B=[], C=[G, I], D=[], E=[H, J], F=[B], G=[E, H, J], H=[], I=[F], J=[B, F]}
            var g = new Graph<Character>(Map.of( 'A',Set.of(),'B',Set.of(),'C',Set.of( 'G','I'),'D',Set.of(),'E',Set.of( 'H','J'),'F',Set.of( 'B'),'G',Set.of( 'E','H','J'),'H',Set.of(),'I',Set.of( 'F'),'J',Set.of( 'B','F')));
            assertEquals("ustia/Test_ustia", Set.of( 'A','B','D','H'), g.ustia());
        }
        {//g={A=[G], B=[H], C=[B, J], D=[B, E, I], E=[C, D, F, H], F=[J], G=[C, D, E, J], H=[F, G], I=[], J=[F]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'G'),'B',Set.of( 'H'),'C',Set.of( 'B','J'),'D',Set.of( 'B','E','I'),'E',Set.of( 'C','D','F','H'),'F',Set.of( 'J'),'G',Set.of( 'C','D','E','J'),'H',Set.of( 'F','G'),'I',Set.of(),'J',Set.of( 'F')));
            assertEquals("ustia/Test_ustia", Set.of( 'I'), g.ustia());
        }
        {//g={A=[], B=[J], C=[I], D=[J], E=[C, F, G, I], F=[], G=[], H=[C, D], I=[B], J=[A, D]}
            var g = new Graph<Character>(Map.of( 'A',Set.of(),'B',Set.of( 'J'),'C',Set.of( 'I'),'D',Set.of( 'J'),'E',Set.of( 'C','F','G','I'),'F',Set.of(),'G',Set.of(),'H',Set.of( 'C','D'),'I',Set.of( 'B'),'J',Set.of( 'A','D')));
            assertEquals("ustia/Test_ustia", Set.of( 'A','F','G'), g.ustia());
        }
        {//g={A=[I], B=[], C=[J], D=[A, H], E=[H, I], F=[G, I], G=[A, E, I], H=[I], I=[], J=[H]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'I'),'B',Set.of(),'C',Set.of( 'J'),'D',Set.of( 'A','H'),'E',Set.of( 'H','I'),'F',Set.of( 'G','I'),'G',Set.of( 'A','E','I'),'H',Set.of( 'I'),'I',Set.of(),'J',Set.of( 'H')));
            assertEquals("ustia/Test_ustia", Set.of( 'B','I'), g.ustia());
        }
        {//g={A=[G], B=[C], C=[], D=[C, H], E=[], F=[A], G=[A, D], H=[], I=[C, D, H], J=[]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'G'),'B',Set.of( 'C'),'C',Set.of(),'D',Set.of( 'C','H'),'E',Set.of(),'F',Set.of( 'A'),'G',Set.of( 'A','D'),'H',Set.of(),'I',Set.of( 'C','D','H'),'J',Set.of()));
            assertEquals("ustia/Test_ustia", Set.of( 'C','E','H','J'), g.ustia());
        }
        {//g={A=[D, G, I], B=[F], C=[], D=[A], E=[F], F=[J], G=[A, B, F], H=[I], I=[F], J=[]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'D','G','I'),'B',Set.of( 'F'),'C',Set.of(),'D',Set.of( 'A'),'E',Set.of( 'F'),'F',Set.of( 'J'),'G',Set.of( 'A','B','F'),'H',Set.of( 'I'),'I',Set.of( 'F'),'J',Set.of()));
            assertEquals("ustia/Test_ustia", Set.of( 'C','J'), g.ustia());
        }
        {//g={A=[C, I, J], B=[J], C=[A, E, F], D=[], E=[C, D, I], F=[], G=[D], H=[A, C, D, J], I=[A, F, H, J], J=[]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'C','I','J'),'B',Set.of( 'J'),'C',Set.of( 'A','E','F'),'D',Set.of(),'E',Set.of( 'C','D','I'),'F',Set.of(),'G',Set.of( 'D'),'H',Set.of( 'A','C','D','J'),'I',Set.of( 'A','F','H','J'),'J',Set.of()));
            assertEquals("ustia/Test_ustia", Set.of( 'D','F','J'), g.ustia());
        }
        {//g={A=[C, E, J], B=[E, G], C=[D, E, J], D=[B, F], E=[G, H], F=[A], G=[D], H=[], I=[], J=[D, I]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'C','E','J'),'B',Set.of( 'E','G'),'C',Set.of( 'D','E','J'),'D',Set.of( 'B','F'),'E',Set.of( 'G','H'),'F',Set.of( 'A'),'G',Set.of( 'D'),'H',Set.of(),'I',Set.of(),'J',Set.of( 'D','I')));
            assertEquals("ustia/Test_ustia", Set.of( 'H','I'), g.ustia());
        }
        {//g={A=[D], B=[C], C=[A, B], D=[A, E], E=[B, G, H], F=[A, I], G=[], H=[B, G], I=[A, C, H], J=[B]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'D'),'B',Set.of( 'C'),'C',Set.of( 'A','B'),'D',Set.of( 'A','E'),'E',Set.of( 'B','G','H'),'F',Set.of( 'A','I'),'G',Set.of(),'H',Set.of( 'B','G'),'I',Set.of( 'A','C','H'),'J',Set.of( 'B')));
            assertEquals("ustia/Test_ustia", Set.of( 'G'), g.ustia());
        }
        {//g={A=[E, F, G], B=[E, H], C=[A, D, G, J], D=[A, F], E=[H], F=[B, E], G=[A, H, J], H=[I], I=[A, D, H], J=[]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'E','F','G'),'B',Set.of( 'E','H'),'C',Set.of( 'A','D','G','J'),'D',Set.of( 'A','F'),'E',Set.of( 'H'),'F',Set.of( 'B','E'),'G',Set.of( 'A','H','J'),'H',Set.of( 'I'),'I',Set.of( 'A','D','H'),'J',Set.of()));
            assertEquals("ustia/Test_ustia", Set.of( 'J'), g.ustia());
        }
        {//g={A=[C, I, J], B=[], C=[A, H], D=[E, J], E=[A, F], F=[D, G, I], G=[B, F, J], H=[B], I=[G, H], J=[B, G, H]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'C','I','J'),'B',Set.of(),'C',Set.of( 'A','H'),'D',Set.of( 'E','J'),'E',Set.of( 'A','F'),'F',Set.of( 'D','G','I'),'G',Set.of( 'B','F','J'),'H',Set.of( 'B'),'I',Set.of( 'G','H'),'J',Set.of( 'B','G','H')));
            assertEquals("ustia/Test_ustia", Set.of( 'B'), g.ustia());
        }
        {//g={A=[C, G], B=[], C=[F], D=[], E=[], F=[I], G=[D, E, I], H=[A, F, G], I=[A, D, E], J=[C]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'C','G'),'B',Set.of(),'C',Set.of( 'F'),'D',Set.of(),'E',Set.of(),'F',Set.of( 'I'),'G',Set.of( 'D','E','I'),'H',Set.of( 'A','F','G'),'I',Set.of( 'A','D','E'),'J',Set.of( 'C')));
            assertEquals("ustia/Test_ustia", Set.of( 'B','D','E'), g.ustia());
        }
        {//g={A=[C], B=[F], C=[B], D=[], E=[B, H], F=[A, C, H], G=[E, J], H=[C, F], I=[A, J], J=[]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'C'),'B',Set.of( 'F'),'C',Set.of( 'B'),'D',Set.of(),'E',Set.of( 'B','H'),'F',Set.of( 'A','C','H'),'G',Set.of( 'E','J'),'H',Set.of( 'C','F'),'I',Set.of( 'A','J'),'J',Set.of()));
            assertEquals("ustia/Test_ustia", Set.of( 'D','J'), g.ustia());
        }
        {//g={A=[C, D], B=[], C=[A, D, G, I], D=[], E=[D, J], F=[A, B, G], G=[A], H=[], I=[B, J], J=[F, H]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'C','D'),'B',Set.of(),'C',Set.of( 'A','D','G','I'),'D',Set.of(),'E',Set.of( 'D','J'),'F',Set.of( 'A','B','G'),'G',Set.of( 'A'),'H',Set.of(),'I',Set.of( 'B','J'),'J',Set.of( 'F','H')));
            assertEquals("ustia/Test_ustia", Set.of( 'B','D','H'), g.ustia());
        }
        {//g={A=[J], B=[A, F], C=[E], D=[], E=[], F=[C], G=[], H=[A, F, G], I=[A, B, C], J=[E]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'J'),'B',Set.of( 'A','F'),'C',Set.of( 'E'),'D',Set.of(),'E',Set.of(),'F',Set.of( 'C'),'G',Set.of(),'H',Set.of( 'A','F','G'),'I',Set.of( 'A','B','C'),'J',Set.of( 'E')));
            assertEquals("ustia/Test_ustia", Set.of( 'D','E','G'), g.ustia());
        }
        {//g={A=[G, H], B=[E, I], C=[D, G, J], D=[A, C, G], E=[J], F=[], G=[A, I], H=[], I=[C], J=[A, C]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'G','H'),'B',Set.of( 'E','I'),'C',Set.of( 'D','G','J'),'D',Set.of( 'A','C','G'),'E',Set.of( 'J'),'F',Set.of(),'G',Set.of( 'A','I'),'H',Set.of(),'I',Set.of( 'C'),'J',Set.of( 'A','C')));
            assertEquals("ustia/Test_ustia", Set.of( 'F','H'), g.ustia());
        }
        {//g={A=[G], B=[E, F, H, I], C=[J], D=[B, H], E=[D, F, I, J], F=[I], G=[A, F], H=[D, F, G, I], I=[A, C, G], J=[F, G, I]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'G'),'B',Set.of( 'E','F','H','I'),'C',Set.of( 'J'),'D',Set.of( 'B','H'),'E',Set.of( 'D','F','I','J'),'F',Set.of( 'I'),'G',Set.of( 'A','F'),'H',Set.of( 'D','F','G','I'),'I',Set.of( 'A','C','G'),'J',Set.of( 'F','G','I')));
            assertEquals("ustia/Test_ustia", Set.of(), g.ustia());
        }
        {//g={A=[B, H], B=[C, D, F, I], C=[], D=[A, E, F], E=[], F=[D, G, I], G=[A, E], H=[D], I=[], J=[B]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'B','H'),'B',Set.of( 'C','D','F','I'),'C',Set.of(),'D',Set.of( 'A','E','F'),'E',Set.of(),'F',Set.of( 'D','G','I'),'G',Set.of( 'A','E'),'H',Set.of( 'D'),'I',Set.of(),'J',Set.of( 'B')));
            assertEquals("ustia/Test_ustia", Set.of( 'C','E','I'), g.ustia());
        }
        {//g={A=[G], B=[], C=[B, G, I], D=[F, H, J], E=[B, F, G, I], F=[G, I], G=[F, I], H=[B, G, I], I=[C], J=[B]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'G'),'B',Set.of(),'C',Set.of( 'B','G','I'),'D',Set.of( 'F','H','J'),'E',Set.of( 'B','F','G','I'),'F',Set.of( 'G','I'),'G',Set.of( 'F','I'),'H',Set.of( 'B','G','I'),'I',Set.of( 'C'),'J',Set.of( 'B')));
            assertEquals("ustia/Test_ustia", Set.of( 'B'), g.ustia());
        }
        {//g={A=[B, F], B=[C, H], C=[H], D=[G, J], E=[C], F=[B], G=[F, I, J], H=[B], I=[D, F, G], J=[B, G, H]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'B','F'),'B',Set.of( 'C','H'),'C',Set.of( 'H'),'D',Set.of( 'G','J'),'E',Set.of( 'C'),'F',Set.of( 'B'),'G',Set.of( 'F','I','J'),'H',Set.of( 'B'),'I',Set.of( 'D','F','G'),'J',Set.of( 'B','G','H')));
            assertEquals("ustia/Test_ustia", Set.of(), g.ustia());
        }
        {//g={A=[], B=[], C=[], D=[C, G, J], E=[G], F=[G], G=[E], H=[C, D, G, I], I=[C, D], J=[A, H]}
            var g = new Graph<Character>(Map.of( 'A',Set.of(),'B',Set.of(),'C',Set.of(),'D',Set.of( 'C','G','J'),'E',Set.of( 'G'),'F',Set.of( 'G'),'G',Set.of( 'E'),'H',Set.of( 'C','D','G','I'),'I',Set.of( 'C','D'),'J',Set.of( 'A','H')));
            assertEquals("ustia/Test_ustia", Set.of( 'A','B','C'), g.ustia());
        }
        {//g={A=[H, I, J], B=[], C=[B, F, H], D=[], E=[], F=[C, D, E], G=[C], H=[], I=[B, C], J=[D, F]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'H','I','J'),'B',Set.of(),'C',Set.of( 'B','F','H'),'D',Set.of(),'E',Set.of(),'F',Set.of( 'C','D','E'),'G',Set.of( 'C'),'H',Set.of(),'I',Set.of( 'B','C'),'J',Set.of( 'D','F')));
            assertEquals("ustia/Test_ustia", Set.of( 'B','D','E','H'), g.ustia());
        }
        {//g={A=[B, F, J], B=[], C=[], D=[F, G, J], E=[J], F=[], G=[D, I], H=[C], I=[A, G], J=[F, G, H]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'B','F','J'),'B',Set.of(),'C',Set.of(),'D',Set.of( 'F','G','J'),'E',Set.of( 'J'),'F',Set.of(),'G',Set.of( 'D','I'),'H',Set.of( 'C'),'I',Set.of( 'A','G'),'J',Set.of( 'F','G','H')));
            assertEquals("ustia/Test_ustia", Set.of( 'B','C','F'), g.ustia());
        }
        {//g={A=[B, E, H], B=[E], C=[A, E, F], D=[F], E=[H], F=[], G=[], H=[B, I, J], I=[], J=[C, D, F]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'B','E','H'),'B',Set.of( 'E'),'C',Set.of( 'A','E','F'),'D',Set.of( 'F'),'E',Set.of( 'H'),'F',Set.of(),'G',Set.of(),'H',Set.of( 'B','I','J'),'I',Set.of(),'J',Set.of( 'C','D','F')));
            assertEquals("ustia/Test_ustia", Set.of( 'F','G','I'), g.ustia());
        }
        {//g={A=[], B=[D], C=[], D=[G], E=[I], F=[B, G, J], G=[H], H=[A, B], I=[], J=[G]}
            var g = new Graph<Character>(Map.of( 'A',Set.of(),'B',Set.of( 'D'),'C',Set.of(),'D',Set.of( 'G'),'E',Set.of( 'I'),'F',Set.of( 'B','G','J'),'G',Set.of( 'H'),'H',Set.of( 'A','B'),'I',Set.of(),'J',Set.of( 'G')));
            assertEquals("ustia/Test_ustia", Set.of( 'A','C','I'), g.ustia());
        }
        {//g={A=[], B=[C, E], C=[], D=[C, G, J], E=[], F=[A, D, H], G=[C], H=[D], I=[A, E], J=[]}
            var g = new Graph<Character>(Map.of( 'A',Set.of(),'B',Set.of( 'C','E'),'C',Set.of(),'D',Set.of( 'C','G','J'),'E',Set.of(),'F',Set.of( 'A','D','H'),'G',Set.of( 'C'),'H',Set.of( 'D'),'I',Set.of( 'A','E'),'J',Set.of()));
            assertEquals("ustia/Test_ustia", Set.of( 'A','C','E','J'), g.ustia());
        }
        {//g={A=[C, F, J], B=[C, E, F], C=[A, B, J], D=[], E=[H], F=[], G=[B, I, J], H=[A], I=[D], J=[A, D, E, G]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'C','F','J'),'B',Set.of( 'C','E','F'),'C',Set.of( 'A','B','J'),'D',Set.of(),'E',Set.of( 'H'),'F',Set.of(),'G',Set.of( 'B','I','J'),'H',Set.of( 'A'),'I',Set.of( 'D'),'J',Set.of( 'A','D','E','G')));
            assertEquals("ustia/Test_ustia", Set.of( 'D','F'), g.ustia());
        }
        {//g={A=[C, G], B=[D, E, F], C=[B], D=[J], E=[B, D], F=[B, C, H, I], G=[A, B, E, H], H=[B, E, F], I=[], J=[A]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'C','G'),'B',Set.of( 'D','E','F'),'C',Set.of( 'B'),'D',Set.of( 'J'),'E',Set.of( 'B','D'),'F',Set.of( 'B','C','H','I'),'G',Set.of( 'A','B','E','H'),'H',Set.of( 'B','E','F'),'I',Set.of(),'J',Set.of( 'A')));
            assertEquals("ustia/Test_ustia", Set.of( 'I'), g.ustia());
        }
        {//g={A=[I], B=[], C=[A, E, J], D=[], E=[G, I], F=[D], G=[D, F, I], H=[], I=[C], J=[C, H]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'I'),'B',Set.of(),'C',Set.of( 'A','E','J'),'D',Set.of(),'E',Set.of( 'G','I'),'F',Set.of( 'D'),'G',Set.of( 'D','F','I'),'H',Set.of(),'I',Set.of( 'C'),'J',Set.of( 'C','H')));
            assertEquals("ustia/Test_ustia", Set.of( 'B','D','H'), g.ustia());
        }
        {//g={A=[], B=[], C=[D, G, J], D=[C, E], E=[I, J], F=[C, E], G=[D], H=[F, G], I=[], J=[D, H, I]}
            var g = new Graph<Character>(Map.of( 'A',Set.of(),'B',Set.of(),'C',Set.of( 'D','G','J'),'D',Set.of( 'C','E'),'E',Set.of( 'I','J'),'F',Set.of( 'C','E'),'G',Set.of( 'D'),'H',Set.of( 'F','G'),'I',Set.of(),'J',Set.of( 'D','H','I')));
            assertEquals("ustia/Test_ustia", Set.of( 'A','B','I'), g.ustia());
        }
        {//g={A=[D, F], B=[C, D, I], C=[], D=[A, G], E=[A, C], F=[E, J], G=[A, B, I], H=[C, G, I], I=[A, D, F, H], J=[A, D]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'D','F'),'B',Set.of( 'C','D','I'),'C',Set.of(),'D',Set.of( 'A','G'),'E',Set.of( 'A','C'),'F',Set.of( 'E','J'),'G',Set.of( 'A','B','I'),'H',Set.of( 'C','G','I'),'I',Set.of( 'A','D','F','H'),'J',Set.of( 'A','D')));
            assertEquals("ustia/Test_ustia", Set.of( 'C'), g.ustia());
        }
        {//g={A=[], B=[E, H], C=[], D=[], E=[F, H], F=[I, J], G=[], H=[], I=[D, E], J=[B, C, D]}
            var g = new Graph<Character>(Map.of( 'A',Set.of(),'B',Set.of( 'E','H'),'C',Set.of(),'D',Set.of(),'E',Set.of( 'F','H'),'F',Set.of( 'I','J'),'G',Set.of(),'H',Set.of(),'I',Set.of( 'D','E'),'J',Set.of( 'B','C','D')));
            assertEquals("ustia/Test_ustia", Set.of( 'A','C','D','G','H'), g.ustia());
        }
        {//g={A=[], B=[G], C=[A], D=[C, E], E=[], F=[C, D], G=[], H=[C, I], I=[C, H], J=[A]}
            var g = new Graph<Character>(Map.of( 'A',Set.of(),'B',Set.of( 'G'),'C',Set.of( 'A'),'D',Set.of( 'C','E'),'E',Set.of(),'F',Set.of( 'C','D'),'G',Set.of(),'H',Set.of( 'C','I'),'I',Set.of( 'C','H'),'J',Set.of( 'A')));
            assertEquals("ustia/Test_ustia", Set.of( 'A','E','G'), g.ustia());
        }
        {//g={A=[D, H, I], B=[], C=[E, G, I], D=[A, C], E=[F], F=[C, G, I], G=[D, J], H=[F, J], I=[D, E, H], J=[G]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'D','H','I'),'B',Set.of(),'C',Set.of( 'E','G','I'),'D',Set.of( 'A','C'),'E',Set.of( 'F'),'F',Set.of( 'C','G','I'),'G',Set.of( 'D','J'),'H',Set.of( 'F','J'),'I',Set.of( 'D','E','H'),'J',Set.of( 'G')));
            assertEquals("ustia/Test_ustia", Set.of( 'B'), g.ustia());
        }
        {//g={A=[G], B=[], C=[H], D=[], E=[], F=[A, D, J], G=[], H=[F], I=[], J=[D, I]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'G'),'B',Set.of(),'C',Set.of( 'H'),'D',Set.of(),'E',Set.of(),'F',Set.of( 'A','D','J'),'G',Set.of(),'H',Set.of( 'F'),'I',Set.of(),'J',Set.of( 'D','I')));
            assertEquals("ustia/Test_ustia", Set.of( 'B','D','E','G','I'), g.ustia());
        }
        {//g={A=[C, F], B=[J], C=[], D=[C, G, J], E=[], F=[A], G=[B], H=[A], I=[C, H], J=[B, D, G, H]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'C','F'),'B',Set.of( 'J'),'C',Set.of(),'D',Set.of( 'C','G','J'),'E',Set.of(),'F',Set.of( 'A'),'G',Set.of( 'B'),'H',Set.of( 'A'),'I',Set.of( 'C','H'),'J',Set.of( 'B','D','G','H')));
            assertEquals("ustia/Test_ustia", Set.of( 'C','E'), g.ustia());
        }
        {//g={A=[I], B=[E], C=[E, G, J], D=[J], E=[A, D, H, J], F=[], G=[C, E, F, H], H=[D, J], I=[F, H, J], J=[]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'I'),'B',Set.of( 'E'),'C',Set.of( 'E','G','J'),'D',Set.of( 'J'),'E',Set.of( 'A','D','H','J'),'F',Set.of(),'G',Set.of( 'C','E','F','H'),'H',Set.of( 'D','J'),'I',Set.of( 'F','H','J'),'J',Set.of()));
            assertEquals("ustia/Test_ustia", Set.of( 'F','J'), g.ustia());
        }
        {//g={A=[], B=[C, G, I], C=[E], D=[], E=[], F=[C, G], G=[C, E, I], H=[C, D, I], I=[C], J=[I]}
            var g = new Graph<Character>(Map.of( 'A',Set.of(),'B',Set.of( 'C','G','I'),'C',Set.of( 'E'),'D',Set.of(),'E',Set.of(),'F',Set.of( 'C','G'),'G',Set.of( 'C','E','I'),'H',Set.of( 'C','D','I'),'I',Set.of( 'C'),'J',Set.of( 'I')));
            assertEquals("ustia/Test_ustia", Set.of( 'A','D','E'), g.ustia());
        }
        {//g={A=[F], B=[D, H], C=[J], D=[H], E=[G], F=[], G=[A, F], H=[E, F, J], I=[F, J], J=[]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'F'),'B',Set.of( 'D','H'),'C',Set.of( 'J'),'D',Set.of( 'H'),'E',Set.of( 'G'),'F',Set.of(),'G',Set.of( 'A','F'),'H',Set.of( 'E','F','J'),'I',Set.of( 'F','J'),'J',Set.of()));
            assertEquals("ustia/Test_ustia", Set.of( 'F','J'), g.ustia());
        }
        {//g={A=[B, C, G, I], B=[A, H], C=[], D=[], E=[B, F, I, J], F=[], G=[D], H=[G], I=[A, C, D], J=[B, H, I]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'B','C','G','I'),'B',Set.of( 'A','H'),'C',Set.of(),'D',Set.of(),'E',Set.of( 'B','F','I','J'),'F',Set.of(),'G',Set.of( 'D'),'H',Set.of( 'G'),'I',Set.of( 'A','C','D'),'J',Set.of( 'B','H','I')));
            assertEquals("ustia/Test_ustia", Set.of( 'C','D','F'), g.ustia());
        }
        {//g={A=[J], B=[A, E, I], C=[], D=[B, C, E, F], E=[], F=[D, E, H, I], G=[A, J], H=[F, G], I=[A, H], J=[B, D, E]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'J'),'B',Set.of( 'A','E','I'),'C',Set.of(),'D',Set.of( 'B','C','E','F'),'E',Set.of(),'F',Set.of( 'D','E','H','I'),'G',Set.of( 'A','J'),'H',Set.of( 'F','G'),'I',Set.of( 'A','H'),'J',Set.of( 'B','D','E')));
            assertEquals("ustia/Test_ustia", Set.of( 'C','E'), g.ustia());
        }
        {//g={A=[], B=[A, E, I], C=[], D=[], E=[C, H, J], F=[E, H], G=[F, H], H=[C], I=[H], J=[B, C]}
            var g = new Graph<Character>(Map.of( 'A',Set.of(),'B',Set.of( 'A','E','I'),'C',Set.of(),'D',Set.of(),'E',Set.of( 'C','H','J'),'F',Set.of( 'E','H'),'G',Set.of( 'F','H'),'H',Set.of( 'C'),'I',Set.of( 'H'),'J',Set.of( 'B','C')));
            assertEquals("ustia/Test_ustia", Set.of( 'A','C','D'), g.ustia());
        }
        {//g={A=[], B=[F, I], C=[D, I], D=[A, B, E], E=[], F=[G, H], G=[A, C, F, H], H=[E], I=[B, D], J=[F]}
            var g = new Graph<Character>(Map.of( 'A',Set.of(),'B',Set.of( 'F','I'),'C',Set.of( 'D','I'),'D',Set.of( 'A','B','E'),'E',Set.of(),'F',Set.of( 'G','H'),'G',Set.of( 'A','C','F','H'),'H',Set.of( 'E'),'I',Set.of( 'B','D'),'J',Set.of( 'F')));
            assertEquals("ustia/Test_ustia", Set.of( 'A','E'), g.ustia());
        }
        {//g={A=[G], B=[A, I], C=[H], D=[], E=[C], F=[H, I, J], G=[], H=[C, E], I=[G], J=[]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'G'),'B',Set.of( 'A','I'),'C',Set.of( 'H'),'D',Set.of(),'E',Set.of( 'C'),'F',Set.of( 'H','I','J'),'G',Set.of(),'H',Set.of( 'C','E'),'I',Set.of( 'G'),'J',Set.of()));
            assertEquals("ustia/Test_ustia", Set.of( 'D','G','J'), g.ustia());
        }
        {//g={A=[], B=[C, H, J], C=[A, G, H], D=[], E=[], F=[B, I], G=[C], H=[A, C], I=[], J=[E]}
            var g = new Graph<Character>(Map.of( 'A',Set.of(),'B',Set.of( 'C','H','J'),'C',Set.of( 'A','G','H'),'D',Set.of(),'E',Set.of(),'F',Set.of( 'B','I'),'G',Set.of( 'C'),'H',Set.of( 'A','C'),'I',Set.of(),'J',Set.of( 'E')));
            assertEquals("ustia/Test_ustia", Set.of( 'A','D','E','I'), g.ustia());
        }
        {//g={A=[C, G, H], B=[F], C=[A, B, F], D=[E, G, I], E=[H], F=[C], G=[], H=[C], I=[J], J=[B, D, G]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'C','G','H'),'B',Set.of( 'F'),'C',Set.of( 'A','B','F'),'D',Set.of( 'E','G','I'),'E',Set.of( 'H'),'F',Set.of( 'C'),'G',Set.of(),'H',Set.of( 'C'),'I',Set.of( 'J'),'J',Set.of( 'B','D','G')));
            assertEquals("ustia/Test_ustia", Set.of( 'G'), g.ustia());
        }
        {//g={A=[D], B=[D, E], C=[], D=[A], E=[B, F], F=[], G=[B, D, H, I], H=[C, F, I], I=[B, D], J=[]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'D'),'B',Set.of( 'D','E'),'C',Set.of(),'D',Set.of( 'A'),'E',Set.of( 'B','F'),'F',Set.of(),'G',Set.of( 'B','D','H','I'),'H',Set.of( 'C','F','I'),'I',Set.of( 'B','D'),'J',Set.of()));
            assertEquals("ustia/Test_ustia", Set.of( 'C','F','J'), g.ustia());
        }
        {//g={A=[G, I, J], B=[E, I], C=[], D=[B, F, H], E=[], F=[], G=[A, F], H=[], I=[B, E, F, G], J=[]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'G','I','J'),'B',Set.of( 'E','I'),'C',Set.of(),'D',Set.of( 'B','F','H'),'E',Set.of(),'F',Set.of(),'G',Set.of( 'A','F'),'H',Set.of(),'I',Set.of( 'B','E','F','G'),'J',Set.of()));
            assertEquals("ustia/Test_ustia", Set.of( 'C','E','F','H','J'), g.ustia());
        }
        {//g={A=[G, I], B=[A, I, J], C=[D], D=[], E=[A], F=[B, E], G=[F, H], H=[], I=[B, C, E, F], J=[C, E]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'G','I'),'B',Set.of( 'A','I','J'),'C',Set.of( 'D'),'D',Set.of(),'E',Set.of( 'A'),'F',Set.of( 'B','E'),'G',Set.of( 'F','H'),'H',Set.of(),'I',Set.of( 'B','C','E','F'),'J',Set.of( 'C','E')));
            assertEquals("ustia/Test_ustia", Set.of( 'D','H'), g.ustia());
        }
        {//g={A=[C, G, J], B=[A, C, E, F], C=[], D=[], E=[D, G], F=[], G=[A, B, H], H=[J], I=[E], J=[B, E]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'C','G','J'),'B',Set.of( 'A','C','E','F'),'C',Set.of(),'D',Set.of(),'E',Set.of( 'D','G'),'F',Set.of(),'G',Set.of( 'A','B','H'),'H',Set.of( 'J'),'I',Set.of( 'E'),'J',Set.of( 'B','E')));
            assertEquals("ustia/Test_ustia", Set.of( 'C','D','F'), g.ustia());
        }
        {//g={A=[E, F, I], B=[J], C=[E, F], D=[E, F, I], E=[G, J], F=[D, E, I], G=[A, C, D, I], H=[A], I=[B, H, J], J=[F, G]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'E','F','I'),'B',Set.of( 'J'),'C',Set.of( 'E','F'),'D',Set.of( 'E','F','I'),'E',Set.of( 'G','J'),'F',Set.of( 'D','E','I'),'G',Set.of( 'A','C','D','I'),'H',Set.of( 'A'),'I',Set.of( 'B','H','J'),'J',Set.of( 'F','G')));
            assertEquals("ustia/Test_ustia", Set.of(), g.ustia());
        }
        {//g={A=[B, I], B=[], C=[A, E, H], D=[], E=[I], F=[H, J], G=[C, H], H=[D], I=[C, E], J=[B]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'B','I'),'B',Set.of(),'C',Set.of( 'A','E','H'),'D',Set.of(),'E',Set.of( 'I'),'F',Set.of( 'H','J'),'G',Set.of( 'C','H'),'H',Set.of( 'D'),'I',Set.of( 'C','E'),'J',Set.of( 'B')));
            assertEquals("ustia/Test_ustia", Set.of( 'B','D'), g.ustia());
        }
        {//g={A=[E], B=[E, J], C=[I, J], D=[B], E=[A, B], F=[B, C, E, H], G=[D], H=[], I=[A], J=[C, E, G]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'E'),'B',Set.of( 'E','J'),'C',Set.of( 'I','J'),'D',Set.of( 'B'),'E',Set.of( 'A','B'),'F',Set.of( 'B','C','E','H'),'G',Set.of( 'D'),'H',Set.of(),'I',Set.of( 'A'),'J',Set.of( 'C','E','G')));
            assertEquals("ustia/Test_ustia", Set.of( 'H'), g.ustia());
        }
        {//g={A=[E, G, J], B=[G], C=[F], D=[A, E, F], E=[B, D, F, J], F=[D], G=[D, I, J], H=[A, B], I=[A, C], J=[G]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'E','G','J'),'B',Set.of( 'G'),'C',Set.of( 'F'),'D',Set.of( 'A','E','F'),'E',Set.of( 'B','D','F','J'),'F',Set.of( 'D'),'G',Set.of( 'D','I','J'),'H',Set.of( 'A','B'),'I',Set.of( 'A','C'),'J',Set.of( 'G')));
            assertEquals("ustia/Test_ustia", Set.of(), g.ustia());
        }
        {//g={A=[D, E, G, H], B=[C, E, G], C=[A, B, H, J], D=[C, I], E=[H], F=[E, G, J], G=[I, J], H=[C, G], I=[B, C], J=[B, D, E]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'D','E','G','H'),'B',Set.of( 'C','E','G'),'C',Set.of( 'A','B','H','J'),'D',Set.of( 'C','I'),'E',Set.of( 'H'),'F',Set.of( 'E','G','J'),'G',Set.of( 'I','J'),'H',Set.of( 'C','G'),'I',Set.of( 'B','C'),'J',Set.of( 'B','D','E')));
            assertEquals("ustia/Test_ustia", Set.of(), g.ustia());
        }
        {//g={A=[G], B=[G], C=[D, H, I], D=[B, I], E=[A, B, I], F=[C, G], G=[E, F], H=[D, I], I=[], J=[C, F, H]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'G'),'B',Set.of( 'G'),'C',Set.of( 'D','H','I'),'D',Set.of( 'B','I'),'E',Set.of( 'A','B','I'),'F',Set.of( 'C','G'),'G',Set.of( 'E','F'),'H',Set.of( 'D','I'),'I',Set.of(),'J',Set.of( 'C','F','H')));
            assertEquals("ustia/Test_ustia", Set.of( 'I'), g.ustia());
        }
        {//g={A=[E], B=[], C=[G, H, I], D=[B, G, H, I], E=[B, F, G], F=[G], G=[], H=[I, J], I=[C, G], J=[B, I]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'E'),'B',Set.of(),'C',Set.of( 'G','H','I'),'D',Set.of( 'B','G','H','I'),'E',Set.of( 'B','F','G'),'F',Set.of( 'G'),'G',Set.of(),'H',Set.of( 'I','J'),'I',Set.of( 'C','G'),'J',Set.of( 'B','I')));
            assertEquals("ustia/Test_ustia", Set.of( 'B','G'), g.ustia());
        }
        {//g={A=[], B=[C, G], C=[B, D, J], D=[C, F], E=[B, I], F=[G, I, J], G=[F], H=[B, I], I=[B], J=[I]}
            var g = new Graph<Character>(Map.of( 'A',Set.of(),'B',Set.of( 'C','G'),'C',Set.of( 'B','D','J'),'D',Set.of( 'C','F'),'E',Set.of( 'B','I'),'F',Set.of( 'G','I','J'),'G',Set.of( 'F'),'H',Set.of( 'B','I'),'I',Set.of( 'B'),'J',Set.of( 'I')));
            assertEquals("ustia/Test_ustia", Set.of( 'A'), g.ustia());
        }
        {//g={A=[B, D], B=[G, H, J], C=[A, I], D=[B, G, J], E=[], F=[C, E, I], G=[H], H=[], I=[E, H, J], J=[]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'B','D'),'B',Set.of( 'G','H','J'),'C',Set.of( 'A','I'),'D',Set.of( 'B','G','J'),'E',Set.of(),'F',Set.of( 'C','E','I'),'G',Set.of( 'H'),'H',Set.of(),'I',Set.of( 'E','H','J'),'J',Set.of()));
            assertEquals("ustia/Test_ustia", Set.of( 'E','H','J'), g.ustia());
        }
        {//g={A=[], B=[G], C=[H], D=[F, G, J], E=[A, D], F=[A, B], G=[A], H=[A, D, I, J], I=[G, J], J=[G, H]}
            var g = new Graph<Character>(Map.of( 'A',Set.of(),'B',Set.of( 'G'),'C',Set.of( 'H'),'D',Set.of( 'F','G','J'),'E',Set.of( 'A','D'),'F',Set.of( 'A','B'),'G',Set.of( 'A'),'H',Set.of( 'A','D','I','J'),'I',Set.of( 'G','J'),'J',Set.of( 'G','H')));
            assertEquals("ustia/Test_ustia", Set.of( 'A'), g.ustia());
        }
        {//g={A=[C, G], B=[A, E, F, G], C=[D, I], D=[B], E=[F, H], F=[], G=[H], H=[D, E, I], I=[D, F, G], J=[A]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'C','G'),'B',Set.of( 'A','E','F','G'),'C',Set.of( 'D','I'),'D',Set.of( 'B'),'E',Set.of( 'F','H'),'F',Set.of(),'G',Set.of( 'H'),'H',Set.of( 'D','E','I'),'I',Set.of( 'D','F','G'),'J',Set.of( 'A')));
            assertEquals("ustia/Test_ustia", Set.of( 'F'), g.ustia());
        }
        {//g={A=[D, E], B=[C, F], C=[], D=[F, G], E=[B, C, H, J], F=[B, D], G=[F, H, I], H=[B], I=[C, F], J=[A, H]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'D','E'),'B',Set.of( 'C','F'),'C',Set.of(),'D',Set.of( 'F','G'),'E',Set.of( 'B','C','H','J'),'F',Set.of( 'B','D'),'G',Set.of( 'F','H','I'),'H',Set.of( 'B'),'I',Set.of( 'C','F'),'J',Set.of( 'A','H')));
            assertEquals("ustia/Test_ustia", Set.of( 'C'), g.ustia());
        }
        {//g={A=[], B=[I], C=[H], D=[], E=[F, G], F=[A, B], G=[], H=[F, I, J], I=[A, H], J=[D, F, G]}
            var g = new Graph<Character>(Map.of( 'A',Set.of(),'B',Set.of( 'I'),'C',Set.of( 'H'),'D',Set.of(),'E',Set.of( 'F','G'),'F',Set.of( 'A','B'),'G',Set.of(),'H',Set.of( 'F','I','J'),'I',Set.of( 'A','H'),'J',Set.of( 'D','F','G')));
            assertEquals("ustia/Test_ustia", Set.of( 'A','D','G'), g.ustia());
        }
        {//g={A=[], B=[F, I], C=[D, I], D=[B, E, J], E=[], F=[], G=[F], H=[A, G, J], I=[A, B, G], J=[H]}
            var g = new Graph<Character>(Map.of( 'A',Set.of(),'B',Set.of( 'F','I'),'C',Set.of( 'D','I'),'D',Set.of( 'B','E','J'),'E',Set.of(),'F',Set.of(),'G',Set.of( 'F'),'H',Set.of( 'A','G','J'),'I',Set.of( 'A','B','G'),'J',Set.of( 'H')));
            assertEquals("ustia/Test_ustia", Set.of( 'A','E','F'), g.ustia());
        }
        {//g={A=[D, G], B=[F, I, J], C=[], D=[A, G, H], E=[D, F, I], F=[A, B, G, J], G=[], H=[B], I=[B, C, J], J=[B, D, E]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'D','G'),'B',Set.of( 'F','I','J'),'C',Set.of(),'D',Set.of( 'A','G','H'),'E',Set.of( 'D','F','I'),'F',Set.of( 'A','B','G','J'),'G',Set.of(),'H',Set.of( 'B'),'I',Set.of( 'B','C','J'),'J',Set.of( 'B','D','E')));
            assertEquals("ustia/Test_ustia", Set.of( 'C','G'), g.ustia());
        }
        {//g={A=[], B=[D], C=[A, G, I], D=[B], E=[A, D], F=[E], G=[A, D], H=[A, B], I=[], J=[D, E, H]}
            var g = new Graph<Character>(Map.of( 'A',Set.of(),'B',Set.of( 'D'),'C',Set.of( 'A','G','I'),'D',Set.of( 'B'),'E',Set.of( 'A','D'),'F',Set.of( 'E'),'G',Set.of( 'A','D'),'H',Set.of( 'A','B'),'I',Set.of(),'J',Set.of( 'D','E','H')));
            assertEquals("ustia/Test_ustia", Set.of( 'A','I'), g.ustia());
        }
        {//g={A=[H], B=[D, I, J], C=[E], D=[B, H], E=[], F=[B], G=[C, E, F], H=[A], I=[E, F, G, J], J=[E, F]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'H'),'B',Set.of( 'D','I','J'),'C',Set.of( 'E'),'D',Set.of( 'B','H'),'E',Set.of(),'F',Set.of( 'B'),'G',Set.of( 'C','E','F'),'H',Set.of( 'A'),'I',Set.of( 'E','F','G','J'),'J',Set.of( 'E','F')));
            assertEquals("ustia/Test_ustia", Set.of( 'E'), g.ustia());
        }
        {//g={A=[], B=[G], C=[H, I], D=[F, H, I], E=[], F=[B, G, H], G=[], H=[], I=[C, H, J], J=[A]}
            var g = new Graph<Character>(Map.of( 'A',Set.of(),'B',Set.of( 'G'),'C',Set.of( 'H','I'),'D',Set.of( 'F','H','I'),'E',Set.of(),'F',Set.of( 'B','G','H'),'G',Set.of(),'H',Set.of(),'I',Set.of( 'C','H','J'),'J',Set.of( 'A')));
            assertEquals("ustia/Test_ustia", Set.of( 'A','E','G','H'), g.ustia());
        }
        {//g={A=[], B=[I], C=[E], D=[G], E=[], F=[], G=[A, B, I], H=[G], I=[F, H], J=[A]}
            var g = new Graph<Character>(Map.of( 'A',Set.of(),'B',Set.of( 'I'),'C',Set.of( 'E'),'D',Set.of( 'G'),'E',Set.of(),'F',Set.of(),'G',Set.of( 'A','B','I'),'H',Set.of( 'G'),'I',Set.of( 'F','H'),'J',Set.of( 'A')));
            assertEquals("ustia/Test_ustia", Set.of( 'A','E','F'), g.ustia());
        }
        {//g={A=[], B=[], C=[B], D=[], E=[G], F=[G], G=[C, F, H], H=[A, F, I], I=[B, G], J=[C]}
            var g = new Graph<Character>(Map.of( 'A',Set.of(),'B',Set.of(),'C',Set.of( 'B'),'D',Set.of(),'E',Set.of( 'G'),'F',Set.of( 'G'),'G',Set.of( 'C','F','H'),'H',Set.of( 'A','F','I'),'I',Set.of( 'B','G'),'J',Set.of( 'C')));
            assertEquals("ustia/Test_ustia", Set.of( 'A','B','D'), g.ustia());
        }
        {//g={A=[D, E, J], B=[], C=[B], D=[C, H], E=[D], F=[D, E], G=[], H=[C, E], I=[B, D, H], J=[A, F, I]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'D','E','J'),'B',Set.of(),'C',Set.of( 'B'),'D',Set.of( 'C','H'),'E',Set.of( 'D'),'F',Set.of( 'D','E'),'G',Set.of(),'H',Set.of( 'C','E'),'I',Set.of( 'B','D','H'),'J',Set.of( 'A','F','I')));
            assertEquals("ustia/Test_ustia", Set.of( 'B','G'), g.ustia());
        }
        {//g={A=[], B=[E, I, J], C=[D, E, H, I], D=[I], E=[], F=[A], G=[F, H], H=[], I=[E], J=[E]}
            var g = new Graph<Character>(Map.of( 'A',Set.of(),'B',Set.of( 'E','I','J'),'C',Set.of( 'D','E','H','I'),'D',Set.of( 'I'),'E',Set.of(),'F',Set.of( 'A'),'G',Set.of( 'F','H'),'H',Set.of(),'I',Set.of( 'E'),'J',Set.of( 'E')));
            assertEquals("ustia/Test_ustia", Set.of( 'A','E','H'), g.ustia());
        }
        {//g={A=[B, E, G, I], B=[C, I], C=[B, D, H], D=[], E=[B], F=[], G=[A, B], H=[B], I=[], J=[C, F]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'B','E','G','I'),'B',Set.of( 'C','I'),'C',Set.of( 'B','D','H'),'D',Set.of(),'E',Set.of( 'B'),'F',Set.of(),'G',Set.of( 'A','B'),'H',Set.of( 'B'),'I',Set.of(),'J',Set.of( 'C','F')));
            assertEquals("ustia/Test_ustia", Set.of( 'D','F','I'), g.ustia());
        }
        scoring.updateScore("lang:common_list_test_scoring_name",   17.0D);
    }
    @Test
    public void test_cesta() {
        {//g={A=[G, I], B=[A, E, F], C=[], D=[H], E=[C, F], F=[B, G], G=[C, E], H=[A, E, J], I=[], J=[A, B]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'G','I'),'B',Set.of( 'A','E','F'),'C',Set.of(),'D',Set.of( 'H'),'E',Set.of( 'C','F'),'F',Set.of( 'B','G'),'G',Set.of( 'C','E'),'H',Set.of( 'A','E','J'),'I',Set.of(),'J',Set.of( 'A','B')));
            assertEquals("cesta/Test_cesta('D','C')", true, g.cesta('D','C'));
            assertEquals("cesta/Test_cesta('D','I')", true, g.cesta('D','I'));
        }
        {//g={A=[G, J], B=[D, H, J], C=[F, H], D=[H], E=[], F=[B, D, H, I], G=[C, H, J], H=[], I=[D], J=[B, C]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'G','J'),'B',Set.of( 'D','H','J'),'C',Set.of( 'F','H'),'D',Set.of( 'H'),'E',Set.of(),'F',Set.of( 'B','D','H','I'),'G',Set.of( 'C','H','J'),'H',Set.of(),'I',Set.of( 'D'),'J',Set.of( 'B','C')));
            assertEquals("cesta/Test_cesta('A','E')", false, g.cesta('A','E'));
            assertEquals("cesta/Test_cesta('A','H')", true, g.cesta('A','H'));
            assertEquals("cesta/Test_cesta('E','E')", true, g.cesta('E','E'));
            assertEquals("cesta/Test_cesta('E','H')", false, g.cesta('E','H'));
        }
        {//g={A=[], B=[F, J], C=[A, H], D=[F, H, I], E=[], F=[], G=[E], H=[A, B, G], I=[A, E], J=[E]}
            var g = new Graph<Character>(Map.of( 'A',Set.of(),'B',Set.of( 'F','J'),'C',Set.of( 'A','H'),'D',Set.of( 'F','H','I'),'E',Set.of(),'F',Set.of(),'G',Set.of( 'E'),'H',Set.of( 'A','B','G'),'I',Set.of( 'A','E'),'J',Set.of( 'E')));
            assertEquals("cesta/Test_cesta('C','A')", true, g.cesta('C','A'));
            assertEquals("cesta/Test_cesta('C','E')", true, g.cesta('C','E'));
            assertEquals("cesta/Test_cesta('C','F')", true, g.cesta('C','F'));
            assertEquals("cesta/Test_cesta('D','A')", true, g.cesta('D','A'));
            assertEquals("cesta/Test_cesta('D','E')", true, g.cesta('D','E'));
            assertEquals("cesta/Test_cesta('D','F')", true, g.cesta('D','F'));
        }
        {//g={A=[B, F, H], B=[C, H], C=[], D=[J], E=[], F=[J], G=[B, E, F], H=[D, E], I=[B, C, F, H], J=[]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'B','F','H'),'B',Set.of( 'C','H'),'C',Set.of(),'D',Set.of( 'J'),'E',Set.of(),'F',Set.of( 'J'),'G',Set.of( 'B','E','F'),'H',Set.of( 'D','E'),'I',Set.of( 'B','C','F','H'),'J',Set.of()));
            assertEquals("cesta/Test_cesta('A','C')", true, g.cesta('A','C'));
            assertEquals("cesta/Test_cesta('A','E')", true, g.cesta('A','E'));
            assertEquals("cesta/Test_cesta('A','J')", true, g.cesta('A','J'));
            assertEquals("cesta/Test_cesta('G','C')", true, g.cesta('G','C'));
            assertEquals("cesta/Test_cesta('G','E')", true, g.cesta('G','E'));
            assertEquals("cesta/Test_cesta('G','J')", true, g.cesta('G','J'));
            assertEquals("cesta/Test_cesta('I','C')", true, g.cesta('I','C'));
            assertEquals("cesta/Test_cesta('I','E')", true, g.cesta('I','E'));
            assertEquals("cesta/Test_cesta('I','J')", true, g.cesta('I','J'));
        }
        {//g={A=[G, H], B=[D, J], C=[B], D=[H], E=[A], F=[], G=[C, F], H=[F, G], I=[A, D, G], J=[A]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'G','H'),'B',Set.of( 'D','J'),'C',Set.of( 'B'),'D',Set.of( 'H'),'E',Set.of( 'A'),'F',Set.of(),'G',Set.of( 'C','F'),'H',Set.of( 'F','G'),'I',Set.of( 'A','D','G'),'J',Set.of( 'A')));
            assertEquals("cesta/Test_cesta('E','F')", true, g.cesta('E','F'));
            assertEquals("cesta/Test_cesta('I','F')", true, g.cesta('I','F'));
        }
        {//g={A=[C, D], B=[G, H], C=[B, F], D=[A, F, H], E=[C], F=[], G=[E], H=[B, C, D, F], I=[A, C], J=[H, I]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'C','D'),'B',Set.of( 'G','H'),'C',Set.of( 'B','F'),'D',Set.of( 'A','F','H'),'E',Set.of( 'C'),'F',Set.of(),'G',Set.of( 'E'),'H',Set.of( 'B','C','D','F'),'I',Set.of( 'A','C'),'J',Set.of( 'H','I')));
            assertEquals("cesta/Test_cesta('J','F')", true, g.cesta('J','F'));
        }
        {//g={A=[C, J], B=[], C=[A, B, D, F], D=[B, E, I, J], E=[C], F=[C, E, J], G=[A, E], H=[A, E], I=[], J=[]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'C','J'),'B',Set.of(),'C',Set.of( 'A','B','D','F'),'D',Set.of( 'B','E','I','J'),'E',Set.of( 'C'),'F',Set.of( 'C','E','J'),'G',Set.of( 'A','E'),'H',Set.of( 'A','E'),'I',Set.of(),'J',Set.of()));
            assertEquals("cesta/Test_cesta('G','B')", true, g.cesta('G','B'));
            assertEquals("cesta/Test_cesta('G','I')", true, g.cesta('G','I'));
            assertEquals("cesta/Test_cesta('G','J')", true, g.cesta('G','J'));
            assertEquals("cesta/Test_cesta('H','B')", true, g.cesta('H','B'));
            assertEquals("cesta/Test_cesta('H','I')", true, g.cesta('H','I'));
            assertEquals("cesta/Test_cesta('H','J')", true, g.cesta('H','J'));
        }
        {//g={A=[G, H], B=[C, I], C=[G, I], D=[I], E=[B], F=[], G=[E, H], H=[], I=[], J=[G, I]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'G','H'),'B',Set.of( 'C','I'),'C',Set.of( 'G','I'),'D',Set.of( 'I'),'E',Set.of( 'B'),'F',Set.of(),'G',Set.of( 'E','H'),'H',Set.of(),'I',Set.of(),'J',Set.of( 'G','I')));
            assertEquals("cesta/Test_cesta('A','F')", false, g.cesta('A','F'));
            assertEquals("cesta/Test_cesta('A','H')", true, g.cesta('A','H'));
            assertEquals("cesta/Test_cesta('A','I')", true, g.cesta('A','I'));
            assertEquals("cesta/Test_cesta('D','F')", false, g.cesta('D','F'));
            assertEquals("cesta/Test_cesta('D','H')", false, g.cesta('D','H'));
            assertEquals("cesta/Test_cesta('D','I')", true, g.cesta('D','I'));
            assertEquals("cesta/Test_cesta('F','F')", true, g.cesta('F','F'));
            assertEquals("cesta/Test_cesta('F','H')", false, g.cesta('F','H'));
            assertEquals("cesta/Test_cesta('F','I')", false, g.cesta('F','I'));
            assertEquals("cesta/Test_cesta('J','F')", false, g.cesta('J','F'));
            assertEquals("cesta/Test_cesta('J','H')", true, g.cesta('J','H'));
            assertEquals("cesta/Test_cesta('J','I')", true, g.cesta('J','I'));
        }
        {//g={A=[G], B=[], C=[], D=[F, J], E=[D, F], F=[B, C, D], G=[D, J], H=[F, G, I], I=[J], J=[B, F]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'G'),'B',Set.of(),'C',Set.of(),'D',Set.of( 'F','J'),'E',Set.of( 'D','F'),'F',Set.of( 'B','C','D'),'G',Set.of( 'D','J'),'H',Set.of( 'F','G','I'),'I',Set.of( 'J'),'J',Set.of( 'B','F')));
            assertEquals("cesta/Test_cesta('A','B')", true, g.cesta('A','B'));
            assertEquals("cesta/Test_cesta('A','C')", true, g.cesta('A','C'));
            assertEquals("cesta/Test_cesta('E','B')", true, g.cesta('E','B'));
            assertEquals("cesta/Test_cesta('E','C')", true, g.cesta('E','C'));
            assertEquals("cesta/Test_cesta('H','B')", true, g.cesta('H','B'));
            assertEquals("cesta/Test_cesta('H','C')", true, g.cesta('H','C'));
        }
        {//g={A=[C, H, I], B=[C, G], C=[F], D=[A, F, G], E=[B], F=[C, E], G=[D, F], H=[C, G], I=[], J=[]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'C','H','I'),'B',Set.of( 'C','G'),'C',Set.of( 'F'),'D',Set.of( 'A','F','G'),'E',Set.of( 'B'),'F',Set.of( 'C','E'),'G',Set.of( 'D','F'),'H',Set.of( 'C','G'),'I',Set.of(),'J',Set.of()));
            assertEquals("cesta/Test_cesta('J','I')", false, g.cesta('J','I'));
            assertEquals("cesta/Test_cesta('J','J')", true, g.cesta('J','J'));
        }
        {//g={A=[], B=[F, G, I], C=[F], D=[A, E], E=[F], F=[A, H, I], G=[], H=[], I=[], J=[C]}
            var g = new Graph<Character>(Map.of( 'A',Set.of(),'B',Set.of( 'F','G','I'),'C',Set.of( 'F'),'D',Set.of( 'A','E'),'E',Set.of( 'F'),'F',Set.of( 'A','H','I'),'G',Set.of(),'H',Set.of(),'I',Set.of(),'J',Set.of( 'C')));
            assertEquals("cesta/Test_cesta('B','A')", true, g.cesta('B','A'));
            assertEquals("cesta/Test_cesta('B','G')", true, g.cesta('B','G'));
            assertEquals("cesta/Test_cesta('B','H')", true, g.cesta('B','H'));
            assertEquals("cesta/Test_cesta('B','I')", true, g.cesta('B','I'));
            assertEquals("cesta/Test_cesta('D','A')", true, g.cesta('D','A'));
            assertEquals("cesta/Test_cesta('D','G')", false, g.cesta('D','G'));
            assertEquals("cesta/Test_cesta('D','H')", true, g.cesta('D','H'));
            assertEquals("cesta/Test_cesta('D','I')", true, g.cesta('D','I'));
            assertEquals("cesta/Test_cesta('J','A')", true, g.cesta('J','A'));
            assertEquals("cesta/Test_cesta('J','G')", false, g.cesta('J','G'));
            assertEquals("cesta/Test_cesta('J','H')", true, g.cesta('J','H'));
            assertEquals("cesta/Test_cesta('J','I')", true, g.cesta('J','I'));
        }
        {//g={A=[C, I, J], B=[C, F], C=[A], D=[F], E=[A, B, I], F=[C], G=[B, H], H=[B, D, E], I=[A, C, J], J=[A]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'C','I','J'),'B',Set.of( 'C','F'),'C',Set.of( 'A'),'D',Set.of( 'F'),'E',Set.of( 'A','B','I'),'F',Set.of( 'C'),'G',Set.of( 'B','H'),'H',Set.of( 'B','D','E'),'I',Set.of( 'A','C','J'),'J',Set.of( 'A')));
        }
        {//g={A=[], B=[H, J], C=[], D=[B], E=[A, H, I], F=[J], G=[I], H=[], I=[A, F], J=[]}
            var g = new Graph<Character>(Map.of( 'A',Set.of(),'B',Set.of( 'H','J'),'C',Set.of(),'D',Set.of( 'B'),'E',Set.of( 'A','H','I'),'F',Set.of( 'J'),'G',Set.of( 'I'),'H',Set.of(),'I',Set.of( 'A','F'),'J',Set.of()));
            assertEquals("cesta/Test_cesta('C','A')", false, g.cesta('C','A'));
            assertEquals("cesta/Test_cesta('C','C')", true, g.cesta('C','C'));
            assertEquals("cesta/Test_cesta('C','H')", false, g.cesta('C','H'));
            assertEquals("cesta/Test_cesta('C','J')", false, g.cesta('C','J'));
            assertEquals("cesta/Test_cesta('D','A')", false, g.cesta('D','A'));
            assertEquals("cesta/Test_cesta('D','C')", false, g.cesta('D','C'));
            assertEquals("cesta/Test_cesta('D','H')", true, g.cesta('D','H'));
            assertEquals("cesta/Test_cesta('D','J')", true, g.cesta('D','J'));
            assertEquals("cesta/Test_cesta('E','A')", true, g.cesta('E','A'));
            assertEquals("cesta/Test_cesta('E','C')", false, g.cesta('E','C'));
            assertEquals("cesta/Test_cesta('E','H')", true, g.cesta('E','H'));
            assertEquals("cesta/Test_cesta('E','J')", true, g.cesta('E','J'));
            assertEquals("cesta/Test_cesta('G','A')", true, g.cesta('G','A'));
            assertEquals("cesta/Test_cesta('G','C')", false, g.cesta('G','C'));
            assertEquals("cesta/Test_cesta('G','H')", false, g.cesta('G','H'));
            assertEquals("cesta/Test_cesta('G','J')", true, g.cesta('G','J'));
        }
        {//g={A=[], B=[J], C=[F, H, I], D=[], E=[H], F=[C], G=[C, F, I], H=[A, C, F], I=[], J=[C, H]}
            var g = new Graph<Character>(Map.of( 'A',Set.of(),'B',Set.of( 'J'),'C',Set.of( 'F','H','I'),'D',Set.of(),'E',Set.of( 'H'),'F',Set.of( 'C'),'G',Set.of( 'C','F','I'),'H',Set.of( 'A','C','F'),'I',Set.of(),'J',Set.of( 'C','H')));
            assertEquals("cesta/Test_cesta('B','A')", true, g.cesta('B','A'));
            assertEquals("cesta/Test_cesta('B','D')", false, g.cesta('B','D'));
            assertEquals("cesta/Test_cesta('B','I')", true, g.cesta('B','I'));
            assertEquals("cesta/Test_cesta('D','A')", false, g.cesta('D','A'));
            assertEquals("cesta/Test_cesta('D','D')", true, g.cesta('D','D'));
            assertEquals("cesta/Test_cesta('D','I')", false, g.cesta('D','I'));
            assertEquals("cesta/Test_cesta('E','A')", true, g.cesta('E','A'));
            assertEquals("cesta/Test_cesta('E','D')", false, g.cesta('E','D'));
            assertEquals("cesta/Test_cesta('E','I')", true, g.cesta('E','I'));
            assertEquals("cesta/Test_cesta('G','A')", true, g.cesta('G','A'));
            assertEquals("cesta/Test_cesta('G','D')", false, g.cesta('G','D'));
            assertEquals("cesta/Test_cesta('G','I')", true, g.cesta('G','I'));
        }
        {//g={A=[G], B=[A, I, J], C=[D, G], D=[A, G, I], E=[], F=[], G=[], H=[], I=[F], J=[C, F]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'G'),'B',Set.of( 'A','I','J'),'C',Set.of( 'D','G'),'D',Set.of( 'A','G','I'),'E',Set.of(),'F',Set.of(),'G',Set.of(),'H',Set.of(),'I',Set.of( 'F'),'J',Set.of( 'C','F')));
            assertEquals("cesta/Test_cesta('B','E')", false, g.cesta('B','E'));
            assertEquals("cesta/Test_cesta('B','F')", true, g.cesta('B','F'));
            assertEquals("cesta/Test_cesta('B','G')", true, g.cesta('B','G'));
            assertEquals("cesta/Test_cesta('B','H')", false, g.cesta('B','H'));
            assertEquals("cesta/Test_cesta('E','E')", true, g.cesta('E','E'));
            assertEquals("cesta/Test_cesta('E','F')", false, g.cesta('E','F'));
            assertEquals("cesta/Test_cesta('E','G')", false, g.cesta('E','G'));
            assertEquals("cesta/Test_cesta('E','H')", false, g.cesta('E','H'));
            assertEquals("cesta/Test_cesta('H','E')", false, g.cesta('H','E'));
            assertEquals("cesta/Test_cesta('H','F')", false, g.cesta('H','F'));
            assertEquals("cesta/Test_cesta('H','G')", false, g.cesta('H','G'));
            assertEquals("cesta/Test_cesta('H','H')", true, g.cesta('H','H'));
        }
        {//g={A=[D, F], B=[], C=[], D=[A, G, I], E=[A, B, I], F=[H], G=[A, E, H, J], H=[], I=[E, H], J=[E, F, I]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'D','F'),'B',Set.of(),'C',Set.of(),'D',Set.of( 'A','G','I'),'E',Set.of( 'A','B','I'),'F',Set.of( 'H'),'G',Set.of( 'A','E','H','J'),'H',Set.of(),'I',Set.of( 'E','H'),'J',Set.of( 'E','F','I')));
            assertEquals("cesta/Test_cesta('C','B')", false, g.cesta('C','B'));
            assertEquals("cesta/Test_cesta('C','C')", true, g.cesta('C','C'));
            assertEquals("cesta/Test_cesta('C','H')", false, g.cesta('C','H'));
        }
        {//g={A=[C, J], B=[A, G], C=[], D=[], E=[A, D], F=[], G=[], H=[], I=[F, J], J=[]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'C','J'),'B',Set.of( 'A','G'),'C',Set.of(),'D',Set.of(),'E',Set.of( 'A','D'),'F',Set.of(),'G',Set.of(),'H',Set.of(),'I',Set.of( 'F','J'),'J',Set.of()));
            assertEquals("cesta/Test_cesta('B','C')", true, g.cesta('B','C'));
            assertEquals("cesta/Test_cesta('B','D')", false, g.cesta('B','D'));
            assertEquals("cesta/Test_cesta('B','F')", false, g.cesta('B','F'));
            assertEquals("cesta/Test_cesta('B','G')", true, g.cesta('B','G'));
            assertEquals("cesta/Test_cesta('B','H')", false, g.cesta('B','H'));
            assertEquals("cesta/Test_cesta('B','J')", true, g.cesta('B','J'));
            assertEquals("cesta/Test_cesta('E','C')", true, g.cesta('E','C'));
            assertEquals("cesta/Test_cesta('E','D')", true, g.cesta('E','D'));
            assertEquals("cesta/Test_cesta('E','F')", false, g.cesta('E','F'));
            assertEquals("cesta/Test_cesta('E','G')", false, g.cesta('E','G'));
            assertEquals("cesta/Test_cesta('E','H')", false, g.cesta('E','H'));
            assertEquals("cesta/Test_cesta('E','J')", true, g.cesta('E','J'));
            assertEquals("cesta/Test_cesta('H','C')", false, g.cesta('H','C'));
            assertEquals("cesta/Test_cesta('H','D')", false, g.cesta('H','D'));
            assertEquals("cesta/Test_cesta('H','F')", false, g.cesta('H','F'));
            assertEquals("cesta/Test_cesta('H','G')", false, g.cesta('H','G'));
            assertEquals("cesta/Test_cesta('H','H')", true, g.cesta('H','H'));
            assertEquals("cesta/Test_cesta('H','J')", false, g.cesta('H','J'));
            assertEquals("cesta/Test_cesta('I','C')", false, g.cesta('I','C'));
            assertEquals("cesta/Test_cesta('I','D')", false, g.cesta('I','D'));
            assertEquals("cesta/Test_cesta('I','F')", true, g.cesta('I','F'));
            assertEquals("cesta/Test_cesta('I','G')", false, g.cesta('I','G'));
            assertEquals("cesta/Test_cesta('I','H')", false, g.cesta('I','H'));
            assertEquals("cesta/Test_cesta('I','J')", true, g.cesta('I','J'));
        }
        {//g={A=[], B=[A, E, I, J], C=[E, G], D=[C], E=[H], F=[C, D, I, J], G=[A, I, J], H=[E, F, G], I=[], J=[C, D, I]}
            var g = new Graph<Character>(Map.of( 'A',Set.of(),'B',Set.of( 'A','E','I','J'),'C',Set.of( 'E','G'),'D',Set.of( 'C'),'E',Set.of( 'H'),'F',Set.of( 'C','D','I','J'),'G',Set.of( 'A','I','J'),'H',Set.of( 'E','F','G'),'I',Set.of(),'J',Set.of( 'C','D','I')));
            assertEquals("cesta/Test_cesta('B','A')", true, g.cesta('B','A'));
            assertEquals("cesta/Test_cesta('B','I')", true, g.cesta('B','I'));
        }
        {//g={A=[E, J], B=[], C=[E, G, J], D=[C, E, J], E=[G, I, J], F=[H], G=[A, D, I], H=[A, F], I=[G, H], J=[]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'E','J'),'B',Set.of(),'C',Set.of( 'E','G','J'),'D',Set.of( 'C','E','J'),'E',Set.of( 'G','I','J'),'F',Set.of( 'H'),'G',Set.of( 'A','D','I'),'H',Set.of( 'A','F'),'I',Set.of( 'G','H'),'J',Set.of()));
            assertEquals("cesta/Test_cesta('B','B')", true, g.cesta('B','B'));
            assertEquals("cesta/Test_cesta('B','J')", false, g.cesta('B','J'));
        }
        {//g={A=[F, G], B=[A, C], C=[B], D=[A, I], E=[D, F], F=[], G=[B, H], H=[A, D], I=[A, B, E], J=[B, E, F, G]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'F','G'),'B',Set.of( 'A','C'),'C',Set.of( 'B'),'D',Set.of( 'A','I'),'E',Set.of( 'D','F'),'F',Set.of(),'G',Set.of( 'B','H'),'H',Set.of( 'A','D'),'I',Set.of( 'A','B','E'),'J',Set.of( 'B','E','F','G')));
            assertEquals("cesta/Test_cesta('J','F')", true, g.cesta('J','F'));
        }
        {//g={A=[G], B=[G], C=[D, G], D=[F, J], E=[A, D], F=[E, G], G=[A, D, H, J], H=[], I=[H], J=[C, I]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'G'),'B',Set.of( 'G'),'C',Set.of( 'D','G'),'D',Set.of( 'F','J'),'E',Set.of( 'A','D'),'F',Set.of( 'E','G'),'G',Set.of( 'A','D','H','J'),'H',Set.of(),'I',Set.of( 'H'),'J',Set.of( 'C','I')));
            assertEquals("cesta/Test_cesta('B','H')", true, g.cesta('B','H'));
        }
        {//g={A=[], B=[G, J], C=[J], D=[G, J], E=[I], F=[J], G=[B, D, J], H=[G], I=[D, H, J], J=[D, H, I]}
            var g = new Graph<Character>(Map.of( 'A',Set.of(),'B',Set.of( 'G','J'),'C',Set.of( 'J'),'D',Set.of( 'G','J'),'E',Set.of( 'I'),'F',Set.of( 'J'),'G',Set.of( 'B','D','J'),'H',Set.of( 'G'),'I',Set.of( 'D','H','J'),'J',Set.of( 'D','H','I')));
            assertEquals("cesta/Test_cesta('A','A')", true, g.cesta('A','A'));
            assertEquals("cesta/Test_cesta('C','A')", false, g.cesta('C','A'));
            assertEquals("cesta/Test_cesta('E','A')", false, g.cesta('E','A'));
            assertEquals("cesta/Test_cesta('F','A')", false, g.cesta('F','A'));
        }
        {//g={A=[H, J], B=[], C=[], D=[F], E=[D, H], F=[B], G=[B, H], H=[], I=[F, G, H, J], J=[E]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'H','J'),'B',Set.of(),'C',Set.of(),'D',Set.of( 'F'),'E',Set.of( 'D','H'),'F',Set.of( 'B'),'G',Set.of( 'B','H'),'H',Set.of(),'I',Set.of( 'F','G','H','J'),'J',Set.of( 'E')));
            assertEquals("cesta/Test_cesta('A','B')", true, g.cesta('A','B'));
            assertEquals("cesta/Test_cesta('A','C')", false, g.cesta('A','C'));
            assertEquals("cesta/Test_cesta('A','H')", true, g.cesta('A','H'));
            assertEquals("cesta/Test_cesta('C','B')", false, g.cesta('C','B'));
            assertEquals("cesta/Test_cesta('C','C')", true, g.cesta('C','C'));
            assertEquals("cesta/Test_cesta('C','H')", false, g.cesta('C','H'));
            assertEquals("cesta/Test_cesta('I','B')", true, g.cesta('I','B'));
            assertEquals("cesta/Test_cesta('I','C')", false, g.cesta('I','C'));
            assertEquals("cesta/Test_cesta('I','H')", true, g.cesta('I','H'));
        }
        {//g={A=[C, F, J], B=[A, H], C=[D, G, H, J], D=[], E=[I], F=[], G=[], H=[G, I], I=[A, C, H], J=[A]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'C','F','J'),'B',Set.of( 'A','H'),'C',Set.of( 'D','G','H','J'),'D',Set.of(),'E',Set.of( 'I'),'F',Set.of(),'G',Set.of(),'H',Set.of( 'G','I'),'I',Set.of( 'A','C','H'),'J',Set.of( 'A')));
            assertEquals("cesta/Test_cesta('B','D')", true, g.cesta('B','D'));
            assertEquals("cesta/Test_cesta('B','F')", true, g.cesta('B','F'));
            assertEquals("cesta/Test_cesta('B','G')", true, g.cesta('B','G'));
            assertEquals("cesta/Test_cesta('E','D')", true, g.cesta('E','D'));
            assertEquals("cesta/Test_cesta('E','F')", true, g.cesta('E','F'));
            assertEquals("cesta/Test_cesta('E','G')", true, g.cesta('E','G'));
        }
        {//g={A=[], B=[E, G], C=[B, E, H], D=[C], E=[A, C], F=[B, E, I], G=[C, I], H=[A, E], I=[F, G, H], J=[B, C, D]}
            var g = new Graph<Character>(Map.of( 'A',Set.of(),'B',Set.of( 'E','G'),'C',Set.of( 'B','E','H'),'D',Set.of( 'C'),'E',Set.of( 'A','C'),'F',Set.of( 'B','E','I'),'G',Set.of( 'C','I'),'H',Set.of( 'A','E'),'I',Set.of( 'F','G','H'),'J',Set.of( 'B','C','D')));
            assertEquals("cesta/Test_cesta('J','A')", true, g.cesta('J','A'));
        }
        {//g={A=[D, H], B=[A, C, G, H], C=[A], D=[E, H, I], E=[B, C, D], F=[B, C, D], G=[D, E], H=[C, D, I], I=[], J=[H]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'D','H'),'B',Set.of( 'A','C','G','H'),'C',Set.of( 'A'),'D',Set.of( 'E','H','I'),'E',Set.of( 'B','C','D'),'F',Set.of( 'B','C','D'),'G',Set.of( 'D','E'),'H',Set.of( 'C','D','I'),'I',Set.of(),'J',Set.of( 'H')));
            assertEquals("cesta/Test_cesta('F','I')", true, g.cesta('F','I'));
            assertEquals("cesta/Test_cesta('J','I')", true, g.cesta('J','I'));
        }
        {//g={A=[G], B=[F, H, I], C=[D, H, I], D=[B, H, I, J], E=[], F=[H], G=[J], H=[B], I=[A, F, J], J=[]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'G'),'B',Set.of( 'F','H','I'),'C',Set.of( 'D','H','I'),'D',Set.of( 'B','H','I','J'),'E',Set.of(),'F',Set.of( 'H'),'G',Set.of( 'J'),'H',Set.of( 'B'),'I',Set.of( 'A','F','J'),'J',Set.of()));
            assertEquals("cesta/Test_cesta('C','E')", false, g.cesta('C','E'));
            assertEquals("cesta/Test_cesta('C','J')", true, g.cesta('C','J'));
            assertEquals("cesta/Test_cesta('E','E')", true, g.cesta('E','E'));
            assertEquals("cesta/Test_cesta('E','J')", false, g.cesta('E','J'));
        }
        {//g={A=[E], B=[A, I], C=[B, H, J], D=[E, G], E=[], F=[], G=[E], H=[], I=[F], J=[]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'E'),'B',Set.of( 'A','I'),'C',Set.of( 'B','H','J'),'D',Set.of( 'E','G'),'E',Set.of(),'F',Set.of(),'G',Set.of( 'E'),'H',Set.of(),'I',Set.of( 'F'),'J',Set.of()));
            assertEquals("cesta/Test_cesta('C','E')", true, g.cesta('C','E'));
            assertEquals("cesta/Test_cesta('C','F')", true, g.cesta('C','F'));
            assertEquals("cesta/Test_cesta('C','H')", true, g.cesta('C','H'));
            assertEquals("cesta/Test_cesta('C','J')", true, g.cesta('C','J'));
            assertEquals("cesta/Test_cesta('D','E')", true, g.cesta('D','E'));
            assertEquals("cesta/Test_cesta('D','F')", false, g.cesta('D','F'));
            assertEquals("cesta/Test_cesta('D','H')", false, g.cesta('D','H'));
            assertEquals("cesta/Test_cesta('D','J')", false, g.cesta('D','J'));
        }
        {//g={A=[D, E], B=[E, I, J], C=[B], D=[B, J], E=[B, D], F=[], G=[B, D, E, F], H=[C, E], I=[C, E], J=[C, I]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'D','E'),'B',Set.of( 'E','I','J'),'C',Set.of( 'B'),'D',Set.of( 'B','J'),'E',Set.of( 'B','D'),'F',Set.of(),'G',Set.of( 'B','D','E','F'),'H',Set.of( 'C','E'),'I',Set.of( 'C','E'),'J',Set.of( 'C','I')));
            assertEquals("cesta/Test_cesta('A','F')", false, g.cesta('A','F'));
            assertEquals("cesta/Test_cesta('G','F')", true, g.cesta('G','F'));
            assertEquals("cesta/Test_cesta('H','F')", false, g.cesta('H','F'));
        }
        {//g={A=[D], B=[D], C=[F, I], D=[B, I], E=[A, C], F=[B], G=[A], H=[A, C, J], I=[], J=[F, G]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'D'),'B',Set.of( 'D'),'C',Set.of( 'F','I'),'D',Set.of( 'B','I'),'E',Set.of( 'A','C'),'F',Set.of( 'B'),'G',Set.of( 'A'),'H',Set.of( 'A','C','J'),'I',Set.of(),'J',Set.of( 'F','G')));
            assertEquals("cesta/Test_cesta('E','I')", true, g.cesta('E','I'));
            assertEquals("cesta/Test_cesta('H','I')", true, g.cesta('H','I'));
        }
        {//g={A=[], B=[C, G], C=[F, J], D=[], E=[], F=[C], G=[D, E, H], H=[C, E, F], I=[B, C], J=[G]}
            var g = new Graph<Character>(Map.of( 'A',Set.of(),'B',Set.of( 'C','G'),'C',Set.of( 'F','J'),'D',Set.of(),'E',Set.of(),'F',Set.of( 'C'),'G',Set.of( 'D','E','H'),'H',Set.of( 'C','E','F'),'I',Set.of( 'B','C'),'J',Set.of( 'G')));
            assertEquals("cesta/Test_cesta('A','A')", true, g.cesta('A','A'));
            assertEquals("cesta/Test_cesta('A','D')", false, g.cesta('A','D'));
            assertEquals("cesta/Test_cesta('A','E')", false, g.cesta('A','E'));
            assertEquals("cesta/Test_cesta('I','A')", false, g.cesta('I','A'));
            assertEquals("cesta/Test_cesta('I','D')", true, g.cesta('I','D'));
            assertEquals("cesta/Test_cesta('I','E')", true, g.cesta('I','E'));
        }
        {//g={A=[C, E], B=[F], C=[A, J], D=[A, B, F, H], E=[B, G], F=[A], G=[], H=[A, G, J], I=[C, J], J=[A, I]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'C','E'),'B',Set.of( 'F'),'C',Set.of( 'A','J'),'D',Set.of( 'A','B','F','H'),'E',Set.of( 'B','G'),'F',Set.of( 'A'),'G',Set.of(),'H',Set.of( 'A','G','J'),'I',Set.of( 'C','J'),'J',Set.of( 'A','I')));
            assertEquals("cesta/Test_cesta('D','G')", true, g.cesta('D','G'));
        }
        {//g={A=[I], B=[], C=[], D=[I, J], E=[B], F=[G, I, J], G=[D], H=[A, C], I=[], J=[]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'I'),'B',Set.of(),'C',Set.of(),'D',Set.of( 'I','J'),'E',Set.of( 'B'),'F',Set.of( 'G','I','J'),'G',Set.of( 'D'),'H',Set.of( 'A','C'),'I',Set.of(),'J',Set.of()));
            assertEquals("cesta/Test_cesta('E','B')", true, g.cesta('E','B'));
            assertEquals("cesta/Test_cesta('E','C')", false, g.cesta('E','C'));
            assertEquals("cesta/Test_cesta('E','I')", false, g.cesta('E','I'));
            assertEquals("cesta/Test_cesta('E','J')", false, g.cesta('E','J'));
            assertEquals("cesta/Test_cesta('F','B')", false, g.cesta('F','B'));
            assertEquals("cesta/Test_cesta('F','C')", false, g.cesta('F','C'));
            assertEquals("cesta/Test_cesta('F','I')", true, g.cesta('F','I'));
            assertEquals("cesta/Test_cesta('F','J')", true, g.cesta('F','J'));
            assertEquals("cesta/Test_cesta('H','B')", false, g.cesta('H','B'));
            assertEquals("cesta/Test_cesta('H','C')", true, g.cesta('H','C'));
            assertEquals("cesta/Test_cesta('H','I')", true, g.cesta('H','I'));
            assertEquals("cesta/Test_cesta('H','J')", false, g.cesta('H','J'));
        }
        {//g={A=[F, I], B=[], C=[B, G, H], D=[C, F, G, H], E=[], F=[G, J], G=[C, F, H], H=[], I=[C, F], J=[C]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'F','I'),'B',Set.of(),'C',Set.of( 'B','G','H'),'D',Set.of( 'C','F','G','H'),'E',Set.of(),'F',Set.of( 'G','J'),'G',Set.of( 'C','F','H'),'H',Set.of(),'I',Set.of( 'C','F'),'J',Set.of( 'C')));
            assertEquals("cesta/Test_cesta('A','B')", true, g.cesta('A','B'));
            assertEquals("cesta/Test_cesta('A','E')", false, g.cesta('A','E'));
            assertEquals("cesta/Test_cesta('A','H')", true, g.cesta('A','H'));
            assertEquals("cesta/Test_cesta('D','B')", true, g.cesta('D','B'));
            assertEquals("cesta/Test_cesta('D','E')", false, g.cesta('D','E'));
            assertEquals("cesta/Test_cesta('D','H')", true, g.cesta('D','H'));
            assertEquals("cesta/Test_cesta('E','B')", false, g.cesta('E','B'));
            assertEquals("cesta/Test_cesta('E','E')", true, g.cesta('E','E'));
            assertEquals("cesta/Test_cesta('E','H')", false, g.cesta('E','H'));
        }
        {//g={A=[J], B=[E, I], C=[D, H, J], D=[I], E=[H], F=[A, I, J], G=[B], H=[G], I=[B, D, H], J=[A, H]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'J'),'B',Set.of( 'E','I'),'C',Set.of( 'D','H','J'),'D',Set.of( 'I'),'E',Set.of( 'H'),'F',Set.of( 'A','I','J'),'G',Set.of( 'B'),'H',Set.of( 'G'),'I',Set.of( 'B','D','H'),'J',Set.of( 'A','H')));
        }
        {//g={A=[I, J], B=[I], C=[], D=[A, F, I], E=[D, J], F=[], G=[H, J], H=[E], I=[B, G], J=[]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'I','J'),'B',Set.of( 'I'),'C',Set.of(),'D',Set.of( 'A','F','I'),'E',Set.of( 'D','J'),'F',Set.of(),'G',Set.of( 'H','J'),'H',Set.of( 'E'),'I',Set.of( 'B','G'),'J',Set.of()));
            assertEquals("cesta/Test_cesta('C','C')", true, g.cesta('C','C'));
            assertEquals("cesta/Test_cesta('C','F')", false, g.cesta('C','F'));
            assertEquals("cesta/Test_cesta('C','J')", false, g.cesta('C','J'));
        }
        {//g={A=[H], B=[F], C=[F, G, I], D=[C], E=[D, G, H], F=[A, D], G=[B], H=[C, D, G], I=[G, H, J], J=[B, F, I]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'H'),'B',Set.of( 'F'),'C',Set.of( 'F','G','I'),'D',Set.of( 'C'),'E',Set.of( 'D','G','H'),'F',Set.of( 'A','D'),'G',Set.of( 'B'),'H',Set.of( 'C','D','G'),'I',Set.of( 'G','H','J'),'J',Set.of( 'B','F','I')));
        }
        {//g={A=[H], B=[C], C=[], D=[B, C, E], E=[A, B, C], F=[H, J], G=[D, J], H=[A, I], I=[F], J=[E, G]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'H'),'B',Set.of( 'C'),'C',Set.of(),'D',Set.of( 'B','C','E'),'E',Set.of( 'A','B','C'),'F',Set.of( 'H','J'),'G',Set.of( 'D','J'),'H',Set.of( 'A','I'),'I',Set.of( 'F'),'J',Set.of( 'E','G')));
        }
        {//g={A=[C, D], B=[C, D, F, J], C=[E], D=[C, F], E=[A, I], F=[C], G=[A, C], H=[], I=[C, H], J=[]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'C','D'),'B',Set.of( 'C','D','F','J'),'C',Set.of( 'E'),'D',Set.of( 'C','F'),'E',Set.of( 'A','I'),'F',Set.of( 'C'),'G',Set.of( 'A','C'),'H',Set.of(),'I',Set.of( 'C','H'),'J',Set.of()));
            assertEquals("cesta/Test_cesta('B','H')", true, g.cesta('B','H'));
            assertEquals("cesta/Test_cesta('B','J')", true, g.cesta('B','J'));
            assertEquals("cesta/Test_cesta('G','H')", true, g.cesta('G','H'));
            assertEquals("cesta/Test_cesta('G','J')", false, g.cesta('G','J'));
        }
        {//g={A=[J], B=[A, C, H], C=[I], D=[E, H], E=[D, F, G], F=[J], G=[C, E, H, J], H=[G, I], I=[E], J=[C]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'J'),'B',Set.of( 'A','C','H'),'C',Set.of( 'I'),'D',Set.of( 'E','H'),'E',Set.of( 'D','F','G'),'F',Set.of( 'J'),'G',Set.of( 'C','E','H','J'),'H',Set.of( 'G','I'),'I',Set.of( 'E'),'J',Set.of( 'C')));
        }
        {//g={A=[], B=[A, C, G], C=[E], D=[C, E], E=[], F=[A, D, G, I], G=[], H=[], I=[B, E, F, H], J=[C, G]}
            var g = new Graph<Character>(Map.of( 'A',Set.of(),'B',Set.of( 'A','C','G'),'C',Set.of( 'E'),'D',Set.of( 'C','E'),'E',Set.of(),'F',Set.of( 'A','D','G','I'),'G',Set.of(),'H',Set.of(),'I',Set.of( 'B','E','F','H'),'J',Set.of( 'C','G')));
            assertEquals("cesta/Test_cesta('J','A')", false, g.cesta('J','A'));
            assertEquals("cesta/Test_cesta('J','E')", true, g.cesta('J','E'));
            assertEquals("cesta/Test_cesta('J','G')", true, g.cesta('J','G'));
            assertEquals("cesta/Test_cesta('J','H')", false, g.cesta('J','H'));
        }
        {//g={A=[B, D, I], B=[J], C=[A, D], D=[], E=[C, G, H, I], F=[A, E, J], G=[A, B], H=[F, I], I=[], J=[F, I]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'B','D','I'),'B',Set.of( 'J'),'C',Set.of( 'A','D'),'D',Set.of(),'E',Set.of( 'C','G','H','I'),'F',Set.of( 'A','E','J'),'G',Set.of( 'A','B'),'H',Set.of( 'F','I'),'I',Set.of(),'J',Set.of( 'F','I')));
        }
        {//g={A=[E, J], B=[D, F, J], C=[B], D=[C], E=[H, I, J], F=[D, E, G], G=[C, F, H], H=[B, D], I=[A, E, G], J=[]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'E','J'),'B',Set.of( 'D','F','J'),'C',Set.of( 'B'),'D',Set.of( 'C'),'E',Set.of( 'H','I','J'),'F',Set.of( 'D','E','G'),'G',Set.of( 'C','F','H'),'H',Set.of( 'B','D'),'I',Set.of( 'A','E','G'),'J',Set.of()));
        }
        {//g={A=[], B=[], C=[A], D=[A], E=[I], F=[A, I, J], G=[A], H=[C, D], I=[C], J=[]}
            var g = new Graph<Character>(Map.of( 'A',Set.of(),'B',Set.of(),'C',Set.of( 'A'),'D',Set.of( 'A'),'E',Set.of( 'I'),'F',Set.of( 'A','I','J'),'G',Set.of( 'A'),'H',Set.of( 'C','D'),'I',Set.of( 'C'),'J',Set.of()));
            assertEquals("cesta/Test_cesta('B','A')", false, g.cesta('B','A'));
            assertEquals("cesta/Test_cesta('B','B')", true, g.cesta('B','B'));
            assertEquals("cesta/Test_cesta('B','J')", false, g.cesta('B','J'));
            assertEquals("cesta/Test_cesta('E','A')", true, g.cesta('E','A'));
            assertEquals("cesta/Test_cesta('E','B')", false, g.cesta('E','B'));
            assertEquals("cesta/Test_cesta('E','J')", false, g.cesta('E','J'));
            assertEquals("cesta/Test_cesta('F','A')", true, g.cesta('F','A'));
            assertEquals("cesta/Test_cesta('F','B')", false, g.cesta('F','B'));
            assertEquals("cesta/Test_cesta('F','J')", true, g.cesta('F','J'));
            assertEquals("cesta/Test_cesta('G','A')", true, g.cesta('G','A'));
            assertEquals("cesta/Test_cesta('G','B')", false, g.cesta('G','B'));
            assertEquals("cesta/Test_cesta('G','J')", false, g.cesta('G','J'));
            assertEquals("cesta/Test_cesta('H','A')", true, g.cesta('H','A'));
            assertEquals("cesta/Test_cesta('H','B')", false, g.cesta('H','B'));
            assertEquals("cesta/Test_cesta('H','J')", false, g.cesta('H','J'));
        }
        {//g={A=[F, H], B=[C, D, H], C=[B, G], D=[F, I], E=[], F=[C], G=[J], H=[J], I=[D, J], J=[G]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'F','H'),'B',Set.of( 'C','D','H'),'C',Set.of( 'B','G'),'D',Set.of( 'F','I'),'E',Set.of(),'F',Set.of( 'C'),'G',Set.of( 'J'),'H',Set.of( 'J'),'I',Set.of( 'D','J'),'J',Set.of( 'G')));
            assertEquals("cesta/Test_cesta('A','E')", false, g.cesta('A','E'));
            assertEquals("cesta/Test_cesta('E','E')", true, g.cesta('E','E'));
        }
        {//g={A=[B, I], B=[C], C=[], D=[E, F, I], E=[B, H], F=[J], G=[C], H=[], I=[C, G, J], J=[B, C, F, G]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'B','I'),'B',Set.of( 'C'),'C',Set.of(),'D',Set.of( 'E','F','I'),'E',Set.of( 'B','H'),'F',Set.of( 'J'),'G',Set.of( 'C'),'H',Set.of(),'I',Set.of( 'C','G','J'),'J',Set.of( 'B','C','F','G')));
            assertEquals("cesta/Test_cesta('A','C')", true, g.cesta('A','C'));
            assertEquals("cesta/Test_cesta('A','H')", false, g.cesta('A','H'));
            assertEquals("cesta/Test_cesta('D','C')", true, g.cesta('D','C'));
            assertEquals("cesta/Test_cesta('D','H')", true, g.cesta('D','H'));
        }
        {//g={A=[I], B=[J], C=[G, J], D=[G], E=[I], F=[B, E, G], G=[], H=[A], I=[B, C], J=[F, I]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'I'),'B',Set.of( 'J'),'C',Set.of( 'G','J'),'D',Set.of( 'G'),'E',Set.of( 'I'),'F',Set.of( 'B','E','G'),'G',Set.of(),'H',Set.of( 'A'),'I',Set.of( 'B','C'),'J',Set.of( 'F','I')));
            assertEquals("cesta/Test_cesta('D','G')", true, g.cesta('D','G'));
            assertEquals("cesta/Test_cesta('H','G')", true, g.cesta('H','G'));
        }
        {//g={A=[C], B=[], C=[A, B, F], D=[A, J], E=[F], F=[B], G=[], H=[A, C, I], I=[C], J=[C]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'C'),'B',Set.of(),'C',Set.of( 'A','B','F'),'D',Set.of( 'A','J'),'E',Set.of( 'F'),'F',Set.of( 'B'),'G',Set.of(),'H',Set.of( 'A','C','I'),'I',Set.of( 'C'),'J',Set.of( 'C')));
            assertEquals("cesta/Test_cesta('D','B')", true, g.cesta('D','B'));
            assertEquals("cesta/Test_cesta('D','G')", false, g.cesta('D','G'));
            assertEquals("cesta/Test_cesta('E','B')", true, g.cesta('E','B'));
            assertEquals("cesta/Test_cesta('E','G')", false, g.cesta('E','G'));
            assertEquals("cesta/Test_cesta('G','B')", false, g.cesta('G','B'));
            assertEquals("cesta/Test_cesta('G','G')", true, g.cesta('G','G'));
            assertEquals("cesta/Test_cesta('H','B')", true, g.cesta('H','B'));
            assertEquals("cesta/Test_cesta('H','G')", false, g.cesta('H','G'));
        }
        {//g={A=[B, C, G, I], B=[C], C=[J], D=[], E=[G], F=[B], G=[D, F, I], H=[I], I=[J], J=[A, D]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'B','C','G','I'),'B',Set.of( 'C'),'C',Set.of( 'J'),'D',Set.of(),'E',Set.of( 'G'),'F',Set.of( 'B'),'G',Set.of( 'D','F','I'),'H',Set.of( 'I'),'I',Set.of( 'J'),'J',Set.of( 'A','D')));
            assertEquals("cesta/Test_cesta('E','D')", true, g.cesta('E','D'));
            assertEquals("cesta/Test_cesta('H','D')", true, g.cesta('H','D'));
        }
        {//g={A=[G], B=[A, H], C=[B, E], D=[H, I], E=[B, C, G], F=[], G=[A, D, H], H=[I], I=[], J=[C, H]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'G'),'B',Set.of( 'A','H'),'C',Set.of( 'B','E'),'D',Set.of( 'H','I'),'E',Set.of( 'B','C','G'),'F',Set.of(),'G',Set.of( 'A','D','H'),'H',Set.of( 'I'),'I',Set.of(),'J',Set.of( 'C','H')));
            assertEquals("cesta/Test_cesta('F','F')", true, g.cesta('F','F'));
            assertEquals("cesta/Test_cesta('F','I')", false, g.cesta('F','I'));
            assertEquals("cesta/Test_cesta('J','F')", false, g.cesta('J','F'));
            assertEquals("cesta/Test_cesta('J','I')", true, g.cesta('J','I'));
        }
        {//g={A=[], B=[A, E, I], C=[], D=[B, C], E=[B], F=[A], G=[C, H, I], H=[A, F], I=[B], J=[B, E, H]}
            var g = new Graph<Character>(Map.of( 'A',Set.of(),'B',Set.of( 'A','E','I'),'C',Set.of(),'D',Set.of( 'B','C'),'E',Set.of( 'B'),'F',Set.of( 'A'),'G',Set.of( 'C','H','I'),'H',Set.of( 'A','F'),'I',Set.of( 'B'),'J',Set.of( 'B','E','H')));
            assertEquals("cesta/Test_cesta('D','A')", true, g.cesta('D','A'));
            assertEquals("cesta/Test_cesta('D','C')", true, g.cesta('D','C'));
            assertEquals("cesta/Test_cesta('G','A')", true, g.cesta('G','A'));
            assertEquals("cesta/Test_cesta('G','C')", true, g.cesta('G','C'));
            assertEquals("cesta/Test_cesta('J','A')", true, g.cesta('J','A'));
            assertEquals("cesta/Test_cesta('J','C')", false, g.cesta('J','C'));
        }
        {//g={A=[H], B=[], C=[I], D=[A, E, G, H], E=[], F=[D, H, I], G=[], H=[B], I=[], J=[C]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'H'),'B',Set.of(),'C',Set.of( 'I'),'D',Set.of( 'A','E','G','H'),'E',Set.of(),'F',Set.of( 'D','H','I'),'G',Set.of(),'H',Set.of( 'B'),'I',Set.of(),'J',Set.of( 'C')));
            assertEquals("cesta/Test_cesta('F','B')", true, g.cesta('F','B'));
            assertEquals("cesta/Test_cesta('F','E')", true, g.cesta('F','E'));
            assertEquals("cesta/Test_cesta('F','G')", true, g.cesta('F','G'));
            assertEquals("cesta/Test_cesta('F','I')", true, g.cesta('F','I'));
            assertEquals("cesta/Test_cesta('J','B')", false, g.cesta('J','B'));
            assertEquals("cesta/Test_cesta('J','E')", false, g.cesta('J','E'));
            assertEquals("cesta/Test_cesta('J','G')", false, g.cesta('J','G'));
            assertEquals("cesta/Test_cesta('J','I')", true, g.cesta('J','I'));
        }
        {//g={A=[G], B=[J], C=[I], D=[F], E=[], F=[E, J], G=[D], H=[G], I=[A, G], J=[]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'G'),'B',Set.of( 'J'),'C',Set.of( 'I'),'D',Set.of( 'F'),'E',Set.of(),'F',Set.of( 'E','J'),'G',Set.of( 'D'),'H',Set.of( 'G'),'I',Set.of( 'A','G'),'J',Set.of()));
            assertEquals("cesta/Test_cesta('B','E')", false, g.cesta('B','E'));
            assertEquals("cesta/Test_cesta('B','J')", true, g.cesta('B','J'));
            assertEquals("cesta/Test_cesta('C','E')", true, g.cesta('C','E'));
            assertEquals("cesta/Test_cesta('C','J')", true, g.cesta('C','J'));
            assertEquals("cesta/Test_cesta('H','E')", true, g.cesta('H','E'));
            assertEquals("cesta/Test_cesta('H','J')", true, g.cesta('H','J'));
        }
        {//g={A=[], B=[D, G, I], C=[], D=[C], E=[], F=[A], G=[A, B, E], H=[B, C, E, G], I=[D, E, G], J=[]}
            var g = new Graph<Character>(Map.of( 'A',Set.of(),'B',Set.of( 'D','G','I'),'C',Set.of(),'D',Set.of( 'C'),'E',Set.of(),'F',Set.of( 'A'),'G',Set.of( 'A','B','E'),'H',Set.of( 'B','C','E','G'),'I',Set.of( 'D','E','G'),'J',Set.of()));
            assertEquals("cesta/Test_cesta('F','A')", true, g.cesta('F','A'));
            assertEquals("cesta/Test_cesta('F','C')", false, g.cesta('F','C'));
            assertEquals("cesta/Test_cesta('F','E')", false, g.cesta('F','E'));
            assertEquals("cesta/Test_cesta('F','J')", false, g.cesta('F','J'));
            assertEquals("cesta/Test_cesta('H','A')", true, g.cesta('H','A'));
            assertEquals("cesta/Test_cesta('H','C')", true, g.cesta('H','C'));
            assertEquals("cesta/Test_cesta('H','E')", true, g.cesta('H','E'));
            assertEquals("cesta/Test_cesta('H','J')", false, g.cesta('H','J'));
            assertEquals("cesta/Test_cesta('J','A')", false, g.cesta('J','A'));
            assertEquals("cesta/Test_cesta('J','C')", false, g.cesta('J','C'));
            assertEquals("cesta/Test_cesta('J','E')", false, g.cesta('J','E'));
            assertEquals("cesta/Test_cesta('J','J')", true, g.cesta('J','J'));
        }
        {//g={A=[C, J], B=[G], C=[F, I], D=[], E=[I, J], F=[C], G=[D, H, J], H=[E, F], I=[J], J=[A, I]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'C','J'),'B',Set.of( 'G'),'C',Set.of( 'F','I'),'D',Set.of(),'E',Set.of( 'I','J'),'F',Set.of( 'C'),'G',Set.of( 'D','H','J'),'H',Set.of( 'E','F'),'I',Set.of( 'J'),'J',Set.of( 'A','I')));
            assertEquals("cesta/Test_cesta('B','D')", true, g.cesta('B','D'));
        }
        {//g={A=[J], B=[], C=[A, B, F, J], D=[E, F], E=[B, G], F=[I], G=[B, H], H=[B, E, I], I=[D, E, F], J=[D, H]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'J'),'B',Set.of(),'C',Set.of( 'A','B','F','J'),'D',Set.of( 'E','F'),'E',Set.of( 'B','G'),'F',Set.of( 'I'),'G',Set.of( 'B','H'),'H',Set.of( 'B','E','I'),'I',Set.of( 'D','E','F'),'J',Set.of( 'D','H')));
            assertEquals("cesta/Test_cesta('C','B')", true, g.cesta('C','B'));
        }
        {//g={A=[], B=[G, I], C=[B, D, I], D=[A, G], E=[], F=[A, E, G], G=[D, H, J], H=[A, B, G, I], I=[], J=[A, H]}
            var g = new Graph<Character>(Map.of( 'A',Set.of(),'B',Set.of( 'G','I'),'C',Set.of( 'B','D','I'),'D',Set.of( 'A','G'),'E',Set.of(),'F',Set.of( 'A','E','G'),'G',Set.of( 'D','H','J'),'H',Set.of( 'A','B','G','I'),'I',Set.of(),'J',Set.of( 'A','H')));
            assertEquals("cesta/Test_cesta('C','A')", true, g.cesta('C','A'));
            assertEquals("cesta/Test_cesta('C','E')", false, g.cesta('C','E'));
            assertEquals("cesta/Test_cesta('C','I')", true, g.cesta('C','I'));
            assertEquals("cesta/Test_cesta('F','A')", true, g.cesta('F','A'));
            assertEquals("cesta/Test_cesta('F','E')", true, g.cesta('F','E'));
            assertEquals("cesta/Test_cesta('F','I')", true, g.cesta('F','I'));
        }
        {//g={A=[], B=[A, D, E, F], C=[J], D=[E, F], E=[I], F=[], G=[], H=[], I=[C], J=[C, G]}
            var g = new Graph<Character>(Map.of( 'A',Set.of(),'B',Set.of( 'A','D','E','F'),'C',Set.of( 'J'),'D',Set.of( 'E','F'),'E',Set.of( 'I'),'F',Set.of(),'G',Set.of(),'H',Set.of(),'I',Set.of( 'C'),'J',Set.of( 'C','G')));
            assertEquals("cesta/Test_cesta('B','A')", true, g.cesta('B','A'));
            assertEquals("cesta/Test_cesta('B','F')", true, g.cesta('B','F'));
            assertEquals("cesta/Test_cesta('B','G')", true, g.cesta('B','G'));
            assertEquals("cesta/Test_cesta('B','H')", false, g.cesta('B','H'));
            assertEquals("cesta/Test_cesta('H','A')", false, g.cesta('H','A'));
            assertEquals("cesta/Test_cesta('H','F')", false, g.cesta('H','F'));
            assertEquals("cesta/Test_cesta('H','G')", false, g.cesta('H','G'));
            assertEquals("cesta/Test_cesta('H','H')", true, g.cesta('H','H'));
        }
        {//g={A=[], B=[A, C, F], C=[B, I], D=[E, H], E=[], F=[E, I], G=[B, I], H=[F], I=[F], J=[C, G, H]}
            var g = new Graph<Character>(Map.of( 'A',Set.of(),'B',Set.of( 'A','C','F'),'C',Set.of( 'B','I'),'D',Set.of( 'E','H'),'E',Set.of(),'F',Set.of( 'E','I'),'G',Set.of( 'B','I'),'H',Set.of( 'F'),'I',Set.of( 'F'),'J',Set.of( 'C','G','H')));
            assertEquals("cesta/Test_cesta('D','A')", false, g.cesta('D','A'));
            assertEquals("cesta/Test_cesta('D','E')", true, g.cesta('D','E'));
            assertEquals("cesta/Test_cesta('J','A')", true, g.cesta('J','A'));
            assertEquals("cesta/Test_cesta('J','E')", true, g.cesta('J','E'));
        }
        {//g={A=[F], B=[E, G, I, J], C=[E, J], D=[A, I], E=[B, C, D, J], F=[B, C], G=[], H=[E, F], I=[F, H], J=[]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'F'),'B',Set.of( 'E','G','I','J'),'C',Set.of( 'E','J'),'D',Set.of( 'A','I'),'E',Set.of( 'B','C','D','J'),'F',Set.of( 'B','C'),'G',Set.of(),'H',Set.of( 'E','F'),'I',Set.of( 'F','H'),'J',Set.of()));
        }
        {//g={A=[E], B=[A, I], C=[D, F, H, I], D=[A, G, H], E=[A, G], F=[D, E, H], G=[F, I], H=[], I=[F], J=[I]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'E'),'B',Set.of( 'A','I'),'C',Set.of( 'D','F','H','I'),'D',Set.of( 'A','G','H'),'E',Set.of( 'A','G'),'F',Set.of( 'D','E','H'),'G',Set.of( 'F','I'),'H',Set.of(),'I',Set.of( 'F'),'J',Set.of( 'I')));
            assertEquals("cesta/Test_cesta('B','H')", true, g.cesta('B','H'));
            assertEquals("cesta/Test_cesta('C','H')", true, g.cesta('C','H'));
            assertEquals("cesta/Test_cesta('J','H')", true, g.cesta('J','H'));
        }
        {//g={A=[], B=[G], C=[D], D=[C], E=[D], F=[], G=[J], H=[G, I], I=[A, B], J=[B, C, E]}
            var g = new Graph<Character>(Map.of( 'A',Set.of(),'B',Set.of( 'G'),'C',Set.of( 'D'),'D',Set.of( 'C'),'E',Set.of( 'D'),'F',Set.of(),'G',Set.of( 'J'),'H',Set.of( 'G','I'),'I',Set.of( 'A','B'),'J',Set.of( 'B','C','E')));
            assertEquals("cesta/Test_cesta('F','A')", false, g.cesta('F','A'));
            assertEquals("cesta/Test_cesta('F','F')", true, g.cesta('F','F'));
            assertEquals("cesta/Test_cesta('H','A')", true, g.cesta('H','A'));
            assertEquals("cesta/Test_cesta('H','F')", false, g.cesta('H','F'));
        }
        {//g={A=[B, F, H], B=[C], C=[E, F, G], D=[A, B, E, H], E=[D, G], F=[B, C, I], G=[], H=[C, G], I=[], J=[B, C, G]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'B','F','H'),'B',Set.of( 'C'),'C',Set.of( 'E','F','G'),'D',Set.of( 'A','B','E','H'),'E',Set.of( 'D','G'),'F',Set.of( 'B','C','I'),'G',Set.of(),'H',Set.of( 'C','G'),'I',Set.of(),'J',Set.of( 'B','C','G')));
            assertEquals("cesta/Test_cesta('J','G')", true, g.cesta('J','G'));
            assertEquals("cesta/Test_cesta('J','I')", true, g.cesta('J','I'));
        }
        {//g={A=[J], B=[F], C=[], D=[J], E=[], F=[], G=[H], H=[B, C, E], I=[], J=[A, B]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'J'),'B',Set.of( 'F'),'C',Set.of(),'D',Set.of( 'J'),'E',Set.of(),'F',Set.of(),'G',Set.of( 'H'),'H',Set.of( 'B','C','E'),'I',Set.of(),'J',Set.of( 'A','B')));
            assertEquals("cesta/Test_cesta('D','C')", false, g.cesta('D','C'));
            assertEquals("cesta/Test_cesta('D','E')", false, g.cesta('D','E'));
            assertEquals("cesta/Test_cesta('D','F')", true, g.cesta('D','F'));
            assertEquals("cesta/Test_cesta('D','I')", false, g.cesta('D','I'));
            assertEquals("cesta/Test_cesta('G','C')", true, g.cesta('G','C'));
            assertEquals("cesta/Test_cesta('G','E')", true, g.cesta('G','E'));
            assertEquals("cesta/Test_cesta('G','F')", true, g.cesta('G','F'));
            assertEquals("cesta/Test_cesta('G','I')", false, g.cesta('G','I'));
            assertEquals("cesta/Test_cesta('I','C')", false, g.cesta('I','C'));
            assertEquals("cesta/Test_cesta('I','E')", false, g.cesta('I','E'));
            assertEquals("cesta/Test_cesta('I','F')", false, g.cesta('I','F'));
            assertEquals("cesta/Test_cesta('I','I')", true, g.cesta('I','I'));
        }
        {//g={A=[D, E, G], B=[E, J], C=[D], D=[F, H], E=[B, F, H], F=[A, B, E, J], G=[], H=[A, C, E], I=[B, D, G], J=[E, F]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'D','E','G'),'B',Set.of( 'E','J'),'C',Set.of( 'D'),'D',Set.of( 'F','H'),'E',Set.of( 'B','F','H'),'F',Set.of( 'A','B','E','J'),'G',Set.of(),'H',Set.of( 'A','C','E'),'I',Set.of( 'B','D','G'),'J',Set.of( 'E','F')));
            assertEquals("cesta/Test_cesta('I','G')", true, g.cesta('I','G'));
        }
        {//g={A=[], B=[A, C, F], C=[F, I], D=[H, J], E=[A, I], F=[A, B, D], G=[A, J], H=[C, E], I=[], J=[A, C, G, H]}
            var g = new Graph<Character>(Map.of( 'A',Set.of(),'B',Set.of( 'A','C','F'),'C',Set.of( 'F','I'),'D',Set.of( 'H','J'),'E',Set.of( 'A','I'),'F',Set.of( 'A','B','D'),'G',Set.of( 'A','J'),'H',Set.of( 'C','E'),'I',Set.of(),'J',Set.of( 'A','C','G','H')));
        }
        {//g={A=[B], B=[H], C=[E, J], D=[], E=[G, H, I], F=[B, J], G=[B, H], H=[J], I=[A, B, H], J=[E]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'B'),'B',Set.of( 'H'),'C',Set.of( 'E','J'),'D',Set.of(),'E',Set.of( 'G','H','I'),'F',Set.of( 'B','J'),'G',Set.of( 'B','H'),'H',Set.of( 'J'),'I',Set.of( 'A','B','H'),'J',Set.of( 'E')));
            assertEquals("cesta/Test_cesta('C','D')", false, g.cesta('C','D'));
            assertEquals("cesta/Test_cesta('D','D')", true, g.cesta('D','D'));
            assertEquals("cesta/Test_cesta('F','D')", false, g.cesta('F','D'));
        }
        {//g={A=[], B=[D], C=[], D=[E, I], E=[D, I], F=[D, H, J], G=[A, B, F, H], H=[E, G], I=[], J=[A, B, F]}
            var g = new Graph<Character>(Map.of( 'A',Set.of(),'B',Set.of( 'D'),'C',Set.of(),'D',Set.of( 'E','I'),'E',Set.of( 'D','I'),'F',Set.of( 'D','H','J'),'G',Set.of( 'A','B','F','H'),'H',Set.of( 'E','G'),'I',Set.of(),'J',Set.of( 'A','B','F')));
            assertEquals("cesta/Test_cesta('C','A')", false, g.cesta('C','A'));
            assertEquals("cesta/Test_cesta('C','C')", true, g.cesta('C','C'));
            assertEquals("cesta/Test_cesta('C','I')", false, g.cesta('C','I'));
        }
        {//g={A=[H], B=[F, H, I], C=[D, F, G], D=[C, G, J], E=[A, J], F=[A, B], G=[], H=[B, F], I=[A, B, H], J=[D, H]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'H'),'B',Set.of( 'F','H','I'),'C',Set.of( 'D','F','G'),'D',Set.of( 'C','G','J'),'E',Set.of( 'A','J'),'F',Set.of( 'A','B'),'G',Set.of(),'H',Set.of( 'B','F'),'I',Set.of( 'A','B','H'),'J',Set.of( 'D','H')));
            assertEquals("cesta/Test_cesta('E','G')", true, g.cesta('E','G'));
        }
        {//g={A=[], B=[], C=[E], D=[A, J], E=[A, D, F], F=[H], G=[F, J], H=[], I=[], J=[]}
            var g = new Graph<Character>(Map.of( 'A',Set.of(),'B',Set.of(),'C',Set.of( 'E'),'D',Set.of( 'A','J'),'E',Set.of( 'A','D','F'),'F',Set.of( 'H'),'G',Set.of( 'F','J'),'H',Set.of(),'I',Set.of(),'J',Set.of()));
            assertEquals("cesta/Test_cesta('B','A')", false, g.cesta('B','A'));
            assertEquals("cesta/Test_cesta('B','B')", true, g.cesta('B','B'));
            assertEquals("cesta/Test_cesta('B','H')", false, g.cesta('B','H'));
            assertEquals("cesta/Test_cesta('B','I')", false, g.cesta('B','I'));
            assertEquals("cesta/Test_cesta('B','J')", false, g.cesta('B','J'));
            assertEquals("cesta/Test_cesta('C','A')", true, g.cesta('C','A'));
            assertEquals("cesta/Test_cesta('C','B')", false, g.cesta('C','B'));
            assertEquals("cesta/Test_cesta('C','H')", true, g.cesta('C','H'));
            assertEquals("cesta/Test_cesta('C','I')", false, g.cesta('C','I'));
            assertEquals("cesta/Test_cesta('C','J')", true, g.cesta('C','J'));
            assertEquals("cesta/Test_cesta('G','A')", false, g.cesta('G','A'));
            assertEquals("cesta/Test_cesta('G','B')", false, g.cesta('G','B'));
            assertEquals("cesta/Test_cesta('G','H')", true, g.cesta('G','H'));
            assertEquals("cesta/Test_cesta('G','I')", false, g.cesta('G','I'));
            assertEquals("cesta/Test_cesta('G','J')", true, g.cesta('G','J'));
            assertEquals("cesta/Test_cesta('I','A')", false, g.cesta('I','A'));
            assertEquals("cesta/Test_cesta('I','B')", false, g.cesta('I','B'));
            assertEquals("cesta/Test_cesta('I','H')", false, g.cesta('I','H'));
            assertEquals("cesta/Test_cesta('I','I')", true, g.cesta('I','I'));
            assertEquals("cesta/Test_cesta('I','J')", false, g.cesta('I','J'));
        }
        {//g={A=[], B=[H, I], C=[A, D, F], D=[J], E=[F], F=[A, G, J], G=[C, D, E], H=[], I=[A, H], J=[E]}
            var g = new Graph<Character>(Map.of( 'A',Set.of(),'B',Set.of( 'H','I'),'C',Set.of( 'A','D','F'),'D',Set.of( 'J'),'E',Set.of( 'F'),'F',Set.of( 'A','G','J'),'G',Set.of( 'C','D','E'),'H',Set.of(),'I',Set.of( 'A','H'),'J',Set.of( 'E')));
            assertEquals("cesta/Test_cesta('B','A')", true, g.cesta('B','A'));
            assertEquals("cesta/Test_cesta('B','H')", true, g.cesta('B','H'));
        }
        {//g={A=[B, I], B=[I, J], C=[B, I], D=[B, F, H, I], E=[B, D, H, I], F=[], G=[], H=[E, I], I=[E], J=[G]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'B','I'),'B',Set.of( 'I','J'),'C',Set.of( 'B','I'),'D',Set.of( 'B','F','H','I'),'E',Set.of( 'B','D','H','I'),'F',Set.of(),'G',Set.of(),'H',Set.of( 'E','I'),'I',Set.of( 'E'),'J',Set.of( 'G')));
            assertEquals("cesta/Test_cesta('A','F')", true, g.cesta('A','F'));
            assertEquals("cesta/Test_cesta('A','G')", true, g.cesta('A','G'));
            assertEquals("cesta/Test_cesta('C','F')", true, g.cesta('C','F'));
            assertEquals("cesta/Test_cesta('C','G')", true, g.cesta('C','G'));
        }
        {//g={A=[], B=[D, E], C=[F], D=[F], E=[F, G, I, J], F=[C, J], G=[A, F, H], H=[J], I=[D, E], J=[C, D]}
            var g = new Graph<Character>(Map.of( 'A',Set.of(),'B',Set.of( 'D','E'),'C',Set.of( 'F'),'D',Set.of( 'F'),'E',Set.of( 'F','G','I','J'),'F',Set.of( 'C','J'),'G',Set.of( 'A','F','H'),'H',Set.of( 'J'),'I',Set.of( 'D','E'),'J',Set.of( 'C','D')));
            assertEquals("cesta/Test_cesta('B','A')", true, g.cesta('B','A'));
        }
        {//g={A=[E], B=[F, G, I], C=[], D=[A, I], E=[D, F, G], F=[], G=[F], H=[A, C, E], I=[F], J=[B, I]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'E'),'B',Set.of( 'F','G','I'),'C',Set.of(),'D',Set.of( 'A','I'),'E',Set.of( 'D','F','G'),'F',Set.of(),'G',Set.of( 'F'),'H',Set.of( 'A','C','E'),'I',Set.of( 'F'),'J',Set.of( 'B','I')));
            assertEquals("cesta/Test_cesta('H','C')", true, g.cesta('H','C'));
            assertEquals("cesta/Test_cesta('H','F')", true, g.cesta('H','F'));
            assertEquals("cesta/Test_cesta('J','C')", false, g.cesta('J','C'));
            assertEquals("cesta/Test_cesta('J','F')", true, g.cesta('J','F'));
        }
        {//g={A=[D, E, G], B=[A, D, G, H], C=[I], D=[B, G, J], E=[A, B, F], F=[C, G], G=[], H=[], I=[E], J=[C]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'D','E','G'),'B',Set.of( 'A','D','G','H'),'C',Set.of( 'I'),'D',Set.of( 'B','G','J'),'E',Set.of( 'A','B','F'),'F',Set.of( 'C','G'),'G',Set.of(),'H',Set.of(),'I',Set.of( 'E'),'J',Set.of( 'C')));
        }
        {//g={A=[F, I], B=[], C=[D], D=[], E=[F, H], F=[C, G], G=[A, C, H], H=[D], I=[A, G, H, J], J=[]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'F','I'),'B',Set.of(),'C',Set.of( 'D'),'D',Set.of(),'E',Set.of( 'F','H'),'F',Set.of( 'C','G'),'G',Set.of( 'A','C','H'),'H',Set.of( 'D'),'I',Set.of( 'A','G','H','J'),'J',Set.of()));
            assertEquals("cesta/Test_cesta('B','B')", true, g.cesta('B','B'));
            assertEquals("cesta/Test_cesta('B','D')", false, g.cesta('B','D'));
            assertEquals("cesta/Test_cesta('B','J')", false, g.cesta('B','J'));
            assertEquals("cesta/Test_cesta('E','B')", false, g.cesta('E','B'));
            assertEquals("cesta/Test_cesta('E','D')", true, g.cesta('E','D'));
            assertEquals("cesta/Test_cesta('E','J')", true, g.cesta('E','J'));
        }
        {//g={A=[], B=[C, F], C=[G], D=[E, I], E=[B, D, I], F=[A, H, I], G=[A, C, E], H=[B], I=[H, J], J=[B]}
            var g = new Graph<Character>(Map.of( 'A',Set.of(),'B',Set.of( 'C','F'),'C',Set.of( 'G'),'D',Set.of( 'E','I'),'E',Set.of( 'B','D','I'),'F',Set.of( 'A','H','I'),'G',Set.of( 'A','C','E'),'H',Set.of( 'B'),'I',Set.of( 'H','J'),'J',Set.of( 'B')));
        }
        {//g={A=[B, D], B=[], C=[], D=[], E=[D, H, J], F=[D, E], G=[C, H], H=[], I=[F], J=[G]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'B','D'),'B',Set.of(),'C',Set.of(),'D',Set.of(),'E',Set.of( 'D','H','J'),'F',Set.of( 'D','E'),'G',Set.of( 'C','H'),'H',Set.of(),'I',Set.of( 'F'),'J',Set.of( 'G')));
            assertEquals("cesta/Test_cesta('A','B')", true, g.cesta('A','B'));
            assertEquals("cesta/Test_cesta('A','C')", false, g.cesta('A','C'));
            assertEquals("cesta/Test_cesta('A','D')", true, g.cesta('A','D'));
            assertEquals("cesta/Test_cesta('A','H')", false, g.cesta('A','H'));
            assertEquals("cesta/Test_cesta('I','B')", false, g.cesta('I','B'));
            assertEquals("cesta/Test_cesta('I','C')", true, g.cesta('I','C'));
            assertEquals("cesta/Test_cesta('I','D')", true, g.cesta('I','D'));
            assertEquals("cesta/Test_cesta('I','H')", true, g.cesta('I','H'));
        }
        {//g={A=[G, H], B=[A], C=[G, H, J], D=[C, E], E=[], F=[J], G=[A, C, E, J], H=[E, G], I=[], J=[B, F, I]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'G','H'),'B',Set.of( 'A'),'C',Set.of( 'G','H','J'),'D',Set.of( 'C','E'),'E',Set.of(),'F',Set.of( 'J'),'G',Set.of( 'A','C','E','J'),'H',Set.of( 'E','G'),'I',Set.of(),'J',Set.of( 'B','F','I')));
            assertEquals("cesta/Test_cesta('D','E')", true, g.cesta('D','E'));
            assertEquals("cesta/Test_cesta('D','I')", true, g.cesta('D','I'));
        }
        {//g={A=[D, F], B=[], C=[D, E], D=[], E=[F, I, J], F=[H, J], G=[B], H=[C, D], I=[J], J=[A, B]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'D','F'),'B',Set.of(),'C',Set.of( 'D','E'),'D',Set.of(),'E',Set.of( 'F','I','J'),'F',Set.of( 'H','J'),'G',Set.of( 'B'),'H',Set.of( 'C','D'),'I',Set.of( 'J'),'J',Set.of( 'A','B')));
            assertEquals("cesta/Test_cesta('G','B')", true, g.cesta('G','B'));
            assertEquals("cesta/Test_cesta('G','D')", false, g.cesta('G','D'));
        }
        {//g={A=[], B=[E, H], C=[F, G, J], D=[A], E=[B, C, I, J], F=[], G=[A, F, J], H=[F, G, I], I=[H], J=[G]}
            var g = new Graph<Character>(Map.of( 'A',Set.of(),'B',Set.of( 'E','H'),'C',Set.of( 'F','G','J'),'D',Set.of( 'A'),'E',Set.of( 'B','C','I','J'),'F',Set.of(),'G',Set.of( 'A','F','J'),'H',Set.of( 'F','G','I'),'I',Set.of( 'H'),'J',Set.of( 'G')));
            assertEquals("cesta/Test_cesta('D','A')", true, g.cesta('D','A'));
            assertEquals("cesta/Test_cesta('D','F')", false, g.cesta('D','F'));
        }
        {//g={A=[], B=[F, I, J], C=[D, I], D=[A, I, J], E=[C, F, I, J], F=[], G=[], H=[A, C, G, J], I=[C, D], J=[B, F]}
            var g = new Graph<Character>(Map.of( 'A',Set.of(),'B',Set.of( 'F','I','J'),'C',Set.of( 'D','I'),'D',Set.of( 'A','I','J'),'E',Set.of( 'C','F','I','J'),'F',Set.of(),'G',Set.of(),'H',Set.of( 'A','C','G','J'),'I',Set.of( 'C','D'),'J',Set.of( 'B','F')));
            assertEquals("cesta/Test_cesta('E','A')", true, g.cesta('E','A'));
            assertEquals("cesta/Test_cesta('E','F')", true, g.cesta('E','F'));
            assertEquals("cesta/Test_cesta('E','G')", false, g.cesta('E','G'));
            assertEquals("cesta/Test_cesta('H','A')", true, g.cesta('H','A'));
            assertEquals("cesta/Test_cesta('H','F')", true, g.cesta('H','F'));
            assertEquals("cesta/Test_cesta('H','G')", true, g.cesta('H','G'));
        }
        {//g={A=[B], B=[D, E], C=[], D=[B, E, H, J], E=[D], F=[A, E, H, I], G=[], H=[D, J], I=[C, D, G], J=[F, G, I]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'B'),'B',Set.of( 'D','E'),'C',Set.of(),'D',Set.of( 'B','E','H','J'),'E',Set.of( 'D'),'F',Set.of( 'A','E','H','I'),'G',Set.of(),'H',Set.of( 'D','J'),'I',Set.of( 'C','D','G'),'J',Set.of( 'F','G','I')));
        }
        {//g={A=[B, F, H, J], B=[C], C=[E], D=[B, H], E=[F, H, J], F=[B, E, I, J], G=[A], H=[A, C, G, I], I=[F, H, J], J=[A, C, H]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'B','F','H','J'),'B',Set.of( 'C'),'C',Set.of( 'E'),'D',Set.of( 'B','H'),'E',Set.of( 'F','H','J'),'F',Set.of( 'B','E','I','J'),'G',Set.of( 'A'),'H',Set.of( 'A','C','G','I'),'I',Set.of( 'F','H','J'),'J',Set.of( 'A','C','H')));
        }
        {//g={A=[D, H], B=[A], C=[A], D=[G, I], E=[A, F], F=[B, H, I, J], G=[F], H=[], I=[E], J=[A]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'D','H'),'B',Set.of( 'A'),'C',Set.of( 'A'),'D',Set.of( 'G','I'),'E',Set.of( 'A','F'),'F',Set.of( 'B','H','I','J'),'G',Set.of( 'F'),'H',Set.of(),'I',Set.of( 'E'),'J',Set.of( 'A')));
            assertEquals("cesta/Test_cesta('C','H')", true, g.cesta('C','H'));
        }
        {//g={A=[C], B=[A, C, D, G], C=[], D=[], E=[A], F=[C, J], G=[B], H=[D], I=[H], J=[B, C, E]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'C'),'B',Set.of( 'A','C','D','G'),'C',Set.of(),'D',Set.of(),'E',Set.of( 'A'),'F',Set.of( 'C','J'),'G',Set.of( 'B'),'H',Set.of( 'D'),'I',Set.of( 'H'),'J',Set.of( 'B','C','E')));
            assertEquals("cesta/Test_cesta('F','C')", true, g.cesta('F','C'));
            assertEquals("cesta/Test_cesta('F','D')", true, g.cesta('F','D'));
            assertEquals("cesta/Test_cesta('I','C')", false, g.cesta('I','C'));
            assertEquals("cesta/Test_cesta('I','D')", true, g.cesta('I','D'));
        }
        {//g={A=[B, D, E], B=[D, E, J], C=[G], D=[A, E, F], E=[A], F=[H, I], G=[B, H], H=[J], I=[G], J=[C, F, I]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'B','D','E'),'B',Set.of( 'D','E','J'),'C',Set.of( 'G'),'D',Set.of( 'A','E','F'),'E',Set.of( 'A'),'F',Set.of( 'H','I'),'G',Set.of( 'B','H'),'H',Set.of( 'J'),'I',Set.of( 'G'),'J',Set.of( 'C','F','I')));
        }
        {//g={A=[D, E], B=[G, H], C=[J], D=[I], E=[], F=[E], G=[], H=[A], I=[B, C], J=[C, E, I]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'D','E'),'B',Set.of( 'G','H'),'C',Set.of( 'J'),'D',Set.of( 'I'),'E',Set.of(),'F',Set.of( 'E'),'G',Set.of(),'H',Set.of( 'A'),'I',Set.of( 'B','C'),'J',Set.of( 'C','E','I')));
            assertEquals("cesta/Test_cesta('F','E')", true, g.cesta('F','E'));
            assertEquals("cesta/Test_cesta('F','G')", false, g.cesta('F','G'));
        }
        {//g={A=[], B=[F], C=[A, D], D=[I, J], E=[], F=[], G=[A, E, H, I], H=[B, F, J], I=[G, H], J=[A, F]}
            var g = new Graph<Character>(Map.of( 'A',Set.of(),'B',Set.of( 'F'),'C',Set.of( 'A','D'),'D',Set.of( 'I','J'),'E',Set.of(),'F',Set.of(),'G',Set.of( 'A','E','H','I'),'H',Set.of( 'B','F','J'),'I',Set.of( 'G','H'),'J',Set.of( 'A','F')));
            assertEquals("cesta/Test_cesta('C','A')", true, g.cesta('C','A'));
            assertEquals("cesta/Test_cesta('C','E')", true, g.cesta('C','E'));
            assertEquals("cesta/Test_cesta('C','F')", true, g.cesta('C','F'));
        }
        {//g={A=[B, F], B=[C, G], C=[A], D=[A, G], E=[A, G, I], F=[C, D], G=[A, F, J], H=[], I=[C, E, G], J=[C]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'B','F'),'B',Set.of( 'C','G'),'C',Set.of( 'A'),'D',Set.of( 'A','G'),'E',Set.of( 'A','G','I'),'F',Set.of( 'C','D'),'G',Set.of( 'A','F','J'),'H',Set.of(),'I',Set.of( 'C','E','G'),'J',Set.of( 'C')));
            assertEquals("cesta/Test_cesta('H','H')", true, g.cesta('H','H'));
        }
        {//g={A=[D], B=[E, G, H], C=[A, I], D=[A, C, H], E=[A, C], F=[E], G=[A, D, H], H=[B, E, J], I=[], J=[]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'D'),'B',Set.of( 'E','G','H'),'C',Set.of( 'A','I'),'D',Set.of( 'A','C','H'),'E',Set.of( 'A','C'),'F',Set.of( 'E'),'G',Set.of( 'A','D','H'),'H',Set.of( 'B','E','J'),'I',Set.of(),'J',Set.of()));
            assertEquals("cesta/Test_cesta('F','I')", true, g.cesta('F','I'));
            assertEquals("cesta/Test_cesta('F','J')", true, g.cesta('F','J'));
        }
        {//g={A=[I], B=[D, E, H, J], C=[], D=[], E=[], F=[C, E, J], G=[A, C], H=[A, B, J], I=[C, H, J], J=[E]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'I'),'B',Set.of( 'D','E','H','J'),'C',Set.of(),'D',Set.of(),'E',Set.of(),'F',Set.of( 'C','E','J'),'G',Set.of( 'A','C'),'H',Set.of( 'A','B','J'),'I',Set.of( 'C','H','J'),'J',Set.of( 'E')));
            assertEquals("cesta/Test_cesta('F','C')", true, g.cesta('F','C'));
            assertEquals("cesta/Test_cesta('F','D')", false, g.cesta('F','D'));
            assertEquals("cesta/Test_cesta('F','E')", true, g.cesta('F','E'));
            assertEquals("cesta/Test_cesta('G','C')", true, g.cesta('G','C'));
            assertEquals("cesta/Test_cesta('G','D')", true, g.cesta('G','D'));
            assertEquals("cesta/Test_cesta('G','E')", true, g.cesta('G','E'));
        }
        {//g={A=[B], B=[A, F, I, J], C=[D, E, F], D=[B, F], E=[], F=[C], G=[], H=[A], I=[], J=[B, F]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'B'),'B',Set.of( 'A','F','I','J'),'C',Set.of( 'D','E','F'),'D',Set.of( 'B','F'),'E',Set.of(),'F',Set.of( 'C'),'G',Set.of(),'H',Set.of( 'A'),'I',Set.of(),'J',Set.of( 'B','F')));
            assertEquals("cesta/Test_cesta('G','E')", false, g.cesta('G','E'));
            assertEquals("cesta/Test_cesta('G','G')", true, g.cesta('G','G'));
            assertEquals("cesta/Test_cesta('G','I')", false, g.cesta('G','I'));
            assertEquals("cesta/Test_cesta('H','E')", true, g.cesta('H','E'));
            assertEquals("cesta/Test_cesta('H','G')", false, g.cesta('H','G'));
            assertEquals("cesta/Test_cesta('H','I')", true, g.cesta('H','I'));
        }
        {//g={A=[C, D], B=[C, H], C=[], D=[B, H], E=[A, B, D, G], F=[], G=[J], H=[J], I=[], J=[I]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'C','D'),'B',Set.of( 'C','H'),'C',Set.of(),'D',Set.of( 'B','H'),'E',Set.of( 'A','B','D','G'),'F',Set.of(),'G',Set.of( 'J'),'H',Set.of( 'J'),'I',Set.of(),'J',Set.of( 'I')));
            assertEquals("cesta/Test_cesta('E','C')", true, g.cesta('E','C'));
            assertEquals("cesta/Test_cesta('E','F')", false, g.cesta('E','F'));
            assertEquals("cesta/Test_cesta('E','I')", true, g.cesta('E','I'));
            assertEquals("cesta/Test_cesta('F','C')", false, g.cesta('F','C'));
            assertEquals("cesta/Test_cesta('F','F')", true, g.cesta('F','F'));
            assertEquals("cesta/Test_cesta('F','I')", false, g.cesta('F','I'));
        }
        {//g={A=[], B=[], C=[E, J], D=[], E=[C, I, J], F=[], G=[C, E], H=[], I=[G], J=[D, F, G]}
            var g = new Graph<Character>(Map.of( 'A',Set.of(),'B',Set.of(),'C',Set.of( 'E','J'),'D',Set.of(),'E',Set.of( 'C','I','J'),'F',Set.of(),'G',Set.of( 'C','E'),'H',Set.of(),'I',Set.of( 'G'),'J',Set.of( 'D','F','G')));
            assertEquals("cesta/Test_cesta('A','A')", true, g.cesta('A','A'));
            assertEquals("cesta/Test_cesta('A','B')", false, g.cesta('A','B'));
            assertEquals("cesta/Test_cesta('A','D')", false, g.cesta('A','D'));
            assertEquals("cesta/Test_cesta('A','F')", false, g.cesta('A','F'));
            assertEquals("cesta/Test_cesta('A','H')", false, g.cesta('A','H'));
            assertEquals("cesta/Test_cesta('B','A')", false, g.cesta('B','A'));
            assertEquals("cesta/Test_cesta('B','B')", true, g.cesta('B','B'));
            assertEquals("cesta/Test_cesta('B','D')", false, g.cesta('B','D'));
            assertEquals("cesta/Test_cesta('B','F')", false, g.cesta('B','F'));
            assertEquals("cesta/Test_cesta('B','H')", false, g.cesta('B','H'));
            assertEquals("cesta/Test_cesta('H','A')", false, g.cesta('H','A'));
            assertEquals("cesta/Test_cesta('H','B')", false, g.cesta('H','B'));
            assertEquals("cesta/Test_cesta('H','D')", false, g.cesta('H','D'));
            assertEquals("cesta/Test_cesta('H','F')", false, g.cesta('H','F'));
            assertEquals("cesta/Test_cesta('H','H')", true, g.cesta('H','H'));
        }
        {//g={A=[C, G], B=[A], C=[A, F, J], D=[F, J], E=[C, F, H], F=[B, I, J], G=[C], H=[F], I=[A, C], J=[]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'C','G'),'B',Set.of( 'A'),'C',Set.of( 'A','F','J'),'D',Set.of( 'F','J'),'E',Set.of( 'C','F','H'),'F',Set.of( 'B','I','J'),'G',Set.of( 'C'),'H',Set.of( 'F'),'I',Set.of( 'A','C'),'J',Set.of()));
            assertEquals("cesta/Test_cesta('D','J')", true, g.cesta('D','J'));
            assertEquals("cesta/Test_cesta('E','J')", true, g.cesta('E','J'));
        }
        {//g={A=[G, H], B=[A], C=[B, F], D=[A, E, G], E=[C, G, H], F=[], G=[C, D, F, J], H=[C, G, J], I=[], J=[B]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'G','H'),'B',Set.of( 'A'),'C',Set.of( 'B','F'),'D',Set.of( 'A','E','G'),'E',Set.of( 'C','G','H'),'F',Set.of(),'G',Set.of( 'C','D','F','J'),'H',Set.of( 'C','G','J'),'I',Set.of(),'J',Set.of( 'B')));
            assertEquals("cesta/Test_cesta('I','F')", false, g.cesta('I','F'));
            assertEquals("cesta/Test_cesta('I','I')", true, g.cesta('I','I'));
        }
        {//g={A=[], B=[F], C=[A, F, J], D=[C], E=[A, F, H], F=[A, E, J], G=[E], H=[D, G], I=[C, G, H, J], J=[C, F]}
            var g = new Graph<Character>(Map.of( 'A',Set.of(),'B',Set.of( 'F'),'C',Set.of( 'A','F','J'),'D',Set.of( 'C'),'E',Set.of( 'A','F','H'),'F',Set.of( 'A','E','J'),'G',Set.of( 'E'),'H',Set.of( 'D','G'),'I',Set.of( 'C','G','H','J'),'J',Set.of( 'C','F')));
            assertEquals("cesta/Test_cesta('B','A')", true, g.cesta('B','A'));
            assertEquals("cesta/Test_cesta('I','A')", true, g.cesta('I','A'));
        }
        {//g={A=[F, H], B=[C, F], C=[], D=[B, C, E, G], E=[B], F=[C, D], G=[], H=[I], I=[B, C, D], J=[]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'F','H'),'B',Set.of( 'C','F'),'C',Set.of(),'D',Set.of( 'B','C','E','G'),'E',Set.of( 'B'),'F',Set.of( 'C','D'),'G',Set.of(),'H',Set.of( 'I'),'I',Set.of( 'B','C','D'),'J',Set.of()));
            assertEquals("cesta/Test_cesta('A','C')", true, g.cesta('A','C'));
            assertEquals("cesta/Test_cesta('A','G')", true, g.cesta('A','G'));
            assertEquals("cesta/Test_cesta('A','J')", false, g.cesta('A','J'));
            assertEquals("cesta/Test_cesta('J','C')", false, g.cesta('J','C'));
            assertEquals("cesta/Test_cesta('J','G')", false, g.cesta('J','G'));
            assertEquals("cesta/Test_cesta('J','J')", true, g.cesta('J','J'));
        }
        {//g={A=[C], B=[C, D], C=[G, I], D=[], E=[G], F=[I, J], G=[], H=[A, G], I=[B], J=[]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'C'),'B',Set.of( 'C','D'),'C',Set.of( 'G','I'),'D',Set.of(),'E',Set.of( 'G'),'F',Set.of( 'I','J'),'G',Set.of(),'H',Set.of( 'A','G'),'I',Set.of( 'B'),'J',Set.of()));
            assertEquals("cesta/Test_cesta('E','D')", false, g.cesta('E','D'));
            assertEquals("cesta/Test_cesta('E','G')", true, g.cesta('E','G'));
            assertEquals("cesta/Test_cesta('E','J')", false, g.cesta('E','J'));
            assertEquals("cesta/Test_cesta('F','D')", true, g.cesta('F','D'));
            assertEquals("cesta/Test_cesta('F','G')", true, g.cesta('F','G'));
            assertEquals("cesta/Test_cesta('F','J')", true, g.cesta('F','J'));
            assertEquals("cesta/Test_cesta('H','D')", true, g.cesta('H','D'));
            assertEquals("cesta/Test_cesta('H','G')", true, g.cesta('H','G'));
            assertEquals("cesta/Test_cesta('H','J')", false, g.cesta('H','J'));
        }
        scoring.updateScore("lang:common_list_test_scoring_name",   33.0D);
    }
    @Test
    public void test_cyklicky() {
        {//g={A=[B, D], B=[], C=[], D=[A, E], E=[H, I, J], F=[C, H, J], G=[], H=[C, E], I=[], J=[]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'B','D'),'B',Set.of(),'C',Set.of(),'D',Set.of( 'A','E'),'E',Set.of( 'H','I','J'),'F',Set.of( 'C','H','J'),'G',Set.of(),'H',Set.of( 'C','E'),'I',Set.of(),'J',Set.of()));
            assertEquals("cyklicky/Test_cyklicky", true, g.cyklicky());
        }
        {//g={A=[], B=[D, E, F], C=[H, J], D=[G], E=[F], F=[E, I], G=[B, I, J], H=[B, F], I=[], J=[A, B, E]}
            var g = new Graph<Character>(Map.of( 'A',Set.of(),'B',Set.of( 'D','E','F'),'C',Set.of( 'H','J'),'D',Set.of( 'G'),'E',Set.of( 'F'),'F',Set.of( 'E','I'),'G',Set.of( 'B','I','J'),'H',Set.of( 'B','F'),'I',Set.of(),'J',Set.of( 'A','B','E')));
            assertEquals("cyklicky/Test_cyklicky", true, g.cyklicky());
        }
        {//g={A=[G, I], B=[A, J], C=[A, D], D=[I], E=[C, F, J], F=[D, H, I], G=[A, E, H], H=[C, D], I=[G], J=[F, H]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'G','I'),'B',Set.of( 'A','J'),'C',Set.of( 'A','D'),'D',Set.of( 'I'),'E',Set.of( 'C','F','J'),'F',Set.of( 'D','H','I'),'G',Set.of( 'A','E','H'),'H',Set.of( 'C','D'),'I',Set.of( 'G'),'J',Set.of( 'F','H')));
            assertEquals("cyklicky/Test_cyklicky", true, g.cyklicky());
        }
        {//g={A=[C], B=[F], C=[A], D=[], E=[F, J], F=[C, I], G=[C, D, F, I], H=[C, D], I=[C, D], J=[C, E, G]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'C'),'B',Set.of( 'F'),'C',Set.of( 'A'),'D',Set.of(),'E',Set.of( 'F','J'),'F',Set.of( 'C','I'),'G',Set.of( 'C','D','F','I'),'H',Set.of( 'C','D'),'I',Set.of( 'C','D'),'J',Set.of( 'C','E','G')));
            assertEquals("cyklicky/Test_cyklicky", true, g.cyklicky());
        }
        {//g={A=[B, F], B=[], C=[B, G, I], D=[A, F], E=[A, H], F=[C, D, G], G=[], H=[A, B, J], I=[A, E], J=[C, D, E, I]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'B','F'),'B',Set.of(),'C',Set.of( 'B','G','I'),'D',Set.of( 'A','F'),'E',Set.of( 'A','H'),'F',Set.of( 'C','D','G'),'G',Set.of(),'H',Set.of( 'A','B','J'),'I',Set.of( 'A','E'),'J',Set.of( 'C','D','E','I')));
            assertEquals("cyklicky/Test_cyklicky", true, g.cyklicky());
        }
        {//g={A=[J], B=[A, J], C=[E, F, H, I], D=[B, C, E], E=[B, J], F=[C, D, E], G=[], H=[], I=[A, F, G], J=[]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'J'),'B',Set.of( 'A','J'),'C',Set.of( 'E','F','H','I'),'D',Set.of( 'B','C','E'),'E',Set.of( 'B','J'),'F',Set.of( 'C','D','E'),'G',Set.of(),'H',Set.of(),'I',Set.of( 'A','F','G'),'J',Set.of()));
            assertEquals("cyklicky/Test_cyklicky", true, g.cyklicky());
        }
        {//g={A=[], B=[], C=[G, J], D=[B, G, H, J], E=[B, H, J], F=[B, D, G, I], G=[], H=[B], I=[B, D], J=[B, H, I]}
            var g = new Graph<Character>(Map.of( 'A',Set.of(),'B',Set.of(),'C',Set.of( 'G','J'),'D',Set.of( 'B','G','H','J'),'E',Set.of( 'B','H','J'),'F',Set.of( 'B','D','G','I'),'G',Set.of(),'H',Set.of( 'B'),'I',Set.of( 'B','D'),'J',Set.of( 'B','H','I')));
            assertEquals("cyklicky/Test_cyklicky", true, g.cyklicky());
        }
        {//g={A=[E], B=[E, H], C=[A, E, G, I], D=[A], E=[], F=[H, I], G=[D, F], H=[B], I=[A, F, G, H], J=[I]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'E'),'B',Set.of( 'E','H'),'C',Set.of( 'A','E','G','I'),'D',Set.of( 'A'),'E',Set.of(),'F',Set.of( 'H','I'),'G',Set.of( 'D','F'),'H',Set.of( 'B'),'I',Set.of( 'A','F','G','H'),'J',Set.of( 'I')));
            assertEquals("cyklicky/Test_cyklicky", true, g.cyklicky());
        }
        {//g={A=[B, H], B=[], C=[F, G], D=[A, B, C, I], E=[A, I], F=[C, H, I], G=[E, F, J], H=[], I=[G, H, J], J=[D, G]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'B','H'),'B',Set.of(),'C',Set.of( 'F','G'),'D',Set.of( 'A','B','C','I'),'E',Set.of( 'A','I'),'F',Set.of( 'C','H','I'),'G',Set.of( 'E','F','J'),'H',Set.of(),'I',Set.of( 'G','H','J'),'J',Set.of( 'D','G')));
            assertEquals("cyklicky/Test_cyklicky", true, g.cyklicky());
        }
        {//g={A=[C, D], B=[E, G], C=[A, B, F], D=[B, C, G], E=[F, H], F=[C, G], G=[], H=[C, E], I=[], J=[]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'C','D'),'B',Set.of( 'E','G'),'C',Set.of( 'A','B','F'),'D',Set.of( 'B','C','G'),'E',Set.of( 'F','H'),'F',Set.of( 'C','G'),'G',Set.of(),'H',Set.of( 'C','E'),'I',Set.of(),'J',Set.of()));
            assertEquals("cyklicky/Test_cyklicky", true, g.cyklicky());
        }
        {//g={A=[G], B=[A, G, J], C=[], D=[B, E, I], E=[], F=[], G=[B, I], H=[], I=[D, E, J], J=[B]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'G'),'B',Set.of( 'A','G','J'),'C',Set.of(),'D',Set.of( 'B','E','I'),'E',Set.of(),'F',Set.of(),'G',Set.of( 'B','I'),'H',Set.of(),'I',Set.of( 'D','E','J'),'J',Set.of( 'B')));
            assertEquals("cyklicky/Test_cyklicky", true, g.cyklicky());
        }
        {//g={A=[F], B=[], C=[G], D=[C, F, G, I], E=[A, F, G], F=[], G=[D, E], H=[F], I=[D], J=[F, H, I]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'F'),'B',Set.of(),'C',Set.of( 'G'),'D',Set.of( 'C','F','G','I'),'E',Set.of( 'A','F','G'),'F',Set.of(),'G',Set.of( 'D','E'),'H',Set.of( 'F'),'I',Set.of( 'D'),'J',Set.of( 'F','H','I')));
            assertEquals("cyklicky/Test_cyklicky", true, g.cyklicky());
        }
        {//g={A=[D, F, H, J], B=[A, F, G], C=[H], D=[B, E, I], E=[D, F, I], F=[B], G=[], H=[A, E, F, G], I=[F, G, J], J=[E, G]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'D','F','H','J'),'B',Set.of( 'A','F','G'),'C',Set.of( 'H'),'D',Set.of( 'B','E','I'),'E',Set.of( 'D','F','I'),'F',Set.of( 'B'),'G',Set.of(),'H',Set.of( 'A','E','F','G'),'I',Set.of( 'F','G','J'),'J',Set.of( 'E','G')));
            assertEquals("cyklicky/Test_cyklicky", true, g.cyklicky());
        }
        {//g={A=[B, J], B=[C, F], C=[E, H], D=[], E=[A, F], F=[C, H], G=[], H=[C, D, F], I=[C, F, J], J=[]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'B','J'),'B',Set.of( 'C','F'),'C',Set.of( 'E','H'),'D',Set.of(),'E',Set.of( 'A','F'),'F',Set.of( 'C','H'),'G',Set.of(),'H',Set.of( 'C','D','F'),'I',Set.of( 'C','F','J'),'J',Set.of()));
            assertEquals("cyklicky/Test_cyklicky", true, g.cyklicky());
        }
        {//g={A=[B, E, F, H], B=[E], C=[E, G, I], D=[], E=[A, C, I], F=[B, I], G=[], H=[D], I=[C, D, H], J=[G]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'B','E','F','H'),'B',Set.of( 'E'),'C',Set.of( 'E','G','I'),'D',Set.of(),'E',Set.of( 'A','C','I'),'F',Set.of( 'B','I'),'G',Set.of(),'H',Set.of( 'D'),'I',Set.of( 'C','D','H'),'J',Set.of( 'G')));
            assertEquals("cyklicky/Test_cyklicky", true, g.cyklicky());
        }
        {//g={A=[G, H], B=[A, G, J], C=[B, G, H], D=[B, C, E, J], E=[A, J], F=[A, D, E], G=[A, F, J], H=[F], I=[C, D, G, H], J=[G]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'G','H'),'B',Set.of( 'A','G','J'),'C',Set.of( 'B','G','H'),'D',Set.of( 'B','C','E','J'),'E',Set.of( 'A','J'),'F',Set.of( 'A','D','E'),'G',Set.of( 'A','F','J'),'H',Set.of( 'F'),'I',Set.of( 'C','D','G','H'),'J',Set.of( 'G')));
            assertEquals("cyklicky/Test_cyklicky", true, g.cyklicky());
        }
        {//g={A=[F, G], B=[A, E, G, H], C=[E], D=[C, E, H], E=[], F=[A, C, G], G=[B, D, F], H=[F, G, I], I=[C], J=[G, I]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'F','G'),'B',Set.of( 'A','E','G','H'),'C',Set.of( 'E'),'D',Set.of( 'C','E','H'),'E',Set.of(),'F',Set.of( 'A','C','G'),'G',Set.of( 'B','D','F'),'H',Set.of( 'F','G','I'),'I',Set.of( 'C'),'J',Set.of( 'G','I')));
            assertEquals("cyklicky/Test_cyklicky", true, g.cyklicky());
        }
        {//g={A=[B, G], B=[H, J], C=[A, E, F, I], D=[F, I, J], E=[H, J], F=[B, D, J], G=[A, H], H=[A, B], I=[A], J=[A, B, E]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'B','G'),'B',Set.of( 'H','J'),'C',Set.of( 'A','E','F','I'),'D',Set.of( 'F','I','J'),'E',Set.of( 'H','J'),'F',Set.of( 'B','D','J'),'G',Set.of( 'A','H'),'H',Set.of( 'A','B'),'I',Set.of( 'A'),'J',Set.of( 'A','B','E')));
            assertEquals("cyklicky/Test_cyklicky", true, g.cyklicky());
        }
        {//g={A=[H], B=[F, H, I], C=[B, D], D=[], E=[C, F, H, I], F=[C, H, I], G=[B], H=[B, D], I=[], J=[]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'H'),'B',Set.of( 'F','H','I'),'C',Set.of( 'B','D'),'D',Set.of(),'E',Set.of( 'C','F','H','I'),'F',Set.of( 'C','H','I'),'G',Set.of( 'B'),'H',Set.of( 'B','D'),'I',Set.of(),'J',Set.of()));
            assertEquals("cyklicky/Test_cyklicky", true, g.cyklicky());
        }
        {//g={A=[B, E, I], B=[A, C], C=[A, F, H], D=[A, G], E=[C, D, F], F=[D, J], G=[D, F], H=[G], I=[C, E, F], J=[F]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'B','E','I'),'B',Set.of( 'A','C'),'C',Set.of( 'A','F','H'),'D',Set.of( 'A','G'),'E',Set.of( 'C','D','F'),'F',Set.of( 'D','J'),'G',Set.of( 'D','F'),'H',Set.of( 'G'),'I',Set.of( 'C','E','F'),'J',Set.of( 'F')));
            assertEquals("cyklicky/Test_cyklicky", true, g.cyklicky());
        }
        {//g={A=[B, C], B=[D, H], C=[], D=[B, G], E=[F], F=[D, E], G=[C, D], H=[B], I=[D, F, G, J], J=[B, E, H, I]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'B','C'),'B',Set.of( 'D','H'),'C',Set.of(),'D',Set.of( 'B','G'),'E',Set.of( 'F'),'F',Set.of( 'D','E'),'G',Set.of( 'C','D'),'H',Set.of( 'B'),'I',Set.of( 'D','F','G','J'),'J',Set.of( 'B','E','H','I')));
            assertEquals("cyklicky/Test_cyklicky", true, g.cyklicky());
        }
        {//g={A=[F, G, J], B=[H], C=[D, J], D=[], E=[], F=[D, I, J], G=[E], H=[B, E, F, G], I=[], J=[]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'F','G','J'),'B',Set.of( 'H'),'C',Set.of( 'D','J'),'D',Set.of(),'E',Set.of(),'F',Set.of( 'D','I','J'),'G',Set.of( 'E'),'H',Set.of( 'B','E','F','G'),'I',Set.of(),'J',Set.of()));
            assertEquals("cyklicky/Test_cyklicky", true, g.cyklicky());
        }
        {//g={A=[B], B=[A, H, I], C=[D, H, I], D=[], E=[], F=[J], G=[C], H=[], I=[], J=[E, F]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'B'),'B',Set.of( 'A','H','I'),'C',Set.of( 'D','H','I'),'D',Set.of(),'E',Set.of(),'F',Set.of( 'J'),'G',Set.of( 'C'),'H',Set.of(),'I',Set.of(),'J',Set.of( 'E','F')));
            assertEquals("cyklicky/Test_cyklicky", true, g.cyklicky());
        }
        {//g={A=[G], B=[C, E], C=[], D=[B, C, F], E=[I], F=[B, C, I], G=[D, E], H=[A], I=[E, F], J=[G, H]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'G'),'B',Set.of( 'C','E'),'C',Set.of(),'D',Set.of( 'B','C','F'),'E',Set.of( 'I'),'F',Set.of( 'B','C','I'),'G',Set.of( 'D','E'),'H',Set.of( 'A'),'I',Set.of( 'E','F'),'J',Set.of( 'G','H')));
            assertEquals("cyklicky/Test_cyklicky", true, g.cyklicky());
        }
        {//g={A=[], B=[A, G, H, I], C=[A, E], D=[A, F, H], E=[], F=[A, B, E, H], G=[C, E], H=[B, F, G], I=[G], J=[D, G]}
            var g = new Graph<Character>(Map.of( 'A',Set.of(),'B',Set.of( 'A','G','H','I'),'C',Set.of( 'A','E'),'D',Set.of( 'A','F','H'),'E',Set.of(),'F',Set.of( 'A','B','E','H'),'G',Set.of( 'C','E'),'H',Set.of( 'B','F','G'),'I',Set.of( 'G'),'J',Set.of( 'D','G')));
            assertEquals("cyklicky/Test_cyklicky", true, g.cyklicky());
        }
        {//g={A=[], B=[A, F, I], C=[D, F, I], D=[C], E=[], F=[D], G=[B, I], H=[I], I=[B], J=[]}
            var g = new Graph<Character>(Map.of( 'A',Set.of(),'B',Set.of( 'A','F','I'),'C',Set.of( 'D','F','I'),'D',Set.of( 'C'),'E',Set.of(),'F',Set.of( 'D'),'G',Set.of( 'B','I'),'H',Set.of( 'I'),'I',Set.of( 'B'),'J',Set.of()));
            assertEquals("cyklicky/Test_cyklicky", true, g.cyklicky());
        }
        {//g={A=[F, H, J], B=[], C=[], D=[A, E, J], E=[], F=[], G=[A, C], H=[I], I=[D, J], J=[H]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'F','H','J'),'B',Set.of(),'C',Set.of(),'D',Set.of( 'A','E','J'),'E',Set.of(),'F',Set.of(),'G',Set.of( 'A','C'),'H',Set.of( 'I'),'I',Set.of( 'D','J'),'J',Set.of( 'H')));
            assertEquals("cyklicky/Test_cyklicky", true, g.cyklicky());
        }
        {//g={A=[G], B=[E, I], C=[G], D=[], E=[B, D], F=[], G=[C, D], H=[G], I=[A], J=[]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'G'),'B',Set.of( 'E','I'),'C',Set.of( 'G'),'D',Set.of(),'E',Set.of( 'B','D'),'F',Set.of(),'G',Set.of( 'C','D'),'H',Set.of( 'G'),'I',Set.of( 'A'),'J',Set.of()));
            assertEquals("cyklicky/Test_cyklicky", true, g.cyklicky());
        }
        {//g={A=[], B=[C, D], C=[D, E], D=[H], E=[], F=[D, E], G=[B, C, J], H=[C, F, I], I=[B, G, H], J=[A, B, E]}
            var g = new Graph<Character>(Map.of( 'A',Set.of(),'B',Set.of( 'C','D'),'C',Set.of( 'D','E'),'D',Set.of( 'H'),'E',Set.of(),'F',Set.of( 'D','E'),'G',Set.of( 'B','C','J'),'H',Set.of( 'C','F','I'),'I',Set.of( 'B','G','H'),'J',Set.of( 'A','B','E')));
            assertEquals("cyklicky/Test_cyklicky", true, g.cyklicky());
        }
        {//g={A=[], B=[F], C=[A], D=[C], E=[], F=[], G=[], H=[I, J], I=[E], J=[A, B]}
            var g = new Graph<Character>(Map.of( 'A',Set.of(),'B',Set.of( 'F'),'C',Set.of( 'A'),'D',Set.of( 'C'),'E',Set.of(),'F',Set.of(),'G',Set.of(),'H',Set.of( 'I','J'),'I',Set.of( 'E'),'J',Set.of( 'A','B')));
            assertEquals("cyklicky/Test_cyklicky", false, g.cyklicky());
        }
        {//g={A=[C, G], B=[A, F, J], C=[A, F, H], D=[A, B, J], E=[A, J], F=[D, H, I], G=[], H=[B, G], I=[A, D, G], J=[D, F]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'C','G'),'B',Set.of( 'A','F','J'),'C',Set.of( 'A','F','H'),'D',Set.of( 'A','B','J'),'E',Set.of( 'A','J'),'F',Set.of( 'D','H','I'),'G',Set.of(),'H',Set.of( 'B','G'),'I',Set.of( 'A','D','G'),'J',Set.of( 'D','F')));
            assertEquals("cyklicky/Test_cyklicky", true, g.cyklicky());
        }
        {//g={A=[], B=[D, E], C=[], D=[A, C, E], E=[], F=[A, H], G=[A, E, H, I], H=[B], I=[], J=[I]}
            var g = new Graph<Character>(Map.of( 'A',Set.of(),'B',Set.of( 'D','E'),'C',Set.of(),'D',Set.of( 'A','C','E'),'E',Set.of(),'F',Set.of( 'A','H'),'G',Set.of( 'A','E','H','I'),'H',Set.of( 'B'),'I',Set.of(),'J',Set.of( 'I')));
            assertEquals("cyklicky/Test_cyklicky", false, g.cyklicky());
        }
        {//g={A=[F, I, J], B=[E, F, J], C=[H], D=[F, G, I], E=[], F=[B, D], G=[F, H], H=[], I=[H, J], J=[]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'F','I','J'),'B',Set.of( 'E','F','J'),'C',Set.of( 'H'),'D',Set.of( 'F','G','I'),'E',Set.of(),'F',Set.of( 'B','D'),'G',Set.of( 'F','H'),'H',Set.of(),'I',Set.of( 'H','J'),'J',Set.of()));
            assertEquals("cyklicky/Test_cyklicky", true, g.cyklicky());
        }
        {//g={A=[E], B=[H], C=[F, H], D=[G, H, J], E=[D, F, G, I], F=[H], G=[A, C, D, I], H=[J], I=[B], J=[B, C, D]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'E'),'B',Set.of( 'H'),'C',Set.of( 'F','H'),'D',Set.of( 'G','H','J'),'E',Set.of( 'D','F','G','I'),'F',Set.of( 'H'),'G',Set.of( 'A','C','D','I'),'H',Set.of( 'J'),'I',Set.of( 'B'),'J',Set.of( 'B','C','D')));
            assertEquals("cyklicky/Test_cyklicky", true, g.cyklicky());
        }
        {//g={A=[], B=[I, J], C=[I], D=[F, I], E=[C], F=[A], G=[C, E], H=[I], I=[D, G], J=[D]}
            var g = new Graph<Character>(Map.of( 'A',Set.of(),'B',Set.of( 'I','J'),'C',Set.of( 'I'),'D',Set.of( 'F','I'),'E',Set.of( 'C'),'F',Set.of( 'A'),'G',Set.of( 'C','E'),'H',Set.of( 'I'),'I',Set.of( 'D','G'),'J',Set.of( 'D')));
            assertEquals("cyklicky/Test_cyklicky", true, g.cyklicky());
        }
        {//g={A=[B, F], B=[H, J], C=[H], D=[C, E], E=[I], F=[C, H, J], G=[A, H], H=[A, C, I, J], I=[E, H], J=[]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'B','F'),'B',Set.of( 'H','J'),'C',Set.of( 'H'),'D',Set.of( 'C','E'),'E',Set.of( 'I'),'F',Set.of( 'C','H','J'),'G',Set.of( 'A','H'),'H',Set.of( 'A','C','I','J'),'I',Set.of( 'E','H'),'J',Set.of()));
            assertEquals("cyklicky/Test_cyklicky", true, g.cyklicky());
        }
        {//g={A=[C], B=[H, I], C=[B, G, I], D=[E, F, J], E=[G, H], F=[], G=[C, E, I], H=[B, C, E], I=[J], J=[]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'C'),'B',Set.of( 'H','I'),'C',Set.of( 'B','G','I'),'D',Set.of( 'E','F','J'),'E',Set.of( 'G','H'),'F',Set.of(),'G',Set.of( 'C','E','I'),'H',Set.of( 'B','C','E'),'I',Set.of( 'J'),'J',Set.of()));
            assertEquals("cyklicky/Test_cyklicky", true, g.cyklicky());
        }
        {//g={A=[B, E], B=[], C=[], D=[J], E=[], F=[G, J], G=[F, H, I], H=[I], I=[C, E, J], J=[F]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'B','E'),'B',Set.of(),'C',Set.of(),'D',Set.of( 'J'),'E',Set.of(),'F',Set.of( 'G','J'),'G',Set.of( 'F','H','I'),'H',Set.of( 'I'),'I',Set.of( 'C','E','J'),'J',Set.of( 'F')));
            assertEquals("cyklicky/Test_cyklicky", true, g.cyklicky());
        }
        {//g={A=[H], B=[C, D], C=[H, J], D=[B, J], E=[D, G, I], F=[E, G], G=[F], H=[], I=[E, H, J], J=[A, I]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'H'),'B',Set.of( 'C','D'),'C',Set.of( 'H','J'),'D',Set.of( 'B','J'),'E',Set.of( 'D','G','I'),'F',Set.of( 'E','G'),'G',Set.of( 'F'),'H',Set.of(),'I',Set.of( 'E','H','J'),'J',Set.of( 'A','I')));
            assertEquals("cyklicky/Test_cyklicky", true, g.cyklicky());
        }
        {//g={A=[], B=[F, G, J], C=[A, I, J], D=[E, I], E=[C, H], F=[I], G=[D, J], H=[E], I=[], J=[B]}
            var g = new Graph<Character>(Map.of( 'A',Set.of(),'B',Set.of( 'F','G','J'),'C',Set.of( 'A','I','J'),'D',Set.of( 'E','I'),'E',Set.of( 'C','H'),'F',Set.of( 'I'),'G',Set.of( 'D','J'),'H',Set.of( 'E'),'I',Set.of(),'J',Set.of( 'B')));
            assertEquals("cyklicky/Test_cyklicky", true, g.cyklicky());
        }
        {//g={A=[], B=[], C=[D, G], D=[H], E=[], F=[E], G=[D], H=[D], I=[D, G], J=[]}
            var g = new Graph<Character>(Map.of( 'A',Set.of(),'B',Set.of(),'C',Set.of( 'D','G'),'D',Set.of( 'H'),'E',Set.of(),'F',Set.of( 'E'),'G',Set.of( 'D'),'H',Set.of( 'D'),'I',Set.of( 'D','G'),'J',Set.of()));
            assertEquals("cyklicky/Test_cyklicky", true, g.cyklicky());
        }
        {//g={A=[H, I], B=[A], C=[G], D=[J], E=[], F=[G, J], G=[H, I], H=[J], I=[D, G], J=[C]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'H','I'),'B',Set.of( 'A'),'C',Set.of( 'G'),'D',Set.of( 'J'),'E',Set.of(),'F',Set.of( 'G','J'),'G',Set.of( 'H','I'),'H',Set.of( 'J'),'I',Set.of( 'D','G'),'J',Set.of( 'C')));
            assertEquals("cyklicky/Test_cyklicky", true, g.cyklicky());
        }
        {//g={A=[F, J], B=[A, H], C=[A, G, I], D=[A, B], E=[A, D], F=[I], G=[F, I], H=[], I=[], J=[]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'F','J'),'B',Set.of( 'A','H'),'C',Set.of( 'A','G','I'),'D',Set.of( 'A','B'),'E',Set.of( 'A','D'),'F',Set.of( 'I'),'G',Set.of( 'F','I'),'H',Set.of(),'I',Set.of(),'J',Set.of()));
            assertEquals("cyklicky/Test_cyklicky", false, g.cyklicky());
        }
        {//g={A=[C, I], B=[G], C=[B, H, I], D=[], E=[F, I], F=[J], G=[J], H=[D, E], I=[C, E], J=[B, E, H]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'C','I'),'B',Set.of( 'G'),'C',Set.of( 'B','H','I'),'D',Set.of(),'E',Set.of( 'F','I'),'F',Set.of( 'J'),'G',Set.of( 'J'),'H',Set.of( 'D','E'),'I',Set.of( 'C','E'),'J',Set.of( 'B','E','H')));
            assertEquals("cyklicky/Test_cyklicky", true, g.cyklicky());
        }
        {//g={A=[B, C, G, I], B=[H, I], C=[E, G, I], D=[B, G, H], E=[A, C, F], F=[D], G=[], H=[D, F], I=[A, F, J], J=[I]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'B','C','G','I'),'B',Set.of( 'H','I'),'C',Set.of( 'E','G','I'),'D',Set.of( 'B','G','H'),'E',Set.of( 'A','C','F'),'F',Set.of( 'D'),'G',Set.of(),'H',Set.of( 'D','F'),'I',Set.of( 'A','F','J'),'J',Set.of( 'I')));
            assertEquals("cyklicky/Test_cyklicky", true, g.cyklicky());
        }
        {//g={A=[C, D, E], B=[D, I], C=[A, D, F], D=[E], E=[D, J], F=[], G=[C, E, F], H=[C, G, J], I=[A, D, F], J=[B, C]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'C','D','E'),'B',Set.of( 'D','I'),'C',Set.of( 'A','D','F'),'D',Set.of( 'E'),'E',Set.of( 'D','J'),'F',Set.of(),'G',Set.of( 'C','E','F'),'H',Set.of( 'C','G','J'),'I',Set.of( 'A','D','F'),'J',Set.of( 'B','C')));
            assertEquals("cyklicky/Test_cyklicky", true, g.cyklicky());
        }
        {//g={A=[B], B=[E, G], C=[], D=[C, J], E=[D, G], F=[C, J], G=[C], H=[], I=[], J=[D, E]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'B'),'B',Set.of( 'E','G'),'C',Set.of(),'D',Set.of( 'C','J'),'E',Set.of( 'D','G'),'F',Set.of( 'C','J'),'G',Set.of( 'C'),'H',Set.of(),'I',Set.of(),'J',Set.of( 'D','E')));
            assertEquals("cyklicky/Test_cyklicky", true, g.cyklicky());
        }
        {//g={A=[], B=[E], C=[], D=[], E=[I], F=[D, H], G=[C, F, H], H=[B], I=[C, D, F, G], J=[C, G, H]}
            var g = new Graph<Character>(Map.of( 'A',Set.of(),'B',Set.of( 'E'),'C',Set.of(),'D',Set.of(),'E',Set.of( 'I'),'F',Set.of( 'D','H'),'G',Set.of( 'C','F','H'),'H',Set.of( 'B'),'I',Set.of( 'C','D','F','G'),'J',Set.of( 'C','G','H')));
            assertEquals("cyklicky/Test_cyklicky", true, g.cyklicky());
        }
        {//g={A=[F, H], B=[A, G], C=[A], D=[], E=[], F=[E], G=[], H=[B, D], I=[E], J=[A]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'F','H'),'B',Set.of( 'A','G'),'C',Set.of( 'A'),'D',Set.of(),'E',Set.of(),'F',Set.of( 'E'),'G',Set.of(),'H',Set.of( 'B','D'),'I',Set.of( 'E'),'J',Set.of( 'A')));
            assertEquals("cyklicky/Test_cyklicky", true, g.cyklicky());
        }
        {//g={A=[E, F, H, J], B=[J], C=[A, F], D=[C, H], E=[A, B, I], F=[D], G=[B], H=[], I=[F, H, J], J=[B, H]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'E','F','H','J'),'B',Set.of( 'J'),'C',Set.of( 'A','F'),'D',Set.of( 'C','H'),'E',Set.of( 'A','B','I'),'F',Set.of( 'D'),'G',Set.of( 'B'),'H',Set.of(),'I',Set.of( 'F','H','J'),'J',Set.of( 'B','H')));
            assertEquals("cyklicky/Test_cyklicky", true, g.cyklicky());
        }
        {//g={A=[D, J], B=[I], C=[E], D=[], E=[A], F=[C, G, H], G=[D, I], H=[C, D], I=[D, G], J=[]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'D','J'),'B',Set.of( 'I'),'C',Set.of( 'E'),'D',Set.of(),'E',Set.of( 'A'),'F',Set.of( 'C','G','H'),'G',Set.of( 'D','I'),'H',Set.of( 'C','D'),'I',Set.of( 'D','G'),'J',Set.of()));
            assertEquals("cyklicky/Test_cyklicky", true, g.cyklicky());
        }
        {//g={A=[], B=[H], C=[G, H, J], D=[H], E=[D], F=[A, G, I], G=[A, J], H=[G], I=[B, G], J=[]}
            var g = new Graph<Character>(Map.of( 'A',Set.of(),'B',Set.of( 'H'),'C',Set.of( 'G','H','J'),'D',Set.of( 'H'),'E',Set.of( 'D'),'F',Set.of( 'A','G','I'),'G',Set.of( 'A','J'),'H',Set.of( 'G'),'I',Set.of( 'B','G'),'J',Set.of()));
            assertEquals("cyklicky/Test_cyklicky", false, g.cyklicky());
        }
        {//g={A=[E, G], B=[], C=[D], D=[I, J], E=[A, C, I, J], F=[D, E, I], G=[], H=[D], I=[A, E], J=[B, C, E, I]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'E','G'),'B',Set.of(),'C',Set.of( 'D'),'D',Set.of( 'I','J'),'E',Set.of( 'A','C','I','J'),'F',Set.of( 'D','E','I'),'G',Set.of(),'H',Set.of( 'D'),'I',Set.of( 'A','E'),'J',Set.of( 'B','C','E','I')));
            assertEquals("cyklicky/Test_cyklicky", true, g.cyklicky());
        }
        {//g={A=[], B=[], C=[D], D=[], E=[B, F], F=[G, J], G=[], H=[A], I=[], J=[D]}
            var g = new Graph<Character>(Map.of( 'A',Set.of(),'B',Set.of(),'C',Set.of( 'D'),'D',Set.of(),'E',Set.of( 'B','F'),'F',Set.of( 'G','J'),'G',Set.of(),'H',Set.of( 'A'),'I',Set.of(),'J',Set.of( 'D')));
            assertEquals("cyklicky/Test_cyklicky", false, g.cyklicky());
        }
        {//g={A=[], B=[E, J], C=[F], D=[B, I], E=[], F=[H, I, J], G=[E], H=[C, J], I=[], J=[C, D]}
            var g = new Graph<Character>(Map.of( 'A',Set.of(),'B',Set.of( 'E','J'),'C',Set.of( 'F'),'D',Set.of( 'B','I'),'E',Set.of(),'F',Set.of( 'H','I','J'),'G',Set.of( 'E'),'H',Set.of( 'C','J'),'I',Set.of(),'J',Set.of( 'C','D')));
            assertEquals("cyklicky/Test_cyklicky", true, g.cyklicky());
        }
        {//g={A=[C], B=[E, G], C=[B, D, F, I], D=[A, H, J], E=[A, F, J], F=[D, G], G=[], H=[D, E, G], I=[D, H], J=[B, H]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'C'),'B',Set.of( 'E','G'),'C',Set.of( 'B','D','F','I'),'D',Set.of( 'A','H','J'),'E',Set.of( 'A','F','J'),'F',Set.of( 'D','G'),'G',Set.of(),'H',Set.of( 'D','E','G'),'I',Set.of( 'D','H'),'J',Set.of( 'B','H')));
            assertEquals("cyklicky/Test_cyklicky", true, g.cyklicky());
        }
        {//g={A=[F], B=[A, D], C=[A], D=[B, C, I], E=[F, I], F=[C, H], G=[D, E, F, I], H=[F, J], I=[D], J=[D]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'F'),'B',Set.of( 'A','D'),'C',Set.of( 'A'),'D',Set.of( 'B','C','I'),'E',Set.of( 'F','I'),'F',Set.of( 'C','H'),'G',Set.of( 'D','E','F','I'),'H',Set.of( 'F','J'),'I',Set.of( 'D'),'J',Set.of( 'D')));
            assertEquals("cyklicky/Test_cyklicky", true, g.cyklicky());
        }
        {//g={A=[B, C, I], B=[], C=[A, I], D=[A, F], E=[], F=[G], G=[C, I], H=[], I=[A, C], J=[]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'B','C','I'),'B',Set.of(),'C',Set.of( 'A','I'),'D',Set.of( 'A','F'),'E',Set.of(),'F',Set.of( 'G'),'G',Set.of( 'C','I'),'H',Set.of(),'I',Set.of( 'A','C'),'J',Set.of()));
            assertEquals("cyklicky/Test_cyklicky", true, g.cyklicky());
        }
        {//g={A=[], B=[], C=[A, F, G], D=[C, G], E=[G], F=[B, E, H, J], G=[], H=[C, D], I=[A], J=[I]}
            var g = new Graph<Character>(Map.of( 'A',Set.of(),'B',Set.of(),'C',Set.of( 'A','F','G'),'D',Set.of( 'C','G'),'E',Set.of( 'G'),'F',Set.of( 'B','E','H','J'),'G',Set.of(),'H',Set.of( 'C','D'),'I',Set.of( 'A'),'J',Set.of( 'I')));
            assertEquals("cyklicky/Test_cyklicky", true, g.cyklicky());
        }
        {//g={A=[], B=[F, J], C=[], D=[B, H], E=[A, C, I], F=[I, J], G=[E, F, I], H=[D, E], I=[H], J=[D]}
            var g = new Graph<Character>(Map.of( 'A',Set.of(),'B',Set.of( 'F','J'),'C',Set.of(),'D',Set.of( 'B','H'),'E',Set.of( 'A','C','I'),'F',Set.of( 'I','J'),'G',Set.of( 'E','F','I'),'H',Set.of( 'D','E'),'I',Set.of( 'H'),'J',Set.of( 'D')));
            assertEquals("cyklicky/Test_cyklicky", true, g.cyklicky());
        }
        {//g={A=[J], B=[], C=[A, E, F], D=[A, B, C, G], E=[J], F=[C, D], G=[A, H, I], H=[A, G], I=[H], J=[]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'J'),'B',Set.of(),'C',Set.of( 'A','E','F'),'D',Set.of( 'A','B','C','G'),'E',Set.of( 'J'),'F',Set.of( 'C','D'),'G',Set.of( 'A','H','I'),'H',Set.of( 'A','G'),'I',Set.of( 'H'),'J',Set.of()));
            assertEquals("cyklicky/Test_cyklicky", true, g.cyklicky());
        }
        {//g={A=[I], B=[], C=[B, G], D=[], E=[H, J], F=[], G=[A], H=[B, C, F, I], I=[A], J=[]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'I'),'B',Set.of(),'C',Set.of( 'B','G'),'D',Set.of(),'E',Set.of( 'H','J'),'F',Set.of(),'G',Set.of( 'A'),'H',Set.of( 'B','C','F','I'),'I',Set.of( 'A'),'J',Set.of()));
            assertEquals("cyklicky/Test_cyklicky", true, g.cyklicky());
        }
        {//g={A=[B, F, G, H], B=[F], C=[D], D=[E, G, I], E=[H], F=[], G=[F], H=[E, G, J], I=[B, E, J], J=[]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'B','F','G','H'),'B',Set.of( 'F'),'C',Set.of( 'D'),'D',Set.of( 'E','G','I'),'E',Set.of( 'H'),'F',Set.of(),'G',Set.of( 'F'),'H',Set.of( 'E','G','J'),'I',Set.of( 'B','E','J'),'J',Set.of()));
            assertEquals("cyklicky/Test_cyklicky", true, g.cyklicky());
        }
        {//g={A=[D], B=[J], C=[A, E, F, J], D=[A, I, J], E=[D], F=[D, E, I], G=[D, E, F, I], H=[C, D, J], I=[D, J], J=[E, F, I]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'D'),'B',Set.of( 'J'),'C',Set.of( 'A','E','F','J'),'D',Set.of( 'A','I','J'),'E',Set.of( 'D'),'F',Set.of( 'D','E','I'),'G',Set.of( 'D','E','F','I'),'H',Set.of( 'C','D','J'),'I',Set.of( 'D','J'),'J',Set.of( 'E','F','I')));
            assertEquals("cyklicky/Test_cyklicky", true, g.cyklicky());
        }
        {//g={A=[I], B=[], C=[J], D=[], E=[B], F=[C, E, H], G=[], H=[], I=[C, G, H], J=[A, G]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'I'),'B',Set.of(),'C',Set.of( 'J'),'D',Set.of(),'E',Set.of( 'B'),'F',Set.of( 'C','E','H'),'G',Set.of(),'H',Set.of(),'I',Set.of( 'C','G','H'),'J',Set.of( 'A','G')));
            assertEquals("cyklicky/Test_cyklicky", true, g.cyklicky());
        }
        {//g={A=[], B=[C, F], C=[H], D=[], E=[B, F, H], F=[H], G=[I], H=[A, C, G], I=[B, D, H], J=[D, F, G]}
            var g = new Graph<Character>(Map.of( 'A',Set.of(),'B',Set.of( 'C','F'),'C',Set.of( 'H'),'D',Set.of(),'E',Set.of( 'B','F','H'),'F',Set.of( 'H'),'G',Set.of( 'I'),'H',Set.of( 'A','C','G'),'I',Set.of( 'B','D','H'),'J',Set.of( 'D','F','G')));
            assertEquals("cyklicky/Test_cyklicky", true, g.cyklicky());
        }
        {//g={A=[D, E, H, I], B=[], C=[F, G], D=[A, F], E=[B, F, H, J], F=[B, H, J], G=[A, I, J], H=[], I=[D, F], J=[F]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'D','E','H','I'),'B',Set.of(),'C',Set.of( 'F','G'),'D',Set.of( 'A','F'),'E',Set.of( 'B','F','H','J'),'F',Set.of( 'B','H','J'),'G',Set.of( 'A','I','J'),'H',Set.of(),'I',Set.of( 'D','F'),'J',Set.of( 'F')));
            assertEquals("cyklicky/Test_cyklicky", true, g.cyklicky());
        }
        {//g={A=[C, D, G, I], B=[], C=[E, H, I, J], D=[B, C, G], E=[D, G, J], F=[B, C, J], G=[C, E, J], H=[A, C], I=[], J=[D]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'C','D','G','I'),'B',Set.of(),'C',Set.of( 'E','H','I','J'),'D',Set.of( 'B','C','G'),'E',Set.of( 'D','G','J'),'F',Set.of( 'B','C','J'),'G',Set.of( 'C','E','J'),'H',Set.of( 'A','C'),'I',Set.of(),'J',Set.of( 'D')));
            assertEquals("cyklicky/Test_cyklicky", true, g.cyklicky());
        }
        {//g={A=[C, G, I], B=[E, G], C=[H, I, J], D=[A, E], E=[D, H], F=[B, J], G=[C, I], H=[A, C], I=[], J=[E, H, I]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'C','G','I'),'B',Set.of( 'E','G'),'C',Set.of( 'H','I','J'),'D',Set.of( 'A','E'),'E',Set.of( 'D','H'),'F',Set.of( 'B','J'),'G',Set.of( 'C','I'),'H',Set.of( 'A','C'),'I',Set.of(),'J',Set.of( 'E','H','I')));
            assertEquals("cyklicky/Test_cyklicky", true, g.cyklicky());
        }
        {//g={A=[], B=[E, G], C=[D, H, J], D=[F, H], E=[F, J], F=[A, B, C], G=[], H=[C, F, G], I=[A, E, G, J], J=[]}
            var g = new Graph<Character>(Map.of( 'A',Set.of(),'B',Set.of( 'E','G'),'C',Set.of( 'D','H','J'),'D',Set.of( 'F','H'),'E',Set.of( 'F','J'),'F',Set.of( 'A','B','C'),'G',Set.of(),'H',Set.of( 'C','F','G'),'I',Set.of( 'A','E','G','J'),'J',Set.of()));
            assertEquals("cyklicky/Test_cyklicky", true, g.cyklicky());
        }
        {//g={A=[], B=[A, E, F, G], C=[G, H, I], D=[], E=[], F=[A, J], G=[B, D, E], H=[D], I=[A, G], J=[C, D, E]}
            var g = new Graph<Character>(Map.of( 'A',Set.of(),'B',Set.of( 'A','E','F','G'),'C',Set.of( 'G','H','I'),'D',Set.of(),'E',Set.of(),'F',Set.of( 'A','J'),'G',Set.of( 'B','D','E'),'H',Set.of( 'D'),'I',Set.of( 'A','G'),'J',Set.of( 'C','D','E')));
            assertEquals("cyklicky/Test_cyklicky", true, g.cyklicky());
        }
        {//g={A=[E, G, I], B=[], C=[B, G, I, J], D=[B, E], E=[D], F=[], G=[C, J], H=[A], I=[], J=[B, E]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'E','G','I'),'B',Set.of(),'C',Set.of( 'B','G','I','J'),'D',Set.of( 'B','E'),'E',Set.of( 'D'),'F',Set.of(),'G',Set.of( 'C','J'),'H',Set.of( 'A'),'I',Set.of(),'J',Set.of( 'B','E')));
            assertEquals("cyklicky/Test_cyklicky", true, g.cyklicky());
        }
        {//g={A=[I], B=[H, I, J], C=[D, J], D=[G, I, J], E=[F, G], F=[], G=[C, H], H=[A, J], I=[], J=[A, D, I]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'I'),'B',Set.of( 'H','I','J'),'C',Set.of( 'D','J'),'D',Set.of( 'G','I','J'),'E',Set.of( 'F','G'),'F',Set.of(),'G',Set.of( 'C','H'),'H',Set.of( 'A','J'),'I',Set.of(),'J',Set.of( 'A','D','I')));
            assertEquals("cyklicky/Test_cyklicky", true, g.cyklicky());
        }
        {//g={A=[], B=[F, G], C=[B, E, G], D=[A], E=[B, F], F=[E, J], G=[A, J], H=[B, I], I=[C, E], J=[A, E, H]}
            var g = new Graph<Character>(Map.of( 'A',Set.of(),'B',Set.of( 'F','G'),'C',Set.of( 'B','E','G'),'D',Set.of( 'A'),'E',Set.of( 'B','F'),'F',Set.of( 'E','J'),'G',Set.of( 'A','J'),'H',Set.of( 'B','I'),'I',Set.of( 'C','E'),'J',Set.of( 'A','E','H')));
            assertEquals("cyklicky/Test_cyklicky", true, g.cyklicky());
        }
        {//g={A=[C, D], B=[C], C=[B, E, H], D=[], E=[C, F, H], F=[C, D, E], G=[A, C], H=[], I=[A, D, J], J=[A, E]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'C','D'),'B',Set.of( 'C'),'C',Set.of( 'B','E','H'),'D',Set.of(),'E',Set.of( 'C','F','H'),'F',Set.of( 'C','D','E'),'G',Set.of( 'A','C'),'H',Set.of(),'I',Set.of( 'A','D','J'),'J',Set.of( 'A','E')));
            assertEquals("cyklicky/Test_cyklicky", true, g.cyklicky());
        }
        {//g={A=[B, E, I], B=[C, I], C=[A, E], D=[], E=[], F=[A, C, I], G=[A, D, F, I], H=[B], I=[D], J=[B, C, G]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'B','E','I'),'B',Set.of( 'C','I'),'C',Set.of( 'A','E'),'D',Set.of(),'E',Set.of(),'F',Set.of( 'A','C','I'),'G',Set.of( 'A','D','F','I'),'H',Set.of( 'B'),'I',Set.of( 'D'),'J',Set.of( 'B','C','G')));
            assertEquals("cyklicky/Test_cyklicky", true, g.cyklicky());
        }
        {//g={A=[E], B=[], C=[J], D=[J], E=[], F=[D, H], G=[D], H=[E, F], I=[E], J=[C, F]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'E'),'B',Set.of(),'C',Set.of( 'J'),'D',Set.of( 'J'),'E',Set.of(),'F',Set.of( 'D','H'),'G',Set.of( 'D'),'H',Set.of( 'E','F'),'I',Set.of( 'E'),'J',Set.of( 'C','F')));
            assertEquals("cyklicky/Test_cyklicky", true, g.cyklicky());
        }
        {//g={A=[B], B=[], C=[A, D, F], D=[A, B, C], E=[], F=[E, I], G=[D], H=[A, D, F], I=[A], J=[G, I]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'B'),'B',Set.of(),'C',Set.of( 'A','D','F'),'D',Set.of( 'A','B','C'),'E',Set.of(),'F',Set.of( 'E','I'),'G',Set.of( 'D'),'H',Set.of( 'A','D','F'),'I',Set.of( 'A'),'J',Set.of( 'G','I')));
            assertEquals("cyklicky/Test_cyklicky", true, g.cyklicky());
        }
        {//g={A=[D], B=[], C=[I], D=[A, H], E=[C], F=[A, E], G=[], H=[], I=[A], J=[E]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'D'),'B',Set.of(),'C',Set.of( 'I'),'D',Set.of( 'A','H'),'E',Set.of( 'C'),'F',Set.of( 'A','E'),'G',Set.of(),'H',Set.of(),'I',Set.of( 'A'),'J',Set.of( 'E')));
            assertEquals("cyklicky/Test_cyklicky", true, g.cyklicky());
        }
        {//g={A=[D], B=[], C=[J], D=[A, C, E], E=[C, F], F=[B], G=[E, J], H=[F, J], I=[E, F, H], J=[I]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'D'),'B',Set.of(),'C',Set.of( 'J'),'D',Set.of( 'A','C','E'),'E',Set.of( 'C','F'),'F',Set.of( 'B'),'G',Set.of( 'E','J'),'H',Set.of( 'F','J'),'I',Set.of( 'E','F','H'),'J',Set.of( 'I')));
            assertEquals("cyklicky/Test_cyklicky", true, g.cyklicky());
        }
        {//g={A=[], B=[], C=[G, I], D=[], E=[A, G, H], F=[], G=[], H=[A, I], I=[C, G, J], J=[I]}
            var g = new Graph<Character>(Map.of( 'A',Set.of(),'B',Set.of(),'C',Set.of( 'G','I'),'D',Set.of(),'E',Set.of( 'A','G','H'),'F',Set.of(),'G',Set.of(),'H',Set.of( 'A','I'),'I',Set.of( 'C','G','J'),'J',Set.of( 'I')));
            assertEquals("cyklicky/Test_cyklicky", true, g.cyklicky());
        }
        {//g={A=[B, J], B=[], C=[D], D=[F], E=[], F=[B, E, G, H], G=[], H=[B, J], I=[], J=[]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'B','J'),'B',Set.of(),'C',Set.of( 'D'),'D',Set.of( 'F'),'E',Set.of(),'F',Set.of( 'B','E','G','H'),'G',Set.of(),'H',Set.of( 'B','J'),'I',Set.of(),'J',Set.of()));
            assertEquals("cyklicky/Test_cyklicky", false, g.cyklicky());
        }
        {//g={A=[D, I], B=[I], C=[D, G, I], D=[], E=[A, F], F=[D, G], G=[D, F], H=[E], I=[A], J=[B]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'D','I'),'B',Set.of( 'I'),'C',Set.of( 'D','G','I'),'D',Set.of(),'E',Set.of( 'A','F'),'F',Set.of( 'D','G'),'G',Set.of( 'D','F'),'H',Set.of( 'E'),'I',Set.of( 'A'),'J',Set.of( 'B')));
            assertEquals("cyklicky/Test_cyklicky", true, g.cyklicky());
        }
        {//g={A=[H], B=[F], C=[A, B, H, J], D=[J], E=[C, I], F=[D, E, J], G=[E], H=[F, G], I=[], J=[G]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'H'),'B',Set.of( 'F'),'C',Set.of( 'A','B','H','J'),'D',Set.of( 'J'),'E',Set.of( 'C','I'),'F',Set.of( 'D','E','J'),'G',Set.of( 'E'),'H',Set.of( 'F','G'),'I',Set.of(),'J',Set.of( 'G')));
            assertEquals("cyklicky/Test_cyklicky", true, g.cyklicky());
        }
        {//g={A=[F, H], B=[D, G, H], C=[G], D=[E], E=[F], F=[], G=[D, F, H], H=[], I=[A, E, J], J=[E, F, G]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'F','H'),'B',Set.of( 'D','G','H'),'C',Set.of( 'G'),'D',Set.of( 'E'),'E',Set.of( 'F'),'F',Set.of(),'G',Set.of( 'D','F','H'),'H',Set.of(),'I',Set.of( 'A','E','J'),'J',Set.of( 'E','F','G')));
            assertEquals("cyklicky/Test_cyklicky", false, g.cyklicky());
        }
        {//g={A=[D, H], B=[I], C=[J], D=[H, J], E=[], F=[], G=[C, D, F], H=[D, G, J], I=[E, G, J], J=[A, F, H]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'D','H'),'B',Set.of( 'I'),'C',Set.of( 'J'),'D',Set.of( 'H','J'),'E',Set.of(),'F',Set.of(),'G',Set.of( 'C','D','F'),'H',Set.of( 'D','G','J'),'I',Set.of( 'E','G','J'),'J',Set.of( 'A','F','H')));
            assertEquals("cyklicky/Test_cyklicky", true, g.cyklicky());
        }
        {//g={A=[], B=[], C=[E, F, I], D=[A, C, E, I], E=[J], F=[C], G=[C, E], H=[], I=[D], J=[A, C]}
            var g = new Graph<Character>(Map.of( 'A',Set.of(),'B',Set.of(),'C',Set.of( 'E','F','I'),'D',Set.of( 'A','C','E','I'),'E',Set.of( 'J'),'F',Set.of( 'C'),'G',Set.of( 'C','E'),'H',Set.of(),'I',Set.of( 'D'),'J',Set.of( 'A','C')));
            assertEquals("cyklicky/Test_cyklicky", true, g.cyklicky());
        }
        {//g={A=[B, I, J], B=[C, I], C=[B, G, H], D=[E, G, J], E=[F], F=[B, I], G=[D], H=[B], I=[], J=[B, C]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'B','I','J'),'B',Set.of( 'C','I'),'C',Set.of( 'B','G','H'),'D',Set.of( 'E','G','J'),'E',Set.of( 'F'),'F',Set.of( 'B','I'),'G',Set.of( 'D'),'H',Set.of( 'B'),'I',Set.of(),'J',Set.of( 'B','C')));
            assertEquals("cyklicky/Test_cyklicky", true, g.cyklicky());
        }
        {//g={A=[], B=[C, F, H], C=[A, D, G], D=[B, F], E=[], F=[B, D, E, I], G=[D, H, J], H=[A, B, D, I], I=[A, D], J=[]}
            var g = new Graph<Character>(Map.of( 'A',Set.of(),'B',Set.of( 'C','F','H'),'C',Set.of( 'A','D','G'),'D',Set.of( 'B','F'),'E',Set.of(),'F',Set.of( 'B','D','E','I'),'G',Set.of( 'D','H','J'),'H',Set.of( 'A','B','D','I'),'I',Set.of( 'A','D'),'J',Set.of()));
            assertEquals("cyklicky/Test_cyklicky", true, g.cyklicky());
        }
        {//g={A=[C, D], B=[], C=[D], D=[E], E=[], F=[D, G], G=[], H=[A, D, I, J], I=[G], J=[]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'C','D'),'B',Set.of(),'C',Set.of( 'D'),'D',Set.of( 'E'),'E',Set.of(),'F',Set.of( 'D','G'),'G',Set.of(),'H',Set.of( 'A','D','I','J'),'I',Set.of( 'G'),'J',Set.of()));
            assertEquals("cyklicky/Test_cyklicky", false, g.cyklicky());
        }
        {//g={A=[], B=[J], C=[J], D=[], E=[D, F], F=[A, B, I], G=[E, H, J], H=[B, E, F], I=[J], J=[A, B, I]}
            var g = new Graph<Character>(Map.of( 'A',Set.of(),'B',Set.of( 'J'),'C',Set.of( 'J'),'D',Set.of(),'E',Set.of( 'D','F'),'F',Set.of( 'A','B','I'),'G',Set.of( 'E','H','J'),'H',Set.of( 'B','E','F'),'I',Set.of( 'J'),'J',Set.of( 'A','B','I')));
            assertEquals("cyklicky/Test_cyklicky", true, g.cyklicky());
        }
        {//g={A=[B], B=[D, G], C=[A, B, E, J], D=[], E=[], F=[C, D, J], G=[E, F], H=[B, I], I=[G, J], J=[]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'B'),'B',Set.of( 'D','G'),'C',Set.of( 'A','B','E','J'),'D',Set.of(),'E',Set.of(),'F',Set.of( 'C','D','J'),'G',Set.of( 'E','F'),'H',Set.of( 'B','I'),'I',Set.of( 'G','J'),'J',Set.of()));
            assertEquals("cyklicky/Test_cyklicky", true, g.cyklicky());
        }
        {//g={A=[D, F], B=[D, F, H], C=[], D=[], E=[A, D], F=[J], G=[D, H], H=[D, G, J], I=[H], J=[]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'D','F'),'B',Set.of( 'D','F','H'),'C',Set.of(),'D',Set.of(),'E',Set.of( 'A','D'),'F',Set.of( 'J'),'G',Set.of( 'D','H'),'H',Set.of( 'D','G','J'),'I',Set.of( 'H'),'J',Set.of()));
            assertEquals("cyklicky/Test_cyklicky", true, g.cyklicky());
        }
        {//g={A=[F, G, J], B=[H], C=[D, I], D=[I, J], E=[G, I], F=[A], G=[], H=[], I=[H], J=[B]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'F','G','J'),'B',Set.of( 'H'),'C',Set.of( 'D','I'),'D',Set.of( 'I','J'),'E',Set.of( 'G','I'),'F',Set.of( 'A'),'G',Set.of(),'H',Set.of(),'I',Set.of( 'H'),'J',Set.of( 'B')));
            assertEquals("cyklicky/Test_cyklicky", true, g.cyklicky());
        }
        {//g={A=[], B=[A, E, I], C=[D, G, H, I], D=[E, H, J], E=[F], F=[B, D, E], G=[E, H], H=[A, G], I=[G, H], J=[E, F, H]}
            var g = new Graph<Character>(Map.of( 'A',Set.of(),'B',Set.of( 'A','E','I'),'C',Set.of( 'D','G','H','I'),'D',Set.of( 'E','H','J'),'E',Set.of( 'F'),'F',Set.of( 'B','D','E'),'G',Set.of( 'E','H'),'H',Set.of( 'A','G'),'I',Set.of( 'G','H'),'J',Set.of( 'E','F','H')));
            assertEquals("cyklicky/Test_cyklicky", true, g.cyklicky());
        }
        {//g={A=[], B=[I], C=[D, F, G, H], D=[E, J], E=[D], F=[B, I], G=[], H=[A, B, D, E], I=[A, C], J=[C, I]}
            var g = new Graph<Character>(Map.of( 'A',Set.of(),'B',Set.of( 'I'),'C',Set.of( 'D','F','G','H'),'D',Set.of( 'E','J'),'E',Set.of( 'D'),'F',Set.of( 'B','I'),'G',Set.of(),'H',Set.of( 'A','B','D','E'),'I',Set.of( 'A','C'),'J',Set.of( 'C','I')));
            assertEquals("cyklicky/Test_cyklicky", true, g.cyklicky());
        }
        {//g={A=[], B=[], C=[E, G, J], D=[E, H], E=[], F=[A, B, C], G=[F, H], H=[B], I=[C, H, J], J=[A, C, D]}
            var g = new Graph<Character>(Map.of( 'A',Set.of(),'B',Set.of(),'C',Set.of( 'E','G','J'),'D',Set.of( 'E','H'),'E',Set.of(),'F',Set.of( 'A','B','C'),'G',Set.of( 'F','H'),'H',Set.of( 'B'),'I',Set.of( 'C','H','J'),'J',Set.of( 'A','C','D')));
            assertEquals("cyklicky/Test_cyklicky", true, g.cyklicky());
        }
        {//g={A=[I, J], B=[C], C=[G, H], D=[C, F, H], E=[A, B, G, J], F=[H], G=[A, F, J], H=[D, F], I=[D], J=[B, C, F, H]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'I','J'),'B',Set.of( 'C'),'C',Set.of( 'G','H'),'D',Set.of( 'C','F','H'),'E',Set.of( 'A','B','G','J'),'F',Set.of( 'H'),'G',Set.of( 'A','F','J'),'H',Set.of( 'D','F'),'I',Set.of( 'D'),'J',Set.of( 'B','C','F','H')));
            assertEquals("cyklicky/Test_cyklicky", true, g.cyklicky());
        }
        {//g={A=[B, D], B=[D, I, J], C=[B, E, H], D=[J], E=[], F=[J], G=[C, H, I], H=[C, I], I=[], J=[C, D, G]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'B','D'),'B',Set.of( 'D','I','J'),'C',Set.of( 'B','E','H'),'D',Set.of( 'J'),'E',Set.of(),'F',Set.of( 'J'),'G',Set.of( 'C','H','I'),'H',Set.of( 'C','I'),'I',Set.of(),'J',Set.of( 'C','D','G')));
            assertEquals("cyklicky/Test_cyklicky", true, g.cyklicky());
        }
        {//g={A=[G], B=[], C=[F], D=[G], E=[G, I], F=[B, D, I], G=[C, E, H], H=[I], I=[A, F], J=[D, E, G]}
            var g = new Graph<Character>(Map.of( 'A',Set.of( 'G'),'B',Set.of(),'C',Set.of( 'F'),'D',Set.of( 'G'),'E',Set.of( 'G','I'),'F',Set.of( 'B','D','I'),'G',Set.of( 'C','E','H'),'H',Set.of( 'I'),'I',Set.of( 'A','F'),'J',Set.of( 'D','E','G')));
            assertEquals("cyklicky/Test_cyklicky", true, g.cyklicky());
        }
        scoring.updateScore("lang:common_list_test_scoring_name",   33.0D);
    }
}