import java.util.Arrays;

public abstract class AbstractIntegerSet {
    public Integer[] arr;
    public int count;
    public abstract boolean contains(Integer n);   // vrati true ak mnozina obsahuje prvok s, inak false
    public abstract Integer[] getElements();   // vrati pole vsetkych prvkov, ktore su aktualne v mnozine (na poradi nezalezi)

    public int size() { return getElements().length; };   // vrati pocet prvkov, ktore su aktualne v mnozine

    public String toString() {
//        StringBuilder s = new StringBuilder("[");
//        System.out.println("tostring= "+ Arrays.toString(getElements()));
//        for (int i = 0; i < count; i++) {
//            if (getElements()[i] != null) {
//                if (i != 0)
//                    s.append(", ");
//                s.append(getElements()[i]);
//            }
//        }
//        return s.append("]").toString();
        return Arrays.toString(getElements());
    }
}
