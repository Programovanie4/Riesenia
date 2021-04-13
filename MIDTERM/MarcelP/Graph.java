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
        return pramene().contains(pramen);
    }

    /**
     * vráti všetky pramene grafu
     */
    public Set<V> pramene() {
        Set<V> vrcholy = new TreeSet<>(g.keySet());
        for(Set<V> susedia:g.values()){
            vrcholy.removeAll(susedia);
        }
        return vrcholy;
    }

    /**
     * vrchol je ústie grafu
     */
    public boolean jeUstie(V ustie) {
        return ustia().contains(ustie);
    }

    /**
     * vráti všetky ústia grafu
     */
    public Set<V> ustia() {
        Set<V> vrcholy = new TreeSet<>();
        for(V vrchol:g.keySet()){
            if(g.get(vrchol).size()==0)
                vrcholy.add(vrchol);
        }
        return vrcholy;
    }

    /**
     * rozhodne, či existuje v grafe orientovaná cesta z vrchola from do vrcholu to
     */
    public boolean cesta(V from, V to) {
        Set<V> visited = new HashSet<>(Set.of(from));
        dfs(from, visited);
        return visited.contains(to);
    }

    public void dfs(V v, Set<V> visited){
        for(V w:g.get(v)){
            if(!visited.contains(w)){
                visited.add(w);
                dfs(w, visited);
            }
        }
    }

    /**
     * či je graf cyklický, teda obsahuje orientovaný cyklus
     */
    public boolean cyklicky()  {
        for(V v:g.keySet()){
            if(other_dfs(v, new HashSet<>(Set.of(v))))
                return true;
        }
        return false;
    }

    public boolean other_dfs(V v, Set<V> visited){
        for(V w:g.get(v)){
            if(!visited.contains(w)){
                visited.add(w);
                if(other_dfs(w, new HashSet<>(visited)))
                    return true;
                visited.remove(w);
            }
            else
                return true;
        }
        return false;
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
