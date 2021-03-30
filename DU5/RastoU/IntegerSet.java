import java.util.Arrays;
import java.util.Objects;

public class IntegerSet extends AbstractIntegerSet {

    // Konstruktor vytvori prazdnu mnozinu s danou kapacitou.
    public IntegerSet(int capacity) {
        arr = new Integer[capacity];
    }

    // Prida prvok do mnoziny. Vrati true ak sa prvok naozaj pridal,
    // vrati false ak nie (lebo taky prvok v mnozine uz bol, alebo je mnozina naplnena - pocet prvkov == kapacita).
    // Pridat mozeme aj null.
    public boolean add(Integer n) {
        if (count == arr.length || contains(n)) return false;
        arr[count++] = n;
        return true;
    }

    @Override
    public boolean contains(Integer n) {
        for (int i = 0; i < count; i++) {
            if (Objects.equals(n, arr[i])) return true;
        }
        return false;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public Integer[] getElements() {
        Integer[] rv = new Integer[count];
        for (int i = 0; i < count; i++) rv[i] = arr[i];
        return rv;
    }

    public static void main(String[] args) {
        IntegerSet a = new IntegerSet(10), b = new IntegerSet(10), c = new IntegerSet(10);
        AbstractIntegerSet set =   // set == (a ∪ b) ∩ c
                new Intersection(
                        new Union(a, b),
                        c
                );
        Union u = new Union(a, b);
        System.out.println(u + " " + u.size());
        a.add(1); a.add(21);
        b.add(7); b.add(21);

        c.add(1); c.add(7); c.add(11);
//        System.out.println("hmm=" + Arrays.toString(new Intersection(
//                new Union(a, b),
//                c
//        ).getElements()));
        System.out.println("huh" + set);  // [1, 7]

        Intersection intersection = new Intersection(a, b);
        intersection.getElements();   // [21]
        System.out.println(intersection);
        a.add(42);
        b.add(42);
        System.out.println(intersection);   // [21, 42]
    }
}
