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
    //----------------------- programujte nizsie

    /**
     * či vrchol je prameň grafu
     */
    public boolean jePramen(V pramen)
    {
        List<V> odkial = new ArrayList<>();
        g.forEach((k, v) ->
        {
            v.forEach(x -> {if(x.equals(pramen)){odkial.add(k);}});
        });
        return odkial.isEmpty();
    }

    /**
     * vráti všetky pramene grafu
     */
    public Set<V> pramene()
    {
        Set<V> res  = g.keySet().stream().filter(k -> jePramen(k)).collect(Collectors.toSet());
        return res;
    }

    /**
     * vrchol je ústie grafu
     */
    public boolean jeUstie(V ustie)
    {
        return g.get(ustie).isEmpty();
    }

    /**
     * vráti všetky ústia grafu
     */
    public Set<V> ustia()
    {
        Set<V> res= new HashSet<>();
        g.forEach((k, v) ->
        {
            if(jeUstie(k)) res.add(k);
        });
        return res;

    }

    /**
     * rozhodne, či existuje v grafe orientovaná cesta z vrchola from do vrcholu to
     */
    public boolean cesta(V from, V to)
    {
        return dfs(from, to, new HashSet<>());
    }

    public boolean dfs(V vrchol, V ciel, Set<V> navstivene)
    {
        if(vrchol.equals(ciel)) return true;
        for (V sused : g.get(vrchol))
        {
            if(!navstivene.contains(sused))
            {
                navstivene.add(sused);
                if(dfs(sused, ciel, navstivene)) return true;
                navstivene.remove(sused);
            }
        }
        return false;
    }

    /**
     * či je graf cyklický, teda obsahuje orientovaný cyklus
     */
    public boolean cyklicky()
    {
        boolean cycle = false;
        for(V vrch : g.keySet())
        {
            if(dfs_cycle(vrch, new HashSet<>()))
            {
                return true;
            }
        }
        return false;
    }

    public boolean dfs_cycle(V vrchol, Set<V> navstivene)
    {
        for (V sused : g.get(vrchol))
        {
            if(!navstivene.contains(sused))
            {
                navstivene.add(sused);
                if(dfs_cycle(sused, navstivene)) return true;
                navstivene.remove(sused);
            }
            else
            {
                return true;
            }
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
