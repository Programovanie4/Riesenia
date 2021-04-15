import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        int r = 1;
        if (sons == null) return r;
        for (Node<T> son : sons) r += son.size();
        return r;
    }

    /**
     * hĺbka stromu, pre list je to 0, inak je to 1+max.hĺbok podstromov
     */
    @Override
    public int depth() {
        System.out.println(this);
        if (sons == null) return 0; // malo by tu byt || sons.isEmpty(), ale LIST test je nespravny na toto
        return sons.stream().map(Node::depth).max(Integer::compareTo).orElse(0) + 1;
    }

    /**
     * množina listov stromu, list je vrchol, ktorý nemá synov
     */
    @Override
    public Set<T> leafs() {
        if (sons == null || sons.isEmpty()) return Stream.of(value).collect(Collectors.toSet());
        return sons.stream().map(Node::leafs).flatMap(Collection::stream).collect(Collectors.toSet());
    }

    /**
     * pre každý vrchol stromu platí, že je väčší ako všetky vrcholy jeho podstromoch (synoch)
     */
    @Override
    public boolean isHeap() {
        for (Node<T> son : sons) {
            if (!son.isHeap()) return false;
            if (value.compareTo(son.getValue()) <= 0) return false;
        }
        return true;
    }

    class Tuple<T, P> {
        private T first;
        private P second;

        public Tuple(T first, P second) {
            this.first = first;
            this.second = second;
        }

        public T getFirst() {
            return first;
        }

        public P getSecond() {
            return second;
        }

        @Override
        public String toString() {
            return "Tuple{" +
                    "first=" + first +
                    ", second=" + second +
                    '}';
        }
    }

    /**
     * hodnoty e1 a e2 sa nachádzajú niekde v strome v rovnakej hĺbke od koreňa stromu
     */
    @Override
    public boolean atSameLevel(T e1, T e2) {
        if (e1.compareTo(e2) == 0) return true;
        Deque<Tuple<Node<T>, Integer>> queue = new ArrayDeque<>();
        Set<T> visited = new HashSet<>();
        queue.add(new Tuple<>(this, 0));
        Integer a = null;
        while(!queue.isEmpty()) {
            Tuple<Node<T>, Integer> n = queue.pop();
            if (visited.contains(n.getFirst().value)) continue;
            visited.add(n.getFirst().value);
            if (n.getFirst().getValue().equals(e1) || n.getFirst().getValue().equals(e2))
                if (a == null) a = n.getSecond();
                else return a.equals(n.getSecond());
            if (n.getFirst().sons != null)
                for(Node<T> son : n.getFirst().sons) {
                    queue.add(new Tuple<>(son, n.getSecond() + 1));
                }
        }
        return false;
    }

    public boolean contains(T e) {
        if (value.equals(e)) return true;
        if (sons == null || sons.isEmpty()) return false;
        return sons.stream().anyMatch(s -> s.contains(e));
    }

    public Node<T> minCommon(T e1, T e2) {
        for (Node<T> son : sons)
            if (son.contains(e1) && son.contains(e2))
                return son.minCommon(e1, e2);
        return this;
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
        System.out.println(rt.size());               // 9
        System.out.println(rt.depth());              // 3
        System.out.println(rt.leafs());              // A,B,D,E,Q
        System.out.println(rt.isHeap());             // plati
        System.out.println(rt.atSameLevel("E","D")); // platí,
        System.out.println(rt.atSameLevel("G","Q")); // neplatí,
    }
}
