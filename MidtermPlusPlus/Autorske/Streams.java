import java.util.*;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

/**
 * 5.priklad,
 */

public class Streams {
    /**
     * @return - vrati IntStream celych cisel poradi n, n,n-1, n,n-1,n-2, ... , n,n-1,..,1
     */
    public static IntStream pyramida(int n) {
        //return null;
        return IntStream.range(0,n+1).flatMap(i -> IntStream.range(0,i).map(j -> n-j));
    }

    /**
     * @return - prefiltruje input stream a ponechá v ňom len čísla, ktorých dekadický zápis je symetrický,
     * tzv. palindromy (v tom istom poradí). Napríklad, 11, 121, 4224 sú palindromy a 12, 110 nie sú palindromy.
     */
    public static IntStream palindromy(IntStream input) {
        // return null;
        IntPredicate jePalindrom = n -> new StringBuilder(""+n).reverse().toString().equals(""+n);
        return input.filter(jePalindrom);
    }

    /**
     * @return - zo zoznamu cisel na vstup a vytvori mapu, ktora zobrazuje zlozene cislo zo vstupu na pocet
     * jeho delitelov medzi 2..n-1. Prvocisla na vstupe maju pocet delitelov 0, preto sa do mapy nezobrazuju
     */
    public static Map<Integer, Integer> pocetDelitelov(List<Integer> vstup) {
        // return null;
        Function<Integer, Integer> pocetDelitelov = n -> (int)IntStream.range(2,n).filter(d -> n % d == 0).count();
        return vstup.stream().filter(x -> pocetDelitelov.apply(x) > 0)
                    .collect(Collectors.toMap(x->x, pocetDelitelov));
    }
    /**
     * @return - zo zoznamu cisel na vstup a vytvori mapu, ktora zobrazuje prvocislo cislo vsetky cisla vstupu,
     * ktorymi je cislo delitelne. Mapa zobrazuje len tie prvocisla, ktore delia aspon jedno cislo vstupu.
     */
    public static Map<Integer, Set<Integer>> delitelne(List<Integer> vstup) {
        return null;
    }

    /**
     * @return pre vstupne cislo vyrobi mapu, ktora zobrazuje prvocislo, ktore ho deli, na exponent, ktory ma v
     * prvociselom rozklade. Priklad: pre 967032 = 2^3 * 3^3 * 11^2 * 37, mapa bude Map.of(2,3 3,3, 11,2, 37,1)
     */
    public static Map<Integer, Integer> rozklad(Integer vstup) {
        // return null;
        IntPredicate jePrvocislo = n -> IntStream.range(2,1+(int)Math.floor(Math.sqrt(n))).allMatch(d -> n % d > 0);
        //System.out.println(IntStream.range(2, vstup+1).filter(jePrvocislo).boxed().collect(Collectors.toSet()));
        return IntStream.range(2, vstup+1).filter(jePrvocislo).filter(d -> vstup%d == 0).boxed()
                .collect(Collectors.toMap(x->x, x->exponent(vstup, x)));
    }
    private static Integer exponent(Integer vstup, Integer d) {
        //System.out.println(vstup + "," +d);
        return (vstup >= d && vstup % d == 0)?(1+exponent(vstup/d,d)):0;
    }
    public static void main(String[] args) {
        System.out.println(pyramida(5).boxed().collect(Collectors.toList()));
        System.out.println(palindromy(IntStream.range(0,100)).boxed().collect(Collectors.toList()));
        System.out.println(pocetDelitelov(IntStream.range(0,100).boxed().collect(Collectors.toList())));
        System.out.println(mapToString(pocetDelitelov(IntStream.range(0,100).boxed().collect(Collectors.toList()))));

        System.out.println(rozklad(967032 ));

        ///////////////////////////////////
        int test = 0;
        Random rnd = new Random();
        for (int tt = 0; tt < 4; tt++) {
            System.out.println("@Test");
            System.out.println("public void test" + tt + "() {");
            for (int loop = 0; loop < 20; loop++) {
                if (tt == 0) {
                    System.out.println("\t assertEquals(\"pyramida/Test" + test + "\", " +
                            listToString(pyramida(loop).boxed().collect(Collectors.toList())) +
                            ", Streams.pyramida("+ loop +").boxed().collect(Collectors.toList()));");
                }
                if (tt == 1) {
                    System.out.println("\t assertEquals(\"palindromy/Test" + test + "\", " +
                            listToString(palindromy(IntStream.range(0,1000*loop)).boxed().collect(Collectors.toList()))+
                            ", Streams.palindromy(IntStream.range(0,1000*"+ loop +")).boxed().collect(Collectors.toList()));");
                }

                if (tt == 2) {
                    System.out.println("\t assertEquals(\"pocetDelitelov/Test" + test + "\", " +
                            listToString(pocetDelitelov(IntStream.range(0,10*loop).boxed().collect(Collectors.toList())).values().stream().sorted().collect(Collectors.toList()))+
                            ", Streams.pocetDelitelov(IntStream.range(0,10*"+ loop +").boxed().collect(Collectors.toList())).values().stream().sorted().collect(Collectors.toList()));");
                }
                if (tt == 3) {
                    int inp =
                            rnd.nextInt(2+10*loop)
                            * rnd.nextInt(2+10*loop)
                            * rnd.nextInt(2+10*loop);
                    System.out.println("\t assertEquals(\"pocetDelitelov/Test" + test + "\", " +
                            listToString(rozklad(inp).values().stream().sorted().collect(Collectors.toList()))+
                            ", Streams.rozklad("+ inp +").values().stream().sorted().collect(Collectors.toList()));");
                }
                test++;
            }
            switch (tt) {
                case 0: case 1:
                    System.out.println("scoring.updateScore(\"lang:common_list_test_scoring_name\",   17.0D);");
                    break;
                case 2: case 3:
                    System.out.println("scoring.updateScore(\"lang:common_list_test_scoring_name\",   33.0D);");
                    break;
            }
            System.out.println("}");
        }
    }

    public static String listToString(Collection<Integer> set) {
        String s = "List.of(";
        char ciarka = ' ';
        for (Integer n : set) {
            s += ciarka + "" + n + ""; ciarka = ',';
        }
        s += ")";
        return s;
    }
    public static String mapToString(Map<Integer, Integer> map) {
        String s = "Map.of(";
        char ciarka = ' ';
        for (Integer k : map.keySet()) {
            s += ciarka + " " + k + "," + map.get(k); ciarka = ',';
        }
        s += ")";
        return s;
    }
}