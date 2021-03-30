public class Leaf extends Tree {
    int value;

    public Leaf(int value) {
        this.value = value;
    }

    @Override
    Integer max() { return value; }

    @Override
    int depth() {
        return 0;
    }

    @Override
    boolean isUnique() {
        return true;
    }

    @Override
    boolean isOrdered() {
        return true;
    }

    @Override
    public String toString() { return "[" + value + "]"; }
}
