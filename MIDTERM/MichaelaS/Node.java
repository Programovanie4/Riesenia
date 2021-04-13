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

    /**
     * počet vrcholov stromu
     */
    @Override
    public int size() {
        List<Node> queue = new ArrayList<>(List.of(this));
        int size = 0;
        Node current_node;
        while (queue.size() != 0) {
            current_node = queue.remove(0);
            size += 1;
            if (current_node.sons == null) continue;
            queue.addAll(current_node.sons);
        }
        return size;  // doprogramuje
    }

    /**
     * hĺbka stromu, pre list je to 0, inak je to 1+max.hĺbok podstromov
     */
    @Override
    public int depth() {
        if (sons == null || sons.size() == 0) return 1;
        List<Node> queue = new ArrayList<>(sons);
        Node current_son;
        int depth = 0;
        while (queue.size() != 0) {
            current_son = queue.remove(0);
            depth = Math.max(depth, current_son.depth());
        }
        return depth + 1;              // doprogramuje
    }

    /**
     * množina listov stromu, list je vrchol, ktorý nemá synov
     */
    @Override
    public Set<T> leafs() {
        List<Node> queue = new ArrayList<>(List.of(this));
        Set<T> leafs = new HashSet<>();
        Node current_node;
        while (queue.size() != 0) {
            current_node = queue.remove(0);
            if (current_node.sons == null || current_node.sons.size() == 0) {
                leafs.add((T) current_node.value);
            };
            queue.addAll(current_node.sons);
        }
        return leafs;  // doprogramuje
    }

    /**
     * pre každý vrchol stromu platí, že je väčší ako všetky vrcholy jeho podstromoch (synoch)
     */
    @Override
    public boolean isHeap() {
        List<Node> queue = new ArrayList<>(List.of(this));
        Node current_node;
        while (queue.size() != 0) {
            current_node = queue.remove(0);
            if (current_node.sons == null) continue;
            for (Object son: current_node.sons) {
                if (current_node.value.compareTo(((Node) son).value) < 0) return false;
            }
            queue.addAll(current_node.sons);
        }
        return true;  // doprogramuje
    }

    /**
     * hodnoty e1 a e2 sa nachádzajú niekde v strome v rovnakej hĺbke od koreňa stromu
     */
    @Override
    public boolean atSameLevel(T e1, T e2) {
        List<Integer> vysky = new ArrayList<>();
        List<Node> queue = new ArrayList<>(List.of(this));
        List<Integer> queue_vysky = new ArrayList<>(List.of(0));
        Node current_node;
        Integer current_vyska;
        while (queue.size() != 0) {
            current_node = queue.remove(0);
            current_vyska = queue_vysky.remove(0);
            if (current_node.value.equals(e1) || current_node.value.equals(e2)) vysky.add(current_vyska);
            if (current_node.sons == null) continue;
            for (Object son: current_node.sons) {
                queue.add((Node) son);
                queue_vysky.add(current_vyska+1);
            }
        }
        System.out.println(e1 + " " + e2 + " " + vysky);
        return (vysky.size() == 1 && e1.equals(e2)) || (vysky.size() == 2 && vysky.get(0) - vysky.get(1) == 0);
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
