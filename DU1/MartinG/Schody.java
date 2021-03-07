public class Schody {

    public static int pocet12(int n) {
        if (n <= 0) return 0;
        if (n <= 2) return n;
        int a = 1, b = 2;
        for (int i = 3; i <= n; i++) {
            int c = a + b;
            a = b;
            b = c;
        }
        return b;   // a_n = a_n-1 + a_n-2
    }

    public static int pocet123(int n) {
        if (n <= 0) return 0;
        if (n <= 2) return n;
        if (n == 3) return 4;
        int a = 1, b = 2, c = 4;
        for (int i = 4; i <= n; i++) {
            int d = a + b + c;
            a = b;
            b = c;
            c = d;
        }
        return c;   // a_n = a_n-1 + a_n-2 + a_n-3
    }

    public static void main(String[] args) {
        System.out.println(pocet123(5));
    }

}
