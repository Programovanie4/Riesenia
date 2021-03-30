public class Node extends Tree {
    Tree left, right;

    /**
     * konstruktor
     */
    public Node(Tree left, Tree right) {
        this.left = left;
        this.right = right;
    }

    /**
     *  je maximálna hodnota stromu, null ak je strom prazdny
     */
    @Override
    Integer max() {
        if (left != null && right != null)
            return Math.max(left.max(), right.max());
        else if (left == null && right == null)
            return null;
        else if (left == null)
            return right.max();
        else
            return left.max();
    }
    @Override
    Integer min() {
        if (left != null && right != null)
            return Math.min(left.min(), right.min());
        else if (left == null && right == null)
            return null;
        else if (left == null)
            return right.min();
        else
            return left.min();
    }

    /**
     * hlbka stromu
     */
    @Override
    int depth() {
        int l = (left != null) ? left.depth() : 0;
        int r = (right != null) ? right.depth() : 0;
        return 1 + Math.max(l, r);
    }

    /**
     * platí, ak všetky hodnoty (v listoch stromu) sú rovnaké
     */
    @Override
    boolean isUnique() {
        if (left == null && right == null)
            return true;
        if (left == null) return right.isUnique();
        if (right == null) return left.isUnique();
        return left.max().equals(right.max()) && left.isUnique() && right.isUnique();
    }

    /**
     * prvky v ľavom podstrome sú menšie alebo rovné ako prvky v pravom podstrome, a to pre každý vnútorný vrchol
     */
    @Override
    boolean isOrdered() {
        if (left == null && right == null)
            return true;
        if (left == null) return right.isOrdered();
        if (right == null) return left.isOrdered();
        return left.max() <= right.min() && left.isOrdered() && right.isOrdered();
    }

    @Override
    public String toString() {
        return "N( " + left + ", " + right +" )";
    }
}
