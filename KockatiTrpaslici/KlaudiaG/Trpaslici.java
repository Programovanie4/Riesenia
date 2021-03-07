import java.math.BigInteger;
import java.util.TreeSet;


public class Trpaslici {

    //inspirovane 2.prednaskou
    static TreeSet<Integer> delitele(int n) {
        TreeSet<Integer> res = new TreeSet<>();
        for (int a = 1; (long)a*a <= n; a++) {
            if (n % a == 0) {
                res.add(a);
                res.add(n/a);
            }
        }
        return res;
    }

    public static long[] helpp(int n){
        long[] pole = new long[2];
        long mini  = 0;
        long maxi = 0;
        long vypocet;
        int pocet = 0;
        for (int a : delitele(n)){
            for (int b: delitele(n/a)){
                if (b < a) continue;
                if (a*b <= 0) continue;
                int c = n/(a*b);
                if (c < b) continue;
                if (c*a*b == n){
                    vypocet = 2L*a *b + 2L*b *c + 2L*a*c;
                    if (mini == 0 || vypocet < mini){
                        mini = vypocet;
                    }
                    if (maxi < vypocet){
                        maxi = vypocet;
                    }
                    pocet++;
                }
            }
        }
        pole[0] = pocet;
        pole[1] = maxi - mini;
        return pole;
    }

    public static int pocetMoznosti(int n) {
       return (int)helpp(n)[0];
    }

    public static long rozdiel(int n){
        return helpp(n)[1];
    }

    public static void main(String[] args) {
        //System.out.println(pocetMoznosti(12));
        System.out.println(rozdiel(778950030)); //3084108264
        System.out.println(rozdiel(1639731734)); //4918926772
    }
}
