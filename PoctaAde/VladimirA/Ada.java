import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

public class Ada {

    public static int ageAL() {
        return 36;
    }

// tento nainvny pristup mi moc nesiel:

//    private static int nCr(double n, double k){ // vzorec komb. cisla: n! / ((n-k)! * k!)
//        double citatel = 1;
//        for (int i = 1; i <= n; i++) citatel *= i;
//        double a = 1;
//        for (int i = 1; i <= n-k; i++) a *= i;
//        double b = 1;
//        for (int i = 1; i <= k; i++) b *= i;
//        double menovatel = a * b;
//        return (int) citatel / (int) menovatel;
//    }
//
//    public static double bernoulli(double n){
//            double res = 0;
//            for(double k = 0; k < n; k++){
//                res += nCr(n, k) * (bernoulli(k)/(n-k+1));
//            }
//            return 1 - res;
//    }

    private static double[][] vyrobPascalovT(int n){                // vytvori Pascalov trojuholnik
        double[][] pascalovTrojuholnik = new double[n+1][n+1];
        for (int i = 1; i <= n; i++) pascalovTrojuholnik[0][i] = 0;
        for (int i = 0; i <= n; i++) pascalovTrojuholnik[i][0] = 1;
        for (int i = 1; i <= n; i++){
            for (int k = 1; k <= n; k++){
                pascalovTrojuholnik[i][k] = pascalovTrojuholnik[i-1][k-1] + pascalovTrojuholnik[i-1][k];
            }
        }
        return pascalovTrojuholnik;
    }

    // https://functions.wolfram.com/IntegerFunctions/BernoulliB/06/ShowAll.html
    public static double bernoulli(double n){
        if(n == 0) return 1.0;
        if(n == 1) return -0.5;
        if(n % 2 == 1) return 0.0; // vsetky bernoulliho cisla pre neparne n (okrem 1) su 0
        n += 1;
        double[][] trojuholnikP = vyrobPascalovT((int) n);
        BigDecimal[] bernoullihoCisla = new BigDecimal[(int) n*2]; // alokujem si pole typu BigDecimal dlzky n*2 (pre pripad (pre n+1 mi to spadlo))
        bernoullihoCisla[0] = new BigDecimal(1);     // B(0) = 1
        bernoullihoCisla[1] = BigDecimal.valueOf(-0.5);  // B(1) = -0.5
        for (int k = 2; k < n; k++) {                    // idem od druheho indexu, prve dva mam zaplnene 2,3,4,..,n-1
            bernoullihoCisla[k] = new BigDecimal(0);
            // nasledovnych par riadkov prida do pola bernoullihoCisla podla pozicie (v zavislosti od n) vypocitane bernoulliho cislo
            for (int i = 0; i < k; i++) {                // 0,1,2,..,k-1
                BigDecimal tmpT = BigDecimal.valueOf(trojuholnikP[k + 1][k + 1 - i]);  // cislo z Pascalovho trojuholnika
                bernoullihoCisla[k] = bernoullihoCisla[k].subtract(tmpT.multiply(bernoullihoCisla[i])); // bernoullihoCisla[k] = bernoullihoCisla[k] - tmpT * bernoullihoCisla[i]
            }
            bernoullihoCisla[k] = bernoullihoCisla[k].divide(new BigDecimal(k).add(new BigDecimal(1)), 17, RoundingMode.CEILING); // bernoullihoCisla[k] = bernoullihoCisla[k] / (k + 1)
                                                                                                                                           // zaokruhlujem na 17 des. miest smerom hore
        }
        return bernoullihoCisla[(int) n-1].doubleValue(); // konvertnem BigDecimal na double
    }


    public static void main(String[] args) {
        for (int i = 0; i <13; i++) {
            System.out.println(bernoulli(i));
        }
    }

}
