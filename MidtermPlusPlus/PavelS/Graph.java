import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Graph<V extends Comparable<V>> {
    Map<V, Set<V>> g;
    public Graph(Map<V, Set<V>> g) {
        this.g = g;
    }

    @Override
    public String toString() {
        return  "g=" + g;
    }
    //----------------------- programujte nizsie

    /**
     * či vrchol je prameň grafu
     */
    public boolean jePramen(V pramen) {
        for (V key : g.keySet()) {
            if (g.get(key).contains(pramen)) return false;
        }
        return true;
    }

    /**
     * vráti všetky pramene grafu
     */
    public Set<V> pramene() {
        Set<V> result = new HashSet<>();
        for (V key : g.keySet()) {
            if (jePramen(key)) result.add(key);
        }
        return result;
    }

    /**
     * vrchol je ústie grafu
     */
    public boolean jeUstie(V ustie) {
        return g.get(ustie).isEmpty();
    }

    /**
     * vráti všetky ústia grafu
     */
    public Set<V> ustia() {
        Set<V> result = new HashSet<>();
        for (V key : g.keySet()) {
            if (jeUstie(key)) result.add(key);
        }
        return result;
    }

    /**
     * rozhodne, či existuje v grafe orientovaná cesta z vrchola from do vrcholu to
     */
    public boolean cesta(V from, V to) {
        if (from.equals(to)) return true;
        if (jeUstie(from) || jePramen(to)) return false;
        Set<V> visited = new HashSet<>();
        Deque<V> queue = new ArrayDeque<>();
        queue.add(from);
        while(!queue.isEmpty()){
            V n = queue.pop();
            if (visited.contains(n)) continue;
            visited.add(n);
            for (V v : g.get(n)) {
                if (v.equals(to)) return true;
                queue.add(v);
            }
        }
        return false;
    }

    /**
     * či je graf cyklický, teda obsahuje orientovaný cyklus
     */
    public boolean cyklicky()  {
        for (V k : g.keySet()) {
            for (V l : g.keySet()) {
                if (!k.equals(l))
                    if (cesta(k, l) && cesta(l, k)) return true;
            }
        }
        return false;
    }

    public boolean cestaDouble(V from, V to) {
        if (from.equals(to)) return true;
        Set<V> visited = new HashSet<>();
        Deque<V> queue = new ArrayDeque<>();
        queue.add(from);
        while(!queue.isEmpty()){
            V n = queue.pop();
            if (visited.contains(n)) continue;
            visited.add(n);
            for (V v : g.get(n)) {
                if (v.equals(to)) return true;
                queue.add(v);
            }
            for (V v : g.keySet().stream().filter(k -> g.get(k).contains(n)).collect(Collectors.toSet())) {
                if (v.equals(to)) return true;
                queue.add(v);
            }
        }
        return false;
    }

    public Set<Set<V>> komponentSuvislosti() {
        Set<Set<V>> result = new HashSet<>();
        for (V v1 : g.keySet()) {
            if (result.stream().anyMatch(r -> r.contains(v1))) continue;
            Set<V> r = Stream.of(v1).collect(Collectors.toSet());
            for (V v2 : g.keySet()) {
                if (!v1.equals(v2) && (cestaDouble(v1, v2) || cestaDouble(v2, v1))) r.add(v2);
            }
            result.add(r);
        }
        return result;
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
        }
    }
}
