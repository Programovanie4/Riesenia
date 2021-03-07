public class Trpaslici {
    public static int pocetMoznosti(int n){
        int poc = 0;
        for(int i = 1; i <= Math.sqrt(n); i++){
            if(n%i == 0) {
                for (int j = i; j <= n; j++) {
                    if ((n % (i * j) == 0 && j <= ((n / i) / j))) {
                        poc += 1;
                    }
                    if(j > (n/i)/j) break;
                }
            }
        }
        return poc;
    }
//chceme najst i1, i2, i3 s co najmensim suctom
    public static long rozdiel(int n){
        int i1 = 1;
        int i2 = 1;
        int i3 = n;

        for(int i = 1; i <= Math.sqrt(n); i++){
            if(n%i == 0) {
                for (int j = i; j <= n/i; j++) {
                    if ((n/i) % j == 0 && j <= ((n / i) / j)) {
                        long naj = i1;
                        naj += (i3 + i2);
                        long a = n/(i*j);

                        if(naj >= (i+j+a)) {
                            i1 = i;
                            i2 = j;
                            i3 = ((n / i) / j);
                        }
                    }
                    if(j > (n/i)/j) break;
                }
            }
        }
        long n2 = n;
        n2 *= 4;
        n2 += 2;
        long i11 = (long) i1;
        long i22 = (long) i2;
        long i33 = (long) i3;


        long i0 = 2*(i11*i22 + i22*i33 + i11*i33);
        long vysl = n2 - i0;
        return vysl;
    }

    public static void main(String[] args) {
        System.out.println(rozdiel(2147483646));
    }
}
