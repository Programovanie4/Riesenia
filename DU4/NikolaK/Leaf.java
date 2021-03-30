public class Leaf extends Tree {
    int value;

    /**
     * konstruktor
     */
    public Leaf(int value) {
        this.value = value;
    }

    /**
     * je maximálna hodnota stromu, null ak je strom prazdny
     */
    @Override
    Integer max() {

        return value;
    }

    @Override
    Integer min() {
        return value;
    }

    /**
     * hlbka stromu pre list 0
     */
    @Override
    int depth() {
        return 0;
    }

    /**
     * platí, ak všetky hodnoty (v listoch stromu) sú rovnaké
     */
    @Override
    boolean isUnique() {
        return true;
    }

    /**
     * prvky v ľavom podstrome sú menšie alebo rovné ako prvky v pravom podstrome, a to pre každý vnútorný vrchol
     */
    @Override
    boolean isOrdered() {
        return true;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
