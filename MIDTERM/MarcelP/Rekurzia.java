public class Rekurzia {

    /**
     *  predefinujte funkciu efektivnejsie, memoizacia je cesta
     *  uz vypocitane hodnoty si nejako pamatajte, a nepocitajte znova
     */
    public static long foo(int a, int b) {
        if (a == 0 && b == 0)
            return 1;

        long[][] m = new long[a+1][b+1];
        m[0][0]=1;
        for(int I=0;I<=a;I++){
            for(int J=0;J<=b;J++) {
                long sum=0;
                for (int i = 0; i <= I; i++)
                    for (int j = 0; j <= J; j++) {
                        if (i == a && j == b) continue;
                        sum += m[i][j];
                    }
                m[I][J]=sum;
            }
        }

        return m[a][b];
        /*if (a == 0 && b == 0)
            return 1;
        else {
            long sum = 0L;
            for (int i = 0; i <= a; i++)
                for (int j = 0; j <= b; j++) {
                    if (i == a && j == b) continue;
                    sum += foo(i, j);
                }
            return sum;
        }*/
    }

    /**
     * najvacsia hodnota funkcie long foo(int a, int b) v rozsahu typu long.
     */
    public static long maxHodnota() {
        long max=0, bound=100;
        for(int i=0;i<bound;i++){
            for(int j=0;j<bound;j++){
                long r = foo(i,j);
                if(r<0)
                    break;
                max=Math.max(max, r);
            }
        }
        return max;
    }

    /**
     * ak exituju 0 < a,b <= 15, ze foo(a,b) != 2*(foo(a-1,b) + foo(a,b-1) - foo(a-1,b-1)), vrat a+b
     * inak -1
     */
    public static int kontrapriklad1() {
        for(int a=1;a<=15;a++){
            for(int b=1;b<=15;b++){
                if(foo(a,b) != 2*(foo(a-1,b) + foo(a,b-1) - foo(a-1,b-1)))
                    return a+b;
            }
        }
        return -1;
    }

    /**
     * ak exituju 1 < a,b < 1000000, ze foo(a,b) != 2*(foo(a-1,b) + foo(a,b-1) - foo(a-1,b-1)), vrat a+b
     * inak -1
     */
    public static int kontrapriklad2() {
        return -1;
        /*for(int a=2;a<1000000;a++){
            for(int b=2;b<1000000;b++){
                if(foo(a,b) != 2*(foo(a-1,b) + foo(a,b-1) - foo(a-1,b-1)))
                    return a+b;
            }
        }
        return -1;*/
    }

    public static void main(String[] args) {
        System.out.println(foo(15, 15));    // 731420783788032L
        long hodnota = 731420783788032L;
        if (hodnota <= Long.MAX_VALUE)
            System.out.println("vzdy som true");
        else
            System.out.println("nikdy nie som false");
        foo(5,5);
    }
}
