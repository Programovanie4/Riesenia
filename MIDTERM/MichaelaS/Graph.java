import java.util.*;

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
        Set<V> vedie_do = new HashSet<>();
        for (V a: g.keySet()) {
            vedie_do.addAll(g.get(a));
        }
        return !vedie_do.contains(pramen); // doprogramovat
    }

    /**
     * vráti všetky pramene grafu
     */
    public Set<V> pramene() {
        Set<V> pramene = new HashSet<>();
        for (V a: g.keySet()) {
            if (jePramen(a)) pramene.add(a);
        }
        return pramene; // doprogramovat
    }

    /**
     * vrchol je ústie grafu
     */
    public boolean jeUstie(V ustie) {
        return g.get(ustie).size() == 0; // doprogramovat
    }

    /**
     * vráti všetky ústia grafu
     */
    public Set<V> ustia() {
        Set<V> ustia = new HashSet<>();
        for (V a: g.keySet()) {
            if (jeUstie(a)) ustia.add(a);
        }
        return ustia; // doprogramovat
    }

    /**
     * rozhodne, či existuje v grafe orientovaná cesta z vrchola from do vrcholu to
     */
    public boolean cesta(V from, V to) {
        Set<V> visited = new HashSet<>();
        List<V> queue = new ArrayList<>(List.of(from));

        V current;
        while (queue.size() != 0) {
            current = queue.remove(0);
            if (current == to) return true;
            visited.add(current);
            for (V next: g.get(current)) {
                if (!visited.contains(next)) queue.add(next);
            }
        }

        return false; // doprogramovat
    }

    /**
     * či je graf cyklický, teda obsahuje orientovaný cyklus
     */
    public boolean cyklicky()  {
//        for (V v: g.keySet()) {
//            if (jePramen(v)) continue;
//            Set<V> visited = new HashSet<>();
//            List<V> queue = new ArrayList<>(List.of(v));
//            V current;
//
//            while (queue.size() != 0) {
//                current = queue.remove(0);
//                if (jeUstie(current)) continue;
//                if (visited.contains(current)) return true;
//                visited.add(current);
//            }
//        }
        return false; // doprogramovat
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
//            System.out.println(g.pramene());
//            System.out.println(g.ustia());
//            System.out.println(g.cesta('J','B'));
//            System.out.println(g.cesta('F','D'));
//            System.out.println(g.cesta('J','H'));
//            System.out.println(g.cyklicky());
        }
    }
}
