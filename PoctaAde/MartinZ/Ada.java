import java.math.BigDecimal;

public class Ada {
    public static int ageAL(){
        return 36;
    }
    public static double bernoulli(int n){
        double ret = 1;

        if(n==0) return 1d;
        if(n==1) return -0.5d;
//        if(n%2 == 1) return 0;
        ret -= (n * 0.5d)/(n-1+1);
        for(int k = 0; k<n; k++) {
            if (k == 1) {
                continue;
            }
            double Bk = bernoulli(k);
            ret -= ((comb(n, k)* (Bk / (n - k + 1)))) ;
        }
        return ret;
    }
//    private static double fact(int n){
//        if(n<2){return 1;};
//        return n*fact(n-1);
//    }
    private static int comb(int n, int k){
        //https://www.geeksforgeeks.org/pascal-triangle/
        int res = 1;

        if (k > n - k)
            k = n - k;

        for (int i = 0; i < k; ++i)
        {
            res *= (n - i);
            res /= (i + 1);
        }
        return res;
    }

    public static void main(String[] args) {
//        for(int i = 0; i < 21; i++) {
//            System.out.println(""+i + " " + bernoulli(i));
//        }
        System.out.println(""+26 + " " + bernoulli(26));
    }
}
