import java.util.*;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Streams {
    /**
     * @return - vrati IntStream celych cisel poradi
     * n,
     * n,n-1,
     * n,n-1,n-2,
     * ... ,
     * n,n-1,n-2,...,3,2,1
     */
    public static IntStream pyramida(int n) {
        return IntStream.range(1, n+1)
                .flatMap(x -> IntStream.range(0, x).map(e -> n-e));
    }

    /**
     * @return - prefiltruje input stream a ponechá v ňom len čísla, ktorých dekadický zápis je symetrický,
     * tzv. palindromy (v tom istom poradí). Napríklad, 11, 121, 4224 sú palindromy a 12, 110 nie sú palindromy.
     */
    public static IntStream palindromy(IntStream input) {
        return input.mapToObj(Integer::toString).filter(n -> n.equals(new StringBuilder(n).reverse().toString())).mapToInt(Integer::parseInt);  // doprogramuj
    }

    /**
     * @return - zo zoznamu cisel na vstup a vytvori mapu, ktora zobrazuje zlozene cislo zo vstupu na pocet
     * jeho delitelov medzi 2..n-1. Prvocisla na vstupe maju pocet delitelov 0, preto sa do mapy nezobrazuju
     */
    public static Map<Integer, Integer> pocetDelitelov(List<Integer> vstup) {
        Map<Integer, Integer> a = new HashMap<>();
        vstup.stream().filter(n -> IntStream.range(2, n/2+1).anyMatch(x -> n%x == 0)).forEach(n -> {
            a.put(n, (int) IntStream.range(2, n/2+1).filter(x -> n%x == 0).count());
        });
        return a;
    }

    /**
     * @return pre vstupne cislo vyrobi mapu, ktora zobrazuje prvocislo, ktore ho deli, na exponent, ktory ma v
     * prvociselom rozklade. Priklad: pre 967032 = 2^3 * 3^3 * 11^2 * 37, mapa bude Map.of(2,3 3,3, 11,2, 37,1)
     */
    public static Map<Integer, Integer> rozklad(Integer vstup) {
//        Map<Integer, Integer> a = new HashMap<>();
//        Stream prvocisla = IntStream.range(2, vstup/2+1).filter(n -> IntStream.range(2, n/2+1).noneMatch(x -> n%x == 0))
//                .boxed()
//                .sorted(Collections.reverseOrder());
//        return a;
        return null;
    }
}