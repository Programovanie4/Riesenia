import java.util.*;


public class Rekurzia {
    public static Map<Integer, Map<Integer, Long>> mem = new HashMap<>();

    /**
     *  predefinujte funkciu efektivnejsie, memoizacia je cesta
     *  uz vypocitane hodnoty si nejako pamatajte, a nepocitajte znova
     */
    public static long foo(int a, int b) {
        if (a == 0 && b == 0)
            return 1;
        else {
            long sum = 0L;
            for (int i = 0; i <= a; i++)
                for (int j = 0; j <= b; j++) {
                    if (i == a && j == b) continue;
                    if (!mem.containsKey(i)) mem.put(i, new HashMap<>());
                    if (!mem.get(i).containsKey(j)) mem.get(i).put(j, foo(i, j));
                    sum += mem.get(i).get(j);
                }
            return sum;
        }
    }

    /**
     * najvacsia hodnota funkcie long foo(int a, int b) v rozsahu typu long.
     */
    public static long maxHodnota() {
        foo(200, 200);
        List<Long> values = new ArrayList<>();
        for (int key: mem.keySet()) {
            values.addAll(mem.get(key).values());
        }
        return Collections.max(values);
    }

    /**
     * ak exituju 0 < a,b <= 15, ze foo(a,b) != 2*(foo(a-1,b) + foo(a,b-1) - foo(a-1,b-1)), vrat a+b
     * inak -1
     */
    public static int kontrapriklad1() {
        for (int a = 1; a <= 15; a++) {
            for (int b = 1; b <= 15; b++) {
                if (foo(a, b) != 2*(foo(a-1,b) + foo(a,b-1) - foo(a-1,b-1))) {
                    return a+b;
                }
            }
        }
        return -1; // doprogramuj
    }

    /**
     * ak exituju 1 < a,b < 1000000, ze foo(a,b) != 2*(foo(a-1,b) + foo(a,b-1) - foo(a-1,b-1)), vrat a+b
     * inak -1
     */
    public static int kontrapriklad2() {
        for (int a = 2; a <= 15; a++) {
            for (int b = 2; b <= 15; b++) {
                if (foo(a, b) != 2*(foo(a-1,b) + foo(a,b-1) - foo(a-1,b-1))) {
                    return a+b;
                }
            }
        }
        return -1; // doprogramuj
    }

    public static void main(String[] args) {
        System.out.println(foo(15, 15));    // 731420783788032L
        long hodnota = 731420783788032L;
        if (hodnota <= Long.MAX_VALUE)
            System.out.println("vzdy som true");
        else
            System.out.println("nikdy nie som false");
        System.out.println(maxHodnota());
    }
}
