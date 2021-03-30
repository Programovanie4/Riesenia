import java.util.Arrays;

public abstract class AbstractIntegerSet {
    public abstract boolean contains(Integer n);   // vrati true ak mnozina obsahuje prvok s, inak false
    public abstract Integer[] getElements();   // vrati pole vsetkych prvkov, ktore su aktualne v mnozine (na poradi nezalezi)

    public int size() { return getElements().length; };   // vrati pocet prvkov, ktore su aktualne v mnozine
    public String toString(){return Arrays.toString(getElements());};

    public static void main(String[] args) {
        IntegerSet a = new IntegerSet(10), b = new IntegerSet(10), c = new IntegerSet(10);
        AbstractIntegerSet set =   // set == (a ∪ b) ∩ c
                new Intersection(
                        new Union(a, b),
                        c
                );
        a.add(1); a.add(21);
        b.add(7); b.add(21);
        c.add(1); c.add(7); c.add(11);
        System.out.println(set);  // [1, 7]

        Intersection intersection = new Intersection(a, b);
        intersection.getElements();   // [21]
        a.add(42);
        b.add(42);
        System.out.println(intersection);   // [21, 42]

        Union union = new Union(a, b);
        System.out.println(union);
        union.getElements();   // [1, 21, 7]
        a.add(24);
        System.out.println(union);
        union.getElements();   // [1, 21, 24, 7]
    }
}
