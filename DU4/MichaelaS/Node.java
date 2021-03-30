public class Node extends Tree {
    Tree left, right;

    public Node(Tree left, Tree right) {
        this.left = left;
        this.right = right;
    }

    @Override
    Integer max() {
        if (left == null && right == null) { return null; }
        if (left == null || right == null) { return (left == null) ? right.max() : left.max(); }
        return Math.max(left.max(), right.max());
    }

    @Override
    int depth() {
        if (left == null && right == null) { return 1; }
        if (left == null || right == null) { return ((left == null) ? right.depth() : left.depth()) + 1; }
        return Math.max(left.depth(), right.depth()) + 1;
    }

    @Override
    boolean isUnique() {
        if (left == null && right == null) { return true; }
        if (left == null || right == null) { return (left == null) ? right.isUnique() : left.isUnique(); }

        if (left instanceof Leaf && right instanceof Leaf) { return left.max().equals(right.max()); }
        if (left instanceof Leaf && right.isUnique() || right instanceof Leaf && left.isUnique()) {
            return left.max().equals(right.max());
        }

        if (right.isUnique() && left.isUnique()) { return left.max().equals(right.max()); }
        return false;
    }

    @Override
    boolean isOrdered() {
        String nodes = this.toString().replaceAll("[^0-9]+", ",");
        int previous_number = 0;
        StringBuilder current_number = new StringBuilder();
        for (char ch: nodes.toCharArray()) {
            if (ch == ',') {
                if (current_number.length() == 0) { continue; }
                if (previous_number > Integer.parseInt(current_number.toString())) { return false; }
                previous_number = Integer.parseInt(current_number.toString());
                current_number.delete(0, current_number.length());
            }
            else { current_number.append(ch); }
        }
        return true;
    }

    @Override
    public String toString() {
        return "(" + ((left == null) ? null : left.toString()) + "," + ((right == null) ? null : right.toString()) + ")";
    }
}
