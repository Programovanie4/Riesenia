import java.util.*;
import java.util.stream.Collectors;

interface RTree<T extends Comparable<T>> {
    public int size();               // počet vrcholov stromu
    public int depth();              // hĺbka stromu, pre list je to 0, inak je to 1+max.hĺbok podstromov
    public Set<T> leafs();          // množina listov stromu, list je vrchol, ktorý nemá synov
    public boolean isHeap();        // pre každý vrchol stromu platí, že je väčší ako všetky vrcholy jeho podstromoch (synoch)
    public boolean atSameLevel(T e1, T e2); // hodnoty e1 a e2 sa nachádzajú niekde v strome v rovnakej hĺbke od koreňa stromu
}

public class Node<T extends Comparable<T>> implements RTree<T> {
    private final T value;
    private final List<Node<T>> sons;

    public Node(T value, List<Node<T>> sons) {
        this.value = value;
        this.sons = sons;
    }

    public T getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "new Node<String>(\"" +
                value + "\", " +
                "List.of(" +
                String.join(",", sons.stream().map(x->x.toString()).collect(Collectors.toList())) +
                "))";
    }
    //-------------------------------------------- programujte odtialto nizsie

    /**
     * počet vrcholov stromu
     */
    @Override
    public int size()
    {
        int poc = 0;
        for(int i=0; i<sons.size();i++)
        {
            Node<T> s = sons.get(i);
            if(s != null)
            {
                poc += s.size();
            }
        }
        return poc+1;
    }

    /**
     * hĺbka stromu, pre list je to 0, inak je to 1+max.hĺbok podstromov
     */
    @Override
    public int depth()
    {
        List<Integer> des = new ArrayList<>();
        for(int i=0; i<sons.size();i++)
        {
            Node<T> s = sons.get(i);
            if(s != null)
            {
                des.add(s.depth());
            }
        }
        if(des.isEmpty()) return 1;
        return Collections.max(des)+1;
    }

    /**
     * množina listov stromu, list je vrchol, ktorý nemá synov
     */
    @Override
    public Set<T> leafs()
    {
        Set<T> s = new HashSet<>();
        if(sons.isEmpty())
        {
            s.add(this.value);
            return s;
        }
        for(int i=0; i<sons.size();i++)
        {
            Node<T> son = sons.get(i);
            if(son != null)
            {
                s.addAll(son.leafs());
            }
        }
        return s;
    }


    /**
     * pre každý vrchol stromu platí, že je väčší ako všetky vrcholy jeho podstromoch (synoch)
     */
    @Override
    public boolean isHeap()
    {
        for(int i=0; i<sons.size();i++)
        {
            Node<T> son = sons.get(i);
            if(son != null)
            {
                if(!(son.value.compareTo(value) < 0)) return false;
                if(son.isHeap() == false) return false;
            }
        }
        return true;
    }


    private int level(T e1)
    {
        return dfs(this, 0, e1);
    }

    public int dfs(Node<T> vrchol, int lev, T ciel)
    {
        if(vrchol.value.equals(ciel)) return lev;
        if(vrchol.sons.isEmpty()) return -1;
        for(int i=0; i<vrchol.sons.size();i++)
        {
            Node<T> s = vrchol.sons.get(i);
            if(s != null)
            {
                int l = dfs( s, lev+1, ciel);
                if(l != -1) return l;
            }
        }
        return -1;
    }

    /**
     * hodnoty e1 a e2 sa nachádzajú niekde v strome v rovnakej hĺbke od koreňa stromu
     */
    @Override
    public boolean atSameLevel(T e1, T e2)
    {
        return level(e1)==level(e2);  // doprogramuje
    }

    public static void main(String[] args) {
        Node<String> rt = new Node<>("X", List.of(
                new Node<>("P", List.of(
                        new Node<>("E",List.of()),
                        new Node<>("G",List.of(
                                new Node<>("B",List.of())))
                )),
                new Node<>("Q", List.of()),
                new Node<>("R", List.of(
                        new Node<>("A", List.of()),
                        new Node<>("D", List.of())
                ))
        )
        );
//        System.out.println(rt.size());               // 9
//        System.out.println(rt.depth());              // 3
//        System.out.println(rt.leafs());              // A,B,D,E,Q
//        System.out.println(rt.isHeap());             // plati
        System.out.println(rt.atSameLevel("E","D")); // platí,
//        System.out.println(rt.atSameLevel("G","Q")); // neplatí,
    }
}
