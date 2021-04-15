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
        int rv = 1;
        if(sons != null){
            for (Node n: sons) rv += n.size();
        }
        return rv;
    }

    /**
     * hĺbka stromu, pre list je to 0, inak je to 1+max.hĺbok podstromov
     */
    @Override
    public int depth() {
        if(sons == null || sons.size() == 0){
            return 1;
        }
        int rv = 0;
        for(Node n: sons){
            int x = n.depth();
            if(x > rv) rv = x;
        }
        return rv+1;
    }

    /**
     * množina listov stromu, list je vrchol, ktorý nemá synov
     */
    @Override
    public Set<T> leafs() {
        if(sons == null || sons.size() == 0) return Set.of(value);
        Set<T> rv = new HashSet<>();
        for(Node n:sons){
            rv.addAll(n.leafs());
        }
        return rv;
    }


    /**
     * pre každý vrchol stromu platí, že je väčší ako všetky vrcholy jeho podstromoch (synoch)
     */
    @Override
    public boolean isHeap() {
        if(sons == null || sons.size() == 0) return true;
        for(Node<T> n: sons){
            if(value.compareTo(n.value) < 1) return false;
            if(!n.isHeap()) return false;
        }
        return true;
    }

    /**
     * hodnoty e1 a e2 sa nachádzajú niekde v strome v rovnakej hĺbke od koreňa stromu
     */
    @Override
    public boolean atSameLevel(T e1, T e2) {
        List<Node<T>> uroven = new ArrayList<>(sons);
        while(uroven.size() > 0){
            Set<T> val = uroven.stream().map(t -> t.value).collect(Collectors.toSet());
            List<Node<T>> next = new ArrayList<>();
            if(val.contains(e1) && val.contains(e2)) return true;
            if(val.contains(e1) || val.contains(e2)) return false;
            for(var n: sons){
                if(n.sons == null) continue;
                next.addAll(n.sons);
            }
            uroven = next;
        }
        return true;
    }
    //na pocet prechodov to nie je zrovna najlepsie, ale nic lepsie mi nenapadlo
    public Node<T> minCommon(T e1, T e2){
        if(sons == null) return null;
        if(e1 == e2 && e1 == value) return this;
        Node<T> l = e1 != value ? null : this;
        Node<T> r = e2 != value ? null : this;
        for(var v: sons){
            if(e1 != value && v.contains(e1)) l = v;
            if(e1 != value && v.contains(e2)) r = v;
        }
        if(l == null || r == null) return null;
        if(l == r) return l.minCommon(e1,e2);
        return this;
    }
    private boolean contains(T e){
        if(value == e) return true;
        if(sons == null) return false;
        for(var v: sons) if(v.value == e) return true;
        boolean rv = false;
        for(var v: sons) rv = rv || v.contains(e);
        return rv;
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
        System.out.println(rt.minCommon("E","G").getValue());// je P,
        System.out.println(rt.minCommon("A","B").getValue());// je X,
        System.out.println(rt.minCommon("B","E").getValue());// je P,
        System.out.println(rt.minCommon("Q","Q").getValue());// je Q.
    }
}
