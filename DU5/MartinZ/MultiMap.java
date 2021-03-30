import java.util.Arrays;

public class MultiMap<K, V> {
    K[] keys;
    V[][] values;
    int keyNum = 0;

    public MultiMap(int maxCapacity) {
        keys = (K[]) new Object[maxCapacity];
        values = (V[][]) new Object[maxCapacity][];
    }

    public int numKeys() {
        return keyNum;
//        int rv = 0;
//        for(int i = 0; i<keys.length; i++){
//            if(keys[i] == null) return rv;
//            rv++;
//        }
//        return rv;
    }

    public int numValues() {
        int rv = 0;
        for(V[] v: values){
            if(v == null) return rv;
            rv += numVal(v);
        }
        return rv;
    }

    public V[] get(int idx_k) {
        if(values[idx_k] == null) return null;
        int count = numVal(values[idx_k]);
        V[] rv;
        rv = Arrays.copyOf(values[idx_k], count);
        return rv;
    }

    public V[] get(K k) {
        int index = 0;
        for(K key: keys){
            if(key == null) return null;
            if(key == k) return get(index);
            index++;
        }
        return null;
    }

    public void put(K k, V v) {
        int index = 0;
        for(K key: keys){
            if(key == k){
                break;
            }
            if(key == null){
                keyNum++;
                keys[index] = k;
                break;
            }
            index++;
        }
        addVal(index, v);
    }

    public V[] removeKey(K k) {
        int index = 0;
        for(K key: keys){
            if(key == k){
                keyNum--;
                key = null;
                V[] rv = get(index);
                rv = Arrays.copyOf(rv, rv.length);
                values[index] = null;
                shift(keys, index);
                shift(values, index);
                return rv;
            }
            index++;
        }
        return null;
    }

    public void removeValue(V v) {
        int index = 0;
        for(V[] arr: values){
            if(arr == null) return;
            for(int i = 0; i<arr.length; i++){
                if(arr[i] == null) break;
                if(arr[i] == v){
                    arr[i] = null;
                    shift(arr, i);
                    i--;
                }
            }
            if(values[index][0] == null){
                keyNum--;
                values[index]=null;
                keys[index] = null;
                shift(keys,index);
            }
            index++;
        }
    }
    private int numVal(V[] arr){
        int rv = 0;
        for(int i = 0; i<arr.length; i++){
            if(arr[i] == null) return rv;
            rv++;
        }
        return rv;
    }
    private void addVal(int index, V val){
        if(values[index] == null) values[index] = (V[]) new Object[10];
        V[] arr = values[index];

        for(int i = 0; i<arr.length; i++){
            if(arr[i] == null){
                arr[i] = val;
                return;
            }
        }
        int size = arr.length;
        arr = Arrays.copyOf(arr, size*2);
        arr[size] = val;
    }
    private<P> void shift(P[] arr, int index){
        while(index < arr.length-1){
            if(arr[index+1] == null){
                arr[index] = null;
                return;
            }
            arr[index] = arr[index+1];
            index++;
        }
    }
    private<P> void shift(P[][] arr, int index){
        while(index < arr.length-1){
            if(arr[index+1] == null){
                arr[index] = null;
                return;
            }
            arr[index] = arr[index+1];
            index++;
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

        System.out.println(mm.numKeys()); // 2
        System.out.println(mm.numValues()); // 4
    }
}
