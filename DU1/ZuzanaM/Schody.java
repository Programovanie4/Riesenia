public class Schody {
    //credits to matematika(3)
    public static int pocet12(int n){
        if(n < 3) return n;
        int a = 1;
        int b = 2;
        for(int i = 2; i < n; i++ ){
            int pom = b;
            b += a;
            a = pom;
        }
        return b;
    }

    public static int pocet123(int n){
        if(n < 3) return n;
        if(n == 3) return 4;
        int a = 1;
        int b = 2;
        int c = 4;
        for(int i = 3; i < n; i++ ){
            int pom = c;
            c += (a + b);
            a = b;
            b = pom;
        }
        return c;
    }

    public static void main(String[] args) {
        for (int i = 1; i < 8; i++) {
            System.out.println(pocet123(i));
        }
    }
}
