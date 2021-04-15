import java.util.HashMap;
import java.util.Map;

public class Rekurzia {

    public static long maxHodnota() {
        long max = 0;
        for(int a = 0; a < 100; a++)
            for(int b = 0; b <= a; b++) {
                long val = foo(a, b);
                if (val < 0) continue;
                if (val > max) {
                    max = foo(a, b);
                }
            }
        return max;
    }

    public static int kontrapriklad1() {
        for(int a = 1; a < 20; a++)
            for(int b = 1; b < 20; b++)
                if ( foo(a,b) != 2*(foo(a-1,b) + foo(a,b-1) - foo(a-1,b-1))) {
                    return a+b;
                }
        return -1;
    }

    public static int kontrapriklad2() {
        // ja to viem dokazat :)
        return -1;
    }

    static Map<Long, Long> hm = new HashMap<>();
    public static long foo(int a, int b) {
        if (a == 0 && b == 0)
            return 1;
        if (hm.containsKey(((long)a<<16)+b))
            return hm.get(((long)a<<16)+b);
        else {
            long sum = 0L;
            boolean over = false;
            for (int i = 0; i <= a; i++)
                for (int j = 0; j <= b; j++) {
                    if (i == a && j == b) continue;
                    sum += foo(i, j);
                    if (sum < 0)
                        over = true;
                }
                hm.put(((long)a<<16)+b, sum);
                if (over)
                    return -1;
                else
                    return sum;
        }
    }
    public static void main(String[] args) {
        System.out.println(foo(20,20));
        for(int i = 0; i<10; i++) {
            for (int j = 0; j < 10; j++)
                System.out.print(foo(i, j) + "\t");
            System.out.println();
        }

        System.out.println(kontrapriklad1());
        System.out.println(kontrapriklad2());
        //foo3(15,36);

        // -- testy
        for(int i = 0; i<16; i++) {
             System.out.print("assertTrue(@foo(" + i + ","+ i + ")@ , " + foo(i, i) + " == foo(" + i + ","+ i + ") ); ");

            System.out.println();
        }
        System.out.println(foo(19,18));
        //3534274361610207232
        //3534274361610207232
        //9102433660539240448L
        System.out.println(foo(8,35)); // 9102433660539240448
        //System.out.println(foo(21,60)); //
        System.out.println(maxHodnota());
        //9222895948319948800
        //8, 35 -> 9102433660539240448

    }
}
