import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

class Node<T extends Comparable<T>> implements Comparable<Node<T>>, RTree<T>  {
    private T             value;
    private List<Node<T>> sons;

    // priklad rekurzie cez strom...
    public int size() {
        int count = 1;
        if (sons != null) {
            for (Node<T> son : sons)
                if (son != null)
                    count += son.size();
        }
        return count;
    }
    @Override
    public String toString() {
        StringBuilder rv = new StringBuilder(" " + ((value == null) ? "null" : value) );
        rv.append("(");
        if (sons != null) {
            for (Node<T> n : sons) {
                if (n != null)
                    rv.append(n.toString()).append(",");
            }
        }
        return rv.append(")").toString();
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public List<Node<T>> getSons() {
        return sons;
    }

    public void setSons(List<Node<T>> sons) {
        this.sons = sons;
    }

    public Node(T value, List<Node<T>> sons) {
        this.value = value;
        this.sons = sons;
    }

    public boolean contains(T elem) {
        if (Objects.equals(value, elem)) return true;
        boolean isIn = false;
        if (sons != null) {
            for (Node<T> n : sons) {
                if (n != null)
                    isIn |= n.contains(elem);
            }
        }
        return isIn;
    }

    public boolean isLeaf(T elem) {
        if (sons == null && Objects.equals(value, elem)) return true;
        boolean existsLeaf = false;
        if (sons != null) {
            for (Node<T> n : sons) {
                if (n != null)
                    existsLeaf |= n.isLeaf(elem);
            }
        }
        return existsLeaf;
    }


    public List<T> preorder() {
        List<T> l = new ArrayList<>();
        l.add(value);
        if (sons != null) {
            for (Node<T> n : sons) {
                if (n != null) {
                    l.addAll(n.preorder());
                }
            }
        }
        return l;
    }


    @Override
    public int compareTo(Node<T> o) {
        if (value == null && o.getValue() == null) return 0;
        if (value == null) return -1;
        if (o.getValue() == null) return 1;
        if (value.compareTo(o.getValue()) != 0) return value.compareTo(o.getValue());

        if (sons == null && o.getSons() == null) return 0;
        if (sons == null) return -1;
        if (o.getSons() == null) return 1;


        int size = Math.max(sons.size(), o.getSons().size());
        int i = 0, difference = 0;
        while (i != size) {
            Node<T> e1, e2;
            if (i >= sons.size()) {
                e1 = null;
                difference--;
            }
            else e1 = sons.get(i);

            if (i >= o.getSons().size()) {
                e2 = null;
                difference--;
            }
            else e2 = o.getSons().get(i);

            if (e1 != null && e2 != null)
                difference += e1.compareTo(e2);
//            if (e1 != null && e2 != null) {
//                if (e1.compareTo(e2) != 0) return e1.compareTo(e2);
//                Node<T> n1 = sons.get(i), n2 = o.getSons().get(i);
//                return n1.compareTo(n2);
//            }
            i++;
        }
        return difference;
    }


    public static void main(String[] args) {
        Node<String> n_dabc = new Node<>("d",
                List.of(
                        new Node<>("a", null),
                        new Node<>("b", null),
                        new Node<>("c", null))
        );
        Node<String> n_efg = new Node<>("e",
                List.of(
                        new Node<>("f", null),
                        new Node<>("g", null))
        );
        Node<String> n_habcdefg = new Node<>("h",
                List.of(
                        new Node<>("d",
                              List.of(
                                        new Node<>("a", null),
                                        new Node<>("b", null),
                                        new Node<>("c", null))
                        ),
                        new Node<>("e",
                                List.of(
                                        new Node<>("f", null),
                                        new Node<>("g", null))
                        )));

        for(char ch : "abcdefghijkl".toCharArray()) {
            System.out.println(
                    "contains(" + ch + ")=" + n_habcdefg.contains(""+ch) +
                    "\t isList(" + ch + ")=" + n_habcdefg.isLeaf(""+ch)

            );
        }
        System.out.println(n_habcdefg);
        System.out.println(n_dabc);
        System.out.println(n_efg);
        System.out.println(n_habcdefg.preorder());
        System.out.println(n_habcdefg.compareTo(n_habcdefg));
        System.out.println(n_habcdefg.compareTo(n_dabc));
        System.out.println(n_habcdefg.compareTo(n_efg));
        System.err.println("error");
    }
}
