public class BiLandia {

    public static int najvacsia(int n) {
        // doesn't make sense to do binary search
        // 2^19 is max
        for (int i = 0; i < 21; i++) {
            double m = Math.pow(2, i);
            if (m > n) return (int) m / 2;
            if (n == m) return (int) m;
        }
        return 0;
    }

    public static int najmensia(int n) {
        int m = 0;
        while (m != n) {
            m = najvacsia(n - m);
            if (m == n) break;
            n -= m;
        }
        return m;
    }

    public static int damTringelt(int n) {
        int m = najvacsia(n);
        if (m == n) return n;
        return m * 2 - n;
    }

    public static int pocetMoznosti(int n) {
        int[] matrix1d = new int[n + 1];
        matrix1d[0] = 1;

        int[] coins = {1, 2, 4, 8, 16, 32, 64, 128};
        for (int c: coins) {
            for (int i = c; i < n + 1; i++) {
                matrix1d[i] += matrix1d[i - c];
            }
        }

        return matrix1d[n];
    }

    public static void main(String[] args) {
        /*
        System.out.println(pocetMoznosti(0) );  // = 1
        System.out.println(pocetMoznosti(1) );  // = 1 -- 1
        System.out.println(pocetMoznosti(2) );  // = 2 -- 1+1, 2
        System.out.println(pocetMoznosti(3) );  // = 2 -- 1+1+1,1+2
        */
        System.out.println(pocetMoznosti(4) );  // = 4 -- 1+1+1+1,1+1+2,2+2,4
        /*
        System.out.println(pocetMoznosti(5) );  // = 4 -- 1+1+1+1+1,1+1+1+2,1+2+2,1+4
        System.out.println(pocetMoznosti(6) );  // = 6 -- 1+1+1+1+1+1,1+1+1+1+2,1+1+2+2,2+2+2,2+4,1+1+4
        System.out.println(pocetMoznosti(7) );  // = 6 -- ...
        System.out.println(pocetMoznosti(8) );  // = 10
        System.out.println(pocetMoznosti(9) );  // = 10
        System.out.println(pocetMoznosti(10));  //  = 14
        System.out.println(pocetMoznosti(11));  //  = 14
        System.out.println(pocetMoznosti(12));  //  = 20
        System.out.println(pocetMoznosti(13));  //  = 20
        System.out.println(pocetMoznosti(14));  //  = 26
        System.out.println(pocetMoznosti(15));  //  = 26
        System.out.println(pocetMoznosti(16));  //  = 36
        System.out.println(pocetMoznosti(17));  //  = 36
        System.out.println(pocetMoznosti(18));  //  = 46
        System.out.println(pocetMoznosti(249));  //  = 46
        */
    }

}