
public class HeapStringImplementation implements HeapStringInterface{
    String[] heap = new String[10];
    int count = 0;

    private void reallocate() {
        String[] new_heap = new String[2 * heap.length];
        System.arraycopy(heap, 0, new_heap, 0, heap.length);
        heap = new_heap;
    }

    private void swap(int i1, int i2) {
        String temp = heap[i1];
        heap[i1] = heap[i2];
        heap[i2] = temp;
    }

    private void heap_up(int index) {
        if (heap[index].compareTo(heap[(index-1)/2]) > 0) {
            swap(index, (index-1)/2);
        }
    }

    private void heap_down(int index) {
        int index_child = 2*index + 1;
        if (index_child+1 < count && heap[index_child].compareTo(heap[index_child+1]) < 0) { index_child += 1; }
        if (heap[index].compareTo(heap[index_child]) < 0) {
            swap(index, index_child);
        }
    }

    public String first() {
        if (count == 0) { return null; }
        return heap[0];
    }

    public String remove() {
        if (count == 0) { return null; }

        swap(0, count-1);
        String max = heap[count-1];
        heap[count-1] = null;
        count --;

        int index = 0;
        int left_child, max_child, right_child;
        while (true) {
            left_child = index*2 + 1;
            max_child = left_child;
            right_child = left_child + 1;
            if (left_child >= count) { break; }

            if (right_child < count && heap[right_child].compareTo(heap[left_child]) > 0) {
                max_child = right_child;
            }
            if (heap[max_child].compareTo(heap[index]) > 0) {
                heap_down(index);
                index = max_child;
            }
            else { break; }
        }
        return max;
    }

    // prida prvok str do haldy, halda zostane haldou
    public void insert(String str) {
        if (count == heap.length) { reallocate(); }
        heap[count] = str;
        int index = count;
        count ++;

        while (index > 0) {
            int index_parent = (index-1) / 2;
            if (heap[index].compareTo(heap[index_parent]) > 0) {
                heap_up(index);
                index = index_parent;
            }
            else { break; }
        }
    }

    // vrati velkost haldy, pocet prvkov
    public int size() {
        return count;
    }

}
