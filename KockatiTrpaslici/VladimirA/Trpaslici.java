import java.util.*;

public class Trpaslici {

    public static int pocetMoznosti(int n){
        int count = 0;
        Integer[] d = delitele(n);
        for (int i = 0; i < d.length; i++) {
            for (int j = i; j < d.length; j++) {
                for (int k = j; k < d.length; k++) {
                    if((long) d[i] *d[j] *d[k] == n){
                        count++;
                    }
                }
            }
        }
        return count;
    }

    public static long rozdiel(int n){
        Integer[] tmp = delitele(n);
        long najvacsia = 0;
        long najmensia = Long.MAX_VALUE;
        for (int i = 0; i < tmp.length; i++) {
            for (int j = i; j < tmp.length; j++) {
                for (int k = j; k < tmp.length; k++) {
                    long plocha = 0;
                    if((long) tmp[i] * tmp[j] * tmp[k] == n) {
                        plocha = 2*((long) tmp[i]*tmp[j] + (long) tmp[i]*tmp[k] + (long) tmp[j]*tmp[k]);
                        if(plocha > najvacsia) najvacsia = plocha;
                        if(plocha < najmensia) najmensia = plocha;
                    };
                }
            }
        }
        return najvacsia-najmensia;
    }

    // z prednasky:
    static Integer[] delitele(int n) {
        Integer[] res = new Integer[10]; // naalokujem najprv pre 10 prvkov, ak ich je viac, realokujem a kopcim do 2* vacsieho pola
        int count = 0;
        for (int a = 1; (long) a * a <= n; a++) { // po odmocninu n
            if (n % a == 0) {
                if (count + 2 > res.length) { // tu vidim ze musim realoknut
                    Integer[] newres = new Integer[res.length * 2]; // reallocate 2*
                    System.arraycopy(res,0,newres,0,count); // kopia pola do noveho
                    res = newres;
                }
                res[count++] = a; // pridanie mensieho delitela
                if (a != n/a)
                    res[count++] = n/a; // nesmu sa rovnat
            }
        }
        res = Arrays.copyOf(res,count); // orez vysledne pole
        Arrays.sort(res);
        return res;
    }
//
//    public static void main(String[] args) {
//        System.out.println("Pocet moznosti = " + pocetMoznosti(2147483646));
//        System.out.println("zlozene z: " + Arrays.toString(delitele(2147483646)));
//        System.out.println("rozdiel obsahov = " + rozdiel(2147483646)); // expected: 8579724824
//    }
}
