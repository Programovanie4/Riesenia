import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OpravMa {

    public static void Uloha1(int[] a) {
        for (int i = 0; i < a.length-1 ; i++) {
            for (int j = 0; j < a.length-1; j++) {
                if (a[j] > a[j+1]) {
                    int temp = a[j+1];
                    a[j+1] = a[j];
                    a[j] = temp;
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
    public static void Uloha5(String [][] a) throws Exception
    {
        if(a == null) throw new Exception("zly vstup");
        if(a.length == 0) return;
        int n = a.length;
        if(a[0] == null) throw new Exception("zly vstup");
        int m = a[0].length;
        for(int i=0; i<n; i++)
        {
            if(a[i].length != m)
            {
                throw new Exception("zly vstup");
            }
            for(int j=0; j<m; j++)
            {
                if(a[i][j] == null) throw new Exception("zly vstup");
            }

        }
    }

    public static void main(String[] args)
    {
        int [] p = new int [] {2, 1, 3, 12, 4};
        Uloha1(p);
        //for(int i=0; i<5;i++) System.out.println(p[i]);
    }
}
