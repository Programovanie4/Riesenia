import java.util.HashMap;

public class Ada {


    static HashMap<Integer, Double> memoryBernoulli = new HashMap<Integer, Double>();
    static HashMap<String, Long> memoryCombinatoric = new HashMap<String, Long>();


    public static int ageAL() {
        return 36;
    }

    /**
     * Funkciu na kombinatoricke cisla som nasiel na https://stackoverflow.com/questions/2201113/combinatoric-n-choose-r-in-java-math
     *
     */
    public static long choose(long total, long choose){
        if(total < choose)
            return 0;
        if(choose == 0 || choose == total)
            return 1;
        try {
            return memoryCombinatoric.get(String.valueOf(total)+ ","+ String.valueOf(choose));
        } catch (NullPointerException e) {
            long res =  choose(total - 1, choose - 1) + choose(total - 1, choose);
            memoryCombinatoric.put(String.valueOf(total) + "," + String.valueOf(choose), res);
            return res;
        }
    }

    /**
     * B+m
     * @param n
     * @return
     */
    public static double bernoulli2(int n) {
        if (n == 0) return 1;
        if (n == 1) return -0.5;
        double bNumber = 0;
        for (int k = 0; k < n; k++) {
            bNumber += (choose(n, k) * bernoulli2(k) / (n - k + 1));
        }
        return 1 - bNumber;
    }

    /**
     * B-m using memoization
     * @param n
     * @return
     */
    public static double bernoulli(int n) {
        if (n == 0) return 1;
        double bNumber = 0;
        for (int k = 0; k < n; k++) {
            try {
                bNumber += (choose(n, k) * memoryBernoulli.get(k) / (n - k + 1));
            } catch (NullPointerException e) {
                bNumber += (choose(n, k) * bernoulli(k) / (n - k + 1));
            }
        }
        memoryBernoulli.put(n, -bNumber);
        return - bNumber;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 30; i++) {
            System.out.print(i);
            System.out.print(" ");
            System.out.println(bernoulli(i));
        }

    }
}
