import java.util.*;
import java.util.stream.Collectors;

interface RTree<T extends Comparable<T>> {
    public int size();               // počet vrcholov stromu
    public int depth();              // hĺbka stromu, pre list je to 0, inak je to 1+max.hĺbok podstromov
    public Set<T> leafs();          // množina listov stromu, list je vrchol, ktorý nemá synov
    public boolean isHeap();        // pre každý vrchol stromu platí, že je väčší ako všetky vrcholy jeho podstromoch (synoch)
    public boolean atSameLevel(T e1, T e2); // hodnoty e1 a e2 sa nachádzajú niekde v strome v rovnakej hĺbke od koreňa stromu
//    public Node<T> minCommon(T e1, T e2);  // je najmenší strom, ktorý obsahuje e1 aj e2. Znamená to, že žiaden jeho podstrom neobsahuje aj e1 aj e2.
//                                           // Ak taký podstrom neexistujte, výsledkom je null.
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

    @Override
    public int size() {
        return 1+((sons == null)?0:
                   sons.stream().map(Node::size).reduce(0, Integer::sum));
    }

    @Override
    public int depth() {
        return (sons == null)?0:
                (1+sons.stream().map(Node::depth).reduce(0, Integer::max));
    }

    @Override
    public Set<T> leafs() {
        if (!isOk()) System.err.println(this);
        //return null;
        if (sons == null || sons.size() == 0) {
            return (value != null)?Set.of(value):Set.of();
        } else {
            var result = sons.stream().flatMap(s -> s.leafs().stream()).collect(Collectors.toSet());
//            if (value != null)
//                result.add(value);
            return result;
        }
    }

    private boolean lessOrEqual(T v) {
        return (value == null || value.compareTo(v) <= 0) &&
               (sons == null || sons.stream().allMatch(s -> s.lessOrEqual(value)));
    }

    @Override
    public boolean isHeap() {
        if (!isOk()) System.err.println(this);
        //return false;
        return lessOrEqual(value);
    }

    @Override
    public boolean atSameLevel(T e1, T e2) {
        if (!isOk()) System.err.println(this);
        //return false;
        var hm = new HashMap<T,Set<Integer>>();
        levels(0, hm);
        return (hm.containsKey(e1) && hm.containsKey(e2) && hm.get(e1).stream().anyMatch(l -> hm.get(e2).contains(l)));
    }

    private void levels(int level, Map<T, Set<Integer>> hm) {
        if (value != null) {
            if (!hm.containsKey(value)) hm.put(value, new HashSet<>());
            hm.get(value).add(level);
        }
        if (sons != null)
            sons.forEach(s -> s.levels(level+1, hm));
    }
    class Pair {
        Set<T> postOrderSet;
        Node<T> minCommon;

        public Pair(Set<T> postOrderSet, Node<T> minCommon) {
            this.postOrderSet = postOrderSet;
            this.minCommon = minCommon;
        }
    }
//    @Override
    public Node<T> minCommon(T e1, T e2) {
        if (!isOk()) System.err.println(this);
        //return null;
        return postOrder(e1, e2).minCommon;
    }

    public Pair postOrder(T e1, T e2) {
        Set<T> po = new HashSet<>();
        Node<T> minCommon = null;
        for(Node<T> s : sons) {
            var pair = s.postOrder(e1, e2);
            po.addAll(pair.postOrderSet);
            if (pair.minCommon != null)
                minCommon = pair.minCommon;
        };
        if (value != null)
            po.add(value);
        if (po.contains(e1) && po.contains(e2) && minCommon == null) {
            minCommon = this;
        }
        return new Pair(po, minCommon);
    }
    static Random rnd = new Random();

