import java.util.HashMap;
import java.util.Map;

public class Rekurzia {

    /**
     *  predefinujte funkciu efektivnejsie, memoizacia je cesta
     *  uz vypocitane hodnoty si nejako pamatajte, a nepocitajte znova
     */

    public static Map<Integer, Map<Integer, Long>> table = new HashMap<>();
    public static long foo(int a, int b)
    {
        //System.out.println(table);
        if(table.containsKey(a) && table.get(a).containsKey(b))
        {
            return table.get(a).get(b);
        }

        Long val = Long.valueOf(0);

        if (a == 0 && b == 0)
        {
            val = Long.valueOf(1);
        }

        else
        {

            for (int i = 0; i <= a; i++)
                for (int j = 0; j <= b; j++) {
                    if (i == a && j == b) continue;
                    val += foo(i, j);
                }
        }
        if(table.containsKey(a))
        {
            Map<Integer, Long> tmp = new HashMap<>(table.get(a));
            tmp.put(b, val);
            table.put(a, new HashMap<>(tmp));

        }
        else
        {
            table.put(a, new HashMap<>());
            table.get(a).put(b, val);
        }
        if(table.containsKey(b))
        {
            Map<Integer, Long> tmp = new HashMap<>(table.get(b));
            tmp.put(a, val);
            table.put(b, new HashMap<>(tmp));

        }
        else
        {
            table.put(b, new HashMap<>());
            table.get(b).put(a, val);
        }
        return val;
    }

    /**
     * najvacsia hodnota funkcie long foo(int a, int b) v rozsahu typu long.
     */
    public static long maxHodnota()
    {
        Long max = 0L;
        Long tmp = 0L;
        for(int i=0; i<200; i++)
        {
            for(int j=0; j<200; j++)
            {
                tmp = foo(i, j);
                if(tmp > 0)
                {
                    max = Math.max(max, tmp);
                }
                else
                {
                    break;
                }
            }
        }
        return max;
    }

    /**
     * ak exituju 0 < a,b <= 15, ze foo(a,b) != 2*(foo(a-1,b) + foo(a,b-1) - foo(a-1,b-1)), vrat a+b
     * inak -1
     */
    public static int kontrapriklad1()
    {
        for(int i=1; i<=15; i++)
        {
            for(int j=1; j<=15; j++)
            {
                if(foo(i, j) != 2*foo(i-1, j)+foo(i, j-1)+foo(i-1, j-1)) return i+j;
            }
        }
        return -1;
    }

    /**
     * ak exituju 1 < a,b < 1000000, ze foo(a,b) != 2*(foo(a-1,b) + foo(a,b-1) - foo(a-1,b-1)), vrat a+b
     * inak -1
     */
    public static int kontrapriklad2() {
        return -1; // doprogramuj
    }

    public static void main(String[] args) {
        System.out.println(foo(15, 15));    // 731420783788032L
        System.out.println(maxHodnota());
        System.out.println(kontrapriklad1());

        long hodnota = 731420783788032L;
        if (hodnota <= Long.MAX_VALUE)
            System.out.println("vzdy som true");
        else
            System.out.println("nikdy nie som false");
    }
}
