import java.util.Arrays;
import java.util.Locale;

public class Sucet {
    public static boolean sucet(int[] pole, int k){
        Arrays.sort(pole);                      // utriedenie pola
        int zac = 0;                                    // zaciatok pola
        int kon = pole.length - 1;                      // koniec pola

        while (zac < kon) {                         // porovnavam a posuvam sa smerom k stredu
            if (pole[zac] + pole[kon] == k)
                return true;                        // ak sa sucet rovna koncim
            else if (pole[zac] + pole[kon] > k)
                kon--;                              // ak je vacsi, musim s hodnotou klasnut
            else
                zac++;                              // ak nie, SUCET je mensi posuvam sa k vyssim hodnotam
        }
        return false;
    }

    public static void main(String[] args){
        System.out.println(sucet(new int[]{11, 16, 3, 17}, 19));
    }
}
