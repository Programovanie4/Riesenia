import java.util.*;
import java.util.stream.Collector;
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
    public static IntStream pyramida(int n) {
        List<Integer> res = new ArrayList<>();
        IntStream.range(0,n+1).forEach(i->IntStream.range(0,i).forEach(j->res.add(n-j)));
        return res.stream().mapToInt(i->i);

    }

    /**
     * @return - prefiltruje input stream a ponechá v ňom len čísla, ktorých dekadický zápis je symetrický,
     * tzv. palindromy (v tom istom poradí). Napríklad, 11, 121, 4224 sú palindromy a 12, 110 nie sú palindromy.
     */
    public static IntStream palindromy(IntStream input) {
        return input.filter(a->{
            var r=(String.valueOf(a).toCharArray());
            for(int i=0;i<r.length;i++){
                if(r[i]!=r[r.length-1-i])
                    return false;
            }
            return true;
        });
    }

    /**
     * @return - zo zoznamu cisel na vstup a vytvori mapu, ktora zobrazuje zlozene cislo zo vstupu na pocet
     * jeho delitelov medzi 2..n-1. Prvocisla na vstupe maju pocet delitelov 0, preto sa do mapy nezobrazuju
     */
    public static Map<Integer, Integer> pocetDelitelov(List<Integer> vstup) {
        Map<Integer, Integer> r = new HashMap<>();
        vstup.forEach(i->{
                Set<Integer> delitele = new HashSet<>();
                IntStream.range(2, i).filter(j->i%j==0).forEach(j->delitele.add(j));
                if(delitele.size()!=0)
                    r.put(i, delitele.size());
            });

        return r;
    }

    /**
     * @return pre vstupne cislo vyrobi mapu, ktora zobrazuje prvocislo, ktore ho deli, na exponent, ktory ma v
     * prvociselom rozklade. Priklad: pre 967032 = 2^3 * 3^3 * 11^2 * 37, mapa bude Map.of(2,3 3,3, 11,2, 37,1)
     */
    public static Map<Integer, Integer> rozklad(Integer vstup) {
        IntStream is = IntStream.range(2, (int)(Math.sqrt(vstup))).filter(i->IntStream.range(2,(int)(Math.sqrt(i)+1)).allMatch(j->i%j!=0));

        //System.out.println(t.boxed().collect(Collectors.toList()));

        Map<Integer, Integer> r = new HashMap<>();
        is.forEach(j->{
            int i=vstup;
            int tmp=0;
            if(i%j==0) {
                while (i % j == 0) {
                    tmp++;
                    i /= j;
                }
                r.put(j, tmp);
            }
        });

        return r;
    }
}