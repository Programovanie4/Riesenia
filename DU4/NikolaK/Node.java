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
        if(left == null && right == null){
            return null;
        }
        if(left == null){
            return right.max();
        }
        if(right == null){
            return left.max();
        }
        return Integer.max(left.max(), right.max());
    }

    @Override
    Integer min() {
        if(left == null && right == null){
            return null;
        }
        if(left == null){
            return right.min();
        }
        if(right == null){
            return left.min();
        }
        return Integer.min(left.min(), right.min());
    }

    /**
     * hlbka stromu
     */
    @Override
    int depth() {
        if(left == null && right == null){
            return 1;
        }
        if(left == null){
            return right.depth() + 1;
        }
        if(right == null){
            return left.depth() + 1;
        }
        return Integer.max(left.depth(), right.depth()) + 1;
    }

    /**
     * platí, ak všetky hodnoty (v listoch stromu) sú rovnaké
     */
    @Override
    boolean isUnique() {
        Leaf leftLeaf = null;
        Leaf rightLeaf = null;
        if(left == null){
            if(right != null){
                return right.isUnique();
            }
            return true;
        }
        if(right == null){
            return left.isUnique();
        }
        if(left.getClass() == Leaf.class){
            leftLeaf = (Leaf) left;
        }
        if(right.getClass() == Leaf.class){
            rightLeaf = (Leaf) right;
        }
        if(rightLeaf != null && leftLeaf != null){
            return rightLeaf.value == leftLeaf.value;
        }
        return right.isUnique() && left.isUnique();
    }

    /**
     * prvky v ľavom podstrome sú menšie alebo rovné ako prvky v pravom podstrome, a to pre každý vnútorný vrchol
     */
    @Override
    boolean isOrdered() {
        if(left == null){
            if(right != null){
                return right.isOrdered();
            }
            return true;
        }
        if(right == null){
            return left.isOrdered();
        }

        return left.max() <= right.min() && left.isOrdered() && right.isOrdered();

    }

    @Override
    public String toString() {
        return "";
    }
}
