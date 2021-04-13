import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Streams {
    /**
     * @return - vrati IntStream celych cisel poradi
     * n,
     * n,n-1,
     * n,n-1,n-2,
     * ... ,
     * n,n-1,n-2,...,3,2,1
     */
    public static IntStream pyramida(int n)
    {
        List<Integer> res = new ArrayList<>();
        IntStream.range(1, n+1).forEach(dlzka ->
        {
            IntStream.range(0, dlzka)
                    .forEach(znak -> {res.add(n-znak);});
        });
        return res.stream().mapToInt(Integer::valueOf);
    }

    /**
     * @return - prefiltruje input stream a ponechá v ňom len čísla, ktorých dekadický zápis je symetrický,
     * tzv. palindromy (v tom istom poradí). Napríklad, 11, 121, 4224 sú palindromy a 12, 110 nie sú palindromy.
     */
    public static IntStream palindromy(IntStream input)
    {
            return input.filter(x ->
        {
            List<String> l = Arrays.asList(Integer.toString(x).split(""));
            List<String> l2 = new ArrayList<>(l);
            Collections.reverse(l2);
            return l.equals(l2);

        });
    }

    /**
     * @return - zo zoznamu cisel na vstup a vytvori mapu, ktora zobrazuje zlozene cislo zo vstupu na pocet
     * jeho delitelov medzi 2..n-1. Prvocisla na vstupe maju pocet delitelov 0, preto sa do mapy nezobrazuju
     */
    public static Map<Integer, Integer> pocetDelitelov(List<Integer> vstup)
    {
        Map<Integer, Integer> res = new HashMap<>();
        List<Integer> bez_prvocisel = vstup.stream().filter(x ->
        {
            return IntStream.range(2, x - 1).anyMatch(del -> x % del == 0);
        }).collect(Collectors.toList());
        bez_prvocisel.stream().forEach(x ->
        {
            List<Integer> delitele = IntStream.range(2, x).filter(k -> {return x%k==0;}).boxed().collect(Collectors.toList());
            res.put(x, delitele.size());
        });
        return res;
    }

    /**
     * @return pre vstupne cislo vyrobi mapu, ktora zobrazuje prvocislo, ktore ho deli, na exponent, ktory ma v
     * prvociselom rozklade. Priklad: pre 967032 = 2^3 * 3^3 * 11^2 * 37, mapa bude Map.of(2,3 3,3, 11,2, 37,1)
     */
    public static Map<Integer, Integer> rozklad(Integer vstup)
    {
        Map<Integer, Integer> res = new HashMap<>();
        List<Integer> prvocisla = IntStream.range(2, (int)Math.sqrt(vstup)).filter(x -> {return !IntStream.range(2, x - 1).anyMatch(del -> x % del == 0); }).boxed().collect(Collectors.toList());
        prvocisla.forEach(x -> res.put(x, 0));
        prvocisla.forEach(prv -> IntStream
                .range(0, (int)Math.sqrt(vstup)).forEach(nasobok ->
                {
                    if(vstup%Math.pow(prv, nasobok) == 0) res.put(prv, nasobok);
                }));
        Map<Integer, Integer> res2 = new HashMap<>();
        res.forEach((k, v) ->
        {
            if(v > 0) res2.put(k, v);
        });
        return res2;
    }
}