abstract class Tree {
    abstract int depth();        // hĺbka stromu, hľbka listu je 0, hľbka stromu je 1+max(left, right)
    abstract Integer max();
    abstract Integer min();      // je maximálna hodnota stromu, null ak je strom prázdny
    abstract boolean isUnique(); // platí, ak všetky hodnoty (v listoch stromu) sú rovnaké
    abstract boolean isOrdered();  // prvky v ľavom podstrome sú menšie alebo rovné ako prvky v pravom podstrome, a to pre každý vnútorný vrchol

    public static void main(String[] args) {
        Tree s = new Node(
                new Node(new Leaf(2), new Leaf(5)),
                new Node(new Leaf(7), new Leaf(11)));
        Tree t = new Node(s, s);
        Tree u = new Node(new Leaf(17), new Leaf(17));
        Tree v = new Node(new Leaf(17), u);
        Tree w = new Node(new Leaf(0), new Node(new Leaf(1), new Node(new Leaf(2), new Leaf(3))));
        Tree x = new Node(new Leaf(12), new Node(new Leaf(12), new Node(new Leaf(12), new Leaf(12))));
        Tree y = new Node(new Leaf(12), new Node(null, new Node(null, new Leaf(12))));
        Tree z = new Node(null, null);

        Tree[] benchmarks = { s, t, u, v, w, x, y, z};
        for (Tree tree : benchmarks)
            System.out.println(
                      "   depth()=" + tree.depth()
                    + "\t max()=" + tree.max()
                    + "\t isOrdered()=" + tree.isOrdered()
                    + "\t isUnique()=" + tree.isUnique()
                    + "\t" + tree
            );
    }
}
