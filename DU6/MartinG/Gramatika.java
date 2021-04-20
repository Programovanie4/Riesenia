import java.util.*;


public class Gramatika {
    Map<String, String> rules;

    public Gramatika(List<String> laveStrany, List<String> praveStrany) {
        // rules from longest to shortest
        // https://stackoverflow.com/questions/25899929/in-java-sort-hash-map-by-its-key-length
        rules = new TreeMap<String, String>(
            new Comparator<String>() {
                @Override
                public int compare(String s1, String s2) {
                    if (s1.length() > s2.length()) {
                        return -1;
                    } else if (s1.length() < s2.length()) {
                        return 1;
                    } else {
                        return s1.compareTo(s2);
                    }
                }
        });

        for (int i = 0; i < laveStrany.size(); i++) {
            rules.put(laveStrany.get(i), praveStrany.get(i));
        }
    }

    public String deterministickyKrok(String slovo) {
        StringBuilder encoded = new StringBuilder();

        while (!slovo.isEmpty()) {
            boolean foundRule = false;
            for (String rule : rules.keySet()) {
                if (slovo.startsWith(rule)) {
                    encoded.append(rules.get(rule));
                    slovo = slovo.replaceFirst(rule, "");
                    foundRule = true;
                    break;
                }
            }
            if (!foundRule) return null;
        }

        return encoded.toString();
    }

    private void getAll(Set<String> ref, String word, String current) {
        if (word.isEmpty()) {
            ref.add(current);
            return;
        }
        for (String rule : rules.keySet()) {
            if (word.startsWith(rule)) {
                // ain't it elegant? (:
                getAll(ref, word.replaceFirst(rule, ""), current + rules.get(rule));
            }
        }
    }

    public Set<String> nedeterministickyKrok(String slovo) {
        Set<String> encodedWords = new HashSet<>();
        getAll(encodedWords, slovo, "");
        return encodedWords;
    }

    public boolean jePrefixova() {
        for (String leftSide: rules.keySet()) {
            for (String rightSide: rules.keySet()) {
                // leftSide != rightSide => leftSide is prefix of rightSide => prefix grammar
                if (!leftSide.equals(rightSide) && rightSide.startsWith(leftSide)) return true;
            }
        }
        return false;  // not prefix grammar
    }

    public static void main(String[] args) {
        List<String> lhs = List.of("a", "b", "ba", "ab", "aba", "bbbbb", "aaaa");
        List<String> rhs = List.of("aa", "bb", "ab", "aaa", "bab", "a", "a");

        // bab            - 1. transformacny krok
        // abbb           - 2. transformacny krok
        // aaabbbb        - 3. transformacny krok
        // aaaaaaabbbbbb  - 4. transformacny krok
        Gramatika g = new Gramatika(lhs, rhs);
        String slovo = "aba";
        for (int i = 0; i < 4; ++i) {
            slovo = g.deterministickyKrok(slovo);
            System.out.println(slovo);
        }

        lhs = List.of("a", "b", "ba", "ab", "aba", "bbbbb", "aaaa");
        rhs = List.of("1", "2", "3", "4", "5", "6", "7");

        g = new Gramatika(lhs, rhs);
        System.out.println(g.deterministickyKrok("aba"));           // 5
        System.out.println(g.nedeterministickyKrok("aba"));         // [121, 13, 5, 41]

        g = new Gramatika(List.of("abc", "ccc", "cb", "ab"), List.of("->", "*", ":", "_"));
        System.out.println(g.deterministickyKrok("bacbac"));                // null
        System.out.println(g.jePrefixova());                                     // true
        System.out.println(g.nedeterministickyKrok("bacbac"));             //  []

        // false
        g = new Gramatika(List.of("abc", "ccc", "cb", "acb"), List.of("->", "*", ":", "_"));
        System.out.println(g.jePrefixova());

    }
}