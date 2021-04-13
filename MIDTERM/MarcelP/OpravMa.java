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
            result.add(new LinkedList<>(list));       // do result prida dalsi, este dlhsi riadok 0,1, ..., i
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
        if(a==null)
            throw new Exception("zly vstup");
        long min=Long.MAX_VALUE, max=0;
        for(String[] s:a){
            if(s==null)
                throw new Exception("zly vstup");
            min=Math.min(min, s.length);
            max=Math.max(max, s.length);
        }
        if(min!=max)
            throw new Exception("zly vstup");
        for(String[] s:a){
            for(String s2:s)
                if(s2==null)
                    throw new Exception("zly vstup");
        }
    }
}
