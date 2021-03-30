import java.util.Arrays;

public class HeapStringImplementation implements HeapStringInterface {

    String[] pole = new String[10];
    int pocet = 0;

    public void add(String x) {
        if (!(pocet < pole.length)) {
            String[] novePole = new String[2*pole.length];
            for(int i=0; i < pole.length; i++)
                novePole[i] = pole[i];
            pole = novePole;
        }
        pole[pocet++] = x;
    }

    @Override
    public String first() {
        if (pocet == 0)
            return null;
        return pole[0];
    }

    @Override
    public String remove() {
        if (pocet == 0)
            return null;
        swap(0, pocet - 1);
        String temp = pole[pocet - 1];
        pole[pocet - 1] = null;
        if (--pocet > 0)
            heapDown(0);
        return temp;
    }

    public void heapDown(int i) {
        if (2*i + 1 >= pocet) return;
        int right = 0;
        if (2*i + 2 < pocet && pole[2*i + 1].compareTo(pole[2*i + 2]) < 0)
            right = 1;
        if (2 * i + 1 + right < pocet) {
            if (pole[i].compareTo(pole[2 * i + 1 + right]) < 0) {
                swap(2 * i + 1 + right, i);
                heapDown(2 * i + 1 + right);
            }
        }
    }

    public void heapUp(int i) {
        if (i > 0) {
            if (pole[(i-1)/2].compareTo(pole[i]) < 0) {
                swap((i-1)/2, i);
                heapUp((i-1)/2);
            }
        }
    }

    public void swap(int i, int j) {
        String temp = pole[i];
        pole[i] = pole[j];
        pole[j] = temp;
    }

    @Override
    public void insert(String str) {
        add(str);
        heapUp(pocet - 1);
    }

    @Override
    public int size() {
        return pocet;
    }
}
