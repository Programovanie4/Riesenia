import java.util.Arrays;

public class IntegerSet extends AbstractIntegerSet{

    Integer[] set;
    boolean contNull = false;
    int last = 0;

    public IntegerSet(int n) {
        set = new Integer[n];
    }

    @Override
    public boolean contains(Integer n) {
        if(n == null){
            return contNull;
        }
        for(int i = 0; i<last; i++)
            if(set[i].equals(n)) return true;
        return false;
    }

    @Override
    public Integer[] getElements() {
        int size = last;
        if(contNull) size++;
        return Arrays.copyOf(set, size);
    }

    public boolean add(Integer n) {
        if(isFull()) return false;
        if(n == null){
            if(contNull) return false;
            contNull = true;
            return true;
        }
        for(int i = 0; i<last; i++)
            if(set[i].equals(n)) return false;
        set[last] = n;
        last++;
        return true;
    }
    private boolean isFull(){
        int size = set.length;
        if(contNull) size++;
        return last == size;
    }
}
