import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OpravMa {

    public static void Uloha1(int[] a) {
        for (int i = 0; i < a.length ; i++) {
            for (int j = a.length-1; j > i; j--) {
//            for (int j = i+1; j < a.length; j++) {
                if (a[j-1] > a[j]) {
                    int temp = a[j];
                    a[j] = a[j-1];
                    a[j-1] = temp;
                } // if
            } // for
        } // for
        //for (int elem:a) System.out.print(elem+",");
    }

    public static List<List<Integer>> Uloha2(int n) {
        List<Integer> list = new LinkedList<>();
        List<List<Integer>> result = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            list.add(i);            // list ma tvar 0,1, ... , i
            result.add(list);       // do result prida dalsi, este dlhsi riadok 0,1, ..., i
            list = new ArrayList<>(list);
        }
        return result;
        // 0
        // 0,1
        // 0,1,2
        //...
        // 0,1,....n-1
    }

    public static void Uloha3(int n) {
        var matfyz = List.of(new Bakalar(), new Magister());
        System.out.println(matfyz.stream().map(Matfyzak::goo).collect(Collectors.toSet()).size() > 1);  // true
        System.out.println(Matfyzak.foo() != Bakalar.foo());                    // not                  // true
        System.out.println(Matfyzak.foo().equals(Bakalar.foo()));                                       // true
        System.out.println(new Magister().goo() != new Bakalar().goo());        // not                  // true
        System.out.println(!new Magister().foo().equals(new Bakalar().goo()));  // not                  // true
        System.out.println(Profak.zoo() != Profak.zoo());                       // not                  // true
        System.out.println(!Profak.zoo().equals(Profak.zoo()));                 // not                  // true
    }

    public static void Uloha4()  {
        var b1 = new Bakalar("Palko");
        var b2 = new Bakalar("Ferko");
        var m1 = new Magister("Lukas");
        var m2 = new Magister("Jozko");
        System.out.println(new TreeSet<>(List.of(m1,m2)).size() == 2);
        System.out.println(new TreeSet<>(List.of(m1,m2)).first().equals(m2));

        System.out.println(new TreeSet<>(List.of(b1,b2)).size() == 1);

        System.out.println(new HashSet<>(List.of(b1,b2,m1,m2)).size() == 3);
    }
    public static void Uloha5(String [][] a) throws Exception {
         if (a == null) throw new Exception("zly vstup");
         Set<Integer> lens = new HashSet<>();
         for (String[] row : a) {
             if (row == null) throw new Exception("zly vstup");
             lens.add(row.length);
             for (String e : row)
                 if (e == null) throw new Exception("zly vstup");
         }
         if (lens.size() > 1) throw new Exception("zly vstup");
    }



    public static void main(String[] args) throws Exception {
        Uloha1(new int[] {5,3,1,2,2,1,2,3});
        var u2 = Uloha2(10);
        System.out.println(u2);
        Uloha4();
        Uloha5(null);
    }



}
