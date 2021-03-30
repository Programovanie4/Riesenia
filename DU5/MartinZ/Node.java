import java.util.ArrayList;
import java.util.List;

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
        String s;
        if(value == null) s = "null";
        else s = value.toString();
        StringBuilder rv = new StringBuilder(s);
        if(sons == null) return rv.toString();
        rv.append("(");
        for(Node<T> son: sons){
            if(son == null) rv.append("null");
            else rv.append(son.toString());
            rv.append(",");
        }
        rv.replace(rv.length()-1,rv.length(),")");
        return rv.toString();
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
        if(elem == null || value == null) {
            if (elem == value) return true;
        }
        else if(value.equals(elem)) return true;
        if(sons == null){
            if(elem == null || value == null) return false;
            return value.equals(elem);
        }
        for(Node<T> son: sons){
            if(son == null) continue;
            if(son.contains(elem)) return true;

        }
        return false;

    }

    public boolean isLeaf(T elem) {
        if(sons == null) {
            if(value == null || elem == null) return value == elem;
            return value.equals(elem);
        }
        for(Node<T> son: sons){
            if(son == null) continue;
            if(son.isLeaf(elem)) return true;
        }
        return false;
    }
    public List<T> preorder() {
        List<T> rv = new ArrayList<T>();
        rv.add(value);
        if(sons == null) return rv;
        for(Node<T> son: sons) {
            if (son != null) rv.addAll(son.preorder());
        }
        return rv;
    }

    @Override
    public int compareTo(Node<T> o) {
        if(o == null) return 1;
        if(this == o) return 0;
        if((value == null || o.getValue() == null) || value.equals(o.getValue())){
            if(value == null && o.getValue() != null) return -1;
            if(value != null && o.getValue() == null) return 1;
            List<Node<T>> sons2 = o.getSons();
            if(sons == null || sons2 == null){
                if(sons2 == sons) return 0;
                if(sons == null) return -1;
                return 1;
            }
            int len = Math.max(sons.size(), sons2.size());
            Node<T> s1;
            Node<T> s2;
            for(int i = 0; i<len; i++){
                if(i < sons.size()) s1 = sons.get(i);
                else s1 = null;
                if(i < sons2.size()) s2 = sons2.get(i);
                else s2 = null;
                if(s1 == null || s2 == null){
                    if(s1 == s2) return 0;
                    if(s1 == null) return -1;
                    return 1;
                }
                if(s1.equals(s2)) continue;
                return s1.compareTo(s2);
            }
        }
        if(value == null || o.getValue() == null){
            if(value == o.getValue()) return 0;
            if(value == null) return -1;
            return 1;
        }
        return value.compareTo(o.getValue());
    }
    public static void main(String[] args) {
        Node<String> n_dabc = new Node("d",
                List.of(
                        new Node("a", null),
                        new Node("b", null),
                        new Node("c", null))
        );
        Node<String> n_efg = new Node("e",
                List.of(
                        new Node("f", null),
                        new Node("g", null))
        );
        Node<String> n_habcdefg = new Node("h",
                List.of(n_dabc, n_efg));

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
    }
}
