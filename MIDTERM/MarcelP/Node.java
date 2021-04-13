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
    public int size() {
        int res=1;
        if(sons!=null) {
            for (Node<T> n : sons)
                if (n != null)
                    res+=n.size();
        }
        return res;
    }

    /**
     * hĺbka stromu, pre list je to 0, inak je to 1+max.hĺbok podstromov
     */
    @Override
    public int depth() {
        int res=0;
        if(sons!=null) {
            for (Node<T> n : sons)
                if (n != null)
                    res=Math.max(res, n.depth());
        }
        return res+1;
    }

    /**
     * množina listov stromu, list je vrchol, ktorý nemá synov
     */
    @Override
    public Set<T> leafs() {
        Set<T> res=new HashSet<T>();
        if(sons==null||sons.size()==0) {
            res.add(value);
        }else{
            for (Node<T> n : sons)
                if (n != null)
                    res.addAll(n.leafs());
        }

        return res;
    }

    /**
     * pre každý vrchol stromu platí, že je väčší ako všetky vrcholy jeho podstromoch (synoch)
     */
    @Override
    public boolean isHeap() {
        boolean res=true;
        if(sons!=null) {
            for (Node<T> n : sons)
                if (n != null) {
                    if(value.compareTo(n.value)<0)
                        return false;
                    res = res & n.isHeap();
                }
        }
        return res;
    }

    /**
     * hodnoty e1 a e2 sa nachádzajú niekde v strome v rovnakej hĺbke od koreňa stromu
     */
    @Override
    public boolean atSameLevel(T e1, T e2) {
        return level(e1)==level(e2);
    }

    public int level(T e) {
        if(value.compareTo(e)==0)
            return 0;
        if(sons!=null) {
            for (Node<T> n : sons)
                if (n != null) {
                    int t = n.level(e);
                    if(t!=-1)
                        return t+1;
                }
        }
        return -1;
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
//        System.out.println(rt.atSameLevel("E","D")); // platí,
//        System.out.println(rt.atSameLevel("G","Q")); // neplatí,
    }
}
