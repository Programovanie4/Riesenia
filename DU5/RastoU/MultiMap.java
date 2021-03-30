import java.util.Arrays;
import java.util.Objects;

public class MultiMap<K, V> {
    K[] keys;
    V[][] values;
    int count;

    @SuppressWarnings("unchecked")
    public MultiMap(int maxCapacity) {
        // som zhrozeny z toho co som v tejto triede stvoril :D :(
        keys =  (K[]) new Object[maxCapacity];
        values = (V[][]) new Object[maxCapacity][maxCapacity];
    }

    public int numKeys() {
        if (keys == null || keys.length == 0) return 0;
        int count = 0;
        for (int i = 0; keys[i] != null; i++) count++;
        return count;
    }

    public int numValues() {
        if (values == null || values.length == 0 || numKeys() == 0) return 0;
        int count = 0;
        for (int i = 0; i < numKeys(); i++) {
            for (int j = 0; values[i][j] != null; j++) count++;
        }
        return count;
    }

    public V[] get(int idx_k) {
        if (keys == null || (idx_k >= numKeys() || idx_k < 0)) return null;
        int size = 0;
        while (values[idx_k][size] != null) size++;
        if (size == 0) return null;
        return Arrays.copyOfRange(values[idx_k], 0, size);
    }

    public int findIndexOfKey(K k) {
        if (keys == null || keys.length == 0 || keys[0] == null) return -1;
        int idx_k = 0;

        while (keys[idx_k] != k && idx_k < numKeys()) idx_k++;
        return idx_k;
    }

    public V[] get(K k) {
        return get(findIndexOfKey(k));
    }

    public <T> void  shiftLeft(T[] t, int from, int shift) {
        for (int i = from; i <= t.length - 1 - shift; i++) {
            t[i] = t[i+shift];
        }
        for (int i = t.length - 1; i > t.length - 1 - shift; i--) {
            t[i] = null;
        }
    }

    public void put(K k, V v) {
        int idx_k = findIndexOfKey(k);
        if (0 <= idx_k && idx_k < numKeys()) {
            int i = 0;
            while (values[idx_k][i] != null) i++;
            values[idx_k][i] = v;
        } else {
            int numOfKeys = numKeys();
            keys[numOfKeys] = k;
            values[numOfKeys][0] = v;
        }
    }

    public V[] removeKey(K k) {
        int idx_k = findIndexOfKey(k);
        if (0 > idx_k || idx_k >= numKeys()) return null;
        int size = 0;
        while (values[idx_k][size] != null) size++;
        V[] rv = Arrays.copyOfRange(values[idx_k], 0, size);
        Arrays.fill(values[idx_k], null);
        shiftArraysLeftByOne(idx_k);
        shiftLeft(keys, idx_k, 1);
        return rv;
    }
    public void shiftArraysLeftByOne(int from) {
        for (int i = from; i <= values.length - 2; i++) {
            values[i] = Arrays.copyOfRange(values[i+1], 0, values.length);
        }
        for (int i = values.length - 1; i > values.length - 2; i--) {
            Arrays.fill(values[i], null);
        }
    }

    public void removeValue(V v) {
        for (int i = 0; i < numKeys(); i++) {
            int j = 0;
            while (values[i][j] != null) {
                if (values[i][j] == v) {
                    shiftLeft(values[i], j, 1);
                } else j++;
            }
            if (j == 0) shiftLeft(keys, i, 1);
        }
    }

    public static void main(String[] args) {
        MultiMap<String, Integer> mm = new MultiMap<String, Integer>(10);

        mm.put("kluc1", 1);
        mm.put("kluc1", -1);
        mm.put("kluc2", 2);
        mm.put("kluc2", 222);
        mm.put("kluc2", 22);
        mm.put("kluc3", 3);
        mm.put("kluc3", 2);
        mm.put("kluc3", 3);

        System.out.println("k=" + Arrays.toString(mm.keys));
        System.out.println("v=" + Arrays.deepToString(mm.values));

        /* mapa obsahuje tieto hodnoty a v tomto poradi
          k: "kluc1", v=[1,-1]
          k: "kluc2", v=[2,222,22]
          k: "kluc3", v=[3,2,3]
        */

        System.out.println(Arrays.toString(mm.get(1))); // [2, 222, 22]
        System.out.println(Arrays.toString(mm.get("kluc1"))); // [1, -1]
        System.out.println(mm.numKeys()); // 3
        System.out.println(mm.numValues()); // 8

        mm.removeValue(2);
        System.out.println("vals= " + Arrays.deepToString(mm.values));
        System.out.println(Arrays.toString(mm.get(0))); // [1, -1]
        System.out.println(Arrays.toString(mm.get(1))); // [222, 22]
        System.out.println(Arrays.toString(mm.get(2))); // [3, 3]

        mm.removeKey("kluc1");  // cely riadok mazeme, takze kluce sa poposuvaju
        System.out.println(Arrays.toString(mm.get(0))); // [222, 22]
        System.out.println(Arrays.toString(mm.get(1))); // [3, 3]
        System.out.println(Arrays.toString(mm.get(2))); // null

        System.out.println(Arrays.toString(mm.get("kluc1"))); // null
        System.out.println(Arrays.toString(mm.get("kluc2"))); // [222, 22]
        System.out.println(Arrays.toString(mm.get("kluc3"))); // [3, 3]
        System.out.println("vals= " + Arrays.deepToString(mm.values));
        System.out.println("keys= " + Arrays.toString(mm.keys));

        System.out.println(mm.numKeys()); // 2
        System.out.println(mm.numValues()); // 4
    }
}
