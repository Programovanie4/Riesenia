import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OpravMa {

    public static void Uloha1(int[] a) {
        for (int i = 0; i < a.length ; i++) {
            for (int j = 1; j < a.length; j++) {
                if (a[j-1] > a[j]) {
                    int temp = a[j];
                    a[j] = a[j-1];
                    a[j-1] = temp;
                }
            }
        }
    }

    public static List<List<Integer>> Uloha2(int n) {
        List<Integer> list = new LinkedList<>();
        List<List<Integer>> result = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            list.add(i);            // list ma tvar 0,1, ... , i
            result.add(new ArrayList<>(list));       // do result prida dalsi, este dlhsi riadok 0,1, ..., i
        }
        return result;
        // 0
        // 0,1
        // 0,1,2
        //...
        // 0,1,....n-1
    }

    public static void Uloha3(int n) {
        // doprogramuj triedy Matfyzak, Bakalar, Magister, Profak
    }

    public static void Uloha4()  {
        // doprogramuj triedy Matfyzak, Bakalar, Magister
    }
    public static void Uloha5(String [][] a) throws Exception {
        if (a == null || a[0] == null) throw new Exception("zly vstup");
        int dlzka = a[0].length;
        for (int row = 0; row < a.length; row++) {
            if (a[row] == null) throw new Exception("zly vstup");
            if (a[row].length != dlzka) throw new Exception("zly vstup");
            for (int col = 0; col < a[row].length; col++) {
                if (a[row][col] == null) throw new Exception("zly vstup");
            }
        }
        // doprogramuj
    }
}
