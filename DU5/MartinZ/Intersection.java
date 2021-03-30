import java.util.Arrays;

public class Intersection extends AbstractIntegerSet{
    AbstractIntegerSet s1;
    AbstractIntegerSet s2;
    public Intersection(AbstractIntegerSet a, AbstractIntegerSet b) {
        s1 = a;
        s2 = b;
    }
    @Override
    public boolean contains(Integer n) {
        return s1.contains(n) && s2.contains(n);
    }

    @Override
    public Integer[] getElements() {
        Integer[] rv = new Integer[Math.min(s1.size(), s2.size())];
        int index = 0;

        for(Integer i: s1.getElements()){
            if(s2.contains(i)){
                rv[index] = i;
                index++;
            }
        }
        return Arrays.copyOf(rv,index);

    }

}
