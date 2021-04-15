import java.util.*;
import java.util.stream.Collectors;

public class Graph<V extends Comparable<V>> {
    Map<V, Set<V>> g;
    public Graph(Map<V, Set<V>> g) {
        this.g = g;
    }

    @Override
    public String toString() {
        return  "g=" + g;
    }
    public static <V> String setToString(Set<V> set) {
        String s = "Set.of(";
        char ciarka = ' ';
        for (V n : set) {
            s += ciarka + "'" + n + "'"; ciarka = ',';
        }
        s += ")";
        return s;
    }
    public String toCode() {
        String res = "var g = new Graph<Character>(Map.of(";
        char bciarka = ' ';
        for (V v : g.keySet()) {
            res += bciarka + "'" + v + "'," + setToString(g.get(v)) ; bciarka = ',';
        }
        return res + "));";
    }

    public boolean jePramen(V pramen) {
        for (V v : g.keySet())
            if (g.get(v) != null && g.get(v).contains(pramen))
                return  false;
        return true;
    }
    public Set<V> pramene() {
        return g.keySet().stream().filter(this::jePramen).collect(Collectors.toSet());
    }
    public boolean jeUstie(V ustie) {
        return g.get(ustie).size() == 0;
    }
    public Set<V> ustia() {
        return g.keySet().stream().filter(this::jeUstie).collect(Collectors.toSet());
    }
    public boolean cesta(V from, V to) {
        return cesta(from, to, new HashSet<>());
    }

    private boolean cesta(V x, V ciel, Set<V> visited) {
        if (x.equals(ciel)) return true;
        else {
            if (g.get(x) != null) {
                visited.add(x);
                for(V n : g.get(x)) {
                    if (visited.contains(n)) continue;
                    if (cesta(n, ciel, visited)) return true;
                }
                visited.remove(x);
            }
            return false;
        }
    }
    private boolean cesta1(V x, V ciel, Set<V> visited) {
        if (g.get(x) != null) {
            visited.add(x);
            boolean or = false;
            for(V n : g.get(x)) {
                if (visited.contains(n)) {
                    //System.out.println("cyklus" + visited);
                    return true;
                }
                or |= cesta1(n, ciel, visited);
            }
            visited.remove(x);
            return or;
        } return false;
    }
    public boolean cyklus(V from) {
        return cesta1(from, from, new HashSet<>());
    }

    public boolean cyklicky() {
        return g.keySet().stream().anyMatch(v -> cyklus(v));
    }

    public Set<Set<V>> komponentSuvislosti() {
        Set<Set<V>> s  = g.keySet().stream().map(v -> Set.of(v)).collect(Collectors.toSet());
        for (V v : g.keySet()) {
            if (g.get(v) != null) {
                for (V n : g.get(v)) {
                    // v -> n
                    var vs = s.stream().filter(x -> x.contains(v)).findFirst().get();
                    var ns = s.stream().filter(x -> x.contains(n)).findFirst().get();
                    s.remove(vs);
                    s.remove(ns);
                    var x = new HashSet<V>(vs);
                    x.addAll(ns);
                    s.add(x);
                }
            }
        }
        return s;
    }

    public static Graph<Character> randomGraph(int n) {
        Random rnd = new Random();
        Map<Character, Set<Character>> g = new HashMap<>();
        char v = 'A';
        for (int i = 0; i<n; i++, v++) {
            int nedges = rnd.nextInt(n/2);
            Set<Character> edges = new HashSet<>();
            while (nedges-- > 0) {
                int vto = rnd.nextInt(n);
                if (i == vto) continue; //negeneruj usi
                edges.add((char)((int)'A'+vto));
            }
            g.put((char)((int)'A'+i), edges);
        }
        return new Graph<>(g);
    }

    public static void main(String[] args) {
        {
            //g={A=[J], B=[D], C=[], D=[A, G], E=[B, I], F=[A, B, E], G=[], H=[G,J], I=[E], J=[E]}
            var g = new Graph<Character>(Map.of(
                    'A',Set.of( 'J'),
                    'B',Set.of( 'D'),
                    'C',Set.of(),
                    'D',Set.of( 'A','G'),
                    'E',Set.of( 'B','I'),
                    'F',Set.of( 'A','B','E'),
                    'G',Set.of(),
                    'H',Set.of( 'G','J'),
                    'I',Set.of( 'E'),
                    'J',Set.of( 'E')
            ));
            System.out.println(g.pramene());
            System.out.println(g.ustia());
            System.out.println(g.cesta('J','B'));
            System.out.println(g.cesta('F','D'));
            System.out.println(g.cesta('J','H'));
            System.out.println(g.cyklicky());
            System.out.println(g.komponentSuvislosti());

        }

        String[] tests = {"pramene","ustia", "cesta", "cyklicky",/* "komponentSuvislosti"*/};


        int test = 0;
        for (int tt = 0; tt < tests.length; tt++) {
            System.out.println("@Test");
            System.out.println("public void test_" + tests[tt] + "() {");
            for (int loop = 0; loop < 100; loop++) {
                var g = randomGraph(10);
                System.out.println("{//" + g);
                System.out.println("\t" + g.toCode());
                if (tests[tt].equals("cyklicky")) {
                    System.out.println("\t assertEquals(\"cyklicky/Test_" + tests[tt] + "\", " +
                            g.cyklicky() + ", g.cyklicky());");
                }
                if (tests[tt].equals("pramene")) {
                    System.out.println("\t assertEquals(\"pramene/Test_" + tests[tt] + "\", " +
                            setToString(g.pramene()) + ", g.pramene());");
                }
                if (tests[tt].equals("ustia")) {
                    System.out.println("\t assertEquals(\"ustia/Test_" + tests[tt] + "\", " +
                            setToString(g.ustia()) + ", g.ustia());");
                }
                if (tests[tt].equals("cesta")) {
                    for (Character from : g.pramene())
                        for (Character to : g.ustia())
                            System.out.println("\t assertEquals(\"cesta/Test_" + tests[tt]  + "('" + from + "','" + to + "')\", "
                                    + g.cesta(from, to) + ", g.cesta('" + from + "','" + to + "'));");
                }
                if (tests[tt].equals("komponentSuvislosti")) {
                    System.out.println("\t assertEquals(\"komponentSuvislosti/Test_" + tests[tt] + "\", " +
                            g.komponentSuvislosti().size() + ", g.komponentSuvislosti().size());");
                }
                System.out.println("}");
                test++;
            }
            switch (tests[tt]) {
                case "komponentSuvislosti": case "cesta": case "cyklicky":
                    System.out.println("scoring.updateScore(\"lang:common_list_test_scoring_name\",   33.0D);");
                    break;
                default:
                    System.out.println("scoring.updateScore(\"lang:common_list_test_scoring_name\",   17.0D);");
            }
            System.out.println("}");
        }
    }
}
