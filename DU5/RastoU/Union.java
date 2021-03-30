import java.util.Objects;

public class Union extends AbstractIntegerSet{
    AbstractIntegerSet a, b;

    // Konstruktor, zapamata si ake mnoziny zjednocujeme
    public Union(AbstractIntegerSet a, AbstractIntegerSet b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public boolean contains(Integer n) {
        for (Integer i : getElements()) {
            if (Objects.equals(n, i)) return true;
        }
        return false;
    }

    @Override
    public Integer[] getElements() {
        IntegerSet s = new IntegerSet(a.size() + b.size());
        for (Integer iA : a.getElements()) s.add(iA);
        for (Integer iB : b.getElements()) s.add(iB);
        return s.getElements();
    }

}