    public static Node<String> randomRhodos(int level) {
        if (level <= 0)
            return null;
        else {
            String str = randomString();
            int nsons = rnd.nextInt(2*level);
            List<Node<String>> al = new ArrayList<>();
            while (nsons-- > 0) {
                var x = randomRhodos(level-1);
                if (x != null)
                    al.add(x);
            }
            return new Node<String>(str, al);

        }
    }
    public boolean isOk() {
        var x = preorder();
        return new HashSet<>(x).size() == x.size();
    }
    private List<T> preorder() {
        //return null;
        if (sons == null || sons.size() == 0) {
            return (value != null)?List.of(value):List.of();
        } else {
            var result = sons.stream().flatMap(s -> s.preorder().stream()).collect(Collectors.toList());
            if (value != null)
                result.add(value);
            return result;
        }
    }


    private static String randomString() {
        Random rnd = new Random();
        return Integer.toHexString(rnd.nextInt());
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
//        System.out.println(rt.minCommon("E","G").getValue()); // je P,
//        System.out.println(rt.minCommon("A","B").getValue());// je X,
//        System.out.println(rt.minCommon("B","E").getValue()); // je P,
//        System.out.println(rt.minCommon("Q","Q").getValue()); // je Q.



//        String[] tests = {"leafs", "isHeap", "atSameLevel", "minCommon"};
        String[] tests = {"size","depth", "leafs", "isHeap", "atSameLevel"};

        ///////////////////////////////////
        int test = 0;
        Random rnd = new Random();
        for (int tt = 0; tt < tests.length; tt++) {
            System.out.println("@Test");
            System.out.println("public void test_" + tests[tt] + "() {");
            for (int loop = 0; loop < 15; loop++) {
                var rr = randomRhodos(1+loop/5);
                System.out.println("{  ");
                System.out.println(" Node<String> rr = " +  rr + ";");
                if (tests[tt].equals("size")) {
                    System.out.println("\t assertEquals(\"size/Test_" + tests[tt] + "\", " +
                            (rr.size()) +
                            ", rr.size());");
                }
                if (tests[tt].equals("depth")) {
                    System.out.println("\t assertEquals(\"depth/Test_" + tests[tt] + "\", " +
                            (rr.depth()) +
                            ", rr.depth());");
                }
                if (tests[tt].equals("leafs")) {
                    System.out.println("\t assertEquals(\"leafs/Test_" + tests[tt] + "\", " +
                            setToString(rr.leafs()) +
                            ", rr.leafs());");
                }
                if (tests[tt].equals("isHeap")) {
                    System.out.println("\t assertEquals(\"isHeap/Test_" + tests[tt] + "\", " +
                            rr.isHeap() +
                            ", rr.isHeap());");
                }
                if (tests[tt].equals("atSameLevel")) {
                    for(String e1 : rr.leafs())
                        for(String e2 : rr.leafs()) {
                            System.out.println("\t assertEquals(\"atSameLevel/Test_" + tests[tt] + "\", " +
                                    rr.atSameLevel(e1, e2) +
                                    ", rr.atSameLevel(\"" + e1 + "\", \"" + e2 + "\"));");
                        }
                }
                if (tests[tt].equals("minCommon")) {
                    for(String e1 : rr.leafs())
                        for(String e2 : rr.leafs()) {
                            System.out.println("\t assertEquals(\"minCommon/Test_" + tests[tt] + "\", " +
                                    "\"" + rr.minCommon(e1, e2).getValue() + "\"" +
                                    ", rr.minCommon(\"" + e1 + "\", \"" + e2 + "\").getValue());");
                        }
                }
                test++;
                System.out.println("}");
            }
            switch (tests[tt]) {
                case "minCommon": case "atSameLevel":
                    System.out.println("scoring.updateScore(\"lang:common_list_test_scoring_name\",   33.0D);");
                    break;
                default:
                    System.out.println("scoring.updateScore(\"lang:common_list_test_scoring_name\",   17.0D);");
                    break;
            }
            System.out.println("}");
        }
    }
    public static <V> String setToString(Set<V> set) {
        String s = "Set.of(";
        char ciarka = ' ';
        for (V n : set) {
            s += ciarka + "\"" + n + "\""; ciarka = ',';
        }
        s += ")";
        return s;
    }
}
