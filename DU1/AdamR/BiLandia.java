import java.util.*;


public class BiLandia {
    public static void main(String[] args) {

        for(int i: new int[]{100,200,300,400}){
            System.out.printf("pocetMoznosti(%d) = %d\n",i,pocetMoznosti(i));
        }
    }

    public static double log2(int N){
        return (Math.log(N) / Math.log(2));
    }

    public static int najvacsia(int n){
        return (int) Math.pow(2, (int)log2(n));
    }

    public static int najmensia(int n){
        int min = 0;
        while(n != 0){
            min = (int) Math.pow(2,Math.floor(log2(n)));
            n -= min;
        }
        return min;
    }

    public static int damTringelt(int n){
        if(najvacsia(n) == n){
            return n;
        }
        else{
            return (int) Math.pow(2, Math.ceil(log2(n)))-n;
        }
    }

    // inšpirované
    // https://stackoverflow.com/questions/4243831/how-to-count-possible-combination-for-coin-problem
    public static int pocetMoznosti(int n){
        if(n == 0) return 1;
        if(n < 0) return 0;

        int velkost = (int)log2(n);
        int[] mince = new int[velkost+1];
        int minca = 1;
        for(int i = 0; i < velkost; i++){
            mince[i] = minca;
            minca*=2;
        }
        mince[mince.length-1] = minca;
        return pocetMoznosti(n, mince, 0);
    }

    private static int pocetMoznosti(int n, int mince[], int index) {
        if (n == 0)
            return 1;
        else if (n < 0 || mince.length == index)
            return 0;
        else {
            int s1Mincou = pocetMoznosti(n-mince[index], mince, index);
            int bez1mince = pocetMoznosti(n, mince, index+1);
            return s1Mincou + bez1mince;
        }
    }
}
