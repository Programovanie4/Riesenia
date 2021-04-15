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
        for(Set<V> s : g.values()){
            if(s.contains(pramen)) return false;
        }
        return true;
    }

    /**
     * vráti všetky pramene grafu
     */
    public Set<V> pramene() {
        Set<V> rv = new HashSet<>();
        for(V n: g.keySet()) {
            if (jePramen(n)) rv.add(n);
        }
        return rv;
    }

    /**
     * vrchol je ústie grafu
     */
    public boolean jeUstie(V ustie) {
        return g.get(ustie) == null || g.get(ustie).size() == 0;
    }

    /**
     * vráti všetky ústia grafu
     */
    public Set<V> ustia() {
        Set<V> rv = new HashSet<>();
        for(V n: g.keySet()) {
            if (jeUstie(n)) rv.add(n);
        }
        return rv;
    }

    /**
     * rozhodne, či existuje v grafe orientovaná cesta z vrchola from do vrcholu to
     */
    public boolean cesta(V from, V to) {
        List<V> nodes = List.of(from);
        Set<V> visited = new HashSet<>();
        while(nodes.size() > 0){
            List<V> next = new ArrayList<>();
            for(V n: nodes){
                if(visited.contains(n)) continue;
                visited.add(n);
                if(n == to) return true;
                next.addAll(g.get(n));
            }
            nodes = next;
        }
        return false;
    }

    /**
     * či je graf cyklický, teda obsahuje orientovaný cyklus
     */
    public boolean cyklicky()  {
        for(V n: g.keySet()){
            for(V d: g.get(n)){
                if(cesta(d,n)) return true;
            }
        }
        return false; // doprogramovat
    }
    public Set<Set<V>> komponentSuvislosti(){
        System.out.println(g);
        Set<Set<V>> rv = new HashSet<>();

        for(V n: g.keySet()){
            var x = new HashSet<>(Set.of(n));
            x.addAll(g.get(n));
            rv.add(x);
        }

        while(true){
            Set<Set<V>> next = new HashSet<>();

            for(var v1 : rv){
                Set<V> add = new HashSet<>();
                add.addAll(v1);
                for (var v2 : rv){
                    if(v1.equals(v2)) continue;
                    if(inters(v1,v2)) add.addAll(v2);
                }
                next.add(add);
            }
            if(next.equals(rv)) return next;
            rv = next;
        }

    }
    private boolean inters(Set<V> a, Set<V> b){
        for(V n: a)
            if(b.contains(n)) return true;
        return false;
    }

    public static void main(String[] args) {
           {
            //g={A=[J], B=[D], C=[], D=[A, G], E=[B, I], F=[A, B, E], G=[], H=[G,J], I=[E], J=[E]}
               var g = new Graph<Character>(Map.of(
                       'A',Set.of('I','A'),
                       'B',Set.of('D','A'),
                       'C',Set.of(),
                       'D',Set.of('G', 'E', 'F'),
                       'E',Set.of('E'),
                       'F',Set.of('C'),
                       'G',Set.of('A', 'G', 'F'),
                       'H',Set.of('C','I'),
                       'I',Set.of(),
                       'J',Set.of('B','H','D')
               ));
               System.out.println(g.komponentSuvislosti());
//            System.out.println(g.pramene());
//            System.out.println(g.ustia());
//            System.out.println(g.cesta('J','B'));
//            System.out.println(g.cesta('F','D'));
//            System.out.println(g.cesta('J','H'));
//            System.out.println(g.cyklicky());
        }
    }
}
