import java.util.*;
import java.util.stream.Collectors;

public class Gramatika {
    private Map<String, String> mapa;
    private  boolean prefix = false;
    private List<String> sortedKeys;
    public Gramatika(List<String> laveStrany, List<String> praveStrany) {
        mapa = new HashMap<>();
        for(int i =0 ; i < laveStrany.size(); i++){
            mapa.put(laveStrany.get(i), praveStrany.get(i));
        }
        sortedKeys = laveStrany.stream().sorted(Comparator.comparing(String::length)).collect(Collectors.toList());
        for(String s: laveStrany){
            for(String p : laveStrany){
                if(s.equals(p)){
                    continue;
                }
                if(s.startsWith(p) || p.startsWith(s)){
                    prefix = true;
                    return;
                }
            }
        }

    }

    public String deterministickyKrok(String slovo) {
        StringBuilder res = new StringBuilder();
        boolean changed;
        while (slovo.length() != 0){
            changed = false;
            for(int i = sortedKeys.size() - 1; i >= 0; i--){
                if(slovo.startsWith(sortedKeys.get(i)) || slovo.equals(sortedKeys.get(i))){
                    res.append(mapa.get(sortedKeys.get(i)));
                    slovo = slovo.replaceFirst(sortedKeys.get(i), "");
                    changed = true;
                    break;
                }

            }
            if(!changed){
                return null;
            }
        }
        return res.toString(); // toto doprogramujte
    }

    public Set<String> nedeterministickyKrok(String slovo) {
        Set<String> result = new HashSet<>();
        rek(slovo, new StringBuilder(), result);
        return result; // toto doprogramujte
    }

    private void rek(String slovo, StringBuilder temp, Set<String> result){
        if(slovo.length() == 0){
            result.add(temp.toString());
            return;
        }
        for(int i = sortedKeys.size() - 1; i >= 0; i--){
            if(slovo.startsWith(sortedKeys.get(i)) || slovo.equals(sortedKeys.get(i))){
                StringBuilder sb = new StringBuilder(temp);
                sb.append(mapa.get(sortedKeys.get(i)));
                rek(slovo.replaceFirst(sortedKeys.get(i), ""), sb, result);
            }

        }


    }

    public boolean jePrefixova() {
        return prefix; // toto doprogramujte
    }

    public static void main(String[] args) {
        List<String> lhs = List.of("a", "b", "ba", "ab", "aba", "bbbbb", "aaaa");
        List<String> rhs = List.of("aa", "bb", "ab", "aaa", "bab", "a", "a");

        Gramatika g = new Gramatika(lhs, rhs);

        String slovo = "aba";
        System.out.println(slovo);

        for (int i = 0; i < 4; ++i) {
            slovo = g.deterministickyKrok(slovo);
            System.out.println(slovo);
        }

// aba            - zaciatocne slovo
// bab            - 1. transformacny krok
// abbb           - 2. transformacny krok
// aaabbbb        - 3. transformacny krok
// aaaaaaabbbbbb  - 4. transformacny krok

        lhs = List.of("a", "b", "ba", "ab", "aba", "bbbbb", "aaaa");
        rhs = List.of("1", "2", "3", "4", "5", "6", "7");

        g = new Gramatika(lhs, rhs);
        System.out.println(g.deterministickyKrok("aba"));           // 5
        System.out.println(g.nedeterministickyKrok("aba"));         // [121, 13, 5, 41]

        g = new Gramatika(List.of("abc", "ccc", "cb", "ab"), List.of("->", "*", ":", "_"));

        System.out.println(g.deterministickyKrok("bacbac"));           // null
        System.out.println(g.jePrefixova());                           // true
        System.out.println(g.nedeterministickyKrok("bacbac"));         // []

        g = new Gramatika(List.of("abc", "ccc", "cb", "acb"), List.of("->", "*", ":", "_"));

        System.out.println(g.jePrefixova()); // false

    }
}
