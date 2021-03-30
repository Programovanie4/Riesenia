import java.util.Arrays;
import java.util.Objects;

public class Intersection  extends  AbstractIntegerSet {
    AbstractIntegerSet a, b;

    // Konstruktor, zapamata si ake mnoziny tvoria prienik
    public Intersection(AbstractIntegerSet a,AbstractIntegerSet b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public boolean contains(Integer n) {
        for (Integer integer : getElements()) {
            if (Objects.equals(n, integer)) return true;
        }
        return false;
    }

    @Override
    public Integer[] getElements() {
        IntegerSet s = new IntegerSet(Math.min(a.size(), b.size()));
//        System.out.println("a=" + Arrays.toString(a.getElements()));
//        System.out.println("b=" + Arrays.toString(b.getElements()));
        for (Integer iA : a.getElements()) {
            if (b.contains(iA)) s.add(iA);
        }
        return s.getElements();
    }


}
