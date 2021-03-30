import java.util.Arrays;

public class Union extends AbstractIntegerSet{
    AbstractIntegerSet s1;
    AbstractIntegerSet s2;
    public Union(AbstractIntegerSet a, AbstractIntegerSet b) {
        s1 = a;
        s2 = b;
    }
    @Override
    public boolean contains(Integer n) {
        return s1.contains(n) || s2.contains(n);
    }

    @Override
    public Integer[] getElements() {
        Integer[] rv = Arrays.copyOf(s1.getElements(), s1.size()+s2.size());
        int index = s1.size();
        for(Integer i: s2.getElements()){
            if(!s1.contains(i)){
                rv[index] = i;
                index++;
            }
        }
        return Arrays.copyOf(rv, index);

    }
}
